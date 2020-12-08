package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.audio.SimpleAudioPlayer;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.enemy.Balloon;
import uet.oop.bomberman.entities.stand.Brick;
import uet.oop.bomberman.entities.stand.Grass;
import uet.oop.bomberman.entities.stand.Item.BombItem;
import uet.oop.bomberman.entities.stand.Item.FlameItem;
import uet.oop.bomberman.entities.stand.Item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.handle.Handler;
import uet.oop.bomberman.map.CreateMap;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.GameUI.input;

public class Game {

    private SimpleAudioPlayer simpleAudioPlayer = new SimpleAudioPlayer("res/audio/soundtrack.wav");

    public static List<Bomb> bombs = new ArrayList<>();
    public static List<Entity> items = new ArrayList<>();
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();

    public static Canvas canvas;
    public static Entity bomberman;
    public static GraphicsContext gc;
    public static final Handler handler = new Handler();

    public static int score = 0;

    public final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            Bomb bomb = new Bomb(bomberman.getX() / Sprite.SCALED_SIZE, bomberman.getY() / Sprite.SCALED_SIZE,
                    Sprite.bomb.getFxImage(), input, stillObjects);
            for (Bomb b : bombs) {
                for (Entity e : entities) {
                    b.collision(e);
                }
            }
            bomb.handleBomb(bombs);
            render();
            update();
            setItem();
        }
    };


    public Game(GraphicsContext gc, Canvas canvas) {
        Game.canvas = canvas;
        Game.gc = gc;
    }

    public static void initGame() {
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage(), input, stillObjects, handler, entities);
        entities.add(bomberman);
        createMap(CreateMap.pathData[CreateMap.level]);
    }

    public void PlayGame() {
        timer.start();
        simpleAudioPlayer.play();
    }

    public void PausedGame() {
        timer.stop();
        simpleAudioPlayer.stop();
    }

    public static void cleanUpGame() {
        entities = new ArrayList<>();
        stillObjects = new ArrayList<>();
        bombs = new ArrayList<>();
        items = new ArrayList<>();
    }

    public static void createMap(String path) {
        Entity bomber = entities.get(0);
        CreateMap createMap = new CreateMap(path);
        createMap.createMaps(stillObjects, entities, items, handler, bomber, path);
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).update();
        }
        for (int i = 0; i < items.size(); i++) {
            items.get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
        items.forEach(g -> g.render(gc));
    }


    public void setItem() {
        for (Entity object : stillObjects) {
            if (object.getX() / Sprite.SCALED_SIZE == 7 && object.getY() / Sprite.SCALED_SIZE == 1) {
                if (object instanceof Brick) {
                    SpeedItem.render = 1;
                } else if (object instanceof Grass) {
                    SpeedItem.render = 2;
                }
            }
            if (object.getX() / Sprite.SCALED_SIZE == 4 && object.getY() / Sprite.SCALED_SIZE == 3) {
                if (object instanceof Brick) {
                    FlameItem.render = 1;
                } else if (object instanceof Grass) {
                    FlameItem.render = 2;
                }
            }
            if (object.getX() / Sprite.SCALED_SIZE == 1 && object.getY() / Sprite.SCALED_SIZE == 9) {
                if (object instanceof Brick) {
                    BombItem.render = 1;
                } else if (object instanceof Grass) {
                    BombItem.render = 2;
                }
            }
        }
    }
}
