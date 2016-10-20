/*
Navicat MySQL Data Transfer

Source Server         : 核心圈
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zhongkong

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-10-20 17:57:21
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
  `createTime` bigint(20) DEFAULT NULL,
  `firstUseTime` bigint(20) DEFAULT NULL,
  `limitTime` bigint(20) DEFAULT NULL,
  `lastSynTime` bigint(20) DEFAULT NULL,
  `lastSetTime` bigint(20) DEFAULT NULL,
  `codeDbName` varchar(64) DEFAULT '',
  `codeDbUrl` varchar(64) DEFAULT '',
  `codeDbVersion` int(10) DEFAULT NULL,
  `codeDbLastUpdateTime` bigint(20) DEFAULT NULL,
  `beizhu` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`,`registCode`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '123', '446f3299-286d-4f6b-939e-1717fdb44778', '0', null, '0', '0', '0', '0', null, null, '0', '0', null);
INSERT INTO `t_user` VALUES ('2', '123', 'c31decee-09f0-40c0-94d2-b0281cad771a', '1', null, '0', '0', '0', '0', null, null, '0', '0', null);
INSERT INTO `t_user` VALUES ('3', '123', 'fb71ab98-bec8-4318-ae77-2e0086a9a0d3', '1', null, '0', '0', '0', '0', null, null, '0', '0', null);
INSERT INTO `t_user` VALUES ('4', '123', 'db9a1add-a400-4516-8102-e5bf8f348f02', '1', null, '0', '0', '0', '0', null, null, '0', '0', null);
INSERT INTO `t_user` VALUES ('5', '123', '38c28356-b24b-4b86-b595-691e8f479242', '1', null, '0', '0', '0', '0', null, null, '0', '0', null);
INSERT INTO `t_user` VALUES ('6', '123', '93a7bfd2-8bf4-48df-b0a3-7ea734051976', '1', null, '0', '0', '0', '0', null, null, '0', '0', null);
INSERT INTO `t_user` VALUES ('7', '123', 'bf4f8fbc-e0dc-435b-8a7f-307fa2534024', '1', null, '0', '0', '0', '0', null, null, '0', '0', '333');
INSERT INTO `t_user` VALUES ('8', '123', '891b9234-7141-4126-9504-6fa3c533ab4e', '1', null, '0', '0', '0', '0', null, null, '0', '0', '333');
INSERT INTO `t_user` VALUES ('9', '123', '97e2000a-3825-4bb4-8f98-3eb38e904254', '1', null, '0', '0', '0', '0', null, null, '0', '0', '333');
INSERT INTO `t_user` VALUES ('10', '123', '863504d7-e609-430e-b07f-ae297bcfe051', '1', '28328', '0', '0', '0', '0', null, null, '0', '0', '333');
