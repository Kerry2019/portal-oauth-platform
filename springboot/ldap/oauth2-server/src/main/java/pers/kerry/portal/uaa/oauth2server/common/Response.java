package pers.kerry.portal.uaa.oauth2server.common;

import java.util.*;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/9/25 10:58
 * @history: 1.2019/9/25 created by kerry.wu
 */
public class Response {
    private String code;
    private String message;
    private Long total;
    private Object data;
    private List<Object> table;
    private String requestid;
    public static final String CODE_OK = "ok";
    public static final String CODE_ERR = "error";

    public Response() {
        this("ok", (String)null);
    }

    public Response(String code, String message) {
        this.code = "ok";
        this.code = code;
        this.message = message;
        this.requestid = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static Response error(String message) {
        return new Response("error", message);
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public Response data(Object data) {
        return this.setData(data);
    }

    public Response addListItem(Object item) {
        if (this.table == null) {
            this.table = new ArrayList();
        }

        this.table.add(item);
        return this;
    }

    public Response setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Response setTotal(Integer total) {
        this.total = (long)total;
        return this;
    }

    public static Response ok() {
        return new Response();
    }

    public Response set(String field, String value) {
        if (this.data == null || !(this.data instanceof Map)) {
            this.data = new HashMap();
        }

        ((Map)this.data).put(field, value);
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public Response setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Long getTotal() {
        return this.total == null && this.table != null ? Long.valueOf(String.valueOf(this.table.size())) : this.total;
    }

    public Object getData() {
        return this.data;
    }

    public List<Object> getTable() {
        return this.table;
    }

    public Response setTable(List table) {
        this.table = table;
        return this;
    }

    public Response table(List table) {
        return this.setTable(table);
    }

    public String getRequestid() {
        return this.requestid;
    }

    public Response setRequestid(String requestid) {
        this.requestid = requestid;
        return this;
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
    }
}

