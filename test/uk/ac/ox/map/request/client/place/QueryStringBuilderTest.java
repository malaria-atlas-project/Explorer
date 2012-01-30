package uk.ac.ox.map.request.client.place;

import org.junit.Assert;
import org.junit.Test;

import uk.ac.ox.map.explorer.client.place.QueryStringBuilder;

public class QueryStringBuilderTest {
  
  @Test
  public void testQsMulti() {
    String[] ids = {"BEL", "BRA", "BEN"};
    QueryStringBuilder qsb = new QueryStringBuilder('&');
    qsb.addParam("country", ids);
    Assert.assertEquals("country=BEL,BRA,BEN", qsb.finish());
  }
  
  @Test
  public void testQsSingle() {
    String[] ids = {"BEL"};
    QueryStringBuilder qsb = new QueryStringBuilder('&');
    qsb.addParam("country", ids);
    Assert.assertEquals("country=BEL", qsb.finish());
  }

}
