REPLACE INTO `roles` VALUES (1,'ADMIN');

REPLACE INTO `employees` (`emp_id`, `base_salary`, `email`, `emp_name`, `emp_type`, `first_name`, `last_name`, `password`, `row_state`) VALUES
(1, 150000, 'yaseer@icloud.com', 'yaseer', 'Software Engineer Developer', 'yaseer', 'hussain', '$2a$10$3onlFuIaQcM/XrQBPQEFX.wFELRedefOpap6TCXZ3Zf8coN5q3MRG', b'1');/*
(2, 150000, 'ramesh@icloud.com', 'ramesh', 'Business Consultant', 'ramesh', 'kumar', '$2a$10$3onlFuIaQcM/XrQBPQEFX.wFELRedefOpap6TCXZ3Zf8coN5q3MRG', b'1');
(3, 150000, 'suresh@icloud.com', 'suresh', 'Business Consultant', 'suresh', 'raina', '$2a$10$3onlFuIaQcM/XrQBPQEFX.wFELRedefOpap6TCXZ3Zf8coN5q3MRG', b'1');
(4, 150000, 'paul@icloud.com', 'paul', 'Software Development Engineer', 'paul', 'johnson', '$2a$10$3onlFuIaQcM/XrQBPQEFX.wFELRedefOpap6TCXZ3Zf8coN5q3MRG', b'1');
(5, 150000, 'mouli@icloud.com', 'mouli', 'Digital Marketing', 'mouli', 'vardhan', '$2a$10$3onlFuIaQcM/XrQBPQEFX.wFELRedefOpap6TCXZ3Zf8coN5q3MRG', b'1');*/

REPLACE INTO `emp_role` (`emp_id`, `role_id`) VALUES ('1', '1');

REPLACE INTO `hibernate_sequence` (`next_val`) VALUE
(1);

