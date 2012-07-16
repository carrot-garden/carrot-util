package com.carrotgarden.util.openfast;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openfast.DecimalValue;
import org.openfast.FieldValue;
import org.openfast.GroupValue;
import org.openfast.IntegerValue;
import org.openfast.ScalarValue;
import org.openfast.SequenceValue;
import org.openfast.template.Field;
import org.openfast.template.Group;
import org.openfast.template.LongValue;
import org.openfast.template.Scalar;
import org.openfast.template.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carrotgarden.util.enums.fix.FixAttributeMap;
import com.carrotgarden.util.enums.fix.FixAttributeType;
import com.carrotgarden.util.enums.fix.FixCodeCFI;
import com.carrotgarden.util.enums.fix.FixEntryType;
import com.carrotgarden.util.enums.fix.FixMessageType;
import com.carrotgarden.util.enums.fix.FixSequence;
import com.carrotgarden.util.enums.fix.FixTag;
import com.carrotgarden.util.enums.fix.FixUpdateAction;
import com.carrotgarden.util.math.MathExtra.DoubleParts;

public final class OpenFastUtil {

	private final static Logger log = LoggerFactory
			.getLogger(OpenFastUtil.class);

	public static enum Mode {
		PLAIN, PRETTY,
	}

	public static final String BLANK = "";
	public static final String TABS = "\t";

	public static final char EOL = '\n';
	public static final char SOH = '\u0001';
	public static final char TAB = '\t';

	public static final String PATTERN_YEAR_MONTH = "yyyyMM";
	public static final int PATTERN_YEAR_MONTH_LENGTH = PATTERN_YEAR_MONTH
			.length();

	public static final DateTimeFormatter FORMAT_YEAR_MONTH = //
	DateTimeFormat.forPattern(PATTERN_YEAR_MONTH);

	public static void append(final StringBuilder text,
			final GroupValue groupValue, final String tabs, final Mode mode) {

		final Group group = groupValue.getGroup();

		final Field[] fields = group.getFields();

		for (final Field field : fields) {

			final String fieldId = field.getId();
			final String fieldName = field.getName();
			final FieldValue fieldValue = groupValue.getValue(fieldName);

			if (fieldValue == null) {

				continue;

			} else {

				if (fieldValue instanceof SequenceValue) {

					final SequenceValue sequenceValue = (SequenceValue) fieldValue;
					final Sequence sequence = sequenceValue.getSequence();
					final Scalar header = sequence.getLength();
					final GroupValue[] nestedValues = sequenceValue.getValues();

					final String name = header.getName();
					final String key = header.getId();
					final String value = Integer.toString(nestedValues.length);

					append(text, tabs, name, key, value, mode);

					for (final GroupValue nestedValue : nestedValues) {
						append(text, nestedValue, tabs + TABS, mode);
					}

					continue;

				}

				if (fieldValue instanceof ScalarValue) {
					if (fieldId == null) {
						if ("templateId".equals(fieldName)) {
							// log.debug("fieldName : {}", fieldName);
							continue;
						} else {
							log.error("fieldName unknown: {}", fieldName);
							continue;
						}
					} else {
						append(text, tabs, fieldName, fieldId,
								fieldValue.toString(), mode);
						continue;
					}
				}

				log.error("fieldValue unknown: {}", fieldValue.getClass()
						.getName());

			}

		}

	}

	public static void append(final StringBuilder text, final String tabs,
			final String name, final String key, final String value,
			final Mode mode) {

		switch (mode) {
		case PLAIN:
			text.append(key);
			text.append("=");
			text.append(value);
			text.append(SOH);
			break;
		case PRETTY:
			text.append(tabs);
			text.append(name);
			text.append("(");
			text.append(key);
			text.append(")");
			text.append("=");
			text.append(value);
			text.append(EOL);
			break;
		}

	}

	public static FixAttributeMap getAttributeMap(final GroupValue groupValue) {

		if (isNull(groupValue, "attrib groupValue")) {
			return null;
		}

		final SequenceValue sequenceValue = getRepeatGroup(groupValue,
				FixSequence.InstrAttrib);

		if (sequenceValue == null) {
			return null;
		}

		final GroupValue[] valueArray = sequenceValue.getValues();

		if (isNull(valueArray, "attrib valueArray")) {
			return null;
		}

		final FixAttributeMap map = new FixAttributeMap();

		for (final GroupValue value : valueArray) {

			final Integer code = getInteger(value, FixTag.InstrAttribType);

			if (isNull(code, "attrib code non optional")) {
				continue;
			}

			final FixAttributeType attrType = FixAttributeType.fromCode(code);

			if (attrType == FixAttributeType.UNKNOWN) {
				log.debug("unknown attrType code: {}", code);
				continue;
			}

			final String text = getString(value, FixTag.InstrAttribValue);

			final String attrValue = text == null ? BLANK : text;

			map.put(attrType, attrValue);

		}

		return map;

	}

	public static Character getChar(final GroupValue groupValue,
			final FixTag tag) {

		final FieldValue fieldValue = getFieldValueById(groupValue, tag);

		if (fieldValue == null) {
			return null;
		}

		final String text = fieldValue.toString();

		if (text == null || text.length() == 0) {
			return null;
		}

		return text.charAt(0);

	}

	public static FixCodeCFI getCodeCFI(final GroupValue groupValue) {

		final FieldValue fieldValue = getFieldValueById(groupValue,
				FixTag.CFICode);

		if (fieldValue == null) {
			return null;
		}

		return FixCodeCFI.fromCode(fieldValue.toString().trim());

	}

	public static DoubleParts getDoubleParts(final GroupValue groupValue,
			final FixTag tag) {

		final Object scalar = getFieldValueById(groupValue, tag);

		if (scalar instanceof DecimalValue) {
			final DecimalValue decimalValue = (DecimalValue) scalar;
			return new DoubleParts(decimalValue.mantissa, decimalValue.exponent);
		}

		if (scalar instanceof IntegerValue) {
			final IntegerValue integerValue = (IntegerValue) scalar;
			return new DoubleParts(integerValue.value, 0);
		}

		if (scalar instanceof LongValue) {
			final LongValue longValue = (LongValue) scalar;
			return new DoubleParts(longValue.value, 0);
		}

		log.debug(
				"!(scalar instanceof DecimalValue/IntegerValue/LongValue): {} ",
				scalar);

		return null;

	}

	public static FixEntryType getEntryType(final GroupValue groupValue) {

		if (groupValue == null) {
			// log.debug("value == null");
			return FixEntryType.UNKNOWN;
		}

		final Character code = getChar(groupValue, FixTag.MDEntryType);

		if (code == null) {
			// log.debug("code == null");
			return FixEntryType.UNKNOWN;
		}

		return FixEntryType.of(code);

	}

	public static FieldValue getFieldValueById(final GroupValue groupValue,
			final FixTag tag) {

		final Field field = groupValue.getGroup().getFieldById(tag.codeString);

		return field == null ? null : groupValue.getValue(field.getName());

	}

	public static Integer getInteger(final GroupValue groupValue,
			final FixTag tag) {

		final Object scalar = getFieldValueById(groupValue, tag);

		if (scalar instanceof IntegerValue) {
			final IntegerValue integerValue = (IntegerValue) scalar;
			return new Integer(integerValue.value);
		}

		log.debug("!(scalar instanceof IntegerValue): {}", scalar);

		return null;

	}

	public static Long getLong(final GroupValue groupValue, final FixTag tag) {

		final Object scalar = getFieldValueById(groupValue, tag);

		if (scalar instanceof LongValue) {
			final LongValue longValue = (LongValue) scalar;
			return new Long(longValue.value);
		}

		log.debug("!(scalar instanceof LongValue): {}", scalar);

		return null;

	}

	public static FixMessageType getMessageType(final GroupValue groupValue) {

		if (groupValue == null) {
			// log.debug("groupValue == null");
			return FixMessageType.UNKNOWN;
		}

		final String typeCode = getString(groupValue, FixTag.MsgType);

		if (typeCode == null) {
			// log.debug("typeCode == null");
			return FixMessageType.UNKNOWN;
		}

		final FixMessageType type = FixMessageType.formCode(typeCode);

		return type;

	}

	public static SequenceValue getRepeatGroup(final GroupValue groupValue,
			final FixSequence fixSequence) {

		if (isNull(groupValue, "groupValue")) {
			return null;
		}

		if (isNull(fixSequence, "fixSequence")) {
			return null;
		}

		final FieldValue fieldValue = groupValue.getValue(fixSequence.name());

		if (fieldValue instanceof SequenceValue) {
			return (SequenceValue) fieldValue;
		}

		return null;

	}

	public static Long getSequence(final GroupValue groupValue, final FixTag tag) {

		final Object scalar = getFieldValueById(groupValue, tag);

		if (scalar instanceof IntegerValue) {
			final IntegerValue integerValue = (IntegerValue) scalar;
			return new Long(unsignedInteger(integerValue.value));
		}

		if (scalar instanceof LongValue) {
			final LongValue longValue = (LongValue) scalar;
			return new Long(longValue.value);
		}

		log.debug("!(scalar instanceof IntegerValue/LongValue): {}", scalar);

		return null;

	}

	public static long getSequence(final GroupValue groupValue,
			final FixTag tag, final long defaultValue) {

		final Long sequnece = getSequence(groupValue, tag);

		return sequnece == null ? defaultValue : sequnece.longValue();

	}

	public static DateTime getSpecialDateOnlyUTC(final GroupValue groupValue,
			final FixTag tag) {

		final FieldValue fieldValue = getFieldValueById(groupValue, tag);

		if (fieldValue instanceof IntegerValue) {
			final IntegerValue integerValue = (IntegerValue) fieldValue;
			return parseSpecialDateOnlyUTC(integerValue.value);
		}

		if (fieldValue instanceof LongValue) {
			final LongValue longValue = (LongValue) fieldValue;
			return parseSpecialDateOnlyUTC(longValue.value);
		}

		// log.debug("\n ### {}", groupValue);
		// log.debug("\n ### {}", fieldValue.getClass());

		return null;

	}

	public static LocalTime getSpecialTimeOnlyUTC(final GroupValue groupValue,
			final FixTag tag) {

		final FieldValue fieldValue = getFieldValueById(groupValue, tag);

		if (fieldValue instanceof LongValue) {
			final LongValue longValue = (LongValue) fieldValue;
			return parseSpecialTimeOnlyUTC(longValue.value);
		}

		return null;

	}

	//

	/*
	 * 3 possible formats; trim to shared minimum: year-month only;
	 * http://www.fixprotocol.org/FIXimate3.0/en/FIX.5.0SP2/tag200.html
	 */

	public static DateTime getSpecialTimeStampUTC(final GroupValue groupValue,
			final FixTag tag) {

		final FieldValue fieldValue = getFieldValueById(groupValue, tag);

		if (fieldValue instanceof LongValue) {
			final LongValue longValue = (LongValue) fieldValue;
			return parseSpecialTimeStampUTC(longValue.value);
		}

		return null;

	}

	public static String getString(final GroupValue groupValue, final FixTag tag) {

		final FieldValue fieldValue = getFieldValueById(groupValue, tag);

		return fieldValue == null ? null : fieldValue.toString().trim();

	}

	public static FixUpdateAction getUdateAction(final GroupValue groupValue) {

		if (groupValue == null) {
			// log.debug("groupValue == null");
			return FixUpdateAction.UNKNOWN;
		}

		final Character code = getChar(groupValue, FixTag.MDUpdateAction);

		if (code == null) {
			// log.debug("code == null");
			return FixUpdateAction.UNKNOWN;
		}

		return FixUpdateAction.of(code);

	}

	public static DateTime getYearMonth(final GroupValue groupValue,
			final FixTag tag) {

		final FieldValue fieldValue = getFieldValueById(groupValue, tag);

		return fieldValue == null ? null : parseSpecialYearMonth(fieldValue
				.toString());

	}

	public static boolean isNull(final Object value, final String comment) {

		if (value == null) {
			log.debug("value is null", new NullPointerException(comment));
			return true;
		}

		return false;

	}

	/* TradingDate(5006)=20110418 */
	public static DateTime parseSpecialDateOnlyUTC(long value) {

		final int millis = 0;
		final int second = 0;
		final int minute = 0;
		final int hour = 0;
		final int day = (int) (value % 100);
		value /= 100;
		final int month = (int) (value % 100);
		value /= 100;
		final int year = (int) (value % 10000);

		return new DateTime(year, month, day, hour, minute, second, millis,
				DateTimeZone.UTC);
	}

	/* MDEntryTime(273)=144816227 */
	public static LocalTime parseSpecialTimeOnlyUTC(/* var */long value) {

		final int millis = (int) (value % 1000);
		value /= 1000;
		final int second = (int) (value % 100);
		value /= 100;
		final int minute = (int) (value % 100);
		value /= 100;
		final int hour = (int) (value % 100);

		return new LocalTime(hour, minute, second, millis);

	}

	/* TradSesOpenTime(342)=20110417215000000 */
	public static DateTime parseSpecialTimeStampUTC(long value) {

		final int millis = (int) (value % 1000);
		value /= 1000;
		final int second = (int) (value % 100);
		value /= 100;
		final int minute = (int) (value % 100);
		value /= 100;
		final int hour = (int) (value % 100);
		value /= 100;
		final int day = (int) (value % 100);
		value /= 100;
		final int month = (int) (value % 100);
		value /= 100;
		final int year = (int) (value % 10000);

		return new DateTime(year, month, day, hour, minute, second, millis,
				DateTimeZone.UTC);

	}

	/* MaturityMonthYear(200)=201107 */
	public static DateTime parseSpecialYearMonth(/* var */long value) {

		if (value < 1000 * 100 || 3000 * 100 < value) {
			log.debug("invalid year-month: {}", value);
			return null;
		}

		final int millis = 0;
		final int second = 0;
		final int minute = 0;
		final int hour = 0;
		final int day = 0;
		final int month = (int) (value % 100);
		value /= 100;
		final int year = (int) (value % 10000);

		return new DateTime(year, month, day, hour, minute, second, millis,
				DateTimeZone.UTC);
	}

	/* MaturityMonthYear(200)=201107 */
	public static DateTime parseSpecialYearMonth(/* var */String value) {

		try {
			if (value.length() >= PATTERN_YEAR_MONTH_LENGTH) {
				value = value.substring(0, PATTERN_YEAR_MONTH_LENGTH);
			} else {
				log.debug("invalid year-month: {}", value);
				return null;
			}
			return FORMAT_YEAR_MONTH.parseDateTime(value);
		} catch (final Exception e) {
			log.debug("", e);
			return null;
		}

	}

	public static String toFixPlainString(final GroupValue groupValue) {

		final StringBuilder text = new StringBuilder(1024);

		append(text, groupValue, TABS, Mode.PLAIN);

		return text.toString();

	}

	public static String toPrettyString(final GroupValue groupValue) {

		final StringBuilder text = new StringBuilder(1024);

		append(text, groupValue, TABS, Mode.PRETTY);

		return text.toString();

	}

	public static long unsignedInteger(final int value) {
		if (value > 0) {
			return value;
		} else {
			return 2L * Integer.MAX_VALUE + value + 2L;
		}
	}

}
