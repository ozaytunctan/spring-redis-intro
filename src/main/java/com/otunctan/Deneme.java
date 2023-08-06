package com.otunctan;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Deneme {

    public static void main(String[] args) {

// ByteStream
//        try (BufferedInputStream bis
//                     = new BufferedInputStream(new ClassPathResource("salary.txt").getInputStream())) {
//
//
//            StringBuilder builder = new StringBuilder();
//            int data = 0;
//            while ((data = bis.read()) != -1) {
//                //M1
////                char c = (char) data;
////                builder.append(c);
//
//                //M2
////                byte[] bytes = new byte[bis.available()];
////                bis.read(bytes);
////                System.out.println((char) data);
////                System.out.println(new String(bytes));
//            }
//            System.out.println(builder);
//        } catch (Exception e) {
//             e.printStackTrace();
//        }


//        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("salary.txt");


        //CharacteStream
        try (BufferedReader bis
                     = new BufferedReader(new InputStreamReader(new ClassPathResource("salary.txt").getInputStream()))) {

            StringBuilder builder = new StringBuilder();
            String data;
            while ((data = bis.readLine()) != null) {
                builder.append(data).append("\n");
            }
            System.out.println(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
