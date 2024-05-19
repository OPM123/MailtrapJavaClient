package org.opm;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test suite for {@link FileEncoder}
 */
class FileEncoderShould {

    @Test
    void encodeFileToBase64() {
        // setup
        var file = new File(getClass().getClassLoader().getResource("index.html").getFile());

        // execute
        var encodedFile = FileEncoder.encodeFileToBase64(file);

        // verify
        assertEquals(encodedFile, "PCFET0NUWVBFIGh0bWw+DQo8aHRtbD4NCjxib2R5Pg0KDQo8aDE+TXkgRmlyc3QgSGVhZGluZzwvaDE+DQo8cD5NeSBmaXJzdCBwYXJhZ3JhcGguPC9wPg0KDQo8L2JvZHk+DQo8L2h0bWw+");
    }
}