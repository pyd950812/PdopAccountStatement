-- auto Generated on 2018-07-12 10:08:44 
-- DROP TABLE IF EXISTS `pdop_query_log`; 
CREATE TABLE pdop_query_log(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '��Ʒid',
    `org_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '��֯id',
    `user_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '������id',
    `data_source_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '���ݿ�id',
    `jf_reqlog_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '���id',
    `batch_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '����id',
    `request_param` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '�������',
    `create_datetime` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '��������',
    `query_type` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '��ѯ����',
    `ids` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'ids',
    `group_sql` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'groupSql',
    `is_charge` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '�Ƿ�Ʒ�',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pdop_query_log';
