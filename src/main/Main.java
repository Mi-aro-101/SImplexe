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
        double[] x = {40,30};
        
    // The constraints in a 2D array [matrix]
        double[][] M = {
            { 1, 1, 12 },
            { 2, 1, 16}
        };
            
        // Call Simplexe object
        Simplexe operator = new Simplexe(x, M);
        operator.simplexe();
    }
    
}
