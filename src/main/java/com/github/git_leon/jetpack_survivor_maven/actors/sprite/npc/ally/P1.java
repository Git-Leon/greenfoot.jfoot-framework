package com.github.git_leon.jetpack_survivor_maven.actors.sprite.npc.ally;

import com.github.git_leon.jetpack_survivor_maven.actors.sprite.AnimatedSprite;
import com.github.git_leon.jetpack_survivor_maven.actors.sprite.SpriteCreatorRemover;
import com.github.git_leon.jetpack_survivor_maven.actors.sprite.items.Platform;
import com.github.git_leon.jetpack_survivor_maven.actors.sprite.items.Rocket;
import com.github.git_leon.jetpack_survivor_maven.actors.sprite.items.weapons.projectiles.Gun;
import com.github.git_leon.jetpack_survivor_maven.physics.gravity.Gravity;
import com.github.git_leon.jetpack_survivor_maven.physics.gravity.GravityInfluenceeInterface;
import com.github.git_leon.jetpack_survivor_maven.system.controls.JFootKey;

public class P1 extends AnimatedSprite implements GravityInfluenceeInterface, Ally {
    private final SpriteCreatorRemover spriteCreator;
    private final Gun gun;
    private Rocket rocket;
    private int runSpeed;
    private float verticalSpeed;

    public P1() {
        super("player/walk/walk", ".png", 6);
        this.gun = new Gun(this);
        this.runSpeed = 2;
        this.spriteCreator = new SpriteCreatorRemover(this);
        this.rocket = new Rocket(this);
    }

    @Override
    public final void animate() {
        if (JFootKey.isAnyKeyDown(
                JFootKey.LEFT,
                JFootKey.RIGHT,
                JFootKey.UP,
                JFootKey.DOWN)) {
            super.animate(3);
        }
    }

    @Override
    public final void postAnimationBehavior() {
        controls();
        Gravity.applyNormal(this);
    }

    @Override
    public Float getVerticalSpeed() {
        return verticalSpeed;
    }

    @Override
    public void setVerticalSpeed(Float i) {
        verticalSpeed = i;
    }

    @Override
    public float getTerminalSpeed() {
        return 3;
    }

    @Override
    public boolean isOnGround() {
        return getOneIntersectingObject(Platform.class) != null;
    }

    @Override
    public void moveRight(int xOffset) {
        super.moveRight(xOffset);
        if (!super.flippedHorizontally) {
            flipImagesHorizontally();
        }
    }

    @Override
    public void moveLeft(int xOffset) {
        super.moveLeft(xOffset);
        if (super.flippedHorizontally) {
            flipImagesHorizontally();
        }
    }

    public void controls() {
        JFootKey.SPACE.onKeyPress(this::createRocket);
        JFootKey.DOWN.onKeyPress(gun::shoot, 5);
        JFootKey.LEFT.onKeyPress(this::moveLeft, runSpeed);
        JFootKey.RIGHT.onKeyPress(this::moveRight, runSpeed);
        if (isOnGround() && JFootKey.UP.isKeyDown()) {
            jump();
        }
    }

    private void createRocket() {
        if(!rocket.isAddedToWorld()) {
            spriteCreator.add(rocket);
        }
    }


    private void jump() {
        Gravity.ANTI.apply(this, 15);
    }
}
