package logic;

import Math.*;

public interface Observable {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(Vector2d oldPos, Vector2d newPos);
}
