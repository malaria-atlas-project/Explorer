package uk.ac.ox.map.request.client.place;

import org.junit.Assert;
import org.junit.Test;

import uk.ac.ox.map.explorer.client.place.QueryStringBuilder;

public class QueryStringBuilderTest {
  
  @Test
  public void testQsMulti() {
    QueryStringBuilder qsb = new QueryStringBuilder('&');
    qsb.addParam("country", "BEL", "BRA", "BEN");
    Assert.assertEquals("country=BEL,BRA,BEN", qsb.finish());
  }
  
  @Test
  public void testQsMulti2() {
    QueryStringBuilder qsb = new QueryStringBuilder('&');
    qsb.addParam("country", "BEL", "BRA", "BEN");
    qsb.addParam("ids", "BEL", "BRA", "BEN");
    Assert.assertEquals("country=BEL,BRA,BEN&ids=BEL,BRA,BEN", qsb.finish());
  }
  
  @Test
  public void testQsSingle() {
    String[] ids = {"BEL"};
    QueryStringBuilder qsb = new QueryStringBuilder('&');
    qsb.addParam("country", ids);
    Assert.assertEquals("country=BEL", qsb.finish());
  }

}
