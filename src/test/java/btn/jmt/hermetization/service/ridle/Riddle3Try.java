package btn.jmt.hermetization.service.ridle;

import org.junit.jupiter.api.Test;

final class Riddle3Try {

  @Test
  void tryFinallyWhatHappen() {
    // when
    int whatIGetHere = tryFinally();

    // then
    System.out.println(whatIGetHere);
  }

  private int tryFinally() {
    int x = 0;
    try {
      return x++;
    } finally {
      return x;
    }
  }












  @Test
  void tryFinallyWhatHappenVol2() {
    // when
    int whatIGetHere = tryFinallyVol2();

    // then
    System.out.println(whatIGetHere);
  }

  private int tryFinallyVol2() {
    int x = 0;
    try {
      return x++;
    } finally {
      System.out.println("tryFinallyVol2 x: " + x);
    }
  }
}
