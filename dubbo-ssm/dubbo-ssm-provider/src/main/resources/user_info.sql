-- 创建表
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(20) NULL DEFAULT NULL,
	`name` VARCHAR(20) NULL DEFAULT NULL,
	`password` VARCHAR(50) NULL DEFAULT NULL,
	`salt` VARCHAR(50) NULL DEFAULT NULL,
	`state` TINYINT(1) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;
-- 插入数据
INSERT INTO `user_info` (`username`, `name`, `password`, `salt`, `state`) VALUES ('admin', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', 0);

