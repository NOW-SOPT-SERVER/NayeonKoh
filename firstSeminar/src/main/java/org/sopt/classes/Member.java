package org.sopt.classes;

public class Member extends Person {
    private Part part;

    public Member(final String name, final int age, final String sex, final Part part) {
        super(name, age, sex);
        this.part = part;
    }

}
