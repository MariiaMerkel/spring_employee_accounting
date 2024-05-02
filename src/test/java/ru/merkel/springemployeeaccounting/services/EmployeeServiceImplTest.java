package ru.merkel.springemployeeaccounting.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeAlreadyAddedException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeInvalidateException;
import ru.merkel.springemployeeaccounting.excaptions.EmployeeNotFoundException;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.merkel.springemployeeaccounting.constants.ConstantsForTests.*;

class EmployeeServiceImplTest {
    private EmployeeService employeeService;
    private Map<String, Employee> employees = new HashMap<>();
    private Collection<Employee> employeeCollection;

    @BeforeEach
    @MethodSource("provideParamsForAdd")
    public void setUp() {
        employeeService = new EmployeeServiceImpl();
        employeeService.add(FIRST_NAME1, LAST_NAME1, SALARY1, DEPARTMENT1);
        employeeService.add(FIRST_NAME2, LAST_NAME2, SALARY2, DEPARTMENT2);
        employeeService.add(FIRST_NAME3, LAST_NAME3, SALARY3, DEPARTMENT3);

        Employee employee1 = new Employee(FIRST_NAME1, LAST_NAME1, SALARY1, DEPARTMENT1);
        Employee employee2 = new Employee(FIRST_NAME2, LAST_NAME2, SALARY2, DEPARTMENT2);
        Employee employee3 = new Employee(FIRST_NAME3, LAST_NAME3, SALARY3, DEPARTMENT3);

        employees.put(employee1.toString(), employee1);
        employees.put(employee2.toString(), employee2);
        employees.put(employee3.toString(), employee3);
        employeeCollection = Collections.unmodifiableCollection(employees.values());
    }

    @Test
    void shouldReturnAddedEmployee() {
        Employee actual = employeeService.add(FIRST_NAME4, LAST_NAME4, SALARY4, DEPARTMENT4);
        Employee expected = new Employee(FIRST_NAME4, LAST_NAME4, SALARY4, DEPARTMENT4);
        assertEquals(expected, actual);
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
    void shouldReturnDeletedEmployee() {
        Employee actual = employeeService.remove(NOT_FORMATTED_FIRST_NAME1, NOT_FORMATTED_LAST_NAME1);
        Employee expected = new Employee(FIRST_NAME1, LAST_NAME1, SALARY1, DEPARTMENT1);
        assertEquals(expected, actual);
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
        Employee expected = new Employee(FIRST_NAME1, LAST_NAME1, SALARY1, DEPARTMENT1);
        Employee actual = employeeService.find(NOT_FORMATTED_FIRST_NAME1, NOT_FORMATTED_LAST_NAME1);
        assertEquals(expected, actual);
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
        assertEquals(employeeCollection, actual);
    }
}