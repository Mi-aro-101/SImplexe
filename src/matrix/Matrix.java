/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matrix;

import java.util.ArrayList;

/**
 *
 * @author miaro
 */
public class Matrix {
    double[][] A;
    String[] inbase;
    
    /**
     * Function that tries to find the max in an array and return it's column index
     * @param array the last line of the matrix
     */
    public int max(double[] array){
        int max = 0;
        for(int i = 0 ; i < array.length-1 ; i++){
            if(array[max] < array[i]){
                max = i;
            }
        }
        
        return max;
    }
    
    public int min(double[] array){
        int min = 0;
        for(int i = 1 ; i < array.length-2 ; i++){
            if(array[min] > array[i]){
                min = i;
            }
        }
        
        return min;
    }
    
    /**
     * find the first positive result of the division between the secMember to the pColumn 
     * @param maxI is the column of the pivot
     */
    public int firstPositive(int maxI){
        int res = 0;
        for(int i = 0 ; i < this.getA().length ; i++){
            int secMemberI = this.getA()[i].length-1; 
            double temporary1 = this.getA()[i][secMemberI]/this.getA()[i][maxI];
            if(temporary1 > 0){
                res = i; break;
            }
        }
        
        return res;
    }
    
    /**
     * Function that will find the minimum division of the second member and pivot column
     * @param maxI is the column where to watch
     * @return index of the line where min is located
     */
    public int minDivision(int maxI){
        // minI is set to be the first positive number result of the division between secmember and pColumn
        int minI = this.firstPositive(maxI);
                
        for (int i = minI+1 ; i < this.getA().length-1 ; i++){
            int secMemberI = this.getA()[i].length-1;
            double temporary = this.getA()[minI][secMemberI]/this.getA()[minI][maxI];
            double temporary1 = this.getA()[i][secMemberI]/this.getA()[i][maxI];
            if(temporary > temporary1 && temporary > 0 && temporary1 > 0) {
                minI = i;
            }   
        }
        
        return minI;
    }
    
    /**
     * Depending on simplexe method this function tries to find the pivot [Gauss]
     * @param maxOrmin
     * @return  at the same time the pivot line and the pivot column
     */
    public int[] pivot(String maxOrmin){
        //proper column
        int last = this.getA().length-1;
        int pivotCol = this.max(this.getA()[last]);
        
        if(maxOrmin.equals("min")){
            pivotCol = this.min(this.getA()[last]);
        }
        // proper line
        int i = this.minDivision(pivotCol);
        
        int minmax[] = new int[2];
        minmax[0] = i;minmax[1]=pivotCol;
        
        return minmax;
    }
    
    /** 
     * Divide entire pivot line to the pivot number
     * @param pLine is the pivot line number
     * @param pColumn is the picot column number
     */
    public void dividePivot(int pLine, int pColumn){
        double pivotelt = this.getA()[pLine][pColumn];
        
        // Perform the division column per column
        for(int i = 0 ; i < this.getA()[pLine].length ; i++){
            this.getA()[pLine][i] /=pivotelt;
        }
    }
    
    /**
     * Check if the pivot column is a variable in Z
     * @param i
     * @param variables
     * @return 
     */
    public boolean isInBase(int i, String[] variables){
        boolean val = false;
        if(variables[i].contains("x")){
            val = true;
        }
        
        return val;
    }
    
    /** 
     * Perform Gauss Jordan pivot to set the pivot column to 0
     * @param minorMax define if the resolution shall be in minimisation method or maximisation
     */
    public void Gauss(String minorMax, String[] inbase, String[] variables, ArrayList<Integer> pivots){
        // Getting pivot depending on how It shall be resolve, max or min
        int[] pivot = this.pivot(minorMax);
        int pLine = pivot[0] , pColumn = pivot[1];
        if(minorMax.equals("min")){
            pivots.add(pivot[0]);pivots.add(pivot[1]);
        }
        System.out.println("pivot line : "+pLine);
        System.out.println("pivot column : "+pColumn);
        // Divide pivot entire line to pivot element
        this.dividePivot(pLine, pColumn);
        
        inbase[pLine] = variables[pColumn];
        // line iteration
        for(int i = 0 ; i < this.getA().length ; i++){
            // Do not perform the Gauss on the pivot line
            if(i == pLine) continue;
            // Perform calcul for all the other line
            double a = this.getA()[i][pColumn];
            double b = this.getA()[pLine][pColumn];
            // Change the variables in base depending on pivot
            // Column iteration
            for(int j = 0 ; j < this.getA()[i].length ; j++){
                this.getA()[i][j] = (b*this.getA()[i][j]) - (a*this.getA()[pLine][j]);
            }
        }
    }
    
    public void GausswPivot(ArrayList<Integer> pivot){
        int pLine = pivot.get(0) , pColumn = pivot.get(1);
        // Divide pivot entire line to pivot element
        this.dividePivot(pLine, pColumn);
        // Precise that it shall only touch the last lin-> in order to get the proper value of Z having a variable in base to 0
        int l = this.getA().length-1;
        // Perform calcul for all the other line
        double a = this.getA()[l][pColumn];
//        double b = this.getA()[pLine][pColumn];
        // Column iteration
        for(int j = 0 ; j < this.getA()[l].length ; j++){
            this.getA()[l][j] = (this.getA()[l][j]) - (a*this.getA()[pLine][j]);
        }
    }
    
    /** 
     * Check if the iteration must end
     * @param minOrMax chooses wether if it is a maximisation or minimisation
     * @return boolean if it shall stop or not
     */
    public boolean ifendGauss(String minOrMax){
        boolean value = true;
        // index of the last line of the matrix -> principal equation
        int zLine = this.getA().length-1;
        // If the resolution shall be a maximisation
        if(minOrMax.equals("max")){
        // Column iteration
            for(int j = 0 ; j < this.getA()[zLine].length ; j++){
                if(this.getA()[zLine][j] > 0) {
                    value = false;
                    break;
                }
            }
        }
        
        else if(minOrMax.equals("min")){
            for(int j = 0 ; j < this.getA()[zLine].length ; j++){
                if(this.getA()[zLine][j] < 0) {
                    value = false;
                    break;
                }
            }
        }
        
        return value;
    }
    
    /**
     * Display entire matrix
     */
    public void displayA(){
        for (int i = 0 ; i< this.getA().length ; i++) {
            System.out.print(this.getInbase()[i]+"    ");
            for (int j = 0; j < this.getA()[i].length; j++) {
                System.out.print(this.getA()[i][j] + "    ");
            }
            System.out.println("\n");
        }
    }
    
    /**
     * Check if an element is in an array
     * @param k is the element that we want to check
     * @param array is the array that will be checked if it contains k
     * @return true if k is in array otherwise false
     */
    public static boolean kinArray(int k, int[] array){
        boolean result =false;
        for(int a : array){
            if(k == a) result=true;
        }
        
        return result;
    }
    
    // Class Constructor by giving the Matrix dimension
    public Matrix(double[][] A, String[] inbase){
        this.setA(A);
        this.setInbase(inbase);
    }
    // Simple Constructor
    public Matrix(){}

    // Getter
    public double[][] getA() {
        return A;
    }

    public String[] getInbase() {
        return inbase;
    }
    
    // Setter
    public void setA(double[][] A) {
        this.A = A;
    }

    public void setInbase(String[] inbase) {
        this.inbase = inbase;
    }
}
