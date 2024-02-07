package ru.job4j.assertj.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte byteVar = 123;
        short shortVar = 12345;
        int intVar = 1234567;
        long longVar = 123456789L;
        float floatVar = 123.45F;
        double doubleVar = 123.4567;
        char charVar = 'A';
        boolean booleanVar = true;

        LOG.debug("byteVar: {}, shortVar: {}, intVar: {}, longVar: {}, floatVar: {}, doubleVar: {}, charVar: {}, booleanVar: {}",
                byteVar, shortVar, intVar, longVar, floatVar, doubleVar, charVar, booleanVar);
    }
}