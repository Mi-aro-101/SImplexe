/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation;

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
    
    /**
     * try to resolve the simplexe
     * @return 
     */
    public void simplexe(){
        this.setUpSimplexe();
        // Display the matrix before change done
        System.out.println("Matrix before change");
        this.getM().displayA();
        int iteration = 1;
        
        // Perform Guass pivot Gauss Jordan operation until the value of z do not contain positive value
        while(this.getM().ifendGauss() == false){
            this.getM().Gauss();
            System.out.println("Iteration number : "+iteration);
            this.getM().displayA();
            iteration++;
        }
    }
    
    /**
     * Reunite all the method to set up the final matrix and resolve it with simplexe eventually
     * @return 
     */
    public void setUpSimplexe(){
        this.initSetupMatrix();
        this.secondMemberadd();
    }
    
    /**
     * Continue setting up the great matrix in order to use it with simplexe method
     * 1 - add the Ei variable set up first to 1
     * 2 - add the second member of the constraints
     * @return 
     */
    public void secondMemberadd(){
    // Set all the Ei variable to 1
        // Colum increment variables
        int j = this.getSc()[0].length-1; //-2 because the last element is the second member
        for (int i = 0 ; i < this.getM().getA().length-1 ; i++) {
            this.getM().getA()[i][j] = 1;
            j++;
        }
        
    // Adding the second member of constraints at the last column
        // Last column of Constraints = Second member
        j = this.getSc()[0].length-1;
        // Last column of the matrix to set to j
        int l = this.getM().getA()[0].length-1;
        for ( int k = 0 ; k < this.getSc().length ; k++ ){
            this.getM().getA()[k][l] = this.getSc()[k][j];
        }
    }
    
    /**
     * Intialize Setting up great matrix in order to use simplexe method
     * 1 - create new matrix object with proper size
     * 2 - Copy all the constraints data to the the matrix
     * 3 - add at the end of line the principal equation
     * @return 
     */ 
    public void initSetupMatrix(){
    // Line number of the marix is define by the number of line of the costraints + the principal equation
        int linenbr = this.getSc().length+1;
        
    // Column number of the matrix is define by the number of column of principal equation + linenbr of contraints + contraints value after operator
        int colnbr = this.getZ().length+this.getSc().length+1;
        
        this.setM(new Matrix(linenbr, colnbr));
    // Initialize a line variable to loop in order to trannfer the Constraints element to my new Matrix simplexe
        int i;
    // Copy contraints array to my Simplexe matrix array
        for(i = 0 ; i < this.getSc().length ; i++){
            System.arraycopy(this.getSc()[i], 0, this.getM().getA()[i], 0, this.getSc()[i].length-1);
        }
    
    // Copy the principal equation array at the last line of the matrix array
        System.arraycopy(this.getZ(), 0, this.getM().getA()[i], 0, this.getZ().length);
        
    }
    
    // Constructor receiving the z principal equation and the constraints
    public Simplexe(double[] z, double[][] sc){
        this.setZ(z);
        this.setSc(sc);
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
    
}
