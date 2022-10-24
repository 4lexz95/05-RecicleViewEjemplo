package alex.example.a05_recicleviewejemplo;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;


import java.util.ArrayList;

import alex.example.a05_recicleviewejemplo.Adapters.ToDoAdapter;
import alex.example.a05_recicleviewejemplo.databinding.ActivityMainBinding;
import alex.example.a05_recicleviewejemplo.modelos.ToDo;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<ToDo> todoList;
    private ToDoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        todoList = new ArrayList<>();
        creaTareas();
        adapter = new ToDoAdapter(todoList, R.layout.todo_view_model, MainActivity.this);
        binding.contentMain.contenedor.setAdapter(adapter);
        //te lo muestra de forma vertical por defecto
        layoutManager = new LinearLayoutManager(MainActivity.this);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);






        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
 // crea registros para mostrar datos
    private void creaTareas() {
        for (int i = 0; i <1000000; i++) {
            todoList.add(new ToDo("Titulo "+i , " Contenido "+ i));



        }
    }

}