package com.company;

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

        Board.startBoard().printBoard();

    }
}
