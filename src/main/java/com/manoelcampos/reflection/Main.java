package com.manoelcampos.reflection;

import com.manoelcampos.reflection.model.City;
import com.manoelcampos.reflection.model.Country;
import com.manoelcampos.reflection.model.Customer;
import com.manoelcampos.reflection.model.State;

/**
 * Runs the example to dynamically discover object fields, values, methods and constructors.
 * @author Manoel Campos da Silva Filho
 */
public class Main {
    public static void main(String[] args) {
        final var country = new Country("Brasil");
        final var state = new State("Tocantins", "TO", country);
        final var city = new City("Palmas", state);
        final var customer = new Customer("Manoel", 'M', "99999-9999", city);

        new ObjectDiscovery(customer).print();
    }

}
