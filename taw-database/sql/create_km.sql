/**
 * 创建主键生成表
 */

use km;
zz
drop index u_se on t_km_global_sequence;

drop table if exists t_km_global_sequence;

/*==============================================================*/
/* Table: t_km_global_sequence                                  */
/*==============================================================*/
create table t_km_global_sequence
(
   id                   bigint(20) not null auto_increment comment '主键',
   stub                 char(1) not null comment 'STUB',
   primary key (id)
)
engine=myisam default charset=utf8;

/*==============================================================*/
/* Index: u_se                                                  */
/*==============================================================*/
create unique index u_se on t_km_global_sequence
(
   stub
);
