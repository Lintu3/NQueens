
package nqueens;

/**
 *
 * @author Opinto LL
 */
import java.util.Scanner;

//Kuningattaret asetetaan vasemmasta yläkulmasta alkaen sarake kerrallaan
class NQueens {
    //Tämä pitää yllä ratkaisujen määrää.
     int solutioncount = 0;
    
    //Printtaa pöydän.
    void printBoard (char[][] board) {

        for (char[] b : board) {
            //printtaa yhden paikan kerrallaan rivi kerrallaan.
            for (int c = 0; c < b.length; c++){
            System.out.print("[" + b[c]+ "]");
            }
            System.out.println();
        }
    }
    //Tämä metodi tekee suurimman työn ja etsii ratkaisut
     void findSolution (char[][] board, int c) {
        
        // Jos kaikissa sarakkeissa on kuningatar on ratkaisu oikea.
        if (c == board.length) {
            solutioncount++;
            System.out.println("Solution board number: " + solutioncount);
            printBoard(board);
            System.out.println();
            
        } 
        else 
        {
            //Jos haluaa nähdä vaiheet, ota kommentit pois 
            /*
            System.out.println("Placing queens:");
            printBoard(board);
            System.out.println();
            */
           
            //Yritetään asettaa kuningattaria sarakkeen joka riville ja tarkastetaan onko pöytä ok.
            for (int r = 0 ; r < board.length; r++) {
                
                //Aseta kuningatar.  '_' -> 'Q'
                board[r][c] = 'Q';
                
                //Katsotaan onko paikka hyvä, jos on niin siirrytään seuraavaan sarakkeeseen.
                if (isQueenOk(board, r, c) == true) findSolution(board, c + 1);
                
                //paikka ei ollut hyvä palautetaan '_'
                board[r][c] = '_'; 
                
                
            }
        }   
    }
    
    //Tämä tarkistaa paikan onko se hyvä jos ei palauttaa false jos on niin true.
    boolean isQueenOk (char[][] board, int row, int col) {

       // Katsotaan onko aikaisemman sarakkeen samalla rivillä kuningatar
       for (int c = 0; c < col; c++) {
          if (board[row][c] == 'Q') return false;
       }
       
       // Katsotaan onko ylä vasemmalla kuningatar
       for (int r = row-1, c = col-1; r >= 0 && c >= 0; r--, c--) {
          if (board[r][c] == 'Q') return false;
       }
       
       // Ja vielä katsotaan onko ala vasemmalla kuningatar
       for (int r = row+1, c = col-1; c >= 0 && r < board.length; r++, c--) {
          if (board[r][c] == 'Q') return false;  
       }
       
       // Ei löytynyt eli paikka on hyvä
       return true;
    }
    
    public static void main(String args[]) {

       //Syötetty numero kertoo laudan koon ja kuningattarien määrän.
       Scanner input = new Scanner(System.in);  
       System.out.print("Enter number of queens : ");
       int N = input.nextInt();  
       
       //Tehdään pöytä edellä saadulla numerolla
       char board[][] = new char[N][N];
       
       //Täytetään pöytä '_' merkillä
       for (int r=0; r<N; r++) {
           for (int c=0; c<N; c++) {
               board[r][c] = '_';
           }
       }
       NQueens Q = new NQueens();
       // Aloitetaan ensimmäisestä sarakkeesta 
       Q.findSolution(board, 0);
       
       
       //Nämä helpottavat tarkistusta lopuksi
       System.out.println("Didn't find anymore solutions!");
       System.out.println("Solutions found : " + Q.solutioncount);    
       
       //Tähän voisi lisätä avain arvopari listan jokaiselle ratkaisujen määrälle kuten
       //{1:1,2:0,3:0,4:2....} jolloin voitaisiin lopettaa suoritus kun kaikki ratkaisut on löydetty
       //saattaisi nopeuttaa suoritusta hieman koska tällä hetkellä se jää etsimään vaikka ei ole mitään löydettävää.
       //15 kuningattaren ratkaisussakin kestää jo aika kauan vaikka ottaisi printit pois.
    }
}