/**
 * 创建场景表
 */

use tm;

drop index u_tm_bk1 on t_tm_bookmark;

drop table if exists t_tm_bookmark;

drop index i_tm_post1 on t_tm_conversation;

drop table if exists t_tm_conversation;

drop index u_tm_fp1 on t_tm_foot_print;

drop table if exists t_tm_foot_print;

drop index i_tm_message1 on t_tm_message;

drop table if exists t_tm_message;

drop table if exists t_tm_scene;

drop index u_tm_spic on t_tm_scene_pic;

drop table if exists t_tm_scene_pic;

drop index u_tm_sctag1 on t_tm_scene_tag;

drop table if exists t_tm_scene_tag;

/*==============================================================*/
/* Table: t_tm_bookmark                                         */
/*==============================================================*/
create table t_tm_bookmark
(
   id                   bigint(20) not null comment '主键',
   user_id              bigint(20) comment '用户主键',
   scene_id             bigint(20) comment '场景主键',
   scene_name           varchar(50) comment '场景名称',
   book_time            datetime comment '收藏时间',
   primary key (id)
)
engine=innodb default charset=utf8;

alter table t_tm_bookmark comment '场景书签';

/*==============================================================*/
/* Index: u_tm_bk1                                              */
/*==============================================================*/
create unique index u_tm_bk1 on t_tm_bookmark
(
   user_id,
   scene_id
);

/*==============================================================*/
/* Table: t_tm_conversation                                     */
/*==============================================================*/
create table t_tm_conversation
(
   id                   bigint(20) not null comment '主键',
   scene_id             bigint(20) comment '场景主键',
   post_user_id         bigint(20) comment '发言者主键',
   post_nickname        varchar(20) comment '发言者昵称',
   message              varchar(500) comment '发言内容',
   r_post_id            bigint(20) comment '被回复的发言ID',
   r_post_user_id       bigint(20) comment '被回复的发言者ID',
   r_post_nickname      varchar(20) comment '被回复的发言者昵称',
   pic_count            integer comment '包含的图片数量',
   post_date            datetime comment '发言时间',
   primary key (id)
)
engine=innodb default charset=utf8;

alter table t_tm_conversation comment '场景会话';

/*==============================================================*/
/* Index: i_tm_post1                                            */
/*==============================================================*/
create index i_tm_post1 on t_tm_conversation
(
   scene_id,
   post_user_id
);

/*==============================================================*/
/* Table: t_tm_foot_print                                       */
/*==============================================================*/
create table t_tm_foot_print
(
   id                   bigint(20) not null comment '主键',
   user_id              bigint(20) not null comment '用户主键',
   scene_id             bigint(20) not null comment '场景主键',
   scene_name           varchar(20) comment '场景名称',
   last_enter_time      datetime comment '最后进入时间',
   enter_times          integer comment '进入次数累计',
   primary key (id)
)
engine=innodb default charset=utf8;

alter table t_tm_foot_print comment '场景足迹';

/*==============================================================*/
/* Index: u_tm_fp1                                              */
/*==============================================================*/
create unique index u_tm_fp1 on t_tm_foot_print
(
   user_id,
   scene_id
);

/*==============================================================*/
/* Table: t_tm_message                                          */
/*==============================================================*/
create table t_tm_message
(
   id                   bigint(20) not null,
   receiver_id          bigint(20),
   scene_id             bigint(20),
   content              varchar(500),
   sender_id            bigint(20),
   sender_nickname      varchar(20),
   send_time            datetime,
   primary key (id)
)
engine=innodb default charset=utf8;

alter table t_tm_message comment '场景私信';

/*==============================================================*/
/* Index: i_tm_message1                                         */
/*==============================================================*/
create index i_tm_message1 on t_tm_message
(
   receiver_id,
   scene_id,
   sender_id
);

/*==============================================================*/
/* Table: t_tm_scene                                            */
/*==============================================================*/
create table t_tm_scene
(
   id                   bigint(20) not null comment '主键',
   name                 varchar(50) comment '场景名称',
   kind                 char(1) comment '场景类型',
   lng                  decimal(17,10) comment '经度',
   lat                  decimal(17,10) comment '纬度',
   country              varchar(20) comment '国家',
   province             varchar(20) comment '省',
   city                 varchar(20) comment '市',
   county               varchar(20) comment '区县',
   town                 varchar(20) comment '乡镇',
   region               varchar(20) comment '地区/商圈',
   primary key (id)
)
engine=innodb default charset=utf8;

alter table t_tm_scene comment '场景';

/*==============================================================*/
/* Table: t_tm_scene_pic                                        */
/*==============================================================*/
create table t_tm_scene_pic
(
   主键                   bigint(20) not null comment '主键',
   scene_id             bigint(20) not null comment '场景ID',
   picture_id           bigint(20) not null comment '图片ID',
   primary key (主键)
)
engine=innodb default charset=utf8;

alter table t_tm_scene_pic comment '场景图片';

/*==============================================================*/
/* Index: u_tm_spic                                             */
/*==============================================================*/
create index u_tm_spic on t_tm_scene_pic
(
   scene_id,
   picture_id
);

/*==============================================================*/
/* Table: t_tm_scene_tag                                        */
/*==============================================================*/
create table t_tm_scene_tag
(
   id                   bigint(20) not null comment '主键',
   scene_id             bigint(20) not null comment '场景主键',
   tag_id               bigint(20) not null comment '标签主键',
   tag_name             varchar(20) comment '标签内容',
   crdt                 datetime comment '创建日期',
   primary key (id)
)
engine=innodb default charset=utf8;

alter table t_tm_scene_tag comment '场景标签';

/*==============================================================*/
/* Index: u_tm_sctag1                                           */
/*==============================================================*/
create unique index u_tm_sctag1 on t_tm_scene_tag
(
   scene_id,
   tag_id
);
