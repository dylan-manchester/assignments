package com.hcl.flag.models;

public class Counter implements Comparable<Counter> {

    private final String name;     // counter name
    private int count;             // current value

    // create a new counter with the given parameters
    public Counter(String id) {
        name = id;
        count = 0;
    }

    // increment the counter by 1
    public void increment() {
        count++;
    }

    // return the current count
    public int value() {
        return count;
    }

    public String getName() {
        return name;
    }

    // return a string representation of this counter
    public String toString() {
        return name + ": " + count;
    }

    // compare two Counter objects based on their count
    public int compareTo(Counter that) {
        if      (this.count < that.count) return -1;
        else if (this.count > that.count) return +1;
        else                              return  0;
    }
}