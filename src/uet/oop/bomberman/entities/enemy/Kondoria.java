package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.audio.SimpleAudioPlayer;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.handle.Handler;
import uet.oop.bomberman.handle.MovingEntity;

public class Kondoria extends Entity {

    MovingEntity movingEntity;
    Handler handler;
    Entity bomberman;

    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


    public Kondoria(int xUnit, int yUnit, Image image, Handler handler, Entity bomberman) {
        super(xUnit, yUnit, image);
        this.bomberman = bomberman;
        this.handler = handler;
        movingEntity = new MovingEntity(this, bomberman);
    }

    private void chooseSprite() {
        switch (transition) {
            case 0:
                img = Sprite.movingImage(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate, 20);
                break;
            case 1:
                img = Sprite.movingImage(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate, 20);
                break;
        }
    }

    private void movingKondoria() {
        movingEntity.moveNotWall(this, bomberman);
    }

    private void konDead() {
        if (!FlameSegment.resultKon) {
            Game.score += 100;
            SimpleAudioPlayer.playWhenDie();
            Game.entities.remove(this);
        }
    }

    @Override
    public void update() {
        chooseSprite();
        movingKondoria();
        animate();
        konDead();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
