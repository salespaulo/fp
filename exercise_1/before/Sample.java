// Bean
class Requerente {
  public boolean isConfiavel() {
    return true;    
  }                            
  
  public int getPontuacaoDeCredito() {
    return 700;
  }
  
  public int getAnosDeEmprego() {
    return 10;
  }
  
  public boolean hasRegistroCriminal() {
    return true;
  }
}                  

// == java.util.function.Predicate<T>.test(T t)
interface Avaliador {
  boolean avaliar(Requerente Requerente);
} 

class AvaliadorQualificado implements Avaliador {
  public boolean avaliar(Requerente requerente) {
    return requerente.isConfiavel();
  }
}     

// Um monte de avaliadores implementando Avaliador
// Recursividade OU DesignPattern ??
class AvaliadorChain implements Avaliador {
  private Avaliador next;
  
  public AvaliadorChain(Avaliador next) {
    this.next = next;
  }                      
  
  public boolean avaliar(Requerente requerente) {
    return next.avaliar(requerente);
  }
}

class AvaliadorDeCredito extends AvaliadorChain {
  public AvaliadorDeCredito (Avaliador next) {
    super(next);
  }             
  
  public boolean avaliar(Requerente requerente) {
    if(requerente.getPontuacaoDeCredito() > 600)
      return super.avaliar(requerente);
    return false;
  }
}

class AvaliadorDeEmprego extends AvaliadorChain {
  public AvaliadorDeEmprego (Avaliador next) {
    super(next);
  }             
  
  public boolean avaliador(Requerente requerente) {
    if(requerente.getAnosDeEmprego() > 0)
      return super.avaliar(requerente);
    return false;
  }
}

class AvaliadorDeRegistrosCriminais extends AvaliadorChain {
  public AvaliadorDeRegistrosCriminais(Avaliador next) {
    super(next);
  }             
  
  public boolean avaliar(Requerente Requerente) {
    if(!Requerente.hasRegistroCriminal())
      return super.avaliar(Requerente);
    return false;
  }
}

class Sample {          
  public static void avaliar(Requerente requerente, Avaliador avaliador) {
    String result = avaliador.avaliar(requerente) ? "aceito" : "rejeitado";
    System.out.println("Resultado da Avaliação do Requerente: " + result);
  } 
  
  public static void main(String[] args) {
    Requerente requerente = new Requerente();
    
    avaliar(requerente, 
            new AvaliadorDeCredito(
              new AvaliadorQualificado()));
    
    avaliar(requerente, 
      new AvaliadorDeCredito(
        new AvaliadorDeEmprego(
          new AvaliadorQualificado())));

    avaliar(requerente, 
      new AvaliadorDeRegistrosCriminais(
        new AvaliadorDeEmprego(
          new AvaliadorQualificado())));

    avaliar(requerente, 
      new AvaliadorDeRegistrosCriminais(
        new AvaliadorDeCredito(
          new AvaliadorDeEmprego(
            new AvaliadorQualificado()))));
  }              
}
// Como diminuir este código pela metade?
