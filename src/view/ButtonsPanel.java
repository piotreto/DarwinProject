package view;

import Engine.Simulation;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {

    public static final int HEIGHT = 50;
    public static final int WIDTH = 300;

    private JButton startButton;
    private JButton pauseButton;

    private Simulation engine;

    public ButtonsPanel(Simulation engine) {

        this.engine = engine;
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");

        startButton.addActionListener(this.engine);
        pauseButton.addActionListener(this.engine);

        setLayout(new FlowLayout());

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        add(startButton);
        add(pauseButton);
    }
    //testing

}
