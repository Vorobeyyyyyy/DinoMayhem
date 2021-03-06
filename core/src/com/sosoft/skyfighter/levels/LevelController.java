package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.sosoft.skyfighter.TilemapCollisionParser;
import com.sosoft.skyfighter.TilemapSpawnpointParser;
import com.sosoft.skyfighter.heroes.Hero;
import com.sosoft.skyfighter.heroes.heroes.Dino;
import com.sosoft.skyfighter.heroes.heroes.Imposter;
import com.sosoft.skyfighter.menu.HeroSelectWidget;
import com.sosoft.skyfighter.weapons.Bullet;

import java.util.Map;

import static com.sosoft.skyfighter.levels.Constants.PPM;

public class LevelController {

    Array<Vector2> spawnPoints = new Array<Vector2>();

    public Level level;
    public World world;
    public LevelContactListener levelContactListener;

    RandomXS128 random = new RandomXS128();

    LevelController(Level level,Map<Controller, HeroSelectWidget> controllers) {
        this.level = level;
        world = new World(new Vector2(0, -10), false);
        levelContactListener = new LevelContactListener(level);
        world.setContactListener(levelContactListener);
        TilemapCollisionParser.parseCollisionLayer(world, level.tiledMap.getLayers().get("collision-layer").getObjects());
        TilemapSpawnpointParser.ParseSpawnpoints(spawnPoints, level.tiledMap);
        spawnHeroes(controllers);
    }

    public void update(float delta) {
        world.step(1 / 60f, 6, 2);
        for (Hero hero : level.players)
            hero.update(delta);
        for (Bullet bullet : level.bullets)
            bullet.update(delta);
        respawnHeroes();
        deleteBullets();
    }

    public void spawnHeroes(Map<Controller, HeroSelectWidget> controllers) {
        int i = 1;
        for (Controller controller : controllers.keySet()) {
            String heroName = controllers.get(controller).getCurrentHeroName();
            spawnHero(heroName, controller, i++);
        }
    }

    public void spawnHero(String heroName, Controller controller, int number)
    {
        int n = random.nextInt(spawnPoints.size);
        if  (heroName.equals("Dino")) {
            level.players.add(new Dino(this, spawnPoints.get(n).x, spawnPoints.get(n).y, controller, number));
        }
        if (heroName.equals("Imposter")) {
            level.players.add(new Imposter(this, spawnPoints.get(n).x, spawnPoints.get(n).y, controller, number));
    }

    }

    public void respawnHeroes() {
        for (Hero hero : level.players) {
            if (hero.state.dead)
                hero.body.setTransform(-1000,-1000,0);
            if (hero.state.respawnTime < 0) {
                float x = spawnPoints.get(random.nextInt(spawnPoints.size)).x / PPM;
                float y = spawnPoints.get(random.nextInt(spawnPoints.size)).y / PPM;
                hero.body.setTransform(x, y, 0);
                hero.reset();
            }
        }
    }

    public void deleteBullets() {
        for (int i = 0; i < level.bullets.size; i++) {
            Bullet bullet = level.bullets.get(i);
            if (bullet.endOfLife) {
                world.destroyBody(bullet.body);
                bullet.dispose();
                level.bullets.removeIndex(i);
            }
        }
    }

    public void dispose() {
        world.dispose();
    }
}