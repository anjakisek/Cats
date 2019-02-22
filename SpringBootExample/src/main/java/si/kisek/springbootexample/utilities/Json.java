package si.kisek.springbootexample.utilities;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {

    @FunctionalInterface
    public interface MapperSetup {
        void run(ObjectMapper om);
    }

    public static String prepareLiteral(String s) {
        return s.replace('`', '"');
    }

    public static String toJson(Object value){
        return Json.toJson(value, null);
    }

    public static String toJson(Object value, MapperSetup setup){

        if (value == null) { return ""; }

        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.PUBLIC_ONLY);
        om.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        om.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
        om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        if (setup != null) {
            setup.run(om);
        }

        try {
            return om.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static <T> T fromJson(String value, Class<T> type) {
        return Json.fromJson(value, type, null);
    }



    public static <T> T fromJson(String value, Class<T> type, MapperSetup setup) {

        try {
            return fromJsonUnsafe(value, type, setup);
        } catch (IOException e) {
            return null;
        }
    }


    public static <T> T fromJsonUnsafe(String value, Class<T> type, MapperSetup setup) throws IOException {

        if ((value == null) || (value.isEmpty()) || type == null ) { return null; }

        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        if (setup != null) {
            setup.run(om);
        }

        return om.readValue(value, type);
    }

    public static <T> T fromJsonUnsafe(String value, Class<T> type) throws IOException {
        return fromJsonUnsafe(value, type, null);
    }



    public static JsonNode toJsonTree(String payload) throws IOException {
        ObjectMapper om = new ObjectMapper();
        final ObjectReader reader = om.reader();
        return reader.readTree(payload);
    }
}