package io.github.manoelcampos.reflection.model;

/**
 * @author Manoel Campos da Silva Filho
 */
public class State {
    private Long id;
    private String name;

    private String acronym;
    private Country country;

    public State() {
    }

    public State(String name, String acronym, Country country) {
        this.name = name;
        this.acronym = acronym;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "%s, %s".formatted(name, country.getName());
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}
