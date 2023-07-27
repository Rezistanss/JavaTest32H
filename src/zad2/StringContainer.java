package zad2;

import zad2.exception.DuplicateElementOnListException;
import zad2.exception.InvalidStringContainerPatternException;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

class StringContainer {
    private static class Node {
        String value;
        LocalDateTime dateTimeAdded;
        Node next;

        Node(String value, Node next) {
            this.value = value;
            this.dateTimeAdded = LocalDateTime.now();
            this.next = next;
        }
    }

    private final Pattern pattern;
    private final boolean duplicatedNotAllowed;
    private Node head;
    private int size;

    public StringContainer(String patternRegex, boolean duplicatedNotAllowed) {
        this.pattern = Pattern.compile(patternRegex);
        this.duplicatedNotAllowed = duplicatedNotAllowed;
        this.head = null;
        this.size = 0;
    }

    private boolean isDuplicate(String value) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void add(String value) {
        if (!pattern.matcher(value).matches()) {
            throw new InvalidStringContainerPatternException("Invalid pattern: " + value);
        }

        if (duplicatedNotAllowed && isDuplicate(value)) {
            throw new DuplicateElementOnListException("Duplicated element not allowed: " + value);
        }

        head = new Node(value, head);
        size++;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        if (index == 0) {
            head = head.next;
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            prev.next = prev.next.next;
        }
        size--;
    }

    public void remove(String value) {
        if (head == null) {
            return;
        }

        if (head.value.equals(value)) {
            head = head.next;
            size--;
            return;
        }

        Node prev = head;
        Node current = head.next;
        while (current != null) {
            if (current.value.equals(value)) {
                prev.next = current.next;
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    public StringContainer getDataBetween(LocalDateTime dateFrom, LocalDateTime dateTo) {
        StringContainer filteredContainer = new StringContainer(pattern.pattern(), duplicatedNotAllowed);

        Node current = head;
        while (current != null) {
            LocalDateTime dateTimeAdded = current.dateTimeAdded;
            if ((dateFrom == null || dateTimeAdded.isAfter(dateFrom)) &&
                    (dateTo == null || dateTimeAdded.isBefore(dateTo))) {
                filteredContainer.add(current.value);
            }
            current = current.next;
        }

        return filteredContainer;
    }
}