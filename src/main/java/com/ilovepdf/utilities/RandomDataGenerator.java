package com.ilovepdf.utilities;

import com.github.javafaker.Faker;

public final class RandomDataGenerator {

    private static final Faker FAKER = new Faker();

    private RandomDataGenerator() {}

    public static String randomFirstName() { return FAKER.name().firstName(); }
    public static String randomLastName() { return FAKER.name().lastName(); }
    public static String randomFullName() { return FAKER.name().fullName(); }
    public static String randomEmail() { return FAKER.internet().emailAddress(); }
    public static String randomPassword() { return FAKER.internet().password(8, 16, true, true, true); }
    public static String randomPhoneNumber() { return FAKER.phoneNumber().cellPhone(); }
    public static String randomAddress() { return FAKER.address().fullAddress(); }
    public static String randomCity() { return FAKER.address().city(); }
    public static String randomCountry() { return FAKER.address().country(); }
    public static String randomCompany() { return FAKER.company().name(); }
    public static int randomInt(int min, int max) { return FAKER.number().numberBetween(min, max); }
    public static String randomString(int len) { return FAKER.lorem().characters(len); }
    public static String randomUUID() { return java.util.UUID.randomUUID().toString(); }
}
