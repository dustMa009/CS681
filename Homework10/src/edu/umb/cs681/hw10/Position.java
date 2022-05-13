package edu.umb.cs681.hw10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Position {
    private final double latitude, longitude, altitude;

    public Position(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.getLongitude();
    }

    public double getAltitude() {
        return this.getAltitude();
    }

    public String toString() {
        return this.latitude + " " + this.longitude + " " + this.altitude;
    }

    public boolean equals(Position another) {
        if ( this.toString().equals(another.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Double> getCoordinate() {
        ArrayList<Double> coordinates = new ArrayList<>();
        coordinates.add(this.latitude);
        coordinates.add(this.longitude);
        return coordinates;
    }

    public Position changeLat(double newLat) {
        return new Position(newLat, this.longitude, this.altitude);
    }

    public Position changeLon(double newLon) {
        return new Position(this.latitude, newLon, this.altitude);
    }

    public Position changeAlt(double newAlt) {
        return new Position(this.latitude, this.longitude, newAlt);
    }

    public double distanceTo(Position anotherPos) {
        double distance = Math.sqrt(Math.pow(this.latitude - anotherPos.latitude,2) +
                Math.pow(this.longitude - anotherPos.longitude, 2) + Math.pow(this.altitude - anotherPos.altitude, 2));
        return distance;
    }
}
