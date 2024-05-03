package ru.merkel.springemployeeaccounting.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.merkel.springemployeeaccounting.constants.ConstantsForTests.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void setUp() {
        departmentService = new DepartmentServiceImpl(employeeServiceMock);
    }

    @Test
    void findAll() {
        when(employeeServiceMock.findAll()).thenReturn(EMPLOYEES);
        Map<Integer, List<Employee>> actual = departmentService.findAll();
        assertEquals(GROUPED_EMPLOYEES, actual);
    }

    @Test
    void shouldReturnSumSalaryOfDepartment() {
        when(employeeServiceMock.findAll()).thenReturn(EMPLOYEES);
        assertEquals(DEPARTMENT1_SALARY, departmentService.sumSalaryOfDepartment(DEPARTMENT1));
    }

    @Test
    void findByMaxSalaryOfDepartment() {
        when(employeeServiceMock.findAll()).thenReturn(EMPLOYEES);
        assertEquals(EMPLOYEE_MAX_SALARY, departmentService.findByMaxSalaryOfDepartment(DEPARTMENT4));
    }

    @Test
    void findByMinSalaryOfDepartment() {
        when(employeeServiceMock.findAll()).thenReturn(EMPLOYEES);
        assertEquals(EMPLOYEE_MIN_SALARY, departmentService.findByMinSalaryOfDepartment(DEPARTMENT1));
    }

    @Test
    void findByDepartment() {
        when(employeeServiceMock.findAll()).thenReturn(EMPLOYEES);
        assertEquals(EMPLOYEES_DEPARTMENT_1, departmentService.findByDepartment(DEPARTMENT1));
    }
}