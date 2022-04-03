package com.example.pushnotificationservice;

public class EventHandler {

    private static EventHandler INSTANCE;

    public EventHandler() {

    }

    public static EventHandler getInstance(){
        if (INSTANCE == null){
            INSTANCE = new EventHandler();
        }
        return INSTANCE;
    }

    public void Test(){
        if (getListener() != null){
            getListener().OnEvent();
        }
    }

    public interface EventListener{
        void OnEvent();
    }

    private EventListener listener;

    public EventListener getListener() {
        return listener;
    }

    public void setListener(EventListener listener) {
        this.listener = listener;
    }
}
