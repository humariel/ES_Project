
<template>
  <div class="table">
    <div class="table--title">
      BreatheEasy Devices
    </div>
    <div class="table__header">
      <div v-for="(key, index) in keys" :key="index">
        {{key}}
      </div>
    </div>
    <div class="table__content">
      <!-- <div v-for="(key, index) in keys" :key="index">
        {{Object.keys(test[index])}}
      </div> -->
      <div class="table__content__row" v-for="value in test" :key="value.id">
          <div class="table__content__row--column" v-for="key in keys" :key="key">
            {{value[key]}}
          </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {

  data() {
    return {
      test: [{  
        id: 0,
        lat:38,
        lon:-78,
        city_name:"Louisa",
        timestamp_utc:"2019-03-04T17:00:00",
        aqi:4,
        o3:57.3,
        so2:3.4,
        no2:5.4,
        pm10:2.1,
        pm25:0.9,
        co:37 
      },{  
        id: 1,
        lat:38,
        lon:-78,
        city_name:"Louisa",
        timestamp_utc:"2019-03-04T17:00:00",
        aqi:4,
        o3:57.3,
        so2:3.4,
        no2:5.4,
        pm10:2.1,
        pm25:0.9,
        co:37 
      },{  
        id: 2,
        lat:38,
        lon:-78,
        city_name:"Louisa",
        timestamp_utc:"2019-03-04T17:00:00",
        aqi:4,
        o3:57.3,
        so2:3.4,
        no2:5.4,
        pm10:2.1,
        pm25:0.9,
        co:37 
      },{  
        id: 3,
        lat:38,
        lon:-78,
        city_name:"Louisa",
        timestamp_utc:"2019-03-04T17:00:00",
        aqi:4,
        o3:57.3,
        so2:3.4,
        no2:5.4,
        pm10:2.1,
        pm25:0.9,
        co:37 
      }]
    }
  },
  computed: {
    keys() {
      return Object.keys(this.test[0])
    }
  }

}

</script>

<style lang="scss" scoped>

body {
  box-sizing: border-box;
}

.table {
  padding: 2rem 4rem;
  width: 100%;
  display: flex;
  align-items: center;
  flex-direction: column;
  &--title {
    font-weight: bold;
    color: rgb(144, 106, 11);
    font-size: 1.8rem;
    margin-bottom: 1rem;
  }
  &__header {
    font-weight: bold;
    grid-column-gap: .2rem;
    width: 100%;
    display: grid;
    grid-template-columns: 3fr 3fr 3fr 6fr 9fr 2fr 2fr 2fr 2fr 3fr 3fr 2fr;
    border: 1px solid rgb(144, 106, 11);
    & > * {
      padding: .25rem;
      &:not(:last-child) {
        border-right: 1px solid rgb(144, 106, 11);
      }
    }
  }
  &__content {
    border: 1px solid rgb(144, 106, 11);
    border-top: 0;
    width: 100%;
    display: flex;
    align-items: center;
    flex-direction: column;
    &__row {
      &:not(:last-child) {
        border-bottom: 1px solid rgb(144, 106, 11);
      }
      & > * {
        padding: .25rem;
        &:not(:last-child) {
          border-right: 1px solid rgb(144, 106, 11);
        }
      }
      width: 100%;
      display: grid;
      grid-template-columns: 3fr 3fr 3fr 6fr 9fr 2fr 2fr 2fr 2fr 3fr 3fr 2fr;
      grid-column-gap: .2rem;
    }
  }
}



</style>
