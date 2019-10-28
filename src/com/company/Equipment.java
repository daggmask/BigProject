package com.company;

import java.io.Serializable;

/**
 * <h1>Items base</h1>
 * Base for Items
 */

public class Equipment implements Serializable {
    private String gear;

    /**
     *
     * @param gear gear generated from Items enum
     */
    Equipment(String gear){
        this.gear = gear;
    }

    /**
     * list of items
     */
    enum Items {
        SWORD1("Common Sword", 10),
        SWORD2("Uncommon Sword", 20),
        SWORD3("Rare Sword",30),
        SWORD4("Legendary Sword",40),
        SWORD5("Mythic Sword",50),
        SWORD6("Epic Sword", 60),
        RING1("Common Ring",10),
        RING2("Uncommon Ring",20),
        RING3("Rare Ring",30),
        RING4("Legendary Ring",40),
        RING5("Mythic Ring",50),
        RING6("Epic Ring",60),
        ACCESSORY1("Common Accessory",10),
        ACCESSORY2("Uncommon Accessory",20),
        ACCESSORY3("Rare Accessory",30),
        ACCESSORY4("Legendary Accessory",40),
        ACCESSORY5("Mythic Accessory",50),
        ACCESSORY6("Epic Accessory",60),
        BODY_ARMOUR1("Common armour",10),
        BODY_ARMOUR2("Uncommon armour",20),
        BODY_ARMOUR3("Rare armour",30),
        BODY_ARMOUR4("Legendary armour",40),
        BODY_ARMOUR5("Mythic armour",50),
        BODY_ARMOUR6("Epic armour",60),
        SHIELD1("Common Shield", 10),
        SHIELD2("Uncommon Shield", 20),
        SHIELD3("Rare Shield", 30),
        SHIELD4("Legendary Shield", 40),
        SHIELD5("Mythic Shield",50),
        SHIELD6("Epic Shield",60),
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


    public String toString(){
        return "items: " + gear;
    }
}
