package com.SaaS_Based_Customer_Relationship_Management.CRM.dday;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * DDay - Java Streams Interview Practice
 *
 * This class demonstrates common Java Stream API interview questions
 * using a sample Employee dataset.
 *
 * Covered Stream Problems:
 * 1. Get all employee names
 * 2. Filter employees based on salary
 * 3. Count employees in Engineering department
 * 4. Find employee with highest salary
 * 5. Find employee with lowest salary
 * 6. Get employee names sorted by salary
 * 7. Group employees by department
 * 8. Count employees in each department
 * 9. Find highest paid employee in each department
 * 10. Find average salary in each department
 * 11. Get employee names whose age > 30
 * 12. Get employee names whose salary > 70000
 * 13. Sort employees by age
 * 14. Find second highest salary employee
 * 15. Find department with highest average salary
 * 16. Partition employees based on salary > 70000
 * 17. Group employee names by department
 * 18. Get top 3 highest salary employees
 * 19. Find duplicate employee names
 * 20. Find total salary expense per department
 */
public class DDay {

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

        // 1️⃣ Get all employee names
        List<String> employeeNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        // 2️⃣ Get employees whose salary > 80000
        List<Employee> highSalaryEmployees = employees.stream()
                .filter(e -> e.getSalary() > 80000)
                .collect(Collectors.toList());

        // 3️⃣ Count employees in Engineering department
        long engineeringEmployeeCount = employees.stream()
                .filter(e -> "Engineering".equals(e.getDepartment()))
                .count();

        // 4️⃣ Find the employee with highest salary
        Employee highestSalaryEmployee = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);

        // 5️⃣ Find the employee with lowest salary
        Employee lowestSalaryEmployee = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);

        // 6️⃣ Get list of employee names sorted by salary
        List<String> employeeNamesSortedBySalary = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .map(Employee::getName)
                .collect(Collectors.toList());

        // 7️⃣ Group employees by department
        Map<String, List<Employee>> employeesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        // 8️⃣ Count employees in each department
        Map<String, Long> employeeCountByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));

        // 9️⃣ Find highest paid employee in each department
        Map<String, Optional<Employee>> highestPaidEmployeeByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
                ));

        // 🔟 Find average salary in each department
        Map<String, Double> averageSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));

        // 1️⃣1️⃣ Get employee names whose age > 30
        List<String> employeesAgeAbove30 = employees.stream()
                .filter(e -> e.getAge() > 30)
                .map(Employee::getName)
                .collect(Collectors.toList());

        // 1️⃣2️⃣ Get employee names whose salary > 70000
        List<String> employeesSalaryAbove70k = employees.stream()
                .filter(e -> e.getSalary() > 70000)
                .map(Employee::getName)
                .collect(Collectors.toList());

        // 1️⃣3️⃣ Sort employees by age
        List<Employee> employeesSortedByAge = employees.stream()
                .sorted(Comparator.comparingInt(Employee::getAge))
                .collect(Collectors.toList());

        // 1️⃣4️⃣ Find second highest salary employee
        Employee secondHighestSalaryEmployee = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(1)
                .findFirst()
                .orElse(null);

        // 1️⃣5️⃣ Find department with highest average salary
        Map.Entry<String, Double> departmentWithHighestAverageSalary = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        // 1️⃣6️⃣ Partition employees based on salary > 70000
        Map<Boolean, List<Employee>> employeesPartitionedBySalary = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 70000));

        // 1️⃣7️⃣ Group employee names by department
        Map<String, List<String>> employeeNamesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));

        // 1️⃣8️⃣ Get top 3 highest salary employees
        List<Employee> top3HighestSalaryEmployees = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3)
                .collect(Collectors.toList());

        // 1️⃣9️⃣ Find duplicate employee names
        List<String> duplicateEmployeeNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.groupingBy(
                        name -> name,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // 2️⃣0️⃣ Find total salary expense per department
        Map<String, Double> totalSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)
                ));



    }
}