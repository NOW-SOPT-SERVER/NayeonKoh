package org.sopt.classes;

public class PersonBuilder {
    private String name;
    private int age;
    private String sex;

    public PersonBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder age(final int age) {
        this.age = age;
        return this;
    }

    public PersonBuilder sex(final String sex) {
        this.sex = sex;
        return this;
    }

    public Person build() {
        return new Person(this.name, this.age, this.sex);
    }
}
