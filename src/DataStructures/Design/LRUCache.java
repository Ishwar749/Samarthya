package DataStructures.Design;

import java.util.HashMap;
import java.util.Map;

// Problem: https://leetcode.com/problems/lru-cache/description/

public class LRUCache {

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value, Node prev, Node next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    Map<Integer, Node> map;
    Node startNode;
    Node endNode;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.startNode = new Node(-1, -1, null, null);
        this.endNode = new Node(-1, -1, null, null);
        startNode.next = endNode;
        endNode.prev = startNode;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node currentNode = map.get(key);
        remove(currentNode);
        insert(currentNode);
        return currentNode.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node currentNode = map.get(key);
            remove(currentNode);
            currentNode.value = value;
            insert(currentNode);
        } else {
            if (map.keySet().size() == capacity) {
                remove(endNode.prev);
            }
            Node toAdd = new Node(key, value, null, null);
            insert(toAdd);
        }
    }

    public void remove(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void insert(Node node) {
        map.put(node.key, node);

        node.next = startNode.next;
        startNode.next.prev = node;
        startNode.next = node;
        node.prev = startNode;
    }
}
