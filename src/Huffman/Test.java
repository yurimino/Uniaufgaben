package Huffman;

import java.io.File;
import java.net.StandardSocketOptions;

public class Test {
    public static void main(String[] args) {
        System.out.println(Huffman.decode(new File("message.txt")));
    }

}
