package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.audio.SimpleAudioPlayer;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.handle.Handler;
import uet.oop.bomberman.handle.MovingLowEntity;

import java.util.List;

public class Balloon extends Entity {
    MovingLowEntity movingLowEntity = new MovingLowEntity();
    Handler handler = new Handler(1);
    private List<Entity> objects;

    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public Balloon(int x, int y, Image img, List<Entity> objects) {
        super(x, y, img);
        this.objects = objects;
    }

    private void chooseSprite() {
        switch (transition) {
            case 0:
                img = Sprite.movingImage(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 20);
                break;
            case 1:
                img = Sprite.movingImage(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 20);
                break;
        }
    }

    private void balloonDead() {
        if (!FlameSegment.resultBalloon) {
            Game.score += 100;
            SimpleAudioPlayer.playWhenDie();
            Game.entities.remove(this);
        }
    }

    @Override
    public void update() {
        chooseSprite();
        animate();
        movingLowEntity.moveLowEntityCol(this, handler, objects);
        movingLowEntity.moveLowEntityRow(this, handler, objects);
        balloonDead();
    }

}
