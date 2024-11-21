package org.example.service.logic;

import org.example.db.Registration;
import org.example.db.RegistrationsRepository;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.NumberUtil;
import org.example.util.buttons.InlineKeyboardStorage;
import org.example.util.buttons.InlinebuttonsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import java.sql.SQLException;

public class RegisterLogic {

    private RegistrationsRepository registrationsRepository;

    public RegisterLogic() {
        registrationsRepository = new RegistrationsRepository();
    }



    public SendMessage processWaitingCommandStart(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (textFromUser.equals("/start") == false){
            messageToUser.setText("Ошибка запуска бота. Для старта пожалуйста введите: /start");
            return messageToUser;
        }

        transmittedData.setState(State.WaitingInputTeamName);
        messageToUser.setText("Приветствую! Пожалуйста, введите название команды (от 3  до 30 символов включительно: ");

        return messageToUser;

    }
    public SendMessage processWaitingInputTeamName (String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());
        if (textFromUser.length() > 30){
            messageToUser.setText("Ошибка ввода. Длина названия должна быть от 3 до 30 символов включительно");
            return messageToUser;
        }
        transmittedData.getDataStorage().add("teamName", textFromUser);
        String teamName = (String) transmittedData.getDataStorage().get("teamName");
        messageToUser.setText("Название команды успешно записано. Теперь, пожалуйста, введите количество человек в команде от 1 до 4 включительно");
        transmittedData.setState(State.WaitingInputNumberOfParticipants);
        return messageToUser;

    }
    public SendMessage processWaitingInputNumberOfParticipants(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (NumberUtil.isNumber(textFromUser) == false) {
            messageToUser.setText("Ошибка ввода количства членов команды. Не удалось распознать число.");
            return messageToUser;
        }

        int numbersOfParticipants = NumberUtil.stringToInt(textFromUser);
        if (NumberUtil.isNumberInRange(numbersOfParticipants, 1, 4) == false){
            messageToUser.setText("Ошибка ввода количества членов команды. Количество членов должно быть от 1 до 4");
            return messageToUser;
        }
        transmittedData.getDataStorage().add("numbersOfParticipants", numbersOfParticipants);
        messageToUser.setText("Количество членов команды успешно записано. Теперь номер выбранной задачи \nЗадача №1 - Разработать чат-бота\nЗадача №2 - Разработать мобильное приложение.\nЗадача №3 - Разработь сайт");
        transmittedData.setState(State.WaitingInputSelectedTask);
        return messageToUser;
    }
    public SendMessage processWaitingInputSelectedTask(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (NumberUtil.isNumber(textFromUser) == false) {
            messageToUser.setText("Ошибка ввода номера выбранной задачи. Вы ввели не число.");
            return messageToUser;
        }

        int selectedTask = NumberUtil.stringToInt(textFromUser);

         if (NumberUtil.isNumberInRange(selectedTask, 1, 3) == false){
            messageToUser.setText("Ошибка ввода номера выбранной задачи. Количество задач долно быть от 1 до 3");
            return messageToUser;
        }
        transmittedData.getDataStorage().add("selectedTask", selectedTask);
        String teamName = (String) transmittedData.getDataStorage().get("teamName");
        int numbersOfParticipants = (int) transmittedData.getDataStorage().get("numbersOfParticipants");

        messageToUser.setText(String.format("Выбранная задача успешно записана. Теперь проверьте корректность введённых данных и нажмите кнопку.\nНазвание команды: %s\nКоличество участников команды: %d\nНомер Выбранной задачи: %d", teamName, numbersOfParticipants, selectedTask));

        messageToUser.setReplyMarkup(InlineKeyboardStorage.getApproveRegisterkeyboard());

        transmittedData.setState(State.WaitingApproveData);

        return messageToUser;
    }
    public SendMessage processWaitingApproveData(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());
        return messageToUser;


    }
    public SendMessage processWaitingShowOrRegister(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());


        return messageToUser;
    }
    public SendMessage processWaitingLastShowCommands(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());;
        return messageToUser;

    }
    public SendMessage processWaitingMiddleShowCommands(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());
        return messageToUser;

    }
    public SendMessage processWaitingFirstShowCommands(String textFromUser, TransmittedData transmittedData) throws SQLException {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());
        if (textFromUser.equals(InlinebuttonsStorage.ApproveRegister) == false && textFromUser.equals(InlinebuttonsStorage.DisapproveRegister) == false) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");
            return messageToUser;
        }

        if (textFromUser.equals(InlinebuttonsStorage.ApproveRegister) == true) {
            String teamName = (String) transmittedData.getDataStorage().get("teamName");
            int numbersOfParticipants = NumberUtil.stringToInt(textFromUser);
            int selectedTask = (int) transmittedData.getDataStorage().get("selectedTask");

            Registration registration = new Registration(0, teamName, numbersOfParticipants, selectedTask);
            registrationsRepository.addNew(registration);

            transmittedData.getDataStorage().clear();
            messageToUser.setText("Данные успешно сохранены. Вернитесь в начало регистрации путем нажатия /start");
        }else if(textFromUser.equals(InlinebuttonsStorage.DisapproveRegister) == true) {
            transmittedData.getDataStorage().clear();
            messageToUser.setText("Регистрация отменена. Вернитесь в начало путем /start");
        }
        return messageToUser;

    }
}
