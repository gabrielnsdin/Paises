package devmob.ftce.usjt.br.paises;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * @author Gabriel do Nascimento RA 816113722
 */
public class ListaPaisesActivity extends Activity {
    public static final String PAIS = "br.usjt.desmob.paises.pais";

    private ArrayList<Pais> lista;
    private Activity activity;
    private ArrayList<String> nomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_paises);
        activity = this;
        Intent intent = getIntent();
        String continente = intent.getStringExtra(MainActivity.NOME);
        lista = Data.listarPaises(continente);
        nomes = Data.listarNomes(lista);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nomes);

        ListView listView = findViewById(R.id.lista_paises);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(activity, DadosPaisActivity.class);
                intent1.putExtra(PAIS, lista.get(position));
                startActivity(intent1);
            }
        });
    }
}

