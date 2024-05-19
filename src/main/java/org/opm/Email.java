package org.opm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;
import java.util.Objects;

/**
 * Entity for holding information needed for sending Email
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Email.EmailBuilder.class)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Email {

    private final Sender from;
    private final List<Recipient> recipients;
    private final String subject;
    private final String text;
    private final String html;
    private final List<Attachment> attachments;

    public Email(EmailBuilder emailBuilder) {
        this.from = emailBuilder.from;
        this.recipients = emailBuilder.recipients;
        this.subject = emailBuilder.subject;
        this.text = emailBuilder.text;
        this.html = emailBuilder.html;
        this.attachments = emailBuilder.attachments;
    }

    @JsonProperty(value = "from", required = true)
    public Sender getFrom() {
        return from;
    }

    @JsonProperty(value = "to", required = true)
    public List<Recipient> getRecipients() {
        return recipients;
    }

    @JsonProperty(value = "subject", required = true)
    public String getSubject() {
        return subject;
    }

    @JsonProperty(value = "text")
    public String getText() {
        return text;
    }

    @JsonProperty("html")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getHtml() {
        return html;
    }

    @JsonProperty("attachments")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Attachment> getAttachments() {
        return attachments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(from, email.from) && Objects.equals(recipients, email.recipients) && Objects.equals(subject, email.subject) && Objects.equals(text, email.text) && Objects.equals(html, email.html) && Objects.equals(attachments, email.attachments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, recipients, subject, text, html, attachments);
    }

    @Override
    public String toString() {
        return "Email{" +
                "from=" + from +
                ", recipients=" + recipients +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", html='" + html + '\'' +
                ", attachments=" + attachments +
                '}';
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EmailBuilder {

        private final Sender from;
        private final List<Recipient> recipients;
        private final String subject;
        private String text;
        private String html;
        private List<Attachment> attachments;

        public static EmailBuilder newInstance(Sender from, List<Recipient> recipients, String subject, String text) {
            return new EmailBuilder(from, recipients, subject, text);
        }

        private EmailBuilder(Sender from, List<Recipient> recipients, String subject, String text) {
            this.from = from;
            this.recipients = recipients;
            this.subject = subject;
            this.text = text;
        }

        @JsonProperty("html")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public EmailBuilder withHtml(String html) {
            this.html = html;
            if (!html.isEmpty()) {
                this.text = null;
            }
            return this;
        }

        @JsonProperty("attachments")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public EmailBuilder withAttachments(List<Attachment> attachments) {
            this.attachments = attachments;
            return this;
        }

        public Email build() {
            return new Email(this);
        }
    }
}
