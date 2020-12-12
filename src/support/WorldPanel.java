package support;

import Assets.Animal;
import Assets.Grass;
import Constants.Constants;
import Engine.Simulation;
import Map.WholeMap;
import Math.*;
import Map.*;


import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class WorldPanel extends JPanel implements MouseListener {

    private Simulation engine;
    private WholeMap map;
    private int objSize = Constants.obj_size;
    private boolean isRunning = false;


    public WorldPanel(WholeMap map) {
        addMouseListener(this);
        this.map = map;
        setPreferredSize(new Dimension(map.ySize * objSize,map.xSize * objSize));
    }




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(180,200,180));
        g.fillRect(0,0,map.xSize* objSize,map.ySize* objSize);
        g.setColor(new Color(150,220,150));
        g.fillRect(map.jungle.bottomLeft.x* objSize, map.jungle.bottomLeft.y* objSize, map.jungle.xSize* objSize, map.jungle.ySize* objSize);

        Graphics2D g2d = (Graphics2D) g;

        drawAnimals(g2d);
        drawGrasses(g2d);

    }

    private void drawGrasses(Graphics2D g) {

        ArrayList<Grass> PlantsArray = new ArrayList<>(map.plants.values());
        for(Grass grass : PlantsArray) {
            drawGrass(g, grass);
        }


    }

    private void drawGrass(Graphics2D g, Grass grass) {
        g.setColor(new Color(11, 111, 11));
        g.fillRect(grass.getPosition().x * objSize, grass.getPosition().y* objSize, objSize, objSize);
    }

    private void drawAnimal(Graphics2D g, Animal animal) {
        int redComponent = (((int)(animal.getAnimal_energy()*120/Constants.PROCREATING_ENERGY)));
        if(redComponent > 255) redComponent = 255;
        g.setColor(new Color(redComponent,10,10));
        g.fillRect(animal.getPosition().x * objSize, animal.getPosition().y * objSize, objSize, objSize);
    }

    private void drawAnimals(Graphics2D g) {
        ArrayList<Animal> AnimalsArray = map.getList();
        for(Animal animal : AnimalsArray) {
            drawAnimal(g, animal);
        }

    }

    public void doOneLoop() {
        update();
        repaint();
    }

    private void update() {
        this.map.simulateOneDay();

    }

    public void setRunning(boolean ans) {
        this.isRunning = ans;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(!this.isRunning()) {
            int x = mouseEvent.getX() / objSize;
            int y = mouseEvent.getY() / objSize;
            Vector2d position = new Vector2d(x,y);
            if(map.isOccupied(position)) {
                Animal animal = map.animals.get(position).get(0);
                AnimalDialog dialog = new AnimalDialog(animal, this.engine);
                dialog.setVisible(true);
            }
        }



    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    public void setEngine(Simulation engine) {
        this.engine = engine;
    }
}