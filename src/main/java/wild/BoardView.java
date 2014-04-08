package wild;

import animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;


public class BoardView extends JComponent {

    private final int rows;
    private final int cols;

    private final Game game;
    private final Map<Class<?>, Color> colorMap;

    public BoardView(Game game, Map<Class<?>, Color> colorMap) {
        super();
        this.game = game;
        this.colorMap = colorMap;
        this.rows = game.getBoard().size();
        this.cols = game.getBoard().get(0).size();
        setMinimumSize(new Dimension(cols*15,rows*15));
        setPreferredSize(new Dimension(cols * 15, rows * 15));

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        Dimension cellSize = new Dimension(getWidth() / cols, getHeight() / rows);
//        Dimension cellSize = new Dimension(20, 20);
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++) {
                ArrayList<Animal> animals = game.getBoard().get(row).get(col);
                Animal animal = animals.isEmpty() ? null : animals.get(0);
                drawCell(row, col, animal, cellSize, g);
            }


    }

    private void drawCell(int row, int col, Animal animal, Dimension cellSize, Graphics g) {
        int x = cellSize.width * col;
        int y = cellSize.height * row;
        g.setColor(Color.CYAN);
        g.drawRect(x, y, cellSize.width, cellSize.height);

        String name = animal != null ? Character.toString(animal.letter) : null;


        if (name != null) {
            int txtHeight = g.getFontMetrics().getHeight();
            int txtWidth = g.getFontMetrics().stringWidth(name);
            Color color = colorMap.get(animal.getClass());
            color = color == null ? Color.BLACK : color;
            g.setColor(color);
            g.drawString(name, x+((cellSize.width-txtWidth)/2), y - txtHeight/2);
        }


    }
}
