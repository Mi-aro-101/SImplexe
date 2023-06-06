/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matrix;

/**
 *
 * @author miaro
 */
public class Matrix {
    double[][] A;
    
    /**
     * Function that tries to find the max in an array and return it's column index
     * @param array the last line of the matrix
     */
    public int max(double[] array){
        int max = 0;
        for(int i = 1 ; i < array.length-2 ; i++){
            if(array[max] < array[i]){
                max = i;
            }
        }
        
        return max;
    }
    
    /**
     * Function that will find the minimum division of the second member and pivot column
     * @param maxI is the column where to watch
     * @return index of the line where min is located
     */
    public int minDivision(int maxI){
        int minI = 0;
                
        for (int i = 0 ; i < this.getA().length-2 ; i++){
            int secMemberI = this.getA()[i].length-1;
            double temporary = Math.abs(this.getA()[i][secMemberI]/this.getA()[i][maxI]);
            double temporary1 = Math.abs(this.getA()[i+1][secMemberI]/this.getA()[i+1][maxI]);
            if(temporary > temporary1) minI = i+1;
        }
        
        return minI;
    }
    
    /**
     * Depending on simplexe method this function tries to find the pivot [Gauss]
     * @return  at the same time the pivot line and the pivot column
     */
    public int[] pivot(){
        //proper column
        int last = this.getA().length-1;
        int maxI = this.max(this.getA()[last]);
        // proper line
        int i = this.minDivision(maxI);
        
        int minmax[] = new int[2];
        minmax[0] = i;minmax[1]=maxI;
        
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
     * Perform Gauss Jordan pivot to set the pivot column to 0
     */
    public void Gauss(){
        // Getting pivot 
        int[] pivot = this.pivot();
        int pLine = pivot[0] , pColumn = pivot[1];
        // Divide pivot entire line to pivot element
        this.dividePivot(pLine, pColumn);
        
        // line iteration
        for(int i = 0 ; i < this.getA().length ; i++){
            // Do not perform the Gauss on the pivot line
            if(i == pLine) continue;
            // Perform calcul for all the other line
            double a = this.getA()[i][pColumn];
            double b = this.getA()[pLine][pColumn];
            // Column iteration
            for(int j = 0 ; j < this.getA()[i].length ; j++){
                this.getA()[i][j] = (b*this.getA()[i][j]) - (a*this.getA()[pLine][j]);
            }
        }
    }
    
    /** 
     * Check if the iteration must end
     * @return boolean if it shall stop or not
     */
    public boolean ifendGauss(){
        boolean value = true;
        // index of the last line of the matrix -> principal equation
        int zLine = this.getA().length-1;
        // Column iteration
        for(int j = 0 ; j < this.getA()[zLine].length ; j++){
            if(this.getA()[zLine][j] > 0) {
                value = false;
                break;
            }
        }
        
        return value;
    }
    
    // Display entire matrix
    public void displayA(){
        for (double[] a : this.getA()) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[j] + "    ");
            }
            System.out.println("\n");
        }
    }
    
    // Class Constructor by giving the Matrix dimension
    public Matrix(int line, int column){
        this.setA(new double[line][column]);
    }
    // Simple Constructor
    public Matrix(){}

    // Getter
    public double[][] getA() {
        return A;
    }
    
    // Setter
    public void setA(double[][] A) {
        this.A = A;
    }
    
    
}
