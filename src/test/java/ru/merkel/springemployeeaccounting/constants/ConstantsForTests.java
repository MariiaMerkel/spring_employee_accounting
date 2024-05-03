package ru.merkel.springemployeeaccounting.constants;

import ru.merkel.springemployeeaccounting.models.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConstantsForTests {
    public static final String FIRST_NAME1 = "Иван";
    public static final String FIRST_NAME2 = "Пётр";
    public static final String FIRST_NAME3 = "Сидор";
    public static final String FIRST_NAME4 = "Николай";
    public static final String LAST_NAME1 = "Иванов";
    public static final String LAST_NAME2 = "Пётров";
    public static final String LAST_NAME3 = "Сидоров";
    public static final String LAST_NAME4 = "Николаев";


    public static final String NOT_FORMATTED_FIRST_NAME1 = "бОрИС";
    public static final String NOT_FORMATTED_FIRST_NAME2 = "Пётр123";
    public static final String NOT_FORMATTED_FIRST_NAME3 = "Си  дор";
    public static final String NOT_FORMATTED_FIRST_NAME4 = "  Александр";
    public static final String NOT_FORMATTED_LAST_NAME1 = "боРисоВ";
    public static final String NOT_FORMATTED_LAST_NAME2 = "Петров123";
    public static final String NOT_FORMATTED_LAST_NAME3 = "Сид  оров";
    public static final String NOT_FORMATTED_LAST_NAME4 = "Александров  ";

    public static final Integer SALARY1 = 100000;
    public static final Integer SALARY2 = 150000;
    public static final Integer SALARY3 = 200000;
    public static final Integer SALARY4 = 250000;
    public static final Integer DEPARTMENT1 = 1;
    public static final Integer DEPARTMENT2 = 2;
    public static final Integer DEPARTMENT3 = 3;
    public static final Integer DEPARTMENT4 = 4;

    public static final Employee EMPLOYEE_1 = new Employee(FIRST_NAME1, LAST_NAME1, SALARY1, DEPARTMENT1);
    public static final Employee EMPLOYEE_2 = new Employee(FIRST_NAME2, LAST_NAME2, SALARY2, DEPARTMENT2);
    public static final Employee EMPLOYEE_3 = new Employee(FIRST_NAME3, LAST_NAME3, SALARY3, DEPARTMENT3);
    public static final Employee EMPLOYEE_4 = new Employee(FIRST_NAME4, LAST_NAME4, SALARY4, DEPARTMENT4);
    public static final Employee EMPLOYEE_5 = new Employee(NOT_FORMATTED_FIRST_NAME1, NOT_FORMATTED_LAST_NAME1, SALARY2, DEPARTMENT1);
    public static final Employee EMPLOYEE_6 = new Employee(NOT_FORMATTED_FIRST_NAME4, NOT_FORMATTED_LAST_NAME4, SALARY3, DEPARTMENT1);
    public static final String EMPLOYEE_MIN_SALARY = "Сотрудник с наименьшей зарплатой отдела №1: ФИО сотрудника: Иван Иванов, отдел: 1, зарплата: 100 000,00 ₽.";
    public static final String EMPLOYEE_MAX_SALARY = "Сотрудник с наибольшей зарплатой отдела №4: ФИО сотрудника: Николай Николаев, отдел: 4, зарплата: 250 000,00 ₽.";

    public static final List<Employee> EMPLOYEES = List.of(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5, EMPLOYEE_6);
    public static final Map<Integer, List<Employee>> GROUPED_EMPLOYEES = EMPLOYEES.stream().collect(Collectors.groupingBy(Employee::getDepartment));
    public static final Collection<Employee> EMPLOYEES_DEPARTMENT_1 = EMPLOYEES.stream().filter(e -> e.getDepartment().equals(DEPARTMENT1)).collect(Collectors.toSet());
    public static final String DEPARTMENT1_SALARY = "Сумма зарплат отдела №1: 450000";
}
