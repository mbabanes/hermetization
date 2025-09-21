package btn.jmt.hermetization.service.ridle;

import java.util.List;
import org.junit.jupiter.api.Test;

final class Riddle2Stream {

  @Test
  void streamRiddle() {
    // given
    List<String> emptyList = List.of();

    // when then
    boolean nMatchEG = emptyList.stream().noneMatch("eg"::equals);
    System.out.println("nMatchEG: " + nMatchEG);

    boolean allMatchEG = emptyList.stream().allMatch("eg"::equals);
    System.out.println("allMatchEG: " + allMatchEG);

    boolean anyMatchEG = emptyList.stream().anyMatch("eg"::equals);
    System.out.println("anyMatchEG: " + anyMatchEG);
  }



}
