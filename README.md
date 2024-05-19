# Email Sending Java Client

This library uses integration with [MailtrapAPI](https://api-docs.mailtrap.io/)

## Usage
1. Package and include library into your Java project
2. Start by creating configuration and Mail sending client
```
Configuration configuration = new Configuration("https://send.api.mailtrap.io", "<YOUR_TOKEN>");
HttpClient httpClient = new HttpClient();
MailtrapClient mailtrapClient = new MailtrapClient(configuration, httpClient);
```
3. Create email entity to define your email data
```
var sender = new Sender("<SENDER_NAME>", "<SENDER_EMAIL>");
var recipient = new Recipient("<RECIPIENT_NAME", "<RECIPIENT_EMAIL>");
var recipients = Arrays.asList(recipient);

var file = new File("<PATH_TO_FILE>");
Attachment attachment = Attachment.AttachmentBuilder.newInstance("<FILE_NAME>", file)
        .build();
var attachments = Arrays.asList(attachment);

var email = Email.EmailBuilder.newInstance(sender, recipients, <SUBJECT>, <TEXT>)
        .withHtml("<p>SAMPLE HTML <strong>1234</strong>.</p>")
        .withAttachments(attachments)
        .build();
```
4. Pass Email entity to Email client send method
```
var response = mailtrapClient.send(email);
```

## Build application into JAR
```
run ./gradlew clean build
```

## Run Unit tests
```
run ./gradlew test
```
