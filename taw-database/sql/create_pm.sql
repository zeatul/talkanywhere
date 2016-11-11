/**
 * 创建图片表
 */

use pm;

drop index ui_pic_uuid on t_pm_picture;

drop table if exists t_pm_picture;

drop index i_pm_pic_com1 on t_pm_picture_comment;

drop table if exists t_pm_picture_comment;

drop index i_pm_pic_f1 on t_pm_picture_forward;

drop table if exists t_pm_picture_forward;

drop index ui_pm_thumb_1 on t_pm_picture_thumb;

drop table if exists t_pm_picture_thumb;

/*==============================================================*/
/* Table: t_pm_picture                                          */
/*==============================================================*/
create table t_pm_picture
(
   id                   bigint(20) not null comment '主键',
   uuid                 varchar(60) comment 'UUID',
   user_id              bigint(20) comment '上传者ID',
   nickname             varchar(50) comment '上传者昵称',
   sex                  char(1) comment '用户性别',
   scene_id             bigint(20) comment '场景Id',
   scene_name           varchar(100) comment '场景名称',
   status               char(1) comment '图片状态',
   kind                 varchar(50) comment '图片类型',
   suffix               varchar(50) comment '图片后缀',
   app_src              varchar(50) comment '图片来源（会话，私信）',
   app_src_id           bigint(20) comment '应用来源ID',
   location             varchar(1000) comment '拍摄地点',
   photo_time           timestamp(3) null default null comment '拍摄时间',
   s_url                varchar(200) comment '小图片地址',
   m_url                varchar(200) comment '中图片地址',
   l_url                varchar(200) comment '大图片地址',
   reference_count      integer comment '内部关联引用计数',
   hot                  integer comment '热门图片',
   up_count             integer comment '点赞数',
   down_count           integer comment '不支持数目',
   comment_count        integer comment '评论数',
   forward_count        integer comment '转发数',
   scene_count          integer comment '场景数',
   s_size               bigint(20) comment '小图片大小',
   m_size               bigint(20) comment '中图片大小',
   l_size               bigint(20) comment '大图片大小',
   on_scene             tinyint comment '图片发送者是否在场',
   description          varchar(1000) comment '图片描述',
   crdt                 timestamp(3) null default null comment '创建时间',
   primary key (id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Index: ui_pic_uuid                                           */
/*==============================================================*/
create unique index ui_pic_uuid on t_pm_picture
(
   uuid
);

/*==============================================================*/
/* Table: t_pm_picture_comment                                  */
/*==============================================================*/
create table t_pm_picture_comment
(
   id                   bigint(20) not null comment '主键',
   pic_id               bigint(20) comment '图片ID',
   user_id              bigint(20) comment '评论者ID',
   nickname             varchar(50) comment '评论者昵称',
   sex                  char(1) comment '用户性别',
   content              varchar(1000) comment '评论内容',
   crdt                 timestamp(3) null default null comment '评论时间',
   primary key (id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Index: i_pm_pic_com1                                         */
/*==============================================================*/
create index i_pm_pic_com1 on t_pm_picture_comment
(
   pic_id,
   user_id
);

/*==============================================================*/
/* Table: t_pm_picture_forward                                  */
/*==============================================================*/
create table t_pm_picture_forward
(
   id                   bigint(20) not null comment '主键',
   pic_id               bigint(20) comment '图片ID',
   user_id              bigint(20) comment '转发者ID',
   nickname             varchar(50) comment '转发者昵称',
   sex                  char(1) comment '用户性别',
   crdt                 timestamp(3) null default null comment '转发时间',
   primary key (id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Index: i_pm_pic_f1                                           */
/*==============================================================*/
create index i_pm_pic_f1 on t_pm_picture_forward
(
   pic_id,
   user_id
);

/*==============================================================*/
/* Table: t_pm_picture_thumb                                    */
/*==============================================================*/
create table t_pm_picture_thumb
(
   id                   bigint(20) not null comment '主键',
   pic_id               bigint(20) comment '图片ID',
   user_id              bigint(20) comment '点赞者ID',
   nickname             varchar(100) comment '点赞者昵称',
   sex                  char(1) comment '用户性别',
   kind                 char(1) comment 'up down',
   crdt                 timestamp(3) null default null comment '点赞时间',
   primary key (id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Index: ui_pm_thumb_1                                         */
/*==============================================================*/
create unique index ui_pm_thumb_1 on t_pm_picture_thumb
(
   user_id,
   pic_id
);
