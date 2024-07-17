package com.padi.testcoverage.util;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Test;

class DummyTest {

  @Test
  void dummy(){
    DateTime scheduledDeparture = DateTime.parse("2024-06-17T10:38:36.860").withZone(DateTimeZone.forID("UTC"));
    DateTime scheduledDeparture2 = DateTime.parse("2024-06-17T10:38:36.860");
    DateTime scheduledDepartureLocal = scheduledDeparture.withZone(DateTimeZone.forID("Europe/Rome"));
    DateTime scheduledDepartureLocal2 = DateTime.parse("2024-06-17T10:38:36.860").withZone(DateTimeZone.forID("Europe/Rome"));

    System.out.println(scheduledDeparture);
    System.out.println(scheduledDeparture2);
    System.out.println(scheduledDepartureLocal);
    System.out.println(scheduledDepartureLocal2);
    System.out.println(scheduledDeparture.compareTo(scheduledDepartureLocal));
    System.out.println(scheduledDeparture.compareTo(scheduledDepartureLocal2));
  }

}