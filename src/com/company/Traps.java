package com.company;

/**
 * <h1>Different traps with event description</h1>
 */

public enum Traps {
    /**
     * Trap description used to print event upon trap encounter
     */
    BOULDER("The hero gets hit by a rolling boulder"),
    SPIKE_TRAP("The hero steps on a brick that triggers spikes to emerge from the ground"),
    SWING_TRAP("The hero walks across a bridge in the dungeon and suddenly a large axe swings from the roof"),
    ARROW_TRAP("As the hero walks through a corridor, an arrow shoots at the hero"),
    FIRE_TRAP("Fire sprouts from the floor and roof"),
    MAGIC_TRAP("A magic circle shows up beneath the hero's feet and starts glowing"),
    FALL_TRAP("The floor opens beneath the hero and the hero falls into the pit revealed");

    public String trapDescription;

    private Traps(String trapDescription){
        this.trapDescription = trapDescription;
    }
}
