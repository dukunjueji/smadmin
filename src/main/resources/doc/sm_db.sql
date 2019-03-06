/*
Navicat MySQL Data Transfer

Source Server         : 66汽配测试库
Source Server Version : 50624
Source Host           : 10.104.50.25:3306
Source Database       : sm_db

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2019-03-06 10:16:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_bd_address
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_address`;
CREATE TABLE `t_bd_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `receiver` varchar(30) NOT NULL COMMENT '收货人',
  `telephone` char(11) NOT NULL COMMENT '手机号',
  `province` varchar(10) NOT NULL COMMENT '省',
  `city` varchar(10) NOT NULL COMMENT '市',
  `district` varchar(10) NOT NULL COMMENT '区',
  `area_code` varchar(255) DEFAULT NULL COMMENT '省市区编码',
  `addr_detail` varchar(255) NOT NULL COMMENT '详细地址',
  `is_default` tinyint(1) NOT NULL COMMENT '是否默认地址',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_bd_address_member_id` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='收货地址';

-- ----------------------------
-- Records of t_bd_address
-- ----------------------------
INSERT INTO `t_bd_address` VALUES ('2', '3', '追疯', '17777777777', '河北省', '石家庄市', '长安区', '130102', '801室', '1', null, '2018-11-15 14:02:11', '3', '2018-11-15 15:09:39');
INSERT INTO `t_bd_address` VALUES ('3', '3', 'To追疯', '19999999999', '河北省', '石家庄市', '长安区', '130102', '91号', '0', null, '2018-11-15 14:05:09', '3', '2018-11-15 15:09:39');
INSERT INTO `t_bd_address` VALUES ('4', '3', '测试', '18888888888', '福建省', '厦门市', '思明区', '350203', '展鸿路8号8楼', '0', '3', '2018-11-15 15:10:20', null, '2018-11-15 15:10:20');

-- ----------------------------
-- Table structure for t_bd_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_banner`;
CREATE TABLE `t_bd_banner` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(20) DEFAULT NULL COMMENT '广告名称',
  `type` tinyint(1) DEFAULT NULL COMMENT '广告类型1首页轮播图',
  `sort_num` int(10) DEFAULT NULL COMMENT '排序',
  `image_url` varchar(255) DEFAULT NULL COMMENT '广告图片',
  `url` varchar(255) DEFAULT NULL COMMENT '广告地址',
  `clicks` int(10) DEFAULT NULL COMMENT '点击次数',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_show` tinyint(1) DEFAULT NULL COMMENT '是否显示，1：显示。2,：不显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='广告表';

-- ----------------------------
-- Records of t_bd_banner
-- ----------------------------
INSERT INTO `t_bd_banner` VALUES ('1', 'banner1', '1', '0', 'V2/201811/1/4-5711b048eccf4e0d8bfe02e43ff70ba8-g-o-0.jpg', '', '21', '', '1', '2018-11-13 19:54:34', '1', '2018-11-16 12:21:45', '1');
INSERT INTO `t_bd_banner` VALUES ('2', 'banner2', '1', '0', 'V2/201811/1/4-5d238c4878434e9ca33a0d7f5c35761a-g-o-0.jpg', '', '38', '', '1', '2018-11-13 19:56:57', '1', '2018-11-16 12:21:43', '1');
INSERT INTO `t_bd_banner` VALUES ('3', 'banner3', '1', '3', 'V2/201811/1/4-c80b9693bba849f28cc07f5d59dd1e30-g-o-0.jpg', '', '7', 'banner3', '1', '2018-11-15 18:01:08', '1', '2018-11-16 12:21:46', '1');

-- ----------------------------
-- Table structure for t_bd_growth_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_growth_detail`;
CREATE TABLE `t_bd_growth_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `member_id` bigint(20) NOT NULL COMMENT '会员id',
  `type` tinyint(1) NOT NULL COMMENT '类型 1:登录、2:签到、3:购买商品、4:评价、5:分享',
  `growth` bigint(20) NOT NULL COMMENT '成长值',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8 COMMENT='成长值明细表';

-- ----------------------------
-- Records of t_bd_growth_detail
-- ----------------------------
INSERT INTO `t_bd_growth_detail` VALUES ('1', '1', '1', '5', null, '2018-11-10 10:30:51', null, '2018-11-10 10:30:51');
INSERT INTO `t_bd_growth_detail` VALUES ('2', '1', '1', '5', null, '2018-11-12 09:53:13', null, '2018-11-12 09:53:13');
INSERT INTO `t_bd_growth_detail` VALUES ('3', '19', '1', '5', null, '2018-11-12 14:13:59', null, '2018-11-12 14:13:59');
INSERT INTO `t_bd_growth_detail` VALUES ('4', '5', '1', '5', null, '2018-11-12 14:30:29', null, '2018-11-12 14:30:29');
INSERT INTO `t_bd_growth_detail` VALUES ('5', '3', '1', '5', null, '2018-11-12 14:47:59', null, '2018-11-12 14:47:59');
INSERT INTO `t_bd_growth_detail` VALUES ('6', '14', '1', '5', null, '2018-11-12 15:01:52', null, '2018-11-12 15:01:52');
INSERT INTO `t_bd_growth_detail` VALUES ('7', '21', '1', '5', null, '2018-11-12 15:30:55', null, '2018-11-12 15:30:55');
INSERT INTO `t_bd_growth_detail` VALUES ('8', '23', '1', '5', null, '2018-11-12 15:39:50', null, '2018-11-12 15:39:50');
INSERT INTO `t_bd_growth_detail` VALUES ('9', '16', '1', '5', null, '2018-11-12 15:46:38', null, '2018-11-12 15:46:38');
INSERT INTO `t_bd_growth_detail` VALUES ('10', '16', '3', '49', null, '2018-11-12 17:40:51', null, '2018-11-12 17:40:51');
INSERT INTO `t_bd_growth_detail` VALUES ('11', '3', '1', '5', null, '2018-11-12 18:27:15', null, '2018-11-12 18:27:15');
INSERT INTO `t_bd_growth_detail` VALUES ('12', '16', '3', '49', null, '2018-11-12 18:29:12', null, '2018-11-12 18:29:12');
INSERT INTO `t_bd_growth_detail` VALUES ('13', '20', '1', '5', null, '2018-11-13 09:26:06', null, '2018-11-13 09:26:06');
INSERT INTO `t_bd_growth_detail` VALUES ('14', '14', '1', '5', null, '2018-11-13 09:34:21', null, '2018-11-13 09:34:21');
INSERT INTO `t_bd_growth_detail` VALUES ('15', '1', '1', '5', null, '2018-11-13 09:42:24', null, '2018-11-13 09:42:24');
INSERT INTO `t_bd_growth_detail` VALUES ('16', '20', '3', '0', null, '2018-11-13 12:33:45', null, '2018-11-13 12:33:45');
INSERT INTO `t_bd_growth_detail` VALUES ('17', '20', '3', '4', null, '2018-11-13 12:34:28', null, '2018-11-13 12:34:28');
INSERT INTO `t_bd_growth_detail` VALUES ('18', '20', '3', '4', null, '2018-11-13 12:37:01', null, '2018-11-13 12:37:01');
INSERT INTO `t_bd_growth_detail` VALUES ('19', '3', '1', '5', null, '2018-11-13 13:57:56', null, '2018-11-13 13:57:56');
INSERT INTO `t_bd_growth_detail` VALUES ('20', '19', '1', '5', null, '2018-11-13 14:36:28', null, '2018-11-13 14:36:28');
INSERT INTO `t_bd_growth_detail` VALUES ('21', '3', '1', '5', null, '2018-11-13 17:55:02', null, '2018-11-13 17:55:02');
INSERT INTO `t_bd_growth_detail` VALUES ('22', '3', '3', '4', null, '2018-11-13 17:57:00', null, '2018-11-13 17:57:00');
INSERT INTO `t_bd_growth_detail` VALUES ('23', '16', '1', '5', null, '2018-11-13 17:58:57', null, '2018-11-13 17:58:57');
INSERT INTO `t_bd_growth_detail` VALUES ('24', '3', '3', '4', null, '2018-11-13 18:10:34', null, '2018-11-13 18:10:34');
INSERT INTO `t_bd_growth_detail` VALUES ('25', '20', '3', '10', null, '2018-11-13 20:12:55', null, '2018-11-13 20:12:55');
INSERT INTO `t_bd_growth_detail` VALUES ('26', '3', '1', '5', null, '2018-11-14 08:50:27', null, '2018-11-14 08:50:27');
INSERT INTO `t_bd_growth_detail` VALUES ('27', '14', '1', '5', null, '2018-11-14 09:21:53', null, '2018-11-14 09:21:53');
INSERT INTO `t_bd_growth_detail` VALUES ('28', '16', '1', '5', null, '2018-11-14 09:47:56', null, '2018-11-14 09:47:56');
INSERT INTO `t_bd_growth_detail` VALUES ('29', '1', '1', '5', null, '2018-11-14 10:08:47', null, '2018-11-14 10:08:47');
INSERT INTO `t_bd_growth_detail` VALUES ('30', '20', '1', '5', null, '2018-11-14 13:19:39', null, '2018-11-14 13:19:39');
INSERT INTO `t_bd_growth_detail` VALUES ('31', '16', '3', '49', null, '2018-11-14 13:57:30', null, '2018-11-14 13:57:30');
INSERT INTO `t_bd_growth_detail` VALUES ('32', '16', '3', '79', null, '2018-11-14 13:57:58', null, '2018-11-14 13:57:58');
INSERT INTO `t_bd_growth_detail` VALUES ('33', '16', '3', '49', null, '2018-11-14 14:07:31', null, '2018-11-14 14:07:31');
INSERT INTO `t_bd_growth_detail` VALUES ('34', '16', '3', '9', null, '2018-11-14 14:23:16', null, '2018-11-14 14:23:16');
INSERT INTO `t_bd_growth_detail` VALUES ('35', '16', '3', '149', null, '2018-11-14 14:59:20', null, '2018-11-14 14:59:20');
INSERT INTO `t_bd_growth_detail` VALUES ('36', '16', '3', '49', null, '2018-11-14 15:11:09', null, '2018-11-14 15:11:09');
INSERT INTO `t_bd_growth_detail` VALUES ('37', '16', '3', '49', null, '2018-11-14 17:36:39', null, '2018-11-14 17:36:39');
INSERT INTO `t_bd_growth_detail` VALUES ('38', '14', '3', '59', null, '2018-11-14 17:40:38', null, '2018-11-14 17:40:38');
INSERT INTO `t_bd_growth_detail` VALUES ('39', '1', '3', '42', null, '2018-11-14 18:02:23', null, '2018-11-14 18:02:23');
INSERT INTO `t_bd_growth_detail` VALUES ('40', '14', '3', '9', null, '2018-11-14 20:05:10', null, '2018-11-14 20:05:10');
INSERT INTO `t_bd_growth_detail` VALUES ('41', '16', '3', '49', null, '2018-11-14 20:05:10', null, '2018-11-14 20:05:10');
INSERT INTO `t_bd_growth_detail` VALUES ('42', '1', '1', '5', null, '2018-11-15 09:16:33', null, '2018-11-15 09:16:33');
INSERT INTO `t_bd_growth_detail` VALUES ('43', '14', '3', '59', null, '2018-11-15 09:31:51', null, '2018-11-15 09:31:51');
INSERT INTO `t_bd_growth_detail` VALUES ('44', '1', '3', '42', null, '2018-11-15 09:31:51', null, '2018-11-15 09:31:51');
INSERT INTO `t_bd_growth_detail` VALUES ('45', '1', '1', '5', null, '2018-11-15 09:31:51', null, '2018-11-15 09:31:51');
INSERT INTO `t_bd_growth_detail` VALUES ('46', '3', '3', '25', null, '2018-11-15 09:52:20', null, '2018-11-15 09:52:20');
INSERT INTO `t_bd_growth_detail` VALUES ('47', '14', '1', '5', null, '2018-11-15 10:20:09', null, '2018-11-15 10:20:09');
INSERT INTO `t_bd_growth_detail` VALUES ('48', '14', '3', '59', null, '2018-11-15 11:11:06', null, '2018-11-15 11:11:06');
INSERT INTO `t_bd_growth_detail` VALUES ('49', '1', '3', '42', null, '2018-11-15 11:11:06', null, '2018-11-15 11:11:06');
INSERT INTO `t_bd_growth_detail` VALUES ('50', '1', '1', '5', null, '2018-11-15 11:11:06', null, '2018-11-15 11:11:06');
INSERT INTO `t_bd_growth_detail` VALUES ('51', '3', '3', '25', null, '2018-11-15 11:11:06', null, '2018-11-15 11:11:06');
INSERT INTO `t_bd_growth_detail` VALUES ('52', '14', '1', '5', null, '2018-11-15 11:11:06', null, '2018-11-15 11:11:06');
INSERT INTO `t_bd_growth_detail` VALUES ('53', '20', '1', '5', null, '2018-11-15 11:23:46', null, '2018-11-15 11:23:46');
INSERT INTO `t_bd_growth_detail` VALUES ('54', '3', '3', '12', null, '2018-11-15 14:10:59', null, '2018-11-15 14:10:59');
INSERT INTO `t_bd_growth_detail` VALUES ('55', '1', '3', '127', null, '2018-11-15 17:17:22', null, '2018-11-15 17:17:22');
INSERT INTO `t_bd_growth_detail` VALUES ('56', '20', '3', '49', null, '2018-11-15 17:41:09', null, '2018-11-15 17:41:09');
INSERT INTO `t_bd_growth_detail` VALUES ('57', '1', '3', '8', null, '2018-11-15 19:58:39', null, '2018-11-15 19:58:39');
INSERT INTO `t_bd_growth_detail` VALUES ('58', '1', '3', '8', null, '2018-11-15 20:00:12', null, '2018-11-15 20:00:12');
INSERT INTO `t_bd_growth_detail` VALUES ('59', '1', '3', '8', null, '2018-11-15 20:02:03', null, '2018-11-15 20:02:03');
INSERT INTO `t_bd_growth_detail` VALUES ('60', '20', '1', '5', null, '2018-11-16 09:29:40', null, '2018-11-16 09:29:40');
INSERT INTO `t_bd_growth_detail` VALUES ('61', '1', '1', '5', null, '2018-11-16 09:42:56', null, '2018-11-16 09:42:56');
INSERT INTO `t_bd_growth_detail` VALUES ('62', '14', '1', '5', null, '2018-11-16 09:57:44', null, '2018-11-16 09:57:44');
INSERT INTO `t_bd_growth_detail` VALUES ('63', '25', '1', '5', null, '2018-11-16 11:22:33', null, '2018-11-16 11:22:33');
INSERT INTO `t_bd_growth_detail` VALUES ('64', '20', '3', '49', null, '2018-11-16 20:44:24', null, '2018-11-16 20:44:24');
INSERT INTO `t_bd_growth_detail` VALUES ('65', '20', '1', '5', null, '2018-11-17 09:27:41', null, '2018-11-17 09:27:41');
INSERT INTO `t_bd_growth_detail` VALUES ('66', '14', '1', '5', null, '2018-11-17 10:31:29', null, '2018-11-17 10:31:29');
INSERT INTO `t_bd_growth_detail` VALUES ('67', '14', '1', '5', null, '2018-11-18 20:39:58', null, '2018-11-18 20:39:58');
INSERT INTO `t_bd_growth_detail` VALUES ('68', '3', '1', '5', null, '2018-11-19 09:07:09', null, '2018-11-19 09:07:09');
INSERT INTO `t_bd_growth_detail` VALUES ('69', '1', '1', '5', null, '2018-11-19 09:10:59', null, '2018-11-19 09:10:59');
INSERT INTO `t_bd_growth_detail` VALUES ('70', '14', '1', '5', null, '2018-11-19 09:46:16', null, '2018-11-19 09:46:16');
INSERT INTO `t_bd_growth_detail` VALUES ('71', '20', '1', '5', null, '2018-11-19 10:05:43', null, '2018-11-19 10:05:43');
INSERT INTO `t_bd_growth_detail` VALUES ('72', '14', '3', '59', null, '2018-11-19 13:36:14', null, '2018-11-19 13:36:14');
INSERT INTO `t_bd_growth_detail` VALUES ('73', '14', '1', '5', null, '2018-11-20 09:00:34', null, '2018-11-20 09:00:34');
INSERT INTO `t_bd_growth_detail` VALUES ('74', '20', '1', '5', null, '2018-11-20 09:44:15', null, '2018-11-20 09:44:15');
INSERT INTO `t_bd_growth_detail` VALUES ('75', '14', '3', '5', null, '2018-11-20 10:25:14', null, '2018-11-20 10:25:14');
INSERT INTO `t_bd_growth_detail` VALUES ('76', '16', '1', '5', null, '2018-11-20 13:45:27', null, '2018-11-20 13:45:27');
INSERT INTO `t_bd_growth_detail` VALUES ('77', '16', '3', '74', null, '2018-11-20 13:51:46', null, '2018-11-20 13:51:46');
INSERT INTO `t_bd_growth_detail` VALUES ('78', '1', '1', '5', null, '2018-11-20 16:18:23', null, '2018-11-20 16:18:23');
INSERT INTO `t_bd_growth_detail` VALUES ('79', '14', '1', '5', null, '2018-11-21 09:31:57', null, '2018-11-21 09:31:57');
INSERT INTO `t_bd_growth_detail` VALUES ('80', '20', '1', '5', null, '2018-11-21 10:00:51', null, '2018-11-21 10:00:51');
INSERT INTO `t_bd_growth_detail` VALUES ('81', '14', '1', '5', null, '2018-11-22 11:25:14', null, '2018-11-22 11:25:14');
INSERT INTO `t_bd_growth_detail` VALUES ('82', '14', '1', '5', null, '2018-11-23 09:24:40', null, '2018-11-23 09:24:40');
INSERT INTO `t_bd_growth_detail` VALUES ('83', '3', '1', '5', null, '2018-11-26 11:26:04', null, '2018-11-26 11:26:04');
INSERT INTO `t_bd_growth_detail` VALUES ('84', '16', '1', '5', null, '2018-11-26 13:33:25', null, '2018-11-26 13:33:25');
INSERT INTO `t_bd_growth_detail` VALUES ('85', '16', '3', '74', null, '2018-11-26 13:44:17', null, '2018-11-26 13:44:17');
INSERT INTO `t_bd_growth_detail` VALUES ('86', '14', '1', '5', null, '2018-11-26 13:46:10', null, '2018-11-26 13:46:10');
INSERT INTO `t_bd_growth_detail` VALUES ('87', '16', '3', '71', null, '2018-11-26 14:32:00', null, '2018-11-26 14:32:00');

-- ----------------------------
-- Table structure for t_bd_integral_detaill
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_integral_detaill`;
CREATE TABLE `t_bd_integral_detaill` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `member_id` bigint(20) NOT NULL COMMENT '会员id',
  `type` tinyint(1) NOT NULL COMMENT '类型  1:签到、2:评价、3:购买商品、4:使用积分消费',
  `integral` bigint(20) NOT NULL COMMENT '积分值',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='积分明细表';

-- ----------------------------
-- Records of t_bd_integral_detaill
-- ----------------------------
INSERT INTO `t_bd_integral_detaill` VALUES ('1', '16', '3', '29', null, '2018-11-12 17:40:54', null, '2018-11-12 17:40:54');
INSERT INTO `t_bd_integral_detaill` VALUES ('2', '16', '3', '29', null, '2018-11-12 18:29:13', null, '2018-11-12 18:29:13');
INSERT INTO `t_bd_integral_detaill` VALUES ('3', '20', '3', '0', null, '2018-11-13 12:33:45', null, '2018-11-13 12:33:45');
INSERT INTO `t_bd_integral_detaill` VALUES ('4', '20', '3', '2', null, '2018-11-13 12:34:33', null, '2018-11-13 12:34:33');
INSERT INTO `t_bd_integral_detaill` VALUES ('5', '20', '3', '2', null, '2018-11-13 12:37:00', null, '2018-11-13 12:37:00');
INSERT INTO `t_bd_integral_detaill` VALUES ('6', '3', '3', '2', null, '2018-11-13 17:57:01', null, '2018-11-13 17:57:01');
INSERT INTO `t_bd_integral_detaill` VALUES ('7', '3', '3', '2', null, '2018-11-13 18:10:34', null, '2018-11-13 18:10:34');
INSERT INTO `t_bd_integral_detaill` VALUES ('8', '20', '3', '6', null, '2018-11-13 20:12:54', null, '2018-11-13 20:12:54');
INSERT INTO `t_bd_integral_detaill` VALUES ('9', '16', '3', '29', null, '2018-11-14 13:57:28', null, '2018-11-14 13:57:28');
INSERT INTO `t_bd_integral_detaill` VALUES ('10', '16', '3', '47', null, '2018-11-14 13:57:55', null, '2018-11-14 13:57:55');
INSERT INTO `t_bd_integral_detaill` VALUES ('11', '16', '3', '29', null, '2018-11-14 14:07:31', null, '2018-11-14 14:07:31');
INSERT INTO `t_bd_integral_detaill` VALUES ('12', '16', '3', '5', null, '2018-11-14 14:23:15', null, '2018-11-14 14:23:15');
INSERT INTO `t_bd_integral_detaill` VALUES ('13', '16', '3', '89', null, '2018-11-14 14:59:18', null, '2018-11-14 14:59:18');
INSERT INTO `t_bd_integral_detaill` VALUES ('14', '16', '3', '29', null, '2018-11-14 15:11:09', null, '2018-11-14 15:11:09');
INSERT INTO `t_bd_integral_detaill` VALUES ('15', '16', '3', '29', null, '2018-11-14 17:36:36', null, '2018-11-14 17:36:36');
INSERT INTO `t_bd_integral_detaill` VALUES ('16', '14', '3', '35', null, '2018-11-14 17:40:38', null, '2018-11-14 17:40:38');
INSERT INTO `t_bd_integral_detaill` VALUES ('17', '1', '3', '25', null, '2018-11-14 18:02:24', null, '2018-11-14 18:02:24');
INSERT INTO `t_bd_integral_detaill` VALUES ('18', '14', '3', '5', null, '2018-11-14 20:05:10', null, '2018-11-14 20:05:10');
INSERT INTO `t_bd_integral_detaill` VALUES ('19', '16', '3', '29', null, '2018-11-14 20:05:10', null, '2018-11-14 20:05:10');
INSERT INTO `t_bd_integral_detaill` VALUES ('20', '14', '3', '35', null, '2018-11-15 09:31:41', null, '2018-11-15 09:31:41');
INSERT INTO `t_bd_integral_detaill` VALUES ('21', '1', '3', '25', null, '2018-11-15 09:31:41', null, '2018-11-15 09:31:41');
INSERT INTO `t_bd_integral_detaill` VALUES ('22', '3', '3', '15', null, '2018-11-15 09:52:20', null, '2018-11-15 09:52:20');
INSERT INTO `t_bd_integral_detaill` VALUES ('23', '14', '3', '35', null, '2018-11-15 11:11:11', null, '2018-11-15 11:11:11');
INSERT INTO `t_bd_integral_detaill` VALUES ('24', '1', '3', '25', null, '2018-11-15 11:11:11', null, '2018-11-15 11:11:11');
INSERT INTO `t_bd_integral_detaill` VALUES ('25', '3', '3', '15', null, '2018-11-15 11:11:11', null, '2018-11-15 11:11:11');
INSERT INTO `t_bd_integral_detaill` VALUES ('26', '3', '3', '7', null, '2018-11-15 14:10:59', null, '2018-11-15 14:10:59');
INSERT INTO `t_bd_integral_detaill` VALUES ('27', '1', '3', '76', null, '2018-11-15 17:17:21', null, '2018-11-15 17:17:21');
INSERT INTO `t_bd_integral_detaill` VALUES ('28', '20', '3', '29', null, '2018-11-15 17:41:11', null, '2018-11-15 17:41:11');
INSERT INTO `t_bd_integral_detaill` VALUES ('29', '1', '3', '5', null, '2018-11-15 19:58:39', null, '2018-11-15 19:58:39');
INSERT INTO `t_bd_integral_detaill` VALUES ('30', '1', '3', '5', null, '2018-11-15 20:00:12', null, '2018-11-15 20:00:12');
INSERT INTO `t_bd_integral_detaill` VALUES ('31', '1', '3', '5', null, '2018-11-15 20:02:03', null, '2018-11-15 20:02:03');
INSERT INTO `t_bd_integral_detaill` VALUES ('32', '20', '3', '29', null, '2018-11-16 20:44:21', null, '2018-11-16 20:44:21');
INSERT INTO `t_bd_integral_detaill` VALUES ('33', '14', '3', '35', null, '2018-11-19 13:36:14', null, '2018-11-19 13:36:14');
INSERT INTO `t_bd_integral_detaill` VALUES ('34', '14', '3', '3', null, '2018-11-20 10:25:10', null, '2018-11-20 10:25:10');
INSERT INTO `t_bd_integral_detaill` VALUES ('35', '16', '3', '44', null, '2018-11-20 13:51:48', null, '2018-11-20 13:51:48');
INSERT INTO `t_bd_integral_detaill` VALUES ('36', '16', '3', '44', null, '2018-11-26 13:44:20', null, '2018-11-26 13:44:20');
INSERT INTO `t_bd_integral_detaill` VALUES ('37', '16', '3', '42', null, '2018-11-26 14:32:01', null, '2018-11-26 14:32:01');

-- ----------------------------
-- Table structure for t_bd_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_login_log`;
CREATE TABLE `t_bd_login_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip地址',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=utf8 COMMENT='会员登录记录表';

-- ----------------------------
-- Records of t_bd_login_log
-- ----------------------------
INSERT INTO `t_bd_login_log` VALUES ('1', '3', '127.0.0.1', null, '2018-11-12 14:47:57', null, '2018-11-12 14:47:57');
INSERT INTO `t_bd_login_log` VALUES ('2', '3', '127.0.0.1', null, '2018-11-12 15:11:40', null, '2018-11-12 15:11:40');
INSERT INTO `t_bd_login_log` VALUES ('3', '3', '127.0.0.1', null, '2018-11-12 15:50:14', null, '2018-11-12 15:50:14');
INSERT INTO `t_bd_login_log` VALUES ('4', '3', '127.0.0.1', null, '2018-11-13 13:57:56', null, '2018-11-13 13:57:56');
INSERT INTO `t_bd_login_log` VALUES ('5', '3', '127.0.0.1', null, '2018-11-13 17:23:49', null, '2018-11-13 17:23:49');
INSERT INTO `t_bd_login_log` VALUES ('6', '3', '127.0.0.1', null, '2018-11-13 19:59:10', null, '2018-11-13 19:59:10');
INSERT INTO `t_bd_login_log` VALUES ('7', '3', '127.0.0.1', null, '2018-11-13 21:33:32', null, '2018-11-13 21:33:32');
INSERT INTO `t_bd_login_log` VALUES ('8', '3', '127.0.0.1', null, '2018-11-13 21:33:34', null, '2018-11-13 21:33:34');
INSERT INTO `t_bd_login_log` VALUES ('9', '3', '127.0.0.1', null, '2018-11-13 21:33:35', null, '2018-11-13 21:33:35');
INSERT INTO `t_bd_login_log` VALUES ('10', '3', '127.0.0.1', null, '2018-11-13 21:33:36', null, '2018-11-13 21:33:36');
INSERT INTO `t_bd_login_log` VALUES ('11', '3', '127.0.0.1', null, '2018-11-13 21:33:42', null, '2018-11-13 21:33:42');
INSERT INTO `t_bd_login_log` VALUES ('12', '3', '127.0.0.1', null, '2018-11-13 21:33:44', null, '2018-11-13 21:33:44');
INSERT INTO `t_bd_login_log` VALUES ('13', '3', '127.0.0.1', null, '2018-11-13 21:33:44', null, '2018-11-13 21:33:44');
INSERT INTO `t_bd_login_log` VALUES ('14', '3', '127.0.0.1', null, '2018-11-13 21:33:46', null, '2018-11-13 21:33:46');
INSERT INTO `t_bd_login_log` VALUES ('15', '3', '127.0.0.1', null, '2018-11-13 21:33:46', null, '2018-11-13 21:33:46');
INSERT INTO `t_bd_login_log` VALUES ('16', '3', '127.0.0.1', null, '2018-11-14 08:50:26', null, '2018-11-14 08:50:26');
INSERT INTO `t_bd_login_log` VALUES ('17', '3', '127.0.0.1', null, '2018-11-14 08:51:01', null, '2018-11-14 08:51:01');
INSERT INTO `t_bd_login_log` VALUES ('18', '3', '127.0.0.1', null, '2018-11-14 15:03:33', null, '2018-11-14 15:03:33');
INSERT INTO `t_bd_login_log` VALUES ('19', '3', '127.0.0.1', null, '2018-11-14 18:12:41', null, '2018-11-14 18:12:41');
INSERT INTO `t_bd_login_log` VALUES ('20', '3', '127.0.0.1', null, '2018-11-14 18:12:44', null, '2018-11-14 18:12:44');
INSERT INTO `t_bd_login_log` VALUES ('21', '3', '127.0.0.1', null, '2018-11-14 18:12:45', null, '2018-11-14 18:12:45');
INSERT INTO `t_bd_login_log` VALUES ('22', '3', '127.0.0.1', null, '2018-11-14 18:12:47', null, '2018-11-14 18:12:47');
INSERT INTO `t_bd_login_log` VALUES ('23', '3', '127.0.0.1', null, '2018-11-14 18:12:47', null, '2018-11-14 18:12:47');
INSERT INTO `t_bd_login_log` VALUES ('24', '3', '127.0.0.1', null, '2018-11-14 18:12:47', null, '2018-11-14 18:12:47');
INSERT INTO `t_bd_login_log` VALUES ('25', '3', '127.0.0.1', null, '2018-11-14 18:12:48', null, '2018-11-14 18:12:48');
INSERT INTO `t_bd_login_log` VALUES ('26', '3', '127.0.0.1', null, '2018-11-14 18:12:49', null, '2018-11-14 18:12:49');
INSERT INTO `t_bd_login_log` VALUES ('27', '3', '127.0.0.1', null, '2018-11-14 18:12:50', null, '2018-11-14 18:12:50');
INSERT INTO `t_bd_login_log` VALUES ('28', '3', '127.0.0.1', null, '2018-11-14 18:12:50', null, '2018-11-14 18:12:50');
INSERT INTO `t_bd_login_log` VALUES ('29', '3', '127.0.0.1', null, '2018-11-14 18:12:52', null, '2018-11-14 18:12:52');
INSERT INTO `t_bd_login_log` VALUES ('30', '3', '127.0.0.1', null, '2018-11-14 18:13:05', null, '2018-11-14 18:13:05');
INSERT INTO `t_bd_login_log` VALUES ('31', '3', '127.0.0.1', null, '2018-11-14 18:13:06', null, '2018-11-14 18:13:06');
INSERT INTO `t_bd_login_log` VALUES ('32', '3', '127.0.0.1', null, '2018-11-14 18:36:41', null, '2018-11-14 18:36:41');
INSERT INTO `t_bd_login_log` VALUES ('33', '3', '127.0.0.1', null, '2018-11-14 18:36:42', null, '2018-11-14 18:36:42');
INSERT INTO `t_bd_login_log` VALUES ('34', '3', '127.0.0.1', null, '2018-11-14 18:36:43', null, '2018-11-14 18:36:43');
INSERT INTO `t_bd_login_log` VALUES ('35', '3', '127.0.0.1', null, '2018-11-14 18:36:43', null, '2018-11-14 18:36:43');
INSERT INTO `t_bd_login_log` VALUES ('36', '3', '127.0.0.1', null, '2018-11-14 18:36:44', null, '2018-11-14 18:36:44');
INSERT INTO `t_bd_login_log` VALUES ('37', '3', '127.0.0.1', null, '2018-11-14 18:36:44', null, '2018-11-14 18:36:44');
INSERT INTO `t_bd_login_log` VALUES ('38', '3', '127.0.0.1', null, '2018-11-14 18:36:45', null, '2018-11-14 18:36:45');
INSERT INTO `t_bd_login_log` VALUES ('39', '3', '127.0.0.1', null, '2018-11-14 18:36:45', null, '2018-11-14 18:36:45');
INSERT INTO `t_bd_login_log` VALUES ('40', '3', '127.0.0.1', null, '2018-11-14 18:36:46', null, '2018-11-14 18:36:46');
INSERT INTO `t_bd_login_log` VALUES ('41', '3', '127.0.0.1', null, '2018-11-14 18:36:48', null, '2018-11-14 18:36:48');
INSERT INTO `t_bd_login_log` VALUES ('42', '3', '127.0.0.1', null, '2018-11-14 18:36:48', null, '2018-11-14 18:36:48');
INSERT INTO `t_bd_login_log` VALUES ('43', '3', '127.0.0.1', null, '2018-11-14 18:36:49', null, '2018-11-14 18:36:49');
INSERT INTO `t_bd_login_log` VALUES ('44', '3', '127.0.0.1', null, '2018-11-14 18:36:49', null, '2018-11-14 18:36:49');
INSERT INTO `t_bd_login_log` VALUES ('45', '3', '127.0.0.1', null, '2018-11-14 18:36:50', null, '2018-11-14 18:36:50');
INSERT INTO `t_bd_login_log` VALUES ('46', '3', '127.0.0.1', null, '2018-11-14 18:36:50', null, '2018-11-14 18:36:50');
INSERT INTO `t_bd_login_log` VALUES ('47', '3', '127.0.0.1', null, '2018-11-14 18:36:51', null, '2018-11-14 18:36:51');
INSERT INTO `t_bd_login_log` VALUES ('48', '3', '127.0.0.1', null, '2018-11-14 18:36:51', null, '2018-11-14 18:36:51');
INSERT INTO `t_bd_login_log` VALUES ('49', '3', '127.0.0.1', null, '2018-11-14 18:36:51', null, '2018-11-14 18:36:51');
INSERT INTO `t_bd_login_log` VALUES ('50', '3', '127.0.0.1', null, '2018-11-14 18:37:02', null, '2018-11-14 18:37:02');
INSERT INTO `t_bd_login_log` VALUES ('51', '3', '127.0.0.1', null, '2018-11-14 18:37:03', null, '2018-11-14 18:37:03');
INSERT INTO `t_bd_login_log` VALUES ('52', '3', '127.0.0.1', null, '2018-11-14 18:37:03', null, '2018-11-14 18:37:03');
INSERT INTO `t_bd_login_log` VALUES ('53', '3', '127.0.0.1', null, '2018-11-14 18:37:05', null, '2018-11-14 18:37:05');
INSERT INTO `t_bd_login_log` VALUES ('54', '3', '127.0.0.1', null, '2018-11-14 18:37:11', null, '2018-11-14 18:37:11');
INSERT INTO `t_bd_login_log` VALUES ('55', '3', '127.0.0.1', null, '2018-11-14 18:37:16', null, '2018-11-14 18:37:16');
INSERT INTO `t_bd_login_log` VALUES ('56', '3', '127.0.0.1', null, '2018-11-14 18:37:16', null, '2018-11-14 18:37:16');
INSERT INTO `t_bd_login_log` VALUES ('57', '3', '127.0.0.1', null, '2018-11-14 18:37:17', null, '2018-11-14 18:37:17');
INSERT INTO `t_bd_login_log` VALUES ('58', '3', '127.0.0.1', null, '2018-11-14 18:37:18', null, '2018-11-14 18:37:18');
INSERT INTO `t_bd_login_log` VALUES ('59', '3', '127.0.0.1', null, '2018-11-14 18:37:26', null, '2018-11-14 18:37:26');
INSERT INTO `t_bd_login_log` VALUES ('60', '3', '127.0.0.1', null, '2018-11-14 18:37:27', null, '2018-11-14 18:37:27');
INSERT INTO `t_bd_login_log` VALUES ('61', '3', '127.0.0.1', null, '2018-11-14 18:45:41', null, '2018-11-14 18:45:41');
INSERT INTO `t_bd_login_log` VALUES ('62', '3', '127.0.0.1', null, '2018-11-14 18:45:43', null, '2018-11-14 18:45:43');
INSERT INTO `t_bd_login_log` VALUES ('63', '3', '127.0.0.1', null, '2018-11-14 18:45:44', null, '2018-11-14 18:45:44');
INSERT INTO `t_bd_login_log` VALUES ('64', '3', '127.0.0.1', null, '2018-11-14 18:45:44', null, '2018-11-14 18:45:44');
INSERT INTO `t_bd_login_log` VALUES ('65', '3', '127.0.0.1', null, '2018-11-14 18:45:45', null, '2018-11-14 18:45:45');
INSERT INTO `t_bd_login_log` VALUES ('66', '3', '127.0.0.1', null, '2018-11-14 18:45:46', null, '2018-11-14 18:45:46');
INSERT INTO `t_bd_login_log` VALUES ('67', '3', '127.0.0.1', null, '2018-11-14 18:45:46', null, '2018-11-14 18:45:46');
INSERT INTO `t_bd_login_log` VALUES ('68', '3', '127.0.0.1', null, '2018-11-14 18:45:46', null, '2018-11-14 18:45:46');
INSERT INTO `t_bd_login_log` VALUES ('69', '3', '127.0.0.1', null, '2018-11-14 18:45:46', null, '2018-11-14 18:45:46');
INSERT INTO `t_bd_login_log` VALUES ('70', '3', '127.0.0.1', null, '2018-11-14 18:45:46', null, '2018-11-14 18:45:46');
INSERT INTO `t_bd_login_log` VALUES ('71', '3', '127.0.0.1', null, '2018-11-14 18:45:47', null, '2018-11-14 18:45:47');
INSERT INTO `t_bd_login_log` VALUES ('72', '3', '127.0.0.1', null, '2018-11-14 18:45:48', null, '2018-11-14 18:45:48');
INSERT INTO `t_bd_login_log` VALUES ('73', '3', '127.0.0.1', null, '2018-11-14 18:45:49', null, '2018-11-14 18:45:49');
INSERT INTO `t_bd_login_log` VALUES ('74', '3', '127.0.0.1', null, '2018-11-14 18:45:49', null, '2018-11-14 18:45:49');
INSERT INTO `t_bd_login_log` VALUES ('75', '3', '127.0.0.1', null, '2018-11-14 18:45:50', null, '2018-11-14 18:45:50');
INSERT INTO `t_bd_login_log` VALUES ('76', '3', '127.0.0.1', null, '2018-11-14 18:45:50', null, '2018-11-14 18:45:50');
INSERT INTO `t_bd_login_log` VALUES ('77', '3', '127.0.0.1', null, '2018-11-14 18:45:51', null, '2018-11-14 18:45:51');
INSERT INTO `t_bd_login_log` VALUES ('78', '3', '127.0.0.1', null, '2018-11-14 18:45:52', null, '2018-11-14 18:45:52');
INSERT INTO `t_bd_login_log` VALUES ('79', '3', '127.0.0.1', null, '2018-11-14 18:45:52', null, '2018-11-14 18:45:52');
INSERT INTO `t_bd_login_log` VALUES ('80', '3', '127.0.0.1', null, '2018-11-14 18:45:53', null, '2018-11-14 18:45:53');
INSERT INTO `t_bd_login_log` VALUES ('81', '3', '127.0.0.1', null, '2018-11-14 18:45:54', null, '2018-11-14 18:45:54');
INSERT INTO `t_bd_login_log` VALUES ('82', '3', '127.0.0.1', null, '2018-11-14 18:45:55', null, '2018-11-14 18:45:55');
INSERT INTO `t_bd_login_log` VALUES ('83', '3', '127.0.0.1', null, '2018-11-14 18:45:57', null, '2018-11-14 18:45:57');
INSERT INTO `t_bd_login_log` VALUES ('84', '3', '127.0.0.1', null, '2018-11-14 18:45:58', null, '2018-11-14 18:45:58');
INSERT INTO `t_bd_login_log` VALUES ('85', '3', '127.0.0.1', null, '2018-11-14 18:45:58', null, '2018-11-14 18:45:58');
INSERT INTO `t_bd_login_log` VALUES ('86', '3', '127.0.0.1', null, '2018-11-14 18:45:59', null, '2018-11-14 18:45:59');
INSERT INTO `t_bd_login_log` VALUES ('87', '3', '127.0.0.1', null, '2018-11-14 18:45:59', null, '2018-11-14 18:45:59');
INSERT INTO `t_bd_login_log` VALUES ('88', '3', '127.0.0.1', null, '2018-11-14 18:46:00', null, '2018-11-14 18:46:00');
INSERT INTO `t_bd_login_log` VALUES ('89', '3', '127.0.0.1', null, '2018-11-14 18:46:00', null, '2018-11-14 18:46:00');
INSERT INTO `t_bd_login_log` VALUES ('90', '3', '127.0.0.1', null, '2018-11-14 18:46:01', null, '2018-11-14 18:46:01');
INSERT INTO `t_bd_login_log` VALUES ('91', '3', '127.0.0.1', null, '2018-11-14 18:46:02', null, '2018-11-14 18:46:02');
INSERT INTO `t_bd_login_log` VALUES ('92', '3', '127.0.0.1', null, '2018-11-14 18:46:03', null, '2018-11-14 18:46:03');
INSERT INTO `t_bd_login_log` VALUES ('93', '3', '127.0.0.1', null, '2018-11-14 18:50:39', null, '2018-11-14 18:50:39');
INSERT INTO `t_bd_login_log` VALUES ('94', '3', '127.0.0.1', null, '2018-11-14 18:50:39', null, '2018-11-14 18:50:39');
INSERT INTO `t_bd_login_log` VALUES ('95', '3', '127.0.0.1', null, '2018-11-14 18:50:39', null, '2018-11-14 18:50:39');
INSERT INTO `t_bd_login_log` VALUES ('96', '3', '127.0.0.1', null, '2018-11-14 18:50:51', null, '2018-11-14 18:50:51');
INSERT INTO `t_bd_login_log` VALUES ('97', '3', '127.0.0.1', null, '2018-11-14 18:50:51', null, '2018-11-14 18:50:51');
INSERT INTO `t_bd_login_log` VALUES ('98', '3', '127.0.0.1', null, '2018-11-14 18:54:07', null, '2018-11-14 18:54:07');
INSERT INTO `t_bd_login_log` VALUES ('99', '3', '127.0.0.1', null, '2018-11-14 18:54:09', null, '2018-11-14 18:54:09');
INSERT INTO `t_bd_login_log` VALUES ('100', '3', '127.0.0.1', null, '2018-11-14 18:54:10', null, '2018-11-14 18:54:10');
INSERT INTO `t_bd_login_log` VALUES ('101', '3', '127.0.0.1', null, '2018-11-14 18:54:20', null, '2018-11-14 18:54:20');
INSERT INTO `t_bd_login_log` VALUES ('102', '3', '127.0.0.1', null, '2018-11-14 18:54:20', null, '2018-11-14 18:54:20');
INSERT INTO `t_bd_login_log` VALUES ('103', '3', '127.0.0.1', null, '2018-11-14 18:54:21', null, '2018-11-14 18:54:21');
INSERT INTO `t_bd_login_log` VALUES ('104', '3', '127.0.0.1', null, '2018-11-14 18:54:21', null, '2018-11-14 18:54:21');
INSERT INTO `t_bd_login_log` VALUES ('105', '3', '127.0.0.1', null, '2018-11-14 18:54:21', null, '2018-11-14 18:54:21');
INSERT INTO `t_bd_login_log` VALUES ('106', '3', '127.0.0.1', null, '2018-11-14 18:54:21', null, '2018-11-14 18:54:21');
INSERT INTO `t_bd_login_log` VALUES ('107', '3', '127.0.0.1', null, '2018-11-14 18:54:21', null, '2018-11-14 18:54:21');
INSERT INTO `t_bd_login_log` VALUES ('108', '3', '127.0.0.1', null, '2018-11-14 18:54:21', null, '2018-11-14 18:54:21');
INSERT INTO `t_bd_login_log` VALUES ('109', '3', '127.0.0.1', null, '2018-11-14 18:54:22', null, '2018-11-14 18:54:22');
INSERT INTO `t_bd_login_log` VALUES ('110', '3', '127.0.0.1', null, '2018-11-14 18:54:22', null, '2018-11-14 18:54:22');
INSERT INTO `t_bd_login_log` VALUES ('111', '3', '127.0.0.1', null, '2018-11-14 18:54:22', null, '2018-11-14 18:54:22');
INSERT INTO `t_bd_login_log` VALUES ('112', '3', '127.0.0.1', null, '2018-11-14 18:54:22', null, '2018-11-14 18:54:22');
INSERT INTO `t_bd_login_log` VALUES ('113', '3', '127.0.0.1', null, '2018-11-14 18:54:22', null, '2018-11-14 18:54:22');
INSERT INTO `t_bd_login_log` VALUES ('114', '3', '127.0.0.1', null, '2018-11-14 18:54:22', null, '2018-11-14 18:54:22');
INSERT INTO `t_bd_login_log` VALUES ('115', '3', '127.0.0.1', null, '2018-11-14 18:54:22', null, '2018-11-14 18:54:22');
INSERT INTO `t_bd_login_log` VALUES ('116', '3', '127.0.0.1', null, '2018-11-14 18:54:22', null, '2018-11-14 18:54:22');
INSERT INTO `t_bd_login_log` VALUES ('117', '3', '127.0.0.1', null, '2018-11-14 18:54:22', null, '2018-11-14 18:54:22');
INSERT INTO `t_bd_login_log` VALUES ('118', '3', '127.0.0.1', null, '2018-11-14 18:54:23', null, '2018-11-14 18:54:23');
INSERT INTO `t_bd_login_log` VALUES ('119', '3', '127.0.0.1', null, '2018-11-14 18:54:23', null, '2018-11-14 18:54:23');
INSERT INTO `t_bd_login_log` VALUES ('120', '3', '127.0.0.1', null, '2018-11-14 18:54:23', null, '2018-11-14 18:54:23');
INSERT INTO `t_bd_login_log` VALUES ('121', '3', '127.0.0.1', null, '2018-11-14 18:54:23', null, '2018-11-14 18:54:23');
INSERT INTO `t_bd_login_log` VALUES ('122', '3', '127.0.0.1', null, '2018-11-14 18:54:23', null, '2018-11-14 18:54:23');
INSERT INTO `t_bd_login_log` VALUES ('123', '3', '127.0.0.1', null, '2018-11-14 18:54:23', null, '2018-11-14 18:54:23');
INSERT INTO `t_bd_login_log` VALUES ('124', '3', '127.0.0.1', null, '2018-11-14 18:54:23', null, '2018-11-14 18:54:23');
INSERT INTO `t_bd_login_log` VALUES ('125', '3', '127.0.0.1', null, '2018-11-14 18:54:24', null, '2018-11-14 18:54:24');
INSERT INTO `t_bd_login_log` VALUES ('126', '3', '127.0.0.1', null, '2018-11-14 18:54:24', null, '2018-11-14 18:54:24');
INSERT INTO `t_bd_login_log` VALUES ('127', '3', '127.0.0.1', null, '2018-11-14 18:54:24', null, '2018-11-14 18:54:24');
INSERT INTO `t_bd_login_log` VALUES ('128', '3', '127.0.0.1', null, '2018-11-14 18:54:24', null, '2018-11-14 18:54:24');
INSERT INTO `t_bd_login_log` VALUES ('129', '3', '127.0.0.1', null, '2018-11-14 18:54:24', null, '2018-11-14 18:54:24');
INSERT INTO `t_bd_login_log` VALUES ('130', '3', '127.0.0.1', null, '2018-11-14 18:54:41', null, '2018-11-14 18:54:41');
INSERT INTO `t_bd_login_log` VALUES ('131', '3', '127.0.0.1', null, '2018-11-14 18:54:41', null, '2018-11-14 18:54:41');
INSERT INTO `t_bd_login_log` VALUES ('132', '3', '127.0.0.1', null, '2018-11-14 18:54:41', null, '2018-11-14 18:54:41');
INSERT INTO `t_bd_login_log` VALUES ('133', '3', '127.0.0.1', null, '2018-11-14 18:54:41', null, '2018-11-14 18:54:41');
INSERT INTO `t_bd_login_log` VALUES ('134', '3', '127.0.0.1', null, '2018-11-14 18:54:41', null, '2018-11-14 18:54:41');
INSERT INTO `t_bd_login_log` VALUES ('135', '3', '127.0.0.1', null, '2018-11-14 18:54:42', null, '2018-11-14 18:54:42');
INSERT INTO `t_bd_login_log` VALUES ('136', '3', '127.0.0.1', null, '2018-11-14 18:54:42', null, '2018-11-14 18:54:42');
INSERT INTO `t_bd_login_log` VALUES ('137', '3', '127.0.0.1', null, '2018-11-14 18:54:43', null, '2018-11-14 18:54:43');
INSERT INTO `t_bd_login_log` VALUES ('138', '3', '127.0.0.1', null, '2018-11-14 18:54:43', null, '2018-11-14 18:54:43');
INSERT INTO `t_bd_login_log` VALUES ('139', '3', '127.0.0.1', null, '2018-11-14 18:54:44', null, '2018-11-14 18:54:44');
INSERT INTO `t_bd_login_log` VALUES ('140', '3', '127.0.0.1', null, '2018-11-14 18:54:44', null, '2018-11-14 18:54:44');
INSERT INTO `t_bd_login_log` VALUES ('141', '3', '127.0.0.1', null, '2018-11-14 18:54:44', null, '2018-11-14 18:54:44');
INSERT INTO `t_bd_login_log` VALUES ('142', '3', '127.0.0.1', null, '2018-11-14 18:54:44', null, '2018-11-14 18:54:44');
INSERT INTO `t_bd_login_log` VALUES ('143', '3', '127.0.0.1', null, '2018-11-14 18:54:47', null, '2018-11-14 18:54:47');
INSERT INTO `t_bd_login_log` VALUES ('144', '3', '127.0.0.1', null, '2018-11-14 18:55:03', null, '2018-11-14 18:55:03');
INSERT INTO `t_bd_login_log` VALUES ('145', '3', '127.0.0.1', null, '2018-11-14 18:55:03', null, '2018-11-14 18:55:03');
INSERT INTO `t_bd_login_log` VALUES ('146', '3', '127.0.0.1', null, '2018-11-14 18:55:04', null, '2018-11-14 18:55:04');
INSERT INTO `t_bd_login_log` VALUES ('147', '3', '127.0.0.1', null, '2018-11-14 18:55:04', null, '2018-11-14 18:55:04');
INSERT INTO `t_bd_login_log` VALUES ('148', '3', '127.0.0.1', null, '2018-11-14 18:55:05', null, '2018-11-14 18:55:05');
INSERT INTO `t_bd_login_log` VALUES ('149', '3', '127.0.0.1', null, '2018-11-14 18:55:05', null, '2018-11-14 18:55:05');
INSERT INTO `t_bd_login_log` VALUES ('150', '3', '127.0.0.1', null, '2018-11-14 18:55:06', null, '2018-11-14 18:55:06');
INSERT INTO `t_bd_login_log` VALUES ('151', '3', '127.0.0.1', null, '2018-11-14 18:55:07', null, '2018-11-14 18:55:07');
INSERT INTO `t_bd_login_log` VALUES ('152', '3', '127.0.0.1', null, '2018-11-14 18:55:07', null, '2018-11-14 18:55:07');
INSERT INTO `t_bd_login_log` VALUES ('153', '3', '127.0.0.1', null, '2018-11-14 18:55:07', null, '2018-11-14 18:55:07');
INSERT INTO `t_bd_login_log` VALUES ('154', '3', '127.0.0.1', null, '2018-11-14 18:55:31', null, '2018-11-14 18:55:31');
INSERT INTO `t_bd_login_log` VALUES ('155', '3', '127.0.0.1', null, '2018-11-14 18:55:31', null, '2018-11-14 18:55:31');
INSERT INTO `t_bd_login_log` VALUES ('156', '3', '127.0.0.1', null, '2018-11-14 18:58:16', null, '2018-11-14 18:58:16');
INSERT INTO `t_bd_login_log` VALUES ('157', '3', '127.0.0.1', null, '2018-11-14 19:02:47', null, '2018-11-14 19:02:47');
INSERT INTO `t_bd_login_log` VALUES ('158', '3', '127.0.0.1', null, '2018-11-14 19:02:51', null, '2018-11-14 19:02:51');
INSERT INTO `t_bd_login_log` VALUES ('159', '3', '127.0.0.1', null, '2018-11-14 19:02:55', null, '2018-11-14 19:02:55');
INSERT INTO `t_bd_login_log` VALUES ('160', '3', '127.0.0.1', null, '2018-11-14 19:03:03', null, '2018-11-14 19:03:03');
INSERT INTO `t_bd_login_log` VALUES ('161', '3', '127.0.0.1', null, '2018-11-14 19:03:05', null, '2018-11-14 19:03:05');
INSERT INTO `t_bd_login_log` VALUES ('162', '3', '127.0.0.1', null, '2018-11-14 19:03:06', null, '2018-11-14 19:03:06');
INSERT INTO `t_bd_login_log` VALUES ('163', '3', '127.0.0.1', null, '2018-11-14 19:03:07', null, '2018-11-14 19:03:07');
INSERT INTO `t_bd_login_log` VALUES ('164', '3', '127.0.0.1', null, '2018-11-14 19:03:09', null, '2018-11-14 19:03:09');
INSERT INTO `t_bd_login_log` VALUES ('165', '3', '127.0.0.1', null, '2018-11-14 19:03:10', null, '2018-11-14 19:03:10');
INSERT INTO `t_bd_login_log` VALUES ('166', '3', '127.0.0.1', null, '2018-11-14 19:03:11', null, '2018-11-14 19:03:11');
INSERT INTO `t_bd_login_log` VALUES ('167', '3', '127.0.0.1', null, '2018-11-14 19:09:23', null, '2018-11-14 19:09:23');
INSERT INTO `t_bd_login_log` VALUES ('168', '3', '127.0.0.1', null, '2018-11-14 19:09:24', null, '2018-11-14 19:09:24');
INSERT INTO `t_bd_login_log` VALUES ('169', '3', '127.0.0.1', null, '2018-11-14 19:09:25', null, '2018-11-14 19:09:25');
INSERT INTO `t_bd_login_log` VALUES ('170', '3', '127.0.0.1', null, '2018-11-14 19:09:26', null, '2018-11-14 19:09:26');
INSERT INTO `t_bd_login_log` VALUES ('171', '3', '127.0.0.1', null, '2018-11-14 19:09:26', null, '2018-11-14 19:09:26');
INSERT INTO `t_bd_login_log` VALUES ('172', '3', '127.0.0.1', null, '2018-11-14 19:09:26', null, '2018-11-14 19:09:26');
INSERT INTO `t_bd_login_log` VALUES ('173', '3', '127.0.0.1', null, '2018-11-14 19:09:26', null, '2018-11-14 19:09:26');
INSERT INTO `t_bd_login_log` VALUES ('174', '3', '127.0.0.1', null, '2018-11-14 19:09:26', null, '2018-11-14 19:09:26');
INSERT INTO `t_bd_login_log` VALUES ('175', '3', '127.0.0.1', null, '2018-11-14 19:09:27', null, '2018-11-14 19:09:27');
INSERT INTO `t_bd_login_log` VALUES ('176', '3', '127.0.0.1', null, '2018-11-14 19:09:27', null, '2018-11-14 19:09:27');
INSERT INTO `t_bd_login_log` VALUES ('177', '3', '127.0.0.1', null, '2018-11-14 19:09:27', null, '2018-11-14 19:09:27');
INSERT INTO `t_bd_login_log` VALUES ('178', '3', '127.0.0.1', null, '2018-11-14 19:09:27', null, '2018-11-14 19:09:27');
INSERT INTO `t_bd_login_log` VALUES ('179', '3', '127.0.0.1', null, '2018-11-14 19:09:27', null, '2018-11-14 19:09:27');
INSERT INTO `t_bd_login_log` VALUES ('180', '3', '127.0.0.1', null, '2018-11-14 19:09:28', null, '2018-11-14 19:09:28');
INSERT INTO `t_bd_login_log` VALUES ('181', '3', '127.0.0.1', null, '2018-11-14 19:15:59', null, '2018-11-14 19:15:59');
INSERT INTO `t_bd_login_log` VALUES ('182', '3', '127.0.0.1', null, '2018-11-14 19:16:03', null, '2018-11-14 19:16:03');
INSERT INTO `t_bd_login_log` VALUES ('183', '3', '127.0.0.1', null, '2018-11-14 19:16:04', null, '2018-11-14 19:16:04');
INSERT INTO `t_bd_login_log` VALUES ('184', '3', '127.0.0.1', null, '2018-11-14 19:16:05', null, '2018-11-14 19:16:05');
INSERT INTO `t_bd_login_log` VALUES ('185', '3', '127.0.0.1', null, '2018-11-14 19:16:07', null, '2018-11-14 19:16:07');
INSERT INTO `t_bd_login_log` VALUES ('186', '3', '127.0.0.1', null, '2018-11-14 19:16:08', null, '2018-11-14 19:16:08');
INSERT INTO `t_bd_login_log` VALUES ('187', '3', '127.0.0.1', null, '2018-11-14 19:16:09', null, '2018-11-14 19:16:09');
INSERT INTO `t_bd_login_log` VALUES ('188', '3', '127.0.0.1', null, '2018-11-14 19:16:12', null, '2018-11-14 19:16:12');
INSERT INTO `t_bd_login_log` VALUES ('189', '3', '127.0.0.1', null, '2018-11-14 19:16:26', null, '2018-11-14 19:16:26');
INSERT INTO `t_bd_login_log` VALUES ('190', '3', '127.0.0.1', null, '2018-11-14 19:20:21', null, '2018-11-14 19:20:21');
INSERT INTO `t_bd_login_log` VALUES ('191', '3', '127.0.0.1', null, '2018-11-14 19:20:23', null, '2018-11-14 19:20:23');
INSERT INTO `t_bd_login_log` VALUES ('192', '3', '127.0.0.1', null, '2018-11-14 19:20:23', null, '2018-11-14 19:20:23');
INSERT INTO `t_bd_login_log` VALUES ('193', '3', '127.0.0.1', null, '2018-11-14 19:20:24', null, '2018-11-14 19:20:24');
INSERT INTO `t_bd_login_log` VALUES ('194', '3', '127.0.0.1', null, '2018-11-14 19:20:25', null, '2018-11-14 19:20:25');
INSERT INTO `t_bd_login_log` VALUES ('195', '3', '127.0.0.1', null, '2018-11-14 19:20:25', null, '2018-11-14 19:20:25');
INSERT INTO `t_bd_login_log` VALUES ('196', '3', '127.0.0.1', null, '2018-11-14 19:20:25', null, '2018-11-14 19:20:25');
INSERT INTO `t_bd_login_log` VALUES ('197', '3', '127.0.0.1', null, '2018-11-14 19:20:25', null, '2018-11-14 19:20:25');
INSERT INTO `t_bd_login_log` VALUES ('198', '3', '127.0.0.1', null, '2018-11-14 19:20:25', null, '2018-11-14 19:20:25');
INSERT INTO `t_bd_login_log` VALUES ('199', '3', '127.0.0.1', null, '2018-11-14 19:23:00', null, '2018-11-14 19:23:00');
INSERT INTO `t_bd_login_log` VALUES ('200', '3', '127.0.0.1', null, '2018-11-14 19:23:16', null, '2018-11-14 19:23:16');
INSERT INTO `t_bd_login_log` VALUES ('201', '3', '127.0.0.1', null, '2018-11-14 19:23:22', null, '2018-11-14 19:23:22');
INSERT INTO `t_bd_login_log` VALUES ('202', '3', '127.0.0.1', null, '2018-11-14 19:23:29', null, '2018-11-14 19:23:29');
INSERT INTO `t_bd_login_log` VALUES ('203', '3', '127.0.0.1', null, '2018-11-14 19:25:18', null, '2018-11-14 19:25:18');
INSERT INTO `t_bd_login_log` VALUES ('204', '3', '127.0.0.1', null, '2018-11-14 19:25:21', null, '2018-11-14 19:25:21');
INSERT INTO `t_bd_login_log` VALUES ('205', '3', '127.0.0.1', null, '2018-11-14 19:25:24', null, '2018-11-14 19:25:24');
INSERT INTO `t_bd_login_log` VALUES ('206', '3', '127.0.0.1', null, '2018-11-14 19:25:29', null, '2018-11-14 19:25:29');
INSERT INTO `t_bd_login_log` VALUES ('207', '3', '127.0.0.1', null, '2018-11-14 19:25:31', null, '2018-11-14 19:25:31');
INSERT INTO `t_bd_login_log` VALUES ('208', '3', '127.0.0.1', null, '2018-11-14 19:27:04', null, '2018-11-14 19:27:04');
INSERT INTO `t_bd_login_log` VALUES ('209', '3', '127.0.0.1', null, '2018-11-14 19:27:06', null, '2018-11-14 19:27:06');
INSERT INTO `t_bd_login_log` VALUES ('210', '3', '127.0.0.1', null, '2018-11-14 19:27:07', null, '2018-11-14 19:27:07');
INSERT INTO `t_bd_login_log` VALUES ('211', '3', '127.0.0.1', null, '2018-11-14 19:28:22', null, '2018-11-14 19:28:22');
INSERT INTO `t_bd_login_log` VALUES ('212', '3', '127.0.0.1', null, '2018-11-14 19:28:25', null, '2018-11-14 19:28:25');
INSERT INTO `t_bd_login_log` VALUES ('213', '3', '127.0.0.1', null, '2018-11-14 19:28:27', null, '2018-11-14 19:28:27');
INSERT INTO `t_bd_login_log` VALUES ('214', '3', '127.0.0.1', null, '2018-11-14 19:28:28', null, '2018-11-14 19:28:28');
INSERT INTO `t_bd_login_log` VALUES ('215', '3', '127.0.0.1', null, '2018-11-14 19:28:29', null, '2018-11-14 19:28:29');
INSERT INTO `t_bd_login_log` VALUES ('216', '3', '127.0.0.1', null, '2018-11-14 19:28:31', null, '2018-11-14 19:28:31');
INSERT INTO `t_bd_login_log` VALUES ('217', '3', '127.0.0.1', null, '2018-11-14 19:35:41', null, '2018-11-14 19:35:41');
INSERT INTO `t_bd_login_log` VALUES ('218', '3', '127.0.0.1', null, '2018-11-14 19:40:57', null, '2018-11-14 19:40:57');
INSERT INTO `t_bd_login_log` VALUES ('219', '3', '127.0.0.1', null, '2018-11-14 19:41:07', null, '2018-11-14 19:41:07');
INSERT INTO `t_bd_login_log` VALUES ('220', '3', '127.0.0.1', null, '2018-11-14 19:41:10', null, '2018-11-14 19:41:10');
INSERT INTO `t_bd_login_log` VALUES ('221', '3', '127.0.0.1', null, '2018-11-14 19:41:19', null, '2018-11-14 19:41:19');
INSERT INTO `t_bd_login_log` VALUES ('222', '3', '127.0.0.1', null, '2018-11-14 19:41:27', null, '2018-11-14 19:41:27');
INSERT INTO `t_bd_login_log` VALUES ('223', '3', '127.0.0.1', null, '2018-11-14 19:41:32', null, '2018-11-14 19:41:32');
INSERT INTO `t_bd_login_log` VALUES ('224', '3', '127.0.0.1', null, '2018-11-14 19:41:35', null, '2018-11-14 19:41:35');
INSERT INTO `t_bd_login_log` VALUES ('225', '3', '127.0.0.1', null, '2018-11-14 19:41:38', null, '2018-11-14 19:41:38');
INSERT INTO `t_bd_login_log` VALUES ('226', '3', '127.0.0.1', null, '2018-11-14 19:41:47', null, '2018-11-14 19:41:47');
INSERT INTO `t_bd_login_log` VALUES ('227', '3', '127.0.0.1', null, '2018-11-14 19:41:50', null, '2018-11-14 19:41:50');
INSERT INTO `t_bd_login_log` VALUES ('228', '3', '127.0.0.1', null, '2018-11-15 09:25:29', null, '2018-11-15 09:25:29');
INSERT INTO `t_bd_login_log` VALUES ('229', '3', '127.0.0.1', null, '2018-11-15 09:51:36', null, '2018-11-15 09:51:36');
INSERT INTO `t_bd_login_log` VALUES ('230', '3', '127.0.0.1', null, '2018-11-15 10:03:14', null, '2018-11-15 10:03:14');
INSERT INTO `t_bd_login_log` VALUES ('231', '3', '127.0.0.1', null, '2018-11-15 13:55:53', null, '2018-11-15 13:55:53');
INSERT INTO `t_bd_login_log` VALUES ('232', '3', '127.0.0.1', null, '2018-11-15 14:05:20', null, '2018-11-15 14:05:20');
INSERT INTO `t_bd_login_log` VALUES ('233', '3', '127.0.0.1', null, '2018-11-15 18:22:53', null, '2018-11-15 18:22:53');
INSERT INTO `t_bd_login_log` VALUES ('234', '3', '127.0.0.1', null, '2018-11-19 09:07:07', null, '2018-11-19 09:07:07');
INSERT INTO `t_bd_login_log` VALUES ('235', '3', '127.0.0.1', null, '2018-11-20 15:54:53', null, '2018-11-20 15:54:53');
INSERT INTO `t_bd_login_log` VALUES ('236', '3', '10.112.11.164', null, '2018-11-26 11:26:00', null, '2018-11-26 11:26:00');
INSERT INTO `t_bd_login_log` VALUES ('237', '3', '10.112.11.26', null, '2018-11-26 11:28:56', null, '2018-11-26 11:28:56');
INSERT INTO `t_bd_login_log` VALUES ('238', '3', '10.112.11.164', null, '2018-11-26 13:43:00', null, '2018-11-26 13:43:00');
INSERT INTO `t_bd_login_log` VALUES ('239', '3', '192.168.56.1', null, '2018-11-26 14:52:14', null, '2018-11-26 14:52:14');
INSERT INTO `t_bd_login_log` VALUES ('240', '3', '192.168.72.1', null, '2018-12-19 11:20:33', null, '2018-12-19 11:20:33');
INSERT INTO `t_bd_login_log` VALUES ('241', '3', '192.168.72.1', null, '2018-12-19 11:20:37', null, '2018-12-19 11:20:37');

-- ----------------------------
-- Table structure for t_bd_member
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_member`;
CREATE TABLE `t_bd_member` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `membername` varchar(32) DEFAULT NULL COMMENT '会员名',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(300) NOT NULL COMMENT '密码',
  `telephone` char(11) NOT NULL COMMENT '用户电话',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `growth` bigint(20) DEFAULT NULL COMMENT '成长值',
  `integral` bigint(20) DEFAULT NULL COMMENT '积分',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `balance` decimal(20,4) DEFAULT NULL COMMENT '余额',
  `image_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
-- Records of t_bd_member
-- ----------------------------
INSERT INTO `t_bd_member` VALUES ('1', null, null, 'e10adc3949ba59abbe56e057f20f883e', '17777777777', null, '317', '166', null, '0.0000', null, null, '2018-11-12 14:29:19', null, '2018-11-20 16:18:23');
INSERT INTO `t_bd_member` VALUES ('2', null, null, 'e10adc3949ba59abbe56e057f20f883e', '16666666666', null, '0', '0', null, '0.0000', null, null, '2018-11-12 14:37:10', null, '2018-11-12 14:37:10');
INSERT INTO `t_bd_member` VALUES ('3', null, '123456789@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '18888888888', '0', '105', '41', '测试', '20.1000', '', null, '2018-11-12 14:47:45', '3', '2018-11-26 11:26:04');

-- ----------------------------
-- Table structure for t_bd_member_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_member_grade`;
CREATE TABLE `t_bd_member_grade` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(32) NOT NULL COMMENT '等级名称',
  `min_growth` bigint(20) NOT NULL COMMENT '等级下限',
  `max_growth` bigint(20) NOT NULL COMMENT '等级上限',
  `discount` float(3,2) NOT NULL COMMENT '折扣(以 x.xx 的形式使用，直接与原价相乘)',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='会员等级表';

-- ----------------------------
-- Records of t_bd_member_grade
-- ----------------------------
INSERT INTO `t_bd_member_grade` VALUES ('1', '青铜', '0', '1000', '1.00', null, '2018-10-29 22:33:55', '1', '2018-11-13 20:06:22');
INSERT INTO `t_bd_member_grade` VALUES ('2', '白银', '1000', '2500', '0.95', null, '2018-10-29 22:33:55', '1', '2018-10-30 07:13:58');
INSERT INTO `t_bd_member_grade` VALUES ('3', '黄金', '2500', '5000', '0.90', null, '2018-10-29 22:33:56', null, '2018-10-29 22:33:56');
INSERT INTO `t_bd_member_grade` VALUES ('4', '铂金', '5000', '8000', '0.85', null, '2018-10-29 22:33:56', null, '2018-10-29 22:33:56');
INSERT INTO `t_bd_member_grade` VALUES ('5', '钻石', '8000', '12000', '0.80', null, '2018-10-29 22:33:56', '1', '2018-11-06 05:38:12');

-- ----------------------------
-- Table structure for t_bd_message
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_message`;
CREATE TABLE `t_bd_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `member_id` bigint(20) NOT NULL COMMENT '接收用户ID',
  `content` varchar(255) NOT NULL COMMENT '内容',
  `is_read` tinyint(1) NOT NULL COMMENT '是否已读',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_bd_message_member_id` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8 COMMENT='用户消息';

-- ----------------------------
-- Records of t_bd_message
-- ----------------------------
INSERT INTO `t_bd_message` VALUES ('1', '1', '您共支付了200.0元,成长值增加了10,积分增加了6', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('2', '16', '您共支付了200.0元,成长值增加了10,积分增加了6', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('3', '1', '您共支付了597.0元,成长值增加了29,积分增加了17', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('4', '1', '您共支付了597.0元,成长值增加了29,积分增加了17', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('5', '1', '您共支付了796.0元,成长值增加了39,积分增加了23', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('6', '1', '您共支付了4600.0元,成长值增加了230,积分增加了138', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('7', '5', '306元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('8', '5', '100000元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('9', '5', '您共支付了10000.0元,成长值增加了500,积分增加了300', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('10', '5', '您共支付了10000.0元,成长值增加了500,积分增加了300', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('11', '5', '您共支付了123.0元,成长值增加了6,积分增加了3', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('12', '1', '您共支付了199.0元,成长值增加了9,积分增加了5', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('13', '1', '您共支付了200.0元,成长值增加了10,积分增加了6', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('14', '1', '您共支付了199.0元,成长值增加了9,积分增加了5', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('15', '5', '100元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('16', '1', '您共支付了99.0000元,成长值增加了4,积分增加了2', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('17', '1', '您共支付了169.15元,成长值增加了8,积分增加了5', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('18', '1', '您共支付了170.0000元,成长值增加了8,积分增加了5', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('19', '1', '您共支付了169.1500元,成长值增加了8,积分增加了5', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('20', '1', '您共支付了169.1500元,成长值增加了8,积分增加了5', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('21', '5', '84.15元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('22', '1', '您共支付了0.0000元,成长值增加了0,积分增加了0', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('23', '5', '1元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('24', '5', '1元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('25', '5', '1元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('26', '5', '1元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('27', '5', '1元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('28', '5', '1元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('29', '5', '1元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('30', '5', '1元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('31', '5', '1元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('32', '1', '10.111444元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('33', '5', '1.3333333元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('34', '1', '0.11111111元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('35', '1', '您共支付了5094.9000元,成长值增加了254,积分增加了152', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('36', '1', '243534.23324534元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('37', '5', '100元充值成功', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('38', '5', '您共支付了84.1500元,成长值增加了4,积分增加了2', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('39', '16', '您共支付了597.0000元,成长值增加了29,积分增加了17', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('40', '16', '您共支付了597.0000元,成长值增加了29,积分增加了17', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('41', '3', '121321元充值成功', '1', null, '2018-11-09 18:05:14', '3', '2018-11-12 14:48:08');
INSERT INTO `t_bd_message` VALUES ('42', '3', '2213元充值成功', '1', null, '2018-11-09 18:05:14', '3', '2018-11-12 14:48:12');
INSERT INTO `t_bd_message` VALUES ('43', '16', '您共支付了597.0000元,成长值增加了29,积分增加了17', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('44', '16', '您共支付了999.0000元,成长值增加了49,积分增加了29', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('45', '16', '您共支付了999.0000元,成长值增加了49,积分增加了29', '0', null, '2018-11-09 18:05:14', null, '2018-11-09 18:05:14');
INSERT INTO `t_bd_message` VALUES ('46', '3', '100元充值成功', '1', null, '2018-11-12 15:18:07', '3', '2018-11-12 15:25:37');
INSERT INTO `t_bd_message` VALUES ('47', '3', '100元充值成功', '1', null, '2018-11-12 15:30:10', '3', '2018-11-12 15:43:49');
INSERT INTO `t_bd_message` VALUES ('48', '16', '您共支付了999.0000元,成长值增加了49,积分增加了29', '0', null, '2018-11-12 17:40:54', null, '2018-11-12 17:40:54');
INSERT INTO `t_bd_message` VALUES ('49', '20', '10元充值成功', '0', null, '2018-11-12 18:01:04', null, '2018-11-12 18:01:04');
INSERT INTO `t_bd_message` VALUES ('50', '20', '10元充值成功', '0', null, '2018-11-12 18:05:00', null, '2018-11-12 18:05:00');
INSERT INTO `t_bd_message` VALUES ('51', '20', '10元充值成功', '0', null, '2018-11-12 18:08:13', null, '2018-11-12 18:08:13');
INSERT INTO `t_bd_message` VALUES ('52', '20', '10元充值成功', '0', null, '2018-11-12 18:10:06', null, '2018-11-12 18:10:06');
INSERT INTO `t_bd_message` VALUES ('53', '20', '10元充值成功', '0', null, '2018-11-12 18:14:33', null, '2018-11-12 18:14:33');
INSERT INTO `t_bd_message` VALUES ('54', '20', '10元充值成功', '0', null, '2018-11-12 18:18:05', null, '2018-11-12 18:18:05');
INSERT INTO `t_bd_message` VALUES ('55', '20', '20元充值成功', '0', null, '2018-11-12 18:18:19', null, '2018-11-12 18:18:19');
INSERT INTO `t_bd_message` VALUES ('56', '20', '10元充值成功', '0', null, '2018-11-12 18:23:16', null, '2018-11-12 18:23:16');
INSERT INTO `t_bd_message` VALUES ('57', '16', '您共支付了999.0000元,成长值增加了49,积分增加了29', '0', null, '2018-11-12 18:29:10', null, '2018-11-12 18:29:10');
INSERT INTO `t_bd_message` VALUES ('58', '20', '5元充值成功', '0', null, '2018-11-12 18:33:24', null, '2018-11-12 18:33:24');
INSERT INTO `t_bd_message` VALUES ('59', '20', '1元充值成功', '0', null, '2018-11-12 18:35:16', null, '2018-11-12 18:35:16');
INSERT INTO `t_bd_message` VALUES ('60', '20', '2元充值成功', '0', null, '2018-11-12 18:35:39', null, '2018-11-12 18:35:39');
INSERT INTO `t_bd_message` VALUES ('61', '20', '1元充值成功', '0', null, '2018-11-12 18:39:49', null, '2018-11-12 18:39:49');
INSERT INTO `t_bd_message` VALUES ('62', '3', '10元充值成功', '0', null, '2018-11-12 18:46:37', null, '2018-11-12 18:46:37');
INSERT INTO `t_bd_message` VALUES ('63', '20', '12元充值成功', '0', null, '2018-11-12 18:59:02', null, '2018-11-12 18:59:02');
INSERT INTO `t_bd_message` VALUES ('64', '20', '4元充值成功', '0', null, '2018-11-12 19:07:00', null, '2018-11-12 19:07:00');
INSERT INTO `t_bd_message` VALUES ('65', '20', '5元充值成功', '0', null, '2018-11-12 19:07:42', null, '2018-11-12 19:07:42');
INSERT INTO `t_bd_message` VALUES ('66', '20', '30元充值成功', '0', null, '2018-11-12 19:12:31', null, '2018-11-12 19:12:31');
INSERT INTO `t_bd_message` VALUES ('67', '20', '100元充值成功', '0', null, '2018-11-12 19:14:13', null, '2018-11-12 19:14:13');
INSERT INTO `t_bd_message` VALUES ('68', '20', '13元充值成功', '0', null, '2018-11-12 19:16:36', null, '2018-11-12 19:16:36');
INSERT INTO `t_bd_message` VALUES ('69', '20', '8元充值成功', '0', null, '2018-11-12 19:17:23', null, '2018-11-12 19:17:23');
INSERT INTO `t_bd_message` VALUES ('70', '20', '5元充值成功', '0', null, '2018-11-12 19:18:15', null, '2018-11-12 19:18:15');
INSERT INTO `t_bd_message` VALUES ('71', '20', '2元充值成功', '0', null, '2018-11-12 19:18:33', null, '2018-11-12 19:18:33');
INSERT INTO `t_bd_message` VALUES ('72', '20', '12元充值成功', '0', null, '2018-11-12 19:19:26', null, '2018-11-12 19:19:26');
INSERT INTO `t_bd_message` VALUES ('73', '20', '1元充值成功', '0', null, '2018-11-12 19:22:58', null, '2018-11-12 19:22:58');
INSERT INTO `t_bd_message` VALUES ('74', '20', '12元充值成功', '0', null, '2018-11-12 19:31:48', null, '2018-11-12 19:31:48');
INSERT INTO `t_bd_message` VALUES ('75', '20', '23元充值成功', '0', null, '2018-11-12 19:32:15', null, '2018-11-12 19:32:15');
INSERT INTO `t_bd_message` VALUES ('76', '20', '23元充值成功', '0', null, '2018-11-12 19:32:33', null, '2018-11-12 19:32:33');
INSERT INTO `t_bd_message` VALUES ('77', '20', '50元充值成功', '0', null, '2018-11-12 19:36:19', null, '2018-11-12 19:36:19');
INSERT INTO `t_bd_message` VALUES ('78', '20', '20元充值成功', '0', null, '2018-11-12 19:37:07', null, '2018-11-12 19:37:07');
INSERT INTO `t_bd_message` VALUES ('79', '20', '2元充值成功', '0', null, '2018-11-12 19:38:50', null, '2018-11-12 19:38:50');
INSERT INTO `t_bd_message` VALUES ('80', '3', '10元充值成功', '0', null, '2018-11-12 19:41:57', null, '2018-11-12 19:41:57');
INSERT INTO `t_bd_message` VALUES ('81', '20', '2元充值成功', '0', null, '2018-11-12 19:43:11', null, '2018-11-12 19:43:11');
INSERT INTO `t_bd_message` VALUES ('82', '20', '3元充值成功', '0', null, '2018-11-12 19:43:24', null, '2018-11-12 19:43:24');
INSERT INTO `t_bd_message` VALUES ('83', '20', '2元充值成功', '0', null, '2018-11-12 19:46:28', null, '2018-11-12 19:46:28');
INSERT INTO `t_bd_message` VALUES ('84', '20', '3元充值成功', '0', null, '2018-11-12 19:47:21', null, '2018-11-12 19:47:21');
INSERT INTO `t_bd_message` VALUES ('85', '20', '32元充值成功', '0', null, '2018-11-12 19:47:54', null, '2018-11-12 19:47:54');
INSERT INTO `t_bd_message` VALUES ('86', '3', '100元充值成功', '0', null, '2018-11-12 19:49:00', null, '2018-11-12 19:49:00');
INSERT INTO `t_bd_message` VALUES ('87', '3', '10元充值成功', '0', null, '2018-11-12 19:50:43', null, '2018-11-12 19:50:43');
INSERT INTO `t_bd_message` VALUES ('88', '3', '30元充值成功', '0', null, '2018-11-12 19:51:40', null, '2018-11-12 19:51:40');
INSERT INTO `t_bd_message` VALUES ('89', '3', '5元充值成功', '0', null, '2018-11-12 19:52:48', null, '2018-11-12 19:52:48');
INSERT INTO `t_bd_message` VALUES ('90', '3', '5元充值成功', '0', null, '2018-11-12 19:54:10', null, '2018-11-12 19:54:10');
INSERT INTO `t_bd_message` VALUES ('91', '20', '40元充值成功', '0', null, '2018-11-12 19:54:56', null, '2018-11-12 19:54:56');
INSERT INTO `t_bd_message` VALUES ('92', '20', '20元充值成功', '0', null, '2018-11-12 19:55:29', null, '2018-11-12 19:55:29');
INSERT INTO `t_bd_message` VALUES ('93', '20', '10元充值成功', '0', null, '2018-11-12 19:56:01', null, '2018-11-12 19:56:01');
INSERT INTO `t_bd_message` VALUES ('94', '3', '3元充值成功', '0', null, '2018-11-12 19:57:56', null, '2018-11-12 19:57:56');
INSERT INTO `t_bd_message` VALUES ('95', '3', '13元充值成功', '1', null, '2018-11-12 20:00:08', '3', '2018-11-13 17:54:25');
INSERT INTO `t_bd_message` VALUES ('96', '3', '6元充值成功', '0', null, '2018-11-12 20:03:06', null, '2018-11-12 20:03:06');
INSERT INTO `t_bd_message` VALUES ('97', '3', '20元充值成功', '0', null, '2018-11-12 20:03:33', null, '2018-11-12 20:03:33');
INSERT INTO `t_bd_message` VALUES ('98', '3', '2元充值成功', '0', null, '2018-11-12 20:04:51', null, '2018-11-12 20:04:51');
INSERT INTO `t_bd_message` VALUES ('99', '3', '1元充值成功', '1', null, '2018-11-12 20:06:25', '3', '2018-11-15 10:09:54');
INSERT INTO `t_bd_message` VALUES ('100', '3', '1元充值成功', '1', null, '2018-11-12 20:07:47', '3', '2018-11-15 15:10:47');
INSERT INTO `t_bd_message` VALUES ('101', '3', '1元充值成功', '1', null, '2018-11-12 20:08:26', '3', '2018-11-15 10:09:49');
INSERT INTO `t_bd_message` VALUES ('102', '3', '2元充值成功', '1', null, '2018-11-12 20:08:26', '3', '2018-11-15 15:10:43');
INSERT INTO `t_bd_message` VALUES ('103', '3', '1元充值成功', '1', null, '2018-11-12 20:12:39', '3', '2018-11-13 17:51:08');
INSERT INTO `t_bd_message` VALUES ('104', '3', '3元充值成功', '1', null, '2018-11-12 20:13:24', '3', '2018-11-13 17:49:15');
INSERT INTO `t_bd_message` VALUES ('105', '3', '1元充值成功', '1', null, '2018-11-12 20:19:24', '3', '2018-11-13 17:49:12');
INSERT INTO `t_bd_message` VALUES ('106', '3', '1元充值成功', '1', null, '2018-11-12 20:21:49', '3', '2018-11-13 17:47:51');
INSERT INTO `t_bd_message` VALUES ('107', '3', '1元充值成功', '1', null, '2018-11-12 20:25:11', '3', '2018-11-13 17:47:49');
INSERT INTO `t_bd_message` VALUES ('108', '3', '2元充值成功', '1', null, '2018-11-12 21:13:55', '3', '2018-11-13 17:47:46');
INSERT INTO `t_bd_message` VALUES ('109', '20', '15元充值成功', '0', null, '2018-11-13 09:27:00', null, '2018-11-13 09:27:00');
INSERT INTO `t_bd_message` VALUES ('110', '20', '您共支付了0.0000元,成长值增加了0,积分增加了0', '0', null, '2018-11-13 12:33:45', null, '2018-11-13 12:33:45');
INSERT INTO `t_bd_message` VALUES ('111', '20', '您共支付了90.0000元,成长值增加了4,积分增加了2', '0', null, '2018-11-13 12:34:30', null, '2018-11-13 12:34:30');
INSERT INTO `t_bd_message` VALUES ('112', '20', '您共支付了90.0000元,成长值增加了4,积分增加了2', '0', null, '2018-11-13 12:37:03', null, '2018-11-13 12:37:03');
INSERT INTO `t_bd_message` VALUES ('113', '3', '99元充值成功', '1', null, '2018-11-13 17:50:57', '3', '2018-11-13 17:51:12');
INSERT INTO `t_bd_message` VALUES ('114', '3', '您共支付了90.0000元,成长值增加了4,积分增加了2', '1', null, '2018-11-13 17:57:01', '3', '2018-11-13 18:18:04');
INSERT INTO `t_bd_message` VALUES ('115', '3', '1000元充值成功', '1', null, '2018-11-13 18:09:21', '3', '2018-11-13 18:18:01');
INSERT INTO `t_bd_message` VALUES ('116', '3', '您共支付了90.0000元,成长值增加了4,积分增加了2', '1', null, '2018-11-13 18:10:35', '3', '2018-11-13 18:17:57');
INSERT INTO `t_bd_message` VALUES ('117', '20', '您共支付了200.0000元,成长值增加了10,积分增加了6', '0', null, '2018-11-13 20:12:51', null, '2018-11-13 20:12:51');
INSERT INTO `t_bd_message` VALUES ('118', '20', '20元充值成功', '0', null, '2018-11-13 21:05:55', null, '2018-11-13 21:05:55');
INSERT INTO `t_bd_message` VALUES ('119', '14', '1100元充值成功', '0', null, '2018-11-14 10:52:22', null, '2018-11-14 10:52:22');
INSERT INTO `t_bd_message` VALUES ('120', '16', '您共支付了999.0000元,成长值增加了49,积分增加了29', '0', null, '2018-11-14 13:57:32', null, '2018-11-14 13:57:32');
INSERT INTO `t_bd_message` VALUES ('121', '16', '您共支付了1596.0000元,成长值增加了79,积分增加了47', '0', null, '2018-11-14 13:57:54', null, '2018-11-14 13:57:54');
INSERT INTO `t_bd_message` VALUES ('122', '16', '您共支付了999.0000元,成长值增加了49,积分增加了29', '0', null, '2018-11-14 14:07:28', null, '2018-11-14 14:07:28');
INSERT INTO `t_bd_message` VALUES ('123', '16', '您共支付了199.0000元,成长值增加了9,积分增加了5', '0', null, '2018-11-14 14:23:12', null, '2018-11-14 14:23:12');
INSERT INTO `t_bd_message` VALUES ('124', '16', '您共支付了2997.0000元,成长值增加了149,积分增加了89', '0', null, '2018-11-14 14:59:17', null, '2018-11-14 14:59:17');
INSERT INTO `t_bd_message` VALUES ('125', '16', '您共支付了999.0000元,成长值增加了49,积分增加了29', '0', null, '2018-11-14 15:11:11', null, '2018-11-14 15:11:11');
INSERT INTO `t_bd_message` VALUES ('126', '16', '您共支付了999.0000元,成长值增加了49,积分增加了29', '0', null, '2018-11-14 17:36:37', null, '2018-11-14 17:36:37');
INSERT INTO `t_bd_message` VALUES ('127', '14', '您共支付了1198.0000元,成长值增加了59,积分增加了35', '0', null, '2018-11-14 17:40:40', null, '2018-11-14 17:40:40');
INSERT INTO `t_bd_message` VALUES ('128', '1', '您共支付了849.1500元,成长值增加了42,积分增加了25', '0', null, '2018-11-14 18:02:20', null, '2018-11-14 18:02:20');
INSERT INTO `t_bd_message` VALUES ('129', '14', '您共支付了180.0000元,成长值增加了9,积分增加了5', '0', null, '2018-11-14 20:05:10', null, '2018-11-14 20:05:10');
INSERT INTO `t_bd_message` VALUES ('130', '16', '您共支付了999.0000元,成长值增加了49,积分增加了29', '0', null, '2018-11-14 20:05:10', null, '2018-11-14 20:05:10');
INSERT INTO `t_bd_message` VALUES ('131', '14', '您共支付了1198.0000元,成长值增加了59,积分增加了35', '0', null, '2018-11-15 09:31:46', null, '2018-11-15 09:31:46');
INSERT INTO `t_bd_message` VALUES ('132', '1', '您共支付了849.1500元,成长值增加了42,积分增加了25', '0', null, '2018-11-15 09:31:46', null, '2018-11-15 09:31:46');
INSERT INTO `t_bd_message` VALUES ('133', '3', '您共支付了500.0000元,成长值增加了25,积分增加了15', '1', null, '2018-11-15 09:52:20', '3', '2018-11-15 10:07:49');
INSERT INTO `t_bd_message` VALUES ('134', '14', '您共支付了1198.0000元,成长值增加了59,积分增加了35', '0', null, '2018-11-15 11:10:56', null, '2018-11-15 11:10:56');
INSERT INTO `t_bd_message` VALUES ('135', '1', '您共支付了849.1500元,成长值增加了42,积分增加了25', '0', null, '2018-11-15 11:10:56', null, '2018-11-15 11:10:56');
INSERT INTO `t_bd_message` VALUES ('136', '3', '您共支付了500.0000元,成长值增加了25,积分增加了15', '1', null, '2018-11-15 11:10:56', '3', '2018-11-15 13:58:22');
INSERT INTO `t_bd_message` VALUES ('137', '20', '10元充值成功', '0', null, '2018-11-15 11:24:08', null, '2018-11-15 11:24:08');
INSERT INTO `t_bd_message` VALUES ('138', '3', '您共支付了250.0000元,成长值增加了12,积分增加了7', '1', null, '2018-11-15 14:10:59', '3', '2018-11-15 15:10:38');
INSERT INTO `t_bd_message` VALUES ('139', '3', '1元充值成功', '1', null, '2018-11-15 14:51:56', '3', '2018-11-15 15:10:35');
INSERT INTO `t_bd_message` VALUES ('140', '20', '10元充值成功', '0', null, '2018-11-15 16:03:06', null, '2018-11-15 16:03:06');
INSERT INTO `t_bd_message` VALUES ('141', '20', '2元充值成功', '0', null, '2018-11-15 16:09:04', null, '2018-11-15 16:09:04');
INSERT INTO `t_bd_message` VALUES ('142', '20', '17元充值成功', '0', null, '2018-11-15 16:11:41', null, '2018-11-15 16:11:41');
INSERT INTO `t_bd_message` VALUES ('143', '20', '34元充值成功', '0', null, '2018-11-15 16:14:30', null, '2018-11-15 16:14:30');
INSERT INTO `t_bd_message` VALUES ('144', '20', '22元充值成功', '0', null, '2018-11-15 16:16:21', null, '2018-11-15 16:16:21');
INSERT INTO `t_bd_message` VALUES ('145', '20', '30元充值成功', '0', null, '2018-11-15 16:19:08', null, '2018-11-15 16:19:08');
INSERT INTO `t_bd_message` VALUES ('146', '20', '10元充值成功', '0', null, '2018-11-15 16:24:26', null, '2018-11-15 16:24:26');
INSERT INTO `t_bd_message` VALUES ('147', '20', '8元充值成功', '0', null, '2018-11-15 16:27:39', null, '2018-11-15 16:27:39');
INSERT INTO `t_bd_message` VALUES ('148', '20', '10元充值成功', '0', null, '2018-11-15 16:30:04', null, '2018-11-15 16:30:04');
INSERT INTO `t_bd_message` VALUES ('149', '20', '12元充值成功', '0', null, '2018-11-15 16:34:58', null, '2018-11-15 16:34:58');
INSERT INTO `t_bd_message` VALUES ('150', '20', '123元充值成功', '0', null, '2018-11-15 16:40:13', null, '2018-11-15 16:40:13');
INSERT INTO `t_bd_message` VALUES ('151', '20', '12元充值成功', '0', null, '2018-11-15 17:04:00', null, '2018-11-15 17:04:00');
INSERT INTO `t_bd_message` VALUES ('152', '20', '123元充值成功', '0', null, '2018-11-15 17:04:33', null, '2018-11-15 17:04:33');
INSERT INTO `t_bd_message` VALUES ('153', '20', '12元充值成功', '0', null, '2018-11-15 17:09:01', null, '2018-11-15 17:09:01');
INSERT INTO `t_bd_message` VALUES ('154', '1', '您共支付了2547.4500元,成长值增加了127,积分增加了76', '0', null, '2018-11-15 17:17:19', null, '2018-11-15 17:17:19');
INSERT INTO `t_bd_message` VALUES ('155', '20', '111元充值成功', '0', null, '2018-11-15 17:31:21', null, '2018-11-15 17:31:21');
INSERT INTO `t_bd_message` VALUES ('156', '20', '122132元充值成功', '0', null, '2018-11-15 17:32:29', null, '2018-11-15 17:32:29');
INSERT INTO `t_bd_message` VALUES ('157', '20', '123122元充值成功', '0', null, '2018-11-15 17:38:31', null, '2018-11-15 17:38:31');
INSERT INTO `t_bd_message` VALUES ('158', '20', '您共支付了999.0000元,成长值增加了49,积分增加了29', '0', null, '2018-11-15 17:41:09', null, '2018-11-15 17:41:09');
INSERT INTO `t_bd_message` VALUES ('159', '20', '123元充值成功', '0', null, '2018-11-15 17:45:02', null, '2018-11-15 17:45:02');
INSERT INTO `t_bd_message` VALUES ('160', '20', '12322元充值成功', '0', null, '2018-11-15 17:46:25', null, '2018-11-15 17:46:25');
INSERT INTO `t_bd_message` VALUES ('161', '1', '您共支付了170.0000元,成长值增加了8,积分增加了5', '0', null, '2018-11-15 19:58:36', null, '2018-11-15 19:58:36');
INSERT INTO `t_bd_message` VALUES ('162', '1', '您共支付了169.1500元,成长值增加了8,积分增加了5', '0', null, '2018-11-15 20:00:14', null, '2018-11-15 20:00:14');
INSERT INTO `t_bd_message` VALUES ('163', '1', '您共支付了169.1500元,成长值增加了8,积分增加了5', '0', null, '2018-11-15 20:02:03', null, '2018-11-15 20:02:03');
INSERT INTO `t_bd_message` VALUES ('164', '20', '22元充值成功', '0', null, '2018-11-16 10:42:41', null, '2018-11-16 10:42:41');
INSERT INTO `t_bd_message` VALUES ('165', '20', '5元充值成功', '0', null, '2018-11-16 10:45:30', null, '2018-11-16 10:45:30');
INSERT INTO `t_bd_message` VALUES ('166', '1', '1元充值成功', '0', null, '2018-11-16 10:52:33', null, '2018-11-16 10:52:33');
INSERT INTO `t_bd_message` VALUES ('167', '1', '3元充值成功', '0', null, '2018-11-16 10:53:18', null, '2018-11-16 10:53:18');
INSERT INTO `t_bd_message` VALUES ('168', '1', '8元充值成功', '0', null, '2018-11-16 10:54:52', null, '2018-11-16 10:54:52');
INSERT INTO `t_bd_message` VALUES ('169', '1', '9元充值成功', '0', null, '2018-11-16 10:55:10', null, '2018-11-16 10:55:10');
INSERT INTO `t_bd_message` VALUES ('170', '25', '2元充值成功', '0', null, '2018-11-16 11:22:51', null, '2018-11-16 11:22:51');
INSERT INTO `t_bd_message` VALUES ('171', '25', '4元充值成功', '0', null, '2018-11-16 11:23:23', null, '2018-11-16 11:23:23');
INSERT INTO `t_bd_message` VALUES ('172', '25', '6元充值成功', '0', null, '2018-11-16 11:23:37', null, '2018-11-16 11:23:37');
INSERT INTO `t_bd_message` VALUES ('173', '25', '8元充值成功', '0', null, '2018-11-16 11:23:48', null, '2018-11-16 11:23:48');
INSERT INTO `t_bd_message` VALUES ('174', '25', '10元充值成功', '0', null, '2018-11-16 11:23:55', null, '2018-11-16 11:23:55');
INSERT INTO `t_bd_message` VALUES ('175', '25', '12元充值成功', '0', null, '2018-11-16 11:24:03', null, '2018-11-16 11:24:03');
INSERT INTO `t_bd_message` VALUES ('176', '20', '1元充值成功', '0', null, '2018-11-16 13:24:51', null, '2018-11-16 13:24:51');
INSERT INTO `t_bd_message` VALUES ('177', '20', '10000元充值成功', '0', null, '2018-11-16 13:27:24', null, '2018-11-16 13:27:24');
INSERT INTO `t_bd_message` VALUES ('178', '20', '您共支付了999.0000元,成长值增加了49,积分增加了29', '0', null, '2018-11-16 20:44:22', null, '2018-11-16 20:44:22');
INSERT INTO `t_bd_message` VALUES ('179', '14', '您共支付了1198.0000元,成长值增加了59,积分增加了35', '0', null, '2018-11-19 13:36:17', null, '2018-11-19 13:36:17');
INSERT INTO `t_bd_message` VALUES ('180', '14', '您共支付了100.0000元,成长值增加了5,积分增加了3', '0', null, '2018-11-20 10:25:10', null, '2018-11-20 10:25:10');
INSERT INTO `t_bd_message` VALUES ('181', '16', '您共支付了1495.0000元,成长值增加了74,积分增加了44', '0', null, '2018-11-20 13:51:48', null, '2018-11-20 13:51:48');
INSERT INTO `t_bd_message` VALUES ('182', '16', '您共支付了1498.0000元,成长值增加了74,积分增加了44', '0', null, '2018-11-26 13:44:16', null, '2018-11-26 13:44:16');
INSERT INTO `t_bd_message` VALUES ('183', '16', '您共支付了1423.1000元,成长值增加了71,积分增加了42', '0', null, '2018-11-26 14:32:02', null, '2018-11-26 14:32:02');

-- ----------------------------
-- Table structure for t_bd_sms
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_sms`;
CREATE TABLE `t_bd_sms` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `telephone` char(11) NOT NULL COMMENT '目标用户手机号',
  `content` varchar(255) NOT NULL COMMENT '短信内容',
  `type` tinyint(1) NOT NULL COMMENT '短信类型：1注册，2，登录，3充值，4订单信息，5会员等级',
  `status` tinyint(1) NOT NULL COMMENT '发送状态：0失败，1成功',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COMMENT='短信';

-- ----------------------------
-- Records of t_bd_sms
-- ----------------------------
INSERT INTO `t_bd_sms` VALUES ('1', '18888888888', '【66汽配】验证码:723806。您正在使用使用注册功能，该验证码仅用于身份验证，请勿泄露给他人使用。', '1', '1', null, '2018-11-12 14:22:17', null, '2018-11-12 14:22:17');
INSERT INTO `t_bd_sms` VALUES ('2', '17777777777', '【66汽配】验证码:394206。您正在使用使用注册功能，该验证码仅用于身份验证，请勿泄露给他人使用。', '1', '1', null, '2018-11-12 14:29:03', null, '2018-11-12 14:29:03');
INSERT INTO `t_bd_sms` VALUES ('3', '16666666666', '【66汽配】验证码:387524。您正在使用使用注册功能，该验证码仅用于身份验证，请勿泄露给他人使用。', '1', '1', null, '2018-11-12 14:36:42', null, '2018-11-12 14:36:42');
INSERT INTO `t_bd_sms` VALUES ('4', '18888888888', '【66汽配】验证码:321047。您正在使用使用注册功能，该验证码仅用于身份验证，请勿泄露给他人使用。', '1', '1', null, '2018-11-12 14:47:13', null, '2018-11-12 14:47:13');
INSERT INTO `t_bd_sms` VALUES ('5', '18888888888', '【66汽配】验证码:381670。您正在使用使用修改密码功能，该验证码仅用于身份验证，请勿泄露给他人使用', '2', '1', null, '2018-11-12 15:10:29', null, '2018-11-12 15:10:29');
INSERT INTO `t_bd_sms` VALUES ('6', '18888888888', '【66汽配】成功充值金额500元。', '4', '1', null, '2018-11-12 15:23:32', null, '2018-11-12 15:23:32');
INSERT INTO `t_bd_sms` VALUES ('7', '18888888888', '【66汽配】成功充值金额100元。', '4', '1', null, '2018-11-12 15:30:09', null, '2018-11-12 15:30:09');
INSERT INTO `t_bd_sms` VALUES ('8', '18888888888', '【66汽配】验证码:614308。您正在使用使用修改密码功能，该验证码仅用于身份验证，请勿泄露给他人使用', '3', '1', null, '2018-11-12 15:49:04', null, '2018-11-12 15:49:04');
INSERT INTO `t_bd_sms` VALUES ('9', '17712345678', '【66汽配】您的订单ORD15421022851已支付完成，请注意查收。', '5', '1', null, '2018-11-13 17:44:52', null, '2018-11-13 17:44:52');
INSERT INTO `t_bd_sms` VALUES ('10', '18888888888', '【66汽配】成功充值金额99元。', '4', '1', null, '2018-11-13 17:50:52', null, '2018-11-13 17:50:52');
INSERT INTO `t_bd_sms` VALUES ('11', '18888888888', '【66汽配】成功充值金额120元。', '4', '1', null, '2018-11-13 17:51:20', null, '2018-11-13 17:51:20');
INSERT INTO `t_bd_sms` VALUES ('12', '17712345678', '【66汽配】您的订单ORD-1542246731-1已支付完成，请注意查收。', '5', '1', null, '2018-11-15 09:52:20', null, '2018-11-15 09:52:20');
INSERT INTO `t_bd_sms` VALUES ('13', '17712345678', '【66汽配】您的订单ORD-1542246731-1已支付完成，请注意查收。', '5', '1', null, '2018-11-15 11:11:01', null, '2018-11-15 11:11:01');
INSERT INTO `t_bd_sms` VALUES ('14', '17777777777', '【66汽配】成功充值金额10元。', '4', '1', null, '2018-11-15 11:24:07', null, '2018-11-15 11:24:07');
INSERT INTO `t_bd_sms` VALUES ('15', '17777777777', '【66汽配】您的订单ORD-1542262133-1已支付完成，请注意查收。', '5', '1', null, '2018-11-15 14:10:59', null, '2018-11-15 14:10:59');
INSERT INTO `t_bd_sms` VALUES ('16', '18888888888', '【66汽配】成功充值金额1元。', '4', '1', null, '2018-11-15 14:51:59', null, '2018-11-15 14:51:59');
INSERT INTO `t_bd_sms` VALUES ('17', '17777777777', '【66汽配】成功充值金额10元。', '4', '1', null, '2018-11-15 16:03:06', null, '2018-11-15 16:03:06');
INSERT INTO `t_bd_sms` VALUES ('18', '17777777777', '【66汽配】成功充值金额2元。', '4', '1', null, '2018-11-15 16:09:01', null, '2018-11-15 16:09:01');
INSERT INTO `t_bd_sms` VALUES ('19', '17777777777', '【66汽配】成功充值金额17元。', '4', '1', null, '2018-11-15 16:11:45', null, '2018-11-15 16:11:45');
INSERT INTO `t_bd_sms` VALUES ('20', '17777777777', '【66汽配】成功充值金额34元。', '4', '1', null, '2018-11-15 16:14:34', null, '2018-11-15 16:14:34');
INSERT INTO `t_bd_sms` VALUES ('21', '17777777777', '【66汽配】成功充值金额22元。', '4', '1', null, '2018-11-15 16:16:23', null, '2018-11-15 16:16:23');
INSERT INTO `t_bd_sms` VALUES ('22', '17777777777', '【66汽配】成功充值金额30元。', '4', '1', null, '2018-11-15 16:19:05', null, '2018-11-15 16:19:05');
INSERT INTO `t_bd_sms` VALUES ('23', '17777777777', '【66汽配】成功充值金额10元。', '4', '1', null, '2018-11-15 16:24:21', null, '2018-11-15 16:24:21');
INSERT INTO `t_bd_sms` VALUES ('24', '17777777777', '【66汽配】成功充值金额8元。', '4', '1', null, '2018-11-15 16:27:39', null, '2018-11-15 16:27:39');
INSERT INTO `t_bd_sms` VALUES ('25', '17777777777', '【66汽配】成功充值金额10元。', '4', '1', null, '2018-11-15 16:30:04', null, '2018-11-15 16:30:04');
INSERT INTO `t_bd_sms` VALUES ('26', '17777777777', '【66汽配】成功充值金额12元。', '4', '1', null, '2018-11-15 16:34:59', null, '2018-11-15 16:34:59');
INSERT INTO `t_bd_sms` VALUES ('27', '17777777777', '【66汽配】成功充值金额123元。', '4', '1', null, '2018-11-15 16:40:11', null, '2018-11-15 16:40:11');
INSERT INTO `t_bd_sms` VALUES ('28', '17777777777', '【66汽配】成功充值金额12元。', '4', '1', null, '2018-11-15 17:04:03', null, '2018-11-15 17:04:03');
INSERT INTO `t_bd_sms` VALUES ('29', '17777777777', '【66汽配】成功充值金额123元。', '4', '1', null, '2018-11-15 17:04:36', null, '2018-11-15 17:04:36');
INSERT INTO `t_bd_sms` VALUES ('30', '17777777777', '【66汽配】成功充值金额12元。', '4', '1', null, '2018-11-15 17:09:04', null, '2018-11-15 17:09:04');
INSERT INTO `t_bd_sms` VALUES ('31', '15952644569', '【66汽配】您的订单ORD-1542273380-1已支付完成，请注意查收。', '5', '1', null, '2018-11-15 17:17:20', null, '2018-11-15 17:17:20');
INSERT INTO `t_bd_sms` VALUES ('32', '17777777777', '【66汽配】成功充值金额111元。', '4', '1', null, '2018-11-15 17:31:22', null, '2018-11-15 17:31:22');
INSERT INTO `t_bd_sms` VALUES ('33', '17777777777', '【66汽配】成功充值金额122132元。', '4', '1', null, '2018-11-15 17:32:30', null, '2018-11-15 17:32:30');
INSERT INTO `t_bd_sms` VALUES ('34', '17777777777', '【66汽配】成功充值金额123122元。', '4', '1', null, '2018-11-15 17:38:33', null, '2018-11-15 17:38:33');
INSERT INTO `t_bd_sms` VALUES ('35', '18291404770', '【66汽配】您的订单ORD-1542274857-1已支付完成，请注意查收。', '5', '1', null, '2018-11-15 17:41:10', null, '2018-11-15 17:41:10');
INSERT INTO `t_bd_sms` VALUES ('36', '17777777777', '【66汽配】成功充值金额123元。', '4', '1', null, '2018-11-15 17:44:59', null, '2018-11-15 17:44:59');
INSERT INTO `t_bd_sms` VALUES ('37', '17777777777', '【66汽配】成功充值金额12322元。', '4', '1', null, '2018-11-15 17:46:26', null, '2018-11-15 17:46:26');
INSERT INTO `t_bd_sms` VALUES ('38', '15952644569', '【66汽配】您的订单ORD-1542283109-1已支付完成，请注意查收。', '5', '1', null, '2018-11-15 19:58:38', null, '2018-11-15 19:58:38');
INSERT INTO `t_bd_sms` VALUES ('39', '15952644569', '【66汽配】您的订单ORD-1542283207-1已支付完成，请注意查收。', '5', '1', null, '2018-11-15 20:00:16', null, '2018-11-15 20:00:16');
INSERT INTO `t_bd_sms` VALUES ('40', '15952644569', '【66汽配】您的订单ORD-1542283314-1已支付完成，请注意查收。', '5', '1', null, '2018-11-15 20:02:01', null, '2018-11-15 20:02:01');
INSERT INTO `t_bd_sms` VALUES ('41', '17777777777', '【66汽配】成功充值金额22元。', '4', '1', null, '2018-11-16 10:42:40', null, '2018-11-16 10:42:40');
INSERT INTO `t_bd_sms` VALUES ('42', '17777777777', '【66汽配】成功充值金额5元。', '4', '1', null, '2018-11-16 10:45:28', null, '2018-11-16 10:45:28');
INSERT INTO `t_bd_sms` VALUES ('43', '18888888888', '【66汽配】成功充值金额1元。', '4', '1', null, '2018-11-16 10:52:34', null, '2018-11-16 10:52:34');
INSERT INTO `t_bd_sms` VALUES ('44', '18888888888', '【66汽配】成功充值金额3元。', '4', '1', null, '2018-11-16 10:53:18', null, '2018-11-16 10:53:18');
INSERT INTO `t_bd_sms` VALUES ('45', '18888888888', '【66汽配】成功充值金额8元。', '4', '1', null, '2018-11-16 10:54:56', null, '2018-11-16 10:54:56');
INSERT INTO `t_bd_sms` VALUES ('46', '18888888888', '【66汽配】成功充值金额9元。', '4', '1', null, '2018-11-16 10:55:10', null, '2018-11-16 10:55:10');
INSERT INTO `t_bd_sms` VALUES ('47', '16666666666', '【66汽配】验证码:371290。您正在使用使用注册功能，该验证码仅用于身份验证，请勿泄露给他人使用。', '1', '1', null, '2018-11-16 11:21:40', null, '2018-11-16 11:21:40');
INSERT INTO `t_bd_sms` VALUES ('48', '16666666666', '【66汽配】成功充值金额2元。', '4', '1', null, '2018-11-16 11:22:51', null, '2018-11-16 11:22:51');
INSERT INTO `t_bd_sms` VALUES ('49', '16666666666', '【66汽配】成功充值金额4元。', '4', '1', null, '2018-11-16 11:23:24', null, '2018-11-16 11:23:24');
INSERT INTO `t_bd_sms` VALUES ('50', '16666666666', '【66汽配】成功充值金额6元。', '4', '1', null, '2018-11-16 11:23:38', null, '2018-11-16 11:23:38');
INSERT INTO `t_bd_sms` VALUES ('51', '16666666666', '【66汽配】成功充值金额8元。', '4', '1', null, '2018-11-16 11:23:48', null, '2018-11-16 11:23:48');
INSERT INTO `t_bd_sms` VALUES ('52', '16666666666', '【66汽配】成功充值金额10元。', '4', '1', null, '2018-11-16 11:23:56', null, '2018-11-16 11:23:56');
INSERT INTO `t_bd_sms` VALUES ('53', '16666666666', '【66汽配】成功充值金额12元。', '4', '1', null, '2018-11-16 11:24:03', null, '2018-11-16 11:24:03');
INSERT INTO `t_bd_sms` VALUES ('54', '17777777777', '【66汽配】成功充值金额1元。', '4', '1', null, '2018-11-16 13:24:51', null, '2018-11-16 13:24:51');
INSERT INTO `t_bd_sms` VALUES ('55', '17777777777', '【66汽配】成功充值金额10000元。', '4', '1', null, '2018-11-16 13:27:24', null, '2018-11-16 13:27:24');
INSERT INTO `t_bd_sms` VALUES ('56', '18291404770', '【66汽配】您的订单ORD-1542274683-1已支付完成，请注意查收。', '5', '1', null, '2018-11-16 20:44:22', null, '2018-11-16 20:44:22');
INSERT INTO `t_bd_sms` VALUES ('57', '13163253695', '【66汽配】您的订单ORD-1542605768-1已支付完成，请注意查收。', '5', '1', null, '2018-11-19 13:36:16', null, '2018-11-19 13:36:16');
INSERT INTO `t_bd_sms` VALUES ('58', '13163253695', '【66汽配】您的订单ORD-1542680702-1已支付完成，请注意查收。', '5', '1', null, '2018-11-20 10:25:14', null, '2018-11-20 10:25:14');
INSERT INTO `t_bd_sms` VALUES ('59', '18852380796', '【66汽配】您的订单ORD-1542693099-1已支付完成，请注意查收。', '5', '1', null, '2018-11-20 13:51:45', null, '2018-11-20 13:51:45');
INSERT INTO `t_bd_sms` VALUES ('60', '18852380796', '【66汽配】验证码:045839。您正在使用使用注册功能，该验证码仅用于身份验证，请勿泄露给他人使用。', '1', '1', null, '2018-11-26 11:28:16', null, '2018-11-26 11:28:16');
INSERT INTO `t_bd_sms` VALUES ('61', '18852380796', '【66汽配】您的订单ORD-1543211048-1已支付完成，请注意查收。', '5', '1', null, '2018-11-26 13:44:19', null, '2018-11-26 13:44:19');
INSERT INTO `t_bd_sms` VALUES ('62', '18852380796', '【66汽配】您的订单ORD-1543213914-1已支付完成，请注意查收。', '5', '1', null, '2018-11-26 14:32:03', null, '2018-11-26 14:32:03');

-- ----------------------------
-- Table structure for t_gds_category
-- ----------------------------
DROP TABLE IF EXISTS `t_gds_category`;
CREATE TABLE `t_gds_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(255) NOT NULL COMMENT '商品种类名称',
  `sort_num` int(10) NOT NULL COMMENT '排序号',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `image_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_gds_category_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商品分类表';

-- ----------------------------
-- Records of t_gds_category
-- ----------------------------
INSERT INTO `t_gds_category` VALUES ('1', '零配件', '1', '0', '0', '', '1', '2018-11-13 13:54:37', '1', '2018-11-13 14:06:54');
INSERT INTO `t_gds_category` VALUES ('2', '装饰品', '2', '0', '0', '', '1', '2018-11-13 14:01:48', '1', '2018-11-13 14:07:05');
INSERT INTO `t_gds_category` VALUES ('3', '维修工具', '2', '0', '0', '', '1', '2018-11-13 14:03:34', '1', '2018-11-13 14:07:44');
INSERT INTO `t_gds_category` VALUES ('4', '户外必备', '3', '0', '1', '', '1', '2018-11-13 14:04:21', '1', '2018-11-13 14:07:52');
INSERT INTO `t_gds_category` VALUES ('5', '轮胎', '1', '1', '0', 'V2/201811/1/4-21a0dcb2b7844f52b83808350e8178b6-g-o-0.jpg', '1', '2018-11-13 17:13:25', '1', '2018-11-13 17:13:25');

-- ----------------------------
-- Table structure for t_gds_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_gds_goods`;
CREATE TABLE `t_gds_goods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(255) DEFAULT NULL COMMENT '商品编号',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `category_id` bigint(20) NOT NULL COMMENT '商品品类表的主键',
  `sales` int(11) DEFAULT NULL COMMENT '销量',
  `detail` varchar(2000) DEFAULT NULL COMMENT '详情描述',
  `status` tinyint(1) DEFAULT NULL COMMENT '上架和下架',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_gds_goods_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of t_gds_goods
-- ----------------------------
INSERT INTO `t_gds_goods` VALUES ('1', 'GDS15421014401', '米其林轮胎', '5', '4', '汽车专用带钴轮胎', '1', '0', '1', '2018-11-13 17:30:40', '1', '2018-11-15 14:49:24');
INSERT INTO `t_gds_goods` VALUES ('2', 'GDS-1543210706-1', '佳通(Giti)轮胎', '5', '0', '<p><span style=\"color: rgb(51, 51, 51);\">佳通(Giti)轮胎/汽车轮胎225/65R17 102H Giti Comfort SUV520 原配比亚迪S6/比亚迪宋/哈弗H6/吉利GX7等</span></p>', '0', '0', '1', '2018-11-26 13:38:27', '1', '2018-11-26 13:38:27');

-- ----------------------------
-- Table structure for t_gds_goods_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_gds_goods_pic`;
CREATE TABLE `t_gds_goods_pic` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `property_id` bigint(20) NOT NULL COMMENT '商品属性表的主键id',
  `pic_name` varchar(50) DEFAULT NULL COMMENT '图片名称',
  `pic_url` varchar(100) DEFAULT NULL COMMENT '图片url',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_goods_pic_property_id` (`property_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品图片表';

-- ----------------------------
-- Records of t_gds_goods_pic
-- ----------------------------
INSERT INTO `t_gds_goods_pic` VALUES ('2', '1', '2', 'V2/201811/1/4-614ad960f7c348ebbdb2db11fc353fae-g-o-0.jpg', '1', '2018-11-13 17:39:56', '1', '2018-11-13 17:39:56');
INSERT INTO `t_gds_goods_pic` VALUES ('3', '1', '3', 'V2/201811/1/4-94670679dd364752946a561b034bc741-g-o-0.jpg', '1', '2018-11-13 17:40:03', '1', '2018-11-13 17:40:03');

-- ----------------------------
-- Table structure for t_gds_hot_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_gds_hot_tag`;
CREATE TABLE `t_gds_hot_tag` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `tag` varchar(80) DEFAULT NULL COMMENT '标签',
  `sort_num` int(11) DEFAULT NULL COMMENT '排序号',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='热门标签';

-- ----------------------------
-- Records of t_gds_hot_tag
-- ----------------------------
INSERT INTO `t_gds_hot_tag` VALUES ('1', '轮胎', '0', '1', '2018-11-13 17:37:11', '1', '2018-11-13 17:37:11');
INSERT INTO `t_gds_hot_tag` VALUES ('2', '轮毂', '1', '1', '2018-11-26 13:46:36', '1', '2018-11-26 13:46:36');
INSERT INTO `t_gds_hot_tag` VALUES ('3', '行李架', '3', '1', '2018-11-26 13:47:37', '1', '2018-11-26 13:47:37');
INSERT INTO `t_gds_hot_tag` VALUES ('4', '踏板', '4', '1', '2018-11-26 13:47:48', '1', '2018-11-26 13:47:48');

-- ----------------------------
-- Table structure for t_gds_property
-- ----------------------------
DROP TABLE IF EXISTS `t_gds_property`;
CREATE TABLE `t_gds_property` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `goods_id` bigint(20) NOT NULL COMMENT '商品信息表的主键',
  `stock` int(11) DEFAULT NULL COMMENT '库存量',
  `discount_price` decimal(14,4) DEFAULT NULL COMMENT '优惠价',
  `sale_price` decimal(14,4) DEFAULT NULL COMMENT '定价',
  `property` varchar(200) DEFAULT NULL COMMENT '规格',
  `is_discount` tinyint(1) DEFAULT NULL COMMENT '是否在打折期间（1为是，0为否）',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_gds_property_goods_id` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='商品规格';

-- ----------------------------
-- Records of t_gds_property
-- ----------------------------
INSERT INTO `t_gds_property` VALUES ('1', '1', '2', null, '250.0000', '花纹1', '0', '1', '2018-11-13 17:38:20', '1', '2018-11-15 19:37:04');
INSERT INTO `t_gds_property` VALUES ('2', '2', '100', null, '410.0000', 'SUV&越野型235/55R18', '0', '1', '2018-11-26 13:39:47', '1', '2018-11-26 13:39:47');
INSERT INTO `t_gds_property` VALUES ('3', '2', '100', null, '399.0000', ' SUV&越野型215/60R17', '0', '1', '2018-11-26 13:50:11', '1', '2018-11-26 13:50:11');
INSERT INTO `t_gds_property` VALUES ('4', '2', '100', null, '359.0000', 'SUV&越野型215/65R16', '0', '1', '2018-11-26 13:51:36', '1', '2018-11-26 13:51:36');

-- ----------------------------
-- Table structure for t_ord_cart_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_ord_cart_goods`;
CREATE TABLE `t_ord_cart_goods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `goods_property_id` bigint(20) DEFAULT NULL COMMENT '商品属性id',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `goods_num` int(10) DEFAULT NULL COMMENT '商品数量',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_ord_cart_goods_member_id` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='购物车商品信息表';

-- ----------------------------
-- Records of t_ord_cart_goods
-- ----------------------------
INSERT INTO `t_ord_cart_goods` VALUES ('1', null, '1', '3', '2', null, '2018-11-26 11:29:13', null, '2018-12-19 11:21:23');

-- ----------------------------
-- Table structure for t_ord_order
-- ----------------------------
DROP TABLE IF EXISTS `t_ord_order`;
CREATE TABLE `t_ord_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `order_num` varchar(30) DEFAULT NULL COMMENT '订单号',
  `order_price` decimal(20,4) DEFAULT NULL COMMENT '订单总额',
  `pay_price` decimal(20,4) DEFAULT NULL COMMENT '整个订单的实际支付金额',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除:1表示删除，0表示未删除',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态:1待付款2取消订单3待发货4待收货5已完成',
  `receipt_name` varchar(20) DEFAULT NULL COMMENT '收货人姓名',
  `receipt_tel` char(11) DEFAULT NULL COMMENT '收货人电话',
  `receipt_address` varchar(100) DEFAULT NULL COMMENT '收货人地址',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_ord_order_member_id_order_num` (`member_id`,`order_num`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of t_ord_order
-- ----------------------------
INSERT INTO `t_ord_order` VALUES ('1', '3', 'ORD15421022851', '-1250.0000', '-1250.0000', '1', '5', '追疯', '17712345678', '81号', null, '2018-11-13 17:44:45', '1', '2018-11-15 10:10:23');
INSERT INTO `t_ord_order` VALUES ('2', '3', 'ORD15421034951', '500.0000', '500.0000', '1', '2', '追疯', '17712345678', '81号', null, '2018-11-13 18:04:55', '1', '2018-11-15 10:56:22');
INSERT INTO `t_ord_order` VALUES ('3', '3', 'ORD15421038861', '500.0000', '500.0000', '1', '2', '追疯', '17712345678', '81号', null, '2018-11-13 18:11:26', '1', '2018-11-15 10:56:24');
INSERT INTO `t_ord_order` VALUES ('4', '3', 'ORD15421104241', '250.0000', '245.0000', '1', '2', '追疯', '17712345678', '81号', null, '2018-11-13 20:00:24', '1', '2018-11-15 10:56:20');
INSERT INTO `t_ord_order` VALUES ('5', '3', 'ORD15421105021', '250.0000', '237.5000', '1', '2', '追疯', '17712345678', '81号', null, '2018-11-13 20:01:42', null, '2018-11-15 10:56:18');
INSERT INTO `t_ord_order` VALUES ('6', '3', 'ORD-1542246731-1', '500.0000', '500.0000', '1', '5', '追疯', '17712345678', '81号', null, '2018-11-15 09:52:11', '1', '2018-11-15 15:05:29');
INSERT INTO `t_ord_order` VALUES ('7', '3', 'ORD-1542247750-1', '1250.0000', '1250.0000', '0', '3', '追疯', '17712345678', '81号', null, '2018-11-15 10:09:10', null, '2018-11-15 10:09:13');
INSERT INTO `t_ord_order` VALUES ('8', '3', 'ORD-1542247807-1', '250.0000', '250.0000', '1', '2', '追疯', '17712345678', '81号', null, '2018-11-15 10:10:07', null, '2018-11-15 10:10:19');
INSERT INTO `t_ord_order` VALUES ('9', '3', 'ORD-1542262133-1', '250.0000', '250.0000', '1', '2', '追疯', '17777777777', '801室', null, '2018-11-15 14:08:53', '1', '2018-11-15 15:11:46');
INSERT INTO `t_ord_order` VALUES ('10', '3', 'ORD-1542262316-1', '250.0000', '250.0000', '1', '5', '追疯', '17777777777', '801室', null, '2018-11-15 14:11:56', '1', '2018-11-15 14:47:03');
INSERT INTO `t_ord_order` VALUES ('11', '3', 'ORD-1542262882-1', '500.0000', '500.0000', '1', '2', '追疯', '17777777777', '801室', null, '2018-11-15 14:21:22', null, '2018-11-15 14:46:59');
INSERT INTO `t_ord_order` VALUES ('12', '3', 'ORD-1542266089-1', '1750.0000', '1750.0000', '0', '2', '追疯', '17777777777', '801室', null, '2018-11-15 15:14:49', null, '2018-11-15 15:15:29');
INSERT INTO `t_ord_order` VALUES ('13', '3', 'ORD-1542277425-1', '1000.0000', '1000.0000', '1', '2', 'To追疯', '19999999999', '河北省石家庄市长安区91号', null, '2018-11-15 18:23:45', null, '2018-11-15 19:36:36');
INSERT INTO `t_ord_order` VALUES ('14', '3', 'ORD-1542277475-1', '250.0000', '250.0000', '0', '1', '追疯', '17777777777', '河北省石家庄市长安区801室', null, '2018-11-15 18:24:35', null, '2018-11-15 18:24:35');
INSERT INTO `t_ord_order` VALUES ('15', '3', 'ORD-1542281776-1', '250.0000', '250.0000', '0', '1', '追疯', '17777777777', '河北省石家庄市长安区801室', null, '2018-11-15 19:36:16', null, '2018-11-15 19:36:16');
INSERT INTO `t_ord_order` VALUES ('16', '3', 'ORD-1542281815-1', '500.0000', '500.0000', '0', '1', '追疯', '17777777777', '河北省石家庄市长安区801室', null, '2018-11-15 19:36:55', null, '2018-11-15 19:36:55');
INSERT INTO `t_ord_order` VALUES ('17', '3', 'ORD-1542281824-1', '250.0000', '250.0000', '0', '1', '追疯', '17777777777', '河北省石家庄市长安区801室', null, '2018-11-15 19:37:04', null, '2018-11-15 19:37:04');
INSERT INTO `t_ord_order` VALUES ('18', '3', 'ORD-1543202956-1', '250.0000', '250.0000', '0', '1', '追疯', '17777777777', '河北省石家庄市长安区801室', null, '2018-11-26 11:29:16', null, '2018-11-26 11:29:16');
INSERT INTO `t_ord_order` VALUES ('19', '3', 'ORD-1543202971-1', '250.0000', '250.0000', '0', '1', '追疯', '17777777777', '河北省石家庄市长安区801室', null, '2018-11-26 11:29:32', null, '2018-11-26 11:29:32');

-- ----------------------------
-- Table structure for t_ord_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_ord_order_goods`;
CREATE TABLE `t_ord_order_goods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `goods_property_id` bigint(20) DEFAULT NULL COMMENT '商品属性id',
  `goods_num` int(10) DEFAULT NULL COMMENT '商品数量',
  `sale_price` decimal(20,4) DEFAULT NULL COMMENT '商品的原价',
  `discount_price` decimal(20,4) DEFAULT NULL COMMENT '商品的优惠单价',
  `pay_price` decimal(20,4) DEFAULT NULL COMMENT '商品的实际支付价格',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_ord_order_goods_order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='订单商品信息表';

-- ----------------------------
-- Records of t_ord_order_goods
-- ----------------------------
INSERT INTO `t_ord_order_goods` VALUES ('1', '1', '1', '1', '-5', '250.0000', null, '250.0000', null, '2018-11-13 17:44:45', null, '2018-11-13 17:44:45');
INSERT INTO `t_ord_order_goods` VALUES ('2', '2', '1', '1', '2', '250.0000', null, '250.0000', null, '2018-11-13 18:04:55', null, '2018-11-13 18:04:55');
INSERT INTO `t_ord_order_goods` VALUES ('3', '3', '1', '1', '2', '250.0000', null, '250.0000', null, '2018-11-13 18:11:26', null, '2018-11-13 18:11:26');
INSERT INTO `t_ord_order_goods` VALUES ('4', '4', '1', '1', '1', '250.0000', null, '245.0000', null, '2018-11-13 20:00:24', null, '2018-11-13 20:00:24');
INSERT INTO `t_ord_order_goods` VALUES ('5', '5', '1', '1', '1', '250.0000', null, '237.5000', null, '2018-11-13 20:01:42', null, '2018-11-13 20:01:42');
INSERT INTO `t_ord_order_goods` VALUES ('6', '6', '1', '1', '2', '250.0000', null, '250.0000', null, '2018-11-15 09:52:11', null, '2018-11-15 09:52:11');
INSERT INTO `t_ord_order_goods` VALUES ('7', '7', '1', '1', '5', '250.0000', null, '250.0000', null, '2018-11-15 10:09:10', null, '2018-11-15 10:09:10');
INSERT INTO `t_ord_order_goods` VALUES ('8', '8', '1', '1', '1', '250.0000', null, '250.0000', null, '2018-11-15 10:10:07', null, '2018-11-15 10:10:07');
INSERT INTO `t_ord_order_goods` VALUES ('9', '9', '1', '1', '1', '250.0000', null, '250.0000', null, '2018-11-15 14:08:53', null, '2018-11-15 14:08:53');
INSERT INTO `t_ord_order_goods` VALUES ('10', '10', '1', '1', '1', '250.0000', null, '250.0000', null, '2018-11-15 14:11:56', null, '2018-11-15 14:11:56');
INSERT INTO `t_ord_order_goods` VALUES ('11', '11', '1', '1', '2', '250.0000', null, '250.0000', null, '2018-11-15 14:21:22', null, '2018-11-15 14:21:22');
INSERT INTO `t_ord_order_goods` VALUES ('12', '12', '1', '1', '7', '250.0000', null, '250.0000', null, '2018-11-15 15:14:49', null, '2018-11-15 15:14:49');
INSERT INTO `t_ord_order_goods` VALUES ('13', '13', '1', '1', '4', '250.0000', null, '250.0000', null, '2018-11-15 18:23:45', null, '2018-11-15 18:23:45');
INSERT INTO `t_ord_order_goods` VALUES ('14', '14', '1', '1', '1', '250.0000', null, '250.0000', null, '2018-11-15 18:24:35', null, '2018-11-15 18:24:35');
INSERT INTO `t_ord_order_goods` VALUES ('15', '15', '1', '1', '1', '250.0000', null, '250.0000', null, '2018-11-15 19:36:16', null, '2018-11-15 19:36:16');
INSERT INTO `t_ord_order_goods` VALUES ('16', '16', '1', '1', '2', '250.0000', null, '250.0000', null, '2018-11-15 19:36:55', null, '2018-11-15 19:36:55');
INSERT INTO `t_ord_order_goods` VALUES ('17', '17', '1', '1', '1', '250.0000', null, '250.0000', null, '2018-11-15 19:37:04', null, '2018-11-15 19:37:04');

-- ----------------------------
-- Table structure for t_ord_order_history
-- ----------------------------
DROP TABLE IF EXISTS `t_ord_order_history`;
CREATE TABLE `t_ord_order_history` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态:1待付款2取消订单3待发货4待收货5已完成',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_ord_order_history` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单操作历史';

-- ----------------------------
-- Records of t_ord_order_history
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级',
  `name` varchar(32) NOT NULL COMMENT '菜单名字',
  `url` varchar(255) NOT NULL COMMENT '菜单路径',
  `level` tinyint(1) NOT NULL COMMENT '级别',
  `rel` varchar(255) NOT NULL COMMENT '标识',
  `type` tinyint(1) DEFAULT NULL COMMENT '类型 1:菜单 2:按钮',
  `sort_num` int(11) NOT NULL COMMENT '排序',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1', '0', '系统管理', '/sys', '1', 'sys', '1', '1', null, '2018-10-23 18:20:18', null, '2018-10-26 01:00:35');
INSERT INTO `t_sys_menu` VALUES ('2', '1', '用户管理', '/sys/user', '2', 'sys:user', '1', '1', null, '2018-10-23 18:20:18', null, '2018-10-26 01:05:45');
INSERT INTO `t_sys_menu` VALUES ('3', '2', '用户添加', '/sys/user/add', '3', 'sys:user:add', '2', '2', null, '2018-10-23 18:20:18', null, '2018-10-31 00:23:46');
INSERT INTO `t_sys_menu` VALUES ('4', '2', '用户修改', '/sys/user/edit', '3', 'sys:user:edit', '2', '0', null, '2018-10-23 18:20:18', null, '2018-10-26 01:05:47');
INSERT INTO `t_sys_menu` VALUES ('5', '2', '用户删除', '/sys/user/delete', '3', 'sys:user:delete', '2', '1', null, '2018-10-23 18:20:18', null, '2018-10-31 00:23:46');
INSERT INTO `t_sys_menu` VALUES ('6', '2', '用户授权', '/sys/user/auth', '3', 'sys:user:auth', '2', '0', null, '2018-10-23 18:20:18', null, '2018-10-26 01:05:50');
INSERT INTO `t_sys_menu` VALUES ('7', '1', '角色管理', '/sys/role', '2', 'sys:role', '1', '2', null, '2018-10-23 18:20:18', null, '2018-11-01 02:28:35');
INSERT INTO `t_sys_menu` VALUES ('8', '7', '角色添加', '/sys/role/add', '3', 'sys:role:add', '2', '0', null, '2018-10-23 18:20:18', null, '2018-10-26 01:05:52');
INSERT INTO `t_sys_menu` VALUES ('9', '7', '角色修改', '/sys/role/edit', '3', 'sys:role:edit', '2', '0', null, '2018-10-23 18:20:18', null, '2018-10-26 01:05:54');
INSERT INTO `t_sys_menu` VALUES ('10', '7', '角色删除', '/sys/role/delete', '3', 'sys:role:delete', '2', '0', null, '2018-10-23 18:20:18', null, '2018-10-26 01:05:55');
INSERT INTO `t_sys_menu` VALUES ('11', '7', '角色授权', '/sys/role/auth', '3', 'sys:role:auth', '2', '0', null, '2018-10-23 18:20:18', null, '2018-10-26 01:05:56');
INSERT INTO `t_sys_menu` VALUES ('12', '1', '菜单管理', '/sys/menu', '2', 'sys:menu', '1', '3', null, '2018-10-23 18:20:18', null, '2018-10-31 23:07:58');
INSERT INTO `t_sys_menu` VALUES ('13', '12', '菜单添加', '/sys/men/add', '3', 'sys:menu:add', '2', '1', null, '2018-10-23 18:20:18', null, '2018-11-06 00:48:56');
INSERT INTO `t_sys_menu` VALUES ('14', '12', '菜单修改', '/sys/menu/edit', '3', 'sys:menu:edit', '1', '2', null, '2018-11-06 00:48:39', null, '2018-11-06 00:48:39');
INSERT INTO `t_sys_menu` VALUES ('15', '12', '菜单删除', '/sys/menu/delete', '3', 'sys:menu:delete', '2', '3', null, '2018-11-06 00:49:35', null, '2018-11-06 00:49:35');
INSERT INTO `t_sys_menu` VALUES ('16', '0', '基础管理', '/bd', '1', 'bd', '1', '3', null, '2018-10-23 18:20:18', null, '2018-11-08 04:14:21');
INSERT INTO `t_sys_menu` VALUES ('17', '16', '广告管理', '/bd/banner', '2', 'bd:banner', '1', '1', null, '2018-10-23 18:20:18', null, '2018-11-08 04:14:34');
INSERT INTO `t_sys_menu` VALUES ('18', '17', '广告添加', '/bd/banner/add', '3', 'bd:banner:ad', '2', '0', null, '2018-10-23 18:20:18', null, '2018-11-08 04:15:12');
INSERT INTO `t_sys_menu` VALUES ('19', '17', '广告修改', '/bd/banner/edit', '3', 'bd:banner:edit', '2', '0', null, '2018-10-23 18:20:18', null, '2018-11-08 04:15:13');
INSERT INTO `t_sys_menu` VALUES ('20', '17', '广告删除', '/bd/banner/delete', '3', 'bd:banner:delete', '2', '0', null, '2018-10-23 18:20:18', null, '2018-11-08 04:15:15');
INSERT INTO `t_sys_menu` VALUES ('21', '16', '会员管理', '/bd/member', '2', 'bd:member', '1', '2', null, '2018-10-23 18:20:18', null, '2018-11-06 01:24:11');
INSERT INTO `t_sys_menu` VALUES ('22', '21', '会员禁用', '/bd/member/disable', '3', 'bd:member:disable', '2', '0', null, '2018-10-23 18:20:18', null, '2018-11-08 04:15:24');
INSERT INTO `t_sys_menu` VALUES ('23', '21', '会员启用', '/bd/member/enable', '3', 'bd:member:enable', '2', '0', null, '2018-10-23 18:20:18', null, '2018-11-08 04:15:27');
INSERT INTO `t_sys_menu` VALUES ('24', '16', '会员等级管理', '/bd/memberGrade', '2', 'bd:memberGrade', '1', '0', null, '2018-11-05 16:21:50', null, '2018-11-06 01:24:11');
INSERT INTO `t_sys_menu` VALUES ('25', '24', '会员等级修改 ', '/bd/memberGrade/edit', '2', 'bd:memberGrade:edit', '1', '1', null, '2018-11-06 00:41:03', null, '2018-11-08 04:15:33');
INSERT INTO `t_sys_menu` VALUES ('26', '16', '短信管理', '/bd/sms', '2', 'bd:sms', '1', '5', null, '2018-11-06 00:41:03', null, '2018-11-08 04:21:18');
INSERT INTO `t_sys_menu` VALUES ('27', '16', '短信模板管理', '/bd/smsTemplate', '2', 'bd:smsTemplate', '1', '6', null, '2018-11-06 00:41:03', null, '2018-11-08 04:21:14');
INSERT INTO `t_sys_menu` VALUES ('28', '27', '短信模板添加', '/bd/smsTemplate/add', '3', 'bd:smsTemplate:add', '2', '0', null, '2018-11-06 00:41:03', null, '2018-11-06 00:55:58');
INSERT INTO `t_sys_menu` VALUES ('29', '27', '短信模版修改', '/bd/smsTemplate/edit', '3', 'bd:smsTemplate:edit', '2', '0', null, '2018-11-06 00:55:48', null, '2018-11-08 04:13:33');
INSERT INTO `t_sys_menu` VALUES ('30', '27', '短信模版删除', '/bd/smsTemplate/delete', '3', 'bd:smsTemplate:delete', '2', '0', null, '2018-11-06 00:56:44', null, '2018-11-08 04:13:39');
INSERT INTO `t_sys_menu` VALUES ('50', '0', '商品管理', '/gds', '1', 'gds', '1', '3', null, '2018-10-23 18:20:18', null, '2018-11-08 04:05:31');
INSERT INTO `t_sys_menu` VALUES ('51', '50', '分类管理', '/gds/category', '2', 'gds:category', '1', '1', null, '2018-10-23 18:20:18', null, '2018-10-26 01:06:14');
INSERT INTO `t_sys_menu` VALUES ('52', '51', '分类添加', '/gds/category/add', '3', 'gds:category:add', '2', '0', null, '2018-10-23 18:20:18', null, '2018-10-26 01:06:15');
INSERT INTO `t_sys_menu` VALUES ('53', '51', '分类修改', '/gds/category/edit', '3', 'gds:category:edit', '2', '0', null, '2018-10-23 18:20:18', null, '2018-10-26 01:06:16');
INSERT INTO `t_sys_menu` VALUES ('54', '51', '分类删除', '/gds/category/delete', '3', 'gds:category:delete', '2', '0', null, '2018-10-23 18:20:18', null, '2018-10-26 01:06:17');
INSERT INTO `t_sys_menu` VALUES ('55', '50', '商品管理', '/gds/goods', '2', 'gds:goods', '1', '2', null, '2018-10-23 18:20:18', null, '2018-10-26 01:06:30');
INSERT INTO `t_sys_menu` VALUES ('56', '55', '商品添加', '/gds/goods/add', '3', 'gds:goods:add', '2', '0', null, '2018-10-23 18:20:18', null, '2018-10-26 01:06:31');
INSERT INTO `t_sys_menu` VALUES ('57', '55', '商品修改', '/gds/goods/edit', '3', 'gds:goods:edit', '2', '0', null, '2018-10-23 18:20:18', null, '2018-11-06 00:58:42');
INSERT INTO `t_sys_menu` VALUES ('58', '55', '商品删除', '/gds/goods/delete', '3', 'gds:goods:delete', '2', '0', null, '2018-10-23 18:20:18', null, '2018-10-26 01:06:33');
INSERT INTO `t_sys_menu` VALUES ('59', '55', '商品上架', '/gds/goods/upshelf', '3', 'gds:goods:upshelf', '2', '0', null, '2018-10-23 18:20:18', null, '2018-10-26 01:06:35');
INSERT INTO `t_sys_menu` VALUES ('60', '55', '商品下架', '/gds/goods/downshelf', '3', 'gds:goods:downshelf', '2', '0', null, '2018-10-23 18:20:18', null, '2018-10-26 01:06:38');
INSERT INTO `t_sys_menu` VALUES ('61', '50', '标签管理', '/gds/tag', '2', 'gds/tag', '1', '3', null, '2018-11-07 01:08:17', null, '2018-11-08 04:19:13');
INSERT INTO `t_sys_menu` VALUES ('62', '61', '标签添加', '/gds/tag/add', '3', 'gds/tag/add', '2', '0', null, '2018-11-08 04:08:18', null, '2018-11-08 04:19:16');
INSERT INTO `t_sys_menu` VALUES ('63', '61', '标签修改', '/gds/tag/edit', '3', 'gds/tag/edit', '2', '0', null, '2018-11-08 04:08:46', null, '2018-11-08 04:19:17');
INSERT INTO `t_sys_menu` VALUES ('64', '61', '标签删除', '/gds/tag/delete', '3', 'gds/tag/delete', '2', '0', null, '2018-11-08 04:09:37', null, '2018-11-08 04:19:19');
INSERT INTO `t_sys_menu` VALUES ('65', '0', '订单管理', '/ords', '1', 'ords', '1', '5', null, '2018-11-06 01:03:29', null, '2018-11-08 04:11:27');
INSERT INTO `t_sys_menu` VALUES ('66', '65', '订单管理', '/ords/order', '2', 'ords:order', '1', '1', null, '2018-11-06 01:05:10', null, '2018-11-08 04:19:24');
INSERT INTO `t_sys_menu` VALUES ('67', '66', '订单取消', '/ords/order/cancel', '3', 'ords:order:cancel', '2', '0', null, '2018-11-06 01:05:36', null, '2018-11-10 01:13:20');
INSERT INTO `t_sys_menu` VALUES ('68', '66', '订单发货', '/ords/order/deliver', '3', 'ords:order:deliver', '2', '0', null, '2018-11-06 01:06:46', null, '2018-11-08 04:19:21');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(32) NOT NULL COMMENT '角色名字',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('2', '运营管理员', null, '2018-11-12 13:59:30', null, '2018-11-12 14:02:46');

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------
INSERT INTO `t_sys_role_menu` VALUES ('16', '2', '1', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('17', '2', '2', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('18', '2', '4', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('19', '2', '6', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('20', '2', '5', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('21', '2', '3', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('22', '2', '7', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('23', '2', '8', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('24', '2', '9', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('25', '2', '10', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('26', '2', '11', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('27', '2', '12', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('28', '2', '13', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('29', '2', '14', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('30', '2', '15', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('31', '2', '16', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('32', '2', '24', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('33', '2', '25', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('34', '2', '17', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('35', '2', '18', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('36', '2', '19', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('37', '2', '20', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('38', '2', '21', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('39', '2', '22', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('40', '2', '23', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('41', '2', '26', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('42', '2', '27', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('43', '2', '28', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('44', '2', '29', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('45', '2', '30', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('46', '2', '50', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('47', '2', '51', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('48', '2', '52', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('49', '2', '53', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('50', '2', '54', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('51', '2', '55', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('52', '2', '56', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('53', '2', '57', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('54', '2', '58', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('55', '2', '59', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('56', '2', '60', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('57', '2', '61', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('58', '2', '62', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('59', '2', '63', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('60', '2', '64', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('61', '2', '65', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('62', '2', '66', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('63', '2', '67', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');
INSERT INTO `t_sys_role_menu` VALUES ('64', '2', '68', '1', '2018-11-12 14:05:27', null, '2018-11-12 14:05:27');

-- ----------------------------
-- Table structure for t_sys_sms_template
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_sms_template`;
CREATE TABLE `t_sys_sms_template` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` tinyint(1) NOT NULL COMMENT '模板类型：1注册，2，登录，3充值，4订单信息，5会员等级',
  `code` varchar(10) NOT NULL COMMENT '模板编号',
  `title` varchar(255) NOT NULL COMMENT '模板标题',
  `content` varchar(255) NOT NULL COMMENT '模板内容',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='短信模板';

-- ----------------------------
-- Records of t_sys_sms_template
-- ----------------------------
INSERT INTO `t_sys_sms_template` VALUES ('1', '1', '6671', '注册', '【66汽配】验证码:msg。您正在使用使用注册功能，该验证码仅用于身份验证，请勿泄露给他人使用。', '4', '2018-11-12 14:22:04', null, '2018-11-12 14:22:04');
INSERT INTO `t_sys_sms_template` VALUES ('2', '2', '6672', '忘记密码', '【66汽配】验证码:msg。您正在使用使用修改密码功能，该验证码仅用于身份验证，请勿泄露给他人使用', '4', '2018-11-12 15:10:24', null, '2018-11-12 15:10:24');
INSERT INTO `t_sys_sms_template` VALUES ('3', '3', '6673', '修改密码', '【66汽配】验证码:msg。您正在使用使用修改密码功能，该验证码仅用于身份验证，请勿泄露给他人使用', '4', '2018-11-12 15:19:06', null, '2018-11-12 15:19:06');
INSERT INTO `t_sys_sms_template` VALUES ('4', '4', '6674', '充值', '【66汽配】成功充值金额msg元。', '4', '2018-11-12 15:19:38', null, '2018-11-12 15:19:38');
INSERT INTO `t_sys_sms_template` VALUES ('5', '5', '6675', '订单信息', '【66汽配】您的订单msg已支付完成，请注意查收。', '4', '2018-11-12 15:19:59', null, '2018-11-12 15:19:59');
INSERT INTO `t_sys_sms_template` VALUES ('6', '6', '6676', '会员等级', '【66汽配】您的会员等级已经提升至msg。', '4', '2018-11-12 15:20:19', null, '2018-11-12 15:20:19');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `remark` varchar(32) DEFAULT NULL COMMENT '备注',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_admin` tinyint(4) DEFAULT '0' COMMENT '是否超级管理员(0：否1：是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='后台系统用户表';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '超级管理员！', '1', '2018-10-19 17:04:20', '1', '2018-11-02 22:53:37', '1');
INSERT INTO `t_sys_user` VALUES ('4', 'A区运营', 'e10adc3949ba59abbe56e057f20f883e', null, null, '2018-11-12 13:57:34', '1', '2018-11-12 14:02:06', '0');
INSERT INTO `t_sys_user` VALUES ('5', 'B区运营', '96e79218965eb72c92a549dd5a330112', null, null, '2018-11-12 14:00:19', '1', '2018-11-12 14:02:15', '0');
INSERT INTO `t_sys_user` VALUES ('6', 'test', '96e79218965eb72c92a549dd5a330112', null, null, '2018-11-12 14:49:36', null, '2018-11-12 14:49:36', '0');

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('1', '4', '2', '1', '2018-11-12 14:00:14', null, '2018-11-12 14:00:14');
INSERT INTO `t_sys_user_role` VALUES ('2', '5', '2', '1', '2018-11-12 14:00:32', null, '2018-11-12 14:00:32');
