/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : campuslive

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 29/04/2020 10:14:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for billrecord
-- ----------------------------
DROP TABLE IF EXISTS `billrecord`;
CREATE TABLE `billrecord`  (
  `userID` int(11) NOT NULL,
  `billAmount` int(3) NOT NULL,
  `billType` int(3) NULL DEFAULT NULL,
  `billTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `billSource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userID`, `billTime`) USING BTREE,
  CONSTRAINT `BillRecord_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for complaintrecord
-- ----------------------------
DROP TABLE IF EXISTS `complaintrecord`;
CREATE TABLE `complaintrecord`  (
  `complaineeID` int(11) NOT NULL,
  `complainantID` int(11) NULL DEFAULT NULL,
  `complaintTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `complaintState` int(3) NULL DEFAULT NULL,
  `complaintContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `orderID` int(11) NULL DEFAULT NULL,
  `complaintID` int(11) NOT NULL AUTO_INCREMENT,
  `serviceID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`complaintID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customerservice
-- ----------------------------
DROP TABLE IF EXISTS `customerservice`;
CREATE TABLE `customerservice`  (
  `serviceID` int(11) NOT NULL AUTO_INCREMENT,
  `servidPsd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authority` int(3) NULL DEFAULT NULL,
  `servicePhone` int(11) NULL DEFAULT NULL,
  `serviceMail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`serviceID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `orderMoney` int(3) NULL DEFAULT NULL,
  `orderTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `orderState` int(3) NULL DEFAULT NULL,
  `clientID` int(11) NULL DEFAULT NULL,
  `serverID` int(11) NULL DEFAULT NULL,
  `orderContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `orderAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orderID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stuPersonID` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `stuID` int(11) NULL DEFAULT NULL,
  `stuName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `stuSchool` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`stuPersonID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userPsd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `userSchool` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `userAge` int(2) NULL DEFAULT NULL,
  `userPhone` int(11) NULL DEFAULT NULL,
  `userMail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `userWechat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `userBalance` double(255, 0) NULL DEFAULT NULL,
  `userCredit` int(255) NULL DEFAULT NULL,
  `userImage` bit(1) NULL DEFAULT NULL,
  `userType` int(3) NULL DEFAULT NULL,
  `userState` int(3) NULL DEFAULT NULL,
  `businessNum` int(11) NULL DEFAULT NULL,
  `employeeBasicSalary` double(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`userID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19990524 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for useraddress
-- ----------------------------
DROP TABLE IF EXISTS `useraddress`;
CREATE TABLE `useraddress`  (
  `userID` int(11) NOT NULL,
  `Address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`userID`, `Address`) USING BTREE,
  CONSTRAINT `Address_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
