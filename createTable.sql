drop table if exists user_table;
CREATE TABLE `user_table` (
                              `user_id` bigint NOT NULL AUTO_INCREMENT,
                              `nickname` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '昵称',
                              `phone_num` bigint DEFAULT NULL COMMENT '用户手机号',
                              `password` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
                              `sex` smallint DEFAULT NULL COMMENT '性别',
                              `age` int DEFAULT NULL COMMENT '年龄',
                              `user_character` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性格',
                              PRIMARY KEY (`user_id`),
                              UNIQUE KEY `user_num` (`phone_num`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

drop table if exists travel_table;
CREATE TABLE `travel_table` (
                                `travel_id` bigint NOT NULL AUTO_INCREMENT,
                                `from_area` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '出发地',
                                `to_area` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '目的地',
                                `time` int DEFAULT NULL COMMENT '出行时间',
                                `accept_amount` int DEFAULT NULL COMMENT '可接受的消费金额',
                                `tags` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '出行标签',
                                `user_id` bigint DEFAULT NULL COMMENT '创建旅行的userid',
                                `num_people` smallint DEFAULT NULL COMMENT '出行人数',
                                PRIMARY KEY (`travel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into user_table(nickname, phone_num, password, sex, age, user_character)
 VALUES ('小d','321','1234','0','21','内向');

insert into travel_table(from_area, to_area, time, accept_amount, tags, user_id, num_people)
 VALUES ('上海','南京','3','5000','海边,沙滩','1','2');


