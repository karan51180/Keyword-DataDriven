package com.test;


	import org.junit.Test;
	import static org.junit.Assert.assertEquals;

	public class JunitTest {
	   @Test
		
	   public void testAdd() {
	      String str = "Junit is working fine";
	      assertEquals("Junit is working fine",str);
	   }
	}

