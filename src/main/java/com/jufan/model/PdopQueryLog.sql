-- auto Generated on 2018-07-12 10:08:44 
-- DROP TABLE IF EXISTS `pdop_query_log`; 
CREATE TABLE pdop_query_log(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '产品id',
    `org_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '组织id',
    `user_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '操作人id',
    `data_source_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '数据库id',
    `jf_reqlog_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '结果id',
    `batch_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '批次id',
    `request_param` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '请求参数',
    `create_datetime` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建日期',
    `query_type` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '查询类型',
    `ids` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'ids',
    `group_sql` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'groupSql',
    `is_charge` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '是否计费',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pdop_query_log';
