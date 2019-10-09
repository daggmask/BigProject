package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Hero extends Entity implements Serializable {
    private int level;
    private ArrayList<Equipment> equipment;
    private String title;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int health;
    private int mana;

    Hero(String monsterAffix, String monsterName) {
        super(monsterAffix, monsterName);
        this.title = " Hero ";
        generateStats();
        recruitedMonster();
    }

    @Override
    public void recruitedMonster() {
        System.out.println("Hero has joined the simulation");
    }
    private void generateStats(){
        Random rand = new Random();
        this.strength = level + rand.nextInt((10 - 1)+1)+1;
        if (this.strength < 10){
            this.strength = 10;
        }
        this.dexterity = level + rand.nextInt((10 - 1)+1)+1;
        if (this.dexterity < 10){
            this.dexterity = 10;
        }
        this.intelligence = level + rand.nextInt((10 - 1)+1)+1;
        if (this.intelligence < 10){
            this.intelligence = 10;
        }
        this.health = (int) Math.round(strength * (level * 1.5));
        this.mana = (int) Math.round(intelligence * (level * 1.2));
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


}
