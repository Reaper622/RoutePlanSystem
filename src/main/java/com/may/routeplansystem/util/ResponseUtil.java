package com.may.routeplansystem.util;import com.may.routeplansystem.constant.StatusCode;import com.may.routeplansystem.pojo.NodePojo;import org.apache.http.HttpEntity;import org.apache.http.HttpResponse;import org.apache.http.client.methods.HttpGet;import org.apache.http.client.utils.URIBuilder;import org.apache.http.impl.client.CloseableHttpClient;import org.apache.http.impl.client.HttpClients;import org.apache.http.util.EntityUtils;import java.net.URI;/** * @author:dengsiyuan * @Date:2018/11/6 16:28 */public class ResponseUtil {    /**     * 获取响应的经纬度     * @param response     * */    public NodePojo getLatLng(HttpResponse response,NodePojo nodePojo){        try {            HttpEntity entity = response.getEntity();            String result = EntityUtils.toString(entity, "UTF-8").trim();            System.out.println(result);            int statusStart = result.indexOf("\"status\":");            int status = Integer.parseInt(result.substring(statusStart+9,statusStart+10));            if(status == 0) {                int lngStart = result.indexOf("\"lng\":");                int lngEnd = result.indexOf(",\"lat\"");                int latEnd = result.indexOf("},\"precise\"");                double lng = Double.parseDouble(result.substring(lngStart + 6, lngEnd));                double lat = Double.parseDouble(result.substring(lngEnd + 7, latEnd));                nodePojo.setLat(lat);                nodePojo.setLng(lng);            }else if(status == 1 || status == 2){                nodePojo=null;            }        }catch (Exception e){            e.printStackTrace();            nodePojo=null;        }        return nodePojo;    }}