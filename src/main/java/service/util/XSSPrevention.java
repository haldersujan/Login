package service.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class XSSPrevention  implements ContainerRequestFilter {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
        .getLogger(ReplaceCharacterFilter.class);

    /** The Constant ESCAPE_CHARACTER_MAP. */
    private static final Map<String, String> REPLACE_CHARACTER_MAP =
        new HashMap<String, String>();

    static {
        // Replacing the following characters with a space to prevent XSS
        // < > ; / ( )
        REPLACE_CHARACTER_MAP.put("<", " ");
        REPLACE_CHARACTER_MAP.put("\\u003C", " ");
        REPLACE_CHARACTER_MAP.put("&lt;", " ");

        REPLACE_CHARACTER_MAP.put(">", " ");
        REPLACE_CHARACTER_MAP.put("\\u003E", " ");
        REPLACE_CHARACTER_MAP.put("&gt;", " ");

        REPLACE_CHARACTER_MAP.put("(", " ");
        REPLACE_CHARACTER_MAP.put("\\u0028", " ");
        REPLACE_CHARACTER_MAP.put("&#40;", " ");

        REPLACE_CHARACTER_MAP.put(")", " ");
        REPLACE_CHARACTER_MAP.put("\\u0029", " ");
        REPLACE_CHARACTER_MAP.put("&#41;", " ");

        REPLACE_CHARACTER_MAP.put(";", " ");
        REPLACE_CHARACTER_MAP.put("\\u003B", " ");
        REPLACE_CHARACTER_MAP.put("&#59;", " ");

        REPLACE_CHARACTER_MAP.put("/", " ");
        REPLACE_CHARACTER_MAP.put("\\u002F", " ");
        REPLACE_CHARACTER_MAP.put("&#47;", " ");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerRequest filter(ContainerRequest request) {

        LOGGER.info("Entering container request filter");

        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(request.getEntityInputStream(), writer, UTF8);
            LOGGER.trace("Data sent in : {}", writer.toString());
            String sanitisedString = sanitiseRequestData(writer);
            request.setEntityInputStream(new ByteArrayInputStream(
                sanitisedString.getBytes(UTF8)));
        } catch (IOException e) {
            LOGGER
                .error("Cannot read stream data on the container request object");
        }
        return request;
    }

    /**
     * Sanitise request data.
     * 
     * Set to default scope to allow for unit testing of this functionality
     * 
     * @param writer
     *            object to be sanitised
     * @return sanitised string
     */
    String sanitiseRequestData(StringWriter writer) {
        String sanitisedData = writer.toString();
        if (!StringUtils.isEmpty(sanitisedData)) {
            LOGGER.trace("Sanitising request data: {}", sanitisedData);
            for (String escapeCharacter : REPLACE_CHARACTER_MAP.keySet()) {
                sanitisedData =
                    sanitisedData.replace(escapeCharacter,
                        REPLACE_CHARACTER_MAP.get(escapeCharacter));
            }
            LOGGER.trace("Sanitised data: {}", sanitisedData);
        }
        return sanitisedData;
    }

}
