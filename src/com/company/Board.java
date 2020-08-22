package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board {

    private static int N;

    public static final char EMPTY = ' ';
    public static final char QUEEN = 'Q';

    public static void setN(int n) {
        Board.N = n;
    }

    char[][] board;
    Board parent = null;
    int depth = 0;


    public Board(){
        board = new char[N][N];
    }


    public static Board startBoard() { //Estado inicial

        Board boardState = new Board();

        for (int i=0; i<boardState.board.length; i++) {
            for (int j=0; j<boardState.board.length; j++) {
                boardState.board[i][j] = EMPTY;
            }
        }

        boardState.depth = 0;
        return boardState;
    }

    public void printBoard() {

        for (int i = 0; i < N; i++) {
            System.out.println( "+---".repeat(N) + "+" );

            for (int j = 0; j < N; j++) {
                System.out.print( "+ " + board[i][j] + " " );
            }
            System.out.println( "+" );

        }
        System.out.println( "+---".repeat(N) + "+" );

    }
    private Board placeQueenOnTheBoard(int iQueen, int jQueen){

        if (this.board[iQueen][jQueen] != EMPTY ){
            throw new IllegalArgumentException(" La casilla ya esta ocupada.. ");
        }

        Board nextStep = new Board(); //tablero hijo, donde replico mi estado actual, (lo que tengo en mi estado)

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (i == iQueen && j == jQueen) {
                    nextStep.board[i][j] = QUEEN;
                } else{
                    nextStep.board[i][j] = this.board[i][j];
                }
            }
        }
        //Como estamos creando a los hijos posibles, se pone quien es el padre
        nextStep.parent = this;
        //el hijo tiene una profundidad +1
        nextStep.depth = this.depth + 1;

        return nextStep;
    }

    public boolean isGoalState(){
        //MEJORAS /--> considerar los especios donde la reina no esta atajando
        //1. hay 8 reinas y 2. ninguna se intersecta con otras
        int numberOfQueens = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if(this.board[i][j] == QUEEN){
                    numberOfQueens++;
                }
            }
        }

        if (numberOfQueens != N) {
            return false;
        }
        return !verifyPossibleCapture();
    }

    private boolean verifyPossibleCapture() {
        //Verificar intersecciones:

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(this.board[i][j] == QUEEN){
                    for (int iOtra = 0; iOtra < N; iOtra++) {
                        for (int jOtra = 0; jOtra < N; jOtra++) {
                            if( i == iOtra && j == jOtra ){
                                continue; // esta revisando la misma reina del ciclo anterior
                            }
                            if (this.board[iOtra][jOtra] == EMPTY){
                                continue; //Revisando un espacio vacio con la reina, lo ignoro
                            }
                            //Si llegue aqui, entonces estoy viendo a otra reina en el tablero que la encontrada en el primer par del ciclo for
                            if (i == iOtra || j == jOtra) {
                                return true;
                            }
                            //Caso Diagonal:
                            if ( iOtra - i == jOtra - j ) {
                                return true;
                            }

                            if (i+j == iOtra +jOtra){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public List<Board> succesors(){

        if(verifyPossibleCapture()){
            return Collections.emptyList();
        }
        if(depth >= N){
            return Collections.emptyList();
        }
        List<Board> children = new ArrayList<>();
        //genera todos los posibles estados hijos para crear el arbol

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) { //recorre todos los espacios del tablero, y si esta vacia
                if(this.board[i][j] == EMPTY){//considerar solo los espacios donde la reina no esta atacando... etc... implementar
                    children.add(placeQueenOnTheBoard(i, j));
                    //recorre todos los esspacios del tablero y si encuentra un espacio vacio entonces lo agrega a la lista de hijos
                }

            }
        }
        return children;
    }

}
