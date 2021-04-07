package com.study.SerialDemoGui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private JTextArea outputArea;
    private JTextArea inputArea;
    
    public MainPanel() {
        this.setLayout(new GridLayout(3, 1));
        
        outputArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        this.add(scroll);
        
        inputArea = new JTextArea();
        scroll = new JScrollPane(inputArea);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        this.add(scroll);
    }

}
