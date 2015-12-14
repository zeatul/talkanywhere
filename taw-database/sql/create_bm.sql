/**
 * 创建基础数据表
 */

use bm;

drop table if exists t_bm_district;

/*==============================================================*/
/* Table: t_bm_district                                         */
/*==============================================================*/
create table t_bm_district
(
   id                   bigint(20) not null comment '主键',
   name                 varchar(50) not null comment '名称',
   level                integer not null comment '0-国;1-省;2-市;3-区县;4-乡镇,5-商圈',
   pid                  bigint(20) not null comment '上级行政区主键',
   path                 varchar(500) not null comment '全路径',
   status               char(1) not null comment '0-未启用，1，启用',
   primary key (id)
);

alter table t_bm_district comment '地区';

