/*
 Navicat Premium Data Transfer

 Source Server         : mysql-Tencent
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : cdb-3stfljwg.cd.tencentcdb.com:10031
 Source Schema         : CampusLive

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 01/04/2020 18:17:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Address
-- ----------------------------
DROP TABLE IF EXISTS `Address`;
CREATE TABLE `Address`  (
  `userID` int(11) NOT NULL,
  `Address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`userID`, `Address`) USING BTREE,
  CONSTRAINT `Address_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `User` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for BillRecord
-- ----------------------------
DROP TABLE IF EXISTS `BillRecord`;
CREATE TABLE `BillRecord`  (
  `userID` int(11) NOT NULL,
  `billAmount` int(3) NOT NULL,
  `billType` int(3) NULL DEFAULT NULL,
  `billTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `billSource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userID`, `billTime`) USING BTREE,
  CONSTRAINT `BillRecord_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `User` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ComplaintRecord
-- ----------------------------
DROP TABLE IF EXISTS `ComplaintRecord`;
CREATE TABLE `ComplaintRecord`  (
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
-- Table structure for CustomerService
-- ----------------------------
DROP TABLE IF EXISTS `CustomerService`;
CREATE TABLE `CustomerService`  (
  `serviceID` int(11) NOT NULL AUTO_INCREMENT,
  `servidPsd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authority` int(3) NULL DEFAULT NULL,
  `servicePhone` int(11) NULL DEFAULT NULL,
  `serviceMail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`serviceID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for Employee
-- ----------------------------
DROP TABLE IF EXISTS `Employee`;
# CREATE TABLE `Employee`  (
#   `employeeID` int(11) NOT NULL,
#   `employeePsd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
#   `empolyeeName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
#   `employeeSchool` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
#   `employeeAge` int(2) NULL DEFAULT NULL,
#   `employeePhone` int(11) NULL DEFAULT NULL,
#   `employeeMail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
#   `employeeWechat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
#   `employeeBalance` double(255, 0) NULL DEFAULT NULL,
#   `userCredit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
#   `userImage` bit(1) NULL DEFAULT NULL,
#   `businessNum` int(11) NULL DEFAULT NULL,
#   `employeeBasicSalary` double(10, 2) NULL DEFAULT NULL,
#   PRIMARY KEY (`employeeID`) USING BTREE
# ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for Order
-- ----------------------------
DROP TABLE IF EXISTS `Order`;
CREATE TABLE `Order`  (
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
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
