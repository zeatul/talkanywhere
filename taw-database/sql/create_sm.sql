/**
 * 创建消息管理表
 */

use sm;

drop index i_sm_m1 on t_sm_sms;

drop table if exists t_sm_sms;

drop table if exists t_sm_sms_feedback;

/*==============================================================*/
/* Table: t_sm_sms                                              */
/*==============================================================*/
create table t_sm_sms
(
   id                   bigint(20) not null comment '主键',
   mobile               varchar(20) comment '手机号',
   message              varchar(500) comment '消息体',
   kind                 char(1) comment '消息类型',
   status               char(1) comment '发送状态',
   send_time            timestamp(3) comment '发送时间',
   send_times           integer comment '发送次数',
   expire_time          timestamp(3) comment '失效时间',
   max_times            integer comment '最大可发送次数',
   crdt                 timestamp(3) comment '创建时间',
   updt                 timestamp(3) comment '更新时间',
   primary key (id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Index: i_sm_m1                                               */
/*==============================================================*/
create index i_sm_m1 on t_sm_sms
(
   mobile
);

/*==============================================================*/
/* Table: t_sm_sms_feedback                                     */
/*==============================================================*/
create table t_sm_sms_feedback
(
   id                   bigint(20) not null comment '主键',
   message_id           bigint(20) not null comment '消息主键',
   send_time            timestamp(3) comment '发送时间',
   feedback             varchar(500) comment '失败反馈',
   primary key (id)
)
engine=innodb default charset=utf8;
