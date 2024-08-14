package LldProblems;

import java.util.*;

public class SnakeLadder {
    public static void main(String[] args) {
        Entity snake1 = new Snake(12, 28);
        Entity snake2 = new Snake(34, 78);
        Entity snake3 = new Snake(6, 69);
        Entity snake4 = new Snake(65, 84);

        Entity ladder1 = new Ladder(24, 56);
        Entity ladder2 = new Ladder(43, 83);
        Entity ladder3 = new Ladder(3, 31);
        Entity ladder4 = new Ladder(72, 91);

        Board board = new Board(10);
        board.addEntity(snake1);
        board.addEntity(snake2);
        board.addEntity(snake3);
        board.addEntity(snake4);

        board.addEntity(ladder1);
        board.addEntity(ladder2);
        board.addEntity(ladder3);
        board.addEntity(ladder4);

        Dice dice = new Dice(6);

        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        Player player3 = new Player("p3");
        Queue<Player> queue = new LinkedList<>();
        queue.add(player1);
        queue.add(player2);
        queue.add(player3);

        Game game = new Game(board, queue, dice);
        game.launch();
    }

}

class Player {
    private int position;
    private String name;

    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

class Dice {
    private int max;
    private Random r;

    public Dice(int max) {
        this.max = max;
        r = new Random();
    }

    public int roll() {
        return r.nextInt(max) + 1;
    }
}

/**
 * Entity
 */
abstract class Entity {
    private int start;
    private int end;

    public abstract int getActionPosition();

    public abstract int getStartPosition();

    public abstract String getId();

    protected Entity(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

class Snake extends Entity {

    public Snake(int start, int end) {
        super(start, end);
    }

    @Override
    public int getActionPosition() {
        return this.getStart();
    }

    @Override
    public String getId() {
        return "S-" + this.getEnd();
    }

    @Override
    public int getStartPosition() {
        return this.getEnd();
    }

}

class Ladder extends Entity {

    public Ladder(int start, int end) {
        super(start, end);
    }

    @Override
    public int getActionPosition() {
        return this.getEnd();
    }

    @Override
    public String getId() {
        return "L-" + this.getStart();
    }

    @Override
    public int getStartPosition() {
        return this.getStart();
    }

}

class Board {
    private int dimmensions;
    private Map<Integer, Entity> entities;

    public Board(int dimmensions) {
        this.dimmensions = dimmensions;
        entities = new HashMap<>();
    }

    public int getBoardSize() {
        return dimmensions * dimmensions;
    }

    public void addEntity(Entity entity) {
        entities.put(entity.getStartPosition(), entity);
    }

    public Entity getEntity(int position) {
        return entities.getOrDefault(position, null);
    }

    public void printBoard() {
        for (int i = 1; i <= dimmensions; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 1; j <= dimmensions; j++) {
                int key = ((i - 1) * 10) + j;
                if (entities.get(key) != null) {
                    builder.append(entities.get(key).getId() + " ");
                } else {
                    builder.append(key + " ");
                }
            }
            System.out.println(builder.toString());
        }
    }
}

class Game {
    private Board board;
    private Queue<Player> players;
    private Dice dice;

    public Game(Board board, Queue<Player> players, Dice dice) {
        this.board = board;
        this.players = players;
        this.dice = dice;
    }

    public void launch() {
        System.out.println("Game started");
        board.printBoard();
        while (players.size() > 1) {
            Player player = players.poll();
            makeMove(player);
            if (player.getPosition() == board.getBoardSize()) {
                System.out.println(player.getName() + " has win the game");
                break;
            } else {
                players.add(player);
            }
        }
    }

    public void makeMove(Player player) {
        System.out.println(player.getName() + " turn");
        System.out.println("Touch anywhere to roll the dice");
        Scanner scanner = new Scanner(System.in);
        scanner.close();
        int currentPos = player.getPosition();
        int rollValue = dice.roll();
        currentPos += rollValue;
        if (currentPos > board.getBoardSize()) {
            return;
        }
        if (board.getEntity(currentPos) != null) {
            player.setPosition(board.getEntity(currentPos).getActionPosition());
            System.out.println(player.getName() + " moved to " + player.getPosition());
        } else {
            player.setPosition(currentPos);
        }
    }

}
