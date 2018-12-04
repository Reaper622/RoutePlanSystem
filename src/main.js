import Vue from 'vue'
import Application from './Application'
import VueRouter from 'vue-router'
import createStore from './store/store'
import {routes} from './router/router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import qs from 'qs';
import 'es6-promise/auto'

axios.defaults.withCredentials = true;
Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
Vue.prototype.$qs = qs;
// Vue.prototype.$url = '';
Vue.use(ElementUI);
Vue.use(VueRouter);
Vue.prototype.$url = 'http://172.22.1.134:8081/RoutePlanSystem';
// axios.defaults.baseURL = 'http://47.107.65.249:8081/RoutePlanSystem'

const store = createStore();

const router = new VueRouter({
  routes,
  scrollBehavior(to,from,savedPosition){
    if(savedPosition){
      return savedPosition
    }else{
      return {x:0,y:0}
    }
  }
})

// 路由守卫，检测是否已经登录
router.beforeEach((to, from, next) => {
  if (to.meta.requireLogin) { // 如果前往页面需要登录
    if (store.getters.getUserId == null) { // 未登录
      next('/login')// 跳转到登录界面
    } else { // 已登录
      next()
    }
  } else { // 前往页面不需要登录
    next()
  }
})


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(Application)
})
