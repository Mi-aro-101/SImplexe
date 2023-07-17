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
        double[] x = { 800, 700, 300 };
        
    // The constraints in a 2D array [matrix]
        double[][] M = {
            { 2.0, 1.0, 0.0, -1.0, 0.0, 1.0, 0.0, 4.0 },
            { 1.0, 2.0, 1.0, 0.0, -1.0, 0.0, 1.0, 5.0 }
        };
        
        String[] variables = { "x1", "x2", "x3","s1", "s2", "s3", "a1", "a2" };
        String[] inbase = { "a1", "a2", "Z" };
            
        // Call Simplexe object
        Simplexe operator = new Simplexe(x, M, "max", variables, inbase);
        operator.simplexe();
    }
    
}
