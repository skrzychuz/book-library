package book.collections.homework.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

@Service
public class DateToTimeStamp {

  public Long stringToTimestampAdapter(String stringDate) throws ParseException {
    if (Pattern.matches("\\d{4}", stringDate)) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
      dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
      Date parsedDate = dateFormat.parse(stringDate);
      return parsedDate.getTime();
    } else {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date parsedDate = dateFormat.parse(stringDate);
      return parsedDate.getTime();
    }
  }
}
