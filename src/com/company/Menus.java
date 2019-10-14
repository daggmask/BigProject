package com.company;

import java.security.PublicKey;

public class Menus {

    private static MainMenu mainMenu;
    private static ShowMenu showMenu;
    private static SortMenu sortMenu;

    public static MainMenu getMainMenu() {
        return mainMenu;
    }

    public static ShowMenu getShowMenu() {
        return showMenu;
    }

    public static SortMenu getSortMenu() {
        return sortMenu;
    }

    enum MainMenu implements HasDescription{
        ADD_MONSTER("Add monster"),
        ADD_LOOT("Add loot/treasure"),
        REMOVE_MONSTER("Remove monster"),
        REMOVE_LOOT("Remove loot/treasure"),
        SHOW_MONSTERS("Show monsters"),
        SHOW_LOOT("Show loot"),
        SORT_MONSTERS("Sort monsters"),
        SAVE_OR_LOAD("Save or load"),
        HELP("Help"),
        HERO_SIMULATION("Generate hero and battle your dungeon"),
        EXIT("Exit");

        public String description;

        MainMenu(String description){
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }
    enum ShowMenu implements HasDescription{
        TITLES_AND_NAMES("Show only titles and names"),
        LEVEL("Show only levels"),
        AFFIX("Show only affixes"),
        ALL("Show all"),
        SHOW_EQUIPMENT("Show monster's equipment"),
        STATS("Show monster's stats");

        public String description;

        ShowMenu(String description){
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }

    }
    enum SortMenu implements HasDescription{
        TITLE("Title"),
        AFFIX("Affix"),
        NAME("Name"),
        LEVEL("Level"),
        STRENGTH("Strength"),
        DEXTERITY("Dexterity"),
        INTELLIGENCE("Intelligence"),
        HEALTH("Health"),
        MANA("Mana");

        public String description;

        private SortMenu(String description){
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }
}