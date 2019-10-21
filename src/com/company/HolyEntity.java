package com.company;

/**
 * <h1>Base for hero</h1>
 * base codes for hero class
 */

public abstract class HolyEntity {
    String firstName;
    String lastName;

    HolyEntity(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public abstract void addedEntity();
}
