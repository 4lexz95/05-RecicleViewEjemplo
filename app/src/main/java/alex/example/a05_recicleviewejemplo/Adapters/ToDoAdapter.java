package alex.example.a05_recicleviewejemplo.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import alex.example.a05_recicleviewejemplo.R;
import alex.example.a05_recicleviewejemplo.modelos.ToDo;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoVH> {

    private List<ToDo> objects;
    private int resource;
    // sin el context no puedo inflar la lista de los elementos
    private Context context;

    public ToDoAdapter(List<ToDo> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }


// este se encarga de instanciar tanto elementos como me quepan en la pantalla
    @NonNull
    @Override
    public ToDoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View toDoView = LayoutInflater.from(context).inflate(resource, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT

        );
        toDoView.setLayoutParams(lp);
        return new ToDoVH(toDoView);
    }
//
    //Es llamado por el adapter para modificar el contenido de un viewHolder (VH) ya creado
    @Override
    public void onBindViewHolder(@NonNull ToDoVH holder, int position) {
        ToDo todo = objects.get(position);
        holder.lblTitulo.setText(todo.getTitulo());
        holder.lblContenido.setText(todo.getContenido());
        holder.lblFecha.setText(todo.getFecha().toString());
        if (todo.isCompletado()){
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_on_background);
        }else
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_off_background);

        holder.btnCompletado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmUpdate("SEGURO, SEGUROOOOO", todo).show();
                todo.setCompletado(!todo.isCompletado());
                notifyDataSetChanged();

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete("Vas a eliminar una tarea", holder.getAdapterPosition()).show();
                /*
                //objects.remove(todo);
                objects.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());

                 */
            }
        });
    }
    private AlertDialog confirmDelete(String titulo, int posicion){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setCancelable(false);

        builder.setNegativeButton("No", null);
        builder.setPositiveButton("siii", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

               objects.remove(posicion);
                notifyItemRemoved(posicion);


            }
        });
        // se crea la ventana y con el .show lo muestra
        return builder.create();
    }
 // cuantos objectos va a mostrar (el tamaño de la lista)
    @Override
    public int getItemCount() {

        return objects.size();
    }

    private AlertDialog confirmUpdate(String titulo, ToDo toDo){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setCancelable(false);

        builder.setNegativeButton("NO", null);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                toDo.setCompletado(!toDo.isCompletado());
                notifyDataSetChanged();
            }
        });
        return builder.create();



    }

    public class ToDoVH extends RecyclerView.ViewHolder{


        TextView lblTitulo , lblContenido, lblFecha;
        ImageButton btnCompletado;
        ImageButton btnDelete;



        public ToDoVH(@NonNull View itemView) {
            super(itemView);


            lblTitulo = itemView.findViewById(R.id.lbTituloToDoViewModel);
            lblContenido = itemView.findViewById(R.id.lbContenidoToDoViewModel);
            lblFecha = itemView.findViewById(R.id.lbFechaToDoViewModel);
            btnCompletado = itemView.findViewById(R.id.btnCompletadoToDoViewModel);
            btnDelete = itemView.findViewById(R.id.btndeleteTodo);


        }
    }
}
