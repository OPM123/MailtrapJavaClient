package org.opm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.File;
import java.util.Objects;

/**
 * Entity for holding information needed for and email attachment
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Email.EmailBuilder.class)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Attachment {

    private final String filename;
    private final File attachment;
    private final String type;
    private final String contentId;
    private final String disposition;

    public Attachment(AttachmentBuilder attachmentBuilder) {
        this.filename = attachmentBuilder.filename;
        this.attachment = attachmentBuilder.attachment;
        this.type = attachmentBuilder.type;
        this.contentId = attachmentBuilder.contentId;
        this.disposition = attachmentBuilder.disposition;
    }

    @JsonProperty(value = "filename", required = true)
    public String getFilename() {
        return filename;
    }

    @JsonProperty(value = "content", required = true)
    public String getAttachment() {
        return FileEncoder.encodeFileToBase64(attachment);
    }

    @JsonProperty(value = "type")
    public String getType() {
        return type;
    }

    @JsonProperty(value = "content_id")
    public String getContentId() {
        return contentId;
    }

    @JsonProperty(value = "disposition")
    public String getDisposition() {
        return disposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return Objects.equals(filename, that.filename) && Objects.equals(attachment, that.attachment) && Objects.equals(type, that.type) && Objects.equals(contentId, that.contentId) && disposition == that.disposition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename, attachment, type, contentId, disposition);
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "filename='" + filename + '\'' +
                ", attachment=" + attachment +
                ", type='" + type + '\'' +
                ", contentId='" + contentId + '\'' +
                ", disposition='" + disposition + '\'' +
                '}';
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AttachmentBuilder {

        private final String filename;
        private final File attachment;
        private String type;
        private String contentId;
        private String disposition;

        public static AttachmentBuilder newInstance(String filename, File attachment) {
            return new AttachmentBuilder(filename, attachment);
        }

        private AttachmentBuilder(String filename, File attachment) {
            this.filename = filename;
            this.attachment = attachment;
        }

        @JsonProperty("type")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public AttachmentBuilder withType(String type) {
            this.type = type;
            return this;
        }

        @JsonProperty("content_id")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public AttachmentBuilder withContentId(String contentId) {
            this.contentId = contentId;
            return this;
        }

        @JsonProperty("disposition")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public AttachmentBuilder withDisposition(Disposition disposition) {
            this.disposition = disposition.toString();
            return this;
        }

        public Attachment build() {
            return new Attachment(this);
        }
    }

}
