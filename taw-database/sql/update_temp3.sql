use um;

drop index ui_um_version on t_um_version;

drop table if exists t_um_version;

/*==============================================================*/
/* Table: t_um_version                                          */
/*==============================================================*/
create table t_um_version
(
   id                   bigint(20) not null comment '主键',
   model                varchar(20) comment '模块号',
   version              varchar(50) comment '版本号',
   description          varchar(1000) comment '版本描述',
   forced               tinyint comment '强迫更新',
   crdt                 timestamp(3) null comment '创建日期',
   updt                 timestamp(3) null comment '修改日期',
   primary key (id)
)
 engine=myisam default charset=utf8;

alter table t_um_version comment '版本';

/*==============================================================*/
/* Index: ui_um_version                                         */
/*==============================================================*/
create unique index ui_um_version on t_um_version
(
   model,
   version
);
