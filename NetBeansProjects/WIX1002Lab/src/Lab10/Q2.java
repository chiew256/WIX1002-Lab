/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab10;

/**
 *
 * @author chiew256
 */
import java.io.*;
import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        SubstitutionCipher encode = new SubstitutionCipher("product.txt", "SubstitutionEncrypt.txt", 3);
        SubstitutionCipher decode = new SubstitutionCipher("SubstitutionEncrypt.txt", "SubstitutionDecrypt.txt", 3);
        encode.readPlainFile();
        decode.readEncryptFile();
    }
}

class SubstitutionCipher implements MessageEncoder{
    private String inputFileName;
    private String outputFileName;
    private int shift;

    public SubstitutionCipher(String inputFileName, String outputFileName, int shift){
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.shift = shift;
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

    private final int ascii_code_max = 256;
    //shift means the number added to the characters
    //example if shift = 6 , input = "A" , output will be "G"
    @Override
    public String encode(String plainText) {
        String encoded = "";
        for(int i=0; i<plainText.length(); i++){
            encoded += (char) ( (plainText.charAt(i) + shift) % ascii_code_max );
        }
        return encoded;
    }

    @Override
    public String decode(String cipherText) {
        String decoded = "";
        for(int i=0; i<cipherText.length(); i++){
            int ascii_code = cipherText.charAt(i) - shift;
            if(ascii_code < 0){
                ascii_code  = ascii_code_max + ascii_code;
            }
            decoded += (char) (ascii_code);
        }
        return decoded;
    }
}
