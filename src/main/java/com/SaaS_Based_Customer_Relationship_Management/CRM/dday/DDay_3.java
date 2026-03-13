package com.SaaS_Based_Customer_Relationship_Management.CRM.dday;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * DDay_3 - Java Streams Practice
 *
 * 1️⃣ Get names of employees in Engineering whose salary > average salary
 * 2️⃣ Get departments where salary > 50000
 * 3️⃣ Get employees in Support whose salary < 45000
 * 4️⃣ Get Product employees whose age > 25
 * 5️⃣ Get employee names who are not in Engineering
 * 6️⃣ Get employees whose name length > 5 and salary > 70000
 * 7️⃣ Get employees older than 30 grouped by department
 * 8️⃣ Get second highest salary in each department
 */

public class DDay_3 {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class Employee {
        private int id;
        private String name;
        private double salary;
        private String department;
        private int age;
    }

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee(1, "Rohit", 80000, "Engineering", 28),
                new Employee(2, "Aisha", 90000, "Engineering", 32),
                new Employee(3, "Vikram", 45000, "Support", 26),
                new Employee(4, "Sneha", 75000, "Product", 29),
                new Employee(5, "Kunal", 99000, "Engineering", 35),
                new Employee(6, "Priya", 55000, "Sales", 25),
                new Employee(7, "Anita", 88000, "Product", 31),
                new Employee(8, "Manish", 47000, "Support", 24)
        );


        /** 1️⃣ Get names of Engineering employees whose salary > average salary */
        double avgSalary = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        List<String> engineeringHighSalary = employees.stream()
                .filter(e -> e.getDepartment().equals("Engineering") && e.getSalary() > avgSalary)
                .map(Employee::getName)
                .collect(Collectors.toList());


        /** 2️⃣ Get departments where salary > 50000 */
        List<String> departments = employees.stream()
                .filter(employee -> employee.getSalary() > 50000)
                .map(Employee::getDepartment)
                .collect(Collectors.toList());


        /** 3️⃣ Get employees in Support whose salary < 45000 */
        List<String> supportLowSalary = employees.stream()
                .filter(employee -> employee.getDepartment().equalsIgnoreCase("Support") && employee.getSalary() < 45000)
                .map(Employee::getName)
                .collect(Collectors.toList());


        /** 4️⃣ Get Product employees whose age > 25 */
        List<String> productEmployees = employees.stream()
                .filter(employee -> employee.getDepartment().equalsIgnoreCase("Product") && employee.getAge() > 25)
                .map(Employee::getName)
                .collect(Collectors.toList());


        /** 5️⃣ Get employee names who are not in Engineering */
        List<String> notEngineering = employees.stream()
                .filter(employee -> !employee.getDepartment().equalsIgnoreCase("Engineering"))
                .map(Employee::getName)
                .collect(Collectors.toList());


        /** 6️⃣ Get employees whose name length > 5 and salary > 70000 */
        List<String> longNameHighSalary = employees.stream()
                .filter(employee -> employee.getName().length() > 5 && employee.getSalary() > 70000)
                .map(Employee::getName)
                .collect(Collectors.toList());


        /** 7️⃣ Get employees older than 30 grouped by department */
        Map<String, List<Employee>> employeesAbove30 = employees.stream()
                .filter(employee -> employee.getAge() > 30)
                .collect(Collectors.groupingBy(Employee::getDepartment));


        /** 8️⃣ Get second highest salary in each department */
        Map<String, List<Double>> secondHighestSalary = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(Employee::getSalary, Collectors.toList())
                ));

    }
}