package com.mikewoo.study.java8.optional;

import com.mikewoo.study.java8.domain.Car;
import com.mikewoo.study.java8.domain.Insurance;
import com.mikewoo.study.java8.domain.Person;

/**
 * @author Eric Gui
 * @date 2018/7/27
 */
public class NullPointerExceptioinExample {


    public static void main(String[] args) {
        // getInsuranceName(new Person()); // Exception in thread "main" java.lang.NullPointerException
        String insuranceName = getInsuranceByDeepDoubts(new Person());
        System.out.println(insuranceName);
        System.out.println("=======================");
        insuranceName = getInsuranceNameByNultExit(new Person());
        System.out.println(insuranceName);

    }

    private static String getInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }

    private static String getInsuranceByDeepDoubts(Person person) {
        if (null != person) {
            Car car = person.getCar();
            if (null != car) {
                Insurance insurance = car.getInsurance();
                if (null != insurance) {
                    return insurance.getName();
                }
            }
        }

        return "Unknown";
    }

    private static String getInsuranceNameByNultExit(Person person) {
        final String defaultValue = "Unknown";

        if (null == person)
            return defaultValue;

        Car car = person.getCar();
        if (null == car)
            return defaultValue;

        Insurance insurance = car.getInsurance();
        if (null == insurance)
            return defaultValue;

        return insurance.getName();
    }
}
