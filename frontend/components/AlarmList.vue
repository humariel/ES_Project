<template>
  <div role="tablist">
    <b-card v-for="(v,index) in alarms" :key="v.id" no-body class="mb-1">
      <div>
        <b-card-header @click="disableAlarm(v)" header-tag="header" class="p-1" role="tab">
          <b-button class="alarm-button" block v-b-toggle="'accordion-'+index" >
            <img class="bell" :src="'icons/bell_'+ (v.triggered?'enabled':'disabled') +'.png'" height="32px"/>
            <div class="header-text">
              {{
                v.conditions.map(cond => {
                  return cond.type+' '+cond.operation+' '+cond.threshold
                }).join(" && ")
              }}
            </div>
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
            <h5>FETCHING PERIOD</h5>
            <b-card-text>
              Every {{v.time}} seconds
            </b-card-text>
            <h5>CONDITIONS</h5>
            <b-card-text v-for="cond in v.conditions" :key="cond.type+cond.operation+cond.threshold">
              {{ cond.type }} {{ cond.operation }} {{ cond.threshold }}
            </b-card-text>
            <h5>TRIGGERED</h5>
            <b-card-text>
              {{v.triggered ? 'Yes' : 'No'}}
            </b-card-text>
            <h5>DELETE ALARM?</h5>
            <img @click="deleteAlarm(v.id)" height="32px" class="bin" src="icons/bin.png" />
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
    disableAlarm(alarm){
      this.$store.state.alarms.find(x => x.id == alarm.id).triggered = false
    },
    async deleteAlarm(id) {
      let r = await axios({
        method: 'delete',
        url: 'http://localhost:8080/alarm',
        data: id
      })
      console.log('del res ',r)
      this.$store.dispatch('deleteAlarm',id)
    },
  }
}
</script>

<style scoped>

.bin:hover{
  cursor:pointer;
}

.alarm-button{
  width:100%;
  background-color: white;
  color: rgb(150, 150, 150);
  border:0;
  border-radius: 0;
  display: flex;
  justify-content: left;
  align-content: left;
}

.header-text{
  padding-top:5px;
}

.bell{
  padding-right:10px;
}

.mb-1{
  border-radius: 0;
}

.p-1{
  width:100%;
  border:0;
  padding:0 !important;
}

h5{
  margin-top: 15px;
  margin-bottom: 5px;
  color: rgb(150, 150, 150);
  font-size: 13px;
}

</style>