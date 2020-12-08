package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.GameUI;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.enemy.Balloon;
import uet.oop.bomberman.entities.enemy.Kondoria;
import uet.oop.bomberman.entities.enemy.Oneal;
import uet.oop.bomberman.entities.stand.Item.BombItem;
import uet.oop.bomberman.graphics.Sprite;

public class FlameSegment extends Entity {
    public static boolean resultBomber = true,
            resultBalloon = true, resultOneal = true, resultKon = true;

    public static int timeBomberDead = 30, timeBalloonDead = 30,
            timeOnealDead = 30, timeKonDead = 30;

    public FlameSegment(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    private boolean checkFlameLeft(Entity e) {
        return this.x < e.getX() + e.getImg().getWidth();
    }

    private boolean checkFlameRight(Entity e) {
        return this.x + this.img.getWidth() > e.getX();
    }

    private boolean checkFlameUp(Entity e) {
        return this.y < e.getY() + e.getImg().getHeight();
    }

    private boolean checkFlameDown(Entity e) {
        return this.y + this.img.getHeight() > e.getY();
    }

    public void collision(Entity e) {
        if (timeBomberDead > 0) {
            timeBomberDead--;
        }
        if (timeBalloonDead > 0) {
            timeBalloonDead--;
        }
        if (timeOnealDead > 0) {
            timeOnealDead--;
        }
        if (timeKonDead > 0) {
            timeKonDead--;
        }

        if (checkFlameRight(e) && checkFlameLeft(e) && checkFlameUp(e) && checkFlameDown(e)) {
            if (e instanceof Bomber) {
                e.setImg(Sprite.player_dead1.getFxImage());
                if (timeBomberDead == 0) resultBomber = false;
            } else if (e instanceof Balloon) {
                e.setImg(Sprite.balloom_dead.getFxImage());
                if (timeBalloonDead == 0) {
                    resultBalloon = false;
                }
            } else if (e instanceof Oneal) {
                e.setImg(Sprite.oneal_dead.getFxImage());
                if (timeOnealDead == 0) {
                    resultOneal = false;
                }
            } else if (e instanceof Kondoria) {
                e.setImg(Sprite.kondoria_dead.getFxImage());
                if (timeKonDead == 0) {
                    resultKon = false;
                }
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    @Override
    public void update() {

    }
}