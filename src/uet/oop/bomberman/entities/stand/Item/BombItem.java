package uet.oop.bomberman.entities.stand.Item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.audio.SimpleAudioPlayer;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class BombItem extends Entity {
    Entity e;

    public static int render = 0, render2 = 0;

    private List<Entity> items;

    public BombItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public BombItem(int xUnit, int yUnit, Image img, Entity e, List<Entity> items) {
        super(xUnit, yUnit, img);
        this.e = e;
        this.items = items;
    }

    private void bombItem() {
        if (e.getX()/ Sprite.SCALED_SIZE == x/Sprite.SCALED_SIZE && e.getY()/ Sprite.SCALED_SIZE == y/Sprite.SCALED_SIZE) {
            Bomb.amountBomb = 2;
            render2 = 1;
            SimpleAudioPlayer.playWhenEatItem();
            items.remove(this);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (render == 2 && render2 == 0) {
            gc.drawImage(img, x, y);
        }
    }

    @Override
    public void update() {
        bombItem();
    }
}