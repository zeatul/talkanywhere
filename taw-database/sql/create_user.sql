use um;

drop table if exists t_um_login;

drop index u_um_partner2 on t_um_partner;

drop index u_um_partner1 on t_um_partner;

drop table if exists t_um_partner;

drop index u_um_user1 on t_um_user;

drop table if exists t_um_user;

drop index i_um_r_m on t_um_user_contact;

drop table if exists t_um_user_contact;

/*==============================================================*/
/* Table: t_um_login                                            */
/*==============================================================*/
create table t_um_login
(
   token                varchar(50) not null comment '登录唯一标识',
   user_id              integer not null comment '用户ID',
   login_date           datetime comment '创建日期',
   last_access_date     datetime comment '最近访问日期',
   expire_date          datetime comment 'token失效日期',
   kind                 tinyint comment '长期/短期',
   imei                 varchar(50) comment '设备唯一的串号',
   device_kind          char(1) comment 'android/ios/winphone/pc/mpc',
   os_version           varchar(20) comment 'ios或安卓的版本号',
   brand                varchar(50) comment '三星/华为/苹果',
   device_model         varchar(50) comment '厂商给设备定义的编号',
   ip                   varchar(50) comment '登录IP',
   primary key (token)
)
engine=innodb default charset=utf8;

alter table t_um_login comment '维护登录信息,';

/*==============================================================*/
/* Table: t_um_partner                                          */
/*==============================================================*/
create table t_um_partner
(
   id                   integer not null comment '主键',
   channel              char(1) comment '微信/QQ/微博',
   channel_code         varchar(50) comment '第三方分配的用户编号',
   user_id              integer comment '用户ID',
   crdt                 datetime comment '创建日期',
   primary key (id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Index: u_um_partner1                                         */
/*==============================================================*/
create unique index u_um_partner1 on t_um_partner
(
   channel_code
);

/*==============================================================*/
/* Index: u_um_partner2                                         */
/*==============================================================*/
create unique index u_um_partner2 on t_um_partner
(
   user_id
);

/*==============================================================*/
/* Table: t_um_user                                             */
/*==============================================================*/
create table t_um_user
(
   id                   integer not null comment '用户主键',
   mobile               varchar(20) comment '手机号/自动生成号',
   chcked               tinyint comment '0-未验证 ; 1-已验证',
   mobile_kind          tinyint comment '0-手机号; 1-自动生成号',
   status               char(1) comment '有效/无效',
   kind                 char(1) comment '普通用户/管理员',
   nickname             varchar(20) comment '用户昵称',
   password             varchar(20) comment '登录密码',
   channel              char(1) comment '微信/注册/QQ/微博',
   imei                 varchar(50) comment '设备唯一的串号',
   device_kind          char(1) comment 'android/ios/winphone/pc/mpc',
   os_version           varchar(20) comment 'ios或安卓的版本号',
   brand                varchar(50) comment '三星/华为/苹果',
   device_model         varchar(50) comment '厂商给设备定义的编号',
   crdt                 datetime comment '创建日期',
   updt                 datetime comment '修改日期',
   ip                   varchar(50) comment '注册IP',
   primary key (id)
)
engine=innodb default charset=utf8;

alter table t_um_user comment '用户表';

/*==============================================================*/
/* Index: u_um_user1                                            */
/*==============================================================*/
create unique index u_um_user1 on t_um_user
(
   mobile
);

/*==============================================================*/
/* Table: t_um_user_contact                                     */
/*==============================================================*/
create table t_um_user_contact
(
   id                   integer not null comment '主键',
   user_id              integer not null comment '用户ID',
   co_user_id           integer not null comment '关系用户ID',
   remark               varchar(20) comment '关系用户备注',
   type                 char(1) comment '关系类型',
   crdt                 datetime comment '创建日期',
   updt                 datetime comment '修改日期',
   primary key (id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Index: i_um_r_m                                              */
/*==============================================================*/
create index i_um_r_m on t_um_user_contact
(
   user_id
);
