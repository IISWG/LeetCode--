package offer;

public class offer1 {
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
    /*
    剑指 Offer II 029. 排序的循环链表,与 708 题相同
     */
    class Solution {
        public Node insert(Node head, int insertVal) {
            if (head == null) {
                Node node = new Node(insertVal);
                return node.next = node;
            }
            Node change = head;
            while (change.next != head) {
                Node next = change.next;
                /*
                当insertVal在两点之间，就可以插入了，这是一种情况
                 */
                if (next.val > change.val ) {
                    if (insertVal <= next.val && insertVal >= change.val) {
                        break;
                    }
                }
                /*
                比最大的大，或比最小的小
                 */
                if (next.val < change.val && (insertVal <= next.val || insertVal >= change.val)) {
                    break;
                }
                change = next;
            }
            /*
            如果都没找到说明原链表节点都相等，或者此时才是最大最小分界
             */
            change.next = new Node(insertVal, change.next);
            return head;
        }
    }

}
