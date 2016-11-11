use pm;

alter table t_pm_picture Add column sex char(1) comment '用户性别';
alter table t_pm_picture_thumb Add column sex char(1) comment '用户性别';
alter table t_pm_picture_comment Add column sex char(1) comment '用户性别';
alter table t_pm_picture_forward Add column sex char(1) comment '用户性别';


use tm;
alter table t_tm_foot_print_detail Add column sex char(1) comment '用户性别';


