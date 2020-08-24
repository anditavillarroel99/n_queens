package com.company;

import java.util.Arrays;
import java.util.Iterator;
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

//        dfs(initialState);
        LinkedList<Board> q = new LinkedList<>();
        q.add(initialState);

        System.out.println("Buscando ...");
        Board solution =  initialState.searchForGoalState();

        LinkedList<Board> step_list = new LinkedList<>() ;
        Board step = solution;

        while (step != null ){
            step_list.add(0,step);
            step = step.parent;
        }

//        System.out.println(" -> Profundidad :" + solution.depth);

        System.out.println(" -> Secuencia de pasos a seguir:" );

        for( Board solution_step : step_list ){
            System.out.println("   Nuevo Estado : " );
            solution_step.printBoard();
            System.out.println();
        }
        System.out.println(" -> SOLUCION HALLADA:" );
        solution.printBoard();
    }

    public static void dfs(Board initialState){

        LinkedList<Board> q = new LinkedList<>();
        q.add(initialState);

        int c = 0;
        int cont =0 ;

        do{

            Board n = q.removeFirst();
                if ( c >= 1000000 ) {
                System.out.println(" Nuevo estado: " + cont);
                n.printBoard();
                System.out.println(" ------------------------------------- ");
                c = 0;
            }

            if ( n.isGoalState() ) {
                System.out.println(" SOLUCION HALLADA !");
                n.printBoard();
                System.exit(0);
            }

            LinkedList<Board> s = new LinkedList<>( n.succesors() );

            q.addAll(0,s);

            c++;
            cont++;

        } while( !q.isEmpty() );
    }
}
