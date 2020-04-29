<template>
  <div id="map-wrap" style="height: 100vh">
    <l-map :zoom=13 :center="[40.627343, -8.654386]">
      <l-tile-layer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"></l-tile-layer>
      <l-geo-json :geojson="geojson"></l-geo-json>
      <l-marker :lat-lng="[47.413220, -1.219482]"></l-marker>
    </l-map>
  </div>
</template>

<script>
import {LMap, LTileLayer, LGeoJson} from 'vue2-leaflet';
import axios from 'axios'

export default {
  components: {
    LMap,
    LTileLayer,
    LGeoJson
  },
  data () {
    return {
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      zoom: 8,
      center: [47.313220, -1.319482],
      geojson: null
    };
  },
  async created () {
    let res = await axios.get('/geojson/aveiro.geojson')
    this.geojson = res.data;
  }
}
</script>

