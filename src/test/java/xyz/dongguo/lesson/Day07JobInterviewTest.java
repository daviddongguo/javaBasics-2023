package xyz.dongguo.lesson;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day07JobInterviewTest {
  Day07JobInterview solustion;

  @BeforeEach
  void init(){
    solustion = new Day07JobInterview();
  }

  @Test
  void askQuestions() {
    solustion.askQuestions();
    assertTrue(true);

  }

  @Test
  void rob() {
    int[] inputs = {114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240};
    int expected = 4173;
    int result = solustion.robHouseUsingLoop(inputs);

    assertEquals(expected, result);
  }

  @Test
  void rob2() {
    int[] inputs = {114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240};
    int expected = 4173;
    int result = solustion.robeHouseUsingRecursion(inputs);

    assertEquals(expected, result);
  }
}