CREATE DATABASE IF NOT EXISTS world;
CREATE TABLE IF NOT EXISTS `t_url_keyword` (
  `id` int(11) NOT NULL COMMENT '主键',
  `keyword` varchar(20) NOT NULL COMMENT '短码',
  `deleted` tinyint(4) NOT NULL COMMENT '删除标志位',
  `type` tinyint(4) NOT NULL COMMENT '自定义标志位',
  `url` varchar(255) NOT NULL COMMENT '长链接',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_keyword` (`keyword`) USING BTREE,
  KEY `idx_url` (`url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `t_url_count`(
  `pk_id` INT(11) NOT NULL COMMENT '主键',
  `fk_uk_id` INT(11) NOT NULL COMMENT '长链接短码id',
  ``
)