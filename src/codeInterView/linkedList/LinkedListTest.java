package codeInterView.linkedList;

public class LinkedListTest {

    public static void main(String args[]) {
        // creating LinkedList with 5 elements including head
        MyLinkedListImpl linkedList = new MyLinkedListImpl();
        MyLinkedListImpl.Node head = linkedList.head();
        linkedList.add(new MyLinkedListImpl.Node("1"));
        linkedList.add(new MyLinkedListImpl.Node("2"));
        linkedList.add(new MyLinkedListImpl.Node("3"));
        linkedList.add(new MyLinkedListImpl.Node("4"));
        linkedList.add(new MyLinkedListImpl.Node("5"));
        linkedList.add(new MyLinkedListImpl.Node("5"));

        linkedList.add(new MyLinkedListImpl.Node("5"));

        // finding middle element of MyLinkedListImpl in single pass
        MyLinkedListImpl.Node current = head;
        int length = 0;
        MyLinkedListImpl.Node middle = head;

        while (current.next() != null) {
            length++;
            if (length % 2 == 0) {
                middle = middle.next();
            }
            current = current.next();
        }

        if (length % 2 == 1) {
            middle = middle.next();
        }

        System.out.println("length of MyLinkedListImpl: " + length);
        System.out.println("middle element of MyLinkedListImpl : " + middle);

    }

}

class MyLinkedListImpl {
    private Node head;
    private Node tail;

    public MyLinkedListImpl() {
        this.head = new Node("head");
        tail = head;
    }

    public Node head() {
        return head;
    }

    public void add(Node node) {
        tail.next = node;
        tail = node;
    }

    public static class Node {
        private Node next;
        private String data;

        public Node(String data) {
            this.data = data;
        }

        public String data() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node next() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public String toString() {
            return this.data;
        }
    }
}
