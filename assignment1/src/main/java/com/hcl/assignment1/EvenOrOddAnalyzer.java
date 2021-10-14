package com.hcl.assignment1;

public class EvenOrOddAnalyzer {
    public boolean isEven(int num) {
        if (num%2==0) return true;
        return false;
    }
    public String analyse(int num) {
        if (isEven(num)) return "Even";
        return "Odd";
    }
}
