package book.collections.homework.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DateAdapterTest {

  @Test
  public void shouldVerifyTimestampAdapter() throws Exception {
    //given
    String date1 = "2010";
    String date2 = "2015-05-10";
    String date3 = null;
    DateAdapter dateAdapter = new DateAdapter();

    //when
    long yyyy = dateAdapter.stringToTimestampAdapter(date1);
    long yyyyMMdd = dateAdapter.stringToTimestampAdapter(date2);
    long noDate = dateAdapter.stringToTimestampAdapter(date3);

    //then1262304000
    assertEquals(1262300400000L,yyyy);
    assertEquals(1431208800000L,yyyyMMdd);
    assertEquals(0,noDate);

  }
}