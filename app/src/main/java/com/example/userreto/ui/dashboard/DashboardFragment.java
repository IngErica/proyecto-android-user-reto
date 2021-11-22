package com.example.userreto.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userreto.R;
import com.example.userreto.model.Usuario;
import com.example.userreto.viewadapter.AdaptadorUsuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    DatabaseReference databaseReference;
    AdaptadorUsuario adaptador;
    RecyclerView rvUsuarios;
    List<Usuario> listaUsuarios = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_persona, container, false);

        rvUsuarios = view.findViewById(R.id.recyclerViewPersona);
        rvUsuarios.setLayoutManager(new GridLayoutManager(getContext(), 1));
        databaseReference = FirebaseDatabase.getInstance().getReference();
        obtenerUsuarios();
        return view;
    }

    public void obtenerUsuarios() {
        listaUsuarios.clear();
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objeto : dataSnapshot.getChildren()) {
                    listaUsuarios.add(objeto.getValue(Usuario.class));
                }

                adaptador = new AdaptadorUsuario(getContext(), listaUsuarios);
                rvUsuarios.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}