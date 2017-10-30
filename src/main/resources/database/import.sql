/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost
 Source Database       : test

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 10/30/2017 08:05:36 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `salt` varchar(255) NOT NULL COMMENT '加密盐',
  `state` tinyint(4) NOT NULL COMMENT '状态 0：未启用  1：已启用',
  `user_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '用户类型：2：管理员  1：普通用户',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `add_user_id` int(11) NOT NULL COMMENT '添加人员ID',
  `add_time` int(11) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `update_user_id` int(11) NOT NULL COMMENT '修改人ID',
  `update_time` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_f2ksd6h8hsjtd57ipfq9myr64` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_user_info`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_info` VALUES ('1', 'admin', '6a56e0a0ecf379249be0defc93f77eb7', '099BMBUsSWfJ2BNP', '1', '2', '管理员', '0', '0', '1', '1509266376'), ('24', 'liutie5', '23de75f403ecd2d3193641b96d866fa9', 'zn3Ns3VPpcIuHJHt', '1', '1', '', '23', '1509257672', '1', '1509283700');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;



CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `permission` varchar(255) NOT NULL COMMENT '权限字符串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

CREATE TABLE `sys_user_permission` (
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 |