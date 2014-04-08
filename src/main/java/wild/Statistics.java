
package wild;

public final class Statistics {
    
    private final Game game;
    private final Class[] classes;
    private final int[] living;
    
    protected Statistics(Game game, Class[] classes) {
        this.game = game;
        this.classes = classes;
        this.living = new int[classes.length];
    }
    
    protected void update() {
        for (int i = 0; i < classes.length; i++) {
            living[i] = game.poll(classes[i]);
        }
    }
    
    public String toString() {
        String s = "<html>";
        for (int i = 0; i < classes.length; i++) {
            s += classes[i] + "&nbsp;-&nbsp;" + living[i] + "<br>";
        }
        return s + "</html>";
    }
}
