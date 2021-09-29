package com.hcl.assignment1;

public class WordReverser {
    public String reverse1(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return sb.toString();
    }
    public String reverse2(String s) {
        String rv = "";
        for (int i=s.length()-1; i>=0; i--) {
            rv += s.charAt(i);
        }
        return rv;
    }
}
