package uet.oop.bomberman.scene;


import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.GameUI;
import uet.oop.bomberman.graphics.Sprite;

public class SceneStart extends Group {

    public int check = 0;

    public SceneStart() {

        Button aButton = new Button();

        aButton.setText("PLAY");
        aButton.setFont(Font.font("Monaco", 20));
        aButton.setTextFill(Color.rgb(243, 151, 1));
        aButton.setStyle("-fx-background-color: #0869ff; ");
        aButton.setMaxSize(90, 40);
        aButton.setMinSize(90, 40);
        aButton.setLayoutX(451);
        aButton.setLayoutY(400);

        this.setStyle("-fx-background-image: url('http://textures/SuperBombermanR2.jpg')");

        Image image = new Image("file:res/textures/SuperBombermanR.jpg");
        ImageView imageView = new ImageView(image);

        imageView.setImage(image);
        imageView.setFitWidth(Sprite.SCALED_SIZE * GameUI.WIDTH);

        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        getChildren().addAll(imageView);
        getChildren().addAll(aButton);

        aButton.setOnAction(event -> {
            check = 1;
            SceneLibrary.switchToTwo();
        });
    }
}
