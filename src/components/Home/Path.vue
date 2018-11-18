<template>
  <!-- <div>我是path界面</div> -->

  <div class="mapBox">
    <div id="allmap" ref="allmap"></div>
    <!-- 鼠标滑倒右侧显示箭头 -->
    <div class="add" @mouseenter="enter()" @mouseleave="leave()" :style="add">
      <div class="show" :style="mchange">
        <i class="el-icon-plus"></i>
      </div>
    </div>

    <!-- 导航步骤 -->
    <div class="step" style="">
      <div class="search">
        <span class="searchTip tip">搜索</span>
        <el-input v-model="input" placeholder="请搜索查找的地址" style="width:70%" ></el-input>
      </div>

      <!-- 中心点导入 -->
      <div class="centerInput">
        <span class="centerTips">中心点导入</span>
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-change="handleChange1"
          :file-list="fileList1">
          <el-button size="small" type="primary" style="margin-top:10px;">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </div>

      <!-- 服务点导入 -->
      <div class="centerInput">
        <span class="centerTips">服务点导入</span>
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-change="handleChange2"
          :file-list="fileList2">
          <el-button size="small" type="primary" style="margin-top:10px;">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </div>  

      <!-- 车辆信息导入 -->
      <div class="centerInput">
        <span class="centerTips">车辆信息导入</span>
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-change="handleChange3"
          :file-list="fileList3">
          <el-button size="small" type="primary" style="margin-top:10px;">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </div> 

      <div class="workout">
            <el-button type="primary" size="small">开始计算</el-button>
        </div>   
    </div>

    <!-- 路线展示 -->
    <div class="step" style="display:none">
      <div class="mtitle">方案展示</div>
      <div class="mshow" v-for="item in items">
        <div class="mtips">{{item.title}}</div>
        <div class="mcontent">{{item.content}}</div>
      </div>
    </div>
  </div>


</template>
<script>
export default {
  data() {
    return {
      //data
      dialogVisible: false,
      mchange: {
        marginRight:'0px'
      },
      add: {
        display: 'none'
      },
      fileList1: [{
          name: 'food.jpeg',
          url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        }],
      fileList2: [{
          name: 'food.jpeg',
          url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        }],
        fileList3: [{
          name: 'food.jpeg',
          url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        }],
      input: '',
      items: [{
        title:'路线最短',
        content: 'xxxxxxxx'
      }]
    };
  },
  components: {
    //组件
  },
  methods: {
    map() {
      let map = new BMap.Map(this.$refs.allmap); // 创建Map实例
      map.centerAndZoom(new BMap.Point(116.404, 39.915), 11); // 初始化地图,设置中心点坐标和地图级别
      // map.addControl(
      //   new BMap.MapTypeControl({
      //     //添加地图类型控件
      //     mapTypes: [BMAP_NORMAL_MAP, BMAP_HYBRID_MAP]
      //   })
      // );
      //map.setCurrentCity("北京"); // 设置地图显示的城市 此项是必须设置的
      map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
    },

      enter(index){
        console.log(1);
        let indexnumber = 0;
        let t = setInterval(() => {
          this.mchange = 'marginRight:' + (indexnumber++) + 'px';
          console.log(indexnumber);
          if(indexnumber > 50) {
            clearTimeout(t);
          }
        },1)
      },
      leave(index){
          let indexnumber = 50;
        let t = setInterval(() => {
          this.mchange = 'marginRight:' + (indexnumber--) + 'px';
          console.log(indexnumber);
          if(indexnumber < 0) {
            clearTimeout(t);
          }
        },1)
      },
      //中心点导入
       handleChange1(file, fileList) {
        this.fileList1 = fileList.slice(-3);
      },
      //服务点导入
             handleChange2(file, fileList) {
        this.fileList2 = fileList.slice(-3);
      },
        //车辆信息导入
             handleChange3(file, fileList) {
        this.fileList3 = fileList.slice(-3);
      },
  },
  mounted() {
    console.log("mounted");
    //加载完成触发已加载事件
    this.$emit("loaded");
    this.map(); //调用地图
  }
};
</script>
<style scoped>
.add {
  position: absolute;
  width: 250px;
  background-color: red;
  height: 100%;
  z-index: 1000;
  right: 0px;
  bottom: 0px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.show {
  width: 100px;
  height: 100px;
  background-color: green;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
.mapBox {
  position: relative;
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
#allmap {
    position: relative;
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
.el-main {
  margin: 0px;
  padding: 0px;
}
.addnew {
  position: fixed;
  right: 0px;
  bottom: 0px;
}

/* 导航步骤 */
.step {
  position: absolute;
  height: 100%;
  width: 300px; 
  background-color: white;
  z-index: 1001;
  right: 0;
  top: 0;
}
.search {
  display: flex;
  margin-left: 20px;
  align-items: center;
  margin-top: 20px;
}

.searchTip  {
  margin-right: 20px;
}
.centerInput{
  margin-top: 20px;
  margin-left: 20px;
}
.workout{
  margin-top: 50px;
  display: flex;
  justify-content: center;
}
.mshow {
  margin-left: 20px;
  margin-top: 20px;
}
.mtitle{
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  font-size: 20px;
  font-family: "Microsoft YaHei"
}
</style>

