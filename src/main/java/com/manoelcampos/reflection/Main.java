package com.manoelcampos.reflection;

import com.manoelcampos.reflection.model.City;
import com.manoelcampos.reflection.model.Country;
import com.manoelcampos.reflection.model.Customer;
import com.manoelcampos.reflection.model.State;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        final var country = new Country("Brasil");
        final var state = new State("Tocantins", "TO", country);
        final var city = new City("Palmas", state);
        final var customer = new Customer("Manoel", 'M', "99999-9999", city);
        final var objectClass = customer.getClass();
        final var fields = objectClass.getDeclaredFields();
        final var methods = objectClass.getDeclaredMethods();
        final var constructors = objectClass.getConstructors();

        setFieldsAccessible(fields);
        printFields(customer, fields);
        printConstructors(customer, constructors);
        printMethods(customer, methods);
    }

    /**
     * Allows direct access to private fields to read and write values.
     * @param fields
     */
    private static void setFieldsAccessible(final Field[] fields) {
        Arrays.stream(fields).forEach(f -> f.setAccessible(true));
    }

    private static void printFields(final Object object, final Field[] fields) {
        System.out.printf("%s Fields%n", object.getClass().getSimpleName());
        for (final var f : fields) {
            try {
                System.out.printf("  %s: %s%n", f.getName(), f.get(object));
            } catch (IllegalAccessException e) {
                System.err.printf("Error accessing field %s: %s%n", f.getName(), e.getMessage());
            }
        }

        System.out.println();
    }

    private static void printConstructors(final Object object, final Constructor<?>[] constructors) {
        System.out.printf("%s Constructors%n", object.getClass().getSimpleName());
        Arrays.stream(constructors).forEach(c -> System.out.printf("  %s(%s):%n", c.getName(), getConstructorParamTypes(c)));
        System.out.println();
    }

    private static void printMethods(final Object object, final Method[] methods) {
        System.out.printf("%s Methods%n", object.getClass().getSimpleName());
        Arrays.stream(methods).forEach(m -> System.out.printf("  %s %s(%s):%n", getReturnType(m), m.getName(), getParameterTypes(m)));
        System.out.println();
    }

    private static String getParameterTypes(final Method m) {
        return Arrays.stream(m.getParameterTypes()).map(Class::getSimpleName).collect(Collectors.joining(", "));
    }

    private static String getConstructorParamTypes(final Constructor c) {
        return Arrays.stream(c.getParameterTypes()).map(Class::getSimpleName).collect(Collectors.joining(", "));
    }

    private static String getReturnType(final Method m) {
        return m.getReturnType().getSimpleName();
    }

}
