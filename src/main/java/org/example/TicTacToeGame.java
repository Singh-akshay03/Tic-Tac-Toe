package org.example;

import Models.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    Deque<Player> players;
    Board gameBoard;

    public TicTacToeGame() {
        initializeGame();
    }
    public void initializeGame(){
        players=new LinkedList<>();
        PlayingPieceX crossPiece=new PlayingPieceX();
        Player player1=new Player("Akshay",crossPiece);
        PlayingPieceO nougthPeice=new PlayingPieceO();
        Player player2=new Player("Salman",nougthPeice);
        players.add(player1);
        players.add(player2);
        gameBoard= new Board(3);
    }
    public String startgame(){
        boolean noWinner=true;
        while(noWinner){
            Player playerTurn=players.removeFirst();gameBoard.printBoard();
            List< Pair> freespaces=gameBoard.getFreeCells();
            if (freespaces.isEmpty()){
                noWinner=false;
                continue;
            }
            System.out.println("player "+playerTurn.getName()+" enter row,col");
            Scanner sc=new Scanner(System.in);
            String s=sc.nextLine();
            String[] values=s.split(",");
            int inputRow=Integer.valueOf(values[0]);
            int inputCol=Integer.valueOf(values[1]);


            boolean addPiece=gameBoard.addPiecce(inputRow,inputCol,playerTurn.getPlayingPiece());
            if(!addPiece){
                System.out.println("incorrect position choosen, please enter correct positions");
                players.addFirst(playerTurn);
                continue;
            }
            players.add(playerTurn);
            if(isthereWinner(inputRow,inputCol,playerTurn.getPlayingPiece())){
                return playerTurn.getName();
            }
        }
        return "tie";
    }
    public boolean isthereWinner(int row,int col,PlayingPiece playingPiece){
        PeiceType peiceType=playingPiece.peiceType;
        boolean rowMatch=true;
        boolean colMatch=true;
        boolean diagonalMatch=true;
        boolean antiDiagonalMatch=true;
        for(int i=0;i<gameBoard.getSize();i++){
            if(gameBoard.board[row][i]==null || gameBoard.board[row][i].peiceType!=peiceType){
                rowMatch=false;
                break;
            }
        }
        for(int i=0;i<gameBoard.getSize();i++){
            if(gameBoard.board[i][col]==null|| gameBoard.board[i][col].peiceType!=peiceType){
                colMatch=false;
            }
        }
        for(int i=0,j=0;j<gameBoard.getSize();i++,j++){
            if(gameBoard.board[i][j]==null||gameBoard.board[i][j].peiceType!=peiceType){
                diagonalMatch=false;
            }
        }
        for(int i=0,j=gameBoard.getSize()-1;i< gameBoard.getSize();i++,j--){
            if(gameBoard.board[i][j]==null||gameBoard.board[i][j].peiceType!=peiceType){
                antiDiagonalMatch=false;
            }
            }
        return  rowMatch||colMatch||diagonalMatch||antiDiagonalMatch;
    }
}



