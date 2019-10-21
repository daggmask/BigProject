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
    private int attackSpeed;
    private int damage;

    Hero(String firstName, String lastName, int level) {
        super(firstName, lastName);
        this.level = level;
        this.title = " Hero ";
        generateStats();
        addedEntity();
    }
    private void generateStats(){
        Random rand = new Random();
        this.strength = level + rand.nextInt((10 - 1) + 1) + 1;
        if (this.strength < 10) {
            this.strength = 10;
        }
        this.dexterity = level + rand.nextInt((10 - 1) + 1) + 1;
        if (this.dexterity < 10) {
            this.dexterity = 10;
        }
        this.intelligence = level + rand.nextInt((10 - 1) + 1) + 1;
        if (this.intelligence < 10) {
            this.intelligence = 10;
        }
        if (strength > dexterity && strength > intelligence) {
            this.title = " Strong ";
        } else if (dexterity > strength && dexterity > intelligence) {
            this.title = " Fast ";
        } else if (intelligence > strength && intelligence > dexterity) {
            this.title = " Intelligent ";
        }
        this.health = (int) Math.round(strength * (level * 1.5));
        this.mana = (int) Math.round(intelligence * (level * 1.2));
        this.attackSpeed = (int) Math.round(dexterity * (level * 0.1));
        this.damage = (int) Math.round((strength * attackSpeed) * 0.8);
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public String getTitle() {
        return title;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public void addedEntity() {
        System.out.println("Hero: " + getFirstName() + " " + getLastName() + " has entered the dungeon");
    }
}
