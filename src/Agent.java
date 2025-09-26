package src;

import java.util.Random;

public class Agent {

    private int punishments;
    private Random rand = new Random();

    Agent(int initialPunishment) {
        // Initialize the punishments of the agent
        this.punishments = initialPunishment;
    }

    // int[] decision() -> This function returns the direction that the agent would
    // like
    // to move the box
    //
    // INPUTS
    // None
    public int[] decision() {
        int randomNum = rand.nextInt(4 - 1 + 1) + 1;
        int[][] decisions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 0, 0 } };

        return decisions[randomNum];
    }

    // void applyPenalty() -> This function applies a specified penalty to the agent
    //
    // INPUTS
    // - int penalty -> The numerical penalty that should be applied to the agent
    public void applyPenalty(int penalty) {
        this.punishments += penalty;
    }

}