package com.company;

/**
 * <h1>Different traps with event description</h1>
 * Traps deal percentage damage to hero
 */

public enum Traps {
    /**
     * Boulder trap, rolls and damages hero
     */
    BOULDER("The hero gets hit by a rolling boulder"),
    /**
     * Plate trap that opens floor beneath hero and hero falls into spikes
     */
    SPIKE_TRAP("The hero steps on a brick that triggers spikes to emerge from the ground"),
    /**
     * Swinging axe from roof
     */
    SWING_TRAP("The hero walks across a bridge in the dungeon and suddenly a large axe swings from the roof"),
    /**
     * Shoots arrows from wall
     */
    ARROW_TRAP("As the hero walks through a corridor, an arrow shoots at the hero"),
    /**
     * Sprouts fire from different angles
     */
    FIRE_TRAP("Fire sprouts from the floor and roof"),
    /**
     * Magic circle that damages hero
     */
    MAGIC_TRAP("A magic circle shows up beneath the hero's feet and starts glowing"),
    /**
     * Opens floor and hero falls great height
     */
    FALL_TRAP("The floor opens beneath the hero and the hero falls into the pit revealed");

    public String trapDescription;

    private Traps(String trapDescription){
        this.trapDescription = trapDescription;
    }
}
