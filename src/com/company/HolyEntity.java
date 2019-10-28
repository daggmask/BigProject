package com.company;

/**
 * <h1>Base for hero</h1>
 * base codes for hero class
 */

public abstract class HolyEntity {
    String firstName;
    String lastName;

    /**
     *
     * @param firstName defined by user upon creation of sub-classes
     * @param lastName defined by user upon creation of sub-classes
     */
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

    /**
     * Inherited from sub-classes and has different prints based on sub-class
     */
    public abstract void addedEntity();
}
