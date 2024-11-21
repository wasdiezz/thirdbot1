package org.example.util.buttons;

public class InlineButton {
    private String tittle;
    private String callBackData;
    public InlineButton(String tittle, String callBackData) {
        this.tittle = tittle;
        this.callBackData = callBackData;
    }

    public String getTittle() {return tittle;}
    public String getCallBackData() {return callBackData;}
}
