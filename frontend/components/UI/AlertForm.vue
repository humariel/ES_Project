<template>
  <b-form @submit="onAlertCreation" @reset="onAlertReset">

    <span style="margin-top: 1rem; display: block" v-if="conditions.length">{{(conditions)}}</span> 
    
    <b-form @submit="$event.stopPropagation(); $event.preventDefault(); conditions.push({
          type,
          operation,
          threshold: value
        }); type = null; operation = null; value = ''">
      <div style="display: flex">

        <div style="width: 100%; margin-right: 2.5px; margin-bottom: -1rem">
          <h5>TYPE</h5>
          <b-form-group id="input-group-3" label-for="input-3">
            <b-form-select
              id="input-3"
              v-model="type"
              :options="verticals"
              placeholder="Choose the type"
              required
            >
              <template v-slot:first>
                <b-form-select-option :value="null" disabled>Choose a type</b-form-select-option>
              </template>
            </b-form-select>
          </b-form-group>
        </div>

        <div style="width: 100%; margin-left: 2.5px; margin-bottom: -1rem">
          <h5>OPERATION</h5>
          <b-form-group id="input-group-3" label-for="input-3">
            <b-form-select
              id="input-3"
              v-model="operation"
              :options="[{value: '>', text: 'Higher than'},{value: '<', text: 'Lower than'}]"
              required
              placeholder="Choose the operation"
            >
              <template v-slot:first>
                <b-form-select-option :value="null" disabled>Choose the operation</b-form-select-option>
              </template>
            </b-form-select>
          </b-form-group>
        </div>

      </div>

      <div style="display: flex; align-items:flex-end">
        
        <div style="width: 100%; margin-right: 2.5px">
          <h5>VALUE</h5>
          <b-form-group
            id="input-group-1"
            label-for="input-1"
          >
            <b-form-input
              id="input-1"
              v-model="value"
              required
              placeholder="Enter the threshold value"
            ></b-form-input>
          </b-form-group>
        </div>

        <b-button type="submit" variant="primary" style="width: 25.5rem; margin-left: 2.5px; height: 2.4rem; margin-bottom: 1rem">Add Condition</b-button>

      </div>
    </b-form>

    <b-button v-if="conditions.length" type="submit" variant="primary">Submit</b-button>
    <b-button v-if="changed" @click="onAlertReset" variant="danger">Reset</b-button>
  
  </b-form>
</template>

<script>

import axios from 'axios'

export default {
  props:{
    verticals: Array,
    parish: String
  },
  data(){
    return{
      type: null,
      operation: null,
      time: '',
      value: '',
      conditions: []
    }
  },
  computed: {
    changed() {
      return this.type != null || this.operation != null || this.time != '' || this.value != '' || this.conditions.length
    }
  },
  methods:{
    async onAlertCreation(evt) {
      evt.preventDefault()
      let newAlarm = {
        parish: this.parish,
        time: this.time,
        conditions: this.conditions
      }
      await axios({
        method: 'post',
        url: 'http://localhost:8080/alarm',
<<<<<<< HEAD
        data: {
          parish: this.parish,
          time: 10,
          conditions: this.conditions
=======
        data: newAlarm
      })
      let currentAlarms = this.$store.state.alarms.filter(x => x.parish == this.parish)
      currentAlarms.push
      let backendAlarms = (await axios.get('http://localhost:8080/alarms')).data
      .filter(x => x.parish == this.parish)
      .map(x => {
        let existingAlarm = currentAlarms.find(y => y.id == x.id)
        return {
          triggered: existingAlarm ? existingAlarm.triggered : false,
          ...x
>>>>>>> c0661d35f7f7a0b6eff77cbdbe262320c9cba4ba
        }
      })
      .forEach(x => {
        this.$store.dispatch('deleteAlarm',x.id)
        this.$store.dispatch('addAlarm',x)
      })
      this.onAlertReset(evt)
    },
    onAlertReset(evt) {
      evt.preventDefault()
      // Reset our form values
      this.type = null
      this.time = ''
      this.operation = null
      this.value = ''
      this.conditions = []
    },
  }
}
</script>