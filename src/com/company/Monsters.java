package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * <h1>Monster</h1>
 * base code that builds a monster generating stats and attributes random based on level
 */


public class Monsters extends Entity implements Comparable<Monsters>, Character, Serializable {
    /**
     * level defined by user upon creation of monster
     */
    private int level;
    /**
     * Each monster has a list of equipment
     */
    private ArrayList<Equipment> equipment;
    /**
     * Title is based on monster stats
     */
    private String title;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int health;
    private int mana;
    private double attackSpeed;
    private int damage;

    /**
     *This constructor is for creating a monster object with randomized affix, type and level in the controller
     * @param monsterAffix the trait of monster, ex: defensive, aggressive, strong etc, randomly generated
     * @param monsterType the monster type, randomly generated from factory
     * @param level the level of the monster, <i>DungeonMaster</i> decides level
     */
    Monsters(String monsterAffix, String monsterType, int level) {
        super(monsterAffix, monsterType);
        this.level = level;
        this.equipment = new ArrayList<>();
        generateStats();
        recruitedMonster();
    }

    public String getTitle() {
        return title;
    }

    public int getMana() {
        return mana;
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

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String getMonsterAffix() {
        return super.getMonsterAffix();
    }

    @Override
    public String getMonsterType() {
        return super.getMonsterType();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addEquipment(String items) {
        Equipment gear = new Equipment(items);
        equipment.add(gear);
    }

    /**
     * Abstract method implemented from super class
     */
    public void recruitedMonster() {
        System.out.println(toString() + " has been added to your dungeon");
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    /**
     * SortBy enum created to make sorting easier
     */

    public enum SortBy {
        TITLE,
        AFFIX,
        LEVEL,
        STRENGTH,
        INTELLIGENCE,
        DEXTERITY,
        HEALTH,
        MANA,
        NAME,
        DAMAGE,
    }

    private static SortBy sortBy;

    /**
     *
     * @param sortBy sets sorting order
     */
    public static void setSortBy(SortBy sortBy) {
        Monsters.sortBy = sortBy;
    }

    /**
     * Generates stats bas on level after level of monster has been defined by user
     */
    public void generateStats() {
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
        if (strength > dexterity && strength > intelligence) {
            this.title = "Strong ";
        } else if (dexterity > strength && dexterity > intelligence) {
            this.title = "Fast ";
        } else if (intelligence > strength && intelligence > dexterity) {
            this.title = "Intelligent ";
        }
        if (title == null){
            this.title = "Wild";
        }
        this.health = (int) Math.round(strength * (level * 1.5));
        this.mana = (int) Math.round(intelligence * (level * 1.2));
        this.attackSpeed = (dexterity * (level * 0.1));
        this.damage = (int) Math.round((strength * attackSpeed) * 0.1);
    }

    /**
     * sorting switch case
     * @param monsters receive monsters
     * @return
     */
    @Override
    public int compareTo(Monsters monsters) {
        switch (sortBy) {
            case TITLE:
                return getTitle().compareToIgnoreCase(monsters.getTitle());
            case MANA:
                return -(mana - monsters.mana);
            case HEALTH:
                return -(health - monsters.getHealth());
            case STRENGTH:
                return -(strength - monsters.getStrength());
            case DEXTERITY:
                return -(dexterity - monsters.getDexterity());
            case INTELLIGENCE:
                return -(intelligence - monsters.getIntelligence());
            case NAME:
                return getMonsterType().compareToIgnoreCase(monsters.getMonsterType()); // Fix sort
            case AFFIX:
                return getMonsterAffix().compareToIgnoreCase(monsters.getMonsterAffix()); // Fix sort
            case LEVEL:
                return -(level - monsters.level);
            case DAMAGE:
                return -(damage - monsters.damage);
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return getMonsterAffix() + " " + getMonsterType() + " lvl: " + level;
    }
}
