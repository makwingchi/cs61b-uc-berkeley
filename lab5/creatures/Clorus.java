package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Random;


public class Clorus extends Creature{
    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    public void attack(Creature c) {
        double creatureEnergy = c.energy();
        energy += creatureEnergy;
    }

    public void move() {
        energy -= 0.03;
    }

    public void stay() {
        energy -= 0.01;
    }

    public Clorus replicate() {
        double totalEnergy = energy;
        energy = totalEnergy / 2;
        Clorus offspring = new Clorus(totalEnergy / 2);
        return offspring;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        // Rule 1
        for (Direction dir : neighbors.keySet()) {
            Occupant o = neighbors.get(dir);
            if (o.name() == "empty") {
                emptyNeighbors.addLast(dir);
            } else if (o.name() == "plip") {
                plipNeighbors.addLast(dir);
            }
        }

        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (plipNeighbors.size() > 0) {
            Random randomPlip = new Random();
            int plipIndex = randomPlip.nextInt(plipNeighbors.size());

            for (int i = 0; i < plipIndex; i += 1) {
                plipNeighbors.removeFirst();
            }

            Direction plipSpace = plipNeighbors.removeFirst();
            return new Action(Action.ActionType.ATTACK, plipSpace);
        }

        // Rule 3
        Random randomEmpty = new Random();
        int emptyIndex = randomEmpty.nextInt(emptyNeighbors.size());

        for (int j = 0; j < emptyIndex; j += 1){
            emptyNeighbors.removeFirst();
        }

        Direction emptySpace = emptyNeighbors.removeFirst();

        if (this.energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, emptySpace);
        }

        // Rule 4
        return new Action(Action.ActionType.MOVE, emptySpace);
    }
}
