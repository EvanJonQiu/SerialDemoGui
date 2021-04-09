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
        
        outputArea = new JTextArea(10, 1000);
        JScrollPane scroll = new JScrollPane(outputArea);
        outputArea.setLineWrap(true);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        this.add(scroll);
        
        inputArea = new JTextArea();
        scroll = new JScrollPane(inputArea);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        this.add(scroll);
    }
    
    public void writeData(String data) {
        if (data != null) {
            this.outputArea.append(data);
            this.outputArea.setCaretPosition(this.outputArea.getText().length());
        }
    }
}
