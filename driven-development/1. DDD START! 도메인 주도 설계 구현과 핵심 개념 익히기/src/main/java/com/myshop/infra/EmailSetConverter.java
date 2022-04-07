package com.myshop.infra;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EmailSetConverter implements AttributeConverter<EmailSet, String> {

    @Override
    public String convertToDatabaseColumn(EmailSet emailSet) {
        return emailSet == null
                ? null
                : emailSet.getEmails().stream()
                .map(Email::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public EmailSet convertToEntityAttribute(String dbData) {
        if (dbData == null) { return null; }

        String[] emails = dbData.split(",");
        Set<Email> emailSet = Arrays.stream(emails)
                .map(Email::new)
                .collect(Collectors.toSet());

        return new EmailSet(emailSet);
    }
}
