package com.company;

/**
 * <h1>Loot and treasure</h1>
 * Loot and treasures found in the dungeon
 */

public class LootOrTreasures {
   private String lootOrTreasure;
   private String rarity;

    LootOrTreasures(String lootOrTreasure, String rarity){
        this.lootOrTreasure = lootOrTreasure;
        this.rarity = rarity;
    }

    public String getLootOrTreasure() {
        return lootOrTreasure;
    }

    public String getRarity() {
        return rarity;
    }
}
