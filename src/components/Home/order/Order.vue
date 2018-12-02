<template>
    <div class="order">
        <div class="title">
          <span class="questionId">订单号:{{questionId}} 订单名称:{{questionName}}</span>
           <el-tooltip class="item" effect="dark" content="删除" placement="top">
            <el-button type="danger" icon="el-icon-delete" circle class="deleteButton" @click="deleteQuestion" ></el-button>
           </el-tooltip>
          <span class="time">2018-11-11</span>
        </div>
        <div class="versionContainer">
          <version v-for="(version,index) in versions" :key="index"
                  :question-id="questionId"
                  :version-id="version"
                  :solutions="version"></version>
        </div>
        <div class="bottomLine">
        </div>
    </div>
</template>

<script>
import qs from 'qs'
import Version from './Version.vue'
export default {
  props:['questionId','questionName','userId','delFlag'],
  data(){
    return{
      versions:[],
    }
  },
  components:{
    Version
  },
  mounted(){
    this.loadVersions();
  },
  methods:{
    //获取该问题的所有版本
    loadVersions(){
      let that = this;
      this.$axios.get(that.$url +'/finalSolution/getAllVersion',{
        params:{
          questionId:this.questionId
        }
      }
      )
      .then( res => {
        console.log(res);
        this.versions = res.data.object;
      })
    },
    deleteQuestion(){
      let that = this;
      this.$axios.delete(that.$url +'/question/removeQuestion',{
        params:{
          questionId:this.questionId
        }
      })
      .then( res => {
        console.log(res);
        this.$emit('refresh');
      })
    }
  },
}
</script>

<style scoped>
    .order{
        position: relative;
        display: inline-block;
        width: 100%;
        height: auto;
        border: 1px solid #EBEEF5;
        border-radius: 10px;
        transition: box-shadow .3s ease;
        overflow: hidden;
        margin-bottom: 20px;
    }
    .order::-webkit-scrollbar{
      width: 0 !important
    }
    .order:hover{
        box-shadow: 0 0 10px #888888;
    }
    .title{
        position: absolute;
        width: 100%;
        height: 60px;
        background: #409EFF;
        font-size: 20px;
        color: white;
        line-height: 60px;
    }
    .questionId{
      margin-left: 50px;
    }
    .time{
      float: right;
      margin-right: 30px;
    }
    .versionContainer{
      position: relative;
      top:60px;
      width: 100%;
      min-height: 500px;
      height: auto;
      background: #E8E8E8;
    }
    .bottomLine{
      position: absolute;
      bottom: 0;
      width: 100%;
      height: 40px;
      background: #E8E8E8;
    }
    .deleteButton{
      float: right;
      margin-right: 30px;
      margin-top: 10px;
    }
</style>


