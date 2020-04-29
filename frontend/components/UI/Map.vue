<template>
  <div id="map-wrap" style="height: 100vh">
    <l-map :zoom=13 :center="[40.627343, -8.654386]">
      <l-tile-layer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"></l-tile-layer>
      <l-geo-json :geojson="geojson"></l-geo-json>
      <!-- GLORIA E VERA CRUZ -->
      <l-marker :lat-lng="[40.633084, -8.660537]"></l-marker>
      <l-marker :lat-lng="[40.635691, -8.659498]"></l-marker>
      <l-marker :lat-lng="[40.627348, -8.653447]"></l-marker>
      <l-marker :lat-lng="[40.631554, -8.650752]"></l-marker>
      <l-marker :lat-lng="[40.639417, -8.646243]"></l-marker>
      <!-- ARADAS -->
      <l-marker :lat-lng="[40.623084, -8.658191]"></l-marker>
      <l-marker :lat-lng="[40.614370, -8.639945]"></l-marker>
      <l-marker :lat-lng="[40.607973, -8.627285]"></l-marker>
      <l-marker :lat-lng="[40.622814, -8.640426]"></l-marker>
      <l-marker :lat-lng="[40.606837, -8.628787]"></l-marker>
      <!-- SANTA JOANA -->
      <l-marker :lat-lng="[40.637649, -8.618011]"></l-marker>
      <l-marker :lat-lng="[40.633883, -8.608838]"></l-marker>
      <l-marker :lat-lng="[40.628412, -8.606859]"></l-marker>
      <l-marker :lat-lng="[40.619797, -8.608511]"></l-marker>
      <l-marker :lat-lng="[40.628216, -8.617392]"></l-marker>
      <!-- SAO BERNARDO -->
      <l-marker :lat-lng="[40.624584, -8.628796]"></l-marker>
      <l-marker :lat-lng="[40.623402, -8.623879]"></l-marker>
      <l-marker :lat-lng="[40.615590, -8.628901]"></l-marker>
      <l-marker :lat-lng="[40.612623, -8.616170]"></l-marker>
      <l-marker :lat-lng="[40.613307, -8.623369]"></l-marker>
      <!-- OLIVEIRINHA -->
      <l-marker :lat-lng="[40.600206, -8.600228]"></l-marker>
      <l-marker :lat-lng="[40.605274, -8.595435]"></l-marker>
      <l-marker :lat-lng="[40.602856, -8.603787]"></l-marker>
      <l-marker :lat-lng="[40.599854, -8.600715]"></l-marker>
      <l-marker :lat-lng="[40.603839, -8.602905]"></l-marker>
      <!-- N. SENHORA DE FATIMA -->
      <l-marker :lat-lng="[40.577787, -8.578555]"></l-marker>
      <l-marker :lat-lng="[40.576442, -8.576356]"></l-marker>
      <l-marker :lat-lng="[40.576238, -8.580648]"></l-marker>
      <l-marker :lat-lng="[40.572755, -8.573448]"></l-marker>
      <l-marker :lat-lng="[40.569960, -8.567665]"></l-marker>
      <!-- EIXO -->
      <l-marker :lat-lng="[40.624765, -8.564652]"></l-marker>
      <l-marker :lat-lng="[40.629211, -8.570452]"></l-marker>
      <l-marker :lat-lng="[40.622241, -8.549117]"></l-marker>
      <l-marker :lat-lng="[40.622245, -8.566591]"></l-marker>
      <l-marker :lat-lng="[40.616955, -8.565777]"></l-marker>
      <!-- ESGUEIRA -->
      <l-marker :lat-lng="[40.649415, -8.623272]"></l-marker>
      <l-marker :lat-lng="[40.648634, -8.614002]"></l-marker>
      <l-marker :lat-lng="[40.649687, -8.629945]"></l-marker>
      <l-marker :lat-lng="[40.647815, -8.631747]"></l-marker>
      <l-marker :lat-lng="[40.655780, -8.614240]"></l-marker>
      <!-- CACIA -->
      <l-marker :lat-lng="[40.665585, -8.612773]"></l-marker>
      <l-marker :lat-lng="[40.701724, -8.620130]"></l-marker>
      <l-marker :lat-lng="[40.693423, -8.593055]"></l-marker>
      <l-marker :lat-lng="[40.680955, -8.582951]"></l-marker>
      <l-marker :lat-lng="[40.685136, -8.589633]"></l-marker>
      <!-- SAO JACINTO -->
      <l-marker :lat-lng="[40.666293, -8.743672]"></l-marker>
      <l-marker :lat-lng="[40.675970, -8.720291]"></l-marker>
      <l-marker :lat-lng="[40.694182, -8.732699]"></l-marker>
      <l-marker :lat-lng="[40.656854, -8.741412]"></l-marker>
      <l-marker :lat-lng="[40.664041, -8.729213]"></l-marker>
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

