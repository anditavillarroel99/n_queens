package com.company;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print(" Ingrese el numero de Reinas : ");
        int numQueens = scanner.nextInt();

        if( numQueens < 4){
            System.out.println(" -> El numero minimo de Reinas es de 4 : ");
            Board.setN(4);
        } else {
            Board.setN(numQueens);
        }

        scanner.close();

//        Board.startBoard().printBoard();

        Board initialState =  Board.startBoard();

        LinkedList<Board> q = new LinkedList<>();
        q.add(initialState);

        int c =0;
        do{
            Board n = q.removeFirst(); //El primer elemento de q
            //q se queda como la lista menos la primera
            if(c >= 1000) {
                System.out.println(" Nuevo estado: ");
                n.printBoard();
                c = 0;
            }
            if ( n.isGoalState()) {
                System.out.println(" SOLUCION HALLADA !");
                n.printBoard();
                System.exit(0);
            }

            LinkedList<Board> s = new LinkedList<>(n.succesors());
            q.addAll(0,s);//agrega en la posicion 0 a todos los elementos de s

            c++;

        }while(!q.isEmpty());

    }
}
