package uet.oop.bomberman.handle;

import java.util.List;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.map.CreateMap;
import uet.oop.bomberman.map.ShortestPath;

public class MovingEntity {
    private final Handler handler = new Handler(1);
    public ShortestPath shortestPath;
    CreateMap createMap;
    Entity bomberman;
    int[] s;
    int[] e;
    private int i = 0;

    public MovingEntity(Entity enemy, Entity bomberman) {
        this.bomberman = bomberman;
        s = new int[] {enemy.getY() / 32, enemy.getX() / 32};
        e = new int[] {bomberman.getY() / 32, bomberman.getX() / 32};
        int[] start = {enemy.getY() / 32, enemy.getX() / 32};
        int[] end = {bomberman.getY() / 32, bomberman.getX() / 32};
        createMap = new CreateMap();
        shortestPath = new ShortestPath(start, end);
    }


    public boolean moveEntityFromShortestPathLeft(Entity enemy, List<Entity> walls,
                                                  ShortestPath.Cell path) {
        if (path.y * 32 < enemy.getX() && handler.checkLeft(enemy, walls)) {
            enemy.setX(enemy.getX() - 1);
            enemy.setTransition(0);
            return true;
        }
        return false;
    }

    public boolean moveEntityFromShortestPathRight(Entity enemy, List<Entity> walls,
                                                   ShortestPath.Cell path) {
        if (path.y * 32 > enemy.getX() && handler.checkRight(enemy, walls)) {
            enemy.setX(enemy.getX() + 1);
            enemy.setTransition(1);
            return true;
        }
        return false;
    }

    public boolean movingFromShortestPathUp(Entity enemy, List<Entity> walls,
                                            ShortestPath.Cell path) {
        if (path.x * 32 < enemy.getY() && handler.checkUp(enemy, walls)) {
            enemy.setY(enemy.getY() - 1);
            return true;
        }
        return false;
    }

    public boolean movingFromShortestPathDown(Entity enemy, List<Entity> walls,
                                              ShortestPath.Cell path) {
        if (path.x * 32 > enemy.getY() && handler.checkDown(enemy, walls)) {
            enemy.setY(enemy.getY() + 1);
            return true;
        }
        return false;
    }


    public boolean moveFromShortestPath(Entity enemy, List<Entity> walls,
                                        ShortestPath.Cell path1) {
        return moveEntityFromShortestPathLeft(enemy, walls, path1) ||
            moveEntityFromShortestPathRight(enemy, walls, path1) ||
            movingFromShortestPathUp(enemy, walls, path1) ||
            movingFromShortestPathDown(enemy, walls, path1);
    }


    public void move(Entity enemy, Entity bomberman, List<Entity> objects) {
        if (bomberman.getY() / 32 != e[0] || bomberman.getX() / 32 != e[1]) {
            shortestPath.setPath(enemy, bomberman);
            s = new int[] {enemy.getY() / 32, enemy.getX() / 32};
            e = new int[] {bomberman.getY() / 32, bomberman.getX() / 32};
            this.i = 1;
        }
        if (shortestPath.path != null && shortestPath.path.size() > 0 &&
            i < shortestPath.path.size()) {
            if (!moveFromShortestPath(enemy, objects,
                shortestPath.path.get(i))) {
                i++;
            }
        }
    }

    // kiem tra chay xuyen tuong

    public void moveNotWall(Entity enemy, Entity bomberman) {
        if (enemy.getX() > bomberman.getX()) {
            enemy.setX(enemy.getX() - 1);
            enemy.setTransition(0);
        }
        if (enemy.getX() < bomberman.getX()) {
            enemy.setX(enemy.getX() + 1);
            enemy.setTransition(1);
        }
        if (enemy.getY() > bomberman.getY()) {
            enemy.setY(enemy.getY() - 1);
        }
        if (enemy.getY() < bomberman.getY()) {
            enemy.setY(enemy.getY() + 1);
        }
    }


}
