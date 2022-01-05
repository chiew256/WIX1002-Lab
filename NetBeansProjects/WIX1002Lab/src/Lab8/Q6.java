/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab8;

/**
 *
 * @author chiew256
 */
public class Q6 {
    public static void main(String[] args) {
        BurgerStall stall1 = new BurgerStall("123");
        BurgerStall stall2 = new BurgerStall("456");
        stall1.sold(100);
        stall2.sold(200);
        stall1.sold(300);

        stall1.display();
        stall2.display();

        BurgerStall.displaySum();
    }
}

class BurgerStall{
    private String ID;
    private int numOfBurger;
    private static int sumOfBurger = 0;

    public BurgerStall(String ID){
        this.ID = ID;
        this.numOfBurger = 0;
    }

    public void sold(int num){
        numOfBurger += num;
        sumOfBurger += num;
    }

    public int getNumOfBurger() {
        return numOfBurger;
    }

    public static int getSumOfBurger() {
        return sumOfBurger;
    }

    // this stall
    public void display(){
        System.out.println("The number of burger sold by stall " + ID + " is " + numOfBurger);
    }

    // all stalls
    public static void displaySum(){
        System.out.println("The total number of burgers sold by all stall(s) is "  + sumOfBurger);
    }
}

