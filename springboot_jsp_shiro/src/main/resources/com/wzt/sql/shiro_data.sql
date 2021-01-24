/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2021-01-24 22:27:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_perms
-- ----------------------------
DROP TABLE IF EXISTS `t_perms`;
CREATE TABLE `t_perms` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_perms
-- ----------------------------
INSERT INTO `t_perms` VALUES ('1', 'user:*:*', null);
INSERT INTO `t_perms` VALUES ('2', 'product:*:01', null);
INSERT INTO `t_perms` VALUES ('3', 'order:*:*', null);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin');
INSERT INTO `t_role` VALUES ('2', 'user');
INSERT INTO `t_role` VALUES ('3', 'product');

-- ----------------------------
-- Table structure for t_role_perms
-- ----------------------------
DROP TABLE IF EXISTS `t_role_perms`;
CREATE TABLE `t_role_perms` (
  `id` int(6) NOT NULL,
  `roleid` int(6) DEFAULT NULL,
  `permsid` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_perms
-- ----------------------------
INSERT INTO `t_role_perms` VALUES ('1', '1', '1');
INSERT INTO `t_role_perms` VALUES ('2', '1', '2');
INSERT INTO `t_role_perms` VALUES ('3', '2', '1');
INSERT INTO `t_role_perms` VALUES ('4', '3', '2');
INSERT INTO `t_role_perms` VALUES ('5', '1', '3');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'zhangsan', '3ce3a24875c57467fff0ea74a7c3ce66', 'qon%XQZ@');
INSERT INTO `t_user` VALUES ('2', 'lisi', 'bfcad3f0a51090046f399537c3c4e441', 'cyZXJgHr');
INSERT INTO `t_user` VALUES ('3', 'wangwu', 'c8cae4a0c7c6db71d7ebc0bbceac489e', 'mljnlcIO');
INSERT INTO `t_user` VALUES ('4', 'xiaochen', '5f87cd99b0e52ec7bcb58ef7e92d6f92', '5b)iVS(Y');
INSERT INTO `t_user` VALUES ('5', 'xiaoming', 'b79cd4e8414fe649ad8c04f47c36e667', '%6FbyzMX');
INSERT INTO `t_user` VALUES ('6', 'xiaoxiao', '86ea8c44a280a3391d8164aa1faf3d27', 'eI#9E1N#');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(6) NOT NULL,
  `userid` int(6) DEFAULT NULL,
  `roleid` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1');
INSERT INTO `t_user_role` VALUES ('2', '4', '2');
INSERT INTO `t_user_role` VALUES ('3', '4', '3');
