import Vuex from 'vuex'
import Vue from 'vue'
import axios from 'axios'

const createStore = () => {
    return new Vuex.Store({
        state:{
            alarms:[],
        },
        mutations:{
            setAlarms(state, alarms){
                state.alarms = alarms
            },
            deleteAlarm(state,alarmId){
                state.alarms = state.alarms.filter(x => x.id != alarmId)
            },
            triggerAlarm(state,alarmId){
                state.alarms.find(x => x.id == alarmId).triggered = true
            },
            addAlarm(state,alarm){
                state.alarms.push(alarm)
            }
        },
        actions:{
            async nuxtClientInit(vuexContext,context){
                let backendAlarms = (await axios.get('http://localhost:8080/alarms')).data
                vuexContext.commit('setAlarms',backendAlarms.map(x => {return {triggered:false,...x}}))
            },
            setAlarms(vuexContext,alarms){
                vuexContext.commit('setAlarms',alarms)
            },
            deleteAlarm(vuexContext,alarmId){
                vuexContext.commit('deleteAlarm',alarmId)
            },
            triggerAlarm(vuexContext,alarmId){
                vuexContext.commit('triggerAlarm',alarmId)
            },
            addAlarm(vuexContext,alarm){
                vuexContext.commit('addAlarm',alarm)
            }
        },
    })
}

export default createStore
Vue.prototype.$store = createStore
