import ru.svladislavv.entity.Country;
import ru.svladislavv.i18n.LocalizationServiceImpl;

import org.junit.jupiter.api.*;

public class LocalizationServiceImplTest {

    @BeforeAll
    static void initSuite() { System.out.println("Running LocalizationServiceImplTest:"); }

    @AfterAll
    static void completeSuite() { System.out.println("LocalizationServiceImplTest complete!!!"); }

    @BeforeEach
    void setUp() { System.out.println("Starting new test..."); }

    @AfterEach
    void tearDown() { System.out.println("Test Complete!"); }

    @Test
    public void localeRussiaTest() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        String expected = "Добро пожаловать";
        String actual = localizationService.locale(Country.RUSSIA);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void localeUsaTest() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        String expected = "Welcome";
        String actual = localizationService.locale(Country.USA);

        Assertions.assertEquals(actual, expected);
    }

}