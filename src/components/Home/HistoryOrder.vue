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
        <order v-for="question in questions" :key="question.questionId"
        :question-id="question.questionId"
        :question-name="question.questionName"
        :user-id="question.userId"
        :del-flag="question.delFlag"
        @refresh="loadQuestions"></order>
    </div>
</template>
<script>
import Order from  './order/Order.vue'
export default {
    data() {
        return{
            searchOrderId:'',
            //问题数组
            questions:[],
        }
    },
    components:{
        Order
    },
    mounted(){
      this.loadQuestions();
      //加载完成
      this.$emit('loaded');
    },
    methods:{
      //得到所有问题
      loadQuestions(){
        this.$axios.get("/question/getQuestions",{
        params:{
          userId: 1
        }
        })
        .then( res => {
          this.questions = res.data.object;
        })
      }
    }
}
</script>
<style scoped>
    .searchInput{
    width: 250px;
    margin-left: 20px;
  }
</style>

