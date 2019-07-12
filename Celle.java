class Celle{
    public int valore;      //valore reale del numero di bombe nelle vicinanze
    public int valVis;      //valore visualizzat dall'utente
    
    /**
     * costruttore Celle.
     */
    
    public Celle(int valore){  
        this.valore=valore;
        this.valVis=50;  
    }
   /**  
    * Cambia il valore della griglia visualizzato dall'utente.
    */
    public void scopri(){
        this.valVis=this.valore;  
    }
    
   /**
    * Assegna alla cella un valore diverso per essere riconosciuta dall'utente.
    */    
    public void segnala(){
        this.valVis=100;      
    }
    
   /**
    * La cella, precedentemente segnalata, torna ad avere valore sconosciuto.
    */    
    public void nonSegnala(){
        this.valVis=50;     
    }
    
   /**
    * Restituisce True se la cella è già stata cliccata.
    */    
    public boolean pub(){
        boolean t=false;
        if (this.valore==this.valVis){
            t=true;
        }
        return t;
    }
}