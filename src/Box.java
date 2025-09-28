package src;

public class Box {

    private int width;
    private int height;
    private int x;
    private int y;

    Box(int width, int height, int x, int y) {
        // Initialize the dimensions of the box
        this.width = width;
        this.height = height;

        // Initialize the positions of the box
        this.x = x;
        this.y = y;
    }

    // void moveUp() -> This function moves the box up one cell
    //
    // INPUTS
    // None
    public void moveUp() {
        if (canMoveUp()) {
            this.y--;  // In grid coordinates, up means decreasing y
        }
    }

    // void moveDown() -> This function moves the box down one cell
    //
    // INPUTS
    // None
    public void moveDown() {
        this.y++;  // In grid coordinates, down means increasing y
    }

    // void moveRight() -> This function moves the box right one cell
    //
    // INPUTS
    // None
    public void moveRight() {
        this.x++;
    }

    // void moveLeft() -> This function moves the box left one cell
    //
    // INPUTS
    // None
    public void moveLeft() {
        if (canMoveLeft()) {
            this.x--;
        }
    }
    
    // Overloaded methods that check bounds
    public void moveUp(int maxY) {
        if (canMoveUp()) {
            this.y--;
        }
    }

    public void moveDown(int maxY) {
        if (canMoveDown(maxY)) {
            this.y++;
        }
    }

    public void moveRight(int maxX) {
        if (canMoveRight(maxX)) {
            this.x++;
        }
    }

    public void moveLeft(int maxX) {
        if (canMoveLeft()) {
            this.x--;
        }
    }

    // boolean contains() -> This function returns whether the box contains the
    // given cell coordinates
    //
    // INPUTS
    // - int x -> The x position coordinate
    // - int y -> The y position coordinate
    public boolean isContained(int x, int y) {
        boolean inXBound = x >= this.x && x < this.x + this.width;
        boolean inYBound = y >= this.y && y < this.y + this.height;

        return inXBound && inYBound;
    }

    // boolean isBesides() -> This function returns whether a cell is direcly beside
    // the box
    //
    // INPUTS
    // - int x -> The x position coordinate
    // - int y -> The y position coordinate
    boolean isBeside(int x, int y) {
        // Check if the cell is adjacent to the box (not contained within it)
        if (isContained(x, y)) return false;
        
        // Check if adjacent horizontally or vertically
        boolean adjacentX = (x >= this.x - 1 && x <= this.x + this.width) && 
                           (y >= this.y && y < this.y + this.height);
        boolean adjacentY = (y >= this.y - 1 && y <= this.y + this.height) && 
                           (x >= this.x && x < this.x + this.width);
        
        return adjacentX || adjacentY;
    }

    int[] getPosition() {
        return new int[] {this.x, this.y};
    }
    
    // Add bounds checking methods
    public boolean canMoveUp() {
        return this.y > 0;
    }
    
    public boolean canMoveDown(int maxY) {
        return this.y + this.height < maxY;
    }
    
    public boolean canMoveLeft() {
        return this.x > 0;
    }
    
    public boolean canMoveRight(int maxX) {
        return this.x + this.width < maxX;
    }

}