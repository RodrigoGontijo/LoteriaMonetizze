package com.monetizze.loteria.loteriamonetize.Model;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Rodrigo on 22/09/2019.
 */

public class LoteriaModel {

    private int qtdDezenas;
    private int[] resultado;
    private int totalJogos;
    private int[][] jogos;

    public LoteriaModel(int qtdDezenas, int totalJogos){
        this.qtdDezenas = qtdDezenas;
        this.totalJogos = totalJogos;
        this.resultado = new int[6];
        jogos = new int[totalJogos][qtdDezenas];
    }

    public int getQtdDezenas() {
        return qtdDezenas;
    }

    public void setQtdDezenas(int qtdDezenas) {
        this.qtdDezenas = qtdDezenas;
    }

    public int[] getResultado() {
        return resultado;
    }

    public void setResultado(int[] resultado) {
        resultado = resultado;
    }

    public int getTotalJogos() {
        return totalJogos;
    }

    public void setTotalJogos(int totalJogos) {
        this.totalJogos = totalJogos;
    }

    public int[][] getJogos() {
        return jogos;
    }

    public void setJogos(int[][] jogos) {
        this.jogos = jogos;
    }

    public void gerarJogos(){
        Random random = new Random();
        for(int i=0; i<totalJogos; i++){

            int indexDezena = 0;
            do
            {
                int next = random.nextInt(61 - 1) + 1;;
                if (!confereExisteDezenaGerada(i,next))
                {
                    jogos[i][indexDezena] = next;
                    indexDezena++;
                }
            } while (indexDezena < qtdDezenas);

            Arrays.sort(jogos[i]);
        }
    }

    public boolean confereExisteDezenaGerada(int jogo, int dezena){
        boolean retorno = false;
        for(int i =0; i<qtdDezenas; i++){
            if(jogos[jogo][i] == dezena)
                retorno = true;
        }
        return retorno;
    }

    public void geraResultado(){

        int[] resultadoTemp = new int[6];
        Random random = new Random();
        int indexResultado = 0;
        do
        {
            int next = random.nextInt(61 - 1) + 1;;
            if (!confereExisteDezenaResultrado(next))
            {
                resultado[indexResultado] = next;
                indexResultado++;
            }
        } while (indexResultado < 6);


        setResultado(resultadoTemp);
        Arrays.sort(resultado);
    }

    public boolean confereExisteDezenaResultrado(int dezena){
        boolean retorno = false;
        for(int i =0; i<6; i++){
            if(resultado[i] == dezena)
                retorno = true;
        }
        return retorno;
    }
}
