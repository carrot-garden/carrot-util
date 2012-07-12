package com.carrotgarden.util.net;

import java.util.Map;

import com.typesafe.config.Config;

public final class NetUtil {

	public static boolean isValidMulticastAddress(final NetAddress netAddress) {
		return netAddress != null && netAddress.getAddress() != null
				&& netAddress.getAddress().isMulticastAddress();
	}

	public static NetPoint pointFrom(final Map<String, Object> map) {
		final NetPoint point = new NetPoint();
		point.props.putAll(map);
		return point;
	}

	public static NetPoint pointForm(final Config conf) {
		return pointFrom(conf.root().unwrapped());
	}

	public static int safePort(final String port) {
		try {
			final int number = Integer.parseInt(port);
			if (number < 0 || number > 65535) {
				return 0;
			} else {
				return number;
			}
		} catch (final Throwable e) {
			return 0;
		}
	}

	public static boolean isValid(final CharSequence text) {
		return text != null && text.length() > 0;
	}

}
