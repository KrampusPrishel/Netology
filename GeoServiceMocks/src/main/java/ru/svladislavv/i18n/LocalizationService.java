package ru.svladislavv.i18n;

import ru.svladislavv.entity.Country;

public interface LocalizationService {

    String locale(Country country);
}