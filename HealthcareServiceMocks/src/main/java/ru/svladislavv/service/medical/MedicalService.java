package ru.svladislavv.service.medical;

import java.math.BigDecimal;

import ru.svladislavv.entity.BloodPressure;

public interface MedicalService {

    void checkBloodPressure(String patientId, BloodPressure bloodPressure);

    void checkTemperature(String patientId, BigDecimal temperature);
}