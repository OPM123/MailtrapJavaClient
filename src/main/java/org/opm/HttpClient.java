package org.opm;

import com.squareup.okhttp.*;

import java.io.IOException;

/**
 * Allows to send Http requests
 */
public class HttpClient {

    public Response send(Configuration configuration, Email email) throws IOException {
        var httpClient = new OkHttpClient();
        var mediaType = MediaType.parse(HttpConstants.APPLICATION_JSON_HEADER);
        var body = ApiObjectMapper.INSTANCE.writeValueAsString(email);
        var requestBody = RequestBody.create(mediaType, body);
        var request = new Request.Builder()
                .url(buildSendUri(configuration))
                .addHeader(HttpConstants.CONTENT_TYPE_HEADER, HttpConstants.APPLICATION_JSON_HEADER)
                .addHeader(HttpConstants.AUTHORIZATION, buildBearerHeader(configuration.token()))
                .method(HttpConstants.POST, requestBody)
                .build();

       return httpClient.newCall(request).execute();
    }

    private static String buildSendUri(Configuration configuration) {
        return configuration.uri() + "/api/send";
    }

    private static String buildBearerHeader(String token) {
        return HttpConstants.BEARER + " " + token;
    }
}
