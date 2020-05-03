
<template>
  <div class="map">
    <l-map :class="{margin: selectedEntity ? selectedEntity.id : null}" class="map--map" @click="clickedInsideParish" :zoom=13 :center="[40.627343, -8.654386]">
      <l-tile-layer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png" />
      <l-marker @click="selectedEntity = {type: 'sensor', id: entity.id, parish: entity.parish.properties.Freguesia}" v-for="entity of entities" :key="entity.id" :lat-lng="entity.location">
        <l-tooltip>
          <div><span>ID: </span>{{entity.id}}</div>
          <div><span>Location: </span>{{entity.location}}</div>
          <div v-for="key of keys" :key="key">
            <span>{{key}}:</span>
            {{entity[key].toFixed(2)}}
          </div>
        </l-tooltip>
      </l-marker>
      <l-polygon @click="onParishClick(parish)" v-for="parish in geojson.features" :key="parish.properties.id" :lat-lngs="[parish.geometry.coordinates[0].map(a => [a[1], a[0]])]" :color="'blue'" :fillColor="'#000000aa'">
      </l-polygon>
    </l-map>
    <transition mode="out-in" name="translate-fade">
      <div :key="selectedEntity.id" class="map__sidebar" v-if="selectedEntity">
        <template v-if="selectedEntity.type == 'sensor'">
          <div>{{selectedEntity.id}} ({{selectedEntity.parish}})</div>
          <div>Last 100 values</div>
          <Chart v-for="key of keys" :title="key" :key="key" :series="[{
            name: key.slice(0, 1).toUpperCase() + key.slice(1),
            data: values[selectedEntity.id][key].map(v => [new Date(v.label).getTime(), v.value.toFixed(2)])
          }]"/>
        </template>
        <template v-else>
          <div>{{selectedEntity.parish}}</div>
          <div>Last 100 values from all sensors in the parish</div>
          <Chart :title="selectedEntity.id" :series="seriesParish()"/>
        </template>
      </div>
    </transition>
  </div>
</template>

<script>

import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import axios from 'axios'
import Chart from '@/components/UI/Chart'

export default {
  components: {
    Chart
  },
  data() {
    return {
      mapOptions: {
        style: function style(feature) {
          return {
            weight: 2,
            opacity: 1,
            color: 'red',
            fillOpacity: 0.3
          };
        },   
        onEachFeature: this.onEachFeature
      },
      entities: [],
      selectedEntity: null,
      values: {}
    }
  },
  async asyncData() {
    return {
      geojson: (await axios.get('/geojson/aveiro.geojson')).data
    }
  },
  async created () {
    const serverUrl = 'http://localhost:8080/breatheasy'
    let ws = new SockJS(serverUrl);
    const stompClient = Stomp.over(ws);
    stompClient.debug = () => {}

    stompClient.connect({}, () => {

      console.log("Connected")

      stompClient.subscribe("/topic/value", (message) => {
        const value = JSON.parse(message.body)

        // Adicionar entidade ao array de markers
        const idx = this.entities.findIndex(e => e.id == value.entity)
        if(idx != -1)
          this.entities.splice(idx, 1)
        this.entities.push({
          ...value,
          id: value.entity,
          location: value.location.coords,
          parish: this.getParish(value.parish)
        })

        // Adicionar valor ao array de valores da entidade
        if(!this.values[value.entity])
          this.values[value.entity] = {}
        for(var key of this.keys) {
          if(!this.values[value.entity][key])
            this.values[value.entity][key] = []
          this.values[value.entity][key] = this.values[value.entity][key].concat({
            label: value.timestamp,
            value: value[key]
          })
          if(this.values[value.entity][key].length > 50) 
            this.values[value.entity][key] = this.values[value.entity][key].slice(1);
        }
        
      })
      
    });

  },
  methods:{
    isPointInsidePolygon(point, poly) {
      var polyPoints = poly;       
      var x = point.lat, y = point.lng;
      var inside = false;
      for (var i = 0, j = polyPoints.length - 1; i < polyPoints.length; j = i++) {
          var xi = polyPoints[i][1], yi = polyPoints[i][0];
          var xj = polyPoints[j][1], yj = polyPoints[j][0];

          var intersect = ((yi > y) != (yj > y))
              && (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
          if (intersect) inside = !inside;
      }
      return inside;
    },
    clickedInsideParish(event){
      if(!this.geojson.features.some(cur => this.isPointInsidePolygon(event.latlng,cur.geometry.coordinates[0]))){
        this.selectedEntity = null
      }
    },
    onParishClick(parish) {
      this.selectedEntity = {
        type: 'parish', 
        id: parish.properties.id, 
        parish: parish.properties.Freguesia
      }
    },
    getParishSensors(parishId){
      return this.entities.filter(x => x.parish.properties.id == parishId)
    },
    seriesParish(){
        let series = []
        let sensors = this.getParishSensors(this.selectedEntity.id)
        for(let s in sensors){
            series.push({
              name: sensors[s].id,
              data: this.values[sensors[s].id]['temperature'].map(v => [new Date(v.label).getTime(), v.value.toFixed(2)])
            })
        }
        return series
    },
    seriesSensor(label,values){
      return []
    },
    getParish(parishId){
      return this.geojson.features.find(x => x.properties.id == parishId)
    },
  },
  computed: {
    keys() {
      return Object.keys(this.entities[0]).filter(k => !['id', 'entity', 'location', 'timestamp', 'parish'].includes(k))
    },
  }
}

</script>

<style lang="scss" scoped>

body {
  box-sizing: border-box;
}

.map {
  display: flex;
  position: relative;
  overflow: hidden;
  height: 100vh;
  width: 100%;
  &--map {
    transition: margin .5s;
    width: 100%;
    height: 100%;
    z-index: 1;
    &.margin {
      margin-right: 20rem;
    }
  }
  &__sidebar {
    position: absolute;
    right: 0;
    top: 0;
    height: 100%;
    max-height: 100%;
    overflow: auto;
    width: 30rem;
    background-color: white;
    z-index: 2;
  } 
}

.translate-fade-enter-active, .translate-fade-leave-active {
  transition: transform .5s;
}
.translate-fade-enter, .translate-fade-leave-to {
  transform: translateX(110%);
}

</style>
