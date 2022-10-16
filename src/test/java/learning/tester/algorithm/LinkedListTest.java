package learning.tester.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void 연결리스트_생성하면_head_null() {
        LinkedList linkedList = new LinkedList();
        assertNull(linkedList.getHead());
    }

    @Test
    void 노드를_1개_추가하면_head에_nodA가_있다() {
        LinkedList linkedList = new LinkedList();
        Node nodeA = new Node("nodeA");
        linkedList.add(nodeA);

        assertEquals(nodeA, linkedList.getHead());
    }

    @Test
    void 노드를_1개_추가하면_head에_nodeA가_있고_size가_1이다() {
        LinkedList linkedList = new LinkedList();
        Node nodeA = new Node("nodeA");
        linkedList.add(nodeA);

        assertEquals(nodeA, linkedList.getHead());
        assertEquals(1, linkedList.size());
        assertNull(nodeA.getNext());
    }

    @Test
    void 노드를_2개_추가하면_head에_nodeA가_있고_size가_2이다() {
        LinkedList linkedList = new LinkedList();
        Node nodeA = new Node("nodeA");
        linkedList.add(nodeA);
        Node nodeB = new Node("nodeB");
        linkedList.add(nodeB);

        assertEquals(2, linkedList.size());
        assertEquals(nodeA, linkedList.getHead());
        assertEquals(nodeB, linkedList.getHead().next);
    }

    @Test
    void 노드_5개_추가후_순회() {
        LinkedList linkedList = new LinkedList();
        Node nodeA = new Node("nodeA");
        linkedList.add(nodeA);
        Node nodeB = new Node("nodeB");
        linkedList.add(nodeB);
        Node nodeC = new Node("nodeC");
        Node nodeD = new Node("nodeD");
        Node nodeE = new Node("nodeE");
        linkedList.add(nodeC);
        linkedList.add(nodeD);
        linkedList.add(nodeE);

        Node node = linkedList.getHead();
        assertEquals(5, linkedList.size);
        assertEquals(nodeA, node);
        node = node.getNext();
        assertEquals(nodeB, node);
        node = node.getNext();
        assertEquals(nodeC, node);
        node = node.getNext();
        assertEquals(nodeD, node);
        node = node.getNext();
        assertEquals(nodeE, node);
        node = node.getNext();
        assertNull(node);
    }

    @Test
    void 노드_1개_추가후_remove() {
        LinkedList linkedList = new LinkedList();
        Node nodeA = new Node("nodeA");
        linkedList.add(nodeA);

        boolean removed = linkedList.remove(nodeA);

        assertTrue(removed);
        assertEquals(0, linkedList.size);
        assertNull(linkedList.getHead());
    }

    @Test
    void 노드_3개_추가후_가운데_노드_remove() {
        LinkedList linkedList = new LinkedList();
        Node nodeA = new Node("nodeA");
        linkedList.add(nodeA);
        Node nodeB = new Node("nodeB");
        linkedList.add(nodeB);
        Node nodeC = new Node("nodeC");
        linkedList.add(nodeC);

        boolean removed = linkedList.remove(nodeB);

        assertTrue(removed);
        assertEquals(2, linkedList.size);
        assertEquals(nodeA, linkedList.getHead());
        assertEquals(nodeC, linkedList.getHead().next);
    }

    @Test
    void 빈_리스트_remove() {
        LinkedList linkedList = new LinkedList();
        Node nodeA = new Node("nodeA");

        boolean removed = linkedList.remove(nodeA);
        assertFalse(removed);
    }

    @Test
    void 없는노드_remove() {
        LinkedList linkedList = new LinkedList();
        Node nodeA = new Node("nodeA");
        linkedList.add(nodeA);

        Node nodeB = new Node("nodeB");
        boolean removed = linkedList.remove(nodeB);
        assertFalse(removed);
    }

    private class LinkedList {
        private int size;
        private Node head;
        private Node tail;

        public void add(Node node) {
            size++;
            if (head == null) {
                head = tail = node;
            } else {
                tail.setNext(node);
                tail = node;
            }
        }

        public int size() {
            return size;
        }

        public Node getHead() {
            return head;
        }

        public boolean remove(Node targetNode) {
            Node searching = head;
            if (searching == null)
                return false;

            if (targetNode.equals(searching)) {
                head = head.next;
                size--;
                return true;
            }

            while (searching.next != null) {
                if (targetNode.equals(searching.next)) {
                    searching.next = searching.next.next;
                    size--;
                    return true;
                }
                searching = searching.next;
            }
            return false;
        }
    }

    private class Node {
        private Node next;
        private String data;

        public Node() {
        }
        public Node(String data) {
            this.data = data;
        }


        public Node getNext() {
            return next;
        }

        public void setNext(Node node) {
            next = node;
        }
    }
}
