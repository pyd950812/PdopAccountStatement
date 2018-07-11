-- auto Generated on 2018-07-11 17:05:02 
-- DROP TABLE IF EXISTS `pdop_jf_reqlog`; 
CREATE TABLE pdop_jf_reqlog(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '产品ID',
    `url` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '请求URL',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '姓名',
    `cert_number` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '身份证号码',
    `request_param` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '请求参数',
    `response_raw` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '返回值',
    `original_response_raw` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '原始输出字符串',
    `status` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '状态',
    `create_datetime` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
    `create_operator_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建人',
    `last_update_datetime` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '最后修改时间',
    `last_update_operator_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '最后修改人',
    `ext_info` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '扩展信息(JSON)',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pdop_jf_reqlog';
