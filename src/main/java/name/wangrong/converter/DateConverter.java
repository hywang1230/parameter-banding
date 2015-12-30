package name.wangrong.converter;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by wangrong on 15/12/30.
 */
public class DateConverter implements Converter<String, Date> {
    private static final String DEAFULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public Date convert(String source) {
        try {
            return DateUtils.parseDate(source, DEAFULT_FORMAT);
        } catch (ParseException e) {
            e.printStackTrace();

            throw new IllegalArgumentException("Could not parse date: " + e.getMessage(), e);
        }
    }
}
