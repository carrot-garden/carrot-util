package com.carrotgarden.util.xform;

import java.io.File;

import javax.xml.transform.Transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

public class Maker {

	private final static Logger log = LoggerFactory.getLogger(Maker.class);

	private final File fileXdoc;
	private final File fileXform;
	private final File fileXprops;
	private final File fileXresult;

	public Maker( //
			final File fileXdoc, //
			final File fileXform, //
			final File fileXprops, //
			final File fileXresult //
	) {
		this.fileXdoc = fileXdoc;
		this.fileXform = fileXform;
		this.fileXprops = fileXprops;
		this.fileXresult = fileXresult;
	}

	public void execute() throws Exception {

		log.debug("fileXdoc    : {}", fileXdoc);
		log.debug("fileXform   : {}", fileXform);
		log.debug("fileXprops  : {}", fileXprops);
		log.debug("fileXresult : {}", fileXresult);

		final Document xdoc = UtilX.loadXdoc(fileXdoc);

		final Transformer xform = UtilX.loadXform(fileXform);

		final Params xparams = UtilX.loadXprops(fileXprops);

		UtilX.apply(xparams, xform);

		UtilX.saveXresult(xdoc, xform, fileXresult);

	}

}
