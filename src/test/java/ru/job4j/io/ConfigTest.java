package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Vlad");
    }

    @Test
    void whenInvalidFormatComments() {
        String path = "./data/pair_without_comment_Invalid.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Vlad");
    }

    @Test
    void whenInvalidFormatKeyAndValue() {
        String path = "./data/pair_without_comment_Invalid_Key_Value.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> config.load());
        assertEquals("Синтаксис нарушен name=", exception.getMessage());
    }

    @Test
    void whenDoubleIsEqual() {
        String path = "./data/pair_without_comment_double_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Vlad=");
    }
}