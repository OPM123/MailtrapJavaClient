package org.opm;

/**
 * Reads and load properties file
 *
 * @param uri uri of emails api
 * @param token api token
 */
public record Configuration (String uri, String token) {
}
