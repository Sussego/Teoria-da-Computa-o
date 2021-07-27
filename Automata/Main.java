class Main {
  public static void main(String[] args) {
    Arquivo automato = new Arquivo();
    String retorno;
    retorno = automato.LerRegras("Regras.txt");
    System.out.println(retorno);
    retorno = automato.LerPalavras("Palavras.txt");
    System.out.println(retorno);
    automato.Calcular("Resultado.txt");
  }
}