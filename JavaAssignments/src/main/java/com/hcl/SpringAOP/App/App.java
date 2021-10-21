package com.hcl.SpringAOP.App;

import com.hcl.SpringAOP.bmw.Car;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {
    public static void main( String[] args ) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        Car car = (Car) context.getBean("carBeanOne");
        car.printItemDetails();
    }
}
