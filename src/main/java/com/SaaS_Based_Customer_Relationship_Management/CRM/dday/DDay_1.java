package com.SaaS_Based_Customer_Relationship_Management.CRM.dday;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * DDay_1 - Java Streams Interview Practice
 *
 * Section 1 – Map / Filter / Reduce Variations
 * 1️⃣ Convert List<Employee> to Map<id, name>
 * 2️⃣ Convert List<Employee> to Map<name, salary>
 * 3️⃣ Get total salary of all employees
 * 4️⃣ Get sum of salaries of employees in Engineering department
 * 5️⃣ Get average age of employees older than 30
 * 6️⃣ Get names of employees whose name starts with “A”
 * 7️⃣ Get uppercase names of employees whose salary > 50000
 * 8️⃣ Count employees whose age < 30 and salary > 50000
 * 9️⃣ Find total salary using reduce()
 * 🔟 Concatenate all employee names into a comma-separated string
 *
 * Section 2 – Advanced Grouping / Mapping / Partitioning
 * 1️⃣ Group employees by department and then by age > 30
 * 2️⃣ Find employees older than 30 grouped by department
 * 3️⃣ Count employees by department where age > 30
 * 4️⃣ Map department -> list of employee names
 * 5️⃣ Partition employees based on age >= 30 and salary > 80000
 * 6️⃣ Find total salary per department
 * 7️⃣ Find employee with minimum age in each department
 * 8️⃣ Nested grouping: Department -> Age -> List<Employee>
 */

public class DDay_1 {

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

        // ==============================
        // Section 1 – Map / Filter / Reduce
        // ==============================

        // 1️⃣ Convert List<Employee> → Map<id, name>
        Map<Integer, String> idToNameMap =
                employees.stream()
                        .collect(Collectors.toMap(
                                Employee::getId,
                                Employee::getName
                        ));

        // 2️⃣ Convert List<Employee> → Map<name, salary>
        Map<String, Double> nameToSalaryMap =
                employees.stream()
                        .collect(Collectors.toMap(
                                Employee::getName,
                                Employee::getSalary
                        ));

        // 3️⃣ Get total salary of all employees
        double totalSalary =
                employees.stream()
                        .mapToDouble(Employee::getSalary)
                        .sum();

        // 4️⃣ Sum of salaries of Engineering employees
        double engineeringSalarySum =
                employees.stream()
                        .filter(e -> "Engineering".equals(e.getDepartment()))
                        .mapToDouble(Employee::getSalary)
                        .sum();

        // 5️⃣ Average age of employees older than 30
        double averageAgeAbove30 =
                employees.stream()
                        .filter(e -> e.getAge() > 30)
                        .mapToInt(Employee::getAge)
                        .average()
                        .orElse(0);

        // 6️⃣ Names starting with "A"
        List<String> namesStartingWithA =
                employees.stream()
                        .filter(e -> e.getName().startsWith("A"))
                        .map(Employee::getName)
                        .collect(Collectors.toList());

        // 7️⃣ Uppercase names where salary > 50000
        List<String> uppercaseNamesHighSalary =
                employees.stream()
                        .filter(e -> e.getSalary() > 50000)
                        .map(e -> e.getName().toUpperCase())
                        .collect(Collectors.toList());

        // 8️⃣ Count employees where age < 30 AND salary > 50000
        long countAgeBelow30SalaryAbove50k =
                employees.stream()
                        .filter(e -> e.getAge() < 30 && e.getSalary() > 50000)
                        .count();

        // 9️⃣ Total salary using reduce()
        double totalSalaryUsingReduce =
                employees.stream()
                        .map(Employee::getSalary)
                        .reduce(0.0, Double::sum);

        // 🔟 Concatenate all employee names
        String concatenatedNames =
                employees.stream()
                        .map(Employee::getName)
                        .collect(Collectors.joining(", "));

        // ==============================
        // Section 2 – Advanced Grouping
        // ==============================

        // 1️⃣ Group employees by department → partition age > 30
        Map<String, Map<Boolean, List<Employee>>> groupByDeptAndAgeAbove30 =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.partitioningBy(e -> e.getAge() > 30)
                        ));

        // 2️⃣ Employees older than 30 grouped by department
        Map<String, List<Employee>> employeesAbove30ByDept =
                employees.stream()
                        .filter(e -> e.getAge() > 30)
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment
                        ));

        // 3️⃣ Count employees per department where age > 30
        Map<String, Long> countByDeptAndAgeAbove30 =
                employees.stream()
                        .filter(e -> e.getAge() > 30)
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.counting()
                        ));

        // 4️⃣ Department → List of employee names
        Map<String, List<String>> namesByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.mapping(
                                        Employee::getName,
                                        Collectors.toList()
                                )
                        ));

        // 5️⃣ Partition employees where age >= 30 AND salary > 80000
        Map<Boolean, List<Employee>> partitionedByAgeAndSalary =
                employees.stream()
                        .collect(Collectors.partitioningBy(
                                e -> e.getAge() >= 30 && e.getSalary() > 80000
                        ));

        // 6️⃣ Total salary per department
        Map<String, Double> totalSalaryByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.summingDouble(Employee::getSalary)
                        ));

        // 7️⃣ Employee with minimum age per department
        Map<String, Optional<Employee>> minAgeEmployeeByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.minBy(
                                        Comparator.comparing(Employee::getAge)
                                )
                        ));

        // 8️⃣ Nested grouping: Department → Age → List<Employee>
        Map<String, Map<Integer, List<Employee>>> employeesByDeptAndAge =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.groupingBy(Employee::getAge)
                        ));
    }
}