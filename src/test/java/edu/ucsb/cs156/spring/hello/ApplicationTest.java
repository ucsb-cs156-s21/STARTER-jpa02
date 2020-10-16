package edu.ucsb.cs156.spring.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ApplicationTest {
  @Test
  public void applicationStarts() {
    Application.main(new String[] {});
  }
}