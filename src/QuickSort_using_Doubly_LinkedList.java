public class QuickSort_using_Doubly_LinkedList {
    Node head;
    Node tail;
    public City data;

    static class Node {
        protected City data;
        public Node next;
        public Node prev;

        Node(City d) {
            data = d;
            next = null;
            prev = null;
        }

        public City getData() {
            return data;
        }

    }

    void push(City new_Data)
    {
        Node new_Node = new Node(new_Data);
        if (head == null) {
            head = tail = new_Node;
            //head's previous will be null
            head.prev = null;
            //tail's next will be null
            tail.next = null;
        } else {
            //add newNode to the end of list. tail->next set to newNode
            tail.next = new_Node;
            //newNode->previous set to tail
            new_Node.prev = tail;
            //newNode becomes new tail
            tail = new_Node;
            //tail's next point to null
            tail.next = null;
        }
    }


    // A utility function to find last node of linked list
    Node lastNode(Node node) {
        while (node.next != null)
            node = node.next;
        return node;
    }

    Node partition(Node l, Node h) {
        // set pivot as h element
        City pivot = h.data;

        // similar to i = l-1 for array implementation
        Node i = l.prev;

        // Similar to "for (int j = l; j <= h- 1; j++)"
        for (Node j = l; j != h; j = j.next) {
            if (j.data.compareTo(pivot)==-1) {
                // Similar to i++
                i = (i == null) ? l : i.next;
                City temp = i.data;
                i.data = j.data;
                j.data = temp;
            }else if(j.data.compareTo(pivot)==0){
                if(j.data.name == pivot.name) {
                    if(j.data.ID<pivot.ID){
                        i = (i == null) ? l : i.next;
                        City temp = i.data;
                        i.data = j.data;
                        j.data = temp;
                    }
                }
            }
        }
        i = (i == null) ? l : i.next;  // Similar to i++
        City temp = i.data;
        i.data = h.data;
        h.data = temp;
        return i;
    }

    /* A recursive implementation of quicksort for linked list */
    void _quickSort(Node l, Node h) {
        if (h != null && l != h && l != h.next) {
            Node temp = partition(l, h);
            _quickSort(l, temp.prev);
            _quickSort(temp.next, h);
        }
    }

    public void quickSort(Node node) {
        // Find last node
        Node head = lastNode(node);

        // Call the recursive QuickSort
        _quickSort(node, head);
    }

    public String toString() {

        String s = String.valueOf(this.data);
        return s;
    }
}
