package uet.oop.bomberman.handle;

import java.util.List;
import uet.oop.bomberman.entities.Entity;

public class MovingLowEntity {

    private boolean checkCol;
    private boolean checkRow;

    public void moveLowEntityCol(Entity entity, Handler handler, List<Entity> objects) {
        if (!checkCol && handler.checkLeft(entity, objects)) {
            entity.setX(entity.getX() - 1);
            entity.setTransition(0);
        } else {
            checkCol = true;
        }
        if (checkCol && handler.checkRight(entity, objects)) {
            entity.setX(entity.getX() + 1);
            entity.setTransition(1);
        } else {
            checkCol = false;
        }
    }

    public void moveLowEntityRow(Entity entity, Handler handler, List<Entity> objects) {
        if (!checkRow && handler.checkUp(entity, objects)) {
            entity.setY(entity.getY() - 1);
        } else {
            checkRow = true;
        }
        if (checkRow && handler.checkDown(entity, objects)) {
            entity.setY(entity.getY() + 1);
        } else {
            checkRow = false;
        }
    }
}
