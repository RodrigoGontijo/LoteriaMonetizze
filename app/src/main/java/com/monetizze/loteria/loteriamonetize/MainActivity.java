package com.monetizze.loteria.loteriamonetize;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.monetizze.loteria.loteriamonetize.Model.LoteriaModel;

import org.w3c.dom.Text;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.qtd_dezenas)
    EditText qtdDezenas;

    @BindView(R.id.num_jogos)
    EditText numJogos;

    @BindView(R.id.resultado)
    TextView resultado;

    @BindView(R.id.gerar_jogos)
    Button gerarJogos;

    @BindView(R.id.container_jogos)
    LinearLayout containerJogos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }



    @OnClick(R.id.gerar_jogos)
    public void gerarJogos(View view){
        int qtdDezenasInt = Integer.parseInt(qtdDezenas.getText().toString());
        containerJogos.removeAllViews();

        if(qtdDezenasInt <=10 && qtdDezenasInt >=6 ){
            LoteriaModel loteriaModel = new LoteriaModel(qtdDezenasInt, Integer.parseInt(numJogos.getText().toString()));
            loteriaModel.gerarJogos();
            loteriaModel.geraResultado();
            resultado.setText(convertIntArrayToString(loteriaModel.getResultado()));

            int[][] jogosGerados = loteriaModel.getJogos();

            for(int i=0; i<jogosGerados.length; i++){
                int dezenasAcertadas = comparaResultadoJogos(jogosGerados[i],loteriaModel.getResultado());

                TextView valueTV = new TextView(this);
                valueTV.setText(convertIntArrayToString(jogosGerados[i]) + " - Dezenas acertadas: " + String.valueOf(dezenasAcertadas));
                valueTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                containerJogos.addView(valueTV);
            }
        }else{
            Toast.makeText(getApplicationContext(), "Número de dezenas deve ser maior que 6 e menor que 10",Toast.LENGTH_SHORT).show();
        }
    }

    public int comparaResultadoJogos(int[] jogo, int[] resultado){
        int jogosAcertados = 0;
        for(int i=0; i<jogo.length; i++){
            for(int j=0; j<resultado.length;j++){
                if(jogo[i] == resultado[j])
                    jogosAcertados++;
            }
        }

        return jogosAcertados;
    }

    public String convertIntArrayToString(int[] intArray){

        String stringarray[] = new String[intArray.length];
        for(int i=0; i<stringarray.length; i++)
            stringarray[i] = String.valueOf(intArray[i]);

        return Arrays.toString(stringarray);
    }
}
