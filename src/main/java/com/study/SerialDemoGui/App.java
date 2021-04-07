package com.study.SerialDemoGui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Hello world!
 *
 */
public class App extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public App() {
        this.setTitle("Serial Demo GUI");
        this.setSize(800, 600);
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        LeftPanel leftPanel = new LeftPanel();
        c.weightx = 0.5;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        this.add(leftPanel, c);
        
        MainPanel mainPanel = new MainPanel();
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 0;
        this.add(mainPanel, c);
        
        this.setVisible(true);

    }

    public static void main( String[] args ) {
        App app = new App();
    }
}
