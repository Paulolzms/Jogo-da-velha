package componentes;

import componentes.enums.StatusPlace;

import java.util.Scanner;

public class JogoDaVelha {

    private final StatusPlace[][] grid = new StatusPlace[3][3];
    private final Player player1 = new Player();
    private final Player player2 = new Player();

    int countPlays;
    int whoPlay;

    public JogoDaVelha() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = StatusPlace.E;
            }
        }
    }

    public void displayGrid(){
        System.out.println();
        for(int i = 0; i < 3 ; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkLines(){
        for(int i = 0 ; i < 3 ; i++)
            if (this.grid[i][0] == this.grid[i][1] && this.grid[i][0] == this.grid[i][2]){
                if(this.grid[i][0] != StatusPlace.E) {
                    if(whoPlay % 2 != 0) {
                        System.out.println("\nJogador 1 ganhou por linha");
                    }
                    else{
                        System.out.println("\nJogador 2 ganhou por linha");
                    }
                    return true;
                }
            }
        return false;
    }

    public boolean checkColumns(){
        for(int i = 0 ; i < 3 ; i++)
            if (this.grid[0][i] == this.grid[1][i] && this.grid[0][i] == this.grid[2][i]){
                if(this.grid[0][i] != StatusPlace.E) {
                    if(whoPlay % 2 != 0) {
                        System.out.println("\nJogador 1 ganhou por coluna");
                    }
                    else{
                        System.out.println("\nJogador 2 ganhou por coluna");
                    }
                    return true;
                }
            }
        return false;
    }

    public boolean checkDiagonalP(){
        if(this.grid[0][0] != StatusPlace.E && this.grid[0][0] == this.grid[1][1] && this.grid[0][0] == this.grid[2][2]){
            if(whoPlay % 2 != 0) {
                System.out.println("\nJogador 1 ganhou por diagonal principal");
            }
            else{
                System.out.println("\nJogador 2 ganhou por diagonal principal");
            }
            return true;
        }
        return false;
    }

    public boolean checkDiagonalS() {
        if (this.grid[0][2] != StatusPlace.E && this.grid[0][2] == this.grid[1][1] && this.grid[0][2] == this.grid[2][0]){
            if(whoPlay % 2 != 0) {
                System.out.println("\nJogador 1 ganhou por diagonal secundária");
            }
            else{
                System.out.println("\nJogador 2 ganhou por diagonal secundária");
            }
            return true;
        }
        return false;
    }

    public boolean checkEmpty(int l, int c){
        return this.grid[l][c] == StatusPlace.E;
    }

    public void setPlace (int l, int c, Player player){
        if(l >= 0 && l < 3 && c >= 0 && c < 3) {
            if (checkEmpty(l, c)) {
                this.grid[l][c] = player.getSymbol();
                countPlays++;
                whoPlay++;
            }
        }
        else System.out.println("\nPosição inválida!!!!");
    }

    public void setSymbol(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nO jogador 1 joga com X ou O ? ");
        char symbol = sc.nextLine().charAt(0);

        switch (symbol){
            case 'X':
                this.player1.setSymbol(StatusPlace.X);
                this.player2.setSymbol(StatusPlace.O);
                break;
            case 'O':
                this.player1.setSymbol(StatusPlace.O);
                this.player2.setSymbol(StatusPlace.X);
                break;
            default:
                System.out.println("\nSímbolo inválido");
        }

    }

    public void play() {

        Scanner sc = new Scanner(System.in);
        setSymbol();
        countPlays = 0;
        whoPlay = 0;

        do{
            System.out.println(" \nDigite a linha: ");
            int line = sc.nextInt();
            System.out.println("Digite a coluna: ");
            int column = sc.nextInt();

            if(whoPlay % 2 == 0) {
                setPlace(line, column, player1);
            }else{
                setPlace(line, column, player2);
            }

            sc.nextLine();
            displayGrid();

        }while(!win() && !tie());
    }

    public boolean win() {
        return checkLines() || checkColumns() || checkDiagonalP() || checkDiagonalS();
    }

    public boolean tie(){
        if(countPlays == 9){
            System.out.println("\nDeu velha");
            return true;
        }
        return false;
    }

}
