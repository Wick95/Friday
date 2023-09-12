package com.surge.Friday.algorithm.leetcode.question146;

import com.surge.Friday.algorithm.ListNode;

import java.util.HashMap;

public class Exercise0907 {

    static class LinkedNode {
        int key;
        int value;
        LinkedNode prev;
        LinkedNode next;

        public LinkedNode() {
        }

        public LinkedNode(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    static class LRUCache {
        int capacity = 0;
        int size = 0;
        private LinkedNode head = new LinkedNode(), tail = new LinkedNode();

        private HashMap<Integer, LinkedNode> hashMap;


        public LRUCache(int capacity) {
            hashMap = new HashMap<>(capacity);
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        public Integer get(int key) {
            LinkedNode linkedNode = hashMap.get(key);
            if (linkedNode == null) {
                return -1;
            }
            remove(linkedNode);
            setAtHead(linkedNode);
            return linkedNode.value;
        }


        public void put(int key, int value) {
            if (hashMap.containsKey(key)) {
                //移除该节点的当前位置，并放入第一位
                LinkedNode linkedNode = hashMap.get(key);
                linkedNode.value = value;
                remove(linkedNode);
                setAtHead(linkedNode);
            } else {
                // 是否超出容量
                if (size >= capacity) {
                    //如果超出容量,移除长时间未使用的节点，最后一个节点
                    LinkedNode linkedNode = removeAtTail();
                    hashMap.remove(linkedNode.key);
                    size--;
                }
                //放置到第一位
                LinkedNode newEle = new LinkedNode(key, value);
                setAtHead(newEle);

                //放置到hashMap中
                hashMap.put(key, newEle);
                size++;
            }
        }

        public void remove(LinkedNode listNode) {
            listNode.prev.next = listNode.next;
            listNode.next.prev = listNode.prev;
        }

        public void setAtHead(LinkedNode listNode) {
            LinkedNode temp = this.head.next;
            this.head.next = listNode;
            listNode.prev = this.head;
            listNode.next = temp;
            temp.prev = listNode;
        }

        public LinkedNode removeAtTail() {
            LinkedNode linkedNode = this.tail.prev;
            remove(linkedNode);
            return linkedNode;
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
