package io.github.manoelcampos.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Dynamically discovers object fields, values, methods and constructors.
 * @author Manoel Campos da Silva Filho
 * @see #print()
 */
public class ObjectDiscovery {
    private final Field[] fields;
    private final Method[] methods;
    private final Constructor<?>[] constructors;
    private final Object object;

    /**
     * Creates an instance do discover object data and metadata.
     * @param object
     * @see #print()
     */
    public ObjectDiscovery(final Object object) {
        Objects.requireNonNull(object, "The object to discovery metadata cannot be null");

        this.object = object;
        final var objectClass = object.getClass();
        this.fields = objectClass.getDeclaredFields();
        this.methods = objectClass.getDeclaredMethods();
        this.constructors = objectClass.getConstructors();

        setFieldsAccessible();
    }

    public void print() {
        printFields();
        printConstructors();
        printMethods();
    }

    public void printFields() {
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

    public void printConstructors() {
        System.out.printf("%s Constructors%n", object.getClass().getSimpleName());
        Arrays.stream(constructors).forEach(c -> System.out.printf("  %s(%s)%n", getConstructorSimpleName(c), getConstructorParamTypes(c)));
        System.out.println();
    }

    public void printMethods() {
        System.out.printf("%s Methods%n", object.getClass().getSimpleName());
        Arrays.stream(methods).forEach(m -> System.out.printf("  %s %s(%s)%n", getReturnType(m), m.getName(), getParameterTypes(m)));
        System.out.println();
    }

    /**
     * Allows direct access to private fields to read and write values.
     */
    private void setFieldsAccessible() {
        Arrays.stream(fields).forEach(f -> f.setAccessible(true));
    }

    /**
     * {@return the simple name of a constructor} That is, without the package name.
     *
     * @param c the construtor
     */
    private static String getConstructorSimpleName(final Constructor<?> c) {
        final var array = c.getName().split("\\.");
        return array[array.length - 1];
    }

    /**
     * {@return a String containing the simple name of a method's parameters}. That is, param types without the package name.
     *
     * @param m the method
     */
    private static String getParameterTypes(final Method m) {
        return Arrays.stream(m.getParameterTypes()).map(Class::getSimpleName).collect(Collectors.joining(", "));
    }

    /**
     * {@return the simple name of a method return type}. That is, return type without the package name.
     *
     * @param m the method
     */
    private static String getReturnType(final Method m) {
        return m.getReturnType().getSimpleName();
    }

    /**
     * {@return a String containing the simple name of a constructor's parameters}. That is, param types without the package name.
     *
     * @param c the construtor
     */
    private static String getConstructorParamTypes(final Constructor<?> c) {
        return Arrays.stream(c.getParameterTypes()).map(Class::getSimpleName).collect(Collectors.joining(", "));
    }
}
