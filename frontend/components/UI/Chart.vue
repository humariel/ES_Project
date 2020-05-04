<template>
  <!-- <chartjs-line
        :backgroundcolor="bgColor"
        :bordercolor="borderColor"
        :beginzero="true"
        :bind="true"
        :data="vals"
        :datalabel="label"
        :labels="labels"
  />-->
  <div>

    <my-line :options="{
      scales: {
          xAxes: [{
              ticks: {
                maxTicksLimit: 10,
              }
          }]
      }
    }" :chart-data="{
      labels: labels,
      datasets: data
    }"></my-line>

    <!-- <chartjs-line
      ref="chart"
      :options="options"
      :datalabel="title.charAt(0).toUpperCase() + title.slice(1)"
      :data="data"
      :labels="labels"
      :bind="true"
    />
 -->
    <!-- <apexcharts type="line" height="350" ref="chart" :options="chartOptions" :series="series"></apexcharts> -->
  </div>
</template>

<script>

import moment from 'moment';

export default {
  props: {
    series: Array,
    title: String,
    values: Array
  },
  computed: {
    labels() {
      return this.values[0].data.map(v => moment(new Date(v.label)).format('HH:mm:ss'))
    },
    data() {
      return this.values.map(a => ({
        label: a.label,
        data: a.data.map(v => v.value)
      }))
    }
  },
  // computed: {
  //   series() {
  //     return [
  //         {
  //           name: this.label.slice(0, 1).toUpperCase() + this.label.slice(1),
  //           data: this.values.map(v => [new Date(v.label).getTime(), v.value.toFixed(2)])
  //         }
  //     ]
  //   },
  // },
  data() {
    return {
      options: {
        scales: {
          xAxes: [{
          }]
        }
      },
      chartOptions: {
        chart: {
          id: "realtime",
          height: 200,
          type: "line",
          animations: {
            enabled: false,
            easing: "linear",
            dynamicAnimation: {
              speed: 0
            }
          },
          toolbar: {
            show: false
          },
          zoom: {
            enabled: false
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          curve: "smooth"
        },
        title: {
          text: this.title.slice(0, 1).toUpperCase() + this.title.slice(1),
          align: "left"
        },
        markers: {
          size: 0
        },
        xaxis: {
            type: 'datetime',
            tickAmount: 6,
            labels: {
                formatter: function (value, timestamp) {
                    const date = new Date(timestamp)
                    return `${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')}:${date.getSeconds().toString().padStart(2, '0')}`
                }, 
            }
        },
        legend: {
          show: false
        }
      }
    };
  },
  mounted: function() {
    console.log(this.$refs.chart)
    /* var me = this;
    window.setInterval(function() {
      getNewSeries(lastDate, {
        min: 10,
        max: 90
      });

      me.$refs.chart.updateSeries([
        {
          data: data
        }
      ]);
    }, 1000);

    // every 60 seconds, we reset the data to prevent memory leaks
    window.setInterval(function() {
      resetData();

      me.$refs.chart.updateSeries(
        [
          {
            data
          }
        ],
        false,
        true
      );
    }, 60000); */
  }
};
</script>