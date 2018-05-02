package book.collections.homework.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

@Service
public class DateAdapter {

  public Long stringToTimestampAdapter(String stringDate) {
    if (Pattern.matches("\\d{4}", stringDate)) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
      dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
      Date parsedDate = null;
      try {
        parsedDate = dateFormat.parse(stringDate);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      return parsedDate.getTime();
    } else {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date parsedDate = null;
      try {
        parsedDate = dateFormat.parse(stringDate);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      return parsedDate.getTime();
    }
  }
}
