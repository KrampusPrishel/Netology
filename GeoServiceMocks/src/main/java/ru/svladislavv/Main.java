package ru.svladislavv;

import java.util.HashMap;
import java.util.Map;

import ru.svladislavv.geo.GeoService;
import ru.svladislavv.geo.GeoServiceImpl;
import ru.svladislavv.i18n.LocalizationService;
import ru.svladislavv.i18n.LocalizationServiceImpl;
import ru.svladislavv.sender.MessageSender;
import ru.svladislavv.sender.MessageSenderImpl;

public class Main {

    public static void main(String[] args) {
        GeoService geoService = new GeoServiceImpl();
        LocalizationService localizationService = new LocalizationServiceImpl();
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        messageSender.send(headers);
    }
}