import java.util.Scanner;
 
class Partita{
    public int grandezzaGriglia;
    public int numeroBombe;
    private int salvaNumero;
    
    /**
     * metodo costruttore.
     */
    public Partita(int n, int b){
        this.grandezzaGriglia=n;    
        this.numeroBombe=b; 
    }
    /**
     * metodo per iniziare la partita.Viene inizializzata la griglia di gioco.    
    */
     
     
    public void gioca(){
        Griglia griglia= new Griglia(this.grandezzaGriglia, this.numeroBombe);
        griglia.inizializza();
        svolgimento(griglia);
    }
   /**
    * controlla l'avanzamento della partita.
    */    
    public void svolgimento(Griglia grigliaQui){
        Scanner in = new Scanner(System.in);
        stampa(grigliaQui);
        int max=(this.grandezzaGriglia-2)*(this.grandezzaGriglia-2)-this.numeroBombe;
        //int i=0;
        //int j=0;
        int celleScoperte=0;
        int temp=0;
        Token tipo = Token.gioca;
        while(celleScoperte<max){
            System.out.println("mossa:                        (segnala per segnalere una bomba, esci per uscire)");
            tipo=ottieniMossa();
            temp=0;
            switch (tipo){
                case annulla: annulla(grigliaQui); break;
                case segnala: segnala(grigliaQui); break;
                case cifra: temp=cifra(grigliaQui); break;
                case fine: celleScoperte=max*max*max; break;
            }
            if(temp==-1){
                celleScoperte=max*max;
            }
            else{
                celleScoperte=celleScoperte+temp;
            }
            stampa(grigliaQui);
        }
        
        if (celleScoperte==(max*max)){
            System.out.println("hai perso! ");
        }
        else{
            if(celleScoperte==max){
                System.out.println("hai vinto ");
            }
        }
        
        
    }
    
   /**
    * ripristina il valore di una cella precedentemente segnalata con il metodo "segnala".
    */    
    
    private void annulla (Griglia grigliaQui){
        Scanner in = new Scanner(System.in);
        int i=in.nextInt();
        i=i+1;
        int j=in.nextInt();
        j=j+1;
        if (grigliaQui.griglia[i][j].valVis==100){
            grigliaQui.griglia[i][j].valVis=50;
        }

    }
    /**
     * Viene cambiato il carattere visualizzato dall'utente in modo da segnalare la presenza di una bomba. 
     */
    
    private void segnala (Griglia grigliaQui){
        Scanner in = new Scanner(System.in);
        int i=in.nextInt();
        i=i+1;
        int j=in.nextInt();
        j=j+1;
        if (grigliaQui.griglia[i][j].valVis==50){
            grigliaQui.griglia[i][j].valVis=100;
        }

    }
    
    
   /**
    * richiede un secondo input numerico dopo averne ricevuto uno dello stesso tipo.
    */    
    
    private int cifra(Griglia grigliaQui){
        Scanner in = new Scanner(System.in);
        int j=in.nextInt();
        j=j+1;
        int k=0;
        if (salvaNumero+1>this.grandezzaGriglia-2 || j>this.grandezzaGriglia-2){
            System.out.print("mossa non valida");
            System.out.println();
        }
        else {
            if (grigliaQui.griglia[salvaNumero+1][j].valore==grigliaQui.griglia[salvaNumero+1][j].valVis){
                System.out.print("mossa non valida");
                System.out.println();
            }
            else{
                if (grigliaQui.griglia[salvaNumero+1][j].valore==10){
                    k=-1;
                }
                else{
                    if (grigliaQui.griglia[salvaNumero+1][j].valVis==100){
                        System.out.println("questa cella è stata segnalata, per annullare scrivere annulla");
                    }
                    else{
                        k=grigliaQui.clicca(salvaNumero+1, j);
                    }
                }   
            }   
        }
        
        return k;
        
    }
    
   /**
    * richiede un input valido all'utente.
    */    
    
    public Token ottieniMossa(){
        String linea="o";
        Scanner in = new Scanner(System.in);
        Token tipo= Token.gioca;
        boolean errore=true;
        while(errore==true){
            linea = in.nextLine();
            errore=false;
            switch (linea.charAt(0)) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': tipo = Token.cifra; ; break;
                case 's': tipo = Token.segnala; break;
                case 'a': tipo = Token.annulla; break;
                case 'f': tipo = Token.fine; break;
                default : errore=true; break;
            }
        }
        if (tipo==Token.cifra){
             salvaNumero = Integer.parseInt(linea);
        }
            
        return tipo;
    }
   /**
    * stampa a video la griglia di gioco aggoirnata all'ultima mossa.
    */    
    public void stampa(Griglia grigliaQui){
        System.out.print('\u000C');
        int i=1;
        int j=1;
        int n=grigliaQui.dimensione;
        while(i<n-1){
            j=1;
            System.out.print("| ");
            while(j<n-1){
                
                if(grigliaQui.griglia[i][j].valVis==50){
                    System.out.print("* ");
                }
                else{
                    if (grigliaQui.griglia[i][j].valVis==100){
                        System.out.print("X ");

                    }
                    else{
                    System.out.print(grigliaQui.griglia[i][j].valVis + " ");
                    }
                }
    
                j=j+1;
                System.out.print("| ");
            }
            System.out.print(" ");
            System.out.println();
            i=i+1;
            
            
        }
    }
    
    
}