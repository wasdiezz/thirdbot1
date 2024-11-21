
package org.example.service;

import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Service {
    SendMessage process(String textFromUser, TransmittedData transmittedData) throws Exception;
}
