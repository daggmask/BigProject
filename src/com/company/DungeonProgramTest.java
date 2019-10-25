package com.company;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DungeonProgramTest {

    @org.junit.jupiter.api.Test
    void showLoot() {
    }

    @org.junit.jupiter.api.Test
    void sortMonsters() {
        ArrayList<Monsters> monsters = new ArrayList<>();
        for (Monsters monster : monsters) {
            if (monster != null) {
                System.out.println(monster.getTitle() + monster.getMonsterType());
            } else {
                System.out.println("There's no monsters in the dungeon");
            }
        }
    }

    @org.junit.jupiter.api.Test
    void help() {
        ArrayList<LootOrTreasures.Treasure> lootOrTreasures = new ArrayList<>();
        int dungeonTreasureValue = 0;
        for (LootOrTreasures.Treasure lootOrTreasure : lootOrTreasures) {
            System.out.println(lootOrTreasure + " with value: " + lootOrTreasure);
            dungeonTreasureValue += lootOrTreasure.lootValue;
        }
        System.out.println("Total dungeon treasure value: " + dungeonTreasureValue);
    }

    @org.junit.jupiter.api.Test
    void addFeralMonsters() {
    }

    @org.junit.jupiter.api.Test
    void addPresetLootOrTreasure() {
    }
}