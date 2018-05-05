package book.collections.homework.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class DateAdapter {

  public long stringToTimestampAdapter(String stringDate) {
    SimpleDateFormat dateFormat;
    if (stringDate != null) {
      if (Pattern.matches("\\d{4}", stringDate)) {
        dateFormat = new SimpleDateFormat("yyyy");
      } else {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      }
      Date parsedDate = null;
      try {
        parsedDate = dateFormat.parse(stringDate);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      return parsedDate.getTime();

    }
    return 0;
  }
}
