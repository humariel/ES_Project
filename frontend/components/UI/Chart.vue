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
  <div id="chart">
    <apexcharts type="line" height="350" ref="chart" :options="chartOptions" :series="series"></apexcharts>
  </div>
</template>

<script>
export default {
  props: {
    series: Array,
    title: String,
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
      chartOptions: {
        chart: {
          id: "realtime",
          height: 200,
          type: "line",
          animations: {
            enabled: true,
            easing: "linear",
            dynamicAnimation: {
              speed: 250
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