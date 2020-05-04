<template>
  <b-form @submit="onAlertCreation" @reset="onAlertReset">
    <h5>FETCHING PERIOD (IN MINUTES)</h5>
    <b-form-group
      id="input-group-1"
      label-for="input-1"
      description="Period at which the alert will be checked"
      style="margin-bottom: 0"
    >
      <b-form-input
        id="input-1"
        v-model="time"
        required
        placeholder="Enter fetching period"
      ></b-form-input>
    </b-form-group>

    <span style="margin-top: 1rem; display: block" v-if="conditions.length">{{conditions}}</span> 
    
    <b-form @submit="$event.stopPropagation(); $event.preventDefault(); conditions.push({
          type,
          operation,
          value
        })">
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
            ></b-form-select>
          </b-form-group>
        </div>

        <div style="width: 100%; margin-left: 2.5px; margin-bottom: -1rem">
          <h5>OPERATION</h5>
          <b-form-group id="input-group-3" label-for="input-3">
            <b-form-select
              id="input-3"
              v-model="operation"
              :options="['Higher than', 'Lower than']"
              required
              placeholder="Choose the operation"
            ></b-form-select>
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
export default {
  props:{
    verticals: Array
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
    onAlertCreation(evt) {
      evt.preventDefault()
      this.$axios({
        method: 'post',
        url: 'http://localhost:8080/alarm',
        data: {
          time: this.time,
          conditions: this.conditions
        }
      })
      alert('Alarme criado')
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