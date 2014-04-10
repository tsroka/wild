package animals;

import java.util.Random;

public class AlphaWolf extends Animal{
    private Boolean lionMoveDown = true;

    public AlphaWolf() {
        super('W');
    }
    @Override
    public Attack fight(char opponent) {
        switch(opponent){
            case 'B':
                return Attack.SCISSORS;

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
        int[] danger = new int[4];
        final int wolfsDanger = 4;
        lionMoveDown = !lionMoveDown;
        if(surroundings[0][1] == 'L' && lionMoveDown) {
            return Move.UP;
        }
        if(surroundings[1][0] == 'L'&& !lionMoveDown) {
            return Move.LEFT;
        }
        if(surroundings[0][1] == 'W') {
            danger[0] += wolfsDanger;
        }
        if(surroundings[1][2] == 'W') {
            danger[1] += wolfsDanger;
        }
        if(surroundings[2][1] == 'W') {
            danger[2] += wolfsDanger;
        }
        if(surroundings[1][0] == 'W') {
            danger[3] += wolfsDanger;
        }
        if(surroundings[0][0] == 'W') {
            danger[0]++;
            danger[3]++;
        }
        if(surroundings[0][2] == 'W') {
            danger[0]++;
            danger[1]++;
        }
        if(surroundings[2][2] == 'W') {
            danger[1]++;
            danger[2]++;
        }
        if(surroundings[1][2] == 'W') {
            danger[2]++;
            danger[3]++;
        }
        Boolean shouldMove = false;
        Move bestMove = Move.HOLD;
        int leastDanger = 4;
        for(int i = 0; i < 4; i++) {
            if (danger[i] < leastDanger) {
                bestMove = Move.values()[i];
            }
            if(danger[i] > 3) {
                shouldMove = true;
            }
        }
        if(shouldMove) {
            return bestMove;
        } else {
            return Move.HOLD;
        }
    }

    public Attack randomAttack() {
        Random rand = new Random();
        switch (rand.nextInt(5)){
            case 2: return Attack.SCISSORS;
            case 3: return Attack.ROCK;
            default: return Attack.PAPER;
        }
    }

}
