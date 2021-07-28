package com.keunwon.codingtest.datastructure;

public class MyHash {
    public Slot[] hashTable;

    public MyHash(Integer size) {
        this.hashTable = new Slot[size];
    }

    public int hashFunc(String key) {
        return ((int)key.charAt(0)) % this.hashTable.length;
    }

    public boolean saveData(String key, String value) {
        int address = hashFunc(key);

        if (hashTable[address] != null) {
            hashTable[address].value = value;
        } else {
            hashTable[address] = new Slot(value);
        }
        return true;
    }

    public String getData(String key) {
        Slot slot = hashTable[hashFunc(key)];
        return slot != null ? slot.value : null;
    }

    public class Slot {
        String value;

        public Slot(String value) {
            this.value = value;
        }
    }
}
