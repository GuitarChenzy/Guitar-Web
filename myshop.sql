/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : myshop

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-07-15 13:41:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `rolename` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `regtime` datetime NOT NULL,
  `logtime` datetime DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  `rstate` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) NOT NULL,
  `parentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `category_parentid` (`parentid`),
  CONSTRAINT `category_parentid` FOREIGN KEY (`parentid`) REFERENCES `category` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `addtime` datetime NOT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(50) NOT NULL,
  `gprice` decimal(10,2) NOT NULL,
  `gtypeid` int(11) NOT NULL,
  `gcount` int(11) NOT NULL,
  `gdesc` varchar(255) NOT NULL,
  `gimage` varchar(255) DEFAULT NULL,
  `gstate` int(2) NOT NULL DEFAULT '1' COMMENT '1激活0未激活',
  `gdatetime` datetime NOT NULL,
  PRIMARY KEY (`gid`),
  KEY `goods_type` (`gtypeid`),
  CONSTRAINT `goods_type` FOREIGN KEY (`gtypeid`) REFERENCES `category` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for home
-- ----------------------------
DROP TABLE IF EXISTS `home`;
CREATE TABLE `home` (
  `hid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `hadress` varchar(200) NOT NULL,
  `phone` varchar(14) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `hometime` datetime NOT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for numcoin
-- ----------------------------
DROP TABLE IF EXISTS `numcoin`;
CREATE TABLE `numcoin` (
  `nid` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `grecoin` int(11) NOT NULL,
  `getcoin` int(11) NOT NULL,
  `nmax` int(2) NOT NULL,
  `publictime` datetime NOT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` bigint(20) NOT NULL,
  `gid` int(11) NOT NULL,
  `ofcount` int(11) NOT NULL COMMENT '购买个数',
  `ofprice` decimal(10,2) NOT NULL COMMENT '购买单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `pdatetime` datetime NOT NULL,
  `pcount` int(11) NOT NULL,
  `pstate` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(20) NOT NULL,
  `rdesc` varchar(100) NOT NULL,
  `publictime` datetime NOT NULL,
  `updatetime` datetime DEFAULT NULL,
  `rchild` int(2) DEFAULT '0',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_function
-- ----------------------------
DROP TABLE IF EXISTS `role_function`;
CREATE TABLE `role_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `fid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for torder
-- ----------------------------
DROP TABLE IF EXISTS `torder`;
CREATE TABLE `torder` (
  `oid` bigint(20) NOT NULL,
  `uid` int(11) NOT NULL,
  `ophone` bigint(20) NOT NULL DEFAULT '18229926656',
  `oadress` varchar(255) NOT NULL,
  `oprice` decimal(10,2) NOT NULL,
  `paytype` varchar(20) NOT NULL DEFAULT '网上交易',
  `state` int(11) NOT NULL,
  `odatetime` datetime NOT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_uid` (`uid`),
  CONSTRAINT `fk_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for turl
-- ----------------------------
DROP TABLE IF EXISTS `turl`;
CREATE TABLE `turl` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(20) NOT NULL,
  `furl` varchar(255) NOT NULL,
  `publictime` datetime NOT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `realname` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL,
  `sex` varchar(2) NOT NULL DEFAULT '男',
  `email` varchar(255) NOT NULL,
  `ucoin` int(11) DEFAULT '0',
  `adress` varchar(255) NOT NULL,
  `registertime` datetime NOT NULL,
  `logintime` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
