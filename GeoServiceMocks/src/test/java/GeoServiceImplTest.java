import ru.svladislavv.geo.GeoServiceImpl;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static ru.svladislavv.entity.Country.RUSSIA;
import static ru.svladislavv.entity.Country.USA;

public class GeoServiceImplTest {
    static GeoServiceImpl sut = new GeoServiceImpl();

    @BeforeAll
    static void initSuite() { System.out.println("Running GeoServiceImplTest:"); }

    @AfterAll
    static void completeSuite() { System.out.println("GeoServiceImplTest complete!!!"); }

    @BeforeEach
    void setUp() { System.out.println("Starting new test..."); }

    @AfterEach
    void tearDown() { System.out.println("\nTest Complete!"); }

    @ParameterizedTest
    @ValueSource(strings = {"172.1.0.1", "172.1.1.1"})
    public void byRussiaIpTest(String ip) {
        Assertions.assertEquals(RUSSIA, sut.byIp(ip).getCountry());
        System.out.print("Test byIp (" + ip + ")");
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.1.0.1", "96.1.1.1"})
    public void byUsaIpTest(String ip) {
        Assertions.assertEquals(USA, sut.byIp(ip).getCountry());
        System.out.print("Test byIp (" + ip + ")");
    }

    @Test
    public void byIpLocalhostTest(TestInfo byIpLocalhostTestInfo) {
        Assertions.assertNull(sut.byIp("127.0.0.1").getCountry(), byIpLocalhostTestInfo.getDisplayName() + " not complete");
        System.out.print(byIpLocalhostTestInfo.getDisplayName());
    }

    @Test
    public void byCoordinatesTest(TestInfo byCoordinatesTestInfo) {
        Assertions.assertThrows(RuntimeException.class, () -> sut.byCoordinates(55.45, 37.36),
                byCoordinatesTestInfo.getDisplayName() + " not complete");
        System.out.print(byCoordinatesTestInfo.getDisplayName());
    }

}