package com.keunwon.codingtest.datastructure;

public class ChainingMyHash {
    public Slot[] hashTable;

    public ChainingMyHash(Integer size) {
        this.hashTable = new Slot[size];
    }

    public int hashFunc(String key) {
        return ((int)key.charAt(0)) % this.hashTable.length;
    }

    public boolean saveData(String key, String value) {
        int address = hashFunc(key);

        if (hashTable[address] == null) {
            hashTable[address] = new Slot(key, value);
            return true;
        }

        Slot findSlot = hashTable[address];
        Slot prevSlot = hashTable[address];

        while (findSlot != null) {
            if (findSlot.key.equals(key)) {
                findSlot.value = value;
                return true;
            }

            prevSlot = findSlot;
            findSlot = findSlot.next;
        }
        prevSlot.next = new Slot(key, value);

        return true;
    }

    public String getData(String key) {
        int address = hashFunc(key);

        if (hashTable[address] == null) { return null; }

        Slot findSlot = hashTable[address];
        while (findSlot != null) {
            if (findSlot.key.equals(key)) {
                return findSlot.value;
            }
            findSlot = findSlot.next;
        }

        return null;
    }

    public class Slot {
        String key;
        String value;
        Slot next;

        public Slot(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
