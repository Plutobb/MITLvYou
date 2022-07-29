CREATE TABLE `user_table` (
                              `user_id` bigint NOT NULL AUTO_INCREMENT,
                              `nickname` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '昵称',
                              `phoneNum` bigint DEFAULT NULL COMMENT '用户手机号',
                              `password` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
                              `sex` smallint DEFAULT NULL COMMENT '性别',
                              `age` int DEFAULT NULL COMMENT '年龄',
                              `character` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性格',
                              PRIMARY KEY (`user_id`),
                              UNIQUE KEY `user_num` (`phoneNum`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `travel_table` (
                                `travel_id` bigint NOT NULL AUTO_INCREMENT,
                                `travel_from_area` varchar(10) DEFAULT NULL COMMENT '出发地',
                                `travel_to_area` varchar(10) DEFAULT NULL COMMENT '目的地',
                                `travel_time` int DEFAULT NULL COMMENT '出行时间',
                                `travel_acceptAmount` int DEFAULT NULL COMMENT '可接受的消费金额',
                                `travel_tags` varchar(50) DEFAULT NULL COMMENT '出行标签',
                                `travel_userid` bigint DEFAULT NULL COMMENT '创建旅行的userid',
                                `travel_num_people` smallint DEFAULT NULL COMMENT '出行人数',
                                PRIMARY KEY (`travel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into user_table(user_nickname, user_num, user_password, user_sex, user_age, user_character)
VALUES ('小b','1111','123','0','20','内向'),('小d','2222','1234','0','21','内向');

insert into travel_table(travel_from_area, travel_to_area, travel_time, travel_acceptAmount, travel_tags, travel_userid)
VALUES ('上海','南京','3','5000','海边,沙滩','1');

select * from travel_table as travel join user_table as user on travel.travel_userid = user.user_id;
