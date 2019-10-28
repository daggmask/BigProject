package com.company;

import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * <h1>Main program</h1>
 * This is the main menu for <i>Dungeon simulator</i> containing different menus and options
 * <p>
 * <b>Note:</b> this version is without hero implemented into the dungeon, thus no battle system is available in this version
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 2019-10-21
 */
public class DungeonProgram {
    private Scanner scan = new Scanner(System.in);
    private Random rand = new Random();
    private ArrayList<DungeonMaster> dungeonMasters = new ArrayList<>();
    private ArrayList<LootOrTreasures.Treasure> lootOrTreasures = new ArrayList<>();
    private ArrayList<Monsters> monsters = new ArrayList<>();
    private Hero hero;
    private final static int MAX_MONSTERS = 30;
    private final static int MAX_LOOT = 20;
    private final static int MIN_ITEMS = 10;

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
                    loadingTime(2000);
                }
            } else {
                System.out.println("There are no monsters saved in the portal");
                loadingTime(2000);
                System.out.println("Adding feral monsters: ");
                addFeralMonsters();
            }
        } else if (loadOrNewDungeon == 2) {
            System.out.println("Some monsters are already living in the dungeon, they are being added to your list of monsters");
            System.out.print("Generating random monsters");
            loadDots();
            addFeralMonsters();
            System.out.println();
        }
        loadingTime(1000);
        System.out.println();
        System.out.println("We've found already existing treasures in the dungeon");
        loadingTime(1000);
        addPresetLootOrTreasure();

    }

    /**
     * <h1>Method for main menu</h1>
     * This is the main menu for dungeon
     * <b>note:</b> if user doesn't have save file or wants to create new dungeon, new monster will be generated
     *
     * @param dungeonName Name of dungeon
     */
    public void MainMenu(String dungeonName) {
        System.out.println("Welcome to the dungeon: " + dungeonName);
        loadingTime(1000);
        System.out.println("Please enter your full name: ");
        dungeonMasters.add(new DungeonMaster(tryCatchString(), tryCatchString()));
        System.out.print("Loading");
        loadDots();
        int menu = 1;
        do {
            System.out.println("----------------------------------------------");
            System.out.println("Enter your desired option");
            System.out.println("Monster section:");
            try {
                switch (printMenuAndGetChoice(Menu.MainMenu.values())) {
                    case ADD_MONSTER:
                        addMonster();
                        break;
                    case ADD_LOOT:
                        addLoot();
                        break;
                    case REMOVE_MONSTER:
                        removeMonsters();
                        break;
                    case REMOVE_LOOT:
                        removeLoot();
                        break;
                    case SHOW_MONSTERS:
                        showMonsters();
                        break;
                    case SHOW_LOOT:
                        showLoot();
                        break;
                    case SORT_MONSTERS:
                        sortMonsters();
                        break;
                    case SAVE_OR_LOAD:
                        saveOrLoad();
                        break;
                    case HELP:
                        help();
                        break;
                    case HERO_SIMULATION:
                        enterDungeon();
                        break;
                    case EXIT:
                        exit();
                        menu = 0;
                        break;
                    default:
                        System.out.println("Dungeon master, this option does not exist. Please enter existing options");
                }
            }catch (Exception e){
                System.out.println("Error");
            }
        } while (menu != 0);

    }

    /**
     * <h1>Add monster to dungeon</h1>
     */
    public void addMonster() {
        int itemCount = 1;
        int itemValue = 0;
        if (monsters.size() < MAX_MONSTERS) {
            System.out.println("Enter the  level of the monster");
            MonsterFactory.MonsterAffix monsterAffix = MonsterFactory.MonsterAffix.values()[rand.nextInt(MonsterFactory.MonsterAffix.values().length)];
            MonsterFactory.MonsterType monsterType = MonsterFactory.MonsterType.values()[rand.nextInt(MonsterFactory.MonsterType.values().length)];
            Monsters monster = new Monsters(monsterAffix.monsterAffix, monsterType.monsterType, tryCatchInt());
            loadingTime(1000);
            System.out.print("Generating random items"); //Add random items
            loadDots();
            while (itemCount < MIN_ITEMS) {
                Equipment.Items newItem = Equipment.Items.values()[rand.nextInt(Equipment.Items.values().length)];
                monster.addEquipment(newItem.item);
                itemCount++;
                itemValue += newItem.value;
            }
            System.out.println(monster + " was given: ");
            for (Equipment item : monster.getEquipment()) {
                System.out.println(item.getGear());
                loadingTime(1000);
            }
            System.out.println(monster + " got item value: " + itemValue);
            System.out.print("Calculating monster stats based on item value");
            loadDots();
            monster.setDamage(monster.getDamage() + itemValue);
            monster.setHealth(monster.getHealth() + itemValue);
            System.out.println("Monster added to dungeon");
            monsters.add(monster);
        } else {
            System.out.println("You've reached max amount of monster manageable in the dungeon");
        }
    }

    private void addLoot() {
        if (lootOrTreasures.size() < MAX_LOOT) {
            LootOrTreasures.Treasure loot = LootOrTreasures.Treasure.values()[rand.nextInt(LootOrTreasures.Treasure.values().length)];
            lootOrTreasures.add(loot);
            System.out.println(loot.lootAndTreasure + " has been added");
            loadingTime(1000);
        }
        else
            System.out.println("Max loot/treasure capacity reached in the dungeon");
    }

    private void removeMonsters() {
        System.out.println("Enter the monster's position that you want to remove");
        printMonsterArray();
        int remove = tryCatchInt()-1;
        if (monsters.get(remove) != null) {
            System.out.println("Monster: "
                    + monsters.get(remove).getMonsterAffix() + " "
                    + monsters.get(remove).getMonsterType() + " "
                    + monsters.get(remove).getLevel() + " has been removed");
            monsters.remove(remove);
        } else
            System.out.println("Monster doesn't exist");
    }

    private void removeLoot() {
        System.out.println("Enter the loot position that you want to remove");
        int remove = tryCatchInt() -1;
        if (lootOrTreasures.get((remove)) != null) {
            System.out.println("Loot: "
                    + lootOrTreasures.get(remove).lootAndTreasure + " with value: " + lootOrTreasures.get(remove).lootValue+ " has been removed");
            lootOrTreasures.remove(remove);
        } else
            System.out.println("Loot doesn't exist");
    }

    public void showMonsters() {
        System.out.println("Do you want to see only the monster's names or level too, or affixes too?");
        switch (printMenuAndGetChoice(Menu.ShowMenu.values())) {
            case TITLES_AND_NAMES:
                for (Monsters monsters : monsters) {
                    if (monsters != null) {
                        System.out.println(monsters.getTitle() + monsters.getMonsterType());
                    } else {
                        System.out.println("There's no monsters in the dungeon");
                    }
                }
                break;
            case LEVEL:
                float averageLevel = 0.0f;
                int amountOfMonsters = 0;
                for (Monsters monsters : monsters) {
                    if (monsters != null) {
                        System.out.println(monsters.getMonsterType() + " " + monsters.getLevel());
                        amountOfMonsters++;
                        averageLevel = averageLevel + monsters.getLevel();
                    } else {
                        System.out.println("There's no monsters in the dungeon");
                    }
                }
                float averageSum = (averageLevel / (float) amountOfMonsters);
                System.out.println("The average level of the monsters are: " + averageSum);
                break;
            case AFFIX:
                for (Monsters monsters : monsters) {
                    if (monsters != null) {
                        System.out.println(monsters.getMonsterAffix() + " " + monsters.getMonsterType());
                    } else {
                        System.out.println("There's no monsters in the dungeon");
                    }
                }
                break;
            case ALL:
                for (Monsters monsters : monsters) {
                    if (monsters != null) {
                        System.out.println(monsters.getTitle() + monsters.getMonsterAffix() + " " + monsters.getMonsterType() + " " + monsters.getLevel());
                    } else {
                        System.out.println("There's no monsters in the dungeon");
                    }
                }
                break;
            case SHOW_EQUIPMENT:
                System.out.println("Enter monster's position that you want to view");
                printMonsterArray();
                int monsterIndex = tryCatchInt() - 1;
                for (Monsters monster : monsters) {
                    if (monster != null) {
                        if (monster == monsters.get(monsterIndex)) {
                            System.out.println("Showing: " + monster.getMonsterAffix() + " " + monster.getMonsterType() + " " + monster.getLevel());
                            for (Equipment items : monsters.get(monsterIndex).getEquipment()) {
                                if (items.getGear() != null)
                                    System.out.println(items.getGear());
                                else
                                    System.out.println(monster.getMonsterAffix() + " " + monster.getMonsterType() + " has no gear");
                            }
                        }
                    }
                }
                break;
            case STATS:
                System.out.println("Enter monster's position that you want to view");
                printMonsterArray();
                monsterIndex = tryCatchInt() - 1;
                for (Monsters monster : monsters) {
                    if (monster != null) {
                        if (monster == monsters.get(monsterIndex)) {
                            System.out.println("Showing: " + monster.getTitle() + monster.getMonsterAffix() + " " + monster.getMonsterType() + " " + monster.getLevel());
                            System.out.println("Strength: " + monster.getStrength());
                            System.out.println("Dexterity: " + monster.getDexterity());
                            System.out.println("Intelligence: " + monster.getIntelligence());
                            System.out.println("Damage: " + monster.getDamage());
                            System.out.println("Health: " + monster.getHealth());
                            System.out.println("Mana: " + monster.getMana());
                        }
                    }
                }
                break;
            default:
                System.out.println("This option doesn't exist");
                break;
        }
        loadingTime(3000);
    }

    public void showLoot() {
        int dungeonTreasureValue = 0;
        for (LootOrTreasures.Treasure lootOrTreasures : lootOrTreasures) {
            System.out.println(lootOrTreasures.lootAndTreasure + " with value: " + lootOrTreasures.lootValue);
            dungeonTreasureValue += lootOrTreasures.lootValue;
        }
        System.out.println("Total dungeon treasure value: " + dungeonTreasureValue);
        loadingTime(3000);
    }

    public void sortMonsters() {
        System.out.println("Which aspect do you want to sort the monsters by?");
        switch (printMenuAndGetChoice(Menu.SortMenu.values())) {
            case TITLE:
                Monsters.setSortBy(Monsters.SortBy.TITLE);
                Collections.sort(monsters);
                for (Monsters monsters : monsters) {
                    if (monsters.getTitle() != null)
                        System.out.println(monsters.getTitle() + " " + monsters.getMonsterType() + " " + monsters.getLevel());
                    else
                        System.out.println("Feral " + monsters.getMonsterType() + monsters.getLevel());
                }
                break;
            case AFFIX:
                Monsters.setSortBy(Monsters.SortBy.AFFIX);
                Collections.sort(monsters);
                printMonsterArray();
                break;
            case NAME:
                Monsters.setSortBy(Monsters.SortBy.NAME);
                Collections.sort(monsters);
                printMonsterArray();
                break;
            case LEVEL:
                Monsters.setSortBy(Monsters.SortBy.LEVEL);
                Collections.sort(monsters);
                printMonsterArray();
                break;
            case STRENGTH:
                Monsters.setSortBy(Monsters.SortBy.STRENGTH);
                Collections.sort(monsters);
                for (Monsters monsters : monsters) {
                    if (monsters != null)
                        System.out.println(monsters + " strength: " + monsters.getStrength());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            case DEXTERITY:
                Monsters.setSortBy(Monsters.SortBy.DEXTERITY);
                Collections.sort(monsters);
                for (Monsters monsters : monsters) {
                    if (monsters != null)
                        System.out.println(monsters + " dexterity: " + monsters.getDexterity());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            case INTELLIGENCE:
                Monsters.setSortBy(Monsters.SortBy.INTELLIGENCE);
                Collections.sort(monsters);
                for (Monsters monsters : monsters) {
                    if (monsters != null)
                        System.out.println(monsters + " intelligence: " + monsters.getIntelligence());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            case HEALTH:
                Monsters.setSortBy(Monsters.SortBy.HEALTH);
                Collections.sort(monsters);
                for (Monsters monsters : monsters) {
                    if (monsters != null)
                        System.out.println(monsters + " health: " + monsters.getHealth());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            case MANA:
                Monsters.setSortBy(Monsters.SortBy.MANA);
                Collections.sort(monsters);
                for (Monsters monsters : monsters) {
                    if (monsters != null)
                        System.out.println(monsters + " mana: " + monsters.getMana());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            case DAMAGE:
                Monsters.setSortBy(Monsters.SortBy.DAMAGE);
                Collections.sort(monsters);
                for (Monsters monsters : monsters) {
                    if (monsters != null)
                        System.out.println(monsters + " damage: " + monsters.getDamage());
                    else
                        System.out.println("Monsters doesn't exist");
                }
                break;
            default:
                System.out.println("This sorting choice doesn't exist");
                break;
        }
        loadingTime(3000);
    }
    /**
     * <h1>Save or load monsters</h1>
     */

    public void saveOrLoad() {
        ArrayList<Monsters> monsterSaveOrLoad = new ArrayList<>(monsters);
        switch (printMenuAndGetChoice(Menu.SaveMenu.values())) {
            case SAVE:
                FileUtils.saveObject(monsterSaveOrLoad, "monstersSaveFile.ser", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                FileUtils.saveObject(lootOrTreasures, "LootOrTreasure.ser", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Saving monsters in portal: ");
                for (Monsters monsters : monsterSaveOrLoad) {
                    if (monsters != null)
                        System.out.println(monsters);
                    else
                        System.out.println("No monsters saved");
                }
                loadDots();
                System.out.println("Your monsters are saved in the portal");
                System.out.print("Saving loot or treasures");
                loadDots();
                for (LootOrTreasures.Treasure lootOrTreasure : lootOrTreasures) {
                    if (lootOrTreasure != null)
                        System.out.println(lootOrTreasure);
                    else
                        System.out.println("No loot or treasure saved");
                }
                System.out.println("Saving complete");
                break;
            case LOAD:
                if (FileUtils.loadObject("monstersSaveFile.ser") != null) {
                    ArrayList<Monsters> savedMonsters = (ArrayList<Monsters>) FileUtils.loadObject("monstersSaveFile.ser");
                    System.out.println("Loading monsters from the portal:");
                    if (savedMonsters != null) {
                        monsters.clear();
                        for (Monsters monster : savedMonsters) {
                            monsters.add(monster);
                            System.out.println(monster);
                            loadingTime(2000);
                        }
                    } else {
                        System.out.println("No monsters loaded from portal");
                    }
                    System.out.println("Your monsters are leaving the portal and have entered your dungeon");
                } else {
                    System.out.println("There are no monsters saved in the portal");
                    System.out.println("You have to save monsters in the portal to be able to load them into your dungeon");
                }
                if (FileUtils.loadObject("LootOrTreasure.ser") != null) {
                    ArrayList<LootOrTreasures.Treasure> savedLootOrTreasures = (ArrayList<LootOrTreasures.Treasure>) FileUtils.loadObject("LootOrTreasure.ser");
                    System.out.println("Loading loot or treasure from portal: ");
                    if (savedLootOrTreasures != null) {
                        lootOrTreasures.clear();
                        lootOrTreasures.addAll(savedLootOrTreasures);
                        System.out.println("Loaded loot and treasure complete");
                        loadingTime(2000);
                    } else {
                        System.out.println("No loot or treasure could be loaded from portal");
                    }
                } else
                    System.out.println("No loot or treasure could be found in portal");
                break;
            default:
                for (DungeonMaster dungeonMaster : dungeonMasters) {
                    if (dungeonMaster != null)
                        System.out.println("This option doesn't exist, " + dungeonMaster.toString());
                    else
                        System.out.println("This option doesn't exist, dungeon master");
                }
                break;
        }
    }

    /**
     * <h1>Show menu options and details</h1>
     */

    public void help() {
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
        loadingTime(3000);
    }

    private void enterDungeon() {
        System.out.println("WARNING!");
        System.out.println("Allowing the hero to enter the dungeon will take time to clear depending on the dungeon size and hero's health");
        System.out.println("Are you sure you want to proceed?");
        System.out.println("[Y]es or [N]o");
        String choice = tryCatchString();
        if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")){
            return;
        }
        else if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")){
            System.out.println("Enter hero first name, last name and level: ");
            hero = new Hero(tryCatchString(), tryCatchString(), tryCatchInt());
            generateHero();
            if (monsters.size() < 1){
                System.out.print(hero + " enters the dungeon but it was empty");
                loadDots();
                return;
            }
            int heroTreasureGain = 0;
            System.out.println(hero + "'s stats: ");
            System.out.println("Health: " + hero.getHealth());
            System.out.println("Damage: " + hero.getDamage());
            while (hero.getHealth() > 0 && monsters.size() > 0) {
                int encounterNumber = rand.nextInt((5 - 1) + 1) + 1;
                switch (encounterNumber) {
                    case 1:
                        int roundCount = 1;
                        int monsterIndex = rand.nextInt(monsters.size());
                        Monsters foundMonster = monsters.get(monsterIndex);
                        System.out.println(hero + " has encountered a " + foundMonster);
                        while (hero.getHealth() > 0 && foundMonster.getHealth() > 0) {
                            loadingTime(1000);
                            System.out.println("ROUND " + roundCount++);
                            System.out.println("-------------------------------------------");
                            if (hero.getHealth() > 0)
                                heroTurn(monsterIndex);
                            if (foundMonster.getHealth() > 0)
                                monsterTurn(monsterIndex);
                        }
                        if (hero.getHealth() < 0){
                            System.out.println(foundMonster + " has killed " + hero);
                            loadingTime(1000);
                        }
                        else if (foundMonster.getHealth() < 0){
                            System.out.println(hero + " has killed " + foundMonster);
                            monsters.remove(foundMonster);
                            loadingTime(1000);
                        }
                        break;
                    case 2:
                        System.out.println(hero + " has encountered a trap");
                        int trapDamage = (int) (hero.getHealth() * 0.3);
                        Traps trapType = Traps.values()[rand.nextInt(Traps.values().length)];
                        System.out.println(trapType.trapDescription);
                        System.out.println(hero + " takes " + trapDamage + " damage");
                        hero.setHealth(hero.getHealth() - trapDamage);
                        loadingTime(2000);
                        System.out.println(hero + "'s current health is: " + hero.getHealth());
                        break;
                    case 3:
                        int foundChestChance = rand.nextInt((2 - 1)+1)+1;
                        System.out.print(hero + " found a chest");
                        loadDots();
                        if (lootOrTreasures.size() > 0) {
                            if (foundChestChance == 2) {
                                LootOrTreasures.Treasure foundTreasure = lootOrTreasures.get(rand.nextInt(lootOrTreasures.size()));
                                System.out.println(hero + " found: " + foundTreasure.lootAndTreasure + " worth: " + foundTreasure.lootValue);
                                loadingTime(500);
                                heroTreasureGain += foundTreasure.lootValue;
                                System.out.println("Current found loot/treasure value: " + heroTreasureGain);
                                lootOrTreasures.remove(foundTreasure);
                                loadingTime(1000);
                            }
                            else {
                                System.out.println("but it was empty");
                            }
                        }
                        else {
                            System.out.println("but it was empty since there's no more loot/treasure in the dungeon");
                        }
                        break;
                    case 4:
                        System.out.println(hero + " has encountered nothing and moves on");
                        break;
                    case 5:
                        Shrines shrines = Shrines.values()[rand.nextInt(Shrines.values().length)];
                        switch (shrines) {
                            case HEALING:
                                System.out.println(shrines.shrineDescription);
                                if (hero.getLevel() < 5) {
                                    int healingDone = hero.getHealth() * 2;
                                    healingDone = healingDone - hero.getHealth();
                                    System.out.println(hero + " healed for: " + healingDone + " health");
                                    loadingTime(1000);
                                    hero.setHealth(hero.getHealth() + healingDone);
                                } else {
                                    int healingDone = (int) (hero.getHealth() * 1.3);
                                    healingDone = healingDone - hero.getHealth();
                                    System.out.println(hero + " healed for: " + healingDone + " health");
                                    loadingTime(1000);
                                    hero.setHealth(hero.getHealth() + healingDone);
                                }
                                System.out.println(hero + "'s current health is: " + hero.getHealth());
                                loadingTime(1000);
                                break;
                            case DAMAGE:
                                System.out.println(shrines.shrineDescription);
                                hero.setDamage((int) (hero.getDamage() * 1.1));
                                System.out.println(hero + "'s current damage is: " + hero.getDamage());
                                loadingTime(1000);
                                break;
                            case CURSE:
                                System.out.println(shrines.shrineDescription);
                                hero.setDamage((int) (hero.getDamage() * 0.9));
                                System.out.println(hero + "'s current damage is: " + hero.getDamage());
                                loadingTime(1000);
                                break;
                        }
                        break;
                }
                loadingTime(2000);
                System.out.println();
                System.out.println();
            }
            if (hero.getHealth() <= 0) {
                System.out.println(hero.getTitle() + hero + " has died with loot value: " + heroTreasureGain);
            }
            else if (monsters.size() == 0) {
                System.out.println("All monsters was killed by " + hero);
                System.out.println("Hero emerged from dungeon alive with total loot/treasure value: " + heroTreasureGain);
            }
        }
        else{
            System.out.println("Not an option, dungeon master" );
        }
        loadingTime(2000);
    }

    private void generateHero(){
        int heroItemCount = 0;
        int heroItemValue = 0;
        while (heroItemCount < MIN_ITEMS) {
            Equipment.Items newItem = Equipment.Items.values()[rand.nextInt(Equipment.Items.values().length)];
            hero.addEquipment(newItem.item);
            heroItemCount++;
            heroItemValue += newItem.value;
        }
        System.out.println(hero + " was given:");
        for (Equipment heroItem: hero.getEquipment()){
            System.out.println(heroItem.getGear());
            loadingTime(1000);
        }
        System.out.println("with value: " + heroItemValue);
        loadingTime(2000);
        hero.setHealth(hero.getHealth() + heroItemValue);
        hero.setDamage(hero.getDamage() + heroItemValue);
    }
    /**
     * <h1>Exit program</h1>
     */
    public void exit() {
        for (DungeonMaster dungeonMaster : dungeonMasters) {
            if (dungeonMaster != null)
                System.out.println("Thanks for playing " + dungeonMaster.toString());
            else
                System.out.println("Thanks for playing");
        }
    }

    /**
     * <h1>Adds feral monsters if save file not found or new dungeon</h1>
     */

    public void addFeralMonsters() {
        int i = 0;
        while (i < 5) {
            MonsterFactory.MonsterAffix monsterAffix = MonsterFactory.MonsterAffix.values()[rand.nextInt(MonsterFactory.MonsterAffix.values().length)];
            MonsterFactory.MonsterType monsterType = MonsterFactory.MonsterType.values()[rand.nextInt(MonsterFactory.MonsterType.values().length)];
            monsters.add(new Monsters(monsterAffix.monsterAffix, monsterType.monsterType, rand.nextInt((10 - 1) + 1) + 1));
            monsters.get(i).addEquipment("No gear due to feral monster");
            monsters.get(i).setTitle("Feral ");
            i++;
            loadingTime(1000);
        }
    }

    public void addPresetLootOrTreasure(){
        int presetLootOrTreasureAmount = 0;
        while (presetLootOrTreasureAmount < 3){
            LootOrTreasures.Treasure loot = LootOrTreasures.Treasure.values()[rand.nextInt(LootOrTreasures.Treasure.values().length)];
            lootOrTreasures.add(loot);
            System.out.println(loot.lootAndTreasure + " has been found and added to treasury");
            presetLootOrTreasureAmount++;
            loadingTime(1000);
        }
    }

    /**
     *
     * @param index finds monster at designated index
     */

    public void monsterTurn(int index){
        System.out.println(monsters.get(index).getMonsterType() + " deals " + monsters.get(index).getDamage() + " damage");
        int monsterDamageDealt = monsters.get(index).getDamage();
        int heroHealth = hero.getHealth() - monsterDamageDealt;
        loadingTime(1000);
        System.out.println(hero + " has " + heroHealth + " health left");
        hero.setHealth(heroHealth);
        loadingTime(1000);
    }

    /**
     *
     * @param index finds monster at designated index
     */

    public void heroTurn(int index){
        System.out.println(hero + " deals " + hero.getDamage() + " damage");
        int heroDamageDealt = hero.getDamage();
        int monsterHealth = monsters.get(index).getHealth() - heroDamageDealt;
        loadingTime(1000);
        System.out.println(monsters.get(index) + " has " + monsterHealth + " health left");
        monsters.get(index).setHealth(monsterHealth);
        loadingTime(1000);
    }

    private <T extends HasDescription> T printMenuAndGetChoice(T[] choices) {
        int menu;
        int i = 1;
        for (T menuItem : choices) {
            System.out.println(i++ + ". " + menuItem.getDescription());
        }
        menu = scan.nextInt();
        return choices[menu - 1];
    }

    private void printMonsterArray() {
        int position = 1;
        for (Monsters monsters : monsters) {
            if (monsters != null) {
                System.out.println(position + ". " + monsters);
                position++;
            }
            else
                System.out.println("Monsters doesn't exist");
        }
    }

    private void loadingTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println();
        }
    }

    private void loadDots() {
        System.out.print(".");
        loadingTime(500);
        System.out.print(".");
        loadingTime(500);
        System.out.println(".");
        loadingTime(500);
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
