package com.github.git_leon.jetpack_survivor_maven.actors.sprite.npc.enemy;

public class OrcRider extends Mob {
    public OrcRider() {
        super(  "npc/orc_rider/", ".png", 2);
    }


    public void act() {
        touchGround();
        act(5, 10, 1);
    }

}
