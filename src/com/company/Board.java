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


    public static Board startBoard() {

        Board boardState = new Board(); //Estado inicial

        for (int i = 0; i < boardState.board.length; i++) {
            for (int j = 0; j< boardState.board.length; j++) {
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
    private Board placeQueenOnTheBoard(int xQueen, int yQueen){

        if (this.board[xQueen][yQueen] != EMPTY ) {
            throw new IllegalArgumentException(" La casilla ya esta ocupada! ");
        }

        Board nextStep = new Board(); // tablero "hijo"

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (i == xQueen && j == yQueen) {
                    nextStep.board[i][j] = QUEEN;
                } else{
                    nextStep.board[i][j] = this.board[i][j];
                }

            }
        }

        nextStep.parent = this;
        nextStep.depth = this.depth + 1;

        return nextStep;
    }

    public boolean isGoalState(){
        //1. hay N reinas

        int numberOfQueens = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (this.board[i][j] == QUEEN) {
                    numberOfQueens++;
                }

            }
        }

        if (numberOfQueens != N) {
            return false;
        }

        return  !possibleCapture() ;  //2. ninguna se intersecta con otra

    }

    private boolean possibleCapture() {
        //Intersecciones:

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if ( this.board[i][j] == QUEEN ) {

                    for (int x = 0; x < N; x++) {
                        for (int y = 0; y < N; y++) {

                            //[x][y] nueva reina
                            if ( (i != x || j != y) && (this.board[x][y] == QUEEN) ) { // Si la posicion [x][y] no es la misma a la reina anterior y no es vacio

                                if ( (i == x || j == y ) || ( (x - i == y - j) || (i + j == x + y) ))
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

        if ( possibleCapture() ) {
            return Collections.emptyList();
        }

        if ( depth >= N ) {
            return Collections.emptyList();
        }

        List<Board> children = new ArrayList<>();
        //genera todos los posibles estados hijos para crear el arbol

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) { //recorre todos los espacios del tablero, y si esta vacia

                if(this.board[i][j] == EMPTY){//considerar solo los espacios donde la reina no esta atacando... etc... implementar
                    children.add( placeQueenOnTheBoard(i, j) );
                }

            }
        }
        return children;
    }

}
