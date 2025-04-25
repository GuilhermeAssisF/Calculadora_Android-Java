package br.edu.ifsuldeminas.mch.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ifsuldeminas.mch.calc";
    private Button buttonIgual,
            buttonUm, buttonDois, buttonTres, buttonQuatro, buttonCinco,
            buttonSeis, buttonSete, buttonOito, buttonNove, buttonZero,
            buttonLimpar, buttonMenos, buttonMais, buttonMult, buttonDiv, buttonPorc,
            buttonVirg, buttonDelete;
    private TextView textViewResultado;
    private TextView textViewUltimaExpressao;

    private boolean resultadoMostrado = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IniciarComponentes();

        //esconde barra do titulo
        getSupportActionBar().hide();

        buttonZero.setOnClickListener(this);
        buttonUm.setOnClickListener(this);
        buttonDois.setOnClickListener(this);
        buttonTres.setOnClickListener(this);
        buttonQuatro.setOnClickListener(this);
        buttonCinco.setOnClickListener(this);
        buttonSeis.setOnClickListener(this);
        buttonSete.setOnClickListener(this);
        buttonOito.setOnClickListener(this);
        buttonNove.setOnClickListener(this);

        buttonMais.setOnClickListener(this);
        buttonMenos.setOnClickListener(this);
        buttonMult.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonPorc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expressao = textViewUltimaExpressao.getText().toString();

                if (!expressao.isEmpty() && !expressao.endsWith("%")) {
                    try {
                        // Se a expressão termina com um número, convertemos aquele número em porcentagem
                        // Ex: "200%" vira "200*0.01"
                        String novaExpressao = expressao + "*0.01";
                        Expression exp = new ExpressionBuilder(novaExpressao).build();
                        double resultado = exp.evaluate();

                        // Substitui a última parte da expressão (o número com %) pelo resultado
                        textViewResultado.setText(String.valueOf(resultado));
                        textViewUltimaExpressao.setText(String.valueOf(resultado));
                        resultadoMostrado = true;

                    } catch (Exception e) {
                        textViewResultado.setText("Erro");
                        e.printStackTrace();
                    }
                }
            }
        });

        buttonVirg.setOnClickListener(this);

        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewResultado.setText("");
                textViewUltimaExpressao.setText("");
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = textViewUltimaExpressao.getText().toString();

                if (!string.isEmpty()){

                    byte var0 = 0;
                    int var1 = string.length()-1;
                    String txtExpressao = string.substring(var0,var1);
                    textViewUltimaExpressao.setText(txtExpressao);

                }
                textViewResultado.setText("");
            }
        });

        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String expressao = textViewUltimaExpressao.getText().toString();
                    if (!expressao.isEmpty()) {
                        Expression exp = new ExpressionBuilder(expressao).build();
                        double resultado = exp.evaluate();

                        long longResult = (long) resultado;
                        String resultadoFinal = (resultado == (double) longResult)
                                ? String.valueOf(longResult)
                                : String.valueOf(resultado);

                        textViewResultado.setText(resultadoFinal);

                        textViewUltimaExpressao.setText(expressao);
                    }
                } catch (Exception e) {
                    textViewResultado.setText("Erro");
                    e.printStackTrace();
                }
            }
        });


    }

    private void IniciarComponentes(){
        buttonZero = findViewById(R.id.buttonZeroID);
        buttonUm = findViewById(R.id.buttonUmID);
        buttonDois = findViewById(R.id.buttonDoisID);
        buttonTres = findViewById(R.id.buttonTresID);
        buttonQuatro = findViewById(R.id.buttonQuatroID);
        buttonCinco = findViewById(R.id.buttonCincoID);
        buttonSeis = findViewById(R.id.buttonSeisID);
        buttonSete = findViewById(R.id.buttonSeteID);
        buttonOito = findViewById(R.id.buttonOitoID);
        buttonNove = findViewById(R.id.buttonNoveID);

        buttonMais = findViewById(R.id.buttonSomaID);
        buttonMenos = findViewById(R.id.buttonSubtracaoID);
        buttonMult = findViewById(R.id.buttonMultiplicacaoID);
        buttonDiv = findViewById(R.id.buttonDivisaoID);
        buttonPorc = findViewById(R.id.buttonPorcentoID);
        buttonIgual = findViewById(R.id.buttonIgualID);

        buttonDelete = findViewById(R.id.buttonDeleteID);
        buttonVirg = findViewById(R.id.buttonVirgulaID);
        buttonLimpar = findViewById(R.id.buttonResetID);

        textViewUltimaExpressao = findViewById(R.id.textViewUltimaExpressaoID);
        textViewResultado = findViewById(R.id.textViewResultadoID);

    }

    public void AcrescentarUmaExpressao(String string, boolean limparDados) {
        if (resultadoMostrado) {
            textViewUltimaExpressao.setText("");
            resultadoMostrado = false;
        }

        if (limparDados) {
            textViewResultado.setText("");
            textViewUltimaExpressao.append(string);
        } else {
            textViewUltimaExpressao.append(string);
            textViewResultado.setText("");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonZeroID) {
            AcrescentarUmaExpressao("0", true);
        }
        else if (view.getId() == R.id.buttonUmID) {
            AcrescentarUmaExpressao("1", true);
        }
        else if (view.getId() == R.id.buttonDoisID) {
            AcrescentarUmaExpressao("2", true);
        }
        else if (view.getId() == R.id.buttonTresID) {
            AcrescentarUmaExpressao("3", true);
        }
        else if (view.getId() == R.id.buttonQuatroID) {
            AcrescentarUmaExpressao("4", true);
        }
        else if (view.getId() == R.id.buttonCincoID) {
            AcrescentarUmaExpressao("5", true);
        }
        else if (view.getId() == R.id.buttonSeisID) {
            AcrescentarUmaExpressao("6", true);
        }
        else if (view.getId() == R.id.buttonSeteID) {
            AcrescentarUmaExpressao("7", true);
        }
        else if (view.getId() == R.id.buttonOitoID) {
            AcrescentarUmaExpressao("8", true);
        }
        else if (view.getId() == R.id.buttonNoveID) {
            AcrescentarUmaExpressao("9", true);
        }
        else if (view.getId() == R.id.buttonVirgulaID) {
            AcrescentarUmaExpressao(".", true);
        }
        else if (view.getId() == R.id.buttonSomaID) {
            AcrescentarUmaExpressao("+", false);
        }
        else if (view.getId() == R.id.buttonSubtracaoID) {
            AcrescentarUmaExpressao("-", false);
        }
        else if (view.getId() == R.id.buttonMultiplicacaoID) {
            AcrescentarUmaExpressao("*", false);
        }
        else if (view.getId() == R.id.buttonDivisaoID) {
            AcrescentarUmaExpressao("/", false);
        }
        else if (view.getId() == R.id.buttonPorcentoID) {
            AcrescentarUmaExpressao("%", false);
        }

    }
}











