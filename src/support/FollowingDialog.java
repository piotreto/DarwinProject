package support;

import Assets.Animal;
import Engine.Simulation;

import javax.swing.*;
import java.awt.*;

public class FollowingDialog extends JDialog {

    private Simulation engine;
    private Animal animal;
    private JLabel children;
    private JLabel position;
    private JLabel dead;
    private JLabel ancestors;

    public FollowingDialog(Animal animal, Simulation engine) {
        this.animal = animal;
        this.engine = engine;
        this.engine.setFollowing(true, this);
        this.setSize(600,100);
        this.setLayout(new GridLayout(4,1));
        this.setTitle("Monitorowanie zwierzaka");
        this.setLocationRelativeTo(null);

        this.children = new JLabel(String.format("Liczba dzieci: %d", this.animal.getChildren()));
        this.position = new JLabel(String.format("Pozycja zwierzecia %s", this.animal.getPosition()));
        this.ancestors = new JLabel(String.format("Liczba przodków %d", this.animal.getAncestors()));
        this.dead = new JLabel(String.format("Zwierze żyje"));
        this.add(this.children);
        this.add(this.position);
        this.add(ancestors);
        this.add(dead);
    }

    public void doOneLoop() {
        this.children.setText(String.format("Liczba dzieci: %d", this.animal.getChildren()));
        this.position.setText(String.format("Pozycja zwierzecia %s", this.animal.getPosition()));
        this.ancestors.setText(String.format("Liczba przodków %d", this.animal.getAncestors()));
        if(this.animal.getAnimal_energy() <= 0) {
            this.dead.setText(String.format("Zwierze umarło w epoce: %d", this.animal.getDead()));
            this.engine.setFollowing(false, null);
        }

    }
}


