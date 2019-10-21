package com.company;

import java.io.Serializable;

/**
 * <h1>Items base</h1>
 * Base for Items
 */

public class Equipment implements Serializable {
    private String gear;

    Equipment(String gear){
        this.gear = gear;
    }

    enum Items {
        SWORD1("Common Sword", 1),
        SWORD2("Uncommon Sword", 2),
        SWORD3("Rare Sword",3),
        SWORD4("Legendary Sword",4),
        SWORD5("Mythic Sword",5),
        RING1("Common Ring",1),
        RING2("Uncommon Ring",2),
        RING3("Rare Ring",3),
        RING4("Legendary Ring",4),
        RING5("Mythic Ring",5),
        SHIELD1("Common Ring",1),
        SHIELD2("Uncommon Ring",2),
        SHIELD3("Rare Ring",3),
        SHIELD4("Legendary Ring",4),
        SHIELD5("Mythic Ring",5),
        BODY_ARMOUR("Body armour",10),
        ;
        public String item;
        public int value;

        private Items(String item, int value){
            this.item = item;
            this.value = value;
        }
    }
    public static Items equipment;

    public String getGear() {
        return gear;
    }

    public static Items getEquipment() {
        return equipment;
    }

    public String toString(){
        return "items: " + gear;
    }
}
