import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.lang.*;

public class Arquivo{
  private static String transicoes;
  private String tranFormatado="";
  private static String estInicial;
  private static String estFinais;
  private String palavras = "";
  private char estAtual;

  //Le as regras do automato
  public String LerRegras(String Caminho){
    try{
      FileReader arq = new FileReader(Caminho);
      BufferedReader lerArq = new BufferedReader(arq);
      try{
        estInicial = lerArq.readLine();
        estFinais = lerArq.readLine();
        transicoes = lerArq.readLine();
        arq.close();

        //Formata e separa as regras
        String[] aux1 = transicoes.split(",");
        for(int i = 0 ; i < aux1.length ; i++){
          int k = 0;
          for(int j = 0 ; j < 6 ; j++){
            if(aux1[i].charAt(j) != '(' && aux1[i].charAt(j) != ')' && aux1[i].charAt(j) != '|'){
            tranFormatado = tranFormatado + aux1[i].charAt(j);
            k++;
            }
          }
          if(i < aux1.length - 1){
            tranFormatado = tranFormatado + "\n";
          }
        }

    }catch (IOException ex){
        estInicial = "Erro: Nao foi possivel ler o arquivo!";
    }

    }catch (FileNotFoundException ex){
      estInicial = "Erro: Arquivo nao encontrado!";
    }
    if(estInicial.contains("Erro")){
      return estInicial;
    }else{
      return "Leitura das Regras Realizada!";
    }
  }

  //Le as palavras de entrada
  public String LerPalavras(String Caminho){
    try{
      FileReader arq = new FileReader(Caminho);
      BufferedReader lerArq = new BufferedReader(arq);
      String linha = "";
      try{
        linha = lerArq.readLine();
        while(linha != null){
          palavras = palavras +"\n"+ linha;
          linha = lerArq.readLine();
        }
        arq.close();
      }catch (IOException ex){
        palavras = "Erro: Nao foi possivel ler o arquivo!";
      }

    }catch (FileNotFoundException ex){
      palavras = "Erro: Arquivo nao encontrado!";
    }

    if(palavras.contains("Erro")){
      return palavras;
    }else{
      return "Leitura das palavras Realizada!";
    }
  }
  //execulta as regras na palavra
  public void Calcular(String Caminho){
    String[] palavrasSep = palavras.split("\n");
    String[] regrasSep = tranFormatado.split("\n");
    String[] estFinalSep = estFinais.split(",");
    String DB="";
    char inicial = estInicial.charAt(0);
    for(int i = 0 ; i < palavrasSep.length ; i++){
      estAtual = inicial;
      int tamanho = palavrasSep[i].length();
      for(int j = 0 ; j < tamanho ; j++){
        for(int k = 0; k < regrasSep.length ; k++){
          if(regrasSep[k].charAt(0) == estAtual){
            if(palavrasSep[i].charAt(j) == regrasSep[k].charAt(1)){
              estAtual = regrasSep[k].charAt(2);
              break;
            }
          }
        }
      }
      for(int l = 0 ; l < estFinalSep.length ; l++){
        if(estAtual == estFinalSep[l].charAt(0)){  
          DB = DB + "Aceita\n";
        }else{
          DB = DB + "Rejeita\n";
        }
      }
    }
    //Escreve os Resultados no Arquivo
    try{
        FileWriter arq = new FileWriter(Caminho);
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.println(DB);
        gravarArq.close();
        System.out.println("Resultados escritos no Arquivo!");
      }catch(IOException e){
            System.out.println("Falha ao Escrever no Arquivo!");
      }
  }
}