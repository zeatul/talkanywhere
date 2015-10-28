package com.hawk.pub.pkgen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.hawk.pub.spring.FrameworkContext;

public class PkGenerator {
	
	private static JdbcTemplate jdbcTemplate = FrameworkContext.getApplicationContext().getBean(JdbcTemplate.class);
	
	private final static String sql = "replace into t_km_global_sequence(stub) values('a')";

	public static long genPk(){
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  
		        return ps;
			}
		},keyHolder);
		
				
		return (Long)(keyHolder.getKeyList().get(0).get("GENERATED_KEY"));
		
		

	}

}
