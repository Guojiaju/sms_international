/*
 Navicat Premium Data Transfer

 Source Server         : 31
 Source Server Type    : MySQL
 Source Server Version : 50709
 Source Host           : 127.0.0.1
 Source Database       : sms_international

 Target Server Type    : MySQL
 Target Server Version : 50709
 File Encoding         : utf-8

 Date: 01/24/2018 11:51:22 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `uid` int(11) DEFAULT NULL COMMENT '后台人员编号',
  `rid` int(11) DEFAULT NULL COMMENT '角色编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台人员与角色关联表';

-- ----------------------------
--  Table structure for `channel_day_count`
-- ----------------------------
DROP TABLE IF EXISTS `channel_day_count`;
CREATE TABLE `channel_day_count` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `submit_count` int(10) DEFAULT NULL,
  `submit_succ` int(10) DEFAULT NULL,
  `submit_fail` int(10) DEFAULT NULL,
  `report_count` int(10) DEFAULT NULL,
  `report_succ` int(10) DEFAULT NULL,
  `report_fail` int(10) DEFAULT NULL,
  `create_time` bigint(14) DEFAULT NULL,
  `channelId` int(6) DEFAULT NULL,
  `countryId` int(10) DEFAULT '0' COMMENT '国家地区编号',
  PRIMARY KEY (`id`),
  KEY `cid_time` (`create_time`,`channelId`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=36299 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `department_menu`
-- ----------------------------
DROP TABLE IF EXISTS `department_menu`;
CREATE TABLE `department_menu` (
  `departid` int(11) DEFAULT NULL COMMENT '角色',
  `menuid` int(11) DEFAULT NULL COMMENT '菜单编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单关联表';

-- ----------------------------
--  Table structure for `gateway_channel`
-- ----------------------------
DROP TABLE IF EXISTS `gateway_channel`;
CREATE TABLE `gateway_channel` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '通道ID',
  `gateway_template` int(1) DEFAULT '0' COMMENT '网关配置模板(0为任意,1为cmpp,3为smpp,4为http)',
  `gateway_port` int(10) DEFAULT '0' COMMENT '网关端口号',
  `gateway_ip` varchar(20) DEFAULT NULL COMMENT '网关IP地址',
  `gateway_account` varchar(30) DEFAULT NULL COMMENT '账号',
  `gateway_pass` varchar(50) DEFAULT NULL COMMENT '密码',
  `gateway_url` varchar(200) DEFAULT NULL COMMENT '网关url地址(主要用于http接口的网关)',
  `local_ip` varchar(20) DEFAULT NULL COMMENT '本地ip(主要用于sgip协议)',
  `local_delay` int(5) DEFAULT '10' COMMENT '每次发送间隔时间(单位毫秒)',
  `local_read_num` int(5) DEFAULT '100' COMMENT '网关提供的流速',
  `rabbit_port` int(10) DEFAULT '0' COMMENT '消息队列端口',
  `rabbit_ip` varchar(200) DEFAULT NULL COMMENT '消息队列IP',
  `rabbit_account` varchar(30) DEFAULT NULL COMMENT '消息队列账号',
  `channel_provider` varchar(50) DEFAULT NULL COMMENT '通道提供商',
  `rabbit_pass` varchar(50) DEFAULT NULL COMMENT '消息队列密码',
  `channel_name` varchar(50) DEFAULT NULL COMMENT '通道名称',
  `status` int(1) DEFAULT '0' COMMENT '通道状态0为正常,1为停止',
  `price` double(10,2) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `send_words_len` int(10) DEFAULT '0' COMMENT '计费字数',
  `send_words_maxlen` int(10) DEFAULT '0' COMMENT '最大字数',
  `sign_position` int(1) DEFAULT '0' COMMENT '签名位置(0为任意,1为前置,2为后置)',
  `record_type` int(1) DEFAULT '0' COMMENT '报备方式(0为无,1为先报备后发,2为先发后报备)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT 'url',
  `pid` int(11) DEFAULT NULL COMMENT '父ID',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `grade` int(11) DEFAULT NULL COMMENT '级别',
  `createDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '不同部门ID变动，需要修改ConstantSys中角色常量',
  `roleName` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `pid` int(10) DEFAULT '0' COMMENT '上级id',
  `status` int(1) DEFAULT NULL COMMENT '角色状态：0为正常；－1为禁用',
  `description` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `createDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='后台人员角色表';

-- ----------------------------
--  Table structure for `sms_admin`
-- ----------------------------
DROP TABLE IF EXISTS `sms_admin`;
CREATE TABLE `sms_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(96) CHARACTER SET utf8 NOT NULL COMMENT '密码',
  `username` varchar(120) CHARACTER SET utf8 NOT NULL COMMENT '账号',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态:0为启用;-1为禁用',
  `phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号码',
  `logtime` timestamp NULL DEFAULT NULL COMMENT '登录时间',
  `createDate` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `realname` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '真实姓名',
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `sms_admin_logs`
-- ----------------------------
DROP TABLE IF EXISTS `sms_admin_logs`;
CREATE TABLE `sms_admin_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `user` varchar(90) CHARACTER SET utf8 NOT NULL,
  `time` datetime DEFAULT NULL,
  `ip` varchar(45) CHARACTER SET utf8 NOT NULL,
  `log` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=486 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `sms_black_area`
-- ----------------------------
DROP TABLE IF EXISTS `sms_black_area`;
CREATE TABLE `sms_black_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '类型：0用户 1通道',
  `provincecode` int(11) NOT NULL COMMENT '屏蔽省份',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `channel_id` int(11) DEFAULT NULL COMMENT '通道ID',
  `route_channel` int(11) DEFAULT NULL COMMENT '触发屏蔽地区后路由通道',
  `addtime` bigint(30) DEFAULT NULL COMMENT '创建时间',
  `citycode` int(11) DEFAULT '0' COMMENT '屏蔽地市，0为屏蔽所有',
  `exc_uid` varchar(4000) DEFAULT NULL COMMENT '例外的uid',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=872 DEFAULT CHARSET=utf8 COMMENT='屏蔽号段';

-- ----------------------------
--  Table structure for `sms_black_mobile`
-- ----------------------------
DROP TABLE IF EXISTS `sms_black_mobile`;
CREATE TABLE `sms_black_mobile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` bigint(13) NOT NULL COMMENT '黑名单号码',
  `group_id` int(11) DEFAULT NULL COMMENT '策略组Id',
  `addtime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `mobileType` int(4) DEFAULT '0' COMMENT '号码类型，危险等级',
  `relation` varchar(1000) DEFAULT NULL COMMENT '记录号码出现在其他地方的信息',
  `level` int(4) DEFAULT '0' COMMENT '危险等级',
  `country` varchar(30) DEFAULT NULL COMMENT '郭家驹',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile_groupid_unique` (`mobile`,`group_id`),
  KEY `groupIid_mobileType` (`group_id`,`mobileType`) USING BTREE,
  KEY `group_id` (`group_id`,`addtime`) USING BTREE,
  KEY `mobile` (`mobile`)
) ENGINE=MyISAM AUTO_INCREMENT=56018838 DEFAULT CHARSET=utf8 COMMENT='黑名单';

-- ----------------------------
--  Table structure for `sms_black_mobile_type`
-- ----------------------------
DROP TABLE IF EXISTS `sms_black_mobile_type`;
CREATE TABLE `sms_black_mobile_type` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `level` int(4) NOT NULL,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `levelIndex` (`level`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_black_sign`
-- ----------------------------
DROP TABLE IF EXISTS `sms_black_sign`;
CREATE TABLE `sms_black_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sign` varchar(50) DEFAULT NULL COMMENT '黑签名',
  `group_id` int(11) DEFAULT NULL COMMENT '策略组Id',
  `addtime` bigint(30) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sign_unique` (`sign`,`group_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3077 DEFAULT CHARSET=utf8 COMMENT='黑签名';

-- ----------------------------
--  Table structure for `sms_black_words`
-- ----------------------------
DROP TABLE IF EXISTS `sms_black_words`;
CREATE TABLE `sms_black_words` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `words` varchar(128) DEFAULT NULL COMMENT '屏蔽词',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `group_id` int(11) DEFAULT NULL COMMENT '策略组Id',
  `addtime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `screenType` int(11) DEFAULT '7' COMMENT '屏蔽词类型',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8381 DEFAULT CHARSET=utf8 COMMENT='屏蔽词';

-- ----------------------------
--  Table structure for `sms_black_words_type`
-- ----------------------------
DROP TABLE IF EXISTS `sms_black_words_type`;
CREATE TABLE `sms_black_words_type` (
  `id` int(7) NOT NULL AUTO_INCREMENT,
  `no` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `level` int(2) DEFAULT NULL,
  `createdDate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_caiwu_accpass`
-- ----------------------------
DROP TABLE IF EXISTS `sms_caiwu_accpass`;
CREATE TABLE `sms_caiwu_accpass` (
  `cpwd` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_channel_group`
-- ----------------------------
DROP TABLE IF EXISTS `sms_channel_group`;
CREATE TABLE `sms_channel_group` (
  `group_channel_id` int(11) NOT NULL COMMENT '通道组ID',
  `channel_id` int(11) NOT NULL COMMENT '子通道ID',
  `priority` int(2) NOT NULL DEFAULT '0' COMMENT '通道优先级别，数值越大级别越高',
  `create_time` bigint(30) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`group_channel_id`,`channel_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='通道组关联表';

-- ----------------------------
--  Table structure for `sms_channel_priceMenu`
-- ----------------------------
DROP TABLE IF EXISTS `sms_channel_priceMenu`;
CREATE TABLE `sms_channel_priceMenu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `channelId` int(10) DEFAULT NULL COMMENT '通道id',
  `countryId` int(5) DEFAULT NULL COMMENT '国家id',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `price` double(5,3) DEFAULT '0.000' COMMENT '价格',
  `stat` int(1) DEFAULT NULL COMMENT '状态 0正常,1暂停发送',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `sms_charge_records`
-- ----------------------------
DROP TABLE IF EXISTS `sms_charge_records`;
CREATE TABLE `sms_charge_records` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `uid` int(10) DEFAULT NULL COMMENT '用户编号',
  `amount` decimal(16,2) DEFAULT '0.00' COMMENT '充值金额',
  `chargeType` int(1) DEFAULT '0' COMMENT '0为人工充值,1为系统返还,2其它',
  `stat` int(1) DEFAULT '0' COMMENT '0为到账,1为待收款,2为失败',
  `gift` decimal(16,2) DEFAULT '0.00' COMMENT '赠送',
  `info` varchar(255) DEFAULT NULL COMMENT '到账说明',
  `remark` varchar(250) DEFAULT NULL COMMENT '备注',
  `add_uid` varchar(10) DEFAULT NULL COMMENT '操作人',
  `create_time` varchar(20) DEFAULT NULL COMMENT '充值时间',
  `operate_type` int(1) DEFAULT '0' COMMENT '操作类型，0 后台操作，1前台操作，2其他',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `sms_country_infos`
-- ----------------------------
DROP TABLE IF EXISTS `sms_country_infos`;
CREATE TABLE `sms_country_infos` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `country_en` varchar(50) DEFAULT NULL COMMENT '国家名，英文',
  `country_ab` varchar(5) DEFAULT NULL COMMENT '国际域名缩写',
  `countryCode` varchar(10) DEFAULT NULL COMMENT '国字编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=193 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `sms_customer`
-- ----------------------------
DROP TABLE IF EXISTS `sms_customer`;
CREATE TABLE `sms_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) DEFAULT NULL COMMENT '客户名称',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `link_man` varchar(50) DEFAULT NULL COMMENT '联系人',
  `tel` varchar(50) DEFAULT NULL COMMENT 'crm_customer',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `qq` varchar(255) DEFAULT NULL COMMENT 'QQ',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sales_id` int(11) DEFAULT NULL COMMENT '业务员ID',
  `sales` varchar(255) DEFAULT NULL COMMENT '业务员姓名',
  `create_date` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `stat` int(1) DEFAULT '0' COMMENT '状态 0开启，1禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='客户表';

-- ----------------------------
--  Table structure for `sms_fishing_manage`
-- ----------------------------
DROP TABLE IF EXISTS `sms_fishing_manage`;
CREATE TABLE `sms_fishing_manage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phones` varchar(128) DEFAULT NULL COMMENT '手机号码',
  `addTime` varchar(20) DEFAULT NULL COMMENT '提交时间',
  `content` varchar(500) DEFAULT NULL COMMENT '短信内容',
  `mark` varchar(500) DEFAULT NULL COMMENT '备注',
  `operator` varchar(50) DEFAULT NULL COMMENT '添加人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_mobile_area`
-- ----------------------------
DROP TABLE IF EXISTS `sms_mobile_area`;
CREATE TABLE `sms_mobile_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(11) DEFAULT NULL COMMENT '号段',
  `countryCode` varchar(10) DEFAULT NULL COMMENT '国字区号',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `region` varchar(50) DEFAULT NULL COMMENT '区',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `operator` varchar(50) DEFAULT NULL COMMENT '运营商',
  `regionCode` int(5) DEFAULT NULL COMMENT '区编码',
  `cityCode` int(5) DEFAULT NULL COMMENT '城市编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `sms_price_template`
-- ----------------------------
DROP TABLE IF EXISTS `sms_price_template`;
CREATE TABLE `sms_price_template` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '模板名称',
  `description` varchar(250) DEFAULT NULL COMMENT '描述',
  `status` int(1) DEFAULT '0' COMMENT '状态 0 有效 1无效',
  `addTime` varchar(20) DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `sms_price_template_detail`
-- ----------------------------
DROP TABLE IF EXISTS `sms_price_template_detail`;
CREATE TABLE `sms_price_template_detail` (
  `countryId` int(4) NOT NULL COMMENT '国家id',
  `country` varchar(50) DEFAULT NULL,
  `price` double(5,3) DEFAULT '0.000' COMMENT '价格',
  `temp_id` int(8) DEFAULT NULL COMMENT '模板id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `sms_recounted_uid`
-- ----------------------------
DROP TABLE IF EXISTS `sms_recounted_uid`;
CREATE TABLE `sms_recounted_uid` (
  `uid` int(10) DEFAULT '0',
  KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_release_template`
-- ----------------------------
DROP TABLE IF EXISTS `sms_release_template`;
CREATE TABLE `sms_release_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(20) DEFAULT NULL COMMENT '用户ID',
  `content` text COMMENT '模板内容',
  `type` smallint(2) NOT NULL DEFAULT '1' COMMENT '模板类型:0永久 1临时',
  `effectivetime` bigint(14) NOT NULL DEFAULT '0' COMMENT '生效时间',
  `stat` smallint(2) NOT NULL DEFAULT '1' COMMENT '状态:0可用 1不可用',
  `addtime` bigint(14) DEFAULT NULL COMMENT '添加时间',
  `aid` varchar(32) DEFAULT NULL COMMENT '添加人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_rpt_backups`
-- ----------------------------
DROP TABLE IF EXISTS `sms_rpt_backups`;
CREATE TABLE `sms_rpt_backups` (
  `uid` int(10) DEFAULT '0',
  `mobile` bigint(11) DEFAULT '0',
  `rptcode` varchar(10) DEFAULT NULL,
  `hisId` int(10) DEFAULT '0',
  `pid` bigint(15) DEFAULT '0',
  `rpttime` bigint(14) DEFAULT '0',
  `channelId` int(3) DEFAULT '0',
  `userday` int(8) DEFAULT '0' COMMENT '短信发送日期',
  `stat` int(5) DEFAULT '0',
  `extend1` varchar(50) DEFAULT NULL,
  `extend2` int(10) DEFAULT NULL,
  KEY `uid` (`uid`),
  KEY `mobile` (`mobile`),
  KEY `rptcode` (`rptcode`),
  KEY `hisId` (`hisId`),
  KEY `channelId` (`channelId`),
  KEY `userday` (`userday`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8
/*!50100 PARTITION BY RANGE (`userday`)
(PARTITION p_20170420 VALUES LESS THAN (20170420) ENGINE = MyISAM,
 PARTITION p_20170421 VALUES LESS THAN (20170421) ENGINE = MyISAM,
 PARTITION p_20170422 VALUES LESS THAN (20170422) ENGINE = MyISAM,
 PARTITION p_20170423 VALUES LESS THAN (20170423) ENGINE = MyISAM,
 PARTITION p_20170424 VALUES LESS THAN (20170424) ENGINE = MyISAM,
 PARTITION p_20170425 VALUES LESS THAN (20170425) ENGINE = MyISAM,
 PARTITION p_20170426 VALUES LESS THAN (20170426) ENGINE = MyISAM,
 PARTITION p_20170427 VALUES LESS THAN (20170427) ENGINE = MyISAM,
 PARTITION p_20170428 VALUES LESS THAN (20170428) ENGINE = MyISAM,
 PARTITION p_20170429 VALUES LESS THAN (20170429) ENGINE = MyISAM,
 PARTITION p_20170430 VALUES LESS THAN (20170430) ENGINE = MyISAM,
 PARTITION p_20170501 VALUES LESS THAN (20170501) ENGINE = MyISAM,
 PARTITION p_20170502 VALUES LESS THAN (20170502) ENGINE = MyISAM,
 PARTITION p_20170503 VALUES LESS THAN (20170503) ENGINE = MyISAM,
 PARTITION p_20170504 VALUES LESS THAN (20170504) ENGINE = MyISAM,
 PARTITION p_20170505 VALUES LESS THAN (20170505) ENGINE = MyISAM,
 PARTITION p_20170506 VALUES LESS THAN (20170506) ENGINE = MyISAM,
 PARTITION p_20170507 VALUES LESS THAN (20170507) ENGINE = MyISAM,
 PARTITION p_20170508 VALUES LESS THAN (20170508) ENGINE = MyISAM,
 PARTITION p_20170509 VALUES LESS THAN (20170509) ENGINE = MyISAM,
 PARTITION p_20170510 VALUES LESS THAN (20170510) ENGINE = MyISAM,
 PARTITION p_20170511 VALUES LESS THAN (20170511) ENGINE = MyISAM,
 PARTITION p_20170512 VALUES LESS THAN (20170512) ENGINE = MyISAM,
 PARTITION p_20170513 VALUES LESS THAN (20170513) ENGINE = MyISAM,
 PARTITION p_20170514 VALUES LESS THAN (20170514) ENGINE = MyISAM,
 PARTITION p_20170515 VALUES LESS THAN (20170515) ENGINE = MyISAM,
 PARTITION p_20170516 VALUES LESS THAN (20170516) ENGINE = MyISAM,
 PARTITION p_20170517 VALUES LESS THAN (20170517) ENGINE = MyISAM,
 PARTITION p_20170518 VALUES LESS THAN (20170518) ENGINE = MyISAM,
 PARTITION p_20170519 VALUES LESS THAN (20170519) ENGINE = MyISAM,
 PARTITION p_20170520 VALUES LESS THAN (20170520) ENGINE = MyISAM,
 PARTITION p_20170521 VALUES LESS THAN (20170521) ENGINE = MyISAM,
 PARTITION p_20170522 VALUES LESS THAN (20170522) ENGINE = MyISAM,
 PARTITION p_20170523 VALUES LESS THAN (20170523) ENGINE = MyISAM,
 PARTITION p_20170524 VALUES LESS THAN (20170524) ENGINE = MyISAM,
 PARTITION p_20170525 VALUES LESS THAN (20170525) ENGINE = MyISAM,
 PARTITION p_20170526 VALUES LESS THAN (20170526) ENGINE = MyISAM,
 PARTITION p_20170527 VALUES LESS THAN (20170527) ENGINE = MyISAM,
 PARTITION p_20170528 VALUES LESS THAN (20170528) ENGINE = MyISAM,
 PARTITION p_20170529 VALUES LESS THAN (20170529) ENGINE = MyISAM,
 PARTITION p_20170530 VALUES LESS THAN (20170530) ENGINE = MyISAM,
 PARTITION p_20170531 VALUES LESS THAN (20170531) ENGINE = MyISAM,
 PARTITION p_20170601 VALUES LESS THAN (20170601) ENGINE = MyISAM,
 PARTITION p_20170602 VALUES LESS THAN (20170602) ENGINE = MyISAM,
 PARTITION p_20170603 VALUES LESS THAN (20170603) ENGINE = MyISAM,
 PARTITION p_20170604 VALUES LESS THAN (20170604) ENGINE = MyISAM,
 PARTITION p_20170605 VALUES LESS THAN (20170605) ENGINE = MyISAM,
 PARTITION p_20170606 VALUES LESS THAN (20170606) ENGINE = MyISAM,
 PARTITION p_20170607 VALUES LESS THAN (20170607) ENGINE = MyISAM,
 PARTITION p_20170608 VALUES LESS THAN (20170608) ENGINE = MyISAM,
 PARTITION p_20170609 VALUES LESS THAN (20170609) ENGINE = MyISAM,
 PARTITION p_20170610 VALUES LESS THAN (20170610) ENGINE = MyISAM,
 PARTITION p_20170611 VALUES LESS THAN (20170611) ENGINE = MyISAM,
 PARTITION p_20170612 VALUES LESS THAN (20170612) ENGINE = MyISAM,
 PARTITION p_20170613 VALUES LESS THAN (20170613) ENGINE = MyISAM,
 PARTITION p_20170614 VALUES LESS THAN (20170614) ENGINE = MyISAM,
 PARTITION p_20170615 VALUES LESS THAN (20170615) ENGINE = MyISAM,
 PARTITION p_20170616 VALUES LESS THAN (20170616) ENGINE = MyISAM,
 PARTITION p_20170617 VALUES LESS THAN (20170617) ENGINE = MyISAM,
 PARTITION p_20170618 VALUES LESS THAN (20170618) ENGINE = MyISAM,
 PARTITION p_20170619 VALUES LESS THAN (20170619) ENGINE = MyISAM,
 PARTITION p_20170620 VALUES LESS THAN (20170620) ENGINE = MyISAM,
 PARTITION p_20170621 VALUES LESS THAN (20170621) ENGINE = MyISAM,
 PARTITION p_20170622 VALUES LESS THAN (20170622) ENGINE = MyISAM,
 PARTITION p_20170623 VALUES LESS THAN (20170623) ENGINE = MyISAM,
 PARTITION p_20170624 VALUES LESS THAN (20170624) ENGINE = MyISAM,
 PARTITION p_20170625 VALUES LESS THAN (20170625) ENGINE = MyISAM,
 PARTITION p_20170626 VALUES LESS THAN (20170626) ENGINE = MyISAM,
 PARTITION p_20170627 VALUES LESS THAN (20170627) ENGINE = MyISAM,
 PARTITION p_20170628 VALUES LESS THAN (20170628) ENGINE = MyISAM,
 PARTITION p_20170629 VALUES LESS THAN (20170629) ENGINE = MyISAM,
 PARTITION p_20170630 VALUES LESS THAN (20170630) ENGINE = MyISAM,
 PARTITION p_20170701 VALUES LESS THAN (20170701) ENGINE = MyISAM,
 PARTITION p_20170702 VALUES LESS THAN (20170702) ENGINE = MyISAM,
 PARTITION p_20170703 VALUES LESS THAN (20170703) ENGINE = MyISAM,
 PARTITION p_20170704 VALUES LESS THAN (20170704) ENGINE = MyISAM,
 PARTITION p_20170705 VALUES LESS THAN (20170705) ENGINE = MyISAM,
 PARTITION p_20170706 VALUES LESS THAN (20170706) ENGINE = MyISAM,
 PARTITION p_20170707 VALUES LESS THAN (20170707) ENGINE = MyISAM,
 PARTITION p_20170708 VALUES LESS THAN (20170708) ENGINE = MyISAM,
 PARTITION p_20170709 VALUES LESS THAN (20170709) ENGINE = MyISAM,
 PARTITION p_20170710 VALUES LESS THAN (20170710) ENGINE = MyISAM,
 PARTITION p_20170711 VALUES LESS THAN (20170711) ENGINE = MyISAM,
 PARTITION p_20170712 VALUES LESS THAN (20170712) ENGINE = MyISAM,
 PARTITION p_20170713 VALUES LESS THAN (20170713) ENGINE = MyISAM,
 PARTITION p_20170714 VALUES LESS THAN (20170714) ENGINE = MyISAM,
 PARTITION p_20170715 VALUES LESS THAN (20170715) ENGINE = MyISAM,
 PARTITION p_20170716 VALUES LESS THAN (20170716) ENGINE = MyISAM,
 PARTITION p_20170717 VALUES LESS THAN (20170717) ENGINE = MyISAM,
 PARTITION p_20170718 VALUES LESS THAN (20170718) ENGINE = MyISAM,
 PARTITION p_20170719 VALUES LESS THAN (20170719) ENGINE = MyISAM,
 PARTITION p_20170720 VALUES LESS THAN (20170720) ENGINE = MyISAM,
 PARTITION p_20170721 VALUES LESS THAN (20170721) ENGINE = MyISAM,
 PARTITION p_20170722 VALUES LESS THAN (20170722) ENGINE = MyISAM,
 PARTITION p_20170723 VALUES LESS THAN (20170723) ENGINE = MyISAM,
 PARTITION p_20170724 VALUES LESS THAN (20170724) ENGINE = MyISAM,
 PARTITION p_20170725 VALUES LESS THAN (20170725) ENGINE = MyISAM,
 PARTITION p_20170726 VALUES LESS THAN (20170726) ENGINE = MyISAM,
 PARTITION p_20170727 VALUES LESS THAN (20170727) ENGINE = MyISAM,
 PARTITION p_20170728 VALUES LESS THAN (20170728) ENGINE = MyISAM,
 PARTITION p_20170729 VALUES LESS THAN (20170729) ENGINE = MyISAM,
 PARTITION p_20170730 VALUES LESS THAN (20170730) ENGINE = MyISAM,
 PARTITION p_20170731 VALUES LESS THAN (20170731) ENGINE = MyISAM,
 PARTITION p_20180118000000 VALUES LESS THAN (20180118000000) ENGINE = MyISAM) */;

-- ----------------------------
--  Table structure for `sms_rpt_ratio_config`
-- ----------------------------
DROP TABLE IF EXISTS `sms_rpt_ratio_config`;
CREATE TABLE `sms_rpt_ratio_config` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL DEFAULT '0' COMMENT '用户编号',
  `rate` int(2) DEFAULT '0' COMMENT '扣量比(比如是30%   值则是30)',
  `defalt` int(10) DEFAULT '100' COMMENT '基础值,即大于该值时开始扣量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_send_history_unknown`
-- ----------------------------
DROP TABLE IF EXISTS `sms_send_history_unknown`;
CREATE TABLE `sms_send_history_unknown` (
  `id` bigint(30) NOT NULL,
  `senddate` bigint(20) NOT NULL COMMENT '提交时间',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `mobile` bigint(30) DEFAULT NULL COMMENT '手机号码',
  `channel` smallint(6) NOT NULL COMMENT '通道ID',
  `content` varchar(1000) CHARACTER SET utf8 DEFAULT NULL COMMENT '内容',
  `contentNum` int(2) NOT NULL COMMENT '内容条数',
  `succ` int(2) DEFAULT '0' COMMENT '提交成功数量',
  `fail` int(2) DEFAULT '0' COMMENT '提交失败数量',
  `mtstat` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '屏蔽词',
  `pid` bigint(20) NOT NULL COMMENT '批次ID',
  `grade` smallint(6) NOT NULL DEFAULT '3',
  `expid` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '拓展码',
  `location` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '归属地',
  `arrive_succ` int(11) DEFAULT '0' COMMENT '回执成功条数',
  `arrive_fail` int(11) DEFAULT '0' COMMENT '回执失败',
  `stat` int(1) DEFAULT NULL,
  `user_price` double(10,2) DEFAULT '0.00' COMMENT '用户单价',
  `channel_price` double(10,2) DEFAULT '0.00' COMMENT '通道单价',
  PRIMARY KEY (`senddate`),
  KEY `id` (`id`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE,
  KEY `channel` (`channel`) USING BTREE,
  KEY `mobile` (`mobile`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `sms_send_timeing`
-- ----------------------------
DROP TABLE IF EXISTS `sms_send_timeing`;
CREATE TABLE `sms_send_timeing` (
  `uid` int(10) NOT NULL,
  `mobile` bigint(15) DEFAULT NULL,
  `senddate` bigint(30) DEFAULT NULL,
  `submitdate` bigint(30) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `contentNum` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `sms_sign_channel_black`
-- ----------------------------
DROP TABLE IF EXISTS `sms_sign_channel_black`;
CREATE TABLE `sms_sign_channel_black` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `store` varchar(30) NOT NULL,
  `expend` varchar(15) DEFAULT NULL,
  `expend2` varchar(15) DEFAULT NULL,
  `addtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(1) DEFAULT '0',
  `channel` int(11) DEFAULT '0',
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`,`store`) USING BTREE,
  KEY `status` (`channel`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=273 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_strategy_group`
-- ----------------------------
DROP TABLE IF EXISTS `sms_strategy_group`;
CREATE TABLE `sms_strategy_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '策略组名称',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `type` int(11) DEFAULT NULL COMMENT '策略组类型：1.系统屏蔽词 2审核屏蔽词 3系统黑名单  4屏蔽地区 5系统白名单 6系统白签名',
  `isDefault` int(1) DEFAULT '0' COMMENT '是否默认勾选该策略组',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_temp_priceMenu`
-- ----------------------------
DROP TABLE IF EXISTS `sms_temp_priceMenu`;
CREATE TABLE `sms_temp_priceMenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `countryId` int(4) DEFAULT NULL COMMENT '国家编号',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `channelId` int(5) DEFAULT NULL COMMENT '通道编号',
  `channel` varchar(50) DEFAULT NULL COMMENT '通道名称',
  `price` double(6,2) DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `sms_transferred_hisid`
-- ----------------------------
DROP TABLE IF EXISTS `sms_transferred_hisid`;
CREATE TABLE `sms_transferred_hisid` (
  `hisid` bigint(15) NOT NULL,
  `userday` int(10) DEFAULT NULL,
  KEY `userday` (`userday`),
  KEY `hisid` (`hisid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_user`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user`;
CREATE TABLE `sms_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `pwd` varchar(96) DEFAULT NULL COMMENT '32位MD5加密的密码',
  `dpwd` varchar(20) DEFAULT NULL COMMENT '原密码',
  `company` varchar(120) DEFAULT NULL COMMENT '企业名称',
  `phone` varchar(30) DEFAULT NULL COMMENT '手机号码',
  `tel` varchar(40) DEFAULT NULL COMMENT '联系电话',
  `mail` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `linkman` varchar(48) DEFAULT NULL COMMENT '联系人',
  `address` varchar(200) DEFAULT NULL COMMENT '联系地址',
  `sales` varchar(60) DEFAULT NULL COMMENT '业务员',
  `time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `add_uid` varchar(11) DEFAULT NULL COMMENT '创建人',
  `stat` smallint(6) DEFAULT '1' COMMENT '状态1有效，2无效',
  `sms` double(11,2) DEFAULT '0.00' COMMENT '余额',
  `priority` smallint(6) DEFAULT '0' COMMENT '优先级',
  `qq` varchar(15) DEFAULT NULL COMMENT 'QQ',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `userkind` smallint(6) DEFAULT '0' COMMENT '用户类型1验证码，2通知，3会员',
  `kefu` varchar(60) DEFAULT NULL COMMENT '客服人员',
  `username` varchar(200) DEFAULT NULL COMMENT '账号',
  `submittype` tinyint(4) DEFAULT '0' COMMENT '短信接入类型：0HTTP 1CMPP 9非接口',
  `parentId` int(10) DEFAULT '0' COMMENT '父ID',
  `customer_id` int(20) DEFAULT NULL COMMENT '客户ID，对应企业信息',
  `usertype` int(1) DEFAULT NULL COMMENT '1,代理；2,终端用户',
  `paytype` int(1) DEFAULT '0' COMMENT '付费方式：0预付费 1后付费',
  `send` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_user_alert`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user_alert`;
CREATE TABLE `sms_user_alert` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `mobile` varchar(42) NOT NULL,
  `num` int(11) NOT NULL,
  `senddate` varchar(5) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_user_consume`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user_consume`;
CREATE TABLE `sms_user_consume` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `send` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '消费金额',
  `unsend` double(11,2) DEFAULT '0.00' COMMENT '未计费金额',
  `date` bigint(8) DEFAULT NULL COMMENT 'yyyyMMdd格式',
  `utype` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid_data_unique` (`uid`,`date`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_user_control`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user_control`;
CREATE TABLE `sms_user_control` (
  `uid` int(10) NOT NULL,
  `isShowRpt` int(1) DEFAULT '0' COMMENT '是否前台显示报告;0为不显示,1为显示',
  `childFun` int(1) DEFAULT '0' COMMENT '是否开通子账号;0为不开通;1为开通',
  `childNum` int(5) DEFAULT '0' COMMENT '子账号数量;在childfun为1是起作用',
  `isRelease` int(1) DEFAULT '1' COMMENT '是否审核;1为不审核;0为审核',
  `releaseNum` int(5) DEFAULT '0' COMMENT '短信审核数量启始值;在isRelease为0时起作用',
  `repeatFilter` int(1) DEFAULT '0' COMMENT '重号过滤;0为不过滤;1为10分钟;2为1小时;3为24小时',
  `repeatNum` int(5) DEFAULT '0' COMMENT '重号过滤数量;在repeatFilter大于0时起作用',
  `signPosition` int(1) DEFAULT '1' COMMENT '签名位置;1为前置;2为后置;3为任意',
  `expidSign` int(1) DEFAULT '0' COMMENT '签名方式;0为强制签名;1为自定义拓展',
  `proxyIp` varchar(400) DEFAULT NULL COMMENT '绑定IP;最大可以绑定10个,用逗号分隔',
  `submitType` int(1) DEFAULT NULL COMMENT '短信提交方式 0HTTP, 1CMPP, 9非接口',
  `speed` int(5) DEFAULT '0' COMMENT '用户每秒速度限制',
  `blackAll` int(1) DEFAULT '0' COMMENT '是否过滤全量黑名单',
  `repeatSign` int(1) DEFAULT '0' COMMENT '是否可以重复签名',
  `repeatSignNum` int(5) DEFAULT '0' COMMENT '重复签名数量',
  `channelId` int(10) DEFAULT NULL COMMENT '通道id',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_user_day_count`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user_day_count`;
CREATE TABLE `sms_user_day_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `total` int(8) DEFAULT NULL,
  `fail` int(8) DEFAULT NULL,
  `arrive_succ` int(8) DEFAULT NULL,
  `arrive_fail` int(8) DEFAULT NULL,
  `time` bigint(12) DEFAULT NULL,
  `isReturn` int(1) DEFAULT '0' COMMENT '是否返还',
  `countryId` int(11) DEFAULT '0' COMMENT '国家地区编号',
  `userPrice` decimal(10,2) DEFAULT '0.00' COMMENT '用户金额',
  `channelPrice` decimal(10,2) DEFAULT '0.00' COMMENT '通道金额',
  `sms` decimal(10,2) DEFAULT '0.00' COMMENT '用户余额',
  PRIMARY KEY (`id`),
  KEY `uid_time` (`uid`,`time`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=34659 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_user_logs`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user_logs`;
CREATE TABLE `sms_user_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  `ip` varchar(45) NOT NULL,
  `log` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_user_priceMenu`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user_priceMenu`;
CREATE TABLE `sms_user_priceMenu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) DEFAULT NULL COMMENT '用户id',
  `countryId` int(5) DEFAULT NULL COMMENT '国家id',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `price` double(5,3) DEFAULT '0.000' COMMENT '价格',
  `stat` int(1) DEFAULT NULL COMMENT '发送状态 0正常,1暂停发送',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `sms_user_route`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user_route`;
CREATE TABLE `sms_user_route` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `routetype` tinyint(4) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `routechannel` int(11) NOT NULL,
  `aid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `addtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `mtype` smallint(6) DEFAULT '0' COMMENT '运营商类型，0代表所有',
  `contentType` int(1) NOT NULL DEFAULT '0' COMMENT '关键词类型 0 关键词 1 签名 ',
  `province` int(5) DEFAULT NULL COMMENT '路由省份',
  `city` int(5) DEFAULT NULL COMMENT '路由城市',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_user_send_model`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user_send_model`;
CREATE TABLE `sms_user_send_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `addtime` datetime DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_user_send_timeing`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user_send_timeing`;
CREATE TABLE `sms_user_send_timeing` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `mtype` smallint(6) NOT NULL,
  `sendtime` bigint(30) NOT NULL,
  `submitTime` bigint(30) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `mobile` bigint(15) NOT NULL,
  `channel` smallint(6) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `content_num` smallint(6) NOT NULL,
  `mobile_num` int(11) NOT NULL,
  `stat` smallint(6) NOT NULL,
  `isrelease` smallint(6) NOT NULL,
  `pid` int(11) NOT NULL,
  `period` smallint(6) NOT NULL COMMENT '周期类型，0天 1周 2月',
  `period_num` smallint(6) NOT NULL COMMENT '周期数，配合grade使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_user_sending`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user_sending`;
CREATE TABLE `sms_user_sending` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mtype` smallint(6) NOT NULL DEFAULT '0',
  `senddate` bigint(10) DEFAULT NULL,
  `uid` int(11) NOT NULL DEFAULT '0',
  `mobile` bigint(15) NOT NULL DEFAULT '0',
  `channel` int(11) NOT NULL DEFAULT '0',
  `content` text,
  `content_num` smallint(6) NOT NULL,
  `stat` smallint(6) NOT NULL DEFAULT '0',
  `release` smallint(6) NOT NULL DEFAULT '0',
  `pid` int(11) NOT NULL DEFAULT '0',
  `grade` smallint(6) NOT NULL DEFAULT '0',
  `expid` varchar(20) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `hisids` int(10) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `hand_stat` int(11) DEFAULT '0',
  `countryId` int(4) DEFAULT '0' COMMENT '国家id',
  PRIMARY KEY (`id`),
  KEY `grade` (`grade`) USING BTREE,
  KEY `channel` (`channel`) USING BTREE,
  KEY `mobile` (`mobile`) USING BTREE,
  KEY `uid` (`uid`),
  KEY `mtype` (`mtype`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_user_sending_release`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user_sending_release`;
CREATE TABLE `sms_user_sending_release` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `mtype` smallint(6) NOT NULL,
  `senddate` bigint(14) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `mobile` bigint(15) NOT NULL,
  `channel` int(11) NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `content_num` smallint(6) NOT NULL DEFAULT '0' COMMENT '内容条数',
  `stat` smallint(6) NOT NULL,
  `release` smallint(6) NOT NULL,
  `pid` int(11) NOT NULL,
  `grade` smallint(6) NOT NULL,
  `expid` varchar(20) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `hisids` int(10) DEFAULT NULL,
  `mdstr` varchar(32) DEFAULT NULL,
  `handstat` tinyint(4) DEFAULT '0' COMMENT '0待审核，1审核通过，2审核驳回',
  `location` varchar(50) DEFAULT NULL,
  `screenType` int(7) DEFAULT '0' COMMENT '屏蔽词类型',
  `level` int(4) DEFAULT NULL,
  `countryId` int(4) DEFAULT '0' COMMENT '国家id',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`) USING BTREE,
  KEY `channel` (`channel`) USING BTREE,
  KEY `countid` (`mdstr`) USING BTREE,
  KEY `handstat` (`handstat`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_user_signstore`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user_signstore`;
CREATE TABLE `sms_user_signstore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `store` varchar(30) NOT NULL,
  `expend` varchar(50) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '报备状态 0 未报 1 已报',
  `userstat` tinyint(4) NOT NULL,
  `signtime` varchar(30) DEFAULT NULL COMMENT '报备时间',
  `addtime` varchar(30) DEFAULT NULL COMMENT '添加时间',
  `type` int(1) DEFAULT NULL COMMENT '1,通道签名，2平台签名',
  `channel` int(4) DEFAULT NULL COMMENT '通道ID',
  `expendqd` varchar(50) DEFAULT NULL COMMENT '渠道自增长字段',
  `expend2` varchar(50) DEFAULT NULL COMMENT '普通用户系统生成拓展，用于确定自增长最大值',
  `userexpend` varchar(20) DEFAULT NULL COMMENT '绑定上行推送',
  PRIMARY KEY (`id`),
  UNIQUE KEY `expend` (`expend`,`type`) USING BTREE,
  KEY `uid` (`uid`,`store`,`status`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=137495 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_user_strategy_relation`
-- ----------------------------
DROP TABLE IF EXISTS `sms_user_strategy_relation`;
CREATE TABLE `sms_user_strategy_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '所属用户',
  `group_id` int(11) DEFAULT NULL COMMENT '策略组id',
  `create_date` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '策略类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_white_mobile`
-- ----------------------------
DROP TABLE IF EXISTS `sms_white_mobile`;
CREATE TABLE `sms_white_mobile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` bigint(13) NOT NULL COMMENT '白名单号码',
  `group_id` int(11) DEFAULT NULL COMMENT '策略组Id',
  `addtime` bigint(30) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='白名单';

-- ----------------------------
--  Table structure for `sms_white_sign`
-- ----------------------------
DROP TABLE IF EXISTS `sms_white_sign`;
CREATE TABLE `sms_white_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sign` varchar(30) DEFAULT NULL COMMENT '白签名',
  `group_id` int(11) DEFAULT NULL COMMENT '策略组Id',
  `addtime` bigint(30) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='白签名';

-- ----------------------------
--  Table structure for `sms_words_channel`
-- ----------------------------
DROP TABLE IF EXISTS `sms_words_channel`;
CREATE TABLE `sms_words_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `words` varchar(20) NOT NULL,
  `channel` int(11) NOT NULL,
  `addtime` datetime DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=35472 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sms_words_user`
-- ----------------------------
DROP TABLE IF EXISTS `sms_words_user`;
CREATE TABLE `sms_words_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `words` varchar(50) NOT NULL,
  `addtime` datetime DEFAULT NULL,
  `aid` int(11) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1564 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `syspause` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_black_mobile`
-- ----------------------------
DROP TABLE IF EXISTS `user_black_mobile`;
CREATE TABLE `user_black_mobile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `md` varchar(11) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `bkind` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT '0',
  `remark` varchar(255) DEFAULT NULL,
  `relation` varchar(1000) DEFAULT NULL,
  `mobileType` int(4) DEFAULT '0' COMMENT '号码类型',
  `level` int(4) NOT NULL DEFAULT '0' COMMENT '危险等级',
  PRIMARY KEY (`id`),
  UNIQUE KEY `md_uid_unique` (`uid`,`md`),
  KEY `mobileType` (`mobileType`) USING HASH,
  KEY `level_addTime` (`addtime`,`level`),
  KEY `md` (`md`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_white_mobile`
-- ----------------------------
DROP TABLE IF EXISTS `user_white_mobile`;
CREATE TABLE `user_white_mobile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `md` varchar(255) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  `stat` int(11) DEFAULT '1',
  `remark` varchar(255) DEFAULT NULL,
  `relation` varchar(1000) DEFAULT NULL COMMENT '备注在其他地方出现的记录',
  `mobileType` int(4) DEFAULT NULL COMMENT '危险等级',
  `level` int(4) NOT NULL DEFAULT '0' COMMENT '危险等级',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid_md_unique` (`uid`,`md`) USING BTREE,
  KEY `uid_md_index` (`uid`,`md`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_white_sign`
-- ----------------------------
DROP TABLE IF EXISTS `user_white_sign`;
CREATE TABLE `user_white_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `sign` varchar(255) DEFAULT NULL,
  `codeno` varchar(255) DEFAULT NULL,
  `stat` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
