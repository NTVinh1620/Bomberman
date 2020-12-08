package uet.oop.bomberman.map;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.enemy.Balloon;
import uet.oop.bomberman.entities.enemy.Kondoria;
import uet.oop.bomberman.entities.enemy.Oneal;
import uet.oop.bomberman.entities.stand.Brick;
import uet.oop.bomberman.entities.stand.Grass;
import uet.oop.bomberman.entities.stand.Item.BombItem;
import uet.oop.bomberman.entities.stand.Item.FlameItem;
import uet.oop.bomberman.entities.stand.Item.SpeedItem;
import uet.oop.bomberman.entities.stand.Portal;
import uet.oop.bomberman.entities.stand.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.handle.Handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateMap {
    public static final String[] pathData = {"res/levels/Level1.txt", "res/levels/Level2.txt"};
    public int[][] a = new int[15][31];
    private Entity bomberman;
    private Handler handler;
    public static int level = 0;
    private String path = pathData[level];

    public CreateMap() {
        createBinaryMap();
    }

    public CreateMap(String path) {
        this.path = path;
        createBinaryMap();
    }

    public void createMaps(List<Entity> stillObjects, List<Entity> entities, List<Entity> items, Handler handler,
                           Entity bomberman, String path) {
        this.bomberman = bomberman;
        this.handler = handler;
        this.path = path;
        addObject(stillObjects, entities, items);
    }

    public void createBinaryMap() {
        int i = 0;
        List<String> datafile = createObjects();
        assert datafile != null;
        for (String temp : datafile) {
            String[] tempFile = temp.split("");
            int j = 0;
            for (String x : tempFile) {
                if (x.equals(".") || x.equals("1") || x.equals("2")) {
                    a[i][j] = 1;
                } else {
                    a[i][j] = 0;
                }
                j += 1;
            }
            i += 1;
        }
    }

    private void addObject(List<Entity> stillObjects, List<Entity> entities, List<Entity> items) {
        List<String> a = createObjects();
        int i = 0;
        assert a != null;
        for (String temp : a) {
            String[] TempArray = temp.split("");
            for (int j = 0; j < TempArray.length; j++) {
                switch (TempArray[j]) {
                    case "#":
                        stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
                    case "*":
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case "s":
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        items.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage(), bomberman, handler, items));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case "f":
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        items.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage(), bomberman, items));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case "b":
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        items.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage(), bomberman, items));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case "x":
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case "2":
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        entities.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage(), handler,
                                stillObjects, bomberman));
                        break;
                    case "1":
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        entities.add(
                                new Balloon(j, i, Sprite.balloom_right1.getFxImage(), stillObjects));
                        break;
                    case "3":
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        entities.add(
                                new Kondoria(j, i, Sprite.kondoria_right1.getFxImage(), handler,
                                        bomberman));
                    default:
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        break;
                }
            }
            i += 1;
        }
    }

    private List<String> createObjects() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
            String line;
            List<String> datafile = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                datafile.add(line);
            }
            return datafile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
