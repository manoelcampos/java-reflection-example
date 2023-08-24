package io.github.manoelcampos.reflection.model;

public class Customer {
    private Long id;
    private String name;
    private String phone;
    private City city;
    private char gender;

    public Customer() {
    }

    public Customer(String name, char gender, String phone, City city) {
        this.name = name;
        this.setGender(gender);
        this.phone = phone;
        this.city = city;
    }

    /**
     * Clones a given customer, ignoring the id.
     * @param source
     * @return
     */
    public static Customer of(Customer source){
        return new Customer(source.name, source.gender, source.phone, source.city);
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public char getGender() {
        return gender;
    }

    public final void setGender(char gender) {
        gender = Character.toUpperCase(gender);
        if(gender != 'M' && gender != 'F')
            throw new IllegalArgumentException("Gender must be M or F");

        this.gender = gender;
    }

    public boolean isMale(){
        return gender == 'M';
    }

    public boolean isFemale(){
        return gender == 'F';
    }

}
