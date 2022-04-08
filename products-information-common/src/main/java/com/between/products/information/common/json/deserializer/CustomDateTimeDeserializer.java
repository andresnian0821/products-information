package com.between.products.information.common.json.deserializer;

import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import org.joda.time.DateTime;

public class CustomDateTimeDeserializer extends DateTimeDeserializer {

    private static final long serialVersionUID = -8780519716480687825L;

    public CustomDateTimeDeserializer() {
        super(DateTime.class, FormatConfig.DEFAULT_DATETIME_PARSER);
    }

    public CustomDateTimeDeserializer(Class<?> cls, JacksonJodaDateFormat format) {
        super(cls, format);
    }

}
