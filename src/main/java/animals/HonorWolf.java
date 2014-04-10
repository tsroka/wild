package animals;
public class HonorWolf extends Animal {

    private int moves = 0;

    public HonorWolf() {
        super('W');
    }

    @Override
    public Attack fight(char opponent) {
        switch(opponent) {
            case 'L':
                return Attack.SCISSORS;
            case 'B':
                return Attack.SCISSORS;
            case 'S':
                return Attack.PAPER;
            default:
                return Attack.PAPER;
        }
    }

    public Move move() {
        int numWolves = 0, numLions = 0;

        moves++;

        for (int y = 0; y != 3; y++) {
            for (int x = 0; x != 3; x++) {
                if(surroundings[y][x] != ' ') {
                    if(surroundings[y][x] == 'W') {
                        numWolves++;
                    } else if(surroundings[y][x] == 'L') {
                        numLions++;
                    }
                }
            }
        }

        if (numWolves == 1 && numLions == 0) {
            return Move.HOLD;
        }

        if (surroundings[0][1] == 'L' && moves%2 != 0) {
            return Move.UP;
        }

        if (surroundings[1][0] == 'L' && moves%2 == 0) {
            return Move.LEFT;
        }

        if (surroundings[0][1] == 'W') {
            if (surroundings[2][1] == ' ' || surroundings[2][1] == 'S') {
                return Move.DOWN;
            } else if (surroundings[1][2] == ' ' || surroundings[1][2] == 'S') {
                return Move.RIGHT;
            } else if (surroundings[1][0] == ' ' || surroundings[1][0] == 'S') {
                return Move.LEFT;
            } else {
                return Move.UP;
            }
        }

        if (surroundings[1][0] == 'W') {
            if (surroundings[1][2] == ' ' || surroundings[1][2] == 'S') {
                return Move.RIGHT;
            } else if (surroundings[0][1] == ' ' || surroundings[0][1] == 'S') {
                return Move.UP;
            } else if (surroundings[2][1] == ' ' || surroundings[2][1] == 'S') {
                return Move.DOWN;
            } else {
                return Move.LEFT;
            }
        }

        if (surroundings[1][2] == 'W') {
            if (surroundings[1][0] == ' ' || surroundings[1][0] == 'S') {
                return Move.LEFT;
            } else if (surroundings[0][1] == ' ' || surroundings[0][1] == 'S') {
                return Move.UP;
            } else if (surroundings[2][1] == ' ' || surroundings[2][1] == 'S') {
                return Move.DOWN;
            } else {
                return Move.RIGHT;
            }
        }

        if (surroundings[2][1] == 'W') {
            if (surroundings[0][1] == ' ' || surroundings[0][1] == 'S') {
                return Move.UP;
            } else if (surroundings[1][0] == ' ' || surroundings[1][0] == 'S') {
                return Move.LEFT;
            } else if (surroundings[1][2] == ' ' || surroundings[1][2] == 'S') {
                return Move.RIGHT;
            } else {
                return Move.DOWN;
            }
        }

        return Move.HOLD;
    }
}