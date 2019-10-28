package com.company;

/**
 * <h1>Loot and treasure</h1>
 * Loot and treasures found in the dungeon
 */

public class LootOrTreasures {
    private String treasure;

    /**
     *
     * @param treasures generated from <i>Treasure</i> enum
     *                  Randomly selected in controller.
     */
    LootOrTreasures(String treasures){
        this.treasure = treasures;
    }

    /**
     * List of available treasures or loot found in dungeon
     */
    enum Treasure {
        SPOILS_OF_WAR1("small spoils of war", 10),
        SPOILS_OF_WAR2("medium spoils of war", 15),
        SPOILS_OF_WAR3("large spoils of war", 20),
        CORPSE1("Skeleton of a lost villager", 5),
        CORPSE2("Skeleton of a lost guard", 10),
        CORPSE3("Skeleton of a lost soldier", 15),
        CORPSE4("Skeleton of a lost lieutenant", 20),
        PILE1("Pile of bones", 3),
        PILE2("Pile of flesh",6),
        COIN1("A coin",1),
        COIN2("Pile of coins",5),
        COIN3("Bag of coins",9),
        LOST1("Lost sword",12),
        LOST2("Ancient lost sword",30),
        ;
        public String lootAndTreasure;
        public int lootValue;

        /**
         *
         * @param lootAndTreasure gives enum a description, used to print
         * @param lootValue gives enum a value, used to calculate value of treasure/loot
         */
        private Treasure(String lootAndTreasure, int lootValue){
            this.lootAndTreasure = lootAndTreasure;
            this.lootValue = lootValue;
        }
    }
    @Override
    public String toString() {
        return " loot: " + treasure;
    }
}
