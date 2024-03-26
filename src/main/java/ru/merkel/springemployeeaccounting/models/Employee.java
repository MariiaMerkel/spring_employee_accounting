package ru.merkel.springemployeeaccounting.models;

import java.util.Objects;

public record Employee(String firstName, String lastName) {

    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    public String toString() {
        return String.format("ФИО сотрудника: %s.", this.getFullName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getFullName(), employee.getFullName());
    }


}
