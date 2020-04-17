
<template>
  <div class="table">
    <div class="table--title">
      BreathEasy Entities
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
      <div :class="{new: value.new}" class="table__content__row" v-for="value in values" :key="value.id">
          <div :class="{change: value.changes && value.changes.includes(key)}" class="table__content__row--column" v-for="key in keys" :key="key">
            {{value[key]}}
          </div>
      </div>
    </div>
  </div>
</template>

<script>

import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

export default {

  data() {
    return {
      values: []
    }
  },
  mounted() {
    const serverUrl = 'http://localhost:8080/breatheasy'
    let ws = new SockJS(serverUrl);
    const stompClient = Stomp.over(ws);

    stompClient.debug = () => {}
    stompClient.connect({}, () => {
      stompClient.subscribe("/topic/entity", (message) => {
        const { currently, ...rest } = JSON.parse(message.body)
        const { apparentTemperature, dewPoint, offset, ...entity } = rest

        const oldValue = this.values.findIndex(v => v.latitude == entity.latitude && v.longitude == entity.longitude)

        if(oldValue == -1) {
          const val = {
            ...entity,
            ...currently,
            new: true,
            changes: [],
          }
          this.values.unshift(val)
          setTimeout(() => {
            val.new = false
          }, 1500)
        } else {
          for(var key of this.keys) {
            if(key in currently) {
              if(this.values[oldValue][key] != currently[key])
                this.values[oldValue].changes.push(key)
              this.values[oldValue][key] = currently[key]
            }
          }
          setTimeout(() => {
            this.values[oldValue].changes = []
          }, 1500)
        }
      })
    });
  },
  computed: {
    keys() {
      return this.values.length > 0 ? Object.keys(this.values[0]).filter(k => !['apparentTemperature', 'dewPoint', 'offset', 'new', 'changes', 'nChanges'].includes(k)) : []
    }
  }

}

</script>

<style lang="scss" scoped>

body {
  box-sizing: border-box;
}

.table {
  padding: 2rem 3rem;
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
    width: 100%;
    display: grid;
    grid-template-columns: 4fr 4fr 4fr 4fr 2.5fr 2.5fr 2.5fr 3.5fr 3.5fr 2.5fr 2.5fr 2.5fr;
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
      &.new {
        background-color: goldenrod;
      }
      &:not(.new) {
        transition: background-color 1s ease;
      }
      width: 100%;
      display: grid;
      grid-template-columns: 4fr 4fr 4fr 4fr 2.5fr 2.5fr 2.5fr 3.5fr 3.5fr 2.5fr 2.5fr 2.5fr;
      & > * {
        transition: background-color 1s ease;
        padding: .25rem;
        &:not(:last-child) {
          border-right: 1px solid rgb(144, 106, 11);
        }
        &.change {
        background-color: goldenrod;
        }
      }
    }
  }
}



</style>
