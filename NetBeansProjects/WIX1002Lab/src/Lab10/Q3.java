/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab10;

/**
 *
 * @author chiew256
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Q3 {
    public static void main(String[] args) {
        ShuffleCipher encode = new ShuffleCipher("product.txt", "ShuffleEncrypt.txt", 2);
        ShuffleCipher decode = new ShuffleCipher("ShuffleEncrypt.txt", "ShuffleDecrypt.txt", 2);
        encode.readPlainFile();
        decode.readEncryptFile();
    }
}

class ShuffleCipher implements MessageEncoder{
    private String inputFileName;
    private String outputFileName;
    private int N;

    public ShuffleCipher(String inputFileName, String outputFileName, int N){
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.N = N;
    }

    public void readPlainFile(){
        try{
            Scanner inputStream = new Scanner(new FileInputStream(inputFileName));

            PrintWriter outputStream = new PrintWriter(new FileOutputStream(outputFileName));

            while(inputStream.hasNextLine()){
                String line = inputStream.nextLine();

                outputStream.println(encode(line));
            }
            inputStream.close();
            outputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("The is not found");
        }
    }

    public void readEncryptFile(){
        try{
            Scanner inputStream = new Scanner(new FileInputStream(inputFileName));

            PrintWriter outputStream = new PrintWriter(new FileOutputStream(outputFileName));

            while(inputStream.hasNextLine()){
                String line = inputStream.nextLine();

                outputStream.println(decode(line));
            }
            inputStream.close();
            outputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("The is not found");
        }
    }

    @Override
    public String encode(String plainText) {
        String line = plainText;
        String shuffled = "";
        for(int i=0; i<N; i++){
            shuffled = "";
            int mid = line.length() / 2;

            //if String = "1234";
            //then the first half will take (0,2) = "12", last half will take(2,4) = "34"
            //if String = "12345" , mid = (int)5/2 = 2
            //then the first half will take (0,2) = "12", the last half will take (2,5) = "345"

            String firstHalf  = line.substring(0, mid);
            String lastHalf = line.substring(mid, line.length());


            // abcde, the cipher text will become acbde
            // ab || cde
            // acbd
            //first character of first half + first character of last half + second character of first half + second character of last half......
            for(int j=0; j<firstHalf.length(); j++){
                shuffled = shuffled + firstHalf.charAt(j) + lastHalf.charAt(j);
            }

            //this is the special case if the String.length is an odd number like "12345"
            //after the for loop just now , the result will be "1324" but the 5 is still missing
            //so we have to add the String shuffled , "1324" with the last character of the last part
            //abcde

            if(firstHalf.length() < lastHalf.length()){
                shuffled = shuffled + lastHalf.charAt(lastHalf.length() - 1);
            }
        }
        return shuffled;
    }

    @Override
    public String decode(String cipherText) {
        String line = cipherText;
        String unshuffled = "";

        for(int i=0; i<N; i++){
            unshuffled = "";

            if(line.length() % 2 == 0){
                for(int j=0; j<line.length(); j=j+2){
                    //decoded : abcd -> encoded : acbd
                    //this is to get the first half part
                    // ab
                    // unshuffled = ab
                    unshuffled = unshuffled + line.charAt(j);
                }

                for(int j=1; j<line.length(); j=j+2){
                    unshuffled = unshuffled + line.charAt(j);
                }
            }
            else{
                //decoded : abcd || e -> encoded : acbd || e
                //this is to get the first half part
                for(int j=0; j<line.length() - 2; j=j+2){
                    unshuffled = unshuffled + line.charAt(j);
                }

                for(int j=1; j<line.length(); j=j+2){
                    unshuffled = unshuffled + line.charAt(j);
                }

                unshuffled = unshuffled + line.charAt(line.length() - 1);
            }
        }
        return unshuffled;
    }
}
