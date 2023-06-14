/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation;

import java.util.Arrays;
import matrix.Matrix;

/**
 *
 * @author miaro
 */
public class Simplexe {
    // Principal equation
    double[] z;
    // Sous contraintes
    double[][] sc;
    // Matrix composed by z and sc
    Matrix M;
    // Define if the Simplexe problem is a maximiation or minimisation
    String resolutionMethod;
    
    /**
     * Preform phase I of of resolution using Simplexe 2 phases -> minimisation
     * @param lastLine is the equation that shall represent the mother equation temporarily for this minimisation
     */
    public void phaseI(double[] lastLine) {
        this.setUpMatrix(this.getSc(), lastLine);
        // Display the matrix before begin phase 1
        System.out.println("Matrix brefore phase 1");
        this.getM().displayA();
        
        // Preform Minimisation -> perform Gauss for phase1 minimisation
        this.minimize();
    }
    
    /**
     * Perform phase II of resolution using Simplexe 2 phases -> this.getResolutionMethod
     * @param lastLine is the last line of the matrix, in this case the last line is the :
     *  mother equation z already calculed by you and off shore of the artificial or in base variables x
     */
    public void phaseII(double[] lastLine){
        // First you must know which column you want to remove -> column containing the artificial variables
        int[] indiceRmCol = {4, 5};
        this.removeColumn(indiceRmCol);
        
        // Setup the new matrix with the proper new values
        this.setUpMatrix(getM().getA(), lastLine);
        // Display the matrix after droping the articial column
        System.out.println("No more artificial column");
        this.getM().displayA();
        // Begin resolution of the matrix -> ending phase 2
        System.out.println("Begin phase II resolution");
        this.maximize();
        
    }
    
    /**
     * remove the column that have the same indice as your parameter
     * @param indiceRm is the list of the column I want to remove
     */
    public void removeColumn(int[] indiceRm){
        // Setting up first the size of the mother matrix as we will drop some of it's column
        int lineSize = this.getM().getA().length-1; //-1 because It is only the matrix without the last line 
        int columneSize = this.getM().getA()[0].length - indiceRm.length; 
        System.out.println(lineSize);
        // Define the new mother matrix without artificial variables
        double[][] newMatrix = new double[lineSize][columneSize];
        for(int i = 0 ; i < this.getM().getA().length-1 ; i++){
            // variables responsible of the column increment of the new Matrix
            int k = 0;
            for(int j = 0 ; j < this.getM().getA()[i].length ; j++){
                // Check if the column indice is contained or not in  the "shall remove column" if so do not append it
                if(!Matrix.kinArray(j, indiceRm)){
                    newMatrix[i][k] = this.getM().getA()[i][j];
                    k++;
                }
            }
        }
        
        this.setM(new Matrix(newMatrix));
    }
    
    /**
     * try to resolve the simplexe
     */
    public void simplexe(){
        // Setup the mother matrix by adding at the last line the Z temporary equation that you will calculate
        /** CALCULATE IN HAND FIRST THE EQUATION YOU SHALL PUT ON THE LAST LINE OF THE MOTHER MATRIX :
                *   use the line that can make artificial variables to 0 depending on the artificial variable sum equation
                **  sum the line to the artificial variables summation == lastLine 
        */
        double[] lastLineI = { -2, 2, 0, 1, 0, 0, -7 };
        
        this.phaseI(lastLineI);  
        
        /** CALCULATE IN HAND FIRST THE EQUATION YOU SHALL PUT ON THE LAST LINE OF THE MOTHER MATRIX
         * calculated depending on the only variables in the base :
         *  use the line of the only equation that have variable in the base as base
         *  sum it to the mother equation Z = lastLine
         */
        double[] lastLineII = { 0, -19, 0, 0, 36 };
        // Phase2 does : get rid of the artificial variable column first then by giving him the last line, he will do the maximisation or minimsation
        this.phaseII(lastLineII);
    }
    
    /**
     * This function try to maximize using Simplexe method
     */
    public void maximize(){
        int iteration = 1;
        while(this.getM().ifendGauss("max") == false){
            System.out.println("Iteration : "+iteration);
            this.getM().Gauss("max");
            this.getM().displayA();
            iteration++;
        }
    }
    
    /**
     * Minimisation in phase
     * Stop condition is when all the values are+ and the second memebre is 0
     */
    public void minimize(){
        int lastline = this.getM().getA().length-1;
        int lastColumn = this.getM().getA()[0].length-1;
        int iteration = 1;
        while(this.getM().ifendGauss("min") == false && this.getM().getA()[lastline][lastColumn] != 0){
            System.out.println("Iteration : "+iteration);
            this.getM().Gauss("min");
            this.getM().displayA();
            iteration++;
        }
    }    
    /**
     * Setup the big mother matrix for resolution
     * @param aboveLine all the lines above the last line
     * @param lastLine represent the last line of the matrix mother -> mother equation
     */
    public void setUpMatrix(double[][] aboveLine, double[] lastLine){
        double[][] A = new double[aboveLine.length+1][aboveLine[0].length];
        // Copy the Constraints matrix to the mother matrix
        System.arraycopy(aboveLine, 0, A, 0, aboveLine.length);
        // Copy the mother equation to the last line of the matrix 
        System.arraycopy(lastLine, 0, A[A.length-1], 0, lastLine.length);
        // Set the mother Matrix to a new Object Matrix having Constraints and Z in it
        this.setM(new Matrix(A));
    }
    
    // Constructor receiving the z principal equation and the constraints
    public Simplexe(double[] z, double[][] sc, String resolutionMethod){
        this.setZ(z);
        this.setSc(sc);
        this.setResolutionMethod(resolutionMethod);
    }
    // Simple constructor
    public Simplexe(){}
    
    // Getters
    public double[] getZ() {
        return z;
    }

    public double[][] getSc() {
        return sc;
    }

    public Matrix getM() {
        return M;
    }

    public String getResolutionMethod() {
        return resolutionMethod;
    }
    
    // Setters
    public void setZ(double[] z) {
        this.z = z;
    }

    public void setSc(double[][] sc) {
        this.sc = sc;
    }
    
    public void setM(Matrix M) {
        this.M = M;
    }

    public void setResolutionMethod(String resolutionMethod) {
        this.resolutionMethod = resolutionMethod;
    }

    
}
