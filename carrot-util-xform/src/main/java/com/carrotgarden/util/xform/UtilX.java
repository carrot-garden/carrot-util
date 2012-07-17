package com.carrotgarden.util.xform;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

public final class UtilX {

	public static void apply(final Params xparams, final Transformer xform) {

		for (final Map.Entry<String, String> entry : xparams.entrySet()) {

			xform.setParameter(entry.getKey(), entry.getValue());

		}

	}

	public static Document loadXdoc(final File fileXdoc) throws Exception {

		final DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();

		final DocumentBuilder builder = factory.newDocumentBuilder();

		final Document xdoc = builder.parse(fileXdoc);

		return xdoc;

	}

	public static Transformer loadXform(final File fileXform) throws Exception {

		final StreamSource source = new StreamSource(fileXform);

		final TransformerFactory factory = TransformerFactory.newInstance();

		final Transformer xform = factory.newTransformer(source);

		return xform;

	}

	public static Params loadXprops(final File fileXparams) throws Exception {

		final Properties props = new Properties();

		props.load(new FileReader(fileXparams));

		final Params params = new Params();

		for (final Map.Entry<Object, Object> entry : props.entrySet()) {
			final Object key = entry.getKey();
			final Object value = entry.getValue();
			if (key == null || value == null) {
				continue;
			}
			params.put(key.toString(), value.toString());
		}

		return params;

	}

	public static void saveXresult(//
			final Document xdoc, //
			final Transformer xform, //
			final File fileXresult //
	) throws Exception {

		fileXresult.getParentFile().mkdirs();

		final Source source = new DOMSource(xdoc);

		final Result result = new StreamResult(new FileOutputStream(fileXresult));

		xform.transform(source, result);

	}

	private UtilX() {
	}

}
