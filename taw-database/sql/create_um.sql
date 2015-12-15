/**
 * 创建用户表
 */

use um;

drop index u_um_user1 on t_um_user;

drop table if exists t_um_user;

/*==============================================================*/
/* Table: t_um_user                                             */
/*==============================================================*/
create table t_um_user
(
   id                   bigint(20) not null comment '用户主键',
   mobile               varchar(20) comment '手机号/自动生成号',
   chcked               tinyint comment '0-未验证 ; 1-已验证',
   mobile_kind          tinyint comment '0-手机号; 1-自动生成号',
   status               char(1) comment '有效/无效',
   kind                 char(1) comment '普通用户/管理员',
   sex                  char(1) comment '用户性别',
   nickname             varchar(20) comment '用户昵称',
   password             varchar(200) comment '登录密码',
   channel              char(1) comment '微信/注册/QQ/微博',
   imei                 varchar(50) comment '设备唯一的串号',
   device_kind          char(1) comment '设备类型:android/ios/winphone/pc/mpc',
   os_version           varchar(20) comment 'ios或安卓的版本号',
   brand                varchar(50) comment '三星/华为/苹果',
   device_model         varchar(50) comment '厂商给设备定义的编号',
   ip                   varchar(50) comment '注册IP',
   crdt                 timestamp(3) comment '创建日期',
   updt                 timestamp(3) comment '修改日期',
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
