<!-- 
	template to convert form fix to protocol buffers
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

	<xsl:output method="text" encoding="UTF-8" />

	<xsl:param name="java_package" />
	<xsl:param name="java_classname" />

	<xsl:template match="/">
	
		package <xsl:value-of select="$java_package"/>;

		public final class <xsl:value-of select="$java_classname"/> {

		////////////////////////////

		/**
		 * known field tags
		 */
	    public static enum Tag {
	    <xsl:for-each select="/fix/fields/field">
		/** 
		 * tag name = <xsl:value-of select="@name" />;
		 * tag type = <xsl:value-of select="@type" />;
		 * tag number = <xsl:value-of select="@number" />;
		 * <![CDATA[<a href="]]>http://fixprotocol.org/FIXimate3.0/en/FIX.5.0SP2/tag<xsl:value-of select="@number" />.html<![CDATA[">]]>
		 * tag definition
		 * <![CDATA[</a>]]>
		 */ 
	    <xsl:value-of select="@name" />(<xsl:value-of select="@number" />) , // 
	    
	    </xsl:for-each>
	    
	    UNKNOWN(0); //
	    
	    ;
	    
	    public final int tag;
	    
	    public final String code;
	    
	    private Tag(final int tag){
	    	this.tag = tag;
	    	this.code = Integer.toString(tag);
	    }
	    
	    private final static Tag[] ENUM_VALS = values();
	    
	    public static Tag from(final int tag){
	    	for(Tag known : ENUM_VALS){
	    		if(known.tag == tag) return known;
	    	}
	    	return UNKNOWN;
	    }
	    		 
	    public static Tag from(final String code){
	    	for(Tag known : ENUM_VALS){
	    		if(known.code.equals(code)) return known;
	    	}
	    	return UNKNOWN;
	    }
	    		 
		}

		////////////////////////////

		/**
		 * known field values
		 */
		public static class Value {
	    <xsl:for-each select="/fix/fields/field[count(*)>0]">
   		<xsl:variable name="name" select="@name" />
   		<xsl:variable name="type" select="@type" />
		/**
		 * tag name = <xsl:value-of select="@name" />;
		 * tag type = <xsl:value-of select="@type" />;
		 * tag number = <xsl:value-of select="@number" />;
		 * <![CDATA[<a href="]]>http://fixprotocol.org/FIXimate3.0/en/FIX.5.0SP2/tag<xsl:value-of select="@number" />.html<![CDATA[">]]>
		 * tag definition
		 * <![CDATA[</a>]]>
		 */
	    public static enum <xsl:value-of select="$name" /> { 
	    <xsl:for-each select="/fix/fields/field[@name=$name]/value">
	    <xsl:value-of select="@description" />("<xsl:value-of select="@enum" />"), //
	    </xsl:for-each>
	    ;
	    
	    public final String code;
	    <xsl:choose>
        <xsl:when test="$type = 'CHAR'">
	    public final char codeChar;
        </xsl:when>
        <xsl:when test="$type = 'INT'">
	    public final int codeInt;
        </xsl:when>
		</xsl:choose>
	    
	    private <xsl:value-of select="$name" />(final String code){
	    	this.code = code;
	    <xsl:choose>
        <xsl:when test="$type = 'CHAR'">
		    this.codeChar = code.charAt(0);
        </xsl:when>
        <xsl:when test="$type = 'INT'">
	    	this.codeInt = Integer.parseInt(code);
        </xsl:when>
		</xsl:choose>
	    }
	    }
	    </xsl:for-each>
		}
		}
		
	</xsl:template>

</xsl:stylesheet>
