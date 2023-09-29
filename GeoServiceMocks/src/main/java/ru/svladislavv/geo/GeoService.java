package ru.svladislavv.geo;

import ru.svladislavv.entity.Location;

public interface GeoService {

    Location byIp(String ip);

    Location byCoordinates(double latitude, double longitude);
}