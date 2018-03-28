# 实现功能

- 输入长链生成短链
- 访问短链跳转到长链
- 支持访问计数
- 自定义短链
- 统一异常处理

应用级配置:
- 短链长度，最短为3,最长不超过10
- 字符集(支持纯数字，英文，英文数字混合)

```properties
link.converter.character-set=alpha_num
link.converter.url-code-length=6
```

```mysql
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'url长链接短码表';

```
```mysql
CREATE TABLE `t_url_access_times` (
  `pk_id` int(11) NOT NULL COMMENT '主键',
  `fk_uk_id` int(11) NOT NULL COMMENT 't_url_keyword primary key',
  `access_times` bigint(20) DEFAULT NULL COMMENT '访问次数',
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`pk_id`),
  KEY `idx_uk_id` (`fk_uk_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'url访问表';

```

- 由于我们需要支持访问计数，不能使用301永久重定向，计数采用的是异步计数，避免影响性能
- 程序采用了本地缓存增强并发性