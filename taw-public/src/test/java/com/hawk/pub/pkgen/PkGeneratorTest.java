package com.hawk.pub.pkgen;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.hawk.pub.pkgen.PkGenerator;

@ContextConfiguration(locations={"classpath*:com/hawk/pub/spring/applicationContext-pub-*.xml"})
public class PkGeneratorTest extends AbstractJUnit4SpringContextTests {
	
	@Test
	public void testGenPk(){
		long pk = PkGenerator.genPk();
		System.out.println(pk);
	}

}
