define({ "api": [
  {
    "type": "GET",
    "url": "/distance/getUpdateDistance",
    "title": "得到需要设置距离和时间的两点",
    "description": "<p>通过向后台访问此接口前端得到数据来进行计算距离和时间</p>",
    "group": "Distance",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"distanceId\": 1,\n  \"questionId\": 1,\n  \"startNodeId\": 1,\n  \"endNodeId\": 1,\n  \"startNodeAddr\":\"起始地点位置\",\n  \"endNodeAddr\":\"终点地点位置\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/DistanceController.java",
    "groupTitle": "Distance",
    "name": "GetDistanceGetupdatedistance"
  },
  {
    "type": "PATCH",
    "url": "/distance/updateDisAndTime",
    "title": "设置两点的距离和时间",
    "description": "<p>通过 distanceId 来修改 distance 和 time</p>",
    "group": "Distance",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "distanceId",
            "description": "<p>距离对象ID</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "dis",
            "description": "<p>两地距离间隔</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "time",
            "description": "<p>两地时间间隔</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/DistanceController.java",
    "groupTitle": "Distance",
    "name": "PatchDistanceUpdatedisandtime"
  },
  {
    "type": "DELETE",
    "url": "/question/removeQuestion",
    "title": "删除问题",
    "description": "<p>通过questionId删除问题</p>",
    "group": "Question",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "questionId",
            "description": "<p>问题ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/QuestionController.java",
    "groupTitle": "Question",
    "name": "DeleteQuestionRemovequestion"
  },
  {
    "type": "GET",
    "url": "/question/getQuestions",
    "title": "得到questions",
    "description": "<p>通过用户Id得到所用该用户的问题</p>",
    "group": "Question",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "userId",
            "description": "<p>用户Id</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"questionId\": 1,\n  \"questionName\": \"问题名称\",\n  \"userId\": 1,\n  \"del_flag\": 0\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/QuestionController.java",
    "groupTitle": "Question",
    "name": "GetQuestionGetquestions"
  },
  {
    "type": "PATCH",
    "url": "/question/updateQuestion",
    "title": "修改问题",
    "description": "<p>通过questionId修改问题的名称</p>",
    "group": "Question",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "questionId",
            "description": "<p>问题ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "questionName",
            "description": "<p>问题名称</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/QuestionController.java",
    "groupTitle": "Question",
    "name": "PatchQuestionUpdatequestion"
  },
  {
    "type": "POST",
    "url": "/question/insertQuestion",
    "title": "添加问题",
    "description": "<p>通过question详细信息添加question</p>",
    "group": "Question",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "questionName",
            "description": "<p>问题名称</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "userId",
            "description": "<p>用户Id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/QuestionController.java",
    "groupTitle": "Question",
    "name": "PostQuestionInsertquestion"
  },
  {
    "type": "DELETE",
    "url": "/solution/removeAllSolution",
    "title": "删除一个问题的一个版本的所有方案",
    "description": "<p>通过问题Id删除一个问题的一个版本的所有方案</p>",
    "group": "Solution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "questionId",
            "description": "<p>问题ID</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "versionId",
            "description": "<p>版本ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/SolutionController.java",
    "groupTitle": "Solution",
    "name": "DeleteSolutionRemoveallsolution"
  },
  {
    "type": "DELETE",
    "url": "/solution/removeAllSolution",
    "title": "删除一个问题的所有解决方案",
    "description": "<p>通过问题Id删除一个问题的所有解决方案</p>",
    "group": "Solution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "questionId",
            "description": "<p>问题ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/SolutionController.java",
    "groupTitle": "Solution",
    "name": "DeleteSolutionRemoveallsolution"
  },
  {
    "type": "GET",
    "url": "/solution/attainSolution",
    "title": "进行得到最优方案算法",
    "description": "<p>通过问题Id进行得到最优方案算法</p>",
    "group": "Solution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "questionId",
            "description": "<p>问题ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/SolutionController.java",
    "groupTitle": "Solution",
    "name": "GetSolutionAttainsolution"
  },
  {
    "type": "GET",
    "url": "/solution/getBetterDistanceSolution",
    "title": "得到一个问题两个距离最优解决方案",
    "description": "<p>通过问题Id在所用版本解决方案中得到一个问题两个距离最优解决方案</p>",
    "group": "Solution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "questionId",
            "description": "<p>问题ID</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"solutionId\": 1,\n  \"questionId\": 1,\n  \"route\": \"方案的具体线路\",\n  \"totalDis\": 123456,\n  \"totalTime\": 123456,\n  \"version\": 1,\n  \"delFlag\": 0\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/SolutionController.java",
    "groupTitle": "Solution",
    "name": "GetSolutionGetbetterdistancesolution"
  },
  {
    "type": "GET",
    "url": "/solution/getBetterTimeSolution",
    "title": "得到一个问题两个时间最优解决方案",
    "description": "<p>通过问题Id在所用版本解决方案中得到一个问题两个时间最优解决方案</p>",
    "group": "Solution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "questionId",
            "description": "<p>问题ID</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"solutionId\": 1,\n  \"questionId\": 1,\n  \"route\": \"方案的具体线路\",\n  \"totalDis\": 123456,\n  \"totalTime\": 123456,\n  \"version\": 1,\n  \"delFlag\": 0\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/SolutionController.java",
    "groupTitle": "Solution",
    "name": "GetSolutionGetbettertimesolution"
  },
  {
    "type": "GET",
    "url": "/solution/getSolutions",
    "title": "得到一个问题的所有版本解决方案",
    "description": "<p>通过问题Id得到所有版本的解决方案</p>",
    "group": "Solution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "questionId",
            "description": "<p>问题ID</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"solutionId\": 1,\n  \"questionId\": 1,\n  \"route\": \"方案的具体线路\",\n  \"totalDis\": 123456,\n  \"totalTime\": 123456,\n  \"version\": 1,\n  \"delFlag\": 0\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/SolutionController.java",
    "groupTitle": "Solution",
    "name": "GetSolutionGetsolutions"
  },
  {
    "type": "DELETE",
    "url": "/userSystem/session/user",
    "title": "用户登陆",
    "description": "<p>注销登录</p>",
    "group": "UserSystem",
    "success": {
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n  \"status\":1\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "0",
            "description": "<p>存在异常</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "2",
            "description": "<p>注销失败</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/UserController.java",
    "groupTitle": "UserSystem",
    "name": "DeleteUsersystemSessionUser"
  },
  {
    "type": "GET",
    "url": "/userSystem/verifyCode",
    "title": "获取登录验证码",
    "description": "<p>通过该接口获取登录验证码，并存入session</p>",
    "group": "UserSystem",
    "success": {
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n验证码图片",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/UserController.java",
    "groupTitle": "UserSystem",
    "name": "GetUsersystemVerifycode"
  },
  {
    "type": "POST",
    "url": "/userSystem/session/user",
    "title": "用户登陆",
    "description": "<p>通过userId和password并验证验证码是否正确进行登陆</p>",
    "group": "UserSystem",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>用户密码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>验证码</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n  \"status\":1\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "0",
            "description": "<p>该功能存在异常</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "2",
            "description": "<p>用户名或密码输入错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "3",
            "description": "<p>验证码为空</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "4",
            "description": "<p>请填写完整</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "5",
            "description": "<p>您未登录，请登陆后操作</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/UserController.java",
    "groupTitle": "UserSystem",
    "name": "PostUsersystemSessionUser"
  },
  {
    "type": "POST",
    "url": "/userSystem/user",
    "title": "用户注册",
    "description": "<p>新用户通过填写相关信息进行注册</p>",
    "group": "UserSystem",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userId",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userName",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "eMail",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "rePassword",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mailCode",
            "description": ""
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n  \"status\":1\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "0",
            "description": "<p>存在异常</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "2",
            "description": "<p>两次密码不一致</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "3",
            "description": "<p>邮箱验证码错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "4",
            "description": "<p>信息输入不完整</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/UserController.java",
    "groupTitle": "UserSystem",
    "name": "PostUsersystemUser"
  },
  {
    "type": "GET",
    "url": "/vehicleSystem/user/vehicle",
    "title": "用户车辆信息的查询",
    "description": "<p>查询</p>",
    "group": "vehicleSystem",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "questionId",
            "description": "<p>订单编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>车辆的类型</p>"
          },
          {
            "group": "Parameter",
            "type": "float",
            "optional": false,
            "field": "capacity",
            "description": "<p>载货量</p>"
          },
          {
            "group": "Parameter",
            "type": "float",
            "optional": false,
            "field": "oil",
            "description": "<p>排量</p>"
          },
          {
            "group": "Parameter",
            "type": "float",
            "optional": false,
            "field": "price",
            "description": "<p>价格</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "delFlag",
            "description": "<p>车辆状态 0:空闲 1:在用</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n        \"vehicleId\":1\n        \"questionId\":1\n        \"type\":皮卡\n        \"capacity\":500\n        \"oil\":4.3\n        \"date\":2018-09-26 22:38:14\n        \"price\":253.3\n        \"delFlag\":1\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "0",
            "description": "<p>该功能存在异常</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "4",
            "description": "<p>查询为空</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "5",
            "description": "<p>您未登录，请登陆后操作</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 0 存在异常\n{\n   \"status\": \"0\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/VehicleController.java",
    "groupTitle": "vehicleSystem",
    "name": "GetVehiclesystemUserVehicle"
  },
  {
    "type": "POST",
    "url": "/vehicleSystem/user/vehicle",
    "title": "用户车辆信息的录入",
    "description": "<p>用户车辆信息的录入</p>",
    "group": "vehicleSystem",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "questionId",
            "description": "<p>订单编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>车辆的类型</p>"
          },
          {
            "group": "Parameter",
            "type": "float",
            "optional": false,
            "field": "capacity",
            "description": "<p>载货量</p>"
          },
          {
            "group": "Parameter",
            "type": "float",
            "optional": false,
            "field": "oil",
            "description": "<p>排量</p>"
          },
          {
            "group": "Parameter",
            "type": "float",
            "optional": false,
            "field": "price",
            "description": "<p>价格</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "delFlag",
            "description": "<p>车辆状态</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"questionId\":11,\n    \"type\":皮卡,\n    \"capacity\":55.6,\n    \"oil\":5.5,\n    \"price\":200.5,\n    \"delFlag\":0\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n    \"status\":1\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "0",
            "description": "<p>该功能存在异常</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "2",
            "description": "<p>导入失败，请重试</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "4",
            "description": "<p>请完善必要的信息</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "5",
            "description": "<p>您未登录，请登陆后操作</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 0 存在异常\n{\n   \"status\": \"0\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/VehicleController.java",
    "groupTitle": "vehicleSystem",
    "name": "PostVehiclesystemUserVehicle"
  }
] });
