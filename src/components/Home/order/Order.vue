<template>
    <div class="order">
        <div class="title">订单号:123456789123</div>
        
    </div>
</template>

<script>
export default {
    
}
</script>

<style scoped>
    .order{
        position: relative;
        display: inline-block;
        width: 300px;
        height: 375px;
        background: url('../../../assets/UserSystem/images/order/order.png');
        border: 1px solid #EBEEF5;
        border-radius: 10px;
        transition: box-shadow .3s ease;
        overflow: hidden;
    }
    .order:hover{
        box-shadow: 0 0 10px #888888;
    }
    .title{
        position: relative;
        width: 100%;
        height: 60px;
        background: #409EFF;
        text-align: center;
        font-size: 20px;
        color: white;
        line-height: 60px;
    }
</style>


