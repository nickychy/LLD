import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*Problem Statement - design a tic tac toe game
Requirements 
- Players - Right now 2,can be extended
- Board - n*n matrix
-Gameclass - to maintain game moves and Players
WinningStrategy - Any player can win if place all symbol row wise,column wise or in a diagonal
 in this we can use strategy as 3 are independent criteria for win,and in future some new criteria might come.
 DRAW conditions
 Winner decide
 */



enum Symbol {
    X, O
}

class TicTacToe {
    Board board;
    List<Player> players;
    List<WinningStrategy> winningStrategies;

    public TicTacToe(int row, int col) {
        board = new Board(row, col);
        intializePlayers();
        intializeStartegies();
    }

    public void intializePlayers() {
       Player player1 = new Player("Ashish", Symbol.X);
       Player player2 = new Player("Nicky", Symbol.O);
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
    }
    public void intializeStartegies(){
        winningStrategies =new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinStrategy());
        winningStrategies.add(new DiagonalWinStrategy());
    }

    public String startGame() {
       boolean isWinner = false;
        Scanner sc = new Scanner(System.in);
        Player Winner=null;
        System.out.println("Game Started");
        int currentPlayer = 0;
        while (!isWinner && !board.checkBoardFull()) {
            Player currPlayer = players.get(currentPlayer);
            // Take input from user and make the move
            board.printBoard();
            System.out.println("Player " + currPlayer.name + " turn :: Enter row and column");
            int row = sc.nextInt();
            int col = sc.nextInt();
            // check move
            if (board.isValidMove(row, col)) {
                board.makeMove(row, col, currPlayer.symbol);
                if (checkWinner(currPlayer.symbol)) {
                    System.out.println("Player " + currPlayer.name + " wins!!");
                    isWinner = true;
                    Winner=currPlayer;
                }
                else{
                currentPlayer = (currentPlayer + 1) % players.size();
                }
            } else {
                System.out.println("Invalid move!! Try again");
            }
        }
        if(!isWinner){
                System.out.println("Game ended in a draw!!");
                return "Draw";
        }
        return Winner.name;
    }
    public boolean checkWinner(Symbol s){
       boolean isWinner=false;
        for(WinningStrategy w: winningStrategies){
            if(w.checkWinner(board, s)){
                isWinner=true;
            }
        }
        return isWinner;
    }
}

class Board {
   private List<List<Character>> board;
    private int filledCells;
    private int rows,cols;

    public Board(int row, int col) {
        board = new ArrayList<>();
        this.rows=row;
        this.cols=col;
        for (int i = 0; i < row; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < col; j++) {
                board.get(i).add('-');      
            }
        }
    }

    public boolean isValidMove(int row, int col) {
        if(row< 0 || row>=board.size() || col<0 || col>=board.get(row).size() || board.get(row).get(col) != '-') return false;
        return true;
    }

    public void makeMove(int row, int col, Symbol symbol) {
        board.get(row).set(col,symbol.toString().charAt(0));
        filledCells++;
    }
    public void printBoard(){
        for(List<Character> l: board){
            for(Character c: l){
                System.out.print(c+"  |");
            }
            System.out.println();
        }
    }
    public boolean checkBoardFull(){
        if(filledCells == (board.size()*board.get(0).size())) return true;
        return false;
    }
    public int getCols(){return cols;}
    public int getRows() {return rows;}
    public Character getCell(int row,int col) { return board.get(row).get(col);}
}

class Player {
    String name;
    Symbol symbol;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }
}

interface WinningStrategy{
    boolean checkWinner(Board board,Symbol s);
}
class RowWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board,Symbol s){
      
        Character symbol=s.toString().charAt(0);
        for(int r=0;r<board.getRows();r++){
               boolean isWinner=true;
            for(int c=0;c<board.getCols();c++){
                if(board.getCell(r,c) != s.toString().charAt(0)){
                    isWinner=false;
                    break;
                }
            }
            if(isWinner) return true;
        }
        return false;
    }
}

class ColumnWinStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board,Symbol s){
        Character symbol=s.toString().charAt(0);
        for(int c=0;c<board.getCols();c++){
            Boolean isWinner=true;
            for(int r=0;r<board.getRows();r++){
                if(board.getCell(r,c)!=symbol){
                    isWinner=false;
                }
            }
            if(isWinner) return true;
        }
        return false;
    }
    
}

class DiagonalWinStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board b,Symbol s){
        Character symbol=s.toString().charAt(0);
        boolean main=true, anti=true;
        int size=b.getRows();
        for(int r=0;r<size;r++){
            if(b.getCell(r,r)!=symbol) main= false;
            if(b.getCell(r, size-r-1)!=symbol) anti=false;
        }
        return main || anti;
    }
}
class Main {
    public static void main(String[] args) {
        TicTacToe ticTacGame = new TicTacToe(3, 3);
        ticTacGame.startGame();
    }
}
