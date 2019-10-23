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
        SWORD6("Epic Sword", 6),
        RING1("Common Ring",1),
        RING2("Uncommon Ring",2),
        RING3("Rare Ring",3),
        RING4("Legendary Ring",4),
        RING5("Mythic Ring",5),
        RING6("Epic Ring",6),
        ACCESSORY1("Common Accessory",1),
        ACCESSORY2("Uncommon Accessory",2),
        ACCESSORY3("Rare Accessory",3),
        ACCESSORY4("Legendary Accessory",4),
        ACCESSORY5("Mythic Accessory",5),
        ACCESSORY6("Epic Accessory",6),
        BODY_ARMOUR1("Common armour",1),
        BODY_ARMOUR2("Uncommon armour",2),
        BODY_ARMOUR3("Rare armour",3),
        BODY_ARMOUR4("Legendary armour",4),
        BODY_ARMOUR5("Mythic armour",5),
        BODY_ARMOUR6("Epic armour",6),
        SHIELD1("Common Shield", 1),
        SHIELD2("Uncommon Shield", 2),
        SHIELD3("Rare Shield", 3),
        SHIELD4("Legendary Shield", 4),
        SHIELD5("Mythic Shield",5),
        SHIELD6("Epic Shield",6),
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
