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
        },
        actions:{
            async nuxtClientInit(vuexContext,context){
                let backendAlarms = (await axios.get('http://localhost:8080/alarms')).data
                vuexContext.commit('setAlarms',backendAlarms)
            },
            setAlarms(vuexContext,alarms){
                vuexContext.commit('setAlarms',alarms)
            },
            deleteAlarm(vuexContext,alarmId){
                vuexContext.commit('deleteAlarm',alarmId)
            },
        },
    })
}

export default createStore
Vue.prototype.$store = createStore
