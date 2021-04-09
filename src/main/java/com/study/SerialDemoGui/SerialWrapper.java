package com.study.SerialDemoGui;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortInvalidPortException;

public class SerialWrapper {
    private static final Logger logger = LoggerFactory.getLogger(SerialWrapper.class);
    
    private static final int BUFFER_SIZE = 1000;
    private byte[] buffer = new byte[BUFFER_SIZE];
    
    private static SerialWrapper serialWrapper = null;
    private SerialPort serialPort = null;
    
    public static SerialWrapper getInstance() {
        if (serialWrapper == null) {
            serialWrapper = new SerialWrapper();
        }
        return serialWrapper;
    }
    
    private SerialWrapper() {
        Arrays.fill(this.buffer, (byte)0);
    }
    
    public SerialPort openComPort(String portName, int baudRate, int dataBits, int stopBits, int parity) {
        logger.debug("openComPort: " + portName + "," + String.format("%d",  baudRate) + "," + String.format("%d", dataBits) + ","
                + String.format("%d", stopBits) + "," + String.format("%d", parity));
        
        this.serialPort = SerialPort.getCommPort(portName);
        this.serialPort.openPort();
        this.serialPort.setComPortParameters(baudRate, dataBits, stopBits, parity);
        this.serialPort.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
        
        return this.serialPort;
    }
    
    public void closeComPort() {
        logger.debug("closeComPort: " + serialPort.getSystemPortName());
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.closePort();
            serialPort = null;
        }
    }
    
    public void setSerialPortListener(SerialPort serialPort, SerialPortDataListener listener) {
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.addDataListener(listener);
        }
    }
    
    public final byte[] readData(SerialPort serialPort) {
        InputStream is = null;

        try {
            is = serialPort.getInputStream();
            int buffLength = is.available();
            Arrays.fill(this.buffer, (byte)0);
            
            while (buffLength > 0) {
                is.read(this.buffer);
                buffLength = is.available();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return this.buffer;
    }
    
    public SerialPort getPort() {
        return this.serialPort;
    }
}
