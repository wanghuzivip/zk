/*
Navicat MySQL Data Transfer

Source Server         : zk-ali
Source Server Version : 50711
Source Host           : localhost:3336
Source Database       : zk

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-11-13 21:35:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_pic`
-- ----------------------------
DROP TABLE IF EXISTS `t_pic`;
CREATE TABLE `t_pic` (
  `id` varchar(64) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  `url` varchar(128) DEFAULT NULL,
  `version` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pic
-- ----------------------------
INSERT INTO `t_pic` VALUES ('95911724e44d438780817cb0bac7c9f9', '14', 'icudtl.dat', '/usr/local/zhongkong/backPic/14/95911724e44d438780817cb0bac7c9f9/icudtl.dat', 'http://139.196.112.43:8080/zk/downloadbackPic/95911724e44d438780817cb0bac7c9f9', '5');

-- ----------------------------
-- Table structure for `t_radio`
-- ----------------------------
DROP TABLE IF EXISTS `t_radio`;
CREATE TABLE `t_radio` (
  `id` varchar(64) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `audioName` varchar(128) DEFAULT NULL,
  `audioPath` varchar(128) DEFAULT NULL,
  `audioUrl` varchar(128) DEFAULT NULL,
  `audioTextUrl` varchar(128) DEFAULT NULL,
  `version` int(20) DEFAULT NULL,
  `audioTextPath` varchar(128) DEFAULT NULL,
  `audioTextName` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_radio
-- ----------------------------
INSERT INTO `t_radio` VALUES ('edbe4941afac433db448dd739689dd00', '39', '555.mp3', '/usr/local/zhongkong/radio/audio/39/edbe4941afac433db448dd739689dd00/555.mp3', 'http://139.196.112.43:8080/zk/downloadaudio/1/edbe4941afac433db448dd739689dd00', 'http://139.196.112.43:8080/zk/downloadaudio/2/edbe4941afac433db448dd739689dd00', '1', '/usr/local/zhongkong/radio/text/39/edbe4941afac433db448dd739689dd00/555.txt', '555.txt');

-- ----------------------------
-- Table structure for `t_rule`
-- ----------------------------
DROP TABLE IF EXISTS `t_rule`;
CREATE TABLE `t_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(128) DEFAULT NULL,
  `ruleDbUrl` varchar(128) DEFAULT NULL,
  `ruleDbVersion` int(20) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `updateTime` bigint(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=325 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_rule
-- ----------------------------
INSERT INTO `t_rule` VALUES ('324', '/usr/local/zhongkong/rule/RuleProduce.db', 'http://139.196.112.43:8080/zk/downloadRule/', '3', 'RuleProduce.db', '1478276467139');

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
  `path` varchar(128) DEFAULT NULL,
  `codeDbUrl` varchar(64) DEFAULT '',
  `codeDbVersion` int(20) DEFAULT NULL,
  `codeDbLastUpdateTime` bigint(20) DEFAULT NULL,
  `beizhu` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`,`registCode`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('38', '333hjju', 'ff16c81d292943af8e999b42b8b4a04f', '0', '1478156614955', '0', '1478361600000', '0', '1478156614955', '38ctrl.db', '/usr/local/zhongkong/code/38/38ctrl.db', 'http://139.196.112.43:8080/zk/downloadCode/38', '2', '1478183582001', '');
INSERT INTO `t_user` VALUES ('39', '888', '5393f921cac74753a585ad236c359baf', '0', '1478180778652', '0', '1478102400000', '0', '1478180778652', '39ctrl.db', '/usr/local/zhongkong/code/39/39ctrl.db', 'http://139.196.112.43:8080/zk/downloadCode/39', '2', '1478183560483', '');
INSERT INTO `t_user` VALUES ('40', '88888', 'f2b007c0d8d24ad8a33a170aaa2c24be', '0', '1478184363804', '0', '1478102400000', '0', '1478184363804', '40ctrl.db', '/usr/local/zhongkong/code/40/40ctrl.db', 'http://139.196.112.43:8080/zk/downloadCode/40', '3', '1478276952328', '82');
INSERT INTO `t_user` VALUES ('41', 'bjj', '0ffe6713ff2c46dba4ec8673f8f0c644', '0', '1478317471708', '0', '1478275200000', '0', '1478317471708', '', null, '', '0', '0', '');
