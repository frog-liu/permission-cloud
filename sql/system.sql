DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    username VARCHAR(50) NOT NULL COMMENT '用户账号',
    NAME VARCHAR(50) DEFAULT NULL COMMENT '用户名称',
    PASSWORD VARCHAR(30) NOT NULL COMMENT '用户密码',
    phone VARCHAR(11) DEFAULT NULL COMMENT '用户手机号',
    email VARCHAR(50) DEFAULT NULL COMMENT '用户邮箱',
    department_id BIGINT(11) NOT NULL COMMENT '用户所在部门id',
    sex TINYINT(1) NOT NULL DEFAULT 2 COMMENT '用户性别:0-女;1-男;2-未知',
    avatar VARCHAR(180) DEFAULT NULL COMMENT '用户头像url',
    STATUS TINYINT(1) NOT NULL DEFAULT 1 COMMENT '用户状态:0-停用;1-正常;2-锁定',
    create_by BIGINT(20) NOT NULL COMMENT '创建人id',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    last_update_by BIGINT(20) NOT NULL COMMENT '最近更新人id',
    last_update_time DATETIME NOT NULL COMMENT '最近更新时间',
    PRIMARY KEY (id)
) ENGINE=INNODB COMMENT = '用户信息表';

DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
    NAME VARCHAR(50) NOT NULL COMMENT '角色名称',
    CODE VARCHAR(50) NOT NULL COMMENT '角色code',
    description VARCHAR(200) DEFAULT NULL COMMENT '角色描述',
    STATUS CHAR(1) NOT NULL DEFAULT 1 COMMENT '角色状态:0-停用;1-正常',
    create_by BIGINT(20) NOT NULL COMMENT '创建人id',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    last_update_by BIGINT(20) NOT NULL COMMENT '最近更新人id',
    last_update_time DATETIME NOT NULL COMMENT '最近更新时间',
    PRIMARY KEY (id)
) ENGINE=INNODB COMMENT = '角色信息表';

DROP TABLE IF EXISTS t_user_role;
CREATE TABLE t_user_role (
     user_id BIGINT(20) NOT NULL COMMENT '用户id',
     role_id BIGINT(20) NOT NULL COMMENT '角色id',
     create_by BIGINT(20) NOT NULL COMMENT '创建人id',
     create_time DATETIME NOT NULL COMMENT '创建时间',
     last_update_by BIGINT(20) NOT NULL COMMENT '最近更新人id',
     last_update_time DATETIME NOT NULL COMMENT '最近更新时间',
     PRIMARY KEY (user_id, role_id)
) ENGINE=INNODB COMMENT = '用户角色关联表';

DROP TABLE IF EXISTS t_department;
CREATE TABLE t_department (
      id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
      parent_id BIGINT(20) NOT NULL DEFAULT -1 COMMENT '上级部门id',
      NAME VARCHAR(50) NOT NULL COMMENT '部门名称',
      director_id BIGINT(20) DEFAULT NULL COMMENT '部门总监id',
      administrator_id BIGINT(20) DEFAULT NULL COMMENT '首席运营官/首席技术官id',
      STATUS CHAR(1) NOT NULL DEFAULT 1 COMMENT '部门状态:0-停用;1-正常',
      create_by BIGINT(20) NOT NULL COMMENT '创建人id',
      create_time DATETIME NOT NULL COMMENT '创建时间',
      last_update_by BIGINT(20) NOT NULL COMMENT '最近更新人id',
      last_update_time DATETIME NOT NULL COMMENT '最近更新时间',
      PRIMARY KEY (id)
) ENGINE=INNODB COMMENT = '部门信息表';

DROP TABLE IF EXISTS t_menu;
CREATE TABLE t_menu (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
    parent_id BIGINT(20) NOT NULL DEFAULT -1 COMMENT '上级菜单id',
    NAME VARCHAR(50) NOT NULL COMMENT '菜单名称',
    order_num INT(11) NOT NULL COMMENT '显示顺序',
    TYPE TINYINT(1) NOT NULL COMMENT '菜单类型:0-目录;1-菜单;2-按钮',
    path VARCHAR(30) NOT NULL COMMENT '路由路径',
    permission VARCHAR(30) DEFAULT NULL COMMENT '权限',
    icon VARCHAR(50) DEFAULT NULL COMMENT '图标',
    STATUS CHAR(1) NOT NULL DEFAULT 1 COMMENT '部门状态:0-停用;1-正常',
    create_by BIGINT(20) NOT NULL COMMENT '创建人id',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    last_update_by BIGINT(20) NOT NULL COMMENT '最近更新人id',
    last_update_time DATETIME NOT NULL COMMENT '最近更新时间',
    PRIMARY KEY (id)
) ENGINE=INNODB COMMENT = '菜单信息表';

DROP TABLE IF EXISTS t_role_menu;
CREATE TABLE t_role_menu (
     role_id BIGINT(20) NOT NULL COMMENT '角色id',
     menu_id BIGINT(20) NOT NULL COMMENT '菜单id',
     create_by BIGINT(20) NOT NULL COMMENT '创建人id',
     create_time DATETIME NOT NULL COMMENT '创建时间',
     last_update_by BIGINT(20) NOT NULL COMMENT '最近更新人id',
     last_update_time DATETIME NOT NULL COMMENT '最近更新时间',
     PRIMARY KEY (role_id, menu_id)
) ENGINE=INNODB COMMENT = '角色菜单关联表';

DROP TABLE IF EXISTS t_operation_log;
CREATE TABLE t_operation_log (
     id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
     description VARCHAR(50) DEFAULT NULL COMMENT '操作描述',
     request_id BIGINT(20) NOT NULL COMMENT '请求id',
     ip VARCHAR(50) DEFAULT NULL COMMENT '请求ip',
     STATUS CHAR(1) NOT NULL DEFAULT 1 COMMENT '请求状态:0-失败;1-成功',
     create_by BIGINT(20) NOT NULL COMMENT '创建人id',
     create_time DATETIME NOT NULL COMMENT '创建时间',
     last_update_by BIGINT(20) NOT NULL COMMENT '最近更新人id',
     last_update_time DATETIME NOT NULL COMMENT '最近更新时间',
     PRIMARY KEY (id)
) ENGINE=INNODB COMMENT = '操作日志表';