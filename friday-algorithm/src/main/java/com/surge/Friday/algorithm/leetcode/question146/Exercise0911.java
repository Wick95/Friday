package com.surge.Friday.algorithm.leetcode.question146;

import java.util.HashMap;

public class Exercise0911 {
    static class LRUCache {
        int capacity = 0;
        int size = 0;
        LinkedNode head, tail;
        HashMap<Integer, LinkedNode> hashMap = new HashMap<>();

        static class LinkedNode {
            int key;
            int value;
            LinkedNode prev, next;

            public LinkedNode() {
            }

            public LinkedNode(int _key, int _value) {
                this.key = _key;
                this.value = _value;
            }
        }

        public LRUCache(int _capacity) {
            this.capacity = _capacity;
            hashMap = new HashMap<>(capacity);
            this.head = new LinkedNode();
            this.tail = new LinkedNode();

            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        public int get(int key) {
            LinkedNode temp = hashMap.get(key);
            if (temp == null) {
                return -1;
            }
            remove(temp);
            moveToHead(temp);
            return temp.value;
        }

        public void put(int key, int value) {
            LinkedNode temp = hashMap.get(key);
            if (temp != null) {
                remove(temp);
                moveToHead(temp);

                return;
            }
            if (size >= capacity) {
                hashMap.remove(tail.prev.key);
                remove(tail.prev);
                size--;
            }

            moveToHead(temp = new LinkedNode(key, value));
            hashMap.put(key, temp);
            size++;
        }

        public void remove(LinkedNode linkedNode) {
            linkedNode.prev.next = linkedNode.next;
            linkedNode.next.prev = linkedNode.prev;
        }


        public void moveToHead(LinkedNode linkedNode) {
            LinkedNode oldHead = this.head.next;
            this.head.next = linkedNode;
            linkedNode.prev = this.head;
            linkedNode.next = oldHead;
            oldHead.prev = linkedNode;
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1);
        lRUCache.put(2, 2);
        System.out.println(lRUCache.get(1));
        lRUCache.put(3, 3);
        System.out.println(lRUCache.get(2));
        lRUCache.put(4, 4);
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(3));
        System.out.println(lRUCache.get(4));
    }
}
