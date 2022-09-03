CREATE TABLE `routes` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uri` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
  `service_name` VARCHAR(255) NOT NULL COLLATE 'utf8_unicode_ci',
  `path` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
  `is_auth` BIT(1) NOT NULL DEFAULT b'0',
  `ia_active` BIT(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `service_name` (`service_name`) USING BTREE
)
    COLLATE='utf8_unicode_ci'
    ENGINE=InnoDB
    AUTO_INCREMENT=12
;
