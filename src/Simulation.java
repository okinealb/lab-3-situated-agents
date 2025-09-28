package src;

import java.util.Arrays;
import java.util.Scanner;


public class Simulation {

	/** The height of the environment. */
	static final int N = 7;
	/** The width of the environment. */
	static final int M = 7;
	/** The width of the penalty area for both sides. */
	static final int E = 1;
	/** The height of the box. */
	static final int n = 2;
	/** The width of the box. */
	static final int m = 3;

	/** The weights for each agent. */
	static final int[] weights = new int[] {0, 1, 0};
	/** The initial position. */
	static final int[] initPos = new int[] {3, 0};
	/** The final position. */
	static final int[] finalPos = new int[] {3, 5};

	/** The number of ticks taken so far. */
	static int tickCount = 0;

	/** The environment instance across ticks. */
	static Environment env;


	public static void main(String[] args) {
		// Initialize a scanner from stdin
		Scanner scanner = new Scanner(System.in);

		// Print out a greeting message
		System.out.println("default mode or fast mode? [d/f] ");

		// Get the user's input. Then run the simulation
		String input = scanner.nextLine().trim().toLowerCase();
		boolean fastMode;
		scanner.close();
		if (input.equals("default") || input.equals("d")) {
			// Call the default mode of the program
			fastMode = false;
		} else if (input.equals("fast") || input.equals("f")) {
			// Call the fast mode of the program
			fastMode = true;
		} else {
			// Invalid input. Throw an exception.
			throw new IllegalArgumentException(
				"Please choose between default and fast mode"
			);
		} // if (default or fast)...else

		// Initialize the environment once
		env = new Environment(
			M, N, E, m, n, 
			initPos[0], 
			initPos[1], 
			weights
		);

		// Continuously move the box until reached the goal position
		while (!tick(fastMode));

		// Draw the final environment
		System.out.println();
		env.drawEnvironment();
	} // static void main(String[])
	

	/** Run the simulation. Changes based on the mode given.
	 * @param fast A boolean indicating fast mode or not
	 */
	static boolean tick(boolean fast) {
		// Get the current box position
		int[] boxPos = env.box.getPosition();

		// Get the leftmost and rightmost columns of the box
		int l = boxPos[0];
		int r = boxPos[0] + m - 1;
		
		// Add the penalty to each agent
		addPenalties(env, (env.onBorder(l) || env.onBorder(r)) 
			? -10
			: -1);

		// Print the current box position
		System.out.printf("Tick %d - Current box position: [%d, %d]\n", tickCount + 1, boxPos[0], boxPos[1]);
		
		// Draw the environment
		env.drawEnvironment();

		// Move the box based on the mode and overall decision
		if (fast) {
			env.box.moveDown(N);
		} else {
			switch (env.queryAgents(fast)) {
				case Up: env.box.moveUp(N); 		break;
				case Down: env.box.moveDown(N);   	break;
				case Left: env.box.moveLeft(M);   	break;
				case Right: env.box.moveRight(M); 	break;
				case Still: break;
			}
		}

		tickCount++;
		
		// Check if goal is reached
		boolean goalReached = Arrays.equals(env.box.getPosition(), finalPos);
		if (goalReached) {
			System.out.println("The box has reached the goal position!");
			// Give all agents the reward for reaching the goal
			addPenalties(env, 100);
		}
		
		return goalReached;
	} // static boolean run(boolean)

	/**
	 * Add the penalty to each of the agents.
	 * @param env
	 * @param penalty
	 */
	static void addPenalties(Environment env, int penalty) {
		for (int r = 0; r < N; r ++)
			for (int c = 0; c < M ; c ++)
				env.agents[r][c].applyPenalty(penalty);
	} // static void addPenalties(Environment, int)

} // public class Simulation