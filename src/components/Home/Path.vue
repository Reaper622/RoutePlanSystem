<template>
  <div>我是path界面</div>
</template>
<script>
export default {
    data() {
      return{
        //data
      }
    },
    components: {
      //组件
    },
    methods: {

    },
    mounted(){
      console.log('mounted');
      //加载完成触发已加载事件
      this.$emit('loaded');
    }
}
</script>
<style scoped>

</style>

