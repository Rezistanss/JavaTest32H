package zad2;

import zad2.exception.DuplicateElementOnListException;
import zad2.exception.InvalidStringContainerPatternException;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class StringContainer {
    private final Pattern pattern;
    private final boolean duplicatedNotAllowed;
    private String[] values;
    private LocalDateTime[] dateTimes;
    private int size;

    public StringContainer(String patternRegex, boolean duplicatedNotAllowed) {
        this.pattern = Pattern.compile(patternRegex);
        this.duplicatedNotAllowed = duplicatedNotAllowed;
        this.values = new String[1];
        this.dateTimes = new LocalDateTime[1];
        this.size = 0;
    }

    private boolean isDuplicate(String value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public void add(String value) {
        if (!pattern.matcher(value).matches()) {
            throw new InvalidStringContainerPatternException("Niepoprawny kod: " + value);
        }

        if (duplicatedNotAllowed && isDuplicate(value)) {
            throw new DuplicateElementOnListException("Duplikat nie jest dozwolony: " + value);
        }

        ensureCapacity();
        values[size] = value;
        dateTimes[size] = LocalDateTime.now();
        size++;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index po za zasięgiem: " + index);
        }
        return values[index];
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index po za zasięgiem: " + index);
        }

        System.arraycopy(values, index + 1, values, index, size - index - 1);
        System.arraycopy(dateTimes, index + 1, dateTimes, index, size - index - 1);
        size--;
    }

    public void remove(String value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            remove(index);
        }
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == values.length) {
            int newCapacity = values.length * 2;
            String[] newValues = new String[newCapacity];
            LocalDateTime[] newDateTimes = new LocalDateTime[newCapacity];
            System.arraycopy(values, 0, newValues, 0, size);
            System.arraycopy(dateTimes, 0, newDateTimes, 0, size);
            values = newValues;
            dateTimes = newDateTimes;
        }
    }
}
