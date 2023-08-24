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

        printObjectDataAndMetadata(customer);
    }

    private static void printObjectDataAndMetadata(final Object object) {
        final var objectClass = object.getClass();
        final var fields = objectClass.getDeclaredFields();
        final var methods = objectClass.getDeclaredMethods();
        final var constructors = objectClass.getConstructors();

        setFieldsAccessible(fields);
        printFields(object, fields);
        printConstructors(object, constructors);
        printMethods(object, methods);
    }

    /**
     * Allows direct access to private fields to read and write values.
     * @param fields fields to set accessible
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
        Arrays.stream(constructors).forEach(c -> System.out.printf("  %s(%s)%n", getConstructorSimpleName(c), getConstructorParamTypes(c)));
        System.out.println();
    }

    /**
     * {@return the simple name of a constructor} That is, without the package name.
     * @param c the construtor
     */
    private static String getConstructorSimpleName(final Constructor<?> c) {
        final var array = c.getName().split("\\.");
        return array[array.length-1];
    }

    private static void printMethods(final Object object, final Method[] methods) {
        System.out.printf("%s Methods%n", object.getClass().getSimpleName());
        Arrays.stream(methods).forEach(m -> System.out.printf("  %s %s(%s)%n", getReturnType(m), m.getName(), getParameterTypes(m)));
        System.out.println();
    }

    /**
     * {@return a String containing the simple name of a method's parameters}. That is, param types without the package name.
     * @param m the method
     */
    private static String getParameterTypes(final Method m) {
        return Arrays.stream(m.getParameterTypes()).map(Class::getSimpleName).collect(Collectors.joining(", "));
    }

    /**
     * {@return the simple name of a method return type}. That is, return type without the package name.
     * @param m the method
     */
    private static String getReturnType(final Method m) {
        return m.getReturnType().getSimpleName();
    }

    /**
     * {@return a String containing the simple name of a constructor's parameters}. That is, param types without the package name.
     * @param c the construtor
     */
    private static String getConstructorParamTypes(final Constructor<?> c) {
        return Arrays.stream(c.getParameterTypes()).map(Class::getSimpleName).collect(Collectors.joining(", "));
    }

}
