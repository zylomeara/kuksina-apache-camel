package org.camel.beans;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", skipFirstLine = true, skipField = true)
public class Provider {
    @DataField(pos = 1)
    String accountName;

    @DataField(pos = 2)
    float trafficKB;

    @DataField(pos = 3)
    String date;

    @DataField(pos = 4)
    String city;

    @DataField(pos = 5)
    String street;

    @DataField(pos = 6)
    int number;

    @Override
    public String toString() {
        return "Provider{" +
                "accountName='" + accountName + '\'' +
                ", trafficKB=" + trafficKB +
                ", date='" + date + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public float getTrafficKB() {
        return trafficKB;
    }

    public void setTrafficKB(float trafficKB) {
        this.trafficKB = trafficKB;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
