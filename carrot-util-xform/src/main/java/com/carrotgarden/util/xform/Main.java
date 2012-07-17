package com.carrotgarden.util.xform;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements Const {

	private final static Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(final String[] args) throws Exception {

		log.debug("init");

		final File fileXdoc = new File(System.getProperty(FILE_XDOC));
		final File fileXform = new File(System.getProperty(FILE_XFORM));
		final File fileXprops = new File(System.getProperty(FILE_XPROPS));
		final File fileXresult = new File(System.getProperty(FILE_XRESULT));

		final Maker maker = new Maker(fileXdoc, fileXform, fileXprops,
				fileXresult);

		maker.execute();

		log.debug("done");

	}

}
