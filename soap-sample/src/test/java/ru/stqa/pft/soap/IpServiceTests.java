package ru.stqa.pft.soap;


import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class IpServiceTests {

    @Test
    public void testIp() {
        String location = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("5.29.20.243");
        assertEquals(location, "<GeoIP><Country>IL</Country><State>05</State></GeoIP>" );
    }

}
