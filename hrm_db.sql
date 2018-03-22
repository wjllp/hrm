/*
Navicat MySQL Data Transfer

Source Server         : ctgu
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : hrm_db

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-03-22 16:16:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept_inf
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE `dept_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `REMARK` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept_inf
-- ----------------------------
INSERT INTO `dept_inf` VALUES ('1', '技术部', '负责网站开发');
INSERT INTO `dept_inf` VALUES ('2', '运营部', '运营部');
INSERT INTO `dept_inf` VALUES ('3', '财务部', '财务部');
INSERT INTO `dept_inf` VALUES ('5', '总公办', '总公办');
INSERT INTO `dept_inf` VALUES ('6', '市场部', '市场部');
INSERT INTO `dept_inf` VALUES ('7', '教学部', '教学部');

-- ----------------------------
-- Table structure for document_inf
-- ----------------------------
DROP TABLE IF EXISTS `document_inf`;
CREATE TABLE `document_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(50) NOT NULL,
  `filename` varchar(300) NOT NULL,
  `REMARK` varchar(300) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DOCUMENT_USER` (`USER_ID`),
  CONSTRAINT `FK_DOCUMENT_USER` FOREIGN KEY (`USER_ID`) REFERENCES `user_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of document_inf
-- ----------------------------
INSERT INTO `document_inf` VALUES ('1', '公司logo', '5405890bf090d.jpg', '帅气', '2017-11-11 11:27:13', '2');

-- ----------------------------
-- Table structure for employee_inf
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE `employee_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEPT_ID` int(11) NOT NULL,
  `JOB_ID` int(11) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `CARD_ID` varchar(18) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `POST_CODE` varchar(50) DEFAULT NULL,
  `TEL` varchar(16) DEFAULT NULL,
  `PHONE` varchar(11) NOT NULL,
  `QQ_NUM` varchar(10) DEFAULT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `SEX` int(11) NOT NULL DEFAULT '1',
  `PARTY` varchar(10) DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `RACE` varchar(100) DEFAULT NULL,
  `EDUCATION` varchar(10) DEFAULT NULL,
  `SPECIALITY` varchar(20) DEFAULT NULL,
  `HOBBY` varchar(100) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_EMP_DEPT` (`DEPT_ID`),
  KEY `FK_EMP_JOB` (`JOB_ID`),
  CONSTRAINT `FK_EMP_DEPT` FOREIGN KEY (`DEPT_ID`) REFERENCES `dept_inf` (`ID`),
  CONSTRAINT `FK_EMP_JOB` FOREIGN KEY (`JOB_ID`) REFERENCES `job_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_inf
-- ----------------------------
INSERT INTO `employee_inf` VALUES ('1', '1', '8', '爱丽丝', '4328011988', '广州天河', '510000', '020-77777777', '13902001111', '36750066', '251425887@qq.com', '1', '党员', '1980-01-01 00:00:00', '满', '本科', '美声', '唱歌', '四大天王', '2017-11-11 11:35:18');
INSERT INTO `employee_inf` VALUES ('2', '2', '1', '杰克', '22623', '43234', '42427424', '42242', '4247242', '42424', '251425887@qq.com', '2', '少先队', '1995-07-21 13:27:49', '汉', '本科', '英语', '游泳', '无', '2017-11-11 11:35:18');
INSERT INTO `employee_inf` VALUES ('4', '1', '2', '汪俊', '420704199702140036', '没有', '443006', '07111234567', '13888888888', '3213213', '1242924472@qq.com', '1', '团员', '2017-03-01 00:00:00', '苗族', '本科', '摄影', '轮滑', '帅哥', '2018-03-22 10:44:31');
INSERT INTO `employee_inf` VALUES ('8', '3', '2', 'linkey', '420704198927190036', '中国', '443078', '07117894561', '13329970889', '12345678', '1242924472@qq.com', '0', '党员', '1984-03-17 00:00:00', '汉族', '博士', '管理', '篮球', '五', '2018-03-22 14:47:09');

-- ----------------------------
-- Table structure for job_inf
-- ----------------------------
DROP TABLE IF EXISTS `job_inf`;
CREATE TABLE `job_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `REMARK` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_inf
-- ----------------------------
INSERT INTO `job_inf` VALUES ('1', '职员', '职员');
INSERT INTO `job_inf` VALUES ('2', 'Java开发工程师', '负责后台开发');
INSERT INTO `job_inf` VALUES ('3', 'Java中级开发工程师', 'Java中级开发工程师');
INSERT INTO `job_inf` VALUES ('4', 'Java高级开发工程师', 'Java高级开发工程师');
INSERT INTO `job_inf` VALUES ('5', '系统管理员', '系统管理员');
INSERT INTO `job_inf` VALUES ('6', '架构师', '架构师');
INSERT INTO `job_inf` VALUES ('7', '主管', '主管');
INSERT INTO `job_inf` VALUES ('8', '经理', '经理');
INSERT INTO `job_inf` VALUES ('9', '总经理', '总经理');
INSERT INTO `job_inf` VALUES ('11', '后勤部', '负责公司后勤');

-- ----------------------------
-- Table structure for notice_inf
-- ----------------------------
DROP TABLE IF EXISTS `notice_inf`;
CREATE TABLE `notice_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(50) NOT NULL,
  `CONTENT` varchar(254) NOT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_NOTICE_USER` (`USER_ID`),
  CONSTRAINT `FK_NOTICE_USER` FOREIGN KEY (`USER_ID`) REFERENCES `user_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice_inf
-- ----------------------------
INSERT INTO `notice_inf` VALUES ('3', '今晚加班', '今晚加班三小时', '2017-11-11 13:17:00', '2');

-- ----------------------------
-- Table structure for user_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) NOT NULL,
  `PASSWORD` varchar(16) NOT NULL,
  `STATUS` int(11) NOT NULL DEFAULT '1',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES ('1', 'admin', '123456', '1', '2017-11-11 09:34:28', '管理员');
INSERT INTO `user_inf` VALUES ('2', 'wangjun', '123456', '1', '2017-11-11 09:41:38', '汪俊');
INSERT INTO `user_inf` VALUES ('3', 'lihua', '123456', '2', '2017-11-11 23:05:04', '李华');
INSERT INTO `user_inf` VALUES ('7', 'ksdhfksd', '123456', '2', '2017-11-11 09:37:35', '陈爽');
INSERT INTO `user_inf` VALUES ('8', 'flnk', '53543', '1', '2018-03-22 09:37:45', '吴龙');
INSERT INTO `user_inf` VALUES ('9', 'mnbf', '2143214', '1', '2018-03-22 10:00:10', '霍甲');
