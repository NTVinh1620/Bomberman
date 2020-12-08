package uet.oop.bomberman.entities.enemy;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.audio.SimpleAudioPlayer;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.handle.Handler;
import uet.oop.bomberman.handle.MovingEntity;

public class Oneal extends Entity {
    MovingEntity movingEntity;
    Handler handler;
    List<Entity> stillObject;
    Entity bomberman;

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


    public Oneal(int xUnit, int yUnit, Image image, Handler handler, List<Entity> stillObject,
                 Entity bomberman) {
        super(xUnit, yUnit, image);
        this.bomberman = bomberman;
        this.handler = handler;
        this.stillObject = stillObject;
        movingEntity = new MovingEntity(this, bomberman);
    }

    private void movingOneal() {
        movingEntity.move(this, bomberman, stillObject);
    }

    private void chooseSprite() {
        switch (transition) {
            case 0:
                img = Sprite.movingImage(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 20);
                break;
            case 1:
                img = Sprite.movingImage(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 20);
                break;
        }
    }

    private void onealDead() {
        if (!FlameSegment.resultOneal) {
            Game.score += 100;
            SimpleAudioPlayer.playWhenDie();
            Game.entities.remove(this);
        }
    }

    @Override
    public void update() {
        chooseSprite();
        movingOneal();
        animate();
        onealDead();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
