package com.SaaS_Based_Customer_Relationship_Management.CRM.dday;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * DDay_2 - Java Streams Interview Practice
 *
 * ==============================
 * Section 3 – Sorting / Top N / Ranking
 * ==============================
 * 1️⃣ Get top 5 highest salary employees
 * 2️⃣ Get employee with 3rd highest salary
 * 3️⃣ Sort employees by name ascending and salary descending
 * 4️⃣ Get employees sorted by department, then by salary
 * 5️⃣ Find top 3 oldest employees
 * 6️⃣ Sort employees by name length
 *
 * ==============================
 * Section 4 – Optional / Max / Min Variations
 * ==============================
 * 1️⃣ Find employee with minimum age in Engineering
 * 2️⃣ Find employee with maximum salary in Sales
 * 3️⃣ Find 2nd lowest salary employee
 * 4️⃣ Find 2nd oldest employee in Product
 * 5️⃣ Find employee with max salary in each department (Map<String, Employee>) without Optional
 *
 * ==============================
 * Section 5 – Frequency / Counting / Duplicates
 * ==============================
 * 1️⃣ Find duplicate salaries
 * 2️⃣ Count frequency of employees per age
 * 3️⃣ Count frequency of employees by first letter of name
 *
 * ==============================
 * Section 6 – Department Aggregates
 * ==============================
 * 1️⃣ Get Map<department, average age>
 * 2️⃣ Get Map<department, comma-separated employee names>
 * 3️⃣ Get Map<department, total salary> using reducing() collector
 * 4️⃣ Partition employees by salary > 80000 and count in each partition
 * 5️⃣ Get Map<department, average salary> and sum of all averages
 * 6️⃣ Get Map<department, employee with max salary using maxBy>
 */

public class DDay_2 {

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
        // Section 3 – Sorting / Top N / Ranking
        // ==============================

        // 1️⃣ Get top 5 highest salary employees
        List<Employee> top5HighestSalaryEmployees = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(5)
                .toList();

        // 2️⃣ Get employee with 3rd highest salary
        Optional<Employee> thirdHighestSalaryEmployee = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(2)
                .findFirst();

        // 3️⃣ Sort employees by name ascending and salary descending
        List<Employee> sortByNameAscSalaryDesc = employees.stream()
                .sorted(
                        Comparator.comparing(Employee::getName)
                                .thenComparing(Comparator.comparingDouble(Employee::getSalary).reversed())
                )
                .toList();

        // 4️⃣ Get employees sorted by department, then by salary
        List<Employee> sortByDepartmentThenSalary = employees.stream()
                .sorted(
                        Comparator.comparing(Employee::getDepartment)
                                .thenComparing(Employee::getSalary)
                )
                .toList();

        // 5️⃣ Find top 3 oldest employees
        List<Employee> top3OldestEmployees = employees.stream()
                .sorted(Comparator.comparingInt(Employee::getAge).reversed())
                .limit(3)
                .toList();

        // 6️⃣ Sort employees by name length
        List<Employee> sortByNameLength = employees.stream()
                .sorted(Comparator.comparing(e -> e.getName().length()))
                .toList();


        // ==============================
        // Section 4 – Optional / Max / Min Variations
        // ==============================

        // 1️⃣ Find employee with minimum age in Engineering
        Optional<Employee> youngestEngineer = employees.stream()
                .filter(e -> "Engineering".equals(e.getDepartment()))
                .min(Comparator.comparingInt(Employee::getAge));

        // 2️⃣ Find employee with maximum salary in Sales
        Optional<Employee> highestPaidSalesEmployee = employees.stream()
                .filter(e -> "Sales".equals(e.getDepartment()))
                .max(Comparator.comparingDouble(Employee::getSalary));

        // 3️⃣ Find 2nd lowest salary employee
        Optional<Employee> secondLowestSalaryEmployee = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .skip(1)
                .findFirst();

        // 4️⃣ Find 2nd oldest employee in Product
        Optional<Employee> secondOldestProductEmployee = employees.stream()
                .filter(e -> "Product".equals(e.getDepartment()))
                .sorted(Comparator.comparingInt(Employee::getAge).reversed())
                .skip(1)
                .findFirst();

        // 5️⃣ Find employee with max salary in each department (without Optional)
        Map<String, Employee> maxSalaryEmployeeByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                                Optional::get
                        )
                ));


        // ==============================
        // Section 5 – Frequency / Counting / Duplicates
        // ==============================

        // 1️⃣ Find duplicate salaries
        List<Double> duplicateSalaries = employees.stream()
                .collect(Collectors.groupingBy(Employee::getSalary, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();

        // 2️⃣ Count frequency of employees per age
        Map<Integer, Long> frequencyByAge = employees.stream()
                .collect(Collectors.groupingBy(Employee::getAge, Collectors.counting()));

        // 3️⃣ Count frequency of employees by first letter of name
        Map<Character, Long> frequencyByFirstLetter = employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getName().charAt(0),
                        Collectors.counting()
                ));


        // ==============================
        // Section 6 – Department Aggregates
        // ==============================

        // 1️⃣ Get Map<department, average age>
        Map<String, Double> averageAgeByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getAge)));

        // 2️⃣ Get Map<department, comma-separated employee names>
        Map<String, String> namesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.joining(", "))));

        // 3️⃣ Get Map<department, total salary> using reducing()
        Map<String, Double> totalSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.reducing(0.0, Employee::getSalary, Double::sum)));

        // 4️⃣ Partition employees by salary > 80000 and count in each partition
        Map<Boolean, Long> countBySalaryPartition = employees.stream()
                .collect(Collectors.partitioningBy(
                        e -> e.getSalary() > 80000,
                        Collectors.counting()
                ));

        // 5️⃣ Get Map<department, average salary> and sum of all averages
        Map<String, Double> averageSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        double sumOfDepartmentAverages = averageSalaryByDepartment.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        // 6️⃣ Get Map<department, employee with max salary using maxBy>
        Map<String, Optional<Employee>> maxSalaryOptionalByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));


        // ==============================
        // Optional: Print Results
        // ==============================
        System.out.println("Top 5 Highest Salary Employees: " + top5HighestSalaryEmployees);
        System.out.println("Third Highest Salary Employee: " + thirdHighestSalaryEmployee);
        System.out.println("Sort by Name Asc & Salary Desc: " + sortByNameAscSalaryDesc);
        System.out.println("Sort by Department then Salary: " + sortByDepartmentThenSalary);
        System.out.println("Top 3 Oldest Employees: " + top3OldestEmployees);
        System.out.println("Sort by Name Length: " + sortByNameLength);
        System.out.println("Youngest Engineer: " + youngestEngineer);
        System.out.println("Highest Paid Sales Employee: " + highestPaidSalesEmployee);
        System.out.println("Second Lowest Salary Employee: " + secondLowestSalaryEmployee);
        System.out.println("Second Oldest Product Employee: " + secondOldestProductEmployee);
        System.out.println("Max Salary Employee By Dept: " + maxSalaryEmployeeByDepartment);
        System.out.println("Duplicate Salaries: " + duplicateSalaries);
        System.out.println("Frequency by Age: " + frequencyByAge);
        System.out.println("Frequency by First Letter: " + frequencyByFirstLetter);
        System.out.println("Average Age By Dept: " + averageAgeByDepartment);
        System.out.println("Names By Dept: " + namesByDepartment);
        System.out.println("Total Salary By Dept: " + totalSalaryByDepartment);
        System.out.println("Count By Salary Partition (>80000): " + countBySalaryPartition);
        System.out.println("Average Salary By Dept: " + averageSalaryByDepartment);
        System.out.println("Sum Of Department Averages: " + sumOfDepartmentAverages);
        System.out.println("Max Salary Optional By Dept: " + maxSalaryOptionalByDept);
    }
}