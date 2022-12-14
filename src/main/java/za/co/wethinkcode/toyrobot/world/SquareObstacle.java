package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

public class SquareObstacle implements Obstacle {
    Position position;

    public SquareObstacle(int x, int y) {
        this.position = new Position(x, y);

    }

    public int getBottomLeftX() {
        return position.getX();
    }


    public int getBottomLeftY() {
        return position.getY();
    }


    public int getSize() {
        return 5;
    }


    public boolean blocksPosition(Position position) {
        return ((getBottomLeftX() <= position.getX())) && (position.getX() <= (getBottomLeftX() + 4))
                && (getBottomLeftY() <= position.getY()) && (position.getY() <= getBottomLeftY() + 4);
    }


    public boolean blocksPath(Position a, Position b) {
        if (a.getX() == b.getX()) {
            for (int i = Math.min(a.getY(), b.getY()); i <= Math.max(a.getY(), b.getY()); i++) {
                if (blocksPosition(new Position(a.getX(), i))) {
                    return true;
                }
            }
        } else {
            for (int i = Math.min(a.getX(), b.getX()); i <= Math.max(a.getX(), b.getX()); i++) {
                if (blocksPosition(new Position(i, a.getY()))) {
                    return true;
                }
            }
        }
        return false;
    }
}
