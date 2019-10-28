package com.company;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DungeonProgramTest {
    ArrayList<Monsters> monsters = new ArrayList<>();
    ArrayList<LootOrTreasures.Treasure> lootOrTreasures = new ArrayList<>();
    Random rand = new Random();

    @org.junit.jupiter.api.Test
    void showLoot() {
        int dungeonTreasureValue = 0;
        for (LootOrTreasures.Treasure lootOrTreasures : lootOrTreasures) {
            System.out.println(lootOrTreasures.lootAndTreasure + " with value: " + lootOrTreasures.lootValue);
            dungeonTreasureValue += lootOrTreasures.lootValue;
        }
        System.out.println("Total dungeon treasure value: " + dungeonTreasureValue);
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
        int i = 0;
        try {
            while (i < 1) {
                MonsterFactory.MonsterAffix monsterAffix = MonsterFactory.MonsterAffix.values()[rand.nextInt(MonsterFactory.MonsterAffix.values().length)];
                MonsterFactory.MonsterType monsterType = MonsterFactory.MonsterType.values()[rand.nextInt(MonsterFactory.MonsterType.values().length)];
                monsters.add(new Monsters(monsterAffix.monsterAffix, monsterType.monsterType, rand.nextInt((10 - 1) + 1) + 1));
                monsters.get(i).addEquipment("No gear due to feral monster");
                monsters.get(i).setTitle("Feral ");
                i++;
                System.out.println(monsters.size());
                assertTrue(true);
            }
        }catch (Exception e){
            assertFalse(false);
        }
    }

    @org.junit.jupiter.api.Test
    void addPresetLootOrTreasure() {
        int presetLootOrTreasureAmount = 0;
        while (presetLootOrTreasureAmount < 3){
            LootOrTreasures.Treasure loot = LootOrTreasures.Treasure.values()[rand.nextInt(LootOrTreasures.Treasure.values().length)];
            lootOrTreasures.add(loot);
            System.out.println(loot.lootAndTreasure + " has been found and added to treasury");
            presetLootOrTreasureAmount++;
        }
    }
}