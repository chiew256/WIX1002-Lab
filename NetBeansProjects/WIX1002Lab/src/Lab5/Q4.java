/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5;

/**
 *
 * @author chiew256
 */
public class Q4 {
    public static void main(String[] args) {
        //the input mattrix
        int [][] matrix= {
                {1,5,7},
                {3,6,9},
                {5,3,8}
        };

        //print the matrix
        System.out.println("3 by 3 Matrix");
        for (int i=0;i<matrix.length;i++){
            for(int z=0; z<matrix.length;z++)
                System.out.print(matrix[i][z] + " ");
            System.out.println("");
        }

        int [][] new_matrix = new int[matrix.length][matrix[0].length];
        //variables to indicate position of value to be inserted in new_matrix
        int new_row = 0;
        int new_column = 0;

        //iterate the old matrix by column
        for (int column = 0; column< matrix.length; column++){
            //example
            // old matrix                                   new matrix
            //first column first row   >>            third column first row
            //first column second row  >>            second column first row
            //first column third row   >>            first column first row
            //conclusion : column of old matrix become row of new matrix
            //new column = (length - (current_column -1 ) )
            //in another word = length(is the size of the new matrix) - current_column_index

            //iterating through the old matrix from last row of the column
            /*
            1 5 7    5 3 1
            3 6 9    0 0 0
            5 3 8    0 0 0
             */
            //column of old matrix becomes row of new matrix
            //last element of the column of the old matrix is the first element of the row of the new matrix
            for (int row = matrix.length - 1; row >= 0; row--){
                //new_row and new_column is starting from 0
                new_matrix[new_row][new_column] = matrix[row][column];
                new_column++;
            }
            new_row ++;
            new_column = 0 ;
        }

        //print it out
        System.out.println("After it rotates 90 degree clockwise");
        for (int i=0;i<new_matrix.length;i++){
            for(int z=0; z<new_matrix.length;z++)
                System.out.print(new_matrix[i][z] + " ");
            System.out.println("");
        }
    }
}
