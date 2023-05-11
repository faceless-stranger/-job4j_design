package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).isNotNull()
                .contains("second")
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("seven", "nine");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first1", "second1", "three1", "four1", "four1", "five1", "five1");
        assertThat(set).hasSize(5)
                .contains("five1")
                .anySatisfy(s -> s.contains("d"));


    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "three", "three", "four", "five");
        assertThat(map).hasSize(4)
                .contains(Map.entry("first", 0))
                .contains(Map.entry("five", 4))
                .doesNotContain(Map.entry("two", 0));
    }
}