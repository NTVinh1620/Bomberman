package uet.oop.bomberman.input;

import javafx.scene.Scene;

public class Input {
    private final Scene scene;
    public boolean up, down, left, right, space;

    public Input(Scene scene) {
        this.scene = scene;
        keyPressed();
        keyRealeased();
    }

    public void keyPressed() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    up = true;
                    break;
                case DOWN:
                    down = true;
                    break;
                case LEFT:
                    left = true;
                    break;
                case RIGHT:
                    right = true;
                    break;
                case SPACE:
                    space = true;
                    break;
                default:
                    break;
            }
        });
    }

    public void keyRealeased() {
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    up = false;
                    break;
                case DOWN:
                    down = false;
                    break;
                case LEFT:
                    left = false;
                    break;
                case RIGHT:
                    right = false;
                    break;
                case SPACE:
                    space = false;
                    break;
                default:
                    break;
            }
        });
    }
}

