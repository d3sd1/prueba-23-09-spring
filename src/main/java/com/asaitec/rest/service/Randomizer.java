package com.asaitec.rest.service;

import com.asaitec.rest.model.OperatorStatus;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Randomizer {
    public String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
    public OperatorStatus randomOperatorStatus() {
        return OperatorStatus.values()[new Random().nextInt(OperatorStatus.values().length)];
    }
}
