package ru.merkel.springemployeeaccounting.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.merkel.springemployeeaccounting.models.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.merkel.springemployeeaccounting.constants.ConstantsForTests.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void sumSalaryOfDepartment() {
        when(employeeServiceMock.add(FIRST_NAME1, LAST_NAME1, SALARY1, DEPARTMENT1)).thenReturn(new Employee(FIRST_NAME1, LAST_NAME1, SALARY1, DEPARTMENT1));
        when(employeeServiceMock.find(FIRST_NAME1, LAST_NAME1)).thenReturn(new Employee(FIRST_NAME1, LAST_NAME1, SALARY1, DEPARTMENT1));
        when(employeeServiceMock.remove(FIRST_NAME1, LAST_NAME1)).thenReturn(new Employee(FIRST_NAME1, LAST_NAME1, SALARY1, DEPARTMENT1));

        assertEquals(new Employee(FIRST_NAME1, LAST_NAME1, SALARY1, DEPARTMENT1), departmentService.findByDepartment(1));

        assertEquals(TEAM_NAME_1, out.add(TEAM_NAME_1));
        assertEquals(TEAM_NAME_1, out.find(TEAM_NAME_1));
        assertEquals(TEAM_NAME_1, out.remove(TEAM_NAME_1));

        verify(repositoryMock, times(1)).add(TEAM_NAME_1);
        verify(repositoryMock, times(1)).find(TEAM_NAME_1);
        verify(repositoryMock, times(1)).remove(TEAM_NAME_1);
    }

    @Test
    void findByMaxSalaryOfDepartment() {
    }

    @Test
    void findByMinSalaryOfDepartment() {
    }

    @Test
    void findByDepartment() {
    }

    @Test
    void findAll() {
    }
}