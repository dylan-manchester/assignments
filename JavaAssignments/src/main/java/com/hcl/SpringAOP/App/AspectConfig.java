package com.hcl.SpringAOP.App;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectConfig {

    @Pointcut("execution(* com.hcl.SpringAOP.bmw.Car.*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void printLogo() {
        System.out.println("\n****** BMW LOGO ******\n");
    }

    @After("pointCut()")
    public void printCaller(JoinPoint jp) {
        System.out.println("Courtesy of "+jp.getSignature().getName());
        printLogo();
    }
}
