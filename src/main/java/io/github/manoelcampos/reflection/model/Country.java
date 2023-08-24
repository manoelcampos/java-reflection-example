package io.github.manoelcampos.reflection.model;

/**
 * @author Manoel Campos da Silva Filho
 */
public class Country {
    private Long id;
    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
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
}
