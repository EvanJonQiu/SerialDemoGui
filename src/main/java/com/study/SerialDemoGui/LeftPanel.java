package com.study.SerialDemoGui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fazecast.jSerialComm.SerialPort;

public class LeftPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private static final int BAUD_RATE_DEFAULT = 8;
    private static final int DATA_BITS_DEFAULT = 0;
    private static final int STOP_BITS_DEFAULT = 0;
    private static final int PARITY_DEFAULT = 0;
    private static final int FLOW_CONTROL_DEFAULT = 2;
    
    private static final String BAUD_RATE[] = {"2400", "4800", "9600", "14400", "19200", "38400", "56000", "57600", "115200"};
    private static final String DATA_BITS[] = {"8", "7", "6", "5"};
    private static final String STOP_BITS[] = {"1", "1.5", "2"};
    private static final String PARITY[] = {"None", "Odd", "Even", "Mark", "Space"};
    private static final String FLOW_CONTROL[] = {"Hardware", "Software", "None", "Custom"};
    
    private boolean isOpened = false;
    
    private JButton openBtn;
    private JComboBox<String> portCB;
    private JComboBox<String> baudRateCB;
    private JComboBox<String> dataBitsCB;
    private JComboBox<String> stopBitsCB;
    private JComboBox<String> parityCB;
    private JComboBox<String> flowControlCB;
    
    private String portName;
    private int baudRate = 115200;
    private int dataBits = 8;
    private int stopBits = SerialPort.ONE_STOP_BIT;
    private int parity = SerialPort.NO_PARITY;
    private int flowControl = SerialPort.FLOW_CONTROL_DISABLED;
    
    private SerialWrapper serialWrapper;
    
    public LeftPanel(SerialWrapper serialWrapper) {
        this.setLayout(new GridLayout(2, 1));
        
        this.serialWrapper = serialWrapper;
        
        JPanel optionPanel = new JPanel(new GridBagLayout());
        optionPanel.setBorder(BorderFactory.createTitledBorder("端口设置"));
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 0, 0,  0);
        
        JLabel label = new JLabel("Port");
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        optionPanel.add(label, c);
        
        portCB = new JComboBox<String>();
        portCB.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    setPortName(e.getItem().toString());
                }   
            }
            
        });
        this.intPorts();
        
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 0;
        optionPanel.add(portCB, c);
        
        label = new JLabel("Baud rate");
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 1;
        optionPanel.add(label, c);
        
        baudRateCB = new JComboBox<String>(BAUD_RATE);
        baudRateCB.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    setBaudRate(Integer.parseInt(e.getItem().toString()));
                }                
            }
        });
        baudRateCB.setSelectedIndex(BAUD_RATE_DEFAULT);
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 1;
        optionPanel.add(baudRateCB, c);
        
        label = new JLabel("Data bits");
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 2;
        optionPanel.add(label, c);
        
        
        dataBitsCB = new JComboBox<String>(DATA_BITS);
        dataBitsCB.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    setDataBits(Integer.parseInt(e.getItem().toString()));
                }    
            }
        });
        dataBitsCB.setSelectedIndex(DATA_BITS_DEFAULT);
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 2;
        optionPanel.add(dataBitsCB, c);
        
        label = new JLabel("Stop bits");
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 3;
        optionPanel.add(label, c);
        
        stopBitsCB = new JComboBox<String>(STOP_BITS);
        stopBitsCB.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    switch (e.getItem().toString()) {
                    case "1":
                        setStopBits(SerialPort.ONE_STOP_BIT);
                        break;
                    case "1.5":
                        setStopBits(SerialPort.ONE_POINT_FIVE_STOP_BITS);
                        break;
                    case "2":
                        setStopBits(SerialPort.TWO_STOP_BITS);
                        break;
                    default:
                        setStopBits(SerialPort.ONE_STOP_BIT);
                    }
                }  
            }
            
        });
        stopBitsCB.setSelectedIndex(STOP_BITS_DEFAULT);
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 3;
        optionPanel.add(stopBitsCB, c);
        
        label = new JLabel("Parity");
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 4;
        optionPanel.add(label, c);
        
        parityCB = new JComboBox<String>(PARITY);
        parityCB.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    switch (e.getItem().toString()) {
                    case "None":
                        setParity(SerialPort.NO_PARITY);
                        break;
                    case "Odd":
                        setParity(SerialPort.ODD_PARITY);
                        break;
                    case "Even":
                        setParity(SerialPort.EVEN_PARITY);
                        break;
                    case "Mark":
                        setParity(SerialPort.MARK_PARITY);
                        break;
                    case "Space":
                        setParity(SerialPort.SPACE_PARITY);
                        break;
                    default:
                        setParity(SerialPort.NO_PARITY);
                    }
                }
            }
            
        });
        parityCB.setSelectedIndex(PARITY_DEFAULT);
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 4;
        optionPanel.add(parityCB, c);
        
        label = new JLabel("Flow control");
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 5;
        optionPanel.add(label, c);
        
        flowControlCB = new JComboBox<String>(FLOW_CONTROL);
        flowControlCB.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    
                }
            }
            
        });
        flowControlCB.setEnabled(false);
        flowControlCB.setSelectedIndex(FLOW_CONTROL_DEFAULT);
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 5;
        optionPanel.add(flowControlCB, c);
        
        openBtn = new JButton("打开");
        c.gridx = 1;
        c.gridy = 6;
        optionPanel.add(openBtn, c);
        
        openBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isOpened) {
                    isOpened = true;
                    openBtn.setText("关闭");
                    disableCtrls(false);
                    openPort();
                } else {
                    isOpened = false;
                    openBtn.setText("打开");
                    disableCtrls(true);
                    closePort();
                }
            }
        });
        
        this.add(optionPanel);
    }
    
    private void disableCtrls(boolean b) {
        portCB.setEnabled(b);
        baudRateCB.setEnabled(b);
        dataBitsCB.setEnabled(b);
        stopBitsCB.setEnabled(b);
        parityCB.setEnabled(b);
        flowControlCB.setEnabled(false);
    }
    
    private void intPorts() {
        SerialPort [] serialPorts = SerialPort.getCommPorts();
        if (serialPorts != null && serialPorts.length > 0) {
            for(SerialPort serialPort: serialPorts) {
                this.portCB.addItem(serialPort.getSystemPortName());
            }
            this.portCB.setSelectedIndex(0);
        }
    }
    
    private void openPort() {
        if (this.serialWrapper != null) {
            this.serialWrapper.openComPort(this.portName, this.baudRate, this.dataBits, this.stopBits, this.parity);
        }
    }
    
    private void closePort() {
        if (this.serialWrapper != null && this.serialWrapper.getPort() != null) {
            this.serialWrapper.closeComPort();
        }
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public int getDataBits() {
        return dataBits;
    }

    public void setDataBits(int dataBits) {
        this.dataBits = dataBits;
    }

    public int getStopBits() {
        return stopBits;
    }

    public void setStopBits(int stopBits) {
        this.stopBits = stopBits;
    }

    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

    public int getFlowControl() {
        return flowControl;
    }

    public void setFlowControl(int flowControl) {
        this.flowControl = flowControl;
    }
}
