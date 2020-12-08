package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.audio.SimpleAudioPlayer;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Input;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {
    Input input;

    private int timeToExplode = 120;
    public static int amountBomb = 1;

    private boolean isExplode = false;

    private final List<Flame> flames = new ArrayList<>();
    private List<Entity> objects = new ArrayList<>();

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Bomb(int xUnit, int yUnit, Image img, Input input, List<Entity> objects) {
        super(xUnit, yUnit, img);
        this.input = input;
        this.objects = objects;
    }

    private void explode() {
        if (timeToExplode > 0) {
            img = Sprite.movingImage(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 60);
            timeToExplode--;
        }
    }

    public void handleBomb(List<Bomb> bombs) {
        handle(bombs);
    }

    private void handle(List<Bomb> bombs) {
        if (input.space) {
            bombs.add(this);
            SimpleAudioPlayer.playWhenPutBom();
        }
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).isExplode) {
                bombs.remove(bombs.get(i));
            }
        }
        if (bombs.size() > amountBomb) {
            for (int i = 1; i < bombs.size(); i++) {
                bombs.remove(bombs.get(i));
            }
        }
    }

    private void flame() {
        Flame flame = new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE,
                Sprite.bomb_exploded2.getFxImage(), objects);
        for (Flame f : flames) {
            f.update();
        }
        if (timeToExplode == 0) {
            flames.add(flame);
            SimpleAudioPlayer.playWhenExplosive();
        }
        if (flames.size() > 1 && flames.get(0).getTimeToRender() == 0) isExplode = true;
    }

    public void collision(Entity e) {
        for (Flame f : flames) {
            f.collision(e);
        }
    }

    @Override
    public void update() {
        animate();
        explode();
        flame();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        flames.forEach(g -> g.render(gc));
    }
}
