CREATE TABLE IF NOT EXISTS `user_login` (
    `user_login_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `login_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(`user_id`),
    CONSTRAINT `fk_user_login_user_id` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
);