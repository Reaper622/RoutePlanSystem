package com.may.routeplansystem.controller;import com.alibaba.fastjson.JSON;import com.alibaba.fastjson.JSONObject;import com.may.routeplansystem.constant.StatusCode;import com.may.routeplansystem.pojo.NodePojo;import com.may.routeplansystem.service.NodeService;import com.may.routeplansystem.util.ExcelUtil;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.*;import org.springframework.web.multipart.MultipartFile;import org.thymeleaf.util.StringUtils;import javax.servlet.http.HttpSession;import javax.validation.constraints.NotNull;import java.util.HashMap;import java.util.Map;/** * @author:dengsiyuan * @Date:2018/10/22 16:30 */@RestController@RequestMapping("node")public class NodeController {    @Autowired    private NodeService nodeService;    private String userAttribute = "user";    /**     * @api {POST} /node/newNode 服务点的录入     * @apiDescription 用户服务点的录入(1.点地图2.输入地址)     * @apiGroup node     * @apiParamExample {json} Request-Example:     *     {     *         "questionId":1,     *         "nodeName":"邮电大学服务点",     *         "nodeAddress":"重庆市南岸区南山街道崇文路2号重庆邮电大学",     *         "lat":155.52,     *         "len":242.25,     *         "isCenter":1/0,     *         "delFlag":1     *     }     * @apiSuccess 1 录入成功     * @apiSuccessExample Success-Response:     *     HTTP/1.1 200 OK     *     {     *         "status":1     *     }     * @apiError 0 该功能存在异常     * @apiError 2 录入失败，请重试     * @apiError 3 请输入正确的地址     * @apiError 4 请完善必要的信息     * @apiError 5 您未登录，请登陆后操作     * @apiErrorExample {json} Error-Response:     *     HTTP/1.1 0 存在异常     *     {     *        "status": "0"     *     }     */    @PostMapping(value = "newNode")    public Object insertNode(@RequestBody JSONObject nodeJson, HttpSession session){        NodePojo nodePojo = JSONObject.toJavaObject(nodeJson,NodePojo.class);        Map map = new HashMap<String,String>(16);        try {            System.out.println(nodePojo.toString());            if(session.getAttribute(userAttribute) != null){                if(nodePojo.getNodeAddress() != null){                    map.clear();                    map.put("status",nodeService.insertNode(nodePojo));                }else{                    map.clear();                    map.put("status",StatusCode.MESSAGE_NULL);                }            }else {                map.clear();                map.put("status",StatusCode.PERMISSION_FAIL);            }        }catch (Exception e){            e.printStackTrace();            map.clear();            map.put("status",StatusCode.FAIL);        }        return map;    }    /**     * @api {POST} /node/excelNodeInfo 服务点的录入     * @apiDescription 用户服务点的录入（通过Excel）     * @apiGroup node     * @apiSuccess 1 录入成功     * @apiSuccessExample Success-Response:     *     HTTP/1.1 200 OK     *     {     *         "status":"导入成功"     *     }     * @apiErrorExample {json} Error-Response:     *     HTTP/1.1 0 存在异常     *     {     *        "status": "第1行...数据出现问题"     *     }     */    @PostMapping(value = "excelNodeInfo")    public Object upload(@RequestParam(value="file", required = false) MultipartFile file, HttpSession session) throws Exception {        Map map = new HashMap<String,String>(16);        try {            //判断文件是否为空            if (file == null) {                map.clear();                //4,文件为空                map.put("status","您所上传文件为空！！");            }            //获取文件名            String fileName = file.getOriginalFilename();            //验证文件名是否合格            if (!ExcelUtil.validateExcel(fileName)) {                map.clear();                //3，文件不符合要求                map.put("status","文件类型不符合要求");            }            //获取到文件名后，再次判断是否符合要求，是否为空            long size = file.getSize();            if (StringUtils.isEmpty(fileName) || size == 0) {                //4,文件大小或名称为空                map.put("status","文件大小或名称为空!!");            }            //批量导入            String message = nodeService.batchImport(fileName,file,session);            map.clear();            map.put("status",message);        }catch (Exception e){            map.clear();            map.put("status","上传失败！！！");            e.printStackTrace();        }        return JSON.toJSON(map);    }    /**     * @api {DELETE} /node/deleteNode 服务点的删除     * @apiDescription 用户删除服务点     * @apiGroup node     * @apiParam {int} nodeId     * @apiParam {int} questionId     * @apiSuccess 1 删除成功     * @apiSuccessExample Success-Response:     *     HTTP/1.1 200 OK     *     {     *         "status":"删除成功"     *     }     * @apiError 5 未登录，无权操作     * @apiErrorExample {json} Error-Response:     *     HTTP/1.1 0 存在异常     *     {     *        "status": 0     *     }     */    @DeleteMapping("deleteNode")    public Object deleteNode(@NotNull NodePojo nodePojo, HttpSession session){        Map map = new HashMap<String,String>(16);        try {            if(session.getAttribute(userAttribute) != null){                map.clear();                map.put("status", nodeService.deleteNode(nodePojo));            }else {                map.clear();                map.put("status",StatusCode.PERMISSION_FAIL);            }        }catch (Exception e){            map.clear();            map.put("status",StatusCode.FAIL);            e.printStackTrace();        }        return map;    }    /**     * @api {UPDATE} /node/updateNode 服务点的更新     * @apiDescription 用户更新服务点     * @apiGroup node     * @apiParamExample {json} Request-Example:     *     {     *         "questionId":1,     *         "nodeName":"邮电大学服务点",     *         "nodeAddress":"重庆市南岸区南山街道崇文路2号重庆邮电大学",     *         "lat":155.52,     *         "len":242.25,     *         "isCenter":1/0,     *         "delFlag":1     *     }     * @apiSuccess 1 录入成功     * @apiSuccessExample Success-Response:     *     HTTP/1.1 200 OK     *     {     *         "status":1     *     }     * @apiError 0 该功能存在异常     * @apiError 2 更新失败，请重试     * @apiError 4 请完善必要的信息     * @apiError 5 您未登录，请登陆后操作     * @apiErrorExample {json} Error-Response:     *     HTTP/1.1 0 存在异常     *     {     *        "status": "0"     *     }     */    @PatchMapping("updateNode")    public Object updateNode(@RequestBody JSONObject nodeJson,HttpSession session){        NodePojo nodePojo = JSONObject.toJavaObject(nodeJson,NodePojo.class);        Map map = new HashMap<String,String>(16);        try {            if(session.getAttribute(userAttribute) != null){                map.clear();                map.put("status",nodeService.updateNode(nodePojo));            }else {                map.clear();                map.put("status",StatusCode.PERMISSION_FAIL);            }        }catch (Exception e){            map.clear();            map.put("status",StatusCode.FAIL);            e.printStackTrace();        }        return map;    }    @GetMapping("selectNode")    public Object selectNode(@NotNull NodePojo nodePojo,HttpSession session){        Map map = new HashMap<String,Object>(16);        try {            if(session.getAttribute(userAttribute) != null){                map.clear();                map.put("status",nodeService.selectNode(nodePojo));                return map;            }else {                map.clear();                map.put("status",StatusCode.PERMISSION_FAIL);            }        }catch (Exception e){            map.clear();            map.put("status",StatusCode.FAIL);            e.printStackTrace();        }        return map;    }}