import java.util.Scanner;



class Griglia{
    public Celle [][] griglia; //griglia di gioco
    public int numeroBombe; //numero di bombe presenti/difficoltà
    public int dimensione;  //dimensione della griglia
    
    /** 
     * metodo costruttore.
     */ 
    public Griglia(int n, int b){ 
        this.griglia=new Celle [n][n]; // matrice contenente le Celle
        this.numeroBombe=b; 
        this.dimensione=n;
        
    }
   /**
    * inizializza i singoli elementi della griglia di gioco.
    */
    public void inizializza(){
        this.creaCelle();
        this.bombe(this.numeroBombe);
        this.numeri();
    }
    
   /**
    * gestisce una mossa in base al valore della cella selezionata.
    */    
    
    public int clicca(int i, int j){
        int c=0;
        if (this.griglia[i][j].valore>0){
            this.griglia[i][j].scopri();
            c=1;
        }
        else{
            c=cellaVuota(i, j);
        }
        
        return c;
    }
     /**
       * scopre l'area intorno ad una cella di valore 0.
      */
    
    
    private int cellaVuota(int i, int j){ 
        int c=1;
        this.griglia[i][j].scopri();
        if (this.griglia[i-1][j-1].valore>0){
            if (this.griglia[i-1][j-1].pub()==false) c=c+1;
            this.griglia[i-1][j-1].scopri();
        }
        else{
            if (this.griglia[i-1][j-1].pub()==false) c=c+cellaVuota(i-1, j-1);
        }
        
        if (this.griglia[i][j-1].valore>0){
            if (this.griglia[i][j-1].pub()==false) c=c+1;
            this.griglia[i][j-1].scopri();
        }
        else{
            if (this.griglia[i][j-1].pub()==false) c=c+cellaVuota(i, j-1);
        }
        
        if (this.griglia[i+1][j-1].valore>0){
            if (this.griglia[i+1][j-1].pub()==false) c=c+1;
            this.griglia[i+1][j-1].scopri();
        }
        else{
            if (this.griglia[i+1][j-1].pub()==false) c=c+cellaVuota(i+1, j-1);
        }   


        if (this.griglia[i-1][j+1].valore>0){
            if (this.griglia[i-1][j+1].pub()==false) c=c+1;
            this.griglia[i-1][j+1].scopri();
        }
        else{
            if (this.griglia[i-1][j+1].pub()==false) c=c+cellaVuota(i-1, j+1);
        }
        
        if (this.griglia[i][j+1].valore>0){
            if (this.griglia[i][j+1].pub()==false) c=c+1;
            this.griglia[i][j+1].scopri();
        }
        else{
            if (this.griglia[i][j+1].pub()==false) c=c+cellaVuota(i, j+1);
        }
        
        if (this.griglia[i+1][j+1].valore>0){
            if (this.griglia[i+1][j+1].pub()==false) c=c+1;
            this.griglia[i+1][j+1].scopri();
        }
        else{
            if (this.griglia[i+1][j+1].pub()==false) c=c+cellaVuota(i+1, j+1);
        }       
        if (this.griglia[i-1][j].valore>0){
            if (this.griglia[i-1][j].pub()==false) c=c+1;
            this.griglia[i-1][j].scopri();
        }
        else{
            if (this.griglia[i-1][j].pub()==false) c=c+cellaVuota(i-1, j);
        }
        if (this.griglia[i+1][j].valore>0){
            if (this.griglia[i+1][j].pub()==false) c=c+1;
            this.griglia[i+1][j].scopri();
        }
        else{
            if (this.griglia[i+1][j].pub()==false) c=c+cellaVuota(i+1, j);
        }   
        
        return c;
    }
    
    
   /**
    * Invoca in metodo costruttore delle celle per ogni elemento della griglia.
    */    
    private void creaCelle(){ 
        int i=0;
        int j=0;
        int n=this.dimensione;
        while(i<n){
            j=0;
            while(j<n){
                this.griglia[i][j]= new Celle(0); 
                j=j+1;
            }
            i=i+1;
        }
        i=0;
        j=0;
        while(i<n){
            this.griglia[i][0].valVis=0;
            this.griglia[i][n-1].valVis=0;      
            i=i+1;
        }
        j=1;
        while (j<n){
            this.griglia[0][j].valVis=0;
            this.griglia[n-1][j].valVis=0;  
            j=j+1;
        }
    }
   /**
    * Le bombe vengono messe in celle scelte a caso.
    */   

    private void bombe(int b){
        int i=0;
        int randi=0;
        int randj=0;
        while(i<b){
            randi=1+(int)(Math.random()*(this.dimensione-2)); //-2 o -1
            randj=1+(int)(Math.random()*(this.dimensione-2));

            if (this.griglia[randi][randj].valore==10){
            }
            else{
                this.griglia[randi][randj].valore=10;
                i=i+1;
            }

        }
    }
   /**
    * Calcola il numero di bombe presenti nelle celle adiacenti ad ogni singola cella.
    */
    private void numeri(){
        int i=1;
        int j=1;
        int v=0;
        while(i<this.dimensione-1){  // scorro la matrice della griglia 
            j=1;
            v=0;
            while(j<this.dimensione-1){
                        v=0;
                        if (this.griglia[i-1][j-1].valore==10) v=v+1;
                        if (this.griglia[i-1][j].valore==10) v=v+1;
                        if (this.griglia[i-1][j+1].valore==10) v=v+1;
                        if (this.griglia[i][j+1].valore==10) v=v+1;
                        if (this.griglia[i][j-1].valore==10) v=v+1;
                        if (this.griglia[i+1][j-1].valore==10) v=v+1;
                        if (this.griglia[i+1][j+1].valore==10) v=v+1;
                        if (this.griglia[i+1][j].valore==10) v=v+1;
                        if (this.griglia[i][j].valore==10){
                        }
                        else{
                            this.griglia[i][j].valore=v;
                        }
                        j=j+1;
            }
            i=i+1;
        }
    }
    
} 
