package uet.oop.bomberman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Input;
import uet.oop.bomberman.scene.SceneEnd;
import uet.oop.bomberman.scene.SceneLibrary;
import uet.oop.bomberman.scene.SceneMiddle;
import uet.oop.bomberman.scene.SceneStart;

import static uet.oop.bomberman.scene.SceneMiddle.canvas;
import static uet.oop.bomberman.scene.SceneMiddle.gc;

public class GameUI extends Application {

    public static final double WIDTH = 31;
    public static final double HEIGHT = 15;


    public static final Scene scene1 =
            new Scene(new SceneStart(), Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
    public static final Scene scene3 =
            new Scene(new SceneEnd(), Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
    public static final Scene scene2 = new Scene(new SceneMiddle(), Sprite.SCALED_SIZE * WIDTH,
            Sprite.SCALED_SIZE * HEIGHT);


    public static Input input = new Input(scene2);
    public static Game game;

    @Override
    public void start(Stage stage) {

        game = new Game(gc, canvas);
        Game.initGame();

        SceneLibrary.setThePrimaryStage(stage, scene2, scene3, game);
        stage.getIcons().add(new Image("file:res/textures/SuperBombermanR.jpg"));
        stage.setTitle("BOMBERMAN");
        stage.setScene(scene1);
        stage.show();
    }

}
