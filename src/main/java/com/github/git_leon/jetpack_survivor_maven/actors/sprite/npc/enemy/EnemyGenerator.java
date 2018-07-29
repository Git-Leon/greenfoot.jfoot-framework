package com.github.git_leon.jetpack_survivor_maven.actors.sprite.npc.enemy;

import com.github.git_leon.RandomUtils;
import com.github.git_leon.jetpack_survivor_maven.actors.sprite.AnimatedSprite;

import java.util.function.Supplier;

public enum EnemyGenerator {
    BAT(Bat::new),
    MUMMY(Mummy::new),
    FIEND_CRAWLER(FiendCrawler::new),
    ORC_RIDER(OrcRider::new),
    ZOMBIE_CRAWLER(ZombieCrawler::new),
    ZOMBIE_GUNNER(ZombieGunner::new);
    private final Supplier<Mob> enemySupplier;

    EnemyGenerator(Supplier<Mob> enemySupplier) {
        this.enemySupplier = enemySupplier;
    }

    public Supplier<Mob> getEnemySupplier() {
        return enemySupplier;
    }

    public static AnimatedSprite getRandom() {
        return RandomUtils.selectElement(values()).enemySupplier.get();
    }
}