package com.carrotgarden.util.net;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	static final Pattern pattern = Pattern.compile(NetConst.ADDRESS_REGEX);

	/** from tuple : "host:port" */
	// @JsonCreator
	public static NetAddress formTuple(final String address) {

		// System.out.println("address = " + address);

		final String host;
		final int port;

		if (address == null) {
			host = "0.0.0.0";
			port = 0;
		} else {
			final Matcher matcher = pattern.matcher(address);
			if (matcher.matches()) {

				// System.out.println("1 = " + matcher.group(1));
				// System.out.println("2 = " + matcher.group(2));
				// System.out.println("3 = " + matcher.group(3));

				host = matcher.group(1);
				port = NetUtil.safePort(matcher.group(3));
			} else {
				host = "0.0.0.0";
				port = 0;
			}
		}

		// if (address == null || address.length() == 0) {
		// parts = new String[] { "0.0.0.0", "0" };
		// } else if (address.contains(NetConst.ADDRESS_SEPARATOR[0])) {
		// parts = address.split(NetConst.ADDRESS_SEPARATOR[0]);
		// } else if (address.contains(NetConst.ADDRESS_SEPARATOR[1])) {
		// parts = address.split(NetConst.ADDRESS_SEPARATOR[1]);
		// } else {
		// parts = new String[] { address, "0" };
		// }

		return new NetAddress(host, port);

	}

	@Override
	public String toString() {
		return intoTuple();
	}

	/**
	 * use original(non resolved) host name and ":" separator
	 * 
	 * "host:port"
	 */
	// @JsonValue
	public String intoTuple() {
		return getHost() + ":" + getPort();
	}

}
