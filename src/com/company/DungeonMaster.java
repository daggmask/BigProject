package com.company;

public class DungeonMaster extends HolyEntity {


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
