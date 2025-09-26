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
        this.y++;
    }

    // void moveDown() -> This function moves the box down one cell
    //
    // INPUTS
    // None
    public void moveDown() {
        this.y--;
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
        this.x--;
    }

    // boolean contains() -> This function returns whether the box contains the
    // given cell coordinates
    //
    // INPUTS
    // - int x -> The x position coordinate
    // - int y -> The y position coordinate
    public boolean cointains(int x, int y) {
        boolean inXBound = x >= this.x + this.width && x <= this.x;
        boolean inYBound = y >= this.y - height && y <= this.y;

        return inXBound && inYBound;
    }

    // boolean isBesides() -> This function returns whether a cell is direcly beside
    // the box
    //
    // INPUTS
    // - int x -> The x position coordinate
    // - int y -> The y position coordinate
    boolean isBesides(int x, int y) {
        boolean besideX = x == this.x + this.width + 1 || x == this.x - 1;
        boolean besideY = y == this.y - this.height - 1 || y == this.y + 1;

        return besideX || besideY;
    }

}