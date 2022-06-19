-- PW: oktaiscool
INSERT INTO user (ID, USERNAME, PASSWORD_HASH) VALUES
('17e3d83c-6e09-41b8-b4ee-b4b14cb8a797', 'dbuser@dbauth.com', '$2y$12$0uXgosmcpIRWLxcAhCx0s.dUEZbrAHa0F0pkn6MO4Y57PCJ5fDjfa'),
('17e3d83c-6e09-41b8-b4ee-b4b14cb8a799', '814631252@qq.com', '$2a$10$U0EcaLMu0BgMZ6g0Reqnv.FK7Cmf4arG30YfJYn0e4vBdve.XfrNG'),
('17e3d83c-6e09-41b8-b4ee-b4b14cb8a798', 'samluser@oktaauth.com', '$2y$12$0uXgosmcpIRWLxcAhCx0s.dUEZbrAHa0F0pkn6MO4Y57PCJ5fDjfa');


DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限编码（标识）',
                                   `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
                                   `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限URL',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `sys_permission` VALUES (1, 'home', '首页', '/home/**');
INSERT INTO `sys_permission` VALUES (2, 'user:add', '添加用户', '/user/add');
INSERT INTO `sys_permission` VALUES (3, 'user:delete', '删除用户', '/user/delete');
INSERT INTO `sys_permission` VALUES (4, 'hello:sayHello', '打招呼', '/hello/sayHello');

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
                             `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


INSERT INTO `sys_role` VALUES (1, 'employee', '员工');
INSERT INTO `sys_role` VALUES (2, 'engineer', '工程师');
INSERT INTO `sys_role` VALUES (3, 'leader', '组长');



-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
                                        `id` int(11) NOT NULL AUTO_INCREMENT,
                                        `role_id` int(11) NOT NULL COMMENT '角色ID',
                                        `permission_id` int(11) NOT NULL COMMENT '权限ID',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 1);
INSERT INTO `sys_role_permission` VALUES (2, 2, 1);
INSERT INTO `sys_role_permission` VALUES (3, 2, 2);
INSERT INTO `sys_role_permission` VALUES (4, 3, 1);
INSERT INTO `sys_role_permission` VALUES (5, 3, 2);
INSERT INTO `sys_role_permission` VALUES (6, 3, 3);
INSERT INTO `sys_role_permission` VALUES (7, 3, 4);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
                             `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'zhangsan', '$2a$10$e4wFsFHQCNjPe5tTJMPkRuKGwmMGC45pfjMupY9nwbTuoKQ0bKc/u');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `user_id` int(11) NOT NULL COMMENT '用户ID',
                                  `role_id` int(11) NOT NULL COMMENT '角色ID',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 1, 2);
INSERT INTO `sys_user_role` VALUES (3, 1, 3);