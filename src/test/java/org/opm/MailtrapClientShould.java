package org.opm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test suite for {@link MailtrapClient}
 */
@ExtendWith(MockitoExtension.class)
class MailtrapClientShould {

    private HttpClient httpClient;

    private final Configuration configuration = new Configuration("URI", "TOKEN");

    private MailtrapClient mailtrapClient;

    @BeforeEach
    void setup() {
        httpClient = mock(HttpClient.class);
        mailtrapClient = new MailtrapClient(configuration, httpClient);
    }

    @Test
    void sendEmail() throws IOException {
        // setup
        var email = mock(Email.class);

        // execute
        mailtrapClient.send(email);

        // verify
        verify(httpClient).send(configuration, email);
    }
}