/**
 * 场景基础数据
 */

use bm;

/**
 * 国家 保留 主键 1-1000
 */
insert into t_bm_district(id,name,level,pid,path,status) values(1,'中国',0,0,'/1','0');

/**
 * 省份,直辖市， 保留主键 1001 -10000
 */
insert into t_bm_district(id,name,level,pid,path,status) values(1001,'上海市',1,1,'/1/1001','0');
insert into t_bm_district(id,name,level,pid,path,status) values(1002,'陕西',1,1,'/1/1002','0');
insert into t_bm_district(id,name,level,pid,path,status) values(1003,'四川',1,1,'/1/1003','0');
insert into t_bm_district(id,name,level,pid,path,status) values(1004,'浙江',1,1,'/1/1004','0');

/**
 * 地级城市 保留主键 10001 -30000
 */
insert into t_bm_district(id,name,level,pid,path,status) values(10001,'上海',2,1001,'/1/1001/10001','0');
insert into t_bm_district(id,name,level,pid,path,status) values(10002,'西安',2,1002,'/1/1002/10002','0');
insert into t_bm_district(id,name,level,pid,path,status) values(10003,'成都',2,1003,'/1/1003/10003','0');
insert into t_bm_district(id,name,level,pid,path,status) values(10004,'杭州',2,1004,'/1/1004/10004','0');
insert into t_bm_district(id,name,level,pid,path,status) values(10005,'咸阳',2,1002,'/1/1002/10005','0');
 
/**
 * 区县级城市 保留主键 30001 -100000
 */
insert into t_bm_district(id,name,level,pid,path,status) values(30001,'乾县',3,10005,'/1/1002/10005/30001','0');
 
 
 /**
 * 乡镇级城市 保留主键 100001 -300000
 */
 
 