package book.collections.homework.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

@Service
public class DateAdapter {

  public long stringToTimestampAdapter(String stringDate) throws ParseException {
    return stringDate != null ? getDateFormat(stringDate).parse(stringDate).getTime() : 0;
  }

  private SimpleDateFormat getDateFormat(String date) {
    return Pattern.matches("\\d{4}", date) ? new SimpleDateFormat("yyyy")
        : new SimpleDateFormat("yyyy-MM-dd");
  }
}
