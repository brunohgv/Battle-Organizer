package e.bruno.listview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private static ArrayList<Char> personagens = new ArrayList<Char>();
    private ImageView plus;
    private EditText txtNome;
    private EditText txtIniciativa;
    private EditText txtModificador;
    private Button btnGerarLista;

    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.listId);
        plus = findViewById(R.id.plusId);
        txtIniciativa = findViewById(R.id.txtInitiativeId);
        txtNome = findViewById(R.id.txtNameId);
        txtModificador = findViewById(R.id.txtModificadorId);
        final CustomAdapter customAdapter = new CustomAdapter();
        btnGerarLista = findViewById(R.id.btnGerarListaId);

        list.setAdapter(customAdapter);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome;
                String iniciativa;
                String modificador;
                if(txtNome.getText().toString().isEmpty()){
                    nome = "Provavelmente um goblin sem mãe";
                } else {
                    nome = txtNome.getText().toString();
                }
                if(txtIniciativa.getText().toString().isEmpty()){
                    iniciativa = "0";
                } else {
                    iniciativa = txtIniciativa.getText().toString();
                }
                if(txtModificador.getText().toString().isEmpty()){
                    modificador = "0";
                } else {
                    modificador = txtModificador.getText().toString();
                }

                Char character = new Char(nome, Integer.parseInt(iniciativa), Integer.parseInt(modificador));
                personagens.add(character);

                Collections.sort(personagens, new Comparator<Char>() {
                    //Ordenamento decrescente
                    public int compare(Char c1, Char c2) {
                        if (c1.getIniciativa() + c1.getModificador() != c2.getIniciativa() + c2.getModificador()) {
                            return Integer.valueOf(c2.getIniciativa() + c2.getModificador()).compareTo(c1.getIniciativa() + c1.getModificador());
                            } else {
                                return Integer.valueOf(c2.getModificador()).compareTo(c1.getModificador());
                            }
                        }
                    });

                customAdapter.notifyDataSetChanged();

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Deletar");
                dialog.setMessage("Deseja deletar esse item?");
                dialog.setIcon(android.R.drawable.ic_menu_delete);
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        Toast.makeText(MainActivity.this, "Item deletado", Toast.LENGTH_SHORT).show();
                        personagens.remove(i);
                        customAdapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        Toast.makeText(MainActivity.this, "Nada foi feito", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                return false;
            }
        });

        btnGerarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CompleteListActivity.class);

                startActivity(intent);
            }
        });
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
        public View getView(final int i, View view, ViewGroup viewGroup) {
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

