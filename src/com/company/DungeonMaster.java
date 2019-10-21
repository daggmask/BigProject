package com.company;

/**
 * <h1>User</h1>
 * <b>Note:</b>
 * This is the user
 */

public class DungeonMaster extends HolyEntity {


    /**
     *<h1>User</h1>
     * @param firstName first name of user
     * @param lastName last name of user
     */
    DungeonMaster(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void addedEntity() {
        System.out.println("Welcome dungeon master " + getFirstName() + " " + getLastName());
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}
