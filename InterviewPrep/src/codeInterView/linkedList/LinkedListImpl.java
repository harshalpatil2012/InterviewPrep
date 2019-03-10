package codeInterView.linkedList;

public class LinkedListImpl {
    private Node head;

    public LinkedListImpl() {
        this.head = new Node("head");
    }

    public Node head() {
        return head;
    }

    public void add(Node node) {
        Node current = head;

        // find last element of LinkedList i.e. tail
        while (current.next() != null) {
            current = current.next();
        }
        // appending new node to tail in LinkedList
        current.setNext(node);
    }

    /*
     * If singly LinkedList contains Cycle then following would be true 1) slow
     * and fast will point to same node i.e. they meet On the other hand if fast
     * will point to null or next node of fast will point to null then
     * LinkedList does not contains cycle.
     */
    public boolean isCyclic() {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // if fast and slow pointers are meeting then LinkedList is cyclic
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head.next();
        while (current != null) {
            sb.append(current)
                .append("-->");
            current = current.next();
        }
        sb.delete(sb.length() - 3, sb.length()); // to remove --> from last node
        return sb.toString();
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

        @Override
        public String toString() {
            return this.data;
        }
    }

    public static void main(String args[]) { // creating LinkedList with 5
                                             // elements including head
        LinkedListImpl linkedList = new LinkedListImpl();
        linkedList.add(new LinkedListImpl.Node("101"));
        linkedList.add(new LinkedListImpl.Node("201"));
        linkedList.add(new LinkedListImpl.Node("301"));
        linkedList.add(new LinkedListImpl.Node("401"));
        linkedList.add(new LinkedListImpl.Node("101"));
        System.out.println("Linked List : " + linkedList);
        if (linkedList.isCyclic()) {
            System.out.println("Linked List is cyclic as it contains cycles or loop");
        } else {
            System.out.println("LinkedList is not cyclic, no loop or cycle found");
        }
    }

}
