package codeInterView.linkedList;

public class LinkedList1 {

    private Node head;

    public LinkedList1() {
        this.head = new Node("HEad");
    }

    public void insert(Node node) {

        Node current = head;
        while (current.next != null) {
            current = current.next();
        }
        current.setNext(node);
    }

    public boolean isCyclic() {
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast != null) {

            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    static class Node {
        private Node next;
        private String text;

        public Node(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node next() {
            return next;
        }
    }

}
