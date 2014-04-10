package animals;

import java.util.*;
import java.util.stream.Collectors;

public class MutaAlphaWolf extends Animal {
    private static final Attack[] ATTACKS = {Attack.PAPER, Attack.ROCK, Attack.SCISSORS};
    private int iteration = 0;
    private Random rand = new Random();

    public MutaAlphaWolf() {
        super('W');
    }

    class Pair<E1, E2> {
        final E1 _1;
        final E2 _2;

        Pair(E1 _1, E2 _2) {
            this._1 = _1;
            this._2 = _2;
        }
    }

    enum Direction {
        UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0), HOLD(0, 0);
        public final int dx, dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public Move move() {
            return Move.valueOf(this.name());
        }
    }

    @Override
    public Attack fight(char opponent) {
        switch (opponent) {
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
        List<Direction> plausibleMoves = calculatePlausibleMoves();
        iteration++;

        if (plausibleMoves.contains(Direction.HOLD)) {
            return Move.HOLD;
        } else {
            List<Move> moves = plausibleMoves.stream().map(Direction::move).collect(Collectors.toList());
            return moves.get(rand.nextInt(moves.size()));
        }

    }

    private Collection<Direction> possibleDirections(char animal) {
        switch (animal) {
            case 'W':
                return Arrays.asList(Direction.values());
            case 'L':
                Direction currDir = iteration % 2 == 1 ? Direction.DOWN : Direction.RIGHT;
                return Collections.singleton(currDir);
            default:
                return Collections.emptyList();
        }
    }

    private List<Direction> calculatePlausibleMoves() {
        int[][] dangerLevels = new int[3][3];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (x != 1 || y != 1) {
                    char animal = surroundings[y][x];
                    updateDangerFor(animal, y, x, dangerLevels);
                }
            }
        }

        List<Pair<Direction, Integer>> dirDangers = translateDangerLevels(dangerLevels);

        Integer min = dirDangers.stream()
                .map(p -> p._2)
                .min(Integer::compare)
                .get();

        return dirDangers.stream()
                .filter(pair -> min.equals(pair._2))
                .map(p -> p._1)
                .collect(Collectors.toList());
    }

    private List<Pair<Direction, Integer>> translateDangerLevels(int[][] dangerLevels) {
        return Arrays.asList(Direction.values()).stream()
                .map(dir -> new Pair<>(dir, dangerLevels[1 + dir.dy][1 + dir.dx]))
                .collect(Collectors.toList());
    }

    private void updateDangerFor(char animal, int y, int x, int[][] dangerLevels) {
        Collection<Direction> possibleDirections = possibleDirections(animal);
        for (Direction possibleDirection : possibleDirections) {
            int nx = x + possibleDirection.dx;
            int ny = y + possibleDirection.dy;
            if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
                dangerLevels[ny][nx] += dangerLevelFor(animal, possibleDirection);
                if (animal == 'W') {
                    dangerLevels[1][1] += 101;
                }
            }
        }
    }

    private int dangerLevelFor(char animal, Direction possibleDirection) {
        switch (animal) {
            case 'L':
                return 106;
            case 'W':
                if (Direction.HOLD == possibleDirection) {
                    return 108;
                } else {
                    return 104;
                }
            default:
                return 0;
        }
    }

    public Attack randomAttack() {
        return ATTACKS[rand.nextInt(ATTACKS.length)];
    }
}
