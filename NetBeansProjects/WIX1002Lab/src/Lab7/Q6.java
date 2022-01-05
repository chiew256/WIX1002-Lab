import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q6 {
    public static void main(String[] args) {
       try{
           System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "ProductID", "ProductName", "Quantity", "PricePerUnit", "Total");
           Scanner orderInputStream = new Scanner(new FileInputStream("order.txt"));
           Scanner countProductStream = new Scanner(new FileInputStream("product.txt"));
           
           int numOfProduct = 0;
           while(countProductStream.hasNextLine()){
               countProductStream.nextLine();
               numOfProduct++;
           }
           countProductStream.close();
           
           Scanner productInputStream = new Scanner(new FileInputStream("product.txt"));
           String [][] product = new String[numOfProduct][];
           for(int i=0; i<numOfProduct; i++){
               product[i] = productInputStream.nextLine().split(",");
           }
           
           while(orderInputStream.hasNextLine()){
               String [] order = orderInputStream.nextLine().split(",");
               for(int i=0; i<product.length; i++){
                   if(order[1].equalsIgnoreCase(product[i][0])){
                       double price = Integer.parseInt(order[2]) * Double.parseDouble(product[i][2]);
                       System.out.printf("%-20s%-20s%-20s%-20s%-20.2f\n", product[i][0], product[i][1], order[2], product[i][2], price);
                   }
               }
           }
           productInputStream.close();
           orderInputStream.close();
       } catch(FileNotFoundException e){
            System.out.println("File is not found");
        }
    }
}
