package org.opm;

import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Email client
 */
public interface EmailClient {

    Response send(Email email) throws IOException;
}
