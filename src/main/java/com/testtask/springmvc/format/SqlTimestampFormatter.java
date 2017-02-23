package com.testtask.springmvc.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SqlTimestampFormatter implements Formatter<Timestamp> {

    @Autowired
    private MessageSource messageSource;

    @Override
    public Timestamp parse(final String text, final Locale locale) throws ParseException {
        final SimpleDateFormat timestampFormat = createTimestampFormat(locale);
        return new Timestamp(timestampFormat.parse(text).getTime());
    }

    @Override
    public String print(final Timestamp timestamp, final Locale locale) {
        final SimpleDateFormat timestampFormat = createTimestampFormat(locale);
        return timestampFormat.format(timestamp);
    }

    private SimpleDateFormat createTimestampFormat(final Locale locale) {
        final String format = this.messageSource.getMessage("timestamp.format", null, locale);
        final SimpleDateFormat timestampFormat = new SimpleDateFormat(format);
        timestampFormat.setLenient(false);
        return timestampFormat;
    }
}
