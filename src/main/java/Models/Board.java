package Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    int size;
   public PlayingPiece[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new PlayingPiece[size][size];
    }
    public boolean addPiecce(int row,int col,PlayingPiece playingPiece){
        if(board[row][col]!=null){
            return false;
        }else{
            board[row][col]=playingPiece;
        }
        return  true;
    }
    public List<Pair> getFreeCells(){
        List<Pair> freeCells=new ArrayList<>();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]==null){
                    Pair p=new Pair(i,j);
                    freeCells.add(p);
                }
            }
        }
        return freeCells;
    }
    public  void printBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++) {
                if(board[i][j]!=null){
                    System.out.print(board[i][j].peiceType.name());
                }else{
                    System.out.print("  ");
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }

}
