package org.opm;

import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Email sending client based on Mailtrap API
 */
public class MailtrapClient implements EmailClient {

    private final Configuration configuration;
    private final HttpClient httpClient;

    public MailtrapClient(Configuration configuration, HttpClient httpClient) {
        this.configuration = configuration;
        this.httpClient = httpClient;
    }

    @Override
    public Response send(Email email) throws IOException {
        return httpClient.send(configuration, email);
    }
}
