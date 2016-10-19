/*
Navicat MySQL Data Transfer

Source Server         : 核心圈
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zhongkong

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-10-18 15:12:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_pic`
-- ----------------------------
DROP TABLE IF EXISTS `t_pic`;
CREATE TABLE `t_pic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `url` varchar(64) DEFAULT NULL,
  `version` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pic
-- ----------------------------

-- ----------------------------
-- Table structure for `t_radio`
-- ----------------------------
DROP TABLE IF EXISTS `t_radio`;
CREATE TABLE `t_radio` (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `audioUrl` varchar(64) DEFAULT NULL,
  `audioText` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_radio
-- ----------------------------

-- ----------------------------
-- Table structure for `t_rule`
-- ----------------------------
DROP TABLE IF EXISTS `t_rule`;
CREATE TABLE `t_rule` (
  `id` bigint(20) NOT NULL,
  `ruleDbUrl` varchar(64) DEFAULT NULL,
  `ruleDbVersion` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_rule
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `registCode` varchar(64) NOT NULL,
  `hasUsed` tinyint(2) DEFAULT '0',
  `registTime` bigint(20) DEFAULT NULL,
  `limitTime` bigint(20) DEFAULT NULL,
  `lastSynTime` bigint(20) DEFAULT NULL,
  `lastSetTime` bigint(20) DEFAULT NULL,
  `codeDbName` varchar(64) DEFAULT NULL,
  `codeDbUrl` varchar(64) DEFAULT NULL,
  `codeDbVersion` int(10) DEFAULT NULL,
  `codeDbLastUpdateTime` bigint(20) DEFAULT NULL,
  `beizhu` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
