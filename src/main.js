import Vue from 'vue'
import Application from './Application'
import VueRouter from 'vue-router'
import {routes} from './router/router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import qs from 'qs';

axios.defaults.withCredentials = true;
Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
Vue.prototype.$qs = qs;
Vue.use(ElementUI);
Vue.use(VueRouter);

const router = new VueRouter({
  routes,
  mode:'history',
  scrollBehavior(to,from,savedPosition){
    if(savedPosition){
      return savedPosition
    }else{
      return {x:0,y:0}
    }
  }

})


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(Application)
})
