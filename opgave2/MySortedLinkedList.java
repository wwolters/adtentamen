package opgave2;

/**
 * Created by wilco on 30-3-2017.
 */
public class MySortedLinkedList {

    private Node first;
    private Node firstSorted;

    public static void main(String[] args) {
        MySortedLinkedList linkedList = new MySortedLinkedList();
        linkedList.addFirst(2);
        linkedList.addFirst(4);
        linkedList.addFirst(1);
        linkedList.addFirst(3);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringSorted());

        System.out.println("Contains 0: " + linkedList.contains(0));
        System.out.println("Contains 1: " + linkedList.contains(1));
        System.out.println("Contains 2: " + linkedList.contains(2));
        System.out.println("Contains 3: " + linkedList.contains(3));
        System.out.println("Contains 4: " + linkedList.contains(4));
        System.out.println("Contains 5: " + linkedList.contains(5));
    }

    public void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = first;
        first = newNode;

        if (firstSorted == null || newNode.value < firstSorted.value) {
            newNode.nextSorted = firstSorted;
            firstSorted = newNode;
        } else {
            Node current = firstSorted;
            while (current.nextSorted != null && current.nextSorted.value < newNode.value) {
                current = current.nextSorted;
            }
            newNode.nextSorted = current.nextSorted;
            current.nextSorted = newNode;
        }
    }

    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder();

        Node current = first;
        while (current != null) {
            if (current != first) {
                toStringBuilder.append(" -> ");
            }
            toStringBuilder.append(current.value);
            current = current.next;
        }

        return toStringBuilder.toString();
    }

    public String toStringSorted() {
        StringBuilder toStringBuilder = new StringBuilder("[");

        Node current = firstSorted;
        while (current != null) {
            if (current != firstSorted) {
                toStringBuilder.append(", ");
            }
            toStringBuilder.append(current.value);
            current = current.nextSorted;
        }

        toStringBuilder.append("]");

        return toStringBuilder.toString();
    }

    public boolean contains(int data) {
        if (first == null) {
            return false;
        }
        if (firstSorted.value > data) {
            return false;
        }

        Node searchNode = firstSorted;
        if (first.value <= data) {
            searchNode = first;
        }
        while (searchNode != null && searchNode.value < data) {
            searchNode = searchNode.nextSorted;
        }
        return searchNode != null && searchNode.value == data;
    }

    private class Node {
        final int value;
        Node next;
        Node nextSorted;

        private Node(int value) {
            this.value = value;
        }
    }

}
