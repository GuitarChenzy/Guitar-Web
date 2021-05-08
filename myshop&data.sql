/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : myshop

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-07-15 13:46:53
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
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'root', '陈哈哈', '4QrcOUm6Wau+VuBX8g+IPg==', '2018-06-27 16:41:21', '2018-07-14 15:39:27');
INSERT INTO `admin` VALUES ('2', 'czy2', '陈二哈', '4QrcOUm6Wau+VuBX8g+IPg==', '2018-07-05 10:28:53', '2018-07-06 15:31:51');
INSERT INTO `admin` VALUES ('3', 'czy3', '陈三哈', '4QrcOUm6Wau+VuBX8g+IPg==', '2018-07-05 10:29:25', '2018-07-05 14:05:39');
INSERT INTO `admin` VALUES ('4', 'czy4', '陈四哈', '4QrcOUm6Wau+VuBX8g+IPg==', '2018-07-05 10:30:01', '2018-07-06 15:04:19');
INSERT INTO `admin` VALUES ('12', 'czy5', '陈五哈', '4QrcOUm6Wau+VuBX8g+IPg==', '2018-07-06 16:18:59', '2018-07-06 16:26:43');
INSERT INTO `admin` VALUES ('15', 'czy6', '陈六哈', '4QrcOUm6Wau+VuBX8g+IPg==', '2018-07-12 22:02:29', null);

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
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', '1', '1', '1');
INSERT INTO `admin_role` VALUES ('2', '2', '2', '1');
INSERT INTO `admin_role` VALUES ('3', '3', '3', '1');
INSERT INTO `admin_role` VALUES ('4', '4', '4', '1');
INSERT INTO `admin_role` VALUES ('8', '12', '4', '1');
INSERT INTO `admin_role` VALUES ('9', '12', '6', '1');
INSERT INTO `admin_role` VALUES ('10', '12', '7', '1');
INSERT INTO `admin_role` VALUES ('28', '15', '3', '1');

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
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '木吉他', null);
INSERT INTO `category` VALUES ('2', '电吉他', null);
INSERT INTO `category` VALUES ('3', '古典吉他', null);
INSERT INTO `category` VALUES ('4', '特殊吉他', null);
INSERT INTO `category` VALUES ('5', '吉他设备', null);
INSERT INTO `category` VALUES ('6', 'Taylor', '1');
INSERT INTO `category` VALUES ('7', 'MarTin', '1');
INSERT INTO `category` VALUES ('8', 'Fender', '2');
INSERT INTO `category` VALUES ('9', 'Ibanaz', '2');
INSERT INTO `category` VALUES ('10', 'Gilbson-G', '2');
INSERT INTO `category` VALUES ('11', '阿尔达米拉', '3');
INSERT INTO `category` VALUES ('12', '马丁尼', '3');
INSERT INTO `category` VALUES ('13', '12弦吉他', '4');
INSERT INTO `category` VALUES ('14', '尤克里里', '4');
INSERT INTO `category` VALUES ('15', '拾音器', '5');
INSERT INTO `category` VALUES ('16', '吉他琴弦', null);
INSERT INTO `category` VALUES ('17', '音响', '5');
INSERT INTO `category` VALUES ('19', '达达里奥', '16');
INSERT INTO `category` VALUES ('20', 'Elixir', '16');
INSERT INTO `category` VALUES ('24', 'Gibson', '1');
INSERT INTO `category` VALUES ('25', 'Ovation', '1');
INSERT INTO `category` VALUES ('27', 'Breedlove', '1');
INSERT INTO `category` VALUES ('29', 'YAMAHA', '1');
INSERT INTO `category` VALUES ('30', 'Martin-Strings', '16');

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
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES ('9', '17', '9', '2018-07-03 11:58:41');
INSERT INTO `favorite` VALUES ('12', '1', '1', '2018-07-10 11:35:36');
INSERT INTO `favorite` VALUES ('14', '2', '2', '2018-07-10 23:38:15');
INSERT INTO `favorite` VALUES ('15', '14', '2', '2018-07-10 23:38:47');
INSERT INTO `favorite` VALUES ('16', '30', '2', '2018-07-12 14:43:45');
INSERT INTO `favorite` VALUES ('17', '1', '3', '2018-07-12 14:46:55');
INSERT INTO `favorite` VALUES ('18', '4', '3', '2018-07-12 14:47:06');
INSERT INTO `favorite` VALUES ('19', '33', '3', '2018-07-12 14:49:41');
INSERT INTO `favorite` VALUES ('20', '19', '1', '2018-07-12 14:51:43');
INSERT INTO `favorite` VALUES ('21', '22', '9', '2018-07-12 14:52:59');
INSERT INTO `favorite` VALUES ('22', '30', '9', '2018-07-12 14:53:09');
INSERT INTO `favorite` VALUES ('23', '10', '9', '2018-07-12 14:56:04');
INSERT INTO `favorite` VALUES ('24', '7', '1', '2018-07-12 15:31:34');
INSERT INTO `favorite` VALUES ('25', '28', '1', '2018-07-12 21:58:20');
INSERT INTO `favorite` VALUES ('26', '30', '4', '2018-07-14 15:15:01');
INSERT INTO `favorite` VALUES ('27', '36', '4', '2018-07-14 15:15:12');

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
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', 'Taylor 916ce 缺角电箱民谣吉他 ', '38880.00', '6', '39', 'Taylor系列全单吉他，内置拾音器', '/image/goods/Taylor-916ce-fr-2016-1030x580.png', '1', '2018-06-12 10:12:31');
INSERT INTO `goods` VALUES ('2', 'Taylor jsms 民谣吉他 ', '19900.00', '6', '25', 'Taylor系类的jsms款，值得收藏', '/image/goods/Taylor-616ce-brn-fr-2015-1030x580.png', '1', '2018-06-12 10:38:07');
INSERT INTO `goods` VALUES ('3', 'Taylor 616ce 电箱民谣吉他', '21900.00', '6', '34', 'Taylor系列中性价比较高的一款，值得入手', '/image/goods/Taylor-616ce-brn-fr-2015-1030x580.png', '1', '2018-06-16 18:26:09');
INSERT INTO `goods` VALUES ('4', 'D-45 Fire and Ice', '32430.00', '7', '23', '面板：英格曼云杉\r\n背侧板：马达加斯加玫瑰木\r\n指板：FSC人造乌木\r\n琴码：FSC人造乌木\r\n琴颈：桃花心木', '/image/goods/K24ce-front-taylor-guitars-large.jpg', '1', '2018-06-18 22:34:25');
INSERT INTO `goods` VALUES ('5', ' American Vintage ‘65 Stratocaster ', '13800.00', '8', '49', 'American Vintage推出一系列全新的复刻版型号，为这些更真实的Fender历史与文化注入新的生命。', '/image/goods/vsky_0111800800v1_hi-L.jpg', '1', '2018-06-18 22:35:27');
INSERT INTO `goods` VALUES ('6', 'D-16EPD ', '14900.00', '9', '58', 'Inspired by classic Americana art from artist Cassius Marcellus Coolidge, this playful guitar features a 1903 painting called A Friend in Need', '/image/goods/cm0249-dogs_f_new.jpg', '1', '2018-06-18 22:36:42');
INSERT INTO `goods` VALUES ('7', '2018 SJ-200 VS', '36200.00', '24', '36', '全世界顶级民谣的外观和音色', '/image/goods/65a1d847615d5c040e3a9e97c2643429.jpg', '1', '2018-06-18 22:37:46');
INSERT INTO `goods` VALUES ('8', '阿尔达米拉customL2', '4999.00', '11', '69', '古典吉他中的首选之物', '/image/goods/K24ce-front-taylor-guitars-large.jpg', '1', '2018-06-18 22:38:34');
INSERT INTO `goods` VALUES ('9', 'MartuinNi GS Mini American Girl® ', '3999.00', '12', '34', 'Much of the Hummingbirds appeal lies in its versatility.', '/image/goods/taylor-american-girl-sg-mini-2017-1030x713.jpg', '1', '2018-06-18 22:39:24');
INSERT INTO `goods` VALUES ('10', 'Taylor311', '10980.00', '6', '44', '【面板】云杉单板【背板】东印度玫瑰木单板【侧板】东印度玫瑰木单板【琴颈】桃花心【指板】乌木【品点】鲍鱼贝镶嵌【琴码】乌木', '/image/goods/814cetaylor-guitars-large_1.jpg', '1', '2018-06-18 22:40:18');
INSERT INTO `goods` VALUES ('11', '小提琴1', '566.00', '14', '70', '这是一个小提琴的描述', '/image/goods/goods1.jpg', '0', '2018-06-18 22:40:58');
INSERT INTO `goods` VALUES ('14', 'FLEXIBLE CORE SP', '128.00', '30', '84', '指弹专用，手感柔和，易于控制，音色较亮', '/image/goods/20180411120448582.jpg', '1', '2018-07-02 16:30:40');
INSERT INTO `goods` VALUES ('15', 'ACOUSTIC SP', '118.00', '30', '47', '声音刚劲，不失文雅，专为舞台准备', '/image/goods/20180411120410951.jpg', '1', '2018-07-02 16:33:04');
INSERT INTO `goods` VALUES ('16', 'CLASSICAL', '88.00', '30', '63', '精选材质，适合日常各种弹奏水平', '/image/goods/20180411120710343.jpg', '1', '2018-07-02 16:34:06');
INSERT INTO `goods` VALUES ('17', 'GBPC 25周年纪念版', '25880.00', '7', '13', '面板：沙比利\r\n背侧板：沙比利\r\n指板：Black Richlite混合乌木\r\n琴码：Black Richlite混合乌木\r\n琴颈：精选硬木面板：沙比利\r\n背侧板：沙比利\r\n指板：Black Richlite混合乌木\r\n琴码：Black Richlite混合乌木\r\n琴颈：精选硬木', '/image/goods/T5z-standard-honey-burst-front-taylor-guitars-large.jpg', '1', '2018-07-02 16:38:44');
INSERT INTO `goods` VALUES ('18', 'C1K UKE', '899.00', '14', '32', '面板：夏威夷相思木单板\r\n背侧板：夏威夷相思木单板\r\n指板：玫瑰木\r\n琴颈：桃花心木\r\n弦钮：开放式', '/image/goods/20180410105357581.jpg', '1', '2018-07-02 16:44:08');
INSERT INTO `goods` VALUES ('19', '2018 Les Paul Standard HP', '27040.00', '24', '45', '全新的外观，声音以及演奏性能', '/image/goods/083e8217cf0c32c9a1d6add6e2305f14.jpg', '1', '2018-07-12 10:46:40');
INSERT INTO `goods` VALUES ('20', '2018 J-45 Standard', '19630.00', '25', '28', '世界公认的主力民谣', '/image/goods/461e505559e5b2bb0d6d356a8f2b5e7a.jpg', '1', '2018-07-12 10:48:32');
INSERT INTO `goods` VALUES ('21', 'BreedLove-sjo9', '12300.00', '27', '48', '面板：云杉单板背侧板：玫瑰木合板琴颈：桃花心木指板：玫瑰木漆面：亚光', '/image/goods/cfnioqn2412.jpg', '1', '2018-07-12 10:54:42');
INSERT INTO `goods` VALUES ('22', 'SLG200NW', '5823.00', '29', '21', '拥有古典吉他指板宽度的静音吉他，可以在任何地点、任何时间不受限制地进行演奏。 ', '/image/goods/thumb_thumb_SLN200NW1.jpg', '1', '2018-07-12 10:57:27');
INSERT INTO `goods` VALUES ('23', 'APX500III ', '1800.00', '29', '122', '杰出的演奏性能使之成为理想的现场演出用吉他 ', '/image/goods/thumb_APX500III.jpg', '1', '2018-07-12 10:59:10');
INSERT INTO `goods` VALUES ('24', 'APX1000', '4700.00', '29', '77', '杰出的演奏性能使之成为理想的现场演出用吉他 ', '/image/goods/thumb_APX500213123III.jpg', '1', '2018-07-12 11:00:32');
INSERT INTO `goods` VALUES ('25', 'SC-02', '599.00', '17', '50', 'Session Cake SC-02配置了XLR和立体声左（mono）和右（Hi-Z）输入以及动态麦克风的增益控制，让歌手、键盘手、吉他手、贝斯手及其他电子乐器都可以使用。', '/image/goods/thumb_SC02-1.jpg', '1', '2018-07-12 11:01:48');
INSERT INTO `goods` VALUES ('26', 'THR10X', '1980.00', '17', '78', 'THR10X是一台提供强力失真音色的吉他音箱。对于想要获得强劲失真及高增益的吉他手来说，THR10X是再合适不过的选择了', '/image/goods/thumb_THR10X.jpg', '1', '2018-07-12 11:03:00');
INSERT INTO `goods` VALUES ('27', 'THR100HD ', '5100.00', '17', '25', '准备好体验一个全新的音色创造领域，THR100HD将两个独立信号流的放大器装在一个不到5公斤的外壳中。两个放大器都可独立调节5种定制音箱建模，全新的超动态功率放大器与可选择的电子管模拟以及可选的A/AB的拓扑结构，独立的Booster、混响和模拟扬声器，帮助您找到自己想要的完美声音。', '/image/goods/thumb_thrhead_540x540_396x396_08ac65a9de5a7608982d07e0bdc36c26.jpg', '1', '2018-07-12 11:03:54');
INSERT INTO `goods` VALUES ('28', '2018 Hummingbird', '27300.00', '24', '14', '全世界识别度最高的民谣吉他', '/image/goods/ecb41613b5c752783eb3a556ca63386e.jpg', '1', '2018-07-12 11:11:44');
INSERT INTO `goods` VALUES ('29', 'FG820-12单板十二弦吉他原声 ', '2199.00', '13', '13', '12琴弦的吉他，适合特殊演奏与弹唱', '/image/goods/5abc4ed5Nbb49e9fa.jpg', '1', '2018-07-12 11:22:25');
INSERT INTO `goods` VALUES ('30', 'Les Paul Slash Anaconda Burst', '23000.00', '10', '5', 'Slash签名款-限量版', '/image/goods/a6b405aec8b2d248c6b57fd3368a00fd.jpg', '1', '2018-07-12 11:24:32');
INSERT INTO `goods` VALUES ('31', 'EXP15 EXP Phosphor Bronze', '80.00', '19', '162', 'EXP15 是达达里奥最细质的民谣琴弦，非常适合钟爱较柔和音色和易推弦的初学者和乐手使用。', '/image/goods/da_prod_exp15_main_1_0.jpg', '1', '2018-07-12 11:28:59');
INSERT INTO `goods` VALUES ('32', 'EXP16 EXP Phosphor Bronze', '98.00', '19', '126', 'EXP16 是达达里奥最受欢迎的民谣套弦，可达到音量、弹奏力度与舒适的弹奏手感的完美平衡。', '/image/goods/da_prod_exp16_main_1_0.jpg', '1', '2018-07-12 11:29:49');
INSERT INTO `goods` VALUES ('33', 'EXL110 Nickel Wound', '198.00', '19', '43', 'EXL110 是达达里奥的畅销套弦，音色完美，柔韧性卓越，使用寿命长，适合大部分电吉他。', '/image/goods/exl110_main.jpg', '1', '2018-07-12 11:30:42');
INSERT INTO `goods` VALUES ('34', '原声磷铜，NANOWEB® 覆膜', '121.00', '20', '186', '伊利克斯琴弦 (Elixir® Strings) 原声磷铜（带 NANOWEB 覆膜）带来独一无二、欢快明亮的磷铜音色，同时有效延长音质寿命。', '/image/goods/acoustic-phos-bronze-16052-Front.jpg', '1', '2018-07-12 11:32:53');
INSERT INTO `goods` VALUES ('35', '原声 80/20 铜，POLYWEB® 覆膜', '110.00', '20', '162', '伊利克斯® 原声 80/20 铜琴弦（带 POLYWEB 覆膜）带来温润的“现场演奏”音质，同时有效延长音质寿命，而这正是演奏者对伊利克斯琴弦的期望所在。', '/image/goods/acoustic-80-20-polyweb-11050-Front.jpg', '1', '2018-07-12 11:35:13');
INSERT INTO `goods` VALUES ('36', '2018 SG Standard T', '11700.00', '10', '19', '最经典的SG', '/image/goods/3fb38878a187b6cede30e4e3d9814e72.jpg', '1', '2018-07-12 11:36:57');
INSERT INTO `goods` VALUES ('37', '2018 Hummingbird Rosewood AG', '18330.00', '24', '37', '薄箱体，玫瑰木缺角电箱民谣', '/image/goods/30f0c3f85cf199d03dd408ad7d2fa360.jpg', '1', '2018-07-12 11:38:07');

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
-- Records of home
-- ----------------------------

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
-- Records of numcoin
-- ----------------------------

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
-- Records of orderinfo
-- ----------------------------
INSERT INTO `orderinfo` VALUES ('1', '18062914110001', '1', '3', '68.89');
INSERT INTO `orderinfo` VALUES ('2', '18062914110001', '2', '1', '89.99');
INSERT INTO `orderinfo` VALUES ('3', '18062914380001', '5', '2', '288.00');
INSERT INTO `orderinfo` VALUES ('4', '18062914380001', '9', '14', '5.88');
INSERT INTO `orderinfo` VALUES ('5', '18062914480001', '4', '1', '56.88');
INSERT INTO `orderinfo` VALUES ('6', '18062915050001', '8', '8', '9.88');
INSERT INTO `orderinfo` VALUES ('7', '18062915050001', '10', '2', '10980.00');
INSERT INTO `orderinfo` VALUES ('8', '18070311590001', '17', '2', '25880.00');
INSERT INTO `orderinfo` VALUES ('9', '18070909490001', '5', '1', '13800.00');
INSERT INTO `orderinfo` VALUES ('10', '18071010360001', '14', '1', '128.00');
INSERT INTO `orderinfo` VALUES ('11', '18071117150001', '2', '2', '19900.00');
INSERT INTO `orderinfo` VALUES ('12', '18071117150001', '5', '5', '13800.00');
INSERT INTO `orderinfo` VALUES ('13', '18071214440001', '2', '2', '19900.00');
INSERT INTO `orderinfo` VALUES ('14', '18071214440001', '36', '1', '11700.00');
INSERT INTO `orderinfo` VALUES ('15', '18071214460001', '34', '6', '121.00');
INSERT INTO `orderinfo` VALUES ('16', '18071214460001', '26', '1', '1980.00');
INSERT INTO `orderinfo` VALUES ('17', '18071214460001', '29', '1', '2199.00');
INSERT INTO `orderinfo` VALUES ('18', '18071214470001', '6', '1', '14900.00');
INSERT INTO `orderinfo` VALUES ('19', '18071214470001', '24', '1', '4700.00');
INSERT INTO `orderinfo` VALUES ('20', '18071214480001', '18', '10', '899.00');
INSERT INTO `orderinfo` VALUES ('21', '18071214490001', '16', '12', '88.00');
INSERT INTO `orderinfo` VALUES ('22', '18071214500001', '2', '1', '19900.00');
INSERT INTO `orderinfo` VALUES ('23', '18071214500001', '33', '10', '198.00');
INSERT INTO `orderinfo` VALUES ('24', '18071214510001', '5', '1', '13800.00');
INSERT INTO `orderinfo` VALUES ('25', '18071214530001', '22', '1', '5823.00');
INSERT INTO `orderinfo` VALUES ('26', '18071214530001', '30', '1', '23000.00');
INSERT INTO `orderinfo` VALUES ('27', '18071214550001', '3', '1', '21900.00');
INSERT INTO `orderinfo` VALUES ('28', '18071214550001', '1', '10', '38880.00');
INSERT INTO `orderinfo` VALUES ('29', '18071215320001', '30', '1', '23000.00');
INSERT INTO `orderinfo` VALUES ('30', '18071222120001', '18', '1', '899.00');
INSERT INTO `orderinfo` VALUES ('31', '18071222120001', '21', '1', '12300.00');
INSERT INTO `orderinfo` VALUES ('32', '18071222160001', '23', '1', '1800.00');
INSERT INTO `orderinfo` VALUES ('33', '18071222160001', '24', '1', '4700.00');
INSERT INTO `orderinfo` VALUES ('34', '18071222170001', '31', '1', '80.00');
INSERT INTO `orderinfo` VALUES ('35', '18071222170001', '32', '1', '98.00');
INSERT INTO `orderinfo` VALUES ('36', '18071222170001', '33', '1', '198.00');
INSERT INTO `orderinfo` VALUES ('37', '18071222170002', '35', '1', '110.00');
INSERT INTO `orderinfo` VALUES ('38', '18071222170002', '34', '1', '121.00');
INSERT INTO `orderinfo` VALUES ('39', '18071222200001', '26', '1', '1980.00');
INSERT INTO `orderinfo` VALUES ('40', '18071222200001', '25', '1', '599.00');
INSERT INTO `orderinfo` VALUES ('41', '18071222200001', '28', '1', '27300.00');
INSERT INTO `orderinfo` VALUES ('42', '18071222200002', '27', '6', '5100.00');
INSERT INTO `orderinfo` VALUES ('43', '18071222210001', '30', '6', '23000.00');
INSERT INTO `orderinfo` VALUES ('45', '18071223470001', '1', '1', '38880.00');
INSERT INTO `orderinfo` VALUES ('48', '18071223470004', '3', '1', '21900.00');
INSERT INTO `orderinfo` VALUES ('51', '18071300000001', '1', '1', '38880.00');
INSERT INTO `orderinfo` VALUES ('54', '18071300060001', '4', '1', '32430.00');
INSERT INTO `orderinfo` VALUES ('55', '18071415170001', '30', '1', '23000.00');
INSERT INTO `orderinfo` VALUES ('56', '18071415170001', '36', '1', '11700.00');

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
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES ('2', '1', '1', '2018-06-29 09:40:30', '3', '0');
INSERT INTO `purchase` VALUES ('3', '2', '1', '2018-06-29 09:37:14', '1', '0');
INSERT INTO `purchase` VALUES ('4', '3', '1', '2018-06-29 09:37:16', '1', '0');
INSERT INTO `purchase` VALUES ('5', '2', '1', '2018-06-29 14:36:55', '10', '0');
INSERT INTO `purchase` VALUES ('6', '5', '1', '2018-06-29 14:37:07', '2', '0');
INSERT INTO `purchase` VALUES ('7', '9', '1', '2018-06-29 14:37:37', '14', '0');
INSERT INTO `purchase` VALUES ('9', '4', '1', '2018-06-29 14:37:53', '1', '0');
INSERT INTO `purchase` VALUES ('11', '8', '2', '2018-06-29 15:04:45', '8', '0');
INSERT INTO `purchase` VALUES ('12', '10', '2', '2018-06-29 15:04:55', '2', '0');
INSERT INTO `purchase` VALUES ('14', '17', '9', '2018-07-03 11:58:49', '2', '0');
INSERT INTO `purchase` VALUES ('15', '5', '1', '2018-07-09 09:49:03', '1', '0');
INSERT INTO `purchase` VALUES ('19', '14', '1', '2018-07-09 23:35:51', '1', '0');
INSERT INTO `purchase` VALUES ('20', '2', '9', '2018-07-11 17:14:30', '2', '0');
INSERT INTO `purchase` VALUES ('21', '5', '9', '2018-07-11 17:14:52', '5', '0');
INSERT INTO `purchase` VALUES ('22', '3', '9', '2018-07-11 17:14:58', '1', '0');
INSERT INTO `purchase` VALUES ('23', '2', '2', '2018-07-12 14:43:56', '2', '0');
INSERT INTO `purchase` VALUES ('24', '36', '2', '2018-07-12 14:44:12', '1', '0');
INSERT INTO `purchase` VALUES ('25', '34', '2', '2018-07-12 14:45:02', '6', '0');
INSERT INTO `purchase` VALUES ('26', '26', '2', '2018-07-12 14:45:18', '1', '0');
INSERT INTO `purchase` VALUES ('27', '29', '2', '2018-07-12 14:45:31', '1', '0');
INSERT INTO `purchase` VALUES ('29', '1', '3', '2018-07-12 14:47:01', '1', '0');
INSERT INTO `purchase` VALUES ('30', '4', '3', '2018-07-12 14:47:15', '2', '0');
INSERT INTO `purchase` VALUES ('31', '6', '3', '2018-07-12 14:47:11', '1', '0');
INSERT INTO `purchase` VALUES ('32', '24', '3', '2018-07-12 14:47:20', '1', '0');
INSERT INTO `purchase` VALUES ('33', '18', '3', '2018-07-12 14:48:11', '10', '0');
INSERT INTO `purchase` VALUES ('34', '16', '3', '2018-07-12 14:48:25', '12', '0');
INSERT INTO `purchase` VALUES ('35', '2', '3', '2018-07-12 14:48:30', '1', '0');
INSERT INTO `purchase` VALUES ('36', '33', '3', '2018-07-12 14:49:50', '10', '0');
INSERT INTO `purchase` VALUES ('37', '5', '1', '2018-07-12 14:50:56', '1', '0');
INSERT INTO `purchase` VALUES ('39', '22', '9', '2018-07-12 14:53:16', '1', '0');
INSERT INTO `purchase` VALUES ('40', '30', '9', '2018-07-12 14:53:21', '1', '0');
INSERT INTO `purchase` VALUES ('41', '1', '9', '2018-07-12 14:55:04', '10', '0');
INSERT INTO `purchase` VALUES ('44', '30', '1', '2018-07-12 15:31:57', '1', '0');
INSERT INTO `purchase` VALUES ('45', '18', '1', '2018-07-12 21:57:26', '1', '0');
INSERT INTO `purchase` VALUES ('46', '21', '1', '2018-07-12 21:57:34', '1', '0');
INSERT INTO `purchase` VALUES ('48', '23', '1', '2018-07-12 21:57:56', '1', '0');
INSERT INTO `purchase` VALUES ('49', '24', '1', '2018-07-12 21:57:59', '1', '0');
INSERT INTO `purchase` VALUES ('54', '31', '1', '2018-07-12 21:58:29', '1', '2');
INSERT INTO `purchase` VALUES ('55', '32', '1', '2018-07-12 21:58:33', '1', '2');
INSERT INTO `purchase` VALUES ('56', '33', '1', '2018-07-12 21:58:37', '1', '2');
INSERT INTO `purchase` VALUES ('58', '35', '1', '2018-07-12 21:58:53', '1', '0');
INSERT INTO `purchase` VALUES ('59', '34', '1', '2018-07-12 21:58:57', '1', '0');
INSERT INTO `purchase` VALUES ('61', '27', '2', '2018-07-12 22:19:48', '6', '0');
INSERT INTO `purchase` VALUES ('62', '26', '2', '2018-07-12 22:18:55', '1', '2');
INSERT INTO `purchase` VALUES ('63', '25', '2', '2018-07-12 22:18:59', '1', '2');
INSERT INTO `purchase` VALUES ('64', '28', '2', '2018-07-12 22:19:10', '1', '2');
INSERT INTO `purchase` VALUES ('65', '30', '2', '2018-07-12 22:21:23', '6', '0');
INSERT INTO `purchase` VALUES ('66', '1', '3', '2018-07-12 23:47:11', '1', '0');
INSERT INTO `purchase` VALUES ('67', '3', '3', '2018-07-12 23:47:16', '1', '0');
INSERT INTO `purchase` VALUES ('68', '4', '3', '2018-07-12 23:47:19', '1', '0');
INSERT INTO `purchase` VALUES ('69', '2', '3', '2018-07-12 23:47:23', '1', '0');
INSERT INTO `purchase` VALUES ('70', '27', '3', '2018-07-12 23:50:43', '1', '0');
INSERT INTO `purchase` VALUES ('71', '1', '3', '2018-07-12 23:59:49', '1', '0');
INSERT INTO `purchase` VALUES ('72', '4', '3', '2018-07-13 00:00:56', '1', '0');
INSERT INTO `purchase` VALUES ('73', '33', '3', '2018-07-13 00:02:39', '1', '0');
INSERT INTO `purchase` VALUES ('74', '4', '3', '2018-07-13 00:04:01', '1', '0');
INSERT INTO `purchase` VALUES ('75', '4', '3', '2018-07-13 00:06:21', '1', '1');
INSERT INTO `purchase` VALUES ('76', '30', '4', '2018-07-14 15:15:06', '1', '0');
INSERT INTO `purchase` VALUES ('77', '36', '4', '2018-07-14 15:15:16', '1', '0');
INSERT INTO `purchase` VALUES ('78', '4', '1', '2018-07-14 15:37:25', '1', '1');

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
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '什么权限都有', '2018-06-28 16:03:55', '2018-07-05 22:06:11', '0');
INSERT INTO `role` VALUES ('2', '类别主管', '管理类别信息', '2018-07-05 10:21:37', null, '4');
INSERT INTO `role` VALUES ('3', '商品主管', '管理商品信息', '2018-07-05 10:22:05', null, '7');
INSERT INTO `role` VALUES ('4', '类别操作员', '操作类别基本信息（查询，增加，修改）', '2018-07-05 10:22:40', null, '0');
INSERT INTO `role` VALUES ('5', '订单主管', '管理订单信息', '2018-07-05 17:03:55', null, '6');
INSERT INTO `role` VALUES ('6', '订单操作员', '操作订单基本信息（查询）', '2018-07-05 17:05:04', null, '0');
INSERT INTO `role` VALUES ('7', '商品操作员', '操作商品基本信息（查询，增加，修改）', '2018-07-05 17:05:41', null, '0');
INSERT INTO `role` VALUES ('8', '日志管理员', '管理日志信息', '2018-07-05 17:06:07', null, '0');
INSERT INTO `role` VALUES ('11', '用户操作员', '操作管理员基本信息（查询，增加，修改）', '2018-07-06 14:36:36', null, '0');
INSERT INTO `role` VALUES ('12', '用户主管', '管理管理员信息', '2018-07-06 14:37:53', null, '11');
INSERT INTO `role` VALUES ('14', '角色操作员', '操作角色基本信息（查询，增加，修改）', '2018-07-09 11:20:18', null, null);
INSERT INTO `role` VALUES ('15', '角色主管', '管理角色信息', '2018-07-09 11:21:00', null, '14');

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
-- Records of role_function
-- ----------------------------
INSERT INTO `role_function` VALUES ('1', '1', '1');
INSERT INTO `role_function` VALUES ('5', '2', '9');
INSERT INTO `role_function` VALUES ('10', '3', '12');
INSERT INTO `role_function` VALUES ('12', '4', '2');
INSERT INTO `role_function` VALUES ('13', '4', '7');
INSERT INTO `role_function` VALUES ('14', '4', '8');
INSERT INTO `role_function` VALUES ('15', '4', '14');
INSERT INTO `role_function` VALUES ('16', '5', '13');
INSERT INTO `role_function` VALUES ('17', '6', '16');
INSERT INTO `role_function` VALUES ('18', '7', '10');
INSERT INTO `role_function` VALUES ('19', '7', '11');
INSERT INTO `role_function` VALUES ('20', '7', '15');
INSERT INTO `role_function` VALUES ('21', '7', '3');
INSERT INTO `role_function` VALUES ('22', '8', '5');
INSERT INTO `role_function` VALUES ('24', '11', '6');
INSERT INTO `role_function` VALUES ('25', '11', '21');
INSERT INTO `role_function` VALUES ('26', '11', '19');
INSERT INTO `role_function` VALUES ('27', '12', '20');
INSERT INTO `role_function` VALUES ('37', '6', '4');
INSERT INTO `role_function` VALUES ('44', '13', '2');
INSERT INTO `role_function` VALUES ('45', '13', '3');
INSERT INTO `role_function` VALUES ('46', '13', '4');
INSERT INTO `role_function` VALUES ('47', '14', '17');
INSERT INTO `role_function` VALUES ('48', '14', '22');
INSERT INTO `role_function` VALUES ('49', '14', '23');
INSERT INTO `role_function` VALUES ('50', '15', '24');

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
-- Records of torder
-- ----------------------------
INSERT INTO `torder` VALUES ('18062914110001', '1', '18229926656', '陕西咸阳水岸朝阳小区', '293.00', '微信', '3', '2018-06-29 14:11:38', '2018-07-03 23:43:39');
INSERT INTO `torder` VALUES ('18062914380001', '1', '18229926656', '天津武清区', '646.00', '支付宝', '1', '2018-06-29 14:38:36', '2018-07-03 23:43:52');
INSERT INTO `torder` VALUES ('18062915050001', '2', '18229926656', '湖南省长沙市雨花区长沙理工大学', '22032.00', '货到付款', '1', '2018-06-29 15:05:42', '2018-07-03 23:43:56');
INSERT INTO `torder` VALUES ('18070311590001', '9', '18229926656', '你猜', '51760.00', '货到付款', '1', '2018-07-03 11:59:06', '2018-07-03 23:43:58');
INSERT INTO `torder` VALUES ('18070909490001', '1', '18229926656', '湖南省长沙市雨花区长沙理工大学', '13800.00', '支付宝', '3', '2018-07-09 09:49:32', '2018-07-09 09:49:41');
INSERT INTO `torder` VALUES ('18071010360001', '1', '18229926656', '湖南省长沙市雨花区长沙理工大学', '128.00', '货到付款', '3', '2018-07-10 10:36:40', '2018-07-12 14:50:43');
INSERT INTO `torder` VALUES ('18071117150001', '9', '18229926656', '长沙和立功大学', '108800.00', '支付宝', '1', '2018-07-11 17:15:17', '2018-07-11 17:15:22');
INSERT INTO `torder` VALUES ('18071214440001', '2', '18229926656', '湖南省长沙市雨花区长沙理工大学', '51500.00', '支付宝', '1', '2018-07-12 14:44:29', '2018-07-12 14:44:34');
INSERT INTO `torder` VALUES ('18071214460001', '2', '18229926656', '湖南省长沙市雨花区长沙理工大学', '4905.00', '微信', '2', '2018-07-12 14:46:15', '2018-07-12 14:46:21');
INSERT INTO `torder` VALUES ('18071214470001', '3', '18229926656', '陕西咸阳水岸朝阳小区', '19600.00', '货到付款', '1', '2018-07-12 14:47:48', '2018-07-12 14:47:51');
INSERT INTO `torder` VALUES ('18071214480001', '3', '18229926656', '湖南省长沙市雨花区长沙理工大学', '8990.00', '微信', '2', '2018-07-12 14:48:54', '2018-07-12 14:48:58');
INSERT INTO `torder` VALUES ('18071214490001', '3', '18229926656', '湖南省长沙市雨花区长沙理工大学', '1056.00', '微信', '1', '2018-07-12 14:49:22', '2018-07-12 14:49:24');
INSERT INTO `torder` VALUES ('18071214500001', '3', '18229926656', '陕西咸阳水岸朝阳小区', '21880.00', '货到付款', '1', '2018-07-12 14:50:09', '2018-07-12 14:50:12');
INSERT INTO `torder` VALUES ('18071214510001', '1', '18229926656', '湖南省长沙市雨花区长沙理工大学', '13800.00', '货到付款', '1', '2018-07-12 14:51:12', '2018-07-12 14:51:17');
INSERT INTO `torder` VALUES ('18071214530001', '9', '18229926656', '湖南省长沙市雨花区长沙理工大学', '28823.00', '货到付款', '1', '2018-07-12 14:53:39', '2018-07-12 14:53:45');
INSERT INTO `torder` VALUES ('18071214550001', '9', '18229926656', '长沙和立功大学', '410700.00', '支付宝', '2', '2018-07-12 14:55:23', '2018-07-12 14:55:27');
INSERT INTO `torder` VALUES ('18071215320001', '1', '18229926656', '湖南省长沙市雨花区长沙理工大学', '23000.00', '支付宝', '1', '2018-07-12 15:32:13', '2018-07-12 15:32:16');
INSERT INTO `torder` VALUES ('18071222120001', '1', '13891031295', '湖南省长沙市雨花区长沙理工大学', '13199.00', '支付宝', '2', '2018-07-12 22:12:54', '2018-07-12 22:13:01');
INSERT INTO `torder` VALUES ('18071222160001', '1', '13572799666', '陕西咸阳水岸朝阳小区', '6500.00', '微信', '1', '2018-07-12 22:16:08', '2018-07-12 22:16:11');
INSERT INTO `torder` VALUES ('18071222170001', '1', '18229926656', '湖南省长沙市雨花区长沙理工大学', '376.00', '微信', '0', '2018-07-12 22:17:02', null);
INSERT INTO `torder` VALUES ('18071222170002', '1', '13572799666', '陕西咸阳水岸朝阳小区', '231.00', '支付宝', '1', '2018-07-12 22:17:45', '2018-07-12 22:17:50');
INSERT INTO `torder` VALUES ('18071222200001', '2', '13891031295', '河北不知道哪个地方', '29879.00', '微信', '0', '2018-07-12 22:20:37', null);
INSERT INTO `torder` VALUES ('18071222200002', '2', '18229926656', '湖南省长沙市雨花区长沙理工大学', '30600.00', '支付宝', '3', '2018-07-12 22:20:56', '2018-07-12 22:21:01');
INSERT INTO `torder` VALUES ('18071222210001', '2', '18229926656', '长沙和立功大学', '138000.00', '货到付款', '2', '2018-07-12 22:21:45', '2018-07-12 22:25:43');
INSERT INTO `torder` VALUES ('18071223470001', '3', '131233', '213123', '38880.00', '支付宝', '3', '2018-07-12 23:47:03', '2018-07-12 23:51:43');
INSERT INTO `torder` VALUES ('18071223470004', '3', '12313', '123123', '21900.00', '支付宝', '1', '2018-07-12 23:47:53', '2018-07-13 00:07:09');
INSERT INTO `torder` VALUES ('18071300000001', '3', '18229926656', '长沙和立功大学', '38880.00', '支付宝', '3', '2018-07-13 00:00:12', '2018-07-13 00:05:18');
INSERT INTO `torder` VALUES ('18071300060001', '3', '132', '2321', '32430.00', '支付宝', '2', '2018-07-13 00:06:02', '2018-07-13 00:07:03');
INSERT INTO `torder` VALUES ('18071415170001', '4', '18229926656', '湖南省长沙市雨花区长沙理工大学', '34700.00', '支付宝', '1', '2018-07-14 15:17:30', '2018-07-14 15:17:43');

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
-- Records of turl
-- ----------------------------
INSERT INTO `turl` VALUES ('1', '所有页面', '/**', '2018-06-28 16:04:33', null);
INSERT INTO `turl` VALUES ('2', '类别管理页面', '/admin/category', '2018-07-05 10:09:26', null);
INSERT INTO `turl` VALUES ('3', '商品管理页面', '/admin/goods', '2018-07-05 10:09:46', null);
INSERT INTO `turl` VALUES ('4', '订单管理页面', '/admin/order', '2018-07-05 10:11:26', null);
INSERT INTO `turl` VALUES ('5', '日志管理页面', '/admin/log', '2018-07-05 10:11:50', null);
INSERT INTO `turl` VALUES ('6', '用户管理页面', '/admin/admin', '2018-07-05 10:12:22', '2018-07-06 11:34:33');
INSERT INTO `turl` VALUES ('7', '类别增加', '/category/add', '2018-07-05 10:14:35', null);
INSERT INTO `turl` VALUES ('8', '类别更新', '/category/update', '2018-07-05 10:14:54', null);
INSERT INTO `turl` VALUES ('9', '类别删除', '/category/delete', '2018-07-05 10:15:10', null);
INSERT INTO `turl` VALUES ('10', '商品增加', '/goods/add', '2018-07-05 10:16:20', null);
INSERT INTO `turl` VALUES ('11', '商品更新', '/goods/update', '2018-07-05 10:16:38', null);
INSERT INTO `turl` VALUES ('12', '商品删除', '/goods/delete', '2018-07-05 10:16:55', null);
INSERT INTO `turl` VALUES ('13', '订单删除', '/order/delete', '2018-07-05 10:17:38', null);
INSERT INTO `turl` VALUES ('14', '类别查询', '/category/get', '2018-07-05 10:18:59', null);
INSERT INTO `turl` VALUES ('15', '商品查询', '/goods/get', '2018-07-05 10:19:32', null);
INSERT INTO `turl` VALUES ('16', '订单查询', '/order/get', '2018-07-05 10:19:56', null);
INSERT INTO `turl` VALUES ('17', '角色管理页面', '/admin/role', '2018-07-05 15:02:20', '2018-07-06 11:35:49');
INSERT INTO `turl` VALUES ('18', '资源管理页面', '/admin/url', '2018-07-05 15:02:36', '2018-07-06 11:35:58');
INSERT INTO `turl` VALUES ('19', '用户修改', '/admin/update', '2018-07-06 00:01:45', '2018-07-06 00:05:01');
INSERT INTO `turl` VALUES ('20', '用户删除', '/admin/delete', '2018-07-06 11:36:20', null);
INSERT INTO `turl` VALUES ('21', '用户添加', '/admin/add', '2018-07-06 11:36:49', null);
INSERT INTO `turl` VALUES ('22', '角色添加', '/role/add', '2018-07-06 11:37:15', null);
INSERT INTO `turl` VALUES ('23', '角色修改', '/role/update', '2018-07-06 11:37:33', null);
INSERT INTO `turl` VALUES ('24', '角色删除', '/role/delete', '2018-07-06 11:37:53', null);
INSERT INTO `turl` VALUES ('25', '资源添加', '/url/add', '2018-07-06 11:38:21', null);
INSERT INTO `turl` VALUES ('26', '资源修改', '/url/update', '2018-07-06 11:38:36', null);
INSERT INTO `turl` VALUES ('27', '资源删除', '/url/delete', '2018-07-06 11:38:51', null);

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

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'czy', '陈泽宇', '4QrcOUm6Wau+VuBX8g+IPg==', '男', '602974311@qq.com', '0', '湖南省长沙市长沙理工大学', '2018-06-12 00:16:32', '2018-07-14 15:36:51');
INSERT INTO `user` VALUES ('2', 'mjf', '马健锋', 'ICy5YqxZB1uWSwcVLSNLcA==', '男', '2250783493@qq.com', '0', '湖南省长沙市长沙理工大学', '2018-06-12 09:12:44', '2018-07-14 15:13:56');
INSERT INTO `user` VALUES ('3', 'zj', '赵晋', '4QrcOUm6Wau+VuBX8g+IPg==', '男', '619220726@qq.com', '0', '湖南省长沙市长沙理工大', '2018-06-12 09:23:11', '2018-07-12 23:59:41');
INSERT INTO `user` VALUES ('4', 'wyx', '一万年', 'y2RMJvEH1LbP7B34+kA/uw==', '女', '1030873180@qq.com', '0', '天津市武清区', '2018-06-17 21:50:11', '2018-07-14 15:14:40');
INSERT INTO `user` VALUES ('9', 'cwy', '陈武阳', 'ICy5YqxZB1uWSwcVLSNLcA==', '女', '3261045722@qq.com', '0', '长理', '2018-07-03 11:57:51', '2018-07-14 15:14:24');
