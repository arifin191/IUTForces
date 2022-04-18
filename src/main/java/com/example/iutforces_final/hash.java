package com.example.iutforces_final;

public class hash {
    private static String pass = "";

    public hash(String p) {
        pass = p;
    }


    public static Integer doHash() {
        return pass.hashCode();
    }

    public static Integer main(String[] args) {
        return doHash();
    }
}
