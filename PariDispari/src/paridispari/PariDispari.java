/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paridispari;

/**
 * La classe PariDispari eredita dalla classe Thread e fa partire
 * due thread in modo alterno che faranno un conteggio, da 1 a 20,
 * scrivendo se il conteggio in quel momento è pari o dispari
 * @author 73734062
 */
public class PariDispari extends Thread{
  private int massimo;
  private boolean pari;
  private int ritardo = 500;
  
  /** Creazione del costruttore */ 
  
  public PariDispari (int finale, boolean pari){
    massimo   = finale;
    this.pari = pari;
  }
  
  /**
   * Creazione del metodo run, riscrivendo il metodo base, in modo
   * che esso stampi se il numero attuale inirente al conteggio è 
   * un numero pari o un numero dispari
   */
  
  @Override
  public void run(){
    String chisono;
    chisono = Thread.currentThread().getName();
    for (int xx = 0; xx < massimo; xx++){
      if(pari){              // se è il thread che deve stampare i numeri pari 
        if(xx % 2 == 0)      // numero pari 
          System.out.println(chisono+"-pari "+xx);
      }  
      else                   // se è il thread che deve stampare i numeri dispari 
        if (xx % 2 != 0)     // numero dispari 
          System.out.println(chisono+"-dispari "+xx);
      try {Thread.sleep(ritardo);} 
      catch (InterruptedException e){System.out.println(e);}
    }  
  }
  /**
   * Vengono fatti partire i due thread, uno per il conteggio dei numeri pari
   * e uno per il conteggio dei numeri dispari che, in modo alterno, faranno
   * il conteggio da 1 a 20 stampando a video tutti i numeri e specificando se
   * i numeri stampati sono pari o dispari
   */
  
  public static void main(String[] args){
    if (args.length != 1){
	  System.out.println("Dovresti passare il numero intero");
	  return;
    }    
    int n = Integer.parseInt(args[0]);
    Thread TP = new PariDispari (n + 1, true);  // thread che conta i pari
    Thread TD = new PariDispari (n + 1, false); // thread che conta i dispari
    System.out.println("->Contate fino a " + n);
    TP.start();                                 // avvio esecuzione thread
    TD.start();
    try{
      TP.join();                                // attesa terminazione thread
      TD.join();
    }
    catch(Exception e){}
    System.out.println("<-Fine conteggio!");   
  }
 }
