package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 6, 4))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 6, 4))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        input.add(3);
        input.add(7);
        ListUtils.removeIf(input, i -> i > 5);
        assertThat(input).hasSize(3).containsSequence(1, 3, 3);
    }

    @Test
    void whenReplaceIf() {
        input.add(3);
        input.add(7);
        ListUtils.replaceIf(input, i -> i == 3, 0);
        assertThat(input).hasSize(4).containsSequence(1, 0, 0, 7);
    }

    @Test
    void whenRemoveAll() {
        input.add(3);
        input.add(7);
        List<Integer> element = new ArrayList<>(Arrays.asList(3));
        ListUtils.removeAll(input, element);
        assertThat(input).hasSize(2).containsSequence(1, 7);
    }
}