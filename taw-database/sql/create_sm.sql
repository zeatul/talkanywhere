/**
 * 创建消息管理表
 */

use sm;

drop table if exists t_sm_feedback;

drop index i_sm_m1 on t_sm_message;

drop table if exists t_sm_message;

/*==============================================================*/
/* Table: t_sm_feedback                                         */
/*==============================================================*/
create table t_sm_feedback
(
   id                   bigint(20) not null comment '主键',
   message_id           bigint(20) not null comment '消息主键',
   send_time            datetime comment '发送时间',
   feedback             varchar(500) comment '失败反馈',
   primary key (id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Table: t_sm_message                                          */
/*==============================================================*/
create table t_sm_message
(
   id                   bigint(20) not null comment '主键',
   mobile               varchar(20) comment '手机号',
   message              varchar(500) comment '消息体',
   kind                 char(1) comment '消息类型',
   status               char(1) comment '发送状态',
   send_time            datetime comment '发送时间',
   send_times           integer comment '发送次数',
   expire_time          datetime comment '失效时间',
   max_times            integer comment '最大可发送次数',
   crdt                 datetime comment '创建时间',
   updt                 datetime comment '更新时间',
   primary key (id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Index: i_sm_m1                                               */
/*==============================================================*/
create index i_sm_m1 on t_sm_message
(
   mobile
);
