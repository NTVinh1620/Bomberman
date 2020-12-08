package uet.oop.bomberman.scene;

import javafx.scene.Scene;
import javafx.stage.Stage;
import uet.oop.bomberman.Game;

public abstract class SceneLibrary {
    private static Stage stage;
    private static Scene sceneTwo;
    private static Scene sceneThree;
    private static Game game;

    public static void setThePrimaryStage(Stage stage, Scene sceneTwo, Scene sceneThree,
                                          Game game) {
        SceneLibrary.stage = stage;
        SceneLibrary.sceneTwo = sceneTwo;
        SceneLibrary.game = game;
        SceneLibrary.sceneThree = sceneThree;
    }

    public static void switchToTwo() {
        game.PlayGame();
        stage.setScene(sceneTwo);
    }

    public static void switchToThree() {
        stage.setScene(sceneThree);
    }
}
