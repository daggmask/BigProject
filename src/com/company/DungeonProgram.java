package com.company;

import java.nio.file.StandardOpenOption;
import java.util.*;

import static java.lang.Thread.sleep;

public class DungeonProgram {
    private Scanner scan = new Scanner(System.in);
    private ArrayList<DungeonMaster> dungeonMasters = new ArrayList<>();
    private ArrayList<LootOrTreasures> lootOrTreasures = new ArrayList<>();
    private ArrayList<Monsters> monsters = new ArrayList<>();
    private Hero hero;
    private final static int MAX_MONSTERS = 30;
    private final static int MAX_LOOT = 20;
    private final static int MAX_EQUIPMENT = 20;

    DungeonProgram() {
        System.out.println("Do you want to load previous monsters from portal(Load)");
        System.out.println("1. Yes, load");
        System.out.println("2. No, create new dungeon");
        int loadOrNewDungeon = scan.nextInt();
        if (loadOrNewDungeon == 1) {
            if (FileUtils.loadObject("monstersSaveFile.ser") != null) {
                ArrayList<Monsters> savedMonsters = (ArrayList<Monsters>) FileUtils.loadObject("monstersSaveFile.ser");
                System.out.println("Loaded monsters from the portal:");
                if (savedMonsters != null) {
                    for (Monsters monster : savedMonsters) {
                        monsters.add(monster);
                        System.out.println(monster);
                    }
                    System.out.println("Your monsters are leaving the portal and have entered your dungeon");
                }
            } else {
                System.out.println("There are no monsters saved in the portal");
                System.out.println("Adding feral monsters: ");
                addFeralMonsters();
            }
        } else if (loadOrNewDungeon == 2) {
            System.out.println("Some monsters are already living in the dungeon, they are being added to your list of monsters");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            addFeralMonsters();
        }
    }

    public void MainMenu(String dungeonName) {
        System.out.println("Welcome to the dungeon: " + dungeonName);
        try {
            sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Please enter your full name: ");
        dungeonMasters.add(new DungeonMaster(tryCatchString(), tryCatchString()));
        try {
            sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int menu;
        do {
            System.out.println("----------------------------------------------");
            System.out.println("Enter your desired option");
            System.out.println("Monster section:");
            System.out.println("1. Add monster to your dungeon");
            System.out.println("2. Add loot to your dungeon");
            System.out.println("3. Remove monster from your dungeon");
            System.out.println("4. Remove loot from the dungeon");
            System.out.println("5. Show all monsters in the dungeon");
            System.out.println("6. Show all loot in the dungeon");
            System.out.println("7. Show monsters sorted");
            System.out.println("8. Save or load monsters");
            System.out.println("9. Help");
            System.out.println("10. Enter dungeon as hero");
            System.out.println("11. Exit");
            System.out.println("----------------------------------------------");
            menu = tryCatchInt();
            switch (menu) {
                case 1:
                    addMonster();
                    break;
                case 2:
                    addLoot();
                    break;
                case 3:
                    removeMonsters();
                    break;
                case 4:
                    removeLoot();
                    break;
                case 5:
                    showMonsters();
                    break;
                case 6:
                    showLoot();
                    break;
                case 7:
                    sortMonsters();
                    break;
                case 8:
                    saveOrLoad();
                    break;
                case 9:
                    help();
                    break;
                case 10:
                    break;
                case 11:
                    exit();
                    menu = 0;
                    break;
                default:
                    System.out.println("Dungeon master, this option does not exist. Please enter existing options");
            }
        } while (menu != 0);

    }

    private void addMonster() {
        int itemCount = 1;
        if (monsters.size() < MAX_MONSTERS) {
            System.out.println("Enter the affix, name and level of the monster");
            System.out.println("For example: Furious Goblin 13");
            Monsters monster = new Monsters(tryCatchString(), tryCatchString(), tryCatchInt());
            System.out.println("How many items do you want the monster to have?");
            int amountOfItems = tryCatchInt();
            if (amountOfItems > 0) {
                for (int i = 0; i < amountOfItems && i < MAX_EQUIPMENT; i++) {
                    System.out.println("Item number " + itemCount++ + ":");
                    monster.addEquipment(tryCatchString());
                }
            } else {
                System.out.println("No items added to " + monster.getMonsterName());
            }
            if (amountOfItems > MAX_EQUIPMENT) {
                System.out.println("You can't have that many items on the monster");
            }
            monsters.add(monster);
        } else {
            System.out.println("You've reached max amount of monster manageable in the dungeon");
        }
    }

    private void addLoot() {
        if (lootOrTreasures.size() < MAX_LOOT) {
            System.out.println("Enter the treasure/loot you want to add to the dungeon");
            System.out.println("For example: Legendary sword");
            lootOrTreasures.add(new LootOrTreasures(tryCatchString(), tryCatchString()));
        }
    }

    private void removeMonsters() {
        System.out.println("Enter the monster's position that you want to remove");
        System.out.println("Note: first monster's position is 0");
        int remove = tryCatchInt();
        if (monsters.get(remove) != null) {
            System.out.println("Monster: "
                    + monsters.get(remove).getMonsterAffix() + " "
                    + monsters.get(remove).getMonsterName() + " "
                    + monsters.get(remove).getLevel() + " has been removed");
            monsters.remove(remove);
        } else
            System.out.println("Monster doesn't exist");
    }

    private void removeLoot() {
        System.out.println("Enter the loot position that you want to remove");
        System.out.println("Note: first loot position is 0");
        int remove = tryCatchInt();
        if (lootOrTreasures.get(remove) != null) {
            System.out.println("Loot: "
                    + lootOrTreasures.get(remove).getLootOrTreasure() + " "
                    + lootOrTreasures.get(remove).getRarity() + " has been removed");
            lootOrTreasures.remove(remove);
        } else
            System.out.println("Loot doesn't exist");
    }

    private void showMonsters() {
        System.out.println("Do you want to see only the monster's names or level too, or affixes too?");
        System.out.println("1. Only titles and names");
        System.out.println("2. Only level");
        System.out.println("3. Only affix");
        System.out.println("4. All");
        System.out.println("5. Show monster equipment");
        System.out.println("6. Show monster stats");
        int menu2 = tryCatchInt();
        switch (menu2) {
            case 1:
                for (Monsters monsters : monsters) {
                    if (monsters != null) {
                        System.out.println(monsters.getTitle() + monsters.getMonsterName());
                    } else {
                        System.out.println("There's no monsters in the dungeon");
                    }
                }
                break;
            case 2:
                float averageLevel = 0.0f;
                int amountOfMonsters = 0;
                for (Monsters monsters : monsters) {
                    if (monsters != null) {
                        System.out.println(monsters.getMonsterName() + " " + monsters.getLevel());
                        amountOfMonsters++;
                        averageLevel = averageLevel + monsters.getLevel();
                    } else {
                        System.out.println("There's no monsters in the dungeon");
                    }
                }
                float averageSum = (averageLevel / (float) amountOfMonsters);
                System.out.println("The average level of the monsters are: " + averageSum);
                break;
            case 3:
                for (Monsters monsters : monsters) {
                    if (monsters != null) {
                        System.out.println(monsters.getMonsterAffix() + " " + monsters.getMonsterName());
                    } else {
                        System.out.println("There's no monsters in the dungeon");
                    }
                }
                break;
            case 4:
                for (Monsters monsters : monsters) {
                    if (monsters != null) {
                        System.out.println( monsters.getTitle() + monsters.getMonsterAffix() + " " + monsters.getMonsterName() + " " + monsters.getLevel());
                    } else {
                        System.out.println("There's no monsters in the dungeon");
                    }
                }
                break;
            case 5:
                System.out.println("Enter monster's position that you want to view");
                System.out.println("Note: first monster's position is 0");
                int monsterIndex = tryCatchInt();
                for (Monsters monster : monsters) {
                    if (monster != null) {
                        if (monster == monsters.get(monsterIndex)) {
                            System.out.println("Showing: " + monster.getMonsterAffix() + " " + monster.getMonsterName() + " " + monster.getLevel());
                            for (Equipment items : monsters.get(monsterIndex).getEquipment()) {
                                if (items.getGear() != null)
                                    System.out.println(items.getGear());
                                else
                                    System.out.println(monster.getMonsterAffix() + " " + monster.getMonsterName() + " has no gear");
                            }
                        }
                    }
                }
                break;
            case 6:
                System.out.println("Enter monster's position that you want to view");
                System.out.println("Note: first monster's position is 0");
                monsterIndex = tryCatchInt();
                for (Monsters monster : monsters) {
                    if (monster != null) {
                        if (monster == monsters.get(monsterIndex)) {
                            System.out.println("Showing: "+ monster.getTitle() + monster.getMonsterAffix() + " " + monster.getMonsterName() + " " + monster.getLevel());
                            System.out.println("Strength: " + monster.getStrength());
                            System.out.println("Dexterity: " + monster.getDexterity());
                            System.out.println("Intelligence: " + monster.getIntelligence());
                            System.out.println("Health: " + monster.getHealth());
                            System.out.println("Mana: " +  monster.getMana());
                        }
                    }
                }
                break;
            default:
                System.out.println("This option doesn't exist");
                break;
        }
    }

    private void showLoot() {
        for (LootOrTreasures lootOrTreasures : lootOrTreasures) {
            System.out.println(lootOrTreasures.getLootOrTreasure() + " " + lootOrTreasures.getRarity());
        }
    }
    private void sortMonsters(){
        int menu = 0;
        System.out.println("Which aspect do you want to sort the monsters by?");
        System.out.println("1. Title");
        System.out.println("2. Affix");
        System.out.println("3. Name");
        System.out.println("4. Level");
        System.out.println("5. Strength");
        System.out.println("6. Dexterity");
        System.out.println("7. Intelligence");
        System.out.println("8. Health");
        System.out.println("9. Mana");
        menu = scan.nextInt();
        switch (menu){
            case 1:
                Monsters.setSortBy(Monsters.SortBy.TITLE);
                Collections.sort(monsters);
                for (Monsters monsters: monsters){
                    if (monsters.getTitle() != null)
                    System.out.println(monsters.getTitle() +  monsters.getMonsterName() + monsters.getLevel());
                    else
                        System.out.println("Feral " + monsters.getMonsterName() + monsters.getLevel());
                }
                break;
            case 2:
                Monsters.setSortBy(Monsters.SortBy.AFFIX);
                Collections.sort(monsters);
                for (Monsters monsters: monsters){
                    if (monsters != null)
                        System.out.println(monsters.getMonsterAffix() + monsters.getMonsterName() + monsters.getLevel());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            case 3:
                Monsters.setSortBy(Monsters.SortBy.NAME);
                Collections.sort(monsters);
                for (Monsters monsters: monsters){
                    if (monsters != null)
                        System.out.println(monsters.getMonsterAffix() + monsters.getMonsterName());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            case 4:
                Monsters.setSortBy(Monsters.SortBy.LEVEL);
                Collections.sort(monsters);
                for (Monsters monsters: monsters){
                    if (monsters != null)
                        System.out.println(monsters.getMonsterAffix() + monsters.getMonsterName() + monsters.getLevel());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            case 5:
                Monsters.setSortBy(Monsters.SortBy.STRENGTH);
                Collections.sort(monsters);
                for (Monsters monsters: monsters){
                    if (monsters != null)
                        System.out.println(monsters + " strength: " + monsters.getStrength());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            case 6:
                Monsters.setSortBy(Monsters.SortBy.DEXTERITY);
                Collections.sort(monsters);
                for (Monsters monsters: monsters){
                    if (monsters != null)
                        System.out.println(monsters + " dexterity: " + monsters.getDexterity());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            case 7:
                Monsters.setSortBy(Monsters.SortBy.INTELLIGENCE);
                Collections.sort(monsters);
                for (Monsters monsters: monsters){
                    if (monsters != null)
                        System.out.println(monsters + " intelligence: " + monsters.getIntelligence());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            case 8:
                Monsters.setSortBy(Monsters.SortBy.HEALTH);
                Collections.sort(monsters);
                for (Monsters monsters: monsters){
                    if (monsters != null)
                        System.out.println(monsters + " health: " + monsters.getHealth());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            case 9:
                Monsters.setSortBy(Monsters.SortBy.MANA);
                Collections.sort(monsters);
                for (Monsters monsters: monsters){
                    if (monsters != null)
                        System.out.println(monsters + " mana: " + monsters.getMana());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            default:
                System.out.println("This sorting choice doesn't exist");
                break;
        }
    }

    private void saveOrLoad() {
        ArrayList<Monsters> monsterSaveOrLoad = new ArrayList<>(monsters);
        System.out.println("1. Save your monsters in a portal");
        System.out.println("2. Load saved monsters from portal");
        int menu3 = scan.nextInt();
        switch (menu3) {
            case 1:
                FileUtils.saveObject(monsterSaveOrLoad, "monstersSaveFile.ser", StandardOpenOption.CREATE);
                System.out.println("Saved monsters in portal: ");
                for (Monsters monsters : monsterSaveOrLoad) {
                    if (monsters != null)
                        System.out.println(monsters);
                    else
                        System.out.println("No monsters saved");
                }
                System.out.println("Your monsters are saved in the portal");
                break;
            case 2:
                if (FileUtils.loadObject("monstersSaveFile.ser") != null) {
                    ArrayList<Monsters> savedMonsters = (ArrayList<Monsters>) FileUtils.loadObject("monstersSaveFile.ser");
                    System.out.println("Loaded monsters from the portal:");
                    if (savedMonsters != null) {
                        for (Monsters monster : savedMonsters) {
                            monsters.add(monster);
                            System.out.println(monster);
                        }
                    }
                    else{
                        System.out.println("No monsters loaded from portal");
                    }
                    System.out.println("Your monsters are leaving the portal and have entered your dungeon");
                } else {
                    System.out.println("There are no monsters saved in the portal");
                    System.out.println("You have to save monsters in the portal to be able to load them into your dungeon");
                }
                break;
            default:
                for (DungeonMaster dungeonMaster : dungeonMasters) {
                    if (dungeonMaster != null)
                        System.out.println("This option doesn't exist, " + dungeonMaster.getMonsterAffix() + dungeonMaster.getMonsterName());
                    else
                        System.out.println("This option doesn't exist, dungeon master");
                }
                break;
        }
    }

    private void help() {
        System.out.println("Option 1 adds a monster to the dungeon");
        System.out.println("Option 2 adds loot to the dungeon");
        System.out.println("Option 3 removes a monster from the dungeon");
        System.out.println("Option 4 removes loot from the dungeon");
        System.out.println("Option 5 shows monsters from chosen list option");
        System.out.println("Option 6 shows all the loot in the dungeon");
        System.out.println("Option 7 sort monsters by their aspects");
        System.out.println("Option 8 save or load monsters ");
        System.out.println("Option 9 menu tool tip ");
        System.out.println("Option 10 battle the dungeon ");
        System.out.println("Option 11 exit ");
    }

    private void enterDungeon(){
        System.out.println("Enter hero level: ");
        hero = new Hero("Awesome", "Hero",tryCatchInt());
        while (hero.getHealth() > 0){
            
        }
    }

    private void exit() {
        for (DungeonMaster dungeonMaster : dungeonMasters) {
            if (dungeonMaster != null)
                System.out.println("Thanks for playing " + dungeonMaster.getMonsterAffix() + " " + dungeonMaster.getMonsterName());
            else
                System.out.println("Thanks for playing");
        }
    }

    private void addFeralMonsters(){
        monsters.add(new Monsters(" Furious", " Minotaur ", 13));
        monsters.get(0).addEquipment("No gear due to feral monster");
        monsters.get(0).setTitle("Feral ");
        monsters.add(new Monsters(" Weak", " Goblin ", 4));
        monsters.get(1).addEquipment("No gear due to feral monster");
        monsters.get(1).setTitle("Feral ");
        monsters.add(new Monsters(" Fragile", " Spider ", 3));
        monsters.get(2).addEquipment("No gear due to feral monster");
        monsters.get(2).setTitle("Feral ");
        monsters.add(new Monsters(" Defensive", " Skeleton ", 5));
        monsters.get(3).addEquipment("No gear due to feral monster");
        monsters.get(3).setTitle("Feral ");
    }

    private int tryCatchInt() {
        int number;
        while (true) {
            try {
                number = scan.nextInt();
                return number;
            } catch (Exception e) {
                System.out.println("Wrong input, enter number(s)");
                scan.nextLine();
            }
        }
    }

    private String tryCatchString() {
        String text;
        while (true) {
            try {
                text = scan.next();
                return text;
            } catch (Exception e) {
                System.out.println("Wrong input, enter text");
                scan.nextLine();
            }
        }
    }
}
