import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;


public class IntegerListImplTest {

    private final IntegerList integerList = new IntegerListImpl();

    @AfterEach
    public void afterEach() {
        integerList.clear();
    }

    private void add(Integer[] elements) {
        assertThat(integerList.isEmpty()).isTrue();
        Stream.of(elements).forEach(integerList::add);
        assertThat(integerList.size()).isEqualTo(elements.length);
    }

    @Test
    void addTest() {
        Integer[] elements = {5, 4, 1, 3, 2};
        add(elements);
        for (int i = 0; i < elements.length; i++) {
            assertThat(integerList.get(i)).isEqualTo(elements[i]);
            assertThat(integerList.contains(elements[i])).isTrue();
            assertThat(integerList.indexOf(elements[i])).isEqualTo(i);
            assertThat(integerList.lastIndexOf(elements[i])).isEqualTo(i);
        }
        assertThat(integerList.toArray()).hasSize(elements.length);
        assertThat(integerList.toArray()).containsExactly(elements);
    }

    @Test
    void addByIndexTest() {
        Integer[] elements = {5, 4, 1, 3, 2};
        add(elements);
        integerList.add(0, 6);
        assertThat(integerList.size()).isEqualTo(elements.length + 1);
        assertThat(integerList.get(0)).isEqualTo(6);
        integerList.add(3, 7);
        assertThat(integerList.size()).isEqualTo(elements.length + 2);
        assertThat(integerList.get(3)).isEqualTo(7);
        assertThat(integerList.indexOf(7)).isEqualTo(3);
        assertThat(integerList.lastIndexOf(7)).isEqualTo(3);
        integerList.add(7, 9);
        assertThat(integerList.size()).isEqualTo(elements.length + 3);
        assertThat(integerList.get(7)).isEqualTo(9);
        assertThat(integerList.indexOf(9)).isEqualTo(7);
        assertThat(integerList.lastIndexOf(9)).isEqualTo(7);
    }
}
