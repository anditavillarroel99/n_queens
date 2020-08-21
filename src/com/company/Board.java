package com.company;

import java.util.Arrays;
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

    public Board(){
        board = new char[N][N];
    }


    public static Board startBoard() { //Estado inicial

        Board boardState = new Board();

        for (int i=0; i<boardState.board.length; i++) {
            for (int j=0; j<boardState.board.length; j++) {
                boardState.board[i][j] = QUEEN;
            }
        }

        return boardState;
    }

    public void printBoard() {

        for (int i=0; i<N; i++) {
            System.out.println( "+---".repeat(N) + "+" );

            for (int j=0; j<N; j++) {
                System.out.print( "+ " + board[i][j] + " " );
            }
            System.out.println( "+" );

        }
        System.out.println( "+---".repeat(N) + "+" );

    }

    public List<Board> succesors(){
        return List.of();
    }

}
