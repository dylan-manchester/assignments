package com.hcl.assignment1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nTest Even or Odd:");
        EvenOrOddAnalyzer analyzer = new EvenOrOddAnalyzer();
        System.out.println("30: "+analyzer.analyse(30));
        System.out.println("7: "+analyzer.analyse(7));
        System.out.println("-6: "+analyzer.analyse(-6));
        System.out.println("10000001: "+analyzer.analyse(10000001));


        System.out.println("\nTest Word Reversal (StringBuilder implementation):");
        WordReverser reverser = new WordReverser();
        System.out.println("JAVA: "+reverser.reverse1("JAVA"));
        System.out.println("Python: "+reverser.reverse1("Python"));

        System.out.println("\nTest Word Reversal (for loop implementation):");
        System.out.println("123&456: "+reverser.reverse2("123&456"));
        System.out.println("aA: "+reverser.reverse2("aA"));


        System.out.println("\nTest Sorting Increasing Order (Comparable implementation):");
        EmployeeWithComparable e1 = new EmployeeWithComparable("John", 65);
        EmployeeWithComparable e2 = new EmployeeWithComparable("Alex", 21);
        EmployeeWithComparable e3 = new EmployeeWithComparable("Becky", 18);
        EmployeeWithComparable e4 = new EmployeeWithComparable("Raj", 44);
        EmployeeWithComparable e5 = new EmployeeWithComparable("Zach", 45);
        EmployeeWithComparable[] employeeWithComparableArray = {e1,e2,e3,e4,e5};
        System.out.println(Arrays.toString(employeeWithComparableArray));
        Arrays.sort(employeeWithComparableArray);
        System.out.println(Arrays.toString(employeeWithComparableArray));


        System.out.println("\nTest Sorting Decreasing Order (Comparator implementation):");
        Comparator<Employee> comparator = Collections.reverseOrder(Comparator.comparing(Employee::getAge));
        Employee e10 = new Employee("John", 65);
        Employee e11 = new Employee("Alex", 21);
        Employee e12 = new Employee("Becky", 18);
        Employee e13 = new Employee("Raj", 44);
        Employee e14 = new Employee("Zach", 45);
        Employee[] employeeArray = {e10,e11,e12,e13,e14};
        System.out.println(Arrays.toString(employeeArray));
        Arrays.sort(employeeArray, comparator);
        System.out.println(Arrays.toString(employeeArray));


    }
}
