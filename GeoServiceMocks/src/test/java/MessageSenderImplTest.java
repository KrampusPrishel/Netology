import ru.svladislavv.entity.Country;
import ru.svladislavv.entity.Location;
import ru.svladislavv.geo.GeoServiceImpl;
import ru.svladislavv.i18n.LocalizationServiceImpl;
import ru.svladislavv.sender.MessageSenderImpl;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.Map;
import java.util.HashMap;

public class MessageSenderImplTest {

    @BeforeAll
    static void initSuite() { System.out.println("Running MessageSenderImplTest:"); }

    @AfterAll
    static void completeSuite() { System.out.println("MessageSenderImplTest complete!!!"); }

    @BeforeEach
    void setUp() { System.out.println("Starting new test..."); }

    @AfterEach
    void tearDown() { System.out.println("\nTest Complete!"); }

    @Test
    public void sendMessageRussiaIpTest() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        String expected = "Добро пожаловать";
        Map<String, String> user = new HashMap<String, String>();
        user.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.1.0.1");
        String actual = messageSender.send(user);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void sendMessageUsaIpTest() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        String expected = "Welcome";
        Map<String, String> user = new HashMap<String, String>();
        user.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.1.0.1");
        String actual = messageSender.send(user);

        Assertions.assertEquals(expected, actual);
    }

}