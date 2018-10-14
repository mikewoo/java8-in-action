package com.mikewoo.study.java8.optional;

import com.mikewoo.study.java8.domain.Insurance;

import java.util.Optional;

/**
 * @author Eric Gui
 * @date 2018/7/27
 */
public class OptionalUsage {

    public static void main(String[] args) {
        Optional<Insurance> optionalInsurance = Optional.empty();
        // optionalInsurance.get(); // throw NoSuchElementException
        Optional<Insurance> optionalInsurance1 = Optional.of(new Insurance());
        optionalInsurance1.get();

        /*Optional<Object> objectOptional = Optional.ofNullable(null);
        objectOptional.orElseGet(Insurance::new);

        objectOptional.orElse(new Insurance());

        objectOptional.orElseThrow(RuntimeException::new);

        objectOptional.orElseThrow(() -> new RuntimeException("Not have reference"));*/

        // optionalInsurance1.filter(i -> i.getName() == null).get();

        Optional<String> nameOptional = optionalInsurance1.map(i -> i.getName());

        System.out.println(nameOptional.orElse("empty value"));

        System.out.println(nameOptional.isPresent());

        nameOptional.ifPresent(System.out::println);

        System.out.println(getInsuranceName(null));

        System.out.println(getInsuranceNameByOptional(null));

    }

    private static String getInsuranceName(Insurance insurance) {
        if (null == insurance)
            return "unknown";
        return insurance.getName();
    }

    private static String getInsuranceNameByOptional(Insurance insurance) {
        return Optional.ofNullable(insurance).map(Insurance::getName).orElse("unknown");
    }
}
