<template>
  <div role="tablist">
    <b-card v-for="(v,index) in alarms" :key="v.id" no-body class="mb-1">
      <div>
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button class="alarm-button" block v-b-toggle="'accordion-'+index" >
            {{
              v.conditions.map(cond => {
                return cond.type+' '+cond.operation+' '+cond.threshold
              }).join(" && ")
            }}
          </b-button>
        </b-card-header>
        <b-collapse :id="'accordion-'+index" accordion="my-accordion" role="tabpanel">
          <b-card-body>
            <h5>ID</h5>
            <b-card-text>
              <div>
                {{v.id}}
              </div>
            </b-card-text>
            <h5>CONDITIONS</h5>
            <b-card-text v-for="cond in v.conditions" :key="cond.type+cond.operation+cond.threshold">
              {{ cond.type }} {{ cond.operation }} {{ cond.threshold }}
            </b-card-text>
            <h5>DELETE ALARM?</h5>
            <img @click="deleteAlarm(v.id)" height="32px" src="icons/bin.png" />
          </b-card-body>
        </b-collapse>
      </div>
    </b-card>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  props:{
    alarms: Array,
  },
  methods:{
    async deleteAlarm(id) {
      await axios({
        method: 'delete',
        url: 'http://localhost:8080/alarm',
        data: {
          id
        }
      })
      alert("ALARME REMOVIDO")
      this.$store.dispatch('deleteAlarm',id)
    },
  }
}
</script>

<style scoped>
.mb-1{
  border-radius: 0;
}

.p-1{
  width:100%;
  border:0;
  padding:0 !important;
}

.alarm-button{
  width:100%;
  background-color: white;
  color: rgb(150, 150, 150);
  border:0;
  border-radius: 0;
}

h5{
  margin-top: 15px;
  margin-bottom: 5px;
  color: rgb(150, 150, 150);
  font-size: 13px;
}

</style>