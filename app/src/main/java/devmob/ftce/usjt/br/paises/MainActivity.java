package devmob.ftce.usjt.br.paises;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * @author Gabriel do Nascimento RA 816113722
 */
public class MainActivity extends Activity{

    public static final String NOME = "br.usjt.deswebmob.paises.nomeContinente";
    private String continente;
    private Spinner continentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        continentes = findViewById(R.id.continenes);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        continentes.setAdapter(adapter);*/

        continentes.setOnItemSelectedListener(new PaisSelecionado());

    }

    public void listarPaises(View view){
        Intent intent = new Intent(this, ListaPaisesActivity.class);
        intent.putExtra(NOME, continente);
        startActivity(intent);
    }

    //Classe da solução
    private class PaisSelecionado implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            continente = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
