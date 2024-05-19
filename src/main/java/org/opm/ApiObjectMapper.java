package org.opm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Provides functionality for reading and writing JSON
 */
class ApiObjectMapper extends ObjectMapper {

    static final ApiObjectMapper INSTANCE = new ApiObjectMapper();

    private ApiObjectMapper() {
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
