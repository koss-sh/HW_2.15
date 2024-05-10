import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {
    private final static int DEFAULT_CAPACITY = 10;

    private final Integer[] data;
    private int size;

    public IntegerListImpl(int capacity) {
        this.data = new Integer[capacity];
        this.size = 0;
    }

    public IntegerListImpl() {
        this(DEFAULT_CAPACITY);
    }

    private void checkItem(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("item не должен быть null!");
        }
    }

    private void checkSize() {
        if (size == data.length) {
            throw new IllegalArgumentException("Список полон!");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Выход индекса за границы!");
        }
    }

    @Override
    public Integer add(Integer item) {
        checkItem(item);
        checkSize();
        return data[size++] = item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index == size) {
            return add(item);
        }
        checkItem(item);
        checkIndex(index);
        checkSize();
        System.arraycopy(data, index, data, index + 1, size - index);
        size++;
        return data[index] = item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkItem(item);
        checkIndex(index);
        return data[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        checkItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer removed = data[index];
        if (index < size - 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        data[--size] = null;
        return removed;
    }

    @Override
    public boolean contains(Integer item) {
        checkItem(item);
        Integer[] copy = toArray();
        sortInsertion(copy);
        int min = 0;
        int max = copy.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item == copy[mid]) {
                return true;
            }
            if (item < copy[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    @Override
    public int indexOf(Integer item) {
        checkItem(item);
        for (int i = 0; i < size; i++) {
            if (Objects.equals(item, data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(item, data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (Objects.equals(get(i), otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] newData = new Integer[size];
        System.arraycopy(data, 0, newData, 0, size);
        return newData;
    }

}