package com.company;

/**
 * <h1>User</h1>
 * <b>Note:</b>
 * This is the user
 */

public class DungeonMaster extends HolyEntity {


    /**
     *<h1>User</h1>
     * Creating a "<i>DungeonMaster</i> "/user by typing first name and last name when created/instanced
     * <p>
     * Extends HolyEntity due to both <i>Hero</i> class and <i>DungeonMaster</i> class has same variables
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
