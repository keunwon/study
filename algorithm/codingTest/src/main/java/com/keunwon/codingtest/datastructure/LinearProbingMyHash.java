package com.keunwon.codingtest.datastructure;

public class LinearProbingMyHash {
    public Slot[] hashTable;

    public LinearProbingMyHash(Integer size) {
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

        if (hashTable[address].key.equals(key)) {
            hashTable[address].value = value;
            return true;
        }

        Integer currAddress = address + 1;
        while (this.hashTable[currAddress] != null) {
            if (hashTable[currAddress].key.equals(key)) {
                hashTable[currAddress].value = value;
                return true;
            }

            currAddress++;
            if (currAddress >= hashTable.length) { return false; }
        }
        hashTable[currAddress] = new Slot(key, value);

        return true;
    }

    public String getData(String key) {
        int address = hashFunc(key);

        if (hashTable[address] == null) { return null; }

        if (hashTable[address].key.equals(key)) {
            return hashTable[address].value;
        }

        Integer currAddress = address + 1;
        while (hashTable[currAddress] != null) {
            if (hashTable[currAddress].key.equals(key)) {
                return hashTable[currAddress].value;
            }

            currAddress++;
            if (currAddress >= hashTable.length) { return null; }
        }

        return null;
    }

    public class Slot {
        String key;
        String value;

        public Slot(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
