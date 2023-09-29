import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.svladislavv.entity.BloodPressure;
import ru.svladislavv.entity.HealthInfo;
import ru.svladislavv.entity.PatientInfo;
import ru.svladislavv.repository.PatientInfoRepository;
import ru.svladislavv.service.alert.SendAlertService;
import ru.svladislavv.service.medical.MedicalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MedicalServiceImplTest {

    @BeforeAll
    static void initSuite() {
        System.out.println("Running MedicalServiceImplTest:");
    }

    @AfterAll
    static void completeSuite() {
        System.out.println("MedicalServiceImplTest complete!!!");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Starting new test...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Complete!");
    }

    @ParameterizedTest
    @CsvSource(value = {"140| 90| 1| Warning, patient with id: 101, need help",
            "140| 95| 0| "}, delimiterString = "|")
    public void checkBloodPressureTest(int high, int low, int numInvoked, String message) {
        PatientInfo patient = new PatientInfo("101", "Homer", "Simpson",
                LocalDate.of(1998, 11, 11),
                new HealthInfo(BigDecimal.valueOf(36.5), new BloodPressure(120, 80)));

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito.when(patientInfoRepository.getById("101")).thenReturn(patient);

        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        Mockito.doNothing().when(sendAlertService).send(Mockito.anyString());

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkBloodPressure("101", new BloodPressure(high, low));

        Mockito.verify(sendAlertService, Mockito.times(numInvoked)).send(message);
    }

    @ParameterizedTest
    @CsvSource(value = {"38.6| 1| Warning, patient with id: 101, need help",
            "38.6| 0| "}, delimiterString = "|")
    public void checkTemperatureTest(BigDecimal temperature, int numInvoked, String message) {
        PatientInfo patient = new PatientInfo("001", "Homer", "Simpson",
                LocalDate.of(1998, 11, 11),
                new HealthInfo(BigDecimal.valueOf(36.5), new BloodPressure(120, 80)));

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito.when(patientInfoRepository.getById("101")).thenReturn(patient);

        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        Mockito.doNothing().when(sendAlertService).send(Mockito.anyString());

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkTemperature("101", temperature);

        Mockito.verify(sendAlertService, Mockito.times(0)).send(message);
    }


    @Test
    public void getPatientInfoTest() {
        PatientInfo expected = new PatientInfo("101", "Homer", "Simpson",
                LocalDate.of(1998, 11, 11),
                new HealthInfo(BigDecimal.valueOf(36.5), new BloodPressure(120, 80)));

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito.when(patientInfoRepository.getById("101")).thenReturn(expected);

        PatientInfo actual = patientInfoRepository.getById("101");
        Assertions.assertEquals(actual, expected);
    }
}