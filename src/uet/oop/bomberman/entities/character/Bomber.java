package uet.oop.bomberman.entities.character;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.handle.Handler;
import uet.oop.bomberman.input.Input;
import uet.oop.bomberman.map.CreateMap;

import java.util.List;

public class Bomber extends Entity {

    public static boolean checkPassLevel = true;
    public static BooleanProperty booleanProperty = new SimpleBooleanProperty(true);
    public static boolean checkLive = true;
    public static BooleanProperty booleanPropertyLive = new SimpleBooleanProperty(true);
    private Input input;
    private List<Entity> stillObjects;
    private List<Entity> entities;
    private Handler handler;


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }


    public Bomber(int x, int y, Image img, Input input, List<Entity> stillObjects, Handler handler,
                  List<Entity> entities) {
        super(x, y, img);
        this.entities = entities;
        this.input = input;
        this.stillObjects = stillObjects;
        this.handler = handler;
    }


    public void isCheckLive() {
        checkLive = handler.checkTouch(this, entities) && FlameSegment.resultBomber;
        if (!checkLive) {
            CreateMap.level = 0;
        }
    }

    public void CheckPassLevel() {
        checkPassLevel = handler.checkPassLevel(this, stillObjects);
        if (!checkPassLevel) {
            CreateMap.level = 1;
        }
    }

    public void movingBomber(Input input) {
        if (input.down) {
            moving = true;
            transition = 0;
            this.img = Sprite.player_down.getFxImage();
            if (handler.checkDown(this, stillObjects)) {
                this.y += 2;
            }
        }
        if (input.up) {
            moving = true;
            transition = 1;
            this.img = Sprite.player_up.getFxImage();
            if (handler.checkUp(this, stillObjects)) {
                this.y -= 2;
            }
        }
        if (input.left) {
            moving = true;
            transition = 2;
            this.img = Sprite.player_left.getFxImage();
            if (handler.checkLeft(this, stillObjects)) {
                this.x -= 2;
            }
        }
        if (input.right) {
            moving = true;
            transition = 3;
            this.img = Sprite.player_right.getFxImage();
            if (handler.checkRight(this, stillObjects)) {
                this.x += 2;
            }
        }
    }

    protected void chooseSprite() {
        switch (transition) {
            case 0:
                img = Sprite.player_down.getFxImage();
                if (moving) {
                    img =
                            Sprite.movingImage(Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                }
                break;
            case 1:
                img = Sprite.player_up.getFxImage();
                if (moving) {
                    img = Sprite.movingImage(Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                }
                break;
            case 2:
                img = Sprite.player_left.getFxImage();
                if (moving) {
                    img =
                            Sprite.movingImage(Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                }
                break;
            default:
                img = Sprite.player_right.getFxImage();
                if (moving) {
                    img = Sprite
                            .movingImage(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                }
                break;
        }
    }

    @Override
    public void update() {
        movingBomber(input);
        chooseSprite();
        animate();
        moving = false;
        isCheckLive();
        CheckPassLevel();
        booleanProperty.set(checkPassLevel);
        booleanPropertyLive.set(checkLive);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
