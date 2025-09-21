package btn.jmt.hermetization.service.ridle;

import org.junit.jupiter.api.Test;

final class Riddle4Integer {
  @Test
  void whatHappenInteger() {

    //given
    Integer value1 = 20;
    Integer value2 = 20;

    //when then
    System.out.println(value1 == value2);

    //given
    Integer value3 = 200;
    Integer value4 = 200;

    //when then
    System.out.println(value3 == value4);
  }

}
