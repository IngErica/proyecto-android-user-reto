package com.example.userreto.viewadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userreto.MainActivityDetalle;
import com.example.userreto.R;
import com.example.userreto.model.Usuario;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.List;

public class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.UsuarioViewHolder> {

    Context context;
    List<Usuario> listaUsuarios;

    public AdaptadorUsuario(Context context, List<Usuario> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_user, null, false);
        return new AdaptadorUsuario.UsuarioViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        holder.tvname.setText(listaUsuarios.get(position).getName());
        holder.tvdescripcion.setText(listaUsuarios.get(position).getDescripcion());
        holder.visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), MainActivityDetalle.class);
                intent.putExtra("Usuario", listaUsuarios.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView tvname, tvdescripcion;
        Button visualizar;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            tvname = itemView.findViewById(R.id.TextViewtitle);
            tvdescripcion = itemView.findViewById(R.id.TextViewdescripcion);
            visualizar = itemView.findViewById(R.id.visualizar);

        }
    }
}
