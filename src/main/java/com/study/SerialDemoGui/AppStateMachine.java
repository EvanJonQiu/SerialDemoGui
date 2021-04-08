package com.study.SerialDemoGui;

public class AppStateMachine {
    
    private static AppStateMachine stateMachine = null;
    private SerialWrapper serialWrapper;

    public static AppStateMachine getInstance() {
        if (stateMachine == null) {
            stateMachine = new AppStateMachine();
        }
        
        return stateMachine;
    }
    
    private AppStateMachine() {
        
    }
    
    
}
