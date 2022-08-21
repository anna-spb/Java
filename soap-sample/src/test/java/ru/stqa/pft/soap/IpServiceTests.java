package ru.stqa.pft.soap;


import com.lavasoft.GeoIPService;

import com.lavasoft.GetIpLocation;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IpServiceTests {

    @Test
    public void testIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("123456");

    }


}
