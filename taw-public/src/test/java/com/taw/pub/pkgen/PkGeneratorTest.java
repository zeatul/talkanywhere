package com.taw.pub.pkgen;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath*:com/taw/pub/spring/applicationContext-pub-*.xml"})
public class PkGeneratorTest extends AbstractJUnit4SpringContextTests {
	
	@Test
	public void testGenPk(){
		long pk = PkGenerator.genPk();
		System.out.println(pk);
	}

}
