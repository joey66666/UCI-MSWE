public class LinkedListSet {
    class LinkedList {
        public class Node {
            public String val;
            public Node next;

            public Node(String val) {
                this(val, null);
            }

            public Node(String val, Node next) {
                this.val = val;
                this.next = next;
            }
        }

        public Node head;
        public int size;

        public LinkedList() {
            head = null;
            size = 0;
        }

        // size of the list
        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // insert to head
        public void addFirst(String val) {
            head = new Node(val, head);
            size += 1;
        }

        // true: exist
        // false: not
        public boolean contains(String val) {
            Node node = head;
            while (node != null) {
                if (node.val.equals(val)) {
                    return true;
                }
                node = node.next;
            }
            return false;
        }
    }

    public LinkedList list;

    public LinkedListSet() {
        list = new LinkedList();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    // true: not exist, added
    // false: exist
    public boolean add(String word) {
        if (word == null) return false;
        if (!list.contains(word)) {
            list.addFirst(word);
            return true;
        } else {
            return false;
        }
    }

    // true: exist
    // false: not
    public boolean contains(String word) {
        return list.contains(word);
    }

    // size of the set
    public int size() {
        return list.size();
    }

    // int[count, minTime, maxTime, avgTime]
    public long[] search(String[] words) {
        int count = 0;
        long curTime = 0;
        long countTime = 0;
        long minTime = Integer.MAX_VALUE;
        long maxTime = Integer.MIN_VALUE;
        long avgTime = 0;

        for (int i = 0; i < words.length; i++) {
            long startTime = System.nanoTime();
            if (!contains(words[i])) {
                count += 1;
            }
            curTime = System.nanoTime() - startTime;
            minTime = Math.min(minTime, curTime);
            maxTime = Math.max(maxTime, curTime);
            countTime += curTime;
        }
        avgTime = countTime / words.length;
        return new long[]{count, minTime, maxTime, avgTime};
    }
}
