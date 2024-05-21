package ru.merkel.springemployeeaccounting.services;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeAlreadyAddedException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeInvalidateException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeNotFoundException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeStorageIsFullException;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.merkel.springemployeeaccounting.constants.ConstantsForTests.*;

class EmployeeServiceImplTest {
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    @MethodSource("provideParamsForAdd")
    public void setUp() {
        employeeService = new EmployeeServiceImpl();
        employeeService.add(FIRST_NAME1, LAST_NAME1, SALARY1, DEPARTMENT1);
        employeeService.add(FIRST_NAME2, LAST_NAME2, SALARY2, DEPARTMENT2);
        employeeService.add(FIRST_NAME3, LAST_NAME3, SALARY3, DEPARTMENT3);
    }

    @Test
    void shouldReturnAddedEmployee() {
        Employee actual = employeeService.add(FIRST_NAME4, LAST_NAME4, SALARY4, DEPARTMENT4);
        assertEquals(EMPLOYEE_4, actual);
    }

    @Test
    void shouldReturnInvalidateExceptionByAdding() {
        assertThrows(EmployeeInvalidateException.class, () -> employeeService.add(NOT_FORMATTED_FIRST_NAME2, NOT_FORMATTED_LAST_NAME2, SALARY2, DEPARTMENT2));
    }

    @Test
    void shouldReturnAlreadyAddedExceptionByAdding() {
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add(FIRST_NAME3, LAST_NAME3, SALARY3, DEPARTMENT3));
    }

    @Test
    void shouldReturnStorageIsFullExceptionByAdding() {
        employeeService.add(NOT_FORMATTED_FIRST_NAME1, NOT_FORMATTED_LAST_NAME1, SALARY3, DEPARTMENT3);
        employeeService.add(NOT_FORMATTED_FIRST_NAME4, NOT_FORMATTED_LAST_NAME4, SALARY4, DEPARTMENT3);
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.add(FIRST_NAME3, LAST_NAME3, SALARY3, DEPARTMENT3));
    }

    @Test
    void shouldReturnDeletedEmployee() {
        Employee actual = employeeService.remove(FIRST_NAME1, LAST_NAME1);
        assertEquals(EMPLOYEE_1, actual);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.remove(FIRST_NAME1, LAST_NAME1));
    }

    @Test
    void shouldReturnInvalidateExceptionByRemoving() {
        assertThrows(EmployeeInvalidateException.class, () -> employeeService.remove(NOT_FORMATTED_FIRST_NAME2, NOT_FORMATTED_LAST_NAME2));
    }

    @Test
    void shouldReturnNotFoundExceptionByRemoving() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.remove(FIRST_NAME4, LAST_NAME4));
    }

    @Test
    void shouldReturnFoundEmployee() {
        Employee actual = employeeService.find(FIRST_NAME2, LAST_NAME2);
        assertEquals(EMPLOYEE_2, actual);
    }

    @Test
    void shouldReturnInvalidateExceptionByFound() {
        assertThrows(EmployeeInvalidateException.class, () -> employeeService.find(NOT_FORMATTED_FIRST_NAME2, NOT_FORMATTED_LAST_NAME2));
    }

    @Test
    void shouldReturnNotFoundExceptionByFound() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.find(FIRST_NAME4, LAST_NAME4));
    }

    @Test
    void findAll() {
        Collection<Employee> actual = employeeService.findAll();
        assertThat(actual, Matchers.containsInAnyOrder(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3));
    }
}