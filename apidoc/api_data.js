define({ "api": [
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
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/QuestionController.java",
    "groupTitle": "Question",
    "name": "DeleteQuestionRemovequestion"
  },
  {
    "type": "GET",
    "url": "/question/executeAlgorithm",
    "title": "执行算法",
    "description": "<p>通过questionId来执行算法</p>",
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
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/QuestionController.java",
    "groupTitle": "Question",
    "name": "GetQuestionExecutealgorithm"
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
          "content": "HTTP/1.1 200 OK\n{\n    \"status\": 200,\n    \"object\": [\n       {\n           \"questionId\": 1\n           \"questionName: \"问题名称\"\",\n           \"userId\": 1,\n           \"delFlag\": 0\n       }\n    ]\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/QuestionController.java",
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
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/QuestionController.java",
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
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/QuestionController.java",
    "groupTitle": "Question",
    "name": "PostQuestionInsertquestion"
  },
  {
    "type": "DELETE",
    "url": "/userSystem/session/user",
    "title": "用户注销",
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
            "field": "1",
            "description": "<p>注销成功</p>"
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
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/UserController.java",
    "groupTitle": "UserSystem",
    "name": "DeleteUsersystemSessionUser"
  },
  {
    "type": "GET",
    "url": "/user/eMailCode",
    "title": "邮箱验证码发送",
    "description": "<p>用户注册进行绑定邮箱</p>",
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
            "field": "1",
            "description": "<p>发送成功</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "5",
            "description": "<p>发送失败</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/UserController.java",
    "groupTitle": "UserSystem",
    "name": "GetUserEmailcode"
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
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/UserController.java",
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
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/UserController.java",
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
            "description": "<p>用户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userName",
            "description": "<p>用户名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "eMail",
            "description": "<p>用户邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "rePassword",
            "description": "<p>重复密码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mailCode",
            "description": "<p>邮箱验证码</p>"
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
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "5",
            "description": "<p>注册失败</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/UserController.java",
    "groupTitle": "UserSystem",
    "name": "PostUsersystemUser"
  },
  {
    "type": "DELETE",
    "url": "/finalSolution/removeAllQuestionFinalSolution",
    "title": "删除一个问题的所有解决方案",
    "description": "<p>通过问题id删除所有该问题的解决方案</p>",
    "group": "finalSolution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "questionId",
            "description": "<p>问题id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/FinalSolutionController.java",
    "groupTitle": "finalSolution",
    "name": "DeleteFinalsolutionRemoveallquestionfinalsolution"
  },
  {
    "type": "DELETE",
    "url": "/finalSolution/removeFinalSolution",
    "title": "删除一个方案",
    "description": "<p>通过方案Id删除方案</p>",
    "group": "finalSolution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "finalSolutionId",
            "description": "<p>方案id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/FinalSolutionController.java",
    "groupTitle": "finalSolution",
    "name": "DeleteFinalsolutionRemovefinalsolution"
  },
  {
    "type": "GET",
    "url": "/finalSolution/getAllFinalSolution",
    "title": "得到一个问题的所有解决方案",
    "description": "<p>通过问题Id得到所有该问题的解决方案</p>",
    "group": "finalSolution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "questionId",
            "description": "<p>问题id</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "finalSolutionId",
            "description": "<p>方案编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String[]",
            "optional": false,
            "field": "routes",
            "description": "<p>该方案下的所有路径</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "totalDis",
            "description": "<p>该方案的总路径</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "userChoice",
            "description": "<p>用户选择该方案的标志,0表示没有选择，1表示选择了</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建方案的时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n    \"status\":200,\n    \"object\": [\n    {\n        \"finalSolutionId\":1,\n        \"routes\":[\n        \"第一条路径\",\n        \"第二条路径\"\n        ]\n        “totalDis”:100,\n        \"userChoice\":0\n        \"createTime\": \"2018-10-06 12:00:00\"\n    }\n    ]\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/FinalSolutionController.java",
    "groupTitle": "finalSolution",
    "name": "GetFinalsolutionGetallfinalsolution"
  },
  {
    "type": "GET",
    "url": "/finalSolution/getAllVersion",
    "title": "得到该问题的所有版本号",
    "description": "<p>通过问题Id得到该问题的所有版本号</p>",
    "group": "finalSolution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "questionId",
            "description": "<p>问题id</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n    “status”：200\n    “Object”: [\n         1,\n         2\n    ]\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/FinalSolutionController.java",
    "groupTitle": "finalSolution",
    "name": "GetFinalsolutionGetallversion"
  },
  {
    "type": "GET",
    "url": "/finalSolution/getFinalSolution",
    "title": "得到一个方案的所有路径",
    "description": "<p>通过方案Id得到方案下的所有路径</p>",
    "group": "finalSolution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "finalSolutionId",
            "description": "<p>方案id</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "finalSolutionId",
            "description": "<p>方案编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String[]",
            "optional": false,
            "field": "routes",
            "description": "<p>该方案下的所有路径</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "totalDis",
            "description": "<p>该方案的总路径</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "userChoice",
            "description": "<p>用户选择该方案的标志,0表示没有选择，1表示选择了</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建方案的时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n    \"status\":200,\n    \"object\":{\n        \"finalSolutionId\":1,\n        \"routes\":[\n        \"第一条路径\",\n        \"第二条路径\"\n        ]\n        “totalDis”:100,\n        \"userChoice\":0\n        \"createTime\": \"2018-11-11 12:00:00\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/FinalSolutionController.java",
    "groupTitle": "finalSolution",
    "name": "GetFinalsolutionGetfinalsolution"
  },
  {
    "type": "GET",
    "url": "/finalSolution/getOneVersionFinalSolution",
    "title": "得到一个版本的解决方案",
    "description": "<p>通过问题Id和版本号得到一个版本的所有解决方案</p>",
    "group": "finalSolution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "questionId",
            "description": "<p>问题id</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "version",
            "description": "<p>版本号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "finalSolutionId",
            "description": "<p>方案编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String[]",
            "optional": false,
            "field": "routes",
            "description": "<p>该方案下的所有路径</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "totalDis",
            "description": "<p>该方案的总路径</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "userChoice",
            "description": "<p>用户选择该方案的标志,0表示没有选择，1表示选择了</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建方案的时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n    \"status\":200,\n    \"object\": [\n    {\n        \"finalSolutionId\":1,\n        \"routes\":[\n        \"第一条路径\",\n        \"第二条路径\"\n        ]\n        “totalDis”:100,\n        \"userChoice\":0\n        \"createTime\": \"2018-11-11 12:00:00\"\n    }\n    ]\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/FinalSolutionController.java",
    "groupTitle": "finalSolution",
    "name": "GetFinalsolutionGetoneversionfinalsolution"
  },
  {
    "type": "PATCH",
    "url": "/finalSolution/updateFinalSolutionState",
    "title": "修改用户选择的方案的状态",
    "description": "<p>用户选择一个自己觉得最好的方案,一个问题只能选择一个</p>",
    "group": "finalSolution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "questionId",
            "description": "<p>问题id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/FinalSolutionController.java",
    "groupTitle": "finalSolution",
    "name": "PatchFinalsolutionUpdatefinalsolutionstate"
  },
  {
    "type": "DELETE",
    "url": "/node/deleteNode",
    "title": "服务点的删除",
    "description": "<p>用户删除服务点</p>",
    "group": "node",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "nodeId",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "questionId",
            "description": ""
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "1",
            "description": "<p>删除成功</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n    \"status\":\"删除成功\"\n}",
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
            "field": "5",
            "description": "<p>未登录，无权操作</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 0 存在异常\n{\n   \"status\": 0\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/NodeController.java",
    "groupTitle": "node",
    "name": "DeleteNodeDeletenode"
  },
  {
    "type": "POST",
    "url": "/node/excelNodeInfo",
    "title": "服务点的录入",
    "description": "<p>用户服务点的录入（通过Excel）</p>",
    "group": "node",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "1",
            "description": "<p>录入成功</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n    \"status\":\"导入成功\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 0 存在异常\n{\n   \"status\": \"第1行...数据出现问题\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/NodeController.java",
    "groupTitle": "node",
    "name": "PostNodeExcelnodeinfo"
  },
  {
    "type": "POST",
    "url": "/node/newNode",
    "title": "服务点的录入",
    "description": "<p>用户服务点的录入(1.点地图2.输入地址)</p>",
    "group": "node",
    "parameter": {
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"questionId\":1,\n    \"nodeName\":\"邮电大学服务点\",\n    \"nodeAddress\":\"重庆市南岸区南山街道崇文路2号重庆邮电大学\",\n    \"lat\":155.52,\n    \"len\":242.25,\n    \"isCenter\":1/0,\n    \"delFlag\":1\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "1",
            "description": "<p>录入成功</p>"
          }
        ]
      },
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
            "description": "<p>录入失败，请重试</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "3",
            "description": "<p>请输入正确的地址</p>"
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
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/NodeController.java",
    "groupTitle": "node",
    "name": "PostNodeNewnode"
  },
  {
    "type": "UPDATE",
    "url": "/node/updateNode",
    "title": "服务点的更新",
    "description": "<p>用户更新服务点</p>",
    "group": "node",
    "parameter": {
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"questionId\":1,\n    \"nodeName\":\"邮电大学服务点\",\n    \"nodeAddress\":\"重庆市南岸区南山街道崇文路2号重庆邮电大学\",\n    \"lat\":155.52,\n    \"len\":242.25,\n    \"isCenter\":1/0,\n    \"delFlag\":1\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "1",
            "description": "<p>录入成功</p>"
          }
        ]
      },
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
            "description": "<p>更新失败，请重试</p>"
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
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/NodeController.java",
    "groupTitle": "node",
    "name": "UpdateNodeUpdatenode"
  },
  {
    "type": "DELETE",
    "url": "/vehicleSystem/user/vehicle",
    "title": "用户车辆的删除",
    "description": "<p>删除用户车辆</p>",
    "group": "vehicleSystem",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "vehicleId",
            "description": "<p>车辆编号</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n   \"status\"：1\n}",
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
            "description": "<p>请登陆后操作</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "4",
            "description": "<p>不存在该车辆，刷新页面后重新操作</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "5",
            "description": "<p>删除失败</p>"
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
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/VehicleController.java",
    "groupTitle": "vehicleSystem",
    "name": "DeleteVehiclesystemUserVehicle"
  },
  {
    "type": "GET",
    "url": "/vehicleSystem/user/vehicle",
    "title": "当前登录用户车辆信息查询",
    "description": "<p>查询</p>",
    "group": "vehicleSystem",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n        \"vehicleId\":1,{int}车辆编号\n        \"questionId\":1,{int}\n        \"type\":皮卡,{String}车型\n        \"capacity\":500,{float}载货量\n        \"oil\":4.3,{float}排量\n        \"date\":2018-09-26 22:38:14录入时间\n        \"price\":253.3,{float}价格\n        \"delFlag\":0,{int}0:存在 1:已删除\n}",
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
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/VehicleController.java",
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
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"questionId\":11,{int}订单编号\n    \"type\":皮卡,{String}车辆的类型\n    \"capacity\":55.6,{float}载货量\n    \"oil\":5.5,{float}排量\n    \"price\":200.5,{float}价格\n}",
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
    "filename": "E:/project/RoutePlanSystem/src/main/java/com/may/routeplansystem/controller/VehicleController.java",
    "groupTitle": "vehicleSystem",
    "name": "PostVehiclesystemUserVehicle"
  }
] });