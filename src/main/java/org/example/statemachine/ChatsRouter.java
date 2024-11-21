package org.example.statemachine;

import org.example.service.ServiceManager;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class ChatsRouter {
    private Map<Long, TransmittedData> routings;
    private ServiceManager serviceManager;

    public ChatsRouter() {
        routings = new HashMap<>();
        serviceManager = new ServiceManager();
    }

    public SendMessage route(long chatId, String textFromUser) throws Exception {
        if (routings.containsKey(chatId) == false) {
            routings.put(chatId, new TransmittedData(chatId));
        }

        TransmittedData transmittedData = routings.get(chatId);

        return serviceManager.callLogicMethod(textFromUser, transmittedData);
    }
}
