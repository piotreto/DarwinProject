package logic;

import Math.*;

public interface Observer {
    void update(Vector2d oldPosition, Vector2d newPosition);
}
