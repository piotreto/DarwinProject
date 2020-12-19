package view;

import Assets.Animal;
import Engine.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalDialog extends JDialog implements ActionListener{

    private Simulation engine;
    private Animal animal;

    public AnimalDialog(Animal animal, Simulation engine) {
        this.animal = animal;
        this.engine = engine;
        this.setTitle("Dane o zwierzaku");
        this.setSize(600,200);
        this.setLayout(new GridLayout(2,1));
        JLabel text1 = new JLabel("Informacja o zwierzęciu na pozycji" + animal.getPosition());
        JLabel text2 = new JLabel(this.animal.getGens().toString());
        this.add(text1);
        this.add(text2);
        this.setLayout(new FlowLayout());
        JLabel text3 = new JLabel("Jesli chcesz monitorowac wybrane zwierze kliknij w przycisk");
        JButton button = new JButton("Monitoruj");
        button.addActionListener(this::actionPerformed);
        this.add(text3);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        FollowingDialog Follow = new FollowingDialog(this.animal, this.engine);
        Follow.setVisible(true);
    }
}
