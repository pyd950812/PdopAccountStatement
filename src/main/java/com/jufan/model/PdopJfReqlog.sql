-- auto Generated on 2018-07-11 17:05:02 
-- DROP TABLE IF EXISTS `pdop_jf_reqlog`; 
CREATE TABLE pdop_jf_reqlog(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '��ƷID',
    `url` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '����URL',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '����',
    `cert_number` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '���֤����',
    `request_param` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '�������',
    `response_raw` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '����ֵ',
    `original_response_raw` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'ԭʼ����ַ���',
    `status` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '״̬',
    `create_datetime` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '����ʱ��',
    `create_operator_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '������',
    `last_update_datetime` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '����޸�ʱ��',
    `last_update_operator_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '����޸���',
    `ext_info` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '��չ��Ϣ(JSON)',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pdop_jf_reqlog';
