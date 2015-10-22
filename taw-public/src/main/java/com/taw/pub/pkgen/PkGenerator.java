package com.taw.pub.pkgen;

import org.springframework.jdbc.core.JdbcTemplate;

import com.taw.pub.spring.FrameworkContext;

public class PkGenerator {
	
	private static JdbcTemplate jdbcTemplate = FrameworkContext.getApplicationContext().getBean(JdbcTemplate.class);
	
	private final static String sql = "replace into t_km_global_sequence(stub) values('a');select last_insert_id()"
			;
	
	public static long genPk(){
		return jdbcTemplate.queryForObject(sql, Long.class);
	}

}
