package ru.merkel.springemployeeaccounting.services;
import java.util.Objects;

public class Employee {
        private final String firstName;
        private final String lastName;
        private final int id;
        private static int counter = 1;

        public Employee(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.id = getCounter();
        }

        public String getFirstName() {
            return this.firstName;
        }

        public String getLastName() {
            return this.lastName;
        }

        public static int getCounter() {
            return counter++;
        }

        public String getFullName() {
            return String.format("%s %s", this.getFirstName(), this.getLastName());
        }

        public int getId() {
            return this.id;
        }

        public String toString() {
            return String.format("%d. ФИО сотрудника: %s.", this.id, this.getFullName());
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(getFullName(), employee.getFullName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id);
    }
}
