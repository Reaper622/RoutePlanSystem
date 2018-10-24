<template>
    <div class="main">
        <el-input
        placeholder="请输入所要查询的订单号"
        v-model="searchOrderId"
        size="medium"
        class="searchInput">
          <el-button slot="append" icon="el-icon-search" type="primary"></el-button>
        </el-input>
        <br>
        <br>
        <order></order>
    </div>
</template>
<script>
import Order from  './order/Order.vue'
export default {
    data() {
        return{
            searchOrderId:''
        }
    },
    components:{
        Order
    }
}
</script>
<style scoped>
    .searchInput{
    width: 250px;
    margin-left: 20px;
  }
</style>

