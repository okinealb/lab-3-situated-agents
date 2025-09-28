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
    public Decision decision() {
        Decision[] decisions = { Decision.Up, Decision.Down, Decision.Left, Decision.Right, Decision.Still };
        int randomNum = rand.nextInt(decisions.length);

        return decisions[randomNum];
    }
    
    // Special decision method for fast mode where agents under the object choose Down
    public Decision decisionFastMode(boolean isUnderObject) {
        if (isUnderObject) {
            return Decision.Down;
        } else {
            Decision[] decisions = { Decision.Up, Decision.Down, Decision.Left, Decision.Right, Decision.Still };
            int randomNum = rand.nextInt(decisions.length);
            return decisions[randomNum];
        }
    }

    // void applyPenalty() -> This function applies a specified penalty to the agent
    //
    // INPUTS
    // - int penalty -> The numerical penalty that should be applied to the agent
    public void applyPenalty(int penalty) {
        this.punishments += penalty;
    }

    // void getPenalties() -> This function applies a specified penalty to the agent
    //
    // INPUTS
    // None
    public int getPenalties() {
        return this.punishments;
    }
} 