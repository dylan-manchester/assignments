package com.hcl.BasicSyntax;

public class EmployeeWithComparable extends Employee implements Comparable<Employee>{
    public EmployeeWithComparable(String name, int age) {
        super(name, age);
    }

    public int compareTo(Employee o) {
        return Integer.compare(this.getAge(),o.getAge());
    }

}
