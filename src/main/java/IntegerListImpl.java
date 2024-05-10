import java.util.Objects;

public class IntegerListImpl implements IntegerList {
    private final static int DEFAULT_CAPACITY = 10;

    public Integer[] data;
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
            grow();
        }
    }

    private void grow() {
        Integer[] data = new Integer[(int) (this.data.length * 1.5)];
        System.arraycopy(this.data, 0, data, 0, this.data.length);
        this.data = data;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
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
        quickSort(copy, 0, copy.length - 1);
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

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapitems(arr, i, j);
            }
        }
        swapitems(arr, i + 1, end);
        return i + 1;
    }

    private static void swapitems(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
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