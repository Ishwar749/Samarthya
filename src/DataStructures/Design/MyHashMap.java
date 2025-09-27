package DataStructures.Design;

import java.util.LinkedList;

// Problem: https://leetcode.com/problems/design-hashmap/description/
public class MyHashMap {

    Node[] map;

    public MyHashMap() {
        map = new Node[1000];
    }

    public void put(int key, int value) {
        Node matchedNode = getNode(key);
        if (matchedNode == null) {
            Node newNode = new Node(key, value, null);
            addNode(key, newNode);
        } else {
            matchedNode.value = value;
        }
    }

    public int get(int key) {
        Node matchedNode = getNode(key);
        if (matchedNode == null) return -1;
        return matchedNode.value;
    }

    public void remove(int key) {
        removeNode(key);
    }


    static class Node {
        int key;
        int value;
        Node next;

        public Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int hash(int key) {
        return key % 1000;
    }

    private Node getNode(int key) {
        int index = hash(key);
        Node temp = map[index];
        while (temp != null) {
            if (temp.key == key) return temp;
            temp = temp.next;
        }

        return null;
    }

    private void addNode(int key, Node toAdd) {
        int index = hash(key);
        Node temp = map[index];

        if (temp == null) map[index] = toAdd;
        else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = toAdd;
        }
    }

    private void removeNode(int key) {
        int index = hash(key);
        Node temp = map[index];

        if(temp == null) return;

        if (temp.key == key) map[index] = temp.next;
        else {
            while (temp.next != null && temp.next.key != key) {
                temp = temp.next;
            }

            if (temp.next != null) {
                temp.next = temp.next.next;
            }
        }
    }
}
