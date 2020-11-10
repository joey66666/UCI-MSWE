public class BinaryTreeSet {
    BinaryTree tree;

    class BinaryTree {
        class Node {
            public String val;
            Node left;
            Node right;

            public Node(String val, Node left, Node right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }

            public Node(String val) {
                this.val = val;
                this.left = null;
                this.right = null;
            }
        }

        public Node root;
        public int size;

        public BinaryTree() {
            root = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean add(String data) {
            if (!contains(root, data)) {
                root = add(root, data);
                return true;
            } else {
                return false;
            }
        }

        public Node add(Node node, String data) {
            if (node == null) {
                size += 1;
                node = new Node(data);
            }
            if (node.val.compareTo(data) < 0) {
                node.right = add(node.right, data);
            } else if (node.val.compareTo(data) > 0) {
                node.left = add(node.left, data);
            }
            return node;
        }

        public boolean contains(String data) {
            return this.contains(root, data);
        }

        public boolean contains(Node node, String data) {
            if (node == null) {
                return false;
            }
            if (node.val.compareTo(data) < 0) {
                return contains(node.right, data);
            } else if (node.val.compareTo(data) > 0) {
                return contains(node.left, data);
            }
            return true;
        }

        public int size() {
            return size;
        }
    }

    public BinaryTreeSet() {
        tree = new BinaryTree();
    }

    public boolean add(String data) {
        return tree.add(data);
    }

    public boolean contains(String data) {
        return tree.contains(data);
    }

    public int size() {
        return tree.size();
    }

//    public int search(String[] words) {
//        int count = 0;
//        for (String word : words) {
//            if (!contains(word)) {
//                count += 1;
//            }
//        }
//        return count;
//    }

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
