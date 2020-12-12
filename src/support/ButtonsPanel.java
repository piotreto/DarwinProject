package support;

import Engine.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsPanel extends JPanel {

    public static final int HEIGHT = 100;
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

}
