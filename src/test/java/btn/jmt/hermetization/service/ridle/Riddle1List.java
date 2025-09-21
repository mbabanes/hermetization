package btn.jmt.hermetization.service.ridle;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

final class Riddle1List {

  @Test
  void asListRiddle() {
    // given
    Integer[] array = {1, 2, 3, 4, 5, 6, 7};
    List<Integer> listFromArray = Arrays.asList(array);

    // when
    array[0] = 665;

    // then
    System.out.println("Array.asList: " + listFromArray.get(0));
  }

  @Test
  void listOfRiddle() {
    // given
    Integer[] array = {1, 2, 3, 4, 5, 6, 7};
    List<Integer> listFromArray = List.of(array);

    // when
    array[0] = 665;

    // then
    System.out.println("List.Of: " + listFromArray.get(0));
  }
}
