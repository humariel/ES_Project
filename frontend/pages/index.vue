
<template>
  <div class="map">
    <l-map :class="{margin: selectedEntity}" class="map--map" @click="selectedEntity = null" :zoom=13 :center="[40.627343, -8.654386]">
      <l-tile-layer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png" />
      <l-geo-json :geojson="geojson" />
      <l-marker @click="selectedEntity = entity.id" v-for="entity of entities" :key="entity.id" :lat-lng="entity.location">
        <l-tooltip>
          <div v-for="key of Object.keys(entity)" :key="key">
            <span>{{key}}:</span>
            {{entity[key]}}
          </div>
        </l-tooltip>
      </l-marker>
    </l-map>
    <transition name="translate-fade">
      <div :key="selectedEntity" class="map__sidebar" v-if="selectedEntity">
        <div>{{selectedEntity}}</div>
        <div>Últimos 100 valores</div>
        <Chart v-for="key of keys" :key="key" :label="key" :values="values[selectedEntity][key]"/>
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
          location: value.location.coords
        })

        // Adicionar valor ao array de valores da entidade
        if(!this.values[value.entity])
          this.values[value.entity] = {}
          
        for(var key of this.keys) {
          if(!this.values[value.entity][key])
            this.values[value.entity][key] = []
          this.values[value.entity][key].unshift({
            label: value.timestamp,
            value: value[key]
          })
          this.values[value.entity][key] = this.values[value.entity][key].slice(0, 100);
        }
        
      })
      
    });

  },
  computed: {
    keys() {
      return Object.keys(this.entities[0]).filter(k => !['id', 'entity', 'location', 'timestamp', 'parish'].includes(k))
    }
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
