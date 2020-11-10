public class HashTableSet {
    class Node {
        String val;
        Node next;

        public Node(String val) {
            this.val = val;
            next = null;
        }

        public Node() {
            this(null);
        }
    }


    public int arraySize = 32;
    public int size = 0;
    public Node[] hashArray;

    public HashTableSet() {
        hashArray = new Node[arraySize];
    }

    public int hash(String word) {
        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            sum += word.charAt(i);
        }
        return (sum % arraySize);
    }

    public boolean add(String word) {
        if (word == null) return false;
        if (contains(word)) return false;
        else {

            int hashIndex = hash(word);
            if (hashArray[hashIndex] == null) {
                hashArray[hashIndex] = new Node(word);
            } else {
                Node node = hashArray[hashIndex];
                while (node.next != null) {
                    node = node.next;
                }
                node.next = new Node(word);
            }
            size += 1;
            return true;
        }


    }

    public boolean contains(String word) {
        int hashIndex = hash(word);
        if (hashArray[hashIndex] != null) {
            Node node = hashArray[hashIndex];
            while (node != null) {
                if (node.val.equals(word)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public int size() {
        return size;
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
