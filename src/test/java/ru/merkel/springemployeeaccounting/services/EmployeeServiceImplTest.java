package ru.merkel.springemployeeaccounting.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeAlreadyAddedException;
import ru.merkel.springemployeeaccounting.models.Employee;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private EmployeeService employeeService;
    private Map<String, Employee> employees = new HashMap<>();
    private static Faker faker = new Faker();

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("provideParamsForAdd")
    void shouldReturnSuccessfulAddition(String firstName, String lastName, Integer salary, Integer department, String result) {
        assertEquals(employeeService.add(firstName, lastName, salary, department), result);
    }

    public static Stream<Arguments> provideParamsForAdd() {
        String firstName;
        String lastName;
        Integer salary;
        Integer department;
        List<Arguments> arguments = new ArrayList<>();
        Stream<Arguments> stream = null;

        for (int i = 0; i < 10; i++) {

            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            salary = faker.number().numberBetween(100000, 200000);
            department = faker.number().numberBetween(1, 10);
            arguments.add(Arguments.of(firstName, lastName, salary, department, String.format("Добавлен новый сотрудник: %s.", firstName + ' ' + lastName)));
        }
        return Stream.of(
                arguments.get(0),
                arguments.get(1),
                arguments.get(2),
                arguments.get(3),
                arguments.get(4),
                arguments.get(5),
                arguments.get(6),
                arguments.get(7),
                arguments.get(8),
                arguments.get(9)
                );
    }
    @ParameterizedTest
    @MethodSource("provideParamsForAddNegative")
    void shouldReturnUnsuccessfulAddition(String firstName, String lastName, Integer salary, Integer department, String expected, String expectedErr) {
        assertEquals(employeeService.add(firstName, lastName, salary, department), expected);
        EmployeeAlreadyAddedException thrown = Assertions
                .assertThrows(EmployeeAlreadyAddedException.class, () -> {
                    employeeService.add(firstName, lastName, salary, department);
                }, expectedErr);

        Assertions.assertEquals(expected, thrown.getMessage());
    }

    public static Stream<Arguments> provideParamsForAddNegative() {
        String firstName;
        String lastName;
        Integer salary;
        Integer department;
        List<Arguments> arguments = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            salary = faker.number().numberBetween(100000, 200000);
            department = faker.number().numberBetween(1, 10);
            arguments.add(Arguments.of(firstName, lastName, salary, department, String.format("Добавлен новый сотрудник: %s.", firstName + ' ' + lastName), String.format("Сотрудник с именем %s уже есть в списке.", firstName + ' ' + lastName)));
            //arguments.add(Arguments.of(firstName, lastName, salary, department, String.format("Сотрудник с именем %s уже есть в списке.", firstName + ' ' + lastName)));
        }
        return Stream.of(
                arguments.get(0),
                arguments.get(1),
                arguments.get(2),
                arguments.get(3),
                arguments.get(4)
//                arguments.get(5),
//                arguments.get(6),
//                arguments.get(7),
//                arguments.get(8),
//                arguments.get(9)
        );
    }

    @Test
    void remove() {
    }

    @Test
    void find() {
    }

    @Test
    void validateName() {
    }

    @Test
    void findAll() {
    }
}