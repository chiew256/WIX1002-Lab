/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Lab10;

/**
 *
 * @author chiew256
 */
public interface MessageEncoder {
    public String encode(String plainText);

    public String decode(String cipherText);
}

