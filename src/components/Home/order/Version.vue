<template>
  <div class="version">
    <div class="topPanel">
      <span>版本 {{versionId}}</span>
    </div>
    <div class="main">
      <solution v-for="(solution,index) in solutions" :key="index"
        :final-solution-id="solution.finalSolutionId"
        :total-dis="solution.totalDis"
        :user-choice="solution.userChoice"
        :create-time="solution.createTime"
        :routes="solution.routes">
      </solution>
    </div>
  </div>
</template>

<script>
import Solution from './Solution.vue'
export default {
  props:['versionId','questionId'],
  data(){
    return {
      solutions:[]
    }
  },
  components:{
    Solution
  },
  mounted(){
    this.loadSolutions()
  },
  methods:{
    loadSolutions(){
      this.$axios.get('/finalSolution/getOneVersionFinalSolution',{
        params:{
          questionId:this.questionId,
          version:this.versionId
        }
      })
      .then( res => {
        this.solutions = res.data.object;
        console.log(res);
      })
    }
  }
}
</script>

<style scoped>
  .version{
    position: relative;
    width: 23%;
    min-height: 360px;
    height: auto;
    margin: 1% 1%;
    background: white;
    border-radius: 5px;
    overflow: hidden;
    display: inline-block;
  }
  .topPanel{
    position: absolute;
    width: 100%;
    height: 30px;
    background: #409EFF;
    font-size: 16px;
    color: white;
    line-height: 30px;
    text-align: center;
  }
  .main{
    position: absolute;
    top: 30px;
    width:100%;
    height: 330px;
    overflow-y: auto;
  }
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
