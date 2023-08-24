# Java Reflection Example [![build](https://github.com/manoelcampos/java-reflection-example/actions/workflows/build.yml/badge.svg)](https://github.com/manoelcampos/java-reflection-example/actions/workflows/build.yml)

Shows how to dynamically discover object fields, values, methods and constructors.
Below is the result of running the project's [Main.java](src/main/java/com/manoelcampos/reflection/Main.java) class,
which discovers information about any object you provide.
The sample below prints data about a `Customer` object.

```log
Customer Fields
  id: null
  name: Manoel
  phone: 99999-9999
  city: Palmas - TO
  gender: M

Customer Constructors
  Customer()
  Customer(String, char, String, City)

Customer Methods
  String getName()
  Customer of(Customer)
  void setName(String)
  Long getId()
  void setGender(char)
  String getPhone()
  void setPhone(String)
  City getCity()
  void setCity(City)
  char getGender()
  boolean isMale()
  boolean isFemale()
  void setId(Long)
```

## Requirements

The project was build using JDK 17.

## Running

Open the project on you IDE and run the [Main.java](src/main/java/com/manoelcampos/reflection/Main.java) class.