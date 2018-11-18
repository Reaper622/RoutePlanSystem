<template>
    <div  class="main">
        <el-input
        placeholder="请输入所要查询的车辆id"
        v-model="searchVehicleId"
        size="medium"
        class="searchInput">
          <el-button slot="append" icon="el-icon-search" type="primary"></el-button>
        </el-input>

        <el-button type="danger" class="deleteButton" @click="deleteCarIt">删除车辆 <i class="el-icon-remove"></i></el-button>
        <el-button type="success" class="addButton" @click="addCarIt">录入车辆 <i class="el-icon-circle-plus"></i></el-button>
        <br>
        <br>

        <car v-for="car in cars" :key="car.id" 
            :type="car.type" 
            :capacity="car.capacity" 
            :oil="car.oil" 
            :price="car.price" 
            :del-flag="car.delFlag"></car>
    </div>
</template>
<script>
import Car from './VehicleManagement/car.vue'

export default {
    data() {
      return{
        searchVehicleId: '',//搜索车辆的Id
        cars:[
          {id:1, type: '货车', capacity: 200.05, oil: 5.5, price: 200.5, delFlag: 0},
          {id:2, type: '面包车', capacity: 100.15, oil: 2.0, price: 150.5, delFlag: 1},
          {id:3, type: '大货车', capacity: 300.15, oil: 7.0, price: 3000.5, delFlag: 1},
          {id:4, type: '大货车', capacity: 300.15, oil: 7.0, price: 3000.5, delFlag: 1}
        ],
        selectedCar:0
      }
    },
    components: {
      Car
    },
    methods: {
      addCarIt() {
        console.log('addcar');
        this.$emit('addCar');
      },
      deleteCarIt() {
        this.$prompt('请输入要删除的车辆ID','删除车辆',{
          confirmButtonText: '确认删除',
          cancelButtonText: '取消',
          inputPattern: /^[0-9]*$/,
          inputErrorMessage: '车辆ID只能为数字'
        }).then(({value}) => {
          this.$message({
            type: 'success',
            message: '你已经删除' + value
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '你已取消删除'
          })
        })
      },
      setSeleteCar(index) {
        console.log('set');
        this.$data.selectedCar = index;
      }
    },
    mounted(){
      console.log('mounted');
      //加载完成触发已加载事件
      this.$emit('loaded');
    }
}
</script>
<style scoped>
.main {
  margin: 10px 0 0 10px;
}
  .searchInput{
    width: 250px;
    margin-left: 20px;
  }
  .addButton{
    float: right;
    margin-right: 50px;
  }
  .deleteButton{
    float: right;
    margin-right: 100px;
  }
  .selectedCar{
    border: 2px solid #409EFF;
  }
</style>

