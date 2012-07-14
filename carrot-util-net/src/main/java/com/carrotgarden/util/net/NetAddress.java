package com.carrotgarden.util.net;

import java.net.InetAddress;
import java.net.InetSocketAddress;

@SuppressWarnings("serial")
public class NetAddress extends InetSocketAddress {

	private final String host;

	/**
	 * original host name unaffected by DNS resolution; this is different from
	 * {@link #getHostName()} which calls DNS resolve;
	 */
	public String getHost() {
		return host;
	}

	public NetAddress(final String host, final int port) {
		super(host, port);
		this.host = host;
	}

	public NetAddress(final InetAddress addr, final int port) {
		super(addr, port);
		this.host = addr.getHostName();
	}

	/** from tuple : "host:port" */
	// @JsonCreator
	public static NetAddress formTuple(final String address) {

		final String[] parts;

		if (address == null || address.length() == 0) {
			parts = new String[] { "0.0.0.0", "0" };
		} else if (address.contains(NetConst.ADDRESS_SEPARATOR[0])) {
			parts = address.split(NetConst.ADDRESS_SEPARATOR[0]);
		} else if (address.contains(NetConst.ADDRESS_SEPARATOR[1])) {
			parts = address.split(NetConst.ADDRESS_SEPARATOR[1]);
		} else {
			parts = new String[] { address, "0" };
		}

		final String host = parts[0];
		final int port = NetUtil.safePort(parts[1]);

		return new NetAddress(host, port);

	}

	@Override
	public String toString() {
		return intoTuple();
	}

	/** into tuple : "host/port" */
	// @JsonValue
	public String intoTuple() {
		return getHost() + NetConst.ADDRESS_SEPARATOR[0] + getPort();
	}

}
