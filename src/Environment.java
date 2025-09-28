package src;


public class Environment {
    final int M;
    final int N;
    final int E;
    final int[] weights;
    Box box;
    Agent[][] agents;


    Environment(int M, int N, int E, int m, int n, int x, int y, int[] weights) {
        this.M = M;
        this.N = N;
        this.E = E;
        this.weights = weights;

        this.box = new Box(m, n, x, y);
        this.agents = new Agent[M][N];

        for (int i = 0; i < M; i ++)
            for (int j = 0; j < N; j ++)
                this.agents[i][j] = new Agent(0);
    } // Environment(int, int, int, int)


    void drawEnvironment() {
        System.out.println("Environment:");
        for (int r = 0; r < N; r ++) {  // N is height (rows)
            for (int c = 0; c < M; c ++) {  // M is width (columns)
                String ch;
                
                // Check if this cell is part of the box
                if (this.box.isContained(c, r)) {
                    ch = "B";
                } 
                // Check if this cell is in a penalty area (border)
                else if (onBorder(c)) {
                    ch = "E";
                }
                else {
                    ch = ".";
                }

                System.out.print(ch + " ");
            }
            System.out.println();
        }
        System.out.println();
    } // void drawEnvironment()

    /**
     * 
     */
    boolean onBorder(int x) {
        return (x < E || x > N - 1 - E);
    } // void addPenalties()

    /**
     * Get the most popular decision according to the weights.
     * @return
     */
    Decision queryAgents() {
        return queryAgents(false);
    }
    
    /**
     * Get the most popular decision according to the weights.
     * @param fastMode If true, agents under the object always choose Down
     * @return
     */
    Decision queryAgents(boolean fastMode) {
        int[] totalAll = new int[5];  // Count for all agents
        int[] totalUnder = new int[5];  // Count for agents under the object
        
        // Count decisions for all agents and specifically for agents under the object
        for (int r = 0; r < M; r ++) {
            for (int c = 0; c < N; c ++) {
                boolean isUnderObject = this.box.isContained(r, c);
                
                Decision agentDecision;
                if (fastMode) {
                    agentDecision = this.agents[r][c].decisionFastMode(isUnderObject);
                } else {
                    agentDecision = this.agents[r][c].decision();
                }
                
                int index = switch(agentDecision) {
                    case Decision.Up -> 0;
                    case Decision.Down -> 1;
                    case Decision.Left -> 2;
                    case Decision.Right -> 3;
                    case Decision.Still -> 4;
                    default -> throw new IllegalArgumentException();
                };

                totalAll[index]++;
                
                // Only count agents under the object for the final decision
                if (isUnderObject) {
                    totalUnder[index]++;
                }
            } // for
        } // for

        // Print out the number of agents per each action (all agents)
        System.out.printf(
            "Number of agents (all) that chose each action:\n"
            + "  Up:     %d\n"
            + "  Down:   %d\n"
            + "  Left:   %d\n"
            + "  Right:  %d\n"
            + "  Still:  %d\n",
            totalAll[0], totalAll[1], totalAll[2], totalAll[3], totalAll[4]
        );
        
        // Print out the number of agents under the object per each action
        System.out.printf(
            "Number of agents under object that chose each action:\n"
            + "  Up:     %d\n"
            + "  Down:   %d\n"
            + "  Left:   %d\n"
            + "  Right:  %d\n"
            + "  Still:  %d\n\n",
            totalUnder[0], totalUnder[1], totalUnder[2], totalUnder[3], totalUnder[4]
        );

        // Return the decision based only on agents under the object
        return switch (findMax(totalUnder)) {
            case 0 -> Decision.Up;
            case 1 -> Decision.Down;
            case 2 -> Decision.Left;
            case 3 -> Decision.Right;
            case 4 -> Decision.Still;
            default -> throw new IllegalArgumentException();
        };
    } // Decision queryAgents()

    /** 
     * Classify the current position as besides, under, or other by returning
     * the apt scalar weight.
     * @param r
     * @param c
     * @return The weight corresponding to the current position.
     */
    int toPoints(int r, int c) {
        if (this.box.isContained(r, c)) return this.weights[0];
        if (this.box.isBeside(r, c))    return this.weights[1];
        else                            return this.weights[2];
    } // int toPoints(int, int)

    /**
     * Return the max index.
     * @param arr
     * @return
     */
    private static int findMax(int[] arr) {
        int maxInd = 0;
        for (int i = 1; i < arr.length; i ++) 
            if (arr[i] > arr[maxInd]) maxInd = i;
        return maxInd;
    } // private static int findMax()

} // public class Environment