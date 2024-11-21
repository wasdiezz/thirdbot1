package org.example.util.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardStorage {

    public static InlineKeyboardMarkup getApproveRegisterkeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = null;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlinebuttonsStorage.ApproveRegister.getTittle());
        button.setCallbackData(InlinebuttonsStorage.ApproveRegister.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlinebuttonsStorage.DisapproveRegister.getTittle());
        button.setCallbackData(InlinebuttonsStorage.DisapproveRegister.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup inlineKeyboardButton = new InlineKeyboardMarkup();
        inlineKeyboardButton.setKeyboard(keyboard);

        return inlineKeyboardButton;

    }
}
