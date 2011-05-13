package com.carrotgarden.utils.json;

import java.io.InputStream;
import java.util.Map;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.Module;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSON {

	private static Logger log = LoggerFactory.getLogger(JSON.class);

	private static final ObjectMapper mapper = new ObjectMapper();

	static {

		applyMapperPolicy(mapper);

	}

	@SuppressWarnings("deprecation")
	public static void applyMapperPolicy(final ObjectMapper mapper) {

		/* READ: */

		// must annotate fields with @JsonProperty explicitly
		mapper.getDeserializationConfig().disable(
				DeserializationConfig.Feature.AUTO_DETECT_FIELDS);
		// must annotate fields with @JsonProperty explicitly
		mapper.getDeserializationConfig().disable(
				DeserializationConfig.Feature.AUTO_DETECT_SETTERS);
		// must annotate fields with @JsonCerator explicitly
		mapper.getDeserializationConfig().disable(
				DeserializationConfig.Feature.AUTO_DETECT_CREATORS);
		// make all unknown into optional
		mapper.getDeserializationConfig().disable(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);

		/* WRITE: */

		// will introduce tabs
		mapper.getSerializationConfig().enable(
				SerializationConfig.Feature.INDENT_OUTPUT);

		// must annotate with @JsonProperty explicitly
		mapper.getSerializationConfig().disable(
				SerializationConfig.Feature.AUTO_DETECT_FIELDS);
		// must annotate with @JsonProperty explicitly
		mapper.getSerializationConfig().disable(
				SerializationConfig.Feature.AUTO_DETECT_GETTERS);
		// must annotate with @JsonProperty explicitly
		mapper.getSerializationConfig().disable(
				SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS);
		// no more empty fields
		mapper.getSerializationConfig().disable(
				SerializationConfig.Feature.WRITE_NULL_PROPERTIES);

	}

	public static void registerModule(Module module) {
		mapper.registerModule(module);
	}

	public static void applyMapperPolicyIdent(ObjectMapper mapper, boolean on) {
		if (on) {
			mapper.getSerializationConfig().enable(
					SerializationConfig.Feature.INDENT_OUTPUT);
		} else {
			mapper.getSerializationConfig().disable(
					SerializationConfig.Feature.INDENT_OUTPUT);
		}
	}

	public static <T> T fromText(String text, Class<T> klaz) {
		try {
			T value = mapper.readValue(text, klaz);
			return value;
		} catch (Exception e) {
			log.error("", e);
			return null;
		}
	}

	public static <T> T fromCP(String path, Class<T> klaz) {
		try {
			InputStream input = JSON.class.getResourceAsStream(path);
			T value = mapper.readValue(input, klaz);
			input.close();
			return value;
		} catch (Exception e) {
			log.error("", e);
			return null;
		}
	}

	public static <T> T fromURL(String textURL, Class<T> klaz) {
		try {
			String text = null; // new ExtraURL(textURL).readAsText();
			T value = mapper.readValue(text, klaz);
			return value;
		} catch (Exception e) {
			log.error("", e);
			return null;
		}
	}

	public static String intoText(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			log.error("", e);
			return null;
		}
	}

	public static boolean update(final Object value, final String json) {
		try {
			final ObjectReader reader = mapper.updatingReader(value);
			reader.readValue(json);
			return true;
		} catch (Exception e) {
			log.error("", e);
			return false;
		}
	}

	public static final String PROP_NAME = "json";

	public static boolean update(final Object value,
			final Map<String, String> config) {
		final String json = config.get(PROP_NAME);
		return update(value, json);
	}

}
