package uet.oop.bomberman.scene;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.GameUI;
import uet.oop.bomberman.audio.SimpleAudioPlayer;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.GameUI.HEIGHT;
import static uet.oop.bomberman.GameUI.WIDTH;

public class SceneMiddle extends Group {
    public static Canvas canvas;
    public static GraphicsContext gc;

    public SceneMiddle() {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);

        gc = canvas.getGraphicsContext2D();

        this.getChildren().add(canvas);

        Bomber.booleanProperty.addListener((observable, oldValue, newValue) -> {
            Game.cleanUpGame();
            Game.initGame();
            Bomber.checkPassLevel = true;
        });

        Bomber.booleanPropertyLive.addListener((observable, oldValue, newValue) -> {
            Game.cleanUpGame();
            Game.initGame();
            Bomber.checkLive = true;
            GameUI.game.PausedGame();
            SimpleAudioPlayer.playWhenEndGame();
            SceneLibrary.switchToThree();
        });

    }
}
