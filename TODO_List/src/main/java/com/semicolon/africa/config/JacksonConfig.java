//package com.semicolon.africa.config;
//
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.format.DateTimeFormatter;
//
//@Configuration
//public class JacksonConfig {
//
//    private static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
//    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
//
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//        return builder -> builder
//                .simpleDateFormat(DATETIME_FORMAT)
//                .serializers(new LocalDateTimeSerializer(FORMATTER))
//                .deserializers(new LocalDateTimeDeserializer(FORMATTER));
//    }
//}
//
