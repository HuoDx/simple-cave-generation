package terrian;

import render.IRenderer;

import java.awt.*;
import java.util.ArrayList;

public class Terrian implements IRenderer{
    public static int BLOCK_SIZE = 8;
    int[][] dataMap;
    ArrayList<IRenderer> blockRenderers;

    public Terrian(int[][] dataMap) {
        this.dataMap = dataMap;
        blockRenderers = new ArrayList<>();
        for(int i = 0; i < dataMap.length; i++) {
            for(int j = 0; j < dataMap[i].length; j++) {
                if(dataMap[i][j] == 1) blockRenderers.add(new StoneBlock(new Vector2(i,j)));
            }
        }
    }

    @Override
    public void render(Graphics2D g2d) {

        blockRenderers.forEach(r -> r.render(g2d));
    }
    public static int[][] generateRandom(Vector2 size, float prob) {
        int[][] map = new int[size.x][size.y];
        for(int i = 0; i < size.x; i++) {
            for(int j = 0; j < size.y; j++) {
                map[i][j] = Math.random() > prob ? 1 : 0;
            }
        }
        return map;
    }
    static final int[][] directions = new int[][]{{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
    public static int[][] generateBetter(Vector2 size) {
        int fx,fy;
        float cumulative;
        int[][] map = generateRandom(size, 0.52f);
        for(int i = 0; i < size.x; i++) {
            for(int j = 0; j < size.y; j++) {
                cumulative = map[i][j];
                for(int k = 0; k < 8; k++) {
                    fx = i + directions[k][0];
                    fy = j + directions[k][1];

                    fx = fx >= size.x ? size.x - 1 : fx;
                    fx = fx < 0 ? 0 : fx;

                    fy = fy >= size.y ? size.y - 1 : fy;
                    fy = fy < 0 ? 0 : fy;

                    cumulative += map[fx][fy];
                }
                map[i][j] = cumulative > 4 ? 1 : 0;
            }
        }
        return map;
    }

}
