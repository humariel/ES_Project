import Vue from "vue";

import "chart.js";
import "hchs-vue-charts";
Vue.use(window.VueCharts);

import VueApexCharts from 'vue-apexcharts'
Vue.component('apexcharts', VueApexCharts)

import { Line, mixins } from 'vue-chartjs'
const { reactiveProp } = mixins

Vue.component('my-line', {
    extends: Line,
    mixins: [reactiveProp],
    props: ['options'],
    mounted () {
      // this.chartData is created in the mixin.
      // If you want to pass options please create a local options object
      this.renderChart(this.chartData, this.options)
    }
})

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

// Install BootstrapVue
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)

// This imports all the layout components such as <b-container>, <b-row>, <b-col>:
import { LayoutPlugin } from 'bootstrap-vue'
Vue.use(LayoutPlugin)

// This imports <b-modal> as well as the v-b-modal directive as a plugin:
import { ModalPlugin } from 'bootstrap-vue'
Vue.use(ModalPlugin)

// This imports <b-card> along with all the <b-card-*> sub-components as a plugin:
import { CardPlugin } from 'bootstrap-vue'
Vue.use(CardPlugin)

// This imports directive v-b-scrollspy as a plugin:
import { VBScrollspyPlugin } from 'bootstrap-vue'
Vue.use(VBScrollspyPlugin)

// This imports the dropdown and table plugins
import { DropdownPlugin, TablePlugin } from 'bootstrap-vue'
Vue.use(DropdownPlugin)
Vue.use(TablePlugin)

export default async (ctx) => {
  await ctx.store.dispatch('nuxtClientInit', ctx)
}
