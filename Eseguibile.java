import java.util.Scanner;

class Eseguibile {
    
    public static void main(String args[]) { 
        Scanner in = new Scanner(System.in);//inizializzo un oggetto scanner per leggere input
        boolean errore=true;
        String linea;
        int n=0;
        int b=0;
        Partita game= new Partita(n, b);
        Token stato=Token.gioca;
        while(errore==true){ 
            System.out.println("menu:");
            System.out.println("gioca         esci");
            linea = in.nextLine();
            if (linea.charAt(0)=='g'){
                errore=false;
                stato=Token.gioca;
            }
            else{
                if (linea.charAt(0)=='e'){
                    errore=false;
                    stato=Token.esci;
                }   
                else{
                    errore=true;
                }
            }
            
        }
        if (stato==Token.gioca){
            System.out.println("inserire la dimensione della griglia di gioco:");
            n = in.nextInt();
            System.out.print("inserire il numero di bombe da inserire");
             b = in.nextInt();  
            n=n+2;  
            game.grandezzaGriglia=n;
            game.numeroBombe=b;
            game.gioca();
            errore=true;
        }




    }
}