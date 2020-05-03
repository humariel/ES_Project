<template>
  <b-form @submit="onAlertCreation" @reset="onAlertReset">
    <h5>FETCHING PERIOD (IN MINUTES)</h5>
    <b-form-group
      id="input-group-1"
      label-for="input-1"
      description="Period at which the alert will be checked"
    >
      <b-form-input
        id="input-1"
        v-model="form.time"
        required
        placeholder="Enter fetching period"
      ></b-form-input>
    </b-form-group>

    <h5>TYPE</h5>
    <b-form-group id="input-group-3" label-for="input-3">
      <b-form-select
        id="input-3"
        v-model="form.type"
        :options="verticals"
        placeholder="Choose the type"
        required
      ></b-form-select>
    </b-form-group>

    <h5>OPERATION</h5>
    <b-form-group id="input-group-3" label-for="input-3">
      <b-form-select
        id="input-3"
        v-model="form.operation"
        :options="['Higher than', 'Lower than']"
        required
        placeholder="Choose the operation"
      ></b-form-select>
    </b-form-group>


    <h5>VALUE</h5>
    <b-form-group
      id="input-group-1"
      label-for="input-1"
      description="Threshold value"
    >
      <b-form-input
        id="input-1"
        v-model="form.value"
        required
        placeholder="Enter the threshold value"
      ></b-form-input>
    </b-form-group>

    <b-button type="submit" variant="primary">Submit</b-button>
    <b-button type="reset" variant="danger">Reset</b-button>
  </b-form>
</template>

<script>
export default {
  props:{
    verticals: Array
  },
  data(){
    return{
      form: {},
    }
  },
  methods:{
    onAlertCreation(evt) {
      evt.preventDefault()

    },
    onAlertReset(evt) {
      evt.preventDefault()
      // Reset our form values
      this.form.type = ''
      this.form.time = ''
      this.form.operation = ''

    },
  }
}
</script>