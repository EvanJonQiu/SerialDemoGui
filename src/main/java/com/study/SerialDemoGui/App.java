package com.study.SerialDemoGui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
        
        SerialWrapper serialWrapper = SerialWrapper.getInstance();
        
        this.setLayout(new BorderLayout());
        
        JMenuBar  menuBar = new JMenuBar ();
        JMenu menu=new JMenu("Menu");  
        menuBar.add(menu);
        
        this.add(menuBar, BorderLayout.NORTH);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEtchedBorder());
        JLabel label = new JLabel("hello");
        bottomPanel.add(label);
        this.add(bottomPanel, BorderLayout.SOUTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        LeftPanel leftPanel = new LeftPanel(serialWrapper);
        c.weightx = 0.5;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        centerPanel.add(leftPanel, c);
        
        MainPanel mainPanel = new MainPanel();
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 0;
        centerPanel.add(mainPanel, c);
        
        leftPanel.setOutputArea(mainPanel);
        
        this.add(centerPanel, BorderLayout.CENTER);
        
        this.setVisible(true);

    }

    public static void main( String[] args ) {
        System.setProperty("log4j.configuration", "file:./src/main/resources/log.properties");
        App app = new App();
    }
}
