package io.github.manoelcampos.reflection.model;

/**
 * @author Manoel Campos da Silva Filho
 */
public class City {
    private Long id;
    private String name;

    private State state;

    public City() {
    }

    public City(String name, State state) {
        this.name = name;
        this.state = state;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "%s - %s".formatted(name, state.getAcronym());
    }
}
