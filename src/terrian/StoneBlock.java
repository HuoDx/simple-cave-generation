package terrian;

import render.IRenderer;

import java.awt.*;

import static terrian.Terrian.BLOCK_SIZE;

public class StoneBlock implements IRenderer {

    Vector2 position;
    public StoneBlock(Vector2 position) {
        this.position = position;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        g2d.fillRoundRect(position.x * BLOCK_SIZE + 4, position.y * BLOCK_SIZE + 4, BLOCK_SIZE - 4, BLOCK_SIZE - 4, 4, 4);
    }
}
