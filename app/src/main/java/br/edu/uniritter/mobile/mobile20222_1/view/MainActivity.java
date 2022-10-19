package br.edu.uniritter.mobile.mobile20222_1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.DAO.DAO;
import br.edu.uniritter.mobile.mobile20222_1.Objects.Pessoa;
import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.adapter.UsersAddapter;
import br.edu.uniritter.mobile.mobile20222_1.model.User;
import br.edu.uniritter.mobile.mobile20222_1.repository.OnReadyListener;
import br.edu.uniritter.mobile.mobile20222_1.repository.UserRepository;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "MainActivity";

    EditText editTextNome;
    Button botaoSalvar;
    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: começando a bagaça");

        //aqui infla o layout xml
        setContentView(R.layout.activity_main);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Alguém clicou ocl");
                Intent intent = new Intent(view.getContext(), Activity2.class);
                intent.putExtra("userId", 1);


                startActivity(intent);
            }
        };
        //buscando um elemento visual do layout para manuipulação
        Button bt = findViewById(R.id.button02);

        findViewById(R.id.button02).setOnClickListener(ocl);
        findViewById(R.id.botao).setOnClickListener(
                (view) -> {
                    Log.d(TAG, "onClick: Alguém clicou lambda");

                    Intent intent = new Intent(view.getContext(), Activity2.class);
                    startActivity(intent);
                });
        //RecyclerView rc = findViewById(R.id.recycler1);
        //UsersAddapter adapter = new UsersAddapter( UserRepository.getInstance(this).getUsers());
        UserRepository.getInstance(this, new OnReadyListener() {
            @Override
            public void onReady() {
                RecyclerView rc = findViewById(R.id.recycler1);
                UsersAddapter adapter = new UsersAddapter(UserRepository.getInstance().getUsers());
                rc.setAdapter(adapter);
                LinearLayoutManager llm1 = new LinearLayoutManager(getApplicationContext());
                rc.setLayoutManager(llm1);
            }
        });
        User user = getIntent().getParcelableExtra("userObj");

        TextView tv = (TextView) findViewById(R.id.editTextTextPersonName2);
        tv.setText("bem vindo " + user.getName() + "(" + user.getPassword() + ")");

        //SQLITE
        editTextNome = findViewById(R.id.editTextNome);
        botaoSalvar = findViewById(R.id.botaoSalvar);
        listView = findViewById(R.id.listView);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DAO dao = new DAO(getApplicationContext());
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(editTextNome.getText().toString());
            }
        });


    }

    @Override
    public void onClick(View view) {

    }
}

