package org.example.service;

import org.example.service.logic.RegisterLogic;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {
    private Map<String, Service> methods;

    private RegisterLogic registerLogic;


    public ServiceManager() {
        methods = new HashMap<>();
        registerLogic = new RegisterLogic();

        methods.put(State.WaitingCommandStart, registerLogic::processWaitingCommandStart);
        methods.put(State.WaitingInputTeamName, registerLogic::processWaitingInputTeamName);
        methods.put(State.WaitingInputNumberOfParticipants, registerLogic::processWaitingInputNumberOfParticipants );
        methods.put(State.WaitingInputSelectedTask, registerLogic::processWaitingInputSelectedTask);
        methods.put(State.WaitingApproveData, registerLogic::processWaitingApproveData);


    }

    public SendMessage callLogicMethod(String textFromUser, TransmittedData transmittedData) throws Exception {
        String state = transmittedData.getState();
        return methods.get(state).process(textFromUser, transmittedData);
    }
}
