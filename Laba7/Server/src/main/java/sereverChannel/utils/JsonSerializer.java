package sereverChannel.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static byte[] serialize(Object object) throws Exception {
        return mapper.writeValueAsBytes(object);
    }
}
