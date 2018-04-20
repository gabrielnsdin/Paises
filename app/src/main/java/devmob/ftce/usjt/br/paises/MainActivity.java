package devmob.ftce.usjt.br.paises;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Gabriel do Nascimento RA 816113722
 */
public class MainActivity extends Activity{
public static final String URL = "https://restcountries.eu/rest/v2/";
    public static final String PAISES = "devmob.ftce.usjt.br.paises.paises";

    Spinner spinner;
    String continente = "all";
    Pais[] paises;
    Intent intent;
    ProgressBar timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner =  findViewById(R.id.continenes);
        spinner.setOnItemSelectedListener(new RegiaoSelecionada());
        timer = findViewById(R.id.timer);
        timer.setVisibility(View.INVISIBLE);
    }

    public void listarPaises(View view) {
        intent = new Intent(this, ListaPaisesActivity.class);
        if(PaisesNetwork.isConnected(this)) {
            timer.setVisibility(View.VISIBLE);
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                paises = PaisesNetwork.buscarPaises(URL, continente);
                                runOnUiThread(new Runnable() {
                                      @Override
                                      public void run() {
                                          intent.putExtra(PAISES, paises);
                                          startActivity(intent);
                                          timer.setVisibility(View.INVISIBLE);
                                      }
                                  }
                                );
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
        } else {
            Toast.makeText(this, "Sem conexão.", Toast.LENGTH_SHORT).show();
        }
    }

    private class RegiaoSelecionada implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            continente = (String) parent.getItemAtPosition(position);
            if (continente.equals("Todos")) {
                continente = "all";
            } else {
                continente = "region/"+continente.toLowerCase();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
