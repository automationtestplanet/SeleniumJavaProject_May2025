package org.openmrs;

public class CallMethodWithCatchBlock {
    static int i=1;
    public static void printNumbers(){
        try{
            System.out.println(i);
            i++;
            if(i<=10){
                throw new ArithmeticException();
            }
        }catch(Exception e){
            printNumbers();
        }

    }

    public static void main(String[] args) {
        printNumbers();
    }
}
