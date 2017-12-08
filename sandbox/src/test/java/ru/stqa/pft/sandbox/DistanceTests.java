package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {
  @Test
  public static void  distancePositiveNumbersTest() {
    Point p = new Point(8,8);
    Assert.assertEquals(p.distance(4,4),5.656854249492381);
  }

  @Test
  public static void distanceNullTest() {
    Point p = new Point(1,1);
    Assert.assertEquals(p.distance(1,1),0.0);
  }

  @Test
  public static void distanceNegativeNumbersTest() {
    Point p = new Point(-32,-12);
    Assert.assertEquals(p.distance(-43,-65),54.12947441089743);
  }
}
