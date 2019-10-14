package com.company;

public class Menus {

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
            return null;
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
            return null;
        }

    }
    enum SortMenu{

    }
}
