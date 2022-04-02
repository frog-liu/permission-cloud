package com.frog.system.constant;

/**
 * @author lh
 * api url 常量类
 */
public interface ApiConstants {

    /**
     * 所有路径
     */
    String ALL = "/**";

    /**
     * 所有授权路径
     */
    String OAUTH_ALL = "/oauth/**";

    String OAUTH_AUTHORIZE = "/oauth/authorize";

    /**
     * 登录
     */
    String LOGIN = "/login";

    String LIST = "/list";

    String TREE = "/tree";

    /**
     * 用户信息
     */
    String USER = "/users";

    /**
     * 部门信息
     */
    String DEPARTMENT = "/departments";

    /**
     * 角色信息
     */
    String ROLE = "/roles";

    /**
     * 菜单信息
     */
    String MENU = "/menus";

    /**
     * 登录信息
     */
    String LOGIN_INFO = "/loginInfo";

    /**
     * 操作日志
     */
    String OPERATION_LOG = "/operationLog";

}
