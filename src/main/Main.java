/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import operation.Simplexe;

/**
 *
 * @author miaro
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    //Setting datas for testing
        
    // the principal equation z considering x as the 3 variables
        double[] x = {6, 1};
        
    // The constraints in a 2D array [matrix]
        double[][] M = {
            { -1, 3, 1, 0, 0, 0, 6 },
            { 1, -3, 0, 0, 1, 0, 6 },
            { 1, 1, 0, -1, 0, 1, 1 }
        };
            
        // Call Simplexe object
        Simplexe operator = new Simplexe(x, M, "max");
        operator.simplexe();
    }
    
}
