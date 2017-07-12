/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50625
Source Host           : 127.0.0.1:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-07-12 22:28:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `book_id` bigint(20) NOT NULL COMMENT '图书ID',
  `student_id` bigint(20) NOT NULL COMMENT '学号',
  `appoint_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '预约时间',
  PRIMARY KEY (`book_id`,`student_id`),
  KEY `idx_appoint_time` (`appoint_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约图书表';

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('1000', '1590752158', '2017-05-21 19:33:12');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图书ID',
  `name` varchar(100) NOT NULL COMMENT '图书名称',
  `number` int(11) NOT NULL COMMENT '馆藏数量',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='图书表';

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('2', '数据结构', '10');
INSERT INTO `book` VALUES ('3', '设计模式', '10');
INSERT INTO `book` VALUES ('4', '编译原理', '10');
INSERT INTO `book` VALUES ('5', 'Java Web 典型模块大全', '20');
INSERT INTO `book` VALUES ('6', '深入浅出MySQL', '25');
INSERT INTO `book` VALUES ('7', 'Java 多线程编程核心技术', '5');
INSERT INTO `book` VALUES ('8', '数据库系统概论', '8');
INSERT INTO `book` VALUES ('9', '程序员的自我修养', '3');
INSERT INTO `book` VALUES ('10', '软件测试', '4');
INSERT INTO `book` VALUES ('11', '深入浅出MyBatis', '6');
INSERT INTO `book` VALUES ('12', '编程之美', '13');
INSERT INTO `book` VALUES ('13', 'Spring入门经典', '12');
INSERT INTO `book` VALUES ('14', 'Spring MVC 学习指南', '3');
INSERT INTO `book` VALUES ('15', 'Spring 技术内幕', '3');
INSERT INTO `book` VALUES ('16', '深入理解 Java 虚拟机', '1');
INSERT INTO `book` VALUES ('17', 'HTML5 权威指南', '1');
INSERT INTO `book` VALUES ('18', 'JavaScript 高级程序设计', '3');
INSERT INTO `book` VALUES ('19', 'Java网络编程', '1');
INSERT INTO `book` VALUES ('20', '代码整洁之道', '1');
INSERT INTO `book` VALUES ('21', '嵌入式实时操作系统 μC/OS原理与实践', '1');
INSERT INTO `book` VALUES ('22', '重构 改善既有代码的设计', '3');
INSERT INTO `book` VALUES ('23', '软件建模与设计', '2');
INSERT INTO `book` VALUES ('24', '代码大全', '6');
INSERT INTO `book` VALUES ('25', 'TCP/IP详解 卷2：实现', '1');
INSERT INTO `book` VALUES ('26', 'TCP/IP详解 卷1：协议', '1');
INSERT INTO `book` VALUES ('27', 'C程序设计语言', '1');
INSERT INTO `book` VALUES ('28', '设计模式', '1');
INSERT INTO `book` VALUES ('29', '算法导论', '2');
INSERT INTO `book` VALUES ('30', 'C陷阱与缺陷', '3');
INSERT INTO `book` VALUES ('31', '计算机组成原理', '2');
INSERT INTO `book` VALUES ('32', 'C专家编程', '1');
INSERT INTO `book` VALUES ('33', 'Spring实战', '1');
INSERT INTO `book` VALUES ('34', 'C++ Primer Plus', '1');
INSERT INTO `book` VALUES ('35', '数据库原理与应用', '2');
INSERT INTO `book` VALUES ('36', '数据库技术与应用', '1');
INSERT INTO `book` VALUES ('37', '计算机网络', '2');
INSERT INTO `book` VALUES ('38', '无线传感器网络实用教程', '1');
INSERT INTO `book` VALUES ('39', 'Java Web开发实战', '1');
INSERT INTO `book` VALUES ('40', 'Java Web整合开发实战', '1');
INSERT INTO `book` VALUES ('41', 'HTML5从入门到精通', '1');
INSERT INTO `book` VALUES ('42', '程序员教程', '4');
INSERT INTO `book` VALUES ('43', '程序员考试同步辅导', '1');
INSERT INTO `book` VALUES ('44', '疯狂Android讲义', '1');
INSERT INTO `book` VALUES ('45', 'Java 从入门到精通', '51');
INSERT INTO `book` VALUES ('46', '45454', '5');

-- ----------------------------
-- Table structure for book_new
-- ----------------------------
DROP TABLE IF EXISTS `book_new`;
CREATE TABLE `book_new` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图书ID',
  `name` varchar(100) NOT NULL COMMENT '图书名称',
  `number` int(11) NOT NULL COMMENT '馆藏数量',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='图书表';

-- ----------------------------
-- Records of book_new
-- ----------------------------
INSERT INTO `book_new` VALUES ('1', 'Java程序设计', '10');
INSERT INTO `book_new` VALUES ('2', '数据结构', '10');
INSERT INTO `book_new` VALUES ('3', '设计模式', '10');
INSERT INTO `book_new` VALUES ('4', '编译原理', '10');
INSERT INTO `book_new` VALUES ('5', 'Java Web 典型模块大全', '20');
INSERT INTO `book_new` VALUES ('6', '深入浅出MySQL', '25');
INSERT INTO `book_new` VALUES ('7', 'Java 多线程编程核心技术', '5');
INSERT INTO `book_new` VALUES ('8', '数据库系统概论', '8');
INSERT INTO `book_new` VALUES ('9', '程序员的自我修养', '3');
INSERT INTO `book_new` VALUES ('10', '软件测试', '4');
INSERT INTO `book_new` VALUES ('11', '深入浅出MyBatis', '6');
INSERT INTO `book_new` VALUES ('12', '编程之美', '13');
INSERT INTO `book_new` VALUES ('13', 'Spring入门经典', '12');
INSERT INTO `book_new` VALUES ('14', 'Spring MVC 学习指南', '3');
INSERT INTO `book_new` VALUES ('15', 'Spring 技术内幕', '3');
INSERT INTO `book_new` VALUES ('16', '深入理解 Java 虚拟机', '1');
INSERT INTO `book_new` VALUES ('17', 'HTML5 权威指南', '1');
INSERT INTO `book_new` VALUES ('18', 'JavaScript 高级程序设计', '3');
INSERT INTO `book_new` VALUES ('19', 'Java网络编程', '1');
INSERT INTO `book_new` VALUES ('20', '代码整洁之道', '1');
INSERT INTO `book_new` VALUES ('21', '嵌入式实时操作系统 μC/OS原理与实践', '1');
INSERT INTO `book_new` VALUES ('22', '重构 改善既有代码的设计', '3');
INSERT INTO `book_new` VALUES ('23', '软件建模与设计', '2');
INSERT INTO `book_new` VALUES ('24', '代码大全', '6');
INSERT INTO `book_new` VALUES ('25', 'TCP/IP详解 卷2：实现', '1');
INSERT INTO `book_new` VALUES ('26', 'TCP/IP详解 卷1：协议', '1');
INSERT INTO `book_new` VALUES ('27', 'C程序设计语言', '1');
INSERT INTO `book_new` VALUES ('28', '设计模式', '1');
INSERT INTO `book_new` VALUES ('29', '算法导论', '2');
INSERT INTO `book_new` VALUES ('30', 'C陷阱与缺陷', '3');
INSERT INTO `book_new` VALUES ('31', '计算机组成原理', '2');
INSERT INTO `book_new` VALUES ('32', 'C专家编程', '1');
INSERT INTO `book_new` VALUES ('33', 'Spring实战', '1');
INSERT INTO `book_new` VALUES ('34', 'C++ Primer Plus', '1');
INSERT INTO `book_new` VALUES ('35', '数据库原理与应用', '2');
INSERT INTO `book_new` VALUES ('36', '数据库技术与应用', '1');
INSERT INTO `book_new` VALUES ('37', '计算机网络', '2');
INSERT INTO `book_new` VALUES ('38', '无线传感器网络实用教程', '1');
INSERT INTO `book_new` VALUES ('39', 'Java Web开发实战', '1');
INSERT INTO `book_new` VALUES ('40', 'Java Web整合开发实战', '1');
INSERT INTO `book_new` VALUES ('41', 'HTML5从入门到精通', '1');
INSERT INTO `book_new` VALUES ('42', '程序员教程', '4');
INSERT INTO `book_new` VALUES ('43', '程序员考试同步辅导', '1');
INSERT INTO `book_new` VALUES ('44', '疯狂Android讲义', '1');
INSERT INTO `book_new` VALUES ('45', 'Java 从入门到精通', '12321');

-- ----------------------------
-- Table structure for sys_depart_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_depart_role`;
CREATE TABLE `sys_depart_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `departId` varchar(200) NOT NULL COMMENT '部门id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_depart_role
-- ----------------------------
INSERT INTO `sys_depart_role` VALUES ('80', '3', '1');
INSERT INTO `sys_depart_role` VALUES ('81', '1', '4');
INSERT INTO `sys_depart_role` VALUES ('82', '4', '2');
INSERT INTO `sys_depart_role` VALUES ('87', '5', '1');
INSERT INTO `sys_depart_role` VALUES ('88', '5', '3');
INSERT INTO `sys_depart_role` VALUES ('89', '5', '4');
INSERT INTO `sys_depart_role` VALUES ('90', '5', '2');

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `clientip` varchar(50) DEFAULT NULL,
  `logintime` varchar(20) NOT NULL,
  `type` int(10) DEFAULT '0' COMMENT '0:PC端，1:手机端',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=263 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log_login
-- ----------------------------
INSERT INTO `sys_log_login` VALUES ('246', 'admin', '110.249.247.32', '2017-06-26 15:36:13', '0');
INSERT INTO `sys_log_login` VALUES ('247', 'admin', '110.249.247.35', '2017-06-28 13:08:58', '0');
INSERT INTO `sys_log_login` VALUES ('248', 'admin', '110.249.241.22', '2017-07-01 23:45:09', '0');
INSERT INTO `sys_log_login` VALUES ('249', 'admin', '110.249.243.176', '2017-07-06 10:31:57', '0');
INSERT INTO `sys_log_login` VALUES ('250', 'admin', '110.249.240.118', '2017-07-06 11:55:16', '0');
INSERT INTO `sys_log_login` VALUES ('251', 'admin', '110.249.240.118', '2017-07-06 12:06:24', '0');
INSERT INTO `sys_log_login` VALUES ('252', 'admin', '110.249.240.118', '2017-07-06 12:44:37', '0');
INSERT INTO `sys_log_login` VALUES ('253', 'admin', '110.249.240.118', '2017-07-06 13:55:10', '0');
INSERT INTO `sys_log_login` VALUES ('254', 'admin', '110.249.240.118', '2017-07-06 13:55:15', '0');
INSERT INTO `sys_log_login` VALUES ('255', 'admin', '110.249.240.118', '2017-07-06 13:57:26', '0');
INSERT INTO `sys_log_login` VALUES ('256', 'admin', '110.249.240.118', '2017-07-06 16:25:24', '0');
INSERT INTO `sys_log_login` VALUES ('257', 'admin', '110.249.240.112', '2017-07-07 11:09:02', '0');
INSERT INTO `sys_log_login` VALUES ('258', 'admin', '110.249.240.112', '2017-07-07 11:13:53', '0');
INSERT INTO `sys_log_login` VALUES ('259', 'admin', '110.249.240.112', '2017-07-07 11:49:39', '0');
INSERT INTO `sys_log_login` VALUES ('260', 'admin', '121.18.134.254', '2017-07-07 22:23:44', '0');
INSERT INTO `sys_log_login` VALUES ('261', 'admin', '121.18.134.254', '2017-07-07 22:23:59', '0');
INSERT INTO `sys_log_login` VALUES ('262', 'admin', '121.18.134.254', '2017-07-07 22:24:12', '0');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `url` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `open_mode` varchar(32) DEFAULT NULL COMMENT '打开方式 ajax,iframe',
  `description` varchar(255) DEFAULT NULL COMMENT '资源介绍',
  `icon` varchar(32) DEFAULT NULL COMMENT '资源图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级资源id',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `resource_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '资源类别',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=424 DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '权限管理', '', null, '系统管理', 'fi-folder', null, '0', '0', '0', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('11', '资源管理', '/resource/manager', 'ajax', '资源管理', 'fi-database', '1', '1', '0', '0', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('12', '角色管理', '/role/manager', 'ajax', '角色管理', 'fi-torso-business', '1', '2', '0', '0', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('13', '用户管理', '/user/manager', 'ajax', '用户管理', 'fi-torsos-all', '1', '3', '0', '0', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('14', '部门管理', '/organization/manager', 'ajax', '部门管理', 'fi-results-demographics', '1', '4', '0', '0', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('111', '列表', '/resource/treeGrid', 'ajax', '资源列表', 'fi-list', '11', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('112', '添加', '/resource/add', 'ajax', '资源添加', 'fi-page-add', '11', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('113', '编辑', '/resource/edit', 'ajax', '资源编辑', 'fi-page-edit', '11', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('114', '删除', '/resource/delete', 'ajax', '资源删除', 'fi-page-delete', '11', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('121', '列表', '/role/dataGrid', 'ajax', '角色列表', 'fi-list', '12', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('122', '添加', '/role/add', 'ajax', '角色添加', 'fi-page-add', '12', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('123', '编辑', '/role/edit', 'ajax', '角色编辑', 'fi-page-edit', '12', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('124', '删除', '/role/delete', 'ajax', '角色删除', 'fi-page-delete', '12', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('125', '授权', '/role/grant', 'ajax', '角色授权', 'fi-check', '12', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('131', '列表', '/user/dataGrid', 'ajax', '用户列表', 'fi-list', '13', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('132', '添加', '/user/add', 'ajax', '用户添加', 'fi-page-add', '13', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('133', '编辑', '/user/edit', 'ajax', '用户编辑', 'fi-page-edit', '13', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('134', '删除', '/user/delete', 'ajax', '用户删除', 'fi-page-delete', '13', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('141', '列表', '/organization/treeGrid', 'ajax', '用户列表', 'fi-list', '14', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('142', '添加', '/organization/add', 'ajax', '部门添加', 'fi-page-add', '14', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('143', '编辑', '/organization/edit', 'ajax', '部门编辑', 'fi-page-edit', '14', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('144', '删除', '/organization/delete', 'ajax', '部门删除', 'fi-page-delete', '14', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('221', '日志监控', '/sysLogInit', null, null, 'fi-folder', null, '2', '0', '0', '2015-12-01 11:44:20');
INSERT INTO `sys_resource` VALUES ('226', '修改密码', '/user/editPwdPage', 'ajax', null, 'fi-unlock', null, '3', '0', '1', '2015-12-07 20:23:06');
INSERT INTO `sys_resource` VALUES ('227', '登录日志', '/sysLog/log', 'ajax', null, 'fi-info', '221', '0', '0', '0', '2016-09-30 22:10:53');
INSERT INTO `sys_resource` VALUES ('228', '店面管理', '/shopinfo/Menu', null, null, 'fi-folder', null, '0', '0', '0', '2017-03-14 13:46:55');
INSERT INTO `sys_resource` VALUES ('229', '列表', '/shopinfo_maintain/shopInit', null, null, 'fi-list', '228', '1', '0', '0', '2017-03-14 13:49:41');
INSERT INTO `sys_resource` VALUES ('230', '添加', '/shopinfo_maintain/addShop', 'ajax', null, 'fi-page-add', '229', '0', '0', '1', '2017-03-14 13:52:45');
INSERT INTO `sys_resource` VALUES ('231', '编辑', '/shopinfo_maintain/edit', 'ajax', null, 'fi-page-edit', '229', '0', '0', '1', '2017-03-14 13:55:18');
INSERT INTO `sys_resource` VALUES ('232', '删除', '/shopinfo_maintain/updateStatus', 'ajax', null, 'fi-page-delete', '229', '0', '0', '1', '2017-03-14 13:56:48');
INSERT INTO `sys_resource` VALUES ('233', '复尺店面列表', '/shopinfo_maintain/fuchishopInit', null, null, 'fi-list', '228', '1', '0', '0', '2017-03-19 09:26:56');
INSERT INTO `sys_resource` VALUES ('234', '编辑', '/shopinfo_maintain/editFuchi', 'ajax', null, 'fi-page-edit', '233', '2', '0', '1', '2017-03-19 09:29:57');
INSERT INTO `sys_resource` VALUES ('235', '添加', '/shopinfo_maintain/addFuChiShop', 'ajax', null, 'fi-page-add', '233', '2', '0', '1', '2017-03-19 09:31:38');
INSERT INTO `sys_resource` VALUES ('236', '删除', '/shopinfo_maintain/updateFuChiStatus', 'ajax', null, 'fi-page-delete', '233', '2', '0', '1', '2017-03-19 09:32:27');
INSERT INTO `sys_resource` VALUES ('250', '手机照片上传', '/upload/Menu', '', '', 'fi-folder', null, '0', '0', '0', '2017-03-14 13:46:55');
INSERT INTO `sys_resource` VALUES ('251', '复尺', '/upload/upload_fuchi', 'ajax', null, 'fi-page-add', '250', '0', '0', '1', '2017-03-19 09:56:30');
INSERT INTO `sys_resource` VALUES ('252', '收货', '/upload/upload_shouhuo', 'ajax', null, 'fi-page-add', '250', '0', '0', '1', '2017-03-19 09:57:20');
INSERT INTO `sys_resource` VALUES ('253', '完工', '/upload/upload_wangong', 'ajax', null, 'fi-page-add', '250', '0', '0', '1', '2017-03-19 09:59:26');
INSERT INTO `sys_resource` VALUES ('254', '整改', '/upload/upload_zhenggai', 'ajax', null, 'fi-page-add', '250', '0', '0', '1', '2017-03-19 10:01:15');
INSERT INTO `sys_resource` VALUES ('255', '审核', '/AuditView/fuchi.jsp', null, null, 'fi-list', '300', '0', '0', '0', '2017-03-19 10:06:23');
INSERT INTO `sys_resource` VALUES ('256', '报表', '/AuditView/reportFuchi.jsp', null, null, 'fi-list', '300', '0', '0', '0', '2017-03-19 10:07:20');
INSERT INTO `sys_resource` VALUES ('300', '店面复尺管理', '/Fuchi/Menu', '', '', 'fi-folder', null, '0', '0', '0', '2017-03-14 13:46:55');
INSERT INTO `sys_resource` VALUES ('320', '店面收货管理', '/ShouHuo/Menu', '', '', 'fi-folder', null, '0', '0', '0', '2017-03-14 13:46:55');
INSERT INTO `sys_resource` VALUES ('340', '店面完工管理', '/WangGong/Menu', '', '', 'fi-folder', null, '0', '0', '0', '2017-03-14 13:46:55');
INSERT INTO `sys_resource` VALUES ('360', '店面整改管理', '/ZhengGai/Menu', '', '', 'fi-folder', null, '0', '0', '0', '2017-03-14 13:46:55');
INSERT INTO `sys_resource` VALUES ('381', '报表', '/AuditView/reportShouhuo.jsp', null, null, 'fi-list', '320', '0', '0', '0', '2017-03-19 10:10:49');
INSERT INTO `sys_resource` VALUES ('382', '报表', '/AuditView/reportWangong.jsp', null, null, 'fi-list', '340', '0', '0', '0', '2017-03-19 10:10:51');
INSERT INTO `sys_resource` VALUES ('383', '报表', '/AuditView/reportZhenggai.jsp', null, null, 'fi-list', '360', '0', '0', '0', '2017-03-19 10:10:54');
INSERT INTO `sys_resource` VALUES ('384', '审核', '/AuditView/shouhuo.jsp', null, null, 'fi-list', '320', '0', '0', '0', '2017-03-19 10:11:00');
INSERT INTO `sys_resource` VALUES ('385', '审核', '/AuditView/wangong.jsp', null, null, 'fi-list', '340', '0', '0', '0', '2017-03-19 10:11:02');
INSERT INTO `sys_resource` VALUES ('386', '审核', '/AuditView/zhenggai.jsp', null, null, 'fi-list', '360', '0', '0', '0', '2017-03-19 10:11:04');
INSERT INTO `sys_resource` VALUES ('387', '列表', '/audit/fuchi_search', 'ajax', null, 'fi-list', '255', '0', '0', '1', '2017-03-19 10:22:44');
INSERT INTO `sys_resource` VALUES ('388', '列表', '/audit/shouhuo_search', 'ajax', null, 'fi-list', '384', '0', '0', '1', '2017-03-19 10:23:10');
INSERT INTO `sys_resource` VALUES ('389', '列表', '/audit/wangong_search', 'ajax', null, 'fi-list', '385', '0', '0', '1', '2017-03-19 10:23:24');
INSERT INTO `sys_resource` VALUES ('390', '列表', '/audit/zhenggai_search', 'ajax', null, 'fi-list', '386', '0', '0', '1', '2017-03-19 10:23:40');
INSERT INTO `sys_resource` VALUES ('391', '列表', '/audit/fuchi_report', null, null, 'fi-list', '256', '0', '0', '0', '2017-03-19 10:25:02');
INSERT INTO `sys_resource` VALUES ('392', '列表', '/audit/shouhuo_report', null, null, 'fi-list', '381', '0', '0', '0', '2017-03-19 10:25:12');
INSERT INTO `sys_resource` VALUES ('393', '列表', '/audit/wangong_report', null, null, 'fi-list', '382', '0', '0', '0', '2017-03-19 10:25:20');
INSERT INTO `sys_resource` VALUES ('394', '列表', '/audit/zhenggai_report', null, null, 'fi-list', '383', '0', '0', '0', '2017-03-19 10:25:29');
INSERT INTO `sys_resource` VALUES ('395', '店面导入', '/shopinfo_maintain/importShop', 'ajax', null, 'fi-page-add', '229', '2', '0', '1', '2017-04-14 16:27:40');
INSERT INTO `sys_resource` VALUES ('396', '复尺店面导入', '/shopinfo_maintain/importShopfuChi', 'ajax', null, 'fi-page-add', '233', '3', '0', '1', '2017-04-14 16:29:18');
INSERT INTO `sys_resource` VALUES ('397', '复尺审核', '/audit/verify_pig_fuchi', 'ajax', null, 'fi-page-add', '255', '2', '0', '1', '2017-04-14 16:32:50');
INSERT INTO `sys_resource` VALUES ('398', '审核查看', '/audit/turn_audit_view_fuchi', 'ajax', null, 'fi-page-edit', '391', '3', '0', '1', '2017-04-14 16:34:53');
INSERT INTO `sys_resource` VALUES ('399', '审核删除', '/audit/fuchi_delete', 'ajax', null, 'fi-page-delete', '391', '3', '0', '1', '2017-04-14 16:35:43');
INSERT INTO `sys_resource` VALUES ('400', '审核', 'audit/verify_pig_shouhuo', 'ajax', null, '', '384', '0', '0', '1', '2017-04-14 16:38:29');
INSERT INTO `sys_resource` VALUES ('401', '删除', '/audit/shouhuo_delete', 'ajax', null, 'fi-page-delete', '392', '0', '0', '1', '2017-04-14 16:41:52');
INSERT INTO `sys_resource` VALUES ('402', '查看', '/audit/turn_audit_view_shouhuo', 'ajax', null, 'fi-page-edit', '392', '3', '0', '1', '2017-04-14 16:44:12');
INSERT INTO `sys_resource` VALUES ('403', '查看', '/audit/turn_audit_view_wangong', 'ajax', null, 'fi-page-edit', '393', '0', '0', '1', '2017-04-14 16:45:18');
INSERT INTO `sys_resource` VALUES ('404', '删除', '/audit/wangong_delete', 'ajax', null, 'fi-page-delete', '393', '0', '0', '1', '2017-04-14 16:45:51');
INSERT INTO `sys_resource` VALUES ('405', '查看', '/audit/turn_audit_view_zhenggai', 'ajax', null, 'fi-page-edit', '394', '2', '0', '1', '2017-04-14 16:46:49');
INSERT INTO `sys_resource` VALUES ('406', '删除', '/audit/zhenggai_delete', 'ajax', null, 'fi-page-delete', '394', '2', '0', '1', '2017-04-14 16:47:24');
INSERT INTO `sys_resource` VALUES ('407', '审核', '/audit/verify_pig_wangong', 'ajax', null, 'fi-page-edit', '385', '2', '0', '1', '2017-04-14 16:49:48');
INSERT INTO `sys_resource` VALUES ('408', '审核', '/audit/verify_pig_zhenggai', 'ajax', null, 'fi-page-edit', '386', '2', '0', '1', '2017-04-14 16:50:49');
INSERT INTO `sys_resource` VALUES ('409', '品牌形象', '/xingxiang/manager', null, null, 'fi-folder', '1', '5', '0', '0', '2017-04-14 16:55:47');
INSERT INTO `sys_resource` VALUES ('410', '添加', '/xingxiang/addPage', 'ajax', null, 'fi-page-add', '409', '0', '0', '1', '2017-04-14 16:57:52');
INSERT INTO `sys_resource` VALUES ('411', '删除', '/xingxiang/delete', 'ajax', null, 'fi-page-delete', '409', '1', '0', '1', '2017-04-14 16:58:24');
INSERT INTO `sys_resource` VALUES ('412', '编辑', '/xingxiang/editPage', 'ajax', null, 'fi-page-edit', '409', '3', '0', '1', '2017-04-14 16:58:57');
INSERT INTO `sys_resource` VALUES ('413', '客户公司', '/kehu/manager', null, null, 'fi-folder', '1', '6', '0', '0', '2017-04-14 16:59:50');
INSERT INTO `sys_resource` VALUES ('414', '添加', '/kehu/addPage', 'ajax', null, 'fi-page-add', '413', '0', '0', '1', '2017-04-14 17:00:56');
INSERT INTO `sys_resource` VALUES ('415', '编辑', '/kehu/editPage', 'ajax', null, 'fi-page-edit', '413', '2', '0', '1', '2017-04-14 17:01:36');
INSERT INTO `sys_resource` VALUES ('416', '删除', '/kehu/delete', 'ajax', null, 'fi-page-delete', '413', '2', '0', '1', '2017-04-14 17:02:08');
INSERT INTO `sys_resource` VALUES ('417', '桌面照片上传', '', null, null, 'fi-folder', null, '0', '0', '0', '2017-04-14 17:03:44');
INSERT INTO `sys_resource` VALUES ('418', '复尺', '/App/picsys/up_fuchi.jsp', null, null, 'fi-list', '417', '0', '0', '0', '2017-04-14 17:04:46');
INSERT INTO `sys_resource` VALUES ('419', '收货', '/App/picsys/up_shouhuo.jsp', null, null, 'fi-list', '417', '1', '0', '0', '2017-04-14 17:05:22');
INSERT INTO `sys_resource` VALUES ('420', '完工', '/App/picsys/up_wangong.jsp', null, null, 'fi-list', '417', '0', '0', '0', '2017-04-14 17:05:59');
INSERT INTO `sys_resource` VALUES ('421', '整改', '/App/picsys/up_zhenggai.jsp', null, null, 'fi-list', '417', '2', '0', '0', '2017-04-14 17:06:29');
INSERT INTO `sys_resource` VALUES ('422', '操作日志', '/sysLog/log_login', null, null, 'fi-info', '221', '0', '0', '0', '2017-04-15 21:46:12');
INSERT INTO `sys_resource` VALUES ('423', 'fuchiPage', '/shopinfo_maintain/getShopName', 'ajax', null, 'fi-folder', '418', '0', '0', '1', '2017-05-10 10:42:55');

-- ----------------------------
-- Table structure for sys_res_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_res_role`;
CREATE TABLE `sys_res_role` (
  `resId` varchar(200) NOT NULL,
  `roleId` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_res_role
-- ----------------------------
INSERT INTO `sys_res_role` VALUES ('1', '1', '12');
INSERT INTO `sys_res_role` VALUES ('11', '1', '13');
INSERT INTO `sys_res_role` VALUES ('111', '1', '14');
INSERT INTO `sys_res_role` VALUES ('112', '1', '15');
INSERT INTO `sys_res_role` VALUES ('113', '1', '16');
INSERT INTO `sys_res_role` VALUES ('114', '1', '17');
INSERT INTO `sys_res_role` VALUES ('12', '1', '18');
INSERT INTO `sys_res_role` VALUES ('121', '1', '19');
INSERT INTO `sys_res_role` VALUES ('122', '1', '20');
INSERT INTO `sys_res_role` VALUES ('123', '1', '21');
INSERT INTO `sys_res_role` VALUES ('124', '1', '22');
INSERT INTO `sys_res_role` VALUES ('125', '1', '23');
INSERT INTO `sys_res_role` VALUES ('13', '1', '24');
INSERT INTO `sys_res_role` VALUES ('131', '1', '25');
INSERT INTO `sys_res_role` VALUES ('132', '1', '26');
INSERT INTO `sys_res_role` VALUES ('133', '1', '27');
INSERT INTO `sys_res_role` VALUES ('134', '1', '28');
INSERT INTO `sys_res_role` VALUES ('14', '1', '29');
INSERT INTO `sys_res_role` VALUES ('141', '1', '30');
INSERT INTO `sys_res_role` VALUES ('142', '1', '31');
INSERT INTO `sys_res_role` VALUES ('143', '1', '32');
INSERT INTO `sys_res_role` VALUES ('144', '1', '33');
INSERT INTO `sys_res_role` VALUES ('300', '1', '34');
INSERT INTO `sys_res_role` VALUES ('256', '1', '35');
INSERT INTO `sys_res_role` VALUES ('391', '1', '36');
INSERT INTO `sys_res_role` VALUES ('255', '1', '37');
INSERT INTO `sys_res_role` VALUES ('387', '1', '38');
INSERT INTO `sys_res_role` VALUES ('320', '1', '39');
INSERT INTO `sys_res_role` VALUES ('381', '1', '40');
INSERT INTO `sys_res_role` VALUES ('392', '1', '41');
INSERT INTO `sys_res_role` VALUES ('384', '1', '42');
INSERT INTO `sys_res_role` VALUES ('388', '1', '43');
INSERT INTO `sys_res_role` VALUES ('340', '1', '44');
INSERT INTO `sys_res_role` VALUES ('382', '1', '45');
INSERT INTO `sys_res_role` VALUES ('393', '1', '46');
INSERT INTO `sys_res_role` VALUES ('385', '1', '47');
INSERT INTO `sys_res_role` VALUES ('389', '1', '48');
INSERT INTO `sys_res_role` VALUES ('360', '1', '49');
INSERT INTO `sys_res_role` VALUES ('383', '1', '50');
INSERT INTO `sys_res_role` VALUES ('394', '1', '51');
INSERT INTO `sys_res_role` VALUES ('386', '1', '52');
INSERT INTO `sys_res_role` VALUES ('390', '1', '53');
INSERT INTO `sys_res_role` VALUES ('228', '1', '54');
INSERT INTO `sys_res_role` VALUES ('229', '1', '55');
INSERT INTO `sys_res_role` VALUES ('230', '1', '56');
INSERT INTO `sys_res_role` VALUES ('231', '1', '57');
INSERT INTO `sys_res_role` VALUES ('232', '1', '58');
INSERT INTO `sys_res_role` VALUES ('233', '1', '59');
INSERT INTO `sys_res_role` VALUES ('234', '1', '60');
INSERT INTO `sys_res_role` VALUES ('235', '1', '61');
INSERT INTO `sys_res_role` VALUES ('236', '1', '62');
INSERT INTO `sys_res_role` VALUES ('250', '1', '63');
INSERT INTO `sys_res_role` VALUES ('251', '1', '64');
INSERT INTO `sys_res_role` VALUES ('252', '1', '65');
INSERT INTO `sys_res_role` VALUES ('253', '1', '66');
INSERT INTO `sys_res_role` VALUES ('254', '1', '67');
INSERT INTO `sys_res_role` VALUES ('221', '1', '68');
INSERT INTO `sys_res_role` VALUES ('227', '1', '69');
INSERT INTO `sys_res_role` VALUES ('226', '1', '70');
INSERT INTO `sys_res_role` VALUES ('300', '3', '71');
INSERT INTO `sys_res_role` VALUES ('256', '3', '72');
INSERT INTO `sys_res_role` VALUES ('391', '3', '73');
INSERT INTO `sys_res_role` VALUES ('398', '3', '74');
INSERT INTO `sys_res_role` VALUES ('423', '1', '75');
INSERT INTO `sys_res_role` VALUES ('11', '6', '76');
INSERT INTO `sys_res_role` VALUES ('111', '6', '77');
INSERT INTO `sys_res_role` VALUES ('112', '6', '78');
INSERT INTO `sys_res_role` VALUES ('113', '6', '79');
INSERT INTO `sys_res_role` VALUES ('114', '6', '80');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `state` varchar(3) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `roleKey` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', null, '管理员', null, '', null, null);
INSERT INTO `sys_role` VALUES ('3', null, 'role2', null, '', null, null);
INSERT INTO `sys_role` VALUES ('4', null, 'role1', null, '', null, null);
INSERT INTO `sys_role` VALUES ('5', null, 'role3', null, '', null, null);
INSERT INTO `sys_role` VALUES ('6', null, 'role5', null, '', null, null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `accountName` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `credentialsSalt` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `locked` varchar(3) DEFAULT '0',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deletestatus` int(1) DEFAULT '0' COMMENT '逻辑删除状态0:存在1:删除',
  `sex` tinyint(2) NOT NULL DEFAULT '0' COMMENT '性别',
  `age` tinyint(2) DEFAULT '0' COMMENT '年龄',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `user_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '用户类别',
  `organization_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属机构',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '处长', 'simple', '78e21a6eb88529eab722793a448ed394', '4157c3feef4a6ed91b2c28cf4392f2d1', '0', '0', '2017-02-08 08:28:46', '0', '0', '0', '1870000000', '0', '1');
INSERT INTO `sys_user` VALUES ('2', '超级管理员', 'ROOT', '659dcd847e82446e2adf674e279f6ee4', '64718804eff46f1e3732a7e458720337', '0000', '0', '2017-02-08 08:28:48', '0', '0', '0', '1870000000', '1', '3');
INSERT INTO `sys_user` VALUES ('3', '管理员', 'admin', '132e985c476552c32caecf2fd728e379', '1b5bace1bce6690961454fa84635a850', '0', '0', '2017-02-08 08:28:51', '0', '0', '0', '1870000000', '1', '5');
INSERT INTO `sys_user` VALUES ('4', '3测试用户', 'test123', '864fd1f2dacd6ffb79c1843238a4f36d', '1da6b87b293643d8aef82f49954ba44b', null, '0', '2017-02-08 09:19:12', null, '0', '0', '1870000000', '1', '6');

-- ----------------------------
-- Table structure for sys_userlogin
-- ----------------------------
DROP TABLE IF EXISTS `sys_userlogin`;
CREATE TABLE `sys_userlogin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `accountName` varchar(20) DEFAULT NULL,
  `loginTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `loginIP` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ly_user_loginlist` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_userlogin
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `userId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '2', '1');
INSERT INTO `sys_user_role` VALUES ('2', '3', '2');
INSERT INTO `sys_user_role` VALUES ('3', '1', '3');
INSERT INTO `sys_user_role` VALUES ('3', '4', '4');
INSERT INTO `sys_user_role` VALUES ('4', '1', '5');
INSERT INTO `sys_user_role` VALUES ('4', '4', '6');
