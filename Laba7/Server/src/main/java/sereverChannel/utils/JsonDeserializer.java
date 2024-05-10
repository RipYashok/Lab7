package sereverChannel.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDeserializer {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T deserialize(byte[] json, Class<T> type) throws Exception {
        return mapper.readValue(json, type);
    }
}
