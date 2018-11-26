<template>
  <div class="car">
    <el-row class="titleRow">
      <el-col :span="8" :offset="2">车辆ID:{{vehicleId}}</el-col>
      <el-col :span="8" :offset="6">
        <el-switch
          v-model="isSelected"
          active-color="#13ce66"
          active-text="已选中"
          inactive-color="#E5E5E9">
        </el-switch>
      </el-col>
    </el-row>
    <el-row class="attributeRow">
      <el-col :span="10" :offset="2">车辆类型:{{type}}</el-col>
      <el-col :span="10" :offset="0">载货量:{{capacity}}</el-col>
    </el-row>
    <el-row class="attributeRow">
      <el-col :span="10" :offset="2">排量: {{oil}}t</el-col>
      <el-col :span="10" :offset="0">价格: {{price}}元/次</el-col>
    </el-row>
    <div :class="[ delFlag === 0 ? '' : 'flag onTheWay']">{{delFlag === 0 ? '' : '已删除'}}</div>
  </div>
</template>

<script>
export default {
    props: {
      //车辆当前执行订单编号
      vehicleId: {
        type: Number,
        // required: true
      },
      //车辆类型
      type: {
        type: String,
        required: true
      },
      // 车辆载货量
      capacity: {
        type: Number,
        required: true
      },
      // 车辆排量
      oil: {
        type: Number,
        required: true
      },
      // 车辆价格
      price: {
        type: Number,
        required: true
      },
      // 车辆状态
      delFlag: {
        type: Number,
        required: true
      }
    },
    data(){
      return{
        isSelected:false
      }
    },
    watch:{
      isSelected: function(newVal, oldVal){
        if(newVal == true){
          this.$store.commit('deleteArrayAdd',this.vehicleId)
        }else{
          this.$store.commit('deleteArrayDelete',this.vehicleId)
        }
      }
    },
    methods:{

    }
}
</script>


<style scoped>
  .car{
    position: relative;
    display: inline-block;
    width: 400px;
    height: 200px;
    margin: 10px 20px;
    background: url('../../../assets/UserSystem/images/vehicleManagement/carBackground.png');
    border: 1px solid #EBEEF5;
    border-radius: 10px;
    transition: box-shadow .3s ease;
    overflow: hidden;
  }
  .car:hover{
    box-shadow: 0 0 10px #888888;
  }
  .titleRow{
    height: 40px;
    line-height: 40px;
    font-size: 20px;
  }
  .attributeRow{
    height: 40px;
    line-height: 40px;
    font-family:Aria
  }
  .flag{
    position: absolute;
    bottom: 0;
    right: 0;
    width: 120px;
    height: 50px;
    text-align: center;
    font-size: 26px;
    font-weight: bold;
    line-height: 50px;
    color: white;
    border-radius: 10px;
  }
  .onTheWay{
    background: #F56C6C;
  }
</style>

