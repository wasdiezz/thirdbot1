package org.example.statemachine;

public class TransmittedData {
    private String state;
    private DataStorage dataStorage;
    private long chatId;

    public TransmittedData(long chatId) {
        this.chatId = chatId;
        state = State.WaitingCommandStart;
        dataStorage = new DataStorage();
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public DataStorage getDataStorage() {
        return dataStorage;
    }

    public long getChatId() {
        return chatId;
    }

}
