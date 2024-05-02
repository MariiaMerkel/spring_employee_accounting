package ru.merkel.springemployeeaccounting.services;

import net.datafaker.Faker;

public class EmployeeValueGenerator {
    private final String firstName;
    private final String lastName;
    private Integer salary;
    private Integer department;
    private Faker faker = new Faker();

    public EmployeeValueGenerator() {
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.salary = faker.number().numberBetween(100000, 200000);
        this.department = faker.number().numberBetween(1, 10);
    }
}
