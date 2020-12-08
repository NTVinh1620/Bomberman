package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.stand.Brick;
import uet.oop.bomberman.entities.stand.Item.FlameItem;
import uet.oop.bomberman.entities.stand.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Flame extends Entity {
    private int timeAfterExplode = 20;
    private int timeBrickExplode = 20;
    private int lengthRight = 1, lengthLeft = 1, lengthUp = 1, lengthDown = 1;

    private List<Entity> flameSegments = new ArrayList<>();
    private List<Entity> objects;


    public int getTimeToRender() {
        return timeAfterExplode;
    }

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Flame(int xUnit, int yUnit, Image img, List<Entity> objects) {
        super(xUnit, yUnit, img);
        this.objects = objects;
    }

    private void afterExplode() {
        if (timeAfterExplode > 0) {
            timeAfterExplode--;
        }
    }

    private void flameSegment() {
        FlameSegment flameSegment1 = new FlameSegment(x / Sprite.SCALED_SIZE + lengthRight, y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_right_last2.getFxImage());
        FlameSegment flameSegment2 = new FlameSegment(x / Sprite.SCALED_SIZE - lengthLeft, y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_left_last2.getFxImage());
        FlameSegment flameSegment3 = new FlameSegment(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE - lengthUp, Sprite.explosion_vertical_top_last2.getFxImage());
        FlameSegment flameSegment4 = new FlameSegment(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE + lengthDown, Sprite.explosion_vertical_down_last2.getFxImage());
        flameSegments.add(flameSegment1);
        flameSegments.add(flameSegment2);
        flameSegments.add(flameSegment3);
        flameSegments.add(flameSegment4);
        FlameSegment flameSegment11 = new FlameSegment(x / Sprite.SCALED_SIZE + 1, y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal2.getFxImage());
        FlameSegment flameSegment22 = new FlameSegment(x / Sprite.SCALED_SIZE - 1, y / Sprite.SCALED_SIZE, Sprite.explosion_horizontal2.getFxImage());
        FlameSegment flameSegment33 = new FlameSegment(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE - 1, Sprite.explosion_vertical2.getFxImage());
        FlameSegment flameSegment44 = new FlameSegment(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE + 1, Sprite.explosion_vertical2.getFxImage());
        if (lengthRight > 1) flameSegments.add(flameSegment11);
        if (lengthLeft > 1) flameSegments.add(flameSegment22);
        if (lengthUp > 1) flameSegments.add(flameSegment33);
        if (lengthDown > 1) flameSegments.add(flameSegment44);
    }

    private void handleSegment() {
        lengthRight = 1;
        lengthLeft = 1;
        lengthUp = 1;
        lengthDown = 1;
        if (FlameItem.result) {
            lengthRight = 2;
            lengthLeft = 2;
            lengthUp = 2;
            lengthDown = 2;
        }
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof Wall) {
                if (checkFlameRight(objects.get(i))) {
                    lengthRight = 0;
                }
                if (checkFlameLeft(objects.get(i))) {
                    lengthLeft = 0;
                }
                if (checkFlameUp(objects.get(i))) {
                    lengthUp = 0;
                }
                if (checkFlameDown(objects.get(i))) {
                    lengthDown = 0;
                }
            }
            if (objects.get(i) instanceof Brick) {
                if (checkFlameRight(objects.get(i))) {
                    lengthRight = 0;
                    if (timeBrickExplode > 0) {
                        objects.get(i).setImg(Sprite.movingImage(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, 60));
                        timeBrickExplode--;
                    }
                    if (timeBrickExplode == 0) {
                        objects.remove(objects.get(i));
                    }
                }
                if (checkFlameLeft(objects.get(i))) {
                    lengthLeft = 0;
                    if (timeBrickExplode > 0) {
                        objects.get(i).setImg(Sprite.movingImage(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, 60));
                        timeBrickExplode--;
                    }
                    if (timeBrickExplode == 0) {
                        objects.remove(objects.get(i));
                    }
                }
                if (checkFlameUp(objects.get(i))) {
                    lengthUp = 0;
                    if (timeBrickExplode > 0) {
                        objects.get(i).setImg(Sprite.movingImage(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, 60));
                        timeBrickExplode--;
                    }
                    if (timeBrickExplode == 0) {
                        objects.remove(objects.get(i));
                    }
                }
                if (checkFlameDown(objects.get(i))) {
                    lengthDown = 0;
                    if (timeBrickExplode > 0) {
                        objects.get(i).setImg(Sprite.movingImage(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, 60));
                        timeBrickExplode--;
                    }
                    if (timeBrickExplode == 0) {
                        objects.remove(objects.get(i));
                    }
                }
            }
        }
    }

    private boolean checkFlameLeft(Entity e) {
        return this.x - Sprite.SCALED_SIZE == e.getX() && this.y == e.getY() ||
                (this.x - Sprite.SCALED_SIZE * 2 == e.getX() && this.y == e.getY() &&
                        lengthLeft > 1);
    }

    private boolean checkFlameRight(Entity e) {
        return this.x + Sprite.SCALED_SIZE == e.getX() && this.y == e.getY() ||
                (this.x + Sprite.SCALED_SIZE * 2 == e.getX() && this.y == e.getY() &&
                        lengthRight > 1);
    }

    private boolean checkFlameUp(Entity e) {
        return this.y - Sprite.SCALED_SIZE == e.getY() && this.x == e.getX() ||
                (this.y - Sprite.SCALED_SIZE * 2 == e.getY() && this.x == e.getX() &&
                        lengthUp > 1);
    }

    private boolean checkFlameDown(Entity e) {
        return this.y + Sprite.SCALED_SIZE == e.getY() && this.x == e.getX() ||
                (this.y + Sprite.SCALED_SIZE * 2 == e.getY() && this.x == e.getX() &&
                        lengthDown > 1);
    }

    public void collision(Entity e) {
        for (Entity fs : flameSegments) {
            ((FlameSegment) fs).collision(e);
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        flameSegments.forEach(g -> g.render(gc));
    }

    @Override
    public void update() {
        afterExplode();
        handleSegment();
        flameSegment();
    }

}