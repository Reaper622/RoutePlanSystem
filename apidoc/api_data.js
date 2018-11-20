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
  }
] });
