package alex.example.a05_recicleviewejemplo;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


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
        //creaTareas();
        adapter = new ToDoAdapter(todoList, R.layout.todo_view_model, MainActivity.this);
        binding.contentMain.contenedor.setAdapter(adapter);
        //te lo muestra de forma vertical por defecto
        //layoutManager = new LinearLayoutManager(MainActivity.this);

        //para mostrarlo como Grid
        layoutManager = new GridLayoutManager(this,2);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);







        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createToDo().show();
            }
        });
    }

    private AlertDialog createToDo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CREATE Todo");
        builder.setCancelable(false);

        View todoAlert = LayoutInflater.from(this).inflate(R.layout.todo_model_alert, null);
        EditText txtTitulo = todoAlert.findViewById(R.id.txttTtuloTodoModeAlert);
        EditText txtContenido = todoAlert.findViewById(R.id.txtContenidoTodoModelAlert);
        builder.setView(todoAlert);

        //
        builder.setNegativeButton("Cancelar",null);
        builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!txtTitulo.getText().toString().isEmpty()&& !txtContenido.getText().toString().isEmpty()){
                    todoList.add(new ToDo(txtTitulo.getText().toString(), txtContenido.getText().toString()));

                    //decir al adapter que tiene que refrescar el contenido dibuja los elementos
                    adapter.notifyDataSetChanged();

                }else{
                    Toast.makeText(MainActivity.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return builder.create();
    }
 // crea registros para mostrar datos
    private void creaTareas() {
        for (int i = 0; i <1000000; i++) {
            todoList.add(new ToDo("Titulo "+i , " Contenido "+ i));



        }
    }

}