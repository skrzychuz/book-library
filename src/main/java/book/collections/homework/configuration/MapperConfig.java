//package book.collections.homework.configuration;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//@Configuration
//public class MapperConfig {
//
//  @Bean
//  @Primary
//  public ObjectMapper getMapper() {
//    ObjectMapper mapper = new ObjectMapper();
//    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    return mapper;
//
//  }
//}
