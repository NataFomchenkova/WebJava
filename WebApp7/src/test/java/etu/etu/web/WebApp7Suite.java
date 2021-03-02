package etu.etu.web;

import etu.etu.web.client.WebApp7Test;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class WebApp7Suite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for WebApp7");
    suite.addTestSuite(WebApp7Test.class);
    return suite;
  }
}
