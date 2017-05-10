/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : sales

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-05-10 14:17:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sale_department
-- ----------------------------
DROP TABLE IF EXISTS `sale_department`;
CREATE TABLE `sale_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(32) NOT NULL DEFAULT '' COMMENT '组名',
  `p_Id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父亲组id',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态: 1应用2禁用',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '创建日期',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sale_department
-- ----------------------------
INSERT INTO `sale_department` VALUES ('1', 'ROOT', '0', '1', '1970-01-01 08:00:00', '1970-01-01 08:00:00');
INSERT INTO `sale_department` VALUES ('2', '全部', '1', '1', '1970-01-01 08:00:00', '1970-01-01 08:00:00');
INSERT INTO `sale_department` VALUES ('3', '销售', '1', '1', '1970-01-01 08:00:00', '1970-01-01 08:00:00');
INSERT INTO `sale_department` VALUES ('4', '重客', '1', '1', '1970-01-01 08:00:00', '1970-01-01 08:00:00');
INSERT INTO `sale_department` VALUES ('5', '网销中心', '1', '1', '1970-01-01 08:00:00', '2017-05-10 11:34:43');
INSERT INTO `sale_department` VALUES ('7', 'dccvsdfs', '1', '2', '2017-05-10 11:34:58', '2017-05-10 11:35:02');

-- ----------------------------
-- Table structure for sale_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sale_dictionary`;
CREATE TABLE `sale_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dict_name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `dict_value` varchar(50) NOT NULL DEFAULT '' COMMENT '值',
  `dcit_code` varchar(20) NOT NULL DEFAULT '' COMMENT '分类',
  `p_id` bigint(20) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '创建日期',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sale_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for sale_group
-- ----------------------------
DROP TABLE IF EXISTS `sale_group`;
CREATE TABLE `sale_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(32) NOT NULL DEFAULT '' COMMENT '组名',
  `p_Id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父亲组id',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态: 1应用2禁用',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '创建日期',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='分组表';

-- ----------------------------
-- Records of sale_group
-- ----------------------------
INSERT INTO `sale_group` VALUES ('1', '总公司', '0', '1', '1970-01-01 08:00:00', '1970-01-01 08:00:00');
INSERT INTO `sale_group` VALUES ('2', '销售中心', '1', '1', '2017-05-03 11:29:27', '1970-01-01 08:00:00');
INSERT INTO `sale_group` VALUES ('3', '北京销售中心', '2', '1', '2017-05-03 11:29:58', '2017-05-05 12:43:33');
INSERT INTO `sale_group` VALUES ('4', '销售1组', '3', '1', '2017-05-03 11:30:10', '1970-01-01 08:00:00');


-- ----------------------------
-- Table structure for sale_group_menu
-- ----------------------------
DROP TABLE IF EXISTS `sale_group_menu`;
CREATE TABLE `sale_group_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '组id',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL,
  `operates` varchar(50) NOT NULL DEFAULT '' COMMENT '操作按钮id',
  `exp1` varchar(64) DEFAULT '' COMMENT '扩展字段',
  `exp2` varchar(100) DEFAULT '' COMMENT '扩展字段',
  `exp3` varchar(255) DEFAULT '' COMMENT '扩展字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='团队组菜单资源权限表';

-- ----------------------------
-- Records of sale_group_menu
-- ----------------------------
INSERT INTO `sale_group_menu` VALUES ('5', '1', '4', '2', '', null, null, null);
INSERT INTO `sale_group_menu` VALUES ('6', '1', '4', '13', '', null, null, null);
INSERT INTO `sale_group_menu` VALUES ('7', '1', '4', '9', '1,3,5,6', null, null, null);
INSERT INTO `sale_group_menu` VALUES ('8', '1', '4', '11', '1,3,5,6', null, null, null);
INSERT INTO `sale_group_menu` VALUES ('9', '1', '4', '5', '1,3,5,6', null, null, null);
INSERT INTO `sale_group_menu` VALUES ('10', '1', '4', '12', '1,3,5,6', null, null, null);
INSERT INTO `sale_group_menu` VALUES ('11', '1', '4', '6', '1,3,5,6', null, null, null);
INSERT INTO `sale_group_menu` VALUES ('12', '1', '4', '7', '1,3,5,6', null, null, null);
INSERT INTO `sale_group_menu` VALUES ('13', '1', '4', '20', '1,3,5,6', null, null, null);
INSERT INTO `sale_group_menu` VALUES ('14', '1', '4', '21', '1,3,5,6', null, null, null);
INSERT INTO `sale_group_menu` VALUES ('16', '12', '3', '2', '1,3,6', null, null, null);
INSERT INTO `sale_group_menu` VALUES ('17', '4', '5', '5', '1,3,5,6', null, null, null);
INSERT INTO `sale_group_menu` VALUES ('18', '4', '5', '19', '1,3,5,6,7,8,9', null, null, null);

-- ----------------------------
-- Table structure for sale_log
-- ----------------------------
DROP TABLE IF EXISTS `sale_log`;
CREATE TABLE `sale_log` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '内容',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `operate` varchar(64) NOT NULL DEFAULT '' COMMENT '操作信息',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `ip` varchar(20) NOT NULL DEFAULT '' COMMENT 'ip地址',
  `desc` varchar(255) NOT NULL DEFAULT '' COMMENT '详情',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of sale_log
-- ----------------------------

-- ----------------------------
-- Table structure for sale_menu
-- ----------------------------
DROP TABLE IF EXISTS `sale_menu`;
CREATE TABLE `sale_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单名',
  `menu_code` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单编码',
  `p_Id` bigint(20) DEFAULT NULL COMMENT '父菜单id',
  `levels` int(11) NOT NULL DEFAULT '1' COMMENT '层级',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '链接地址url',
  `icon` varchar(100) NOT NULL DEFAULT 'fa fa-circle-o' COMMENT '图标名',
  `target_type` varchar(32) NOT NULL DEFAULT 'iframe-tab' COMMENT '点左侧菜单的打开方式：iframe-tab为iframe;blank为新打开空白页；ajax代表ajax方式打开页面；代表单iframe页面',
  `types` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分类',
  `sort` decimal(5,1) NOT NULL DEFAULT '1.0' COMMENT '排序',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态: 1应用 2禁用',
  `remark` varchar(50) NOT NULL DEFAULT '' COMMENT '备注',
  `exp1` varchar(25) NOT NULL DEFAULT '',
  `exp2` varchar(50) NOT NULL DEFAULT '',
  `exp3` varchar(100) NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '创建日期',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sale_menu
-- ----------------------------
 
INSERT INTO `sale_menu` VALUES ('8', '系统管理', 'MENU8', null, '1', '-', 'fa fa-circle-o', 'ajax', '0', '7.0', '1', '', '', '', '', '1970-01-01 08:00:00', '2017-04-28 10:34:12');
INSERT INTO `sale_menu` VALUES ('9', '角色管理', 'MENU9', '16', '2', '/role/roleListPage', 'fa fa-circle-o', 'ajax', '0', '2.0', '1', '', '', '', '', '1970-01-01 08:00:00', '2017-04-26 16:33:47');
INSERT INTO `sale_menu` VALUES ('10', '账户管理', 'MENU10', '16', '2', '/user/userListPage', 'fa fa-circle-o', 'ajax', '0', '1.0', '1', '', '', '', '', '1970-01-01 08:00:00', '2017-04-26 15:58:57');
INSERT INTO `sale_menu` VALUES ('11', '菜单管理', 'MENU11', '16', '2', '/menu/menuListPage', 'fa fa-circle-o', 'ajax', '0', '3.0', '1', '', '', '', '', '1970-01-01 08:00:00', '2017-04-26 15:59:12');
INSERT INTO `sale_menu` VALUES ('12', '团队管理', 'MENU12', '8', '2', '/group/groupListPage', 'fa fa-circle-o', 'ajax', '0', '4.0', '1', '', '', '', '', '1970-01-01 08:00:00', '2017-04-28 09:48:21');
INSERT INTO `sale_menu` VALUES ('13', '业务部门', 'MENU13', '16', '2', '/department/deptListPage', 'fa fa-circle-o', 'ajax', '0', '10.0', '1', '', '', '', '', '1970-01-01 08:00:00', '2017-05-04 15:38:51');
INSERT INTO `sale_menu` VALUES ('16', '运维功能', 'MENU14', null, '1', '-', 'fa fa-circle-o', 'ajax', '0', '9.0', '1', '', '', '', '', '1970-01-01 08:00:00', '2017-04-26 15:53:07');
INSERT INTO `sale_menu` VALUES ('18', '按钮定义', 'MENU18', '16', '2', '/operate/operateListPage', 'fa fa-circle-o', 'ajax', '0', '6.0', '1', '', '', '', '', '1970-01-01 08:00:00', '2017-04-26 18:10:11');
INSERT INTO `sale_menu` VALUES ('19', '团队角色', 'MENU19', '8', '2', '/roleGroup/roleGroupListPage', 'fa fa-circle-o', 'ajax', '0', '9.0', '1', '', '', '', '', '1970-01-01 08:00:00', '2017-04-28 10:33:55');
INSERT INTO `sale_menu` VALUES ('20', '团队成员', 'MENU20', '8', '2', '/userGroup/userGroupListPage', 'fa fa-circle-o', 'ajax', '0', '3.0', '1', '', '', '', '', '1970-01-01 08:00:00', '2017-04-26 22:37:49');
INSERT INTO `sale_menu` VALUES ('22', '测试菜单', 'MENU22', '16', '2', '/user/dev', 'fa fa-circle-o', 'ajax', '0', '111.0', '1', '', '', '', '', '1970-01-01 08:00:00', '2017-05-04 15:48:07');
INSERT INTO `sale_menu` VALUES ('23', '测试6', 'MENU23', '16', '2', '/user/dev', 'fa fa-circle-o', 'iframe-tab', '0', '222.0', '2', '', '', '', '', '1970-01-01 08:00:00', '2017-05-08 13:58:26');

-- ----------------------------
-- Table structure for sale_menu_operate
-- ----------------------------
DROP TABLE IF EXISTS `sale_menu_operate`;
CREATE TABLE `sale_menu_operate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '菜单id',
  `oper_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '功能id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单按钮中间表';

-- ----------------------------
-- Records of sale_menu_operate
-- ----------------------------

-- ----------------------------
-- Table structure for sale_operate
-- ----------------------------
DROP TABLE IF EXISTS `sale_operate`;
CREATE TABLE `sale_operate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `oper_name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `oper_code` varchar(50) NOT NULL DEFAULT '' COMMENT '编码',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态: 1应用 2禁用',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '创建日期',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='按钮操作功能表';

-- ----------------------------
-- Records of sale_operate
-- ----------------------------
INSERT INTO `sale_operate` VALUES ('1', '添加', 'ADD', '1', '1970-01-01 08:00:00', '2017-04-26 22:16:40');
INSERT INTO `sale_operate` VALUES ('2', '批量加人', 'BATCHADD', '1', '2017-04-26 18:34:01', '1970-01-01 08:00:00');
INSERT INTO `sale_operate` VALUES ('3', '删除', 'REMOVE', '1', '2017-04-26 18:34:01', '1970-01-01 08:00:00');
INSERT INTO `sale_operate` VALUES ('5', '权限', 'AUTH', '1', '2017-04-26 22:16:55', '1970-01-01 08:00:00');
INSERT INTO `sale_operate` VALUES ('6', '修改', 'UPDATE', '1', '2017-04-26 22:17:09', '1970-01-01 08:00:00');
INSERT INTO `sale_operate` VALUES ('7', '功能', 'FUNCTION', '1', '2017-05-04 15:12:10', '1970-01-01 08:00:00');
INSERT INTO `sale_operate` VALUES ('8', '职务', 'TITLE', '1', '2017-05-04 15:12:34', '1970-01-01 08:00:00');
INSERT INTO `sale_operate` VALUES ('9', '重置密码', 'REPWD', '1', '2017-05-04 15:13:02', '1970-01-01 08:00:00');
INSERT INTO `sale_operate` VALUES ('10', '上传', 'UPLOAD', '1', '2017-05-08 10:29:18', '1970-01-01 08:00:00');
INSERT INTO `sale_operate` VALUES ('11', '下载', 'DOWNLOAD', '1', '2017-05-08 10:45:36', '1970-01-01 08:00:00');
INSERT INTO `sale_operate` VALUES ('12', '启用/禁用', 'USE', '1', '1970-01-01 08:00:00', '1970-01-01 08:00:00');
INSERT INTO `sale_operate` VALUES ('13', '添加联系人', 'addcustomer', '1', '2017-05-10 11:16:45', '1970-01-01 08:00:00');
INSERT INTO `sale_operate` VALUES ('14', '测试', '', '1', '2017-05-10 11:48:19', '1970-01-01 08:00:00');

-- ----------------------------
-- Table structure for sale_role
-- ----------------------------
DROP TABLE IF EXISTS `sale_role`;
CREATE TABLE `sale_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(50) NOT NULL DEFAULT '' COMMENT '角色编码',
  `remark` varchar(50) NOT NULL DEFAULT '' COMMENT '备注',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态: 1应用2禁用',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '创建日期',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sale_role
-- ----------------------------
INSERT INTO `sale_role` VALUES ('1', '超级管理员', 'SUPER_ADMIN', '系统最高权限', '1', '2017-04-27 15:23:56', '2017-04-25 14:18:48');
INSERT INTO `sale_role` VALUES ('2', '管理员', 'ADMIN', '可以查看自己及其自己团队下的所有子团队及业务员的信息', '2', '2017-04-27 15:24:13', '2017-05-10 11:12:45');
INSERT INTO `sale_role` VALUES ('3', '团队长', 'MANAGER', '可以查看自己及其自己团队下的所有子团队及业务员的信息', '1', '2017-04-27 15:24:13', '2017-04-27 19:28:58');
INSERT INTO `sale_role` VALUES ('4', '助理', 'ASSIGNER', '可以查看自己及其自己团队下的所有子团队及业务员的信息', '1', '2017-04-27 15:24:13', '2017-04-26 16:15:55');
INSERT INTO `sale_role` VALUES ('5', '组员', 'MEMBER', '仅可以查看自己的信息仅可以查看自己的信息', '1', '2017-04-27 15:24:13', '2017-04-26 17:30:46');

-- ----------------------------
-- Table structure for sale_role_group
-- ----------------------------
DROP TABLE IF EXISTS `sale_role_group`;
CREATE TABLE `sale_role_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '组id',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='角色分组中间表';

-- ----------------------------
-- Records of sale_role_group
-- ----------------------------
INSERT INTO `sale_role_group` VALUES ('1', '1', '1');
INSERT INTO `sale_role_group` VALUES ('2', '1', '3');
INSERT INTO `sale_role_group` VALUES ('3', '1', '4');
INSERT INTO `sale_role_group` VALUES ('4', '1', '5');
INSERT INTO `sale_role_group` VALUES ('5', '2', '3');
INSERT INTO `sale_role_group` VALUES ('6', '2', '4');
INSERT INTO `sale_role_group` VALUES ('7', '2', '5');
INSERT INTO `sale_role_group` VALUES ('8', '3', '3');
INSERT INTO `sale_role_group` VALUES ('9', '3', '4');
INSERT INTO `sale_role_group` VALUES ('10', '3', '5');
INSERT INTO `sale_role_group` VALUES ('11', '4', '3');
INSERT INTO `sale_role_group` VALUES ('12', '4', '4');
INSERT INTO `sale_role_group` VALUES ('13', '4', '5');
INSERT INTO `sale_role_group` VALUES ('14', '5', '3');
INSERT INTO `sale_role_group` VALUES ('15', '5', '4');
INSERT INTO `sale_role_group` VALUES ('16', '5', '5');
INSERT INTO `sale_role_group` VALUES ('17', '6', '3');
INSERT INTO `sale_role_group` VALUES ('18', '6', '4');
INSERT INTO `sale_role_group` VALUES ('19', '6', '5');
INSERT INTO `sale_role_group` VALUES ('20', '7', '3');
INSERT INTO `sale_role_group` VALUES ('21', '7', '4');
INSERT INTO `sale_role_group` VALUES ('22', '7', '5');
INSERT INTO `sale_role_group` VALUES ('23', '8', '3');
INSERT INTO `sale_role_group` VALUES ('24', '8', '4');
INSERT INTO `sale_role_group` VALUES ('25', '8', '5');
INSERT INTO `sale_role_group` VALUES ('26', '9', '3');
INSERT INTO `sale_role_group` VALUES ('27', '9', '4');
INSERT INTO `sale_role_group` VALUES ('28', '9', '5');
INSERT INTO `sale_role_group` VALUES ('29', '10', '3');
INSERT INTO `sale_role_group` VALUES ('30', '10', '4');
INSERT INTO `sale_role_group` VALUES ('31', '10', '5');
INSERT INTO `sale_role_group` VALUES ('32', '11', '3');
INSERT INTO `sale_role_group` VALUES ('33', '11', '4');
INSERT INTO `sale_role_group` VALUES ('34', '11', '5');
INSERT INTO `sale_role_group` VALUES ('35', '12', '3');
INSERT INTO `sale_role_group` VALUES ('36', '12', '4');
INSERT INTO `sale_role_group` VALUES ('37', '12', '5');
INSERT INTO `sale_role_group` VALUES ('38', '13', '3');
INSERT INTO `sale_role_group` VALUES ('39', '13', '4');
INSERT INTO `sale_role_group` VALUES ('40', '13', '5');
INSERT INTO `sale_role_group` VALUES ('41', '14', '3');
INSERT INTO `sale_role_group` VALUES ('42', '14', '4');
INSERT INTO `sale_role_group` VALUES ('43', '14', '5');
INSERT INTO `sale_role_group` VALUES ('44', '15', '3');
INSERT INTO `sale_role_group` VALUES ('45', '15', '4');
INSERT INTO `sale_role_group` VALUES ('46', '15', '5');

-- ----------------------------
-- Table structure for sale_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sale_role_menu`;
CREATE TABLE `sale_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '菜单id',
  `operates` varchar(255) NOT NULL DEFAULT '' COMMENT '操作功能id串，逗号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8 COMMENT='角色菜单资源权限中间表';

-- ----------------------------
-- Records of sale_role_menu
-- ----------------------------
INSERT INTO `sale_role_menu` VALUES ('48', '1', '2', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('49', '1', '10', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('50', '1', '13', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('51', '1', '9', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('52', '1', '4', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('53', '1', '11', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('54', '1', '5', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('55', '1', '12', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('56', '1', '1', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('57', '1', '6', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('58', '1', '7', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('59', '1', '18', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('60', '1', '8', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('61', '1', '16', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('62', '1', '19', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('63', '1', '20', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('64', '1', '21', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('65', '2', '2', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('66', '2', '10', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('67', '2', '13', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('68', '2', '9', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('69', '2', '4', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('70', '2', '11', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('71', '2', '5', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('72', '2', '12', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('73', '2', '1', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('74', '2', '6', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('75', '2', '7', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('76', '2', '18', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('77', '2', '8', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('78', '2', '16', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('79', '2', '19', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('80', '2', '20', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('81', '2', '21', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('115', '3', '2', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('116', '3', '10', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('117', '3', '9', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('118', '3', '4', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('119', '3', '11', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('120', '3', '20', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('121', '3', '5', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('122', '3', '12', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('123', '3', '6', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('124', '3', '7', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('125', '3', '18', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('126', '3', '8', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('127', '3', '16', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('128', '3', '19', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('129', '3', '13', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('130', '3', '22', '1,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('142', '5', '2', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('143', '5', '10', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('144', '5', '9', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('145', '5', '4', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('146', '5', '11', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('147', '5', '20', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('148', '5', '5', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('149', '5', '12', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('150', '5', '6', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('151', '5', '7', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('152', '5', '18', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('153', '5', '8', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('154', '5', '16', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('155', '5', '19', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('156', '5', '13', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('157', '5', '22', '1,2,3,5,6,7,8,9');
INSERT INTO `sale_role_menu` VALUES ('158', '4', '2', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('159', '4', '9', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('160', '4', '4', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('161', '4', '11', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('162', '4', '20', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('163', '4', '5', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('164', '4', '12', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('165', '4', '6', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('166', '4', '7', '1,3,5,6');
INSERT INTO `sale_role_menu` VALUES ('167', '4', '19', '1,2');
INSERT INTO `sale_role_menu` VALUES ('168', '4', '13', '1,3,5,6');

-- ----------------------------
-- Table structure for sale_user
-- ----------------------------
DROP TABLE IF EXISTS `sale_user`;
CREATE TABLE `sale_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) NOT NULL DEFAULT '' COMMENT '登陆账号',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `eng_name` varchar(32) NOT NULL DEFAULT '' COMMENT '英文名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态:1应用,2禁用,3离职',
  `mobile` bigint(20) NOT NULL DEFAULT '0' COMMENT '电话号码',
  `birthday` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '生日',
  `sex` char(1) NOT NULL DEFAULT '1' COMMENT '性别: 1男２女',
  `idcard` varchar(18) NOT NULL DEFAULT '' COMMENT '身份证',
  `dept_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '业务部门id',
  `address` varchar(50) NOT NULL DEFAULT '' COMMENT '地址',
  `job_title` varchar(32) NOT NULL DEFAULT '' COMMENT '岗位名称',
  `education` int(11) NOT NULL DEFAULT '0' COMMENT '教育程度',
  `exp1` varchar(25) NOT NULL DEFAULT '' COMMENT '备用',
  `exp2` varchar(50) NOT NULL DEFAULT '' COMMENT '备用',
  `exp3` varchar(100) NOT NULL DEFAULT '' COMMENT '备用',
  `creat_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '创建日期',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '更新时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人姓名（冗余）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=559 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sale_user
-- ----------------------------
INSERT INTO `sale_user` VALUES ('1', 'admin1', 'admin1', 'admin1', 'E10ADC3949BA59ABBE56E057F20F883E', 'admin', '1', '18511072319', '1970-01-01 08:00:00', '1', '', '0', '', '销售经理', '0', '', '', '', '2017-04-24 08:00:00', '1970-01-01 08:00:00', '1', 'admin1');
INSERT INTO `sale_user` VALUES ('445', 'love61v@163.com', '胡丹丹', '', 'E10ADC3949BA59ABBE56E057F20F883E', 'love61v@163.com', '1', '18511072313', '1970-01-01 08:00:00', '1', '', '4', '', 'admin12', '0', '', '', '', '2017-04-24 18:06:54', '2017-04-24 18:17:40', '1', 'admin1');
INSERT INTO `sale_user` VALUES ('555', 'dan@163.com', '胡丹', '', 'E10ADC3949BA59ABBE56E057F20F883E', 'dan@163.com', '1', '18511072313', '1970-01-01 08:00:00', '1', '', '1', '', 'admin12', '0', '', '', '', '2017-04-24 18:06:54', '2017-04-28 13:46:00', '1', 'admin1');
INSERT INTO `sale_user` VALUES ('556', 'test@163.com', 'test', '', 'E10ADC3949BA59ABBE56E057F20F883E', 'test@163.com', '1', '18511072319', '1970-01-01 08:00:00', '1', '', '14', '', '人事', '0', '', '', '', '2017-05-08 10:25:16', '2017-05-08 10:25:45', '1', 'admin1');
INSERT INTO `sale_user` VALUES ('557', 'test2@163.com', 'test2@163.com', '', 'E10ADC3949BA59ABBE56E057F20F883E', 'test2@163.com', '1', '18511098785', '1970-01-01 08:00:00', '1', '', '13', '', 'sdfds78', '0', '', '', '', '2017-05-08 10:51:19', '2017-05-09 10:46:11', '1', 'admin1');
INSERT INTO `sale_user` VALUES ('558', 'test3@163.com', 'test4@163.com', '', 'E10ADC3949BA59ABBE56E057F20F883E', 'test2@163.com', '1', '13521361327', '1970-01-01 08:00:00', '1', '', '14', '', 'dsffdsfd', '0', '', '', '', '2017-05-08 10:51:49', '2017-05-09 11:04:21', '1', 'admin1');

-- ----------------------------
-- Table structure for sale_user_data_auth
-- ----------------------------
DROP TABLE IF EXISTS `sale_user_data_auth`;
CREATE TABLE `sale_user_data_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `data_auth` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据权限(即组id)',
  PRIMARY KEY (`id`),
  KEY `idx_userid` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户数据权限表';

-- ----------------------------
-- Records of sale_user_data_auth
-- ----------------------------

-- ----------------------------
-- Table structure for sale_user_group
-- ----------------------------
DROP TABLE IF EXISTS `sale_user_group`;
CREATE TABLE `sale_user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '组id',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='用户分组中间表';

-- ----------------------------
-- Records of sale_user_group
-- ----------------------------
INSERT INTO `sale_user_group` VALUES ('2', '8', '3');
INSERT INTO `sale_user_group` VALUES ('5', '8', '3');
INSERT INTO `sale_user_group` VALUES ('7', '8', '35');
INSERT INTO `sale_user_group` VALUES ('8', '8', '555');
INSERT INTO `sale_user_group` VALUES ('11', '5', '15');
INSERT INTO `sale_user_group` VALUES ('12', '5', '25');
INSERT INTO `sale_user_group` VALUES ('16', '4', '45');
INSERT INTO `sale_user_group` VALUES ('17', '1', '1');
INSERT INTO `sale_user_group` VALUES ('19', '4', '445');
INSERT INTO `sale_user_group` VALUES ('20', '1', '55');
INSERT INTO `sale_user_group` VALUES ('21', '1', '335');
INSERT INTO `sale_user_group` VALUES ('22', '1', '85');
INSERT INTO `sale_user_group` VALUES ('23', '1', '95');
INSERT INTO `sale_user_group` VALUES ('24', '1', '65');
INSERT INTO `sale_user_group` VALUES ('25', '3', '115');
INSERT INTO `sale_user_group` VALUES ('26', '2', '115');
INSERT INTO `sale_user_group` VALUES ('27', '2', '558');

-- ----------------------------
-- Table structure for sale_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `sale_user_menu`;
CREATE TABLE `sale_user_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `menu_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '菜单id',
  `operates` varchar(50) DEFAULT '' COMMENT '操作功能id串逗号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8 COMMENT='用户菜单资源权限中间表';

-- ----------------------------
-- Records of sale_user_menu
-- ----------------------------
INSERT INTO `sale_user_menu` VALUES ('18', '65', '4', '1,6');
INSERT INTO `sale_user_menu` VALUES ('127', '55', '2', '');
INSERT INTO `sale_user_menu` VALUES ('128', '55', '9', '1,3,5,6');
INSERT INTO `sale_user_menu` VALUES ('129', '55', '11', '1,3,5,6');
INSERT INTO `sale_user_menu` VALUES ('130', '55', '20', '1,3,5,6');
INSERT INTO `sale_user_menu` VALUES ('131', '55', '5', '1,3,5,6');
INSERT INTO `sale_user_menu` VALUES ('132', '55', '12', '1,3,5,6');
INSERT INTO `sale_user_menu` VALUES ('133', '55', '6', '1,3,5,6');
INSERT INTO `sale_user_menu` VALUES ('134', '55', '7', '1,3,5,6');
INSERT INTO `sale_user_menu` VALUES ('135', '55', '19', '1');
INSERT INTO `sale_user_menu` VALUES ('136', '55', '13', '');

-- ----------------------------
-- Table structure for sale_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sale_user_role`;
CREATE TABLE `sale_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sale_user_role
-- ----------------------------
INSERT INTO `sale_user_role` VALUES ('1', '2', '5');
INSERT INTO `sale_user_role` VALUES ('2', '3', '5');
INSERT INTO `sale_user_role` VALUES ('3', '4', '5');
INSERT INTO `sale_user_role` VALUES ('4', '15', '4');
INSERT INTO `sale_user_role` VALUES ('5', '3', '5');
INSERT INTO `sale_user_role` VALUES ('6', '15', '4');
INSERT INTO `sale_user_role` VALUES ('7', '35', '3');
INSERT INTO `sale_user_role` VALUES ('8', '555', '4');
INSERT INTO `sale_user_role` VALUES ('9', '55', '4');
INSERT INTO `sale_user_role` VALUES ('10', '65', '5');
INSERT INTO `sale_user_role` VALUES ('11', '15', '4');
INSERT INTO `sale_user_role` VALUES ('12', '25', '5');
INSERT INTO `sale_user_role` VALUES ('13', '4', '5');
INSERT INTO `sale_user_role` VALUES ('14', '4', '5');
INSERT INTO `sale_user_role` VALUES ('15', '4', '5');
INSERT INTO `sale_user_role` VALUES ('16', '45', '5');
INSERT INTO `sale_user_role` VALUES ('18', '1', '1');
INSERT INTO `sale_user_role` VALUES ('19', '445', '4');
INSERT INTO `sale_user_role` VALUES ('20', '445', '4');
INSERT INTO `sale_user_role` VALUES ('21', '55', '4');
INSERT INTO `sale_user_role` VALUES ('22', '335', '5');
INSERT INTO `sale_user_role` VALUES ('23', '85', '5');
INSERT INTO `sale_user_role` VALUES ('24', '95', '4');
INSERT INTO `sale_user_role` VALUES ('25', '65', '5');
INSERT INTO `sale_user_role` VALUES ('26', '115', '4');
INSERT INTO `sale_user_role` VALUES ('27', '115', '4');
INSERT INTO `sale_user_role` VALUES ('28', '558', '5');

-- ----------------------------
-- Function structure for queryChildrenGroupInfo
-- ----------------------------
DROP FUNCTION IF EXISTS `queryChildrenGroupInfo`;
DELIMITER ;;
CREATE FUNCTION `queryChildrenGroupInfo`(groupId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp = '$';
SET sTempChd = cast(groupId as char);

WHILE sTempChd is not NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT group_concat(id) INTO sTempChd FROM sale_group where FIND_IN_SET(p_id,sTempChd)>0;
END WHILE;
return sTemp;
END
;;
DELIMITER ;
