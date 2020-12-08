package uet.oop.bomberman;

import javafx.application.Application;

public class BombermanGame {

    public static void main(String[] args) {
        new GameUI();
        Application.launch(GameUI.class, args);
    }
}
