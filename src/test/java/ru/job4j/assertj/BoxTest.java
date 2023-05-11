package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void whatsThisSphere() {
        Box test = new Box(0, 9);
        String name = test.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .contains("Sphere")
                .startsWith("Sp")
                .isEqualTo("Sphere");
        assertThat(test.getArea()).isGreaterThan(1016d)
                .isLessThan(1018d)
                .isEqualTo(1017.9d, withPrecision(0.1d));
    }

    @Test
    void whatsThisSCube() {
        Box test = new Box(8, 4);
        String name = test.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .contains("Cube")
                .startsWith("Cu")
                .isEqualTo("Cube");
        assertThat(test.getArea()).isGreaterThan(95d)
                .isLessThan(97d)
                .isEqualTo(96d, withPrecision(0.1d));
    }

    @Test
    void whatsThisSTetrahedron() {
        Box test = new Box(4, 4);
        String name = test.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .contains("Tetrahedron")
                .startsWith("Tetr")
                .isEqualTo("Tetrahedron");
        assertThat(test.getArea()).isGreaterThan(27d)
                .isLessThan(28d)
                .isEqualTo(27.7d, withPrecision(0.1d));
    }

    @Test
    void isExistFalse() {
        Box test = new Box(3, 3);
        assertThat(test.isExist()).isFalse();
    }

    @Test
    void isExistTrue() {
        Box test = new Box(4, 3);
        assertThat(test.isExist()).isTrue();
    }

    @Test
    void getNumberOfVertices4() {
        Box test = new Box(4, 4);
        int result = test.getNumberOfVertices();
        assertThat(result).isEqualTo(4);
    }

    @Test
    void getNumberOfVertices8() {
        Box test = new Box(8, 4);
        int result = test.getNumberOfVertices();
        assertThat(result).isEqualTo(8);
    }
}