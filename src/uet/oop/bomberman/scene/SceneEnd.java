package uet.oop.bomberman.scene;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.GameUI;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Input;
import uet.oop.bomberman.map.CreateMap;

public class SceneEnd extends Group {
    public SceneEnd() {

        Button playAgain = new Button();
        playAgain.setText("PLAY AGAIN");
        playAgain.setFont(Font.font("Monaco", 20));
        playAgain.setFont(Font.font("Monaco", 20));
        playAgain.setTextFill(Color.rgb(243, 151, 1));
        playAgain.setStyle("-fx-background-color: #0869ff; ");
        playAgain.setMaxSize(150, 40);
        playAgain.setMinSize(150, 40);
        playAgain.setLayoutX(421);
        playAgain.setLayoutY(350);


        Button exitGame = new Button();
        exitGame.setText("EXIT");
        exitGame.setFont(Font.font("Monaco", 20));
        exitGame.setFont(Font.font("Monaco", 20));
        exitGame.setTextFill(Color.rgb(243, 151, 1));
        exitGame.setStyle("-fx-background-color: #0869ff; ");
        exitGame.setMaxSize(150, 40);
        exitGame.setMinSize(150, 40);
        exitGame.setLayoutX(421);
        exitGame.setLayoutY(400);

        this.setStyle("-fx-background-image: url('http://textures/SuperBombermanR2.jpg')");

        Image image = new Image("file:res/textures/SuperBombermanR.jpg");
        ImageView imageView = new ImageView(image);

        imageView.setImage(image);
        imageView.setFitWidth(Sprite.SCALED_SIZE * GameUI.WIDTH);

        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        Text text = new Text(10, 32, "Score: " + Game.score);
        text.setFont(new Font(40));


        getChildren().addAll(imageView);
        getChildren().addAll(playAgain, exitGame);
        getChildren().addAll(text);

        exitGame.setOnAction(event -> Platform.exit());
        playAgain.setOnAction(event -> {
            GameUI.input = new Input(GameUI.scene2);
            FlameSegment.resultBomber = true;
            FlameSegment.resultBalloon = true;
            FlameSegment.resultOneal = true;
            FlameSegment.resultKon = true;
            FlameSegment.timeBomberDead = 15;
            FlameSegment.timeBalloonDead = 30;
            FlameSegment.timeOnealDead = 30;
            FlameSegment.timeKonDead = 30;
            Game.score = 0;
            CreateMap.level = 0;
            Game.cleanUpGame();
            Game.initGame();
            SceneLibrary.switchToTwo();
        });
    }
}
