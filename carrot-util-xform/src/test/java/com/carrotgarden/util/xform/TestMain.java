package com.carrotgarden.util.xform;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMain {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMain() throws Exception {

		System.setProperty(Const.FILE_XDOC, //
				"src/test/resources/xdoc/Fix.xml");

		System.setProperty(Const.FILE_XFORM, //
				"src/test/resources/xform/Builder.xsl");

		System.setProperty(Const.FILE_XPROPS,
				"src/test/resources/xprops/transform.properties");

		System.setProperty(Const.FILE_XRESULT, //
				"target/generated-transform/xresult.txt");

		Main.main(null);

		assertTrue(true);

	}

}
