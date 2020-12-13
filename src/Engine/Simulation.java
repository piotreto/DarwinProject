package Engine;

import Constants.Constants;
import Map.WholeMap;
import support.FollowingDialog;
import support.StatsPanel;
import support.WorldPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation implements ActionListener {

    private Timer timer;
    private WorldPanel worldPanel;
    private StatsPanel stats;
    private WholeMap map;
    private boolean isRunning = false;
    private boolean isFollowing = false;
    private FollowingDialog following = null;

    public Simulation(WorldPanel worldPanel, StatsPanel stats, WholeMap map) {
        this.worldPanel = worldPanel;
        this.stats = stats;
        this.map = map;
        this.timer = new Timer(Constants.GAME_SPEED, this::runEngine);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Start":
                this.worldPanel.setChooseBest(false);
                this.timer.start();
                this.setRunning(true);
                this.worldPanel.setRunning(true);
                break;
            case "Pause":
                this.timer.stop();
                this.setRunning(false);
                this.worldPanel.setRunning(false);
                break;
        }
    }

    public void runEngine(ActionEvent e) {
        this.worldPanel.doOneLoop();
        this.stats.doOneLoop();
        if(this.isFollowing) {
            this.following.doOneLoop();
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }
    public void setRunning(boolean ans) {
        this.isRunning = ans;
    }
    public void setFollowing(boolean ans, FollowingDialog Dialog) {
        this.isFollowing = ans;
        this.following = Dialog;
    }
}
