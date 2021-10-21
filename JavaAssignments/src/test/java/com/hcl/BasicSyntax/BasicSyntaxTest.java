package com.hcl.BasicSyntax;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class BasicSyntaxTest {
    @Test
    public void testEven() {
        EvenOrOddAnalyzer analyser = new EvenOrOddAnalyzer();
        assertEquals(analyser.analyse(30),"Even");
    }

    @Test
    public void testOdd() {
        EvenOrOddAnalyzer analyser = new EvenOrOddAnalyzer();
        assertEquals(analyser.analyse(-3),"Odd");
    }

    @Test
    public void testReverse1() {
        WordReverser reverser = new WordReverser();
        assertEquals(reverser.reverse1("JAVA"),"AVAJ");
    }

    @Test
    public void testReverse2() {
        WordReverser reverser = new WordReverser();
        assertEquals(reverser.reverse2("JAVA"),"AVAJ");
    }

    @Test
    public void testSortingIncreasingComparable() {
        EmployeeWithComparable e1 = new EmployeeWithComparable("John", 65);
        EmployeeWithComparable e2 = new EmployeeWithComparable("Alex", 21);
        EmployeeWithComparable e3 = new EmployeeWithComparable("Becky", 18);
        EmployeeWithComparable e4 = new EmployeeWithComparable("Raj", 44);
        EmployeeWithComparable e5 = new EmployeeWithComparable("Zach", 45);
        EmployeeWithComparable[] employeeWithComparableArray = {e1,e2,e3,e4,e5};
        EmployeeWithComparable[] sorted = {e3,e2,e4,e5,e1};
        Arrays.sort(employeeWithComparableArray);
        assertEquals(Arrays.toString(employeeWithComparableArray),Arrays.toString(sorted));

    }

    @Test
    public void testSortingDecreasingComparator() {
        Comparator<Employee> comparator = Collections.reverseOrder(Comparator.comparing(Employee::getAge));
        Employee e10 = new Employee("John", 65);
        Employee e11 = new Employee("Alex", 21);
        Employee e12 = new Employee("Becky", 18);
        Employee e13 = new Employee("Raj", 44);
        Employee e14 = new Employee("Zach", 45);
        Employee[] employeeArray = {e10,e11,e12,e13,e14};
        Employee[] sorted = {e10,e14,e13,e11,e12};
        Arrays.sort(employeeArray, comparator);
        assertEquals(Arrays.toString(employeeArray),Arrays.toString(sorted));
    }
}
