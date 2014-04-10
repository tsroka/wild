package wild;

import animals.*;
import com.google.common.collect.ImmutableMap;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Wild {

    private static final Class[] classes = {Bear.class, Lion.class, Stone.class,
//            Wolf.class,
            AlphaWolf.class,
//            HonorWolf.class,
            MutaAlphaWolf.class
    };
    private static final Map<Class<?>, Color> colorMap = ImmutableMap.<Class<?>, Color>builder()
            .put(AlphaWolf.class, Color.RED)
            .put(Wolf.class, Color.BLACK)
            .put(BadWolf.class, Color.BLUE)
            .put(MutaAlphaWolf.class, Color.GREEN)
            .put(HonorWolf.class, Color.BLUE)
            .build();

    public static final int MAP_SIZE = Math.round((float) Math.sqrt(classes.length + 3) * 20);


    public static void main(String[] args) {

        int size = Math.round((float) Math.sqrt(classes.length + 3) * 20);
        Game game = new Game(size);

        Statistics stats = new Statistics(game, classes);

        for (Class c : classes)
            game.populate(c, 100);
//        game.getBoard().get(1).get(1).add(new Lion());
//        game.getBoard().get(1).get(2).add(new MutaAlphaWolf());
        stats.update();

        JFrame gui = new JFrame();
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container pane = gui.getContentPane();

        JLabel boardLabel = new JLabel();
        boardLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        boardLabel.setText(game.toString());
//        pane.add(boardLabel, BorderLayout.WEST);

        BoardView boardView = new BoardView(game, colorMap);
        pane.add(boardView, BorderLayout.CENTER);

        JLabel statsLabel = new JLabel();
        statsLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        statsLabel.setText(stats.toString());
        pane.add(statsLabel, BorderLayout.EAST);

        gui.pack();
        gui.setVisible(true);

        while (true) {
            game.iterate();
            stats.update();
//            boardLabel.setText(game.toString());
            boardView.repaint();
            statsLabel.setText(stats.toString());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
        }
    }

}
