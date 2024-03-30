package org.sopt;

import org.sopt.classes.Person;
import org.sopt.classes.PersonBuilder;

public class Main {
    public static void main(String[] args) {
        Person.run();

        Person personWithBuilder = new PersonBuilder()
                .name("고나연")
                .age(24)
                .sex("female")
                .build();

        personWithBuilder.walk();
        System.out.println(personWithBuilder.getName());

        personWithBuilder.setName("고나연2");
        System.out.println(personWithBuilder.getName());
    }
}
