package animals;

import java.util.Random;

public class BadWolf extends Animal {
    private Boolean lionMoveDown = true;

    public BadWolf() {
        super('W');
    }


    @Override
    public Attack fight(char opponent) {
        switch (opponent) {
            case 'B':
            case 'L':
                return Attack.SCISSORS;
            case 'S':
                return Attack.PAPER;
            default:
                return randomAttack();
        }
    }

    @Override
    public Move move() {
        int[] danger = new int[5];
        final int wolfsDanger = 4;
        final int lionsDanger = 3;
        lionMoveDown = !lionMoveDown;
        if (surroundings[0][1] == 'L' && lionMoveDown) {
            danger[4] += lionsDanger;

        }
        if (surroundings[1][0] == 'L' && !lionMoveDown) {
            danger[4] += lionsDanger;
        }

        if (surroundings[0][1] == 'W') {
            danger[0] += wolfsDanger;
        }
        if (surroundings[1][2] == 'W') {
            danger[1] += wolfsDanger;
        }
        if (surroundings[2][1] == 'W') {
            danger[2] += wolfsDanger;
        }
        if (surroundings[1][0] == 'W') {
            danger[3] += wolfsDanger;
        }
        if (surroundings[0][0] == 'W') {
            danger[0]++;
            danger[3]++;
        }
        if (surroundings[0][2] == 'W') {
            danger[0]++;
            danger[1]++;
        }
        if (surroundings[2][2] == 'W') {
            danger[1]++;
            danger[2]++;
        }

        if (surroundings[2][0] == 'W') {
            danger[3]++;
            danger[2]++;
        }
        if (surroundings[1][2] == 'W') {
            danger[2]++;
            danger[3]++;
        }
        Boolean shouldMove = false;
        Move bestMove = Move.HOLD;
        int leastDanger = danger[4];
        for (int i = 0; i < 4; i++) {
            if (danger[i] < leastDanger) {
                bestMove = Move.values()[i];
                leastDanger = danger[i];
            }
        }

        return bestMove;

    }

    public Attack randomAttack() {
        Random rand = new Random();
        switch (rand.nextInt(5)) {
            case 2:
                return Attack.SCISSORS;
            case 3:
                return Attack.ROCK;
            default:
                return Attack.PAPER;
        }
    }

}
