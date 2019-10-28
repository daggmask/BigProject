package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * <h1>Hero class</h1>
 * Hero class used to battle the dungeon you created
 */

public class Hero extends HolyEntity implements Serializable {
    private int level;
    private ArrayList<Equipment> equipment;
    private String title;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int health;
    private int mana;
    private double attackSpeed;
    private int damage;

    /**
     * Params defined by user upon new hero being created and built-in methods to generate fully functional hero
     * @param firstName Hero's first name
     * @param lastName Hero's last name
     * @param level Hero's level
     */
    Hero(String firstName, String lastName, int level) {
        super(firstName, lastName);
        this.level = level;
        this.title = " Hero ";
        this.equipment = new ArrayList<>();
        generateStats();
        addedEntity();
    }

    /**
     * generates stats based on level defined by user
     */
    private void generateStats(){
        Random rand = new Random();
        this.strength = level * rand.nextInt((15 - 1) + 1) + 1;
        if (this.strength < 10) {
            this.strength = 10;
        }
        this.dexterity = level * rand.nextInt((15 - 1) + 1) + 1;
        if (this.dexterity < 10) {
            this.dexterity = 10;
        }
        this.intelligence = level * rand.nextInt((15 - 1) + 1) + 1;
        if (this.intelligence < 10) {
            this.intelligence = 10;
        }
        this.health = (int) Math.round(strength * (level * 1.5));
        this.mana = (int) Math.round(intelligence * (level * 1.5));
        this.attackSpeed = (dexterity * (level * 0.1));
        this.damage = (int) Math.round((strength * attackSpeed) * 0.1);
    }

    public void addEquipment(String items) {
        Equipment gear = new Equipment(items);
        equipment.add(gear);
    }

    public int getLevel() {
        return level;
    }

    /**
     * @return prints equipment of hero
     */
    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public String getTitle() {
        return title;
    }


    public int getHealth() {
        return health;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void addedEntity() {
        System.out.println("Hero: " + getFirstName() + " " + getLastName() + " has entered the dungeon");
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " ";
    }
}
