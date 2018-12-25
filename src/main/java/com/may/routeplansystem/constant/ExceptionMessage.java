package com.may.routeplansystem.constant;

public interface ExceptionMessage {

    /** */
    String VEHICLE_STORE_FAILURE = "车辆存入数据库失败";
    String VEHICLE_CANNOT_NULL = "车辆信息不能为空";
    String VEHICLE_NULL_BY_USERID = "不能通过用户ID找到车辆信息";
    String VEHICLE_NULL_BY_VEHICLEID = "不能通过车辆ID扎到车辆信息";
    String VEHICLE_DELETE_FAILURE = "车辆删除失败";
    String VEHICLE_ID_LIST_EMPTY = "删除车辆的ID不能为空";
    /**
     * 过程中的错误
     */
    String NOT_QUESTION = "所计算距离的问题为空";
    String NOT_NODES = "在计算距离之前请导入点";
}
