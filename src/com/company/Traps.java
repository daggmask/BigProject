package com.company;

public enum Traps {
    BOULDER("The hero gets hit by a rolling boulder"),
    SPIKE_TRAP("The hero steps on a brick that triggers spikes to emerge from the ground"),
    SWING_TRAP,
    ARROW_TRAP,
    FIRE_TRAP,
    MAGIC_TRAP,
    FALL_TRAP;

    public String trapDescription;

    private Traps(String trapDescription){
        this.trapDescription = trapDescription;
    }
}
