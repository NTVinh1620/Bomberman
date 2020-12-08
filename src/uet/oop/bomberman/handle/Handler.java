package uet.oop.bomberman.handle;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.stand.Brick;
import uet.oop.bomberman.entities.stand.Portal;
import uet.oop.bomberman.entities.stand.Wall;

import java.util.List;

public class Handler {

    private int step;

    public void setStep(int step) {
        this.step = step;
    }

    public Handler() {
        this.step = 2;
    }

    public Handler(int step) {
        this.step = step;
    }

    private boolean checkWallLeft(Entity entity, Entity wall) {
        return entity.getX() - step >= wall.getX() + wall.getImg().getWidth();
    }

    private boolean checkWallRight(Entity entity, Entity wall) {
        return entity.getX() + entity.getImg().getWidth() + step <= wall.getX();
    }

    private boolean checkWallUp(Entity entity, Entity wall) {
        return entity.getY() - step >= wall.getY() + wall.getImg().getHeight();
    }

    private boolean checkWallDown(Entity entity, Entity wall) {
        return entity.getY() + entity.getImg().getHeight() + step <= wall.getY();
    }


    public boolean checkUp(Entity entity, List<Entity> objects) {
        for (Entity object : objects) {
            if ((object instanceof Wall || object instanceof Brick) &&
                    !checkWallUp(entity, object) && object.getY() < entity.getY() &&
                    !((entity.getX() >= object.getImg().getWidth() + object.getX()) ||
                            entity.getX() + entity.getImg().getWidth() <= object.getX())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDown(Entity entity, List<Entity> objects) {
        for (Entity object : objects) {
            if ((object instanceof Wall || object instanceof Brick) &&
                    !checkWallDown(entity, object) && object.getY() > entity.getY() &&
                    !((entity.getX() >= object.getImg().getWidth() + object.getX()) ||
                            entity.getX() + entity.getImg().getWidth() <= object.getX())) {
                return false;

            }
        }
        return true;
    }

    public boolean checkRight(Entity entity, List<Entity> objects) {
        for (Entity object : objects) {
            if ((object instanceof Wall || object instanceof Brick) &&
                    !checkWallRight(entity, object) && object.getX() > entity.getX() &&
                    !((entity.getY() >= object.getImg().getHeight() + object.getY()) ||
                            entity.getY() + entity.getImg().getWidth() <= object.getY())) {
                return false;

            }
        }
        return true;
    }

    public boolean checkLeft(Entity entity, List<Entity> objects) {
        for (Entity object : objects) {
            if ((object instanceof Wall || object instanceof Brick) &&
                    !checkWallLeft(entity, object) && object.getX() < entity.getX() &&
                    !((entity.getY() >= object.getImg().getHeight() + object.getY()) ||
                            entity.getY() + entity.getImg().getWidth() <= object.getY())) {
                return false;
            }
        }
        return true;
    }

    private boolean checkEnemy(Entity bomberman, Entity enemy) {
        int leftA = bomberman.getX();
        int rightA = (int) (bomberman.getX() + bomberman.getImg().getWidth());
        int topA = bomberman.getY();
        int bottomA = (int) (bomberman.getY() + bomberman.getImg().getHeight());

        int leftB = enemy.getX();
        int rightB = (int) (enemy.getX() + enemy.getImg().getWidth());
        int topB = enemy.getY();
        int bottomB = (int) (enemy.getY() + enemy.getImg().getHeight());

        if (bottomA <= topB) {
            return false;
        }
        if (topA >= bottomB) {
            return false;
        }
        if (rightA <= leftB) {
            return false;
        }
        return leftA < rightB;
    }

    public boolean checkTouch(Entity bomberman, List<Entity> objects) {
        for (Entity entity : objects) {
            if (!(entity instanceof Bomber) && checkEnemy(bomberman, entity)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPassLevel(Entity bomberman, List<Entity> objects) {
        for (Entity entity : objects) {
            if (entity instanceof Portal && checkEnemy(bomberman, entity)) {
                return false;
            }
        }
        return true;
    }


}
