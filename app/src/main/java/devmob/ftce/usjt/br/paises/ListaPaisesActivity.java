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

    Activity activity;
    Pais[] paises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_paises);
        activity = this;
        Intent intent = getIntent();
        paises = (Pais[]) intent.getSerializableExtra(MainActivity.PAISES);
        ListView listView = findViewById(R.id.lista_paises);
        PaisAdapter adapter = new PaisAdapter(paises, this);
        listView.setAdapter(adapter);
		
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(activity, DadosPaisActivity.class);
                intent1.putExtra(PAIS, paises[position]);
                startActivity(intent1);
            }
        });
    }
}

