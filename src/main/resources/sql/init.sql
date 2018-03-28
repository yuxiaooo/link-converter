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

CREATE TABLE IF NOT EXISTS `t_url_access_times` (
  `pk_id` int(11) NOT NULL COMMENT '主键',
  `fk_uk_id` int(11) NOT NULL COMMENT 't_url_keyword primary key',
  `access_times` bigint(20) DEFAULT NULL COMMENT '访问次数',
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`pk_id`),
  KEY `idx_uk_id` (`fk_uk_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'url访问表';
