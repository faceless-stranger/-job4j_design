package ru.job4j.assertj;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleCollectionTest {

    @Test
    void generalAssert() {
        SimpleCollection<Integer> sc = new SimpleCollection<>(1, 1, 3, 4, 5);
        assertThat(sc).isNotEmpty()
                /*размер:*/
                .hasSize(5)
                /*содержит элементы:*/
                .contains(1, 5, 4)
                /*содержит это в любом порядке, дубликаты не важны:*/
                .containsOnly(1, 5, 4, 3)
                /*содержит только это и только в указанном порядке:*/
                .containsExactly(1, 1, 3, 4, 5)
                /*содержит только это в любом порядке:*/
                .containsExactlyInAnyOrder(5, 1, 3, 4, 1)
                /*содержит хотя бы один из:*/
                .containsAnyOf(200, 100, 3)
                /*не содержит ни одного из:*/
                .doesNotContain(0, 6)
                /*начинается с последовательности:*/
                .startsWith(1, 1)
                /*заканчивается на последовательность:*/
                .endsWith(4, 5)
                /* содержит последовательность:*/
                .containsSequence(1, 3);

        assertThat(sc).filteredOn(e -> e > 1).first().isEqualTo(3);
    }
}