<template>
    <div class="addCar">
        <div class="addCarPage">
            <div class="pageTitle">车辆信息录入</div>
            <el-form ref="form" :model="carInfo" label-width="100px" class="pageForm">
                <el-form-item label="车辆类型">
                    <el-radio-group v-model="carInfo.type">
                        <el-radio label="面包车"></el-radio>
                        <el-radio label="货车"></el-radio>
                        <el-radio label="大货车"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="汽车载货量">
                    <el-input v-model="carInfo.capacity" class="pageInput"></el-input>
                </el-form-item>
                <el-form-item label="汽车排量">
                    <el-input v-model="carInfo.oil" class="pageInput"></el-input>
                </el-form-item>
                <el-form-item label="汽车价格">
                    <el-input v-model="carInfo.price" class="pageInput"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">录入车辆</el-button>
                    <el-button @click="cancel">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>    
</template>

<script>
export default {
    data(){
        return{
            carInfo:{
                type: '',
                capacity: 0,
                oil: 0,
                price: 0,
            }
        }
    },
    methods: {
        onSubmit(){
            this.$emit('successCar');
        },
        cancel(){
            this.$emit('cancelCar');
        }
    }
}
</script>

<style scoped>
    .addCar{
        position: fixed;
        z-index: 2034;
        width: 100%;
        height: 100%;
        background:rgba(128, 128, 128,0.7);
    }
    .addCarPage{
        position: relative;
        width: 400px;
        height: 400px;
        margin: 100px auto;
        background: #ffffff;
    }
    .pageTitle{
        width: 100%;
        height: 50px;
        background: #409EFF;
        text-align: center;
        color: #ffffff;
        font-size: 30px;
        line-height: 50px;
    }
    .pageInput{
        width: 200px;
    }
</style>


