package com.alice.boot.common.enums;

/**
 * http request method
 * GET（SELECT）：从服务器查询，可以在服务器通过请求的参数区分查询的方式。
 * POST（CREATE）：在服务器新建一个资源，调用insert操作。
 * PUT（UPDATE）：在服务器更新资源，调用update操作。
 * DELETE（DELETE）：从服务器删除资源，调用delete语句
 *
 * @author Alice
 */
public enum RequestMethod {
    /**
     * get
     */
    GET,
    /**
     * post
     */
    POST,
    /**
     * put
     */
    PUT,
    /**
     * patch
     */
    PATCH,
    /**
     * delete
     */
    DELETE,
    /**
     * head
     */
    HEAD,
    /**
     * options
     */
    OPTIONS
}
