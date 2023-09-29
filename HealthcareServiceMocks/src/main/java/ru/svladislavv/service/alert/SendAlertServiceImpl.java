package ru.svladislavv.service.alert;

public class SendAlertServiceImpl implements SendAlertService {

    @Override
    public void send(String message) {
        System.out.println(message);
    }
}