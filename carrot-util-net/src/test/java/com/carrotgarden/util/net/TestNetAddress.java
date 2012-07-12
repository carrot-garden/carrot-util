package com.carrotgarden.util.net;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestNetAddress {

	static {
		System.setProperty("java.net.preferIPv4Stack", "true");
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFormTuple() {

		assertEquals(NetAddress.formTuple(null),
				NetAddress.formTuple("0.0.0.0:0"));

		assertEquals(NetAddress.formTuple("localhost"),
				NetAddress.formTuple("localhost:0"));

		assertEquals(NetAddress.formTuple("host:12345").getHost(), "host");

		assertEquals(NetAddress.formTuple("host:12345").getPort(), 12345);

		assertEquals(NetAddress.formTuple("host").getPort(), 0);

		assertEquals(NetAddress.formTuple("host:xxx").getPort(), 0);

	}

}
