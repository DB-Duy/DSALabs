/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa2020;

import Lab03.sorting.*;
import geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import static dsa2020.MainFrame.*;

/**
 * @author LTSACH
 */
public class WorkingPanel extends JPanel
        implements
        MouseMotionListener, MouseListener,
        ItemListener,
        ActionListener,
        ComponentListener {
    Graph sin = null;
    Graph quad = null;
    Graph time = null;
    SpaceMapping spaceMapping = new SpaceMapping();

    public WorkingPanel() {
        this.setBorder(BorderFactory.createEtchedBorder());
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addComponentListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println(this.sin == null);
        if (this.sin != null) {
            graph(g);
        }
        if(this.time!=null){
            time.draw(g,spaceMapping);
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        Point2D logPoint = this.spaceMapping.device2Logic(e.getX(), e.getY());
        String message = String.format("mouseDragged: Device(x,y)=(%d,%d); Logic(x,y)=(%6.2f, %6.2f)", e.getX(), e.getY(), logPoint.getX(),
                logPoint.getY());
        infoPanel.println(message);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        String message = String.format("mouseMoved: (x,y)=(%d,%d)", e.getX(), e.getY());
        infoPanel.println(message);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((e.getClickCount() == 2) && !e.isConsumed()) {
            String message = String.format("Mouse Double Clicked: (x,y)=(%d,%d)", e.getX(), e.getY());
            infoPanel.println(message);
            e.consume();
        } else {
            String message = String.format("Mouse Clicked: (x,y)=(%d,%d)", e.getX(), e.getY());
            infoPanel.println(message);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        String message = String.format("mousePressed: (x,y)=(%d,%d)", e.getX(), e.getY());
        infoPanel.println(message);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        String message = String.format("mouseReleased: (x,y)=(%d,%d)", e.getX(), e.getY());
        infoPanel.println(message);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        String message = String.format("mouseEntered: (x,y)=(%d,%d)", e.getX(), e.getY());
        infoPanel.println(message);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        String message = String.format("mouseExited: (x,y)=(%d,%d)", e.getX(), e.getY());
        infoPanel.println(message);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        int state = e.getStateChange();
        if (state == ItemEvent.SELECTED) {
            infoPanel.println("Selected");
            btnSelect.setText("Selecting");
        } else {
            infoPanel.println("DeSelected");
            btnSelect.setText("Drawing");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCircle) {
            infoPanel.println("action: draw Circle");
        } else if (e.getSource() == btnRect) {
            infoPanel.println("action: draw Rect");
        } else if (e.getSource() == btnGraph) {
            infoPanel.println("action: graphing");

            this.sin = Graph.sin(3, -10, 10, 0.01);
            this.quad = Graph.quadratic(1, 2, 3, -10, 10, 0.01);
            this.spaceMapping.updateLogViewPort(-10, 10, -10, 10);
            repaint();
        } else if (e.getSource() == btnSort) {
            infoPanel.println("action: sort and graph");
            int[] num_segments = {1, 3, 7};
            ISort[] alg = {
                    new StraightInsertionSort<Point2D>(),
                    new ShellSort<Point2D>(num_segments),
                    new StraightSelectionSort<Point2D>(),
                    new BubbleSort<Point2D>()
            };

            Color[] color = {
                    Color.red, Color.green, Color.blue, Color.cyan
            };
            for (int aIdx = 0; aIdx < alg.length; aIdx++) {
                Point2D[] time = SortingEval.timeit(alg[aIdx], 100, 100,1);
                Graph graph = new Graph(time);
                graph.setMode(Graph.GraphMode.SCATTER);
                this.time = graph;
                //this.axis.addGraph(graph, color[aIdx], 1);
            }

            //update viewport
            //this.spaceMapping.updateLogViewPort(this.axis.getViewport());
            this.spaceMapping.updateLogViewPort(-10, 10, -10, 10);
            this.repaint();


        }
    }

    public void graph(Graphics g) {
        sin.draw(g, spaceMapping);
        quad.draw(g, spaceMapping);
        System.out.println("drawing");
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Dimension size = this.getSize();
        int xGap = 50, yGap = 20;
        this.spaceMapping.updateDevViewPort(xGap, size.width - 2 * xGap, yGap, size.height - 2 * yGap);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
