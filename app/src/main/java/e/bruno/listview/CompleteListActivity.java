package e.bruno.listview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CompleteListActivity extends Activity {

    private ListView completeList;
    private Button btnMudarTurno;
    private ArrayList<Char> personagens = new ArrayList<Char>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_list);

        completeList = findViewById(R.id.completeListId);
        btnMudarTurno = findViewById(R.id.btnMudarTurnoId);

        CustomAdapter customAdapter = new CustomAdapter();

        completeList.setAdapter(customAdapter);
    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return personagens.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_row, null);

            TextView txtNome = view.findViewById(R.id.nomeId);
            TextView txtIniciativa = view.findViewById(R.id.iniciativaId);
            TextView txtModificador = view.findViewById(R.id.modifierId);

            txtNome.setText(personagens.get(i).getNome());
            txtIniciativa.setText(personagens.get(i).iniciativaToString());
            txtModificador.setText(personagens.get(i).modificadorToString());

            return view;
        }
    }
}
