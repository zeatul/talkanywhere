/**
 * 场景基础数据
 */

use tm;

drop table if exists t_tm_scene;

/*==============================================================*/
/* Table: t_tm_scene                                            */
/*==============================================================*/
create table t_tm_scene
(
   id                   bigint(20) not null comment '主键',
   name                 varchar(100) comment '场景名称',
   kind                 char(1) comment '场景类型',
   status               char(1) comment '状态',
   radius               integer comment '半径(米)',
   center_lng           decimal(17,10) comment '中心点经度',
   center_lat           decimal(17,10) comment '中心点纬度',
   left_top_lng         decimal(17,10) comment '左上角经度',
   left_top_lat         decimal(17,10) comment '左上角纬度',
   left_bottom_lng      decimal(17,10) comment '左下角经度',
   left_bottom_lat      decimal(17,10) comment '左下角纬度',
   right_top_lng        decimal(17,10) comment '右上角经度',
   right_top_lat        decimal(17,10) comment '右上角纬度',
   right_bottom_lng     decimal(17,10) comment '右下角经度',
   right_bottom_lat     decimal(17,10) comment '右下角纬度',
   country              bigint(20) comment '国家',
   province             bigint(20) comment '省',
   city                 bigint(20) comment '市',
   county               bigint(20) comment '区县',
   town                 bigint(20) comment '乡镇',
   region               bigint(20) comment '地区/商圈',
   address              varchar(1000) comment '全地址',
   crdt                 timestamp(3) null comment '创建时间',
   updt                 timestamp(3) null comment '修改时间',
   primary key (id)
)
engine=innodb default charset=utf8;

alter table t_tm_scene comment '场景';
