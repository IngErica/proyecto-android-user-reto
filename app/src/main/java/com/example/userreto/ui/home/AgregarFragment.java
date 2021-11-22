package com.example.userreto.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.userreto.R;

import com.example.userreto.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarFragment extends Fragment {

    private TextInputEditText editTextName, editTextDescripcion, editTextURL;
    private TextInputLayout textInputName, textInputDescripcion, textInputURL;
    private Button agregar;
    private Spinner spinner;
    private DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agregar, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        editTextDescripcion = view.findViewById(R.id.editTextDescripcion);
        editTextURL = view.findViewById(R.id.editTextUrl);
        textInputName = view.findViewById(R.id.text_input_layout_name);
        textInputDescripcion = view.findViewById(R.id.text_input_layout_descripcion);
        textInputURL = view.findViewById(R.id.text_input_layout_Url);
        agregar = view.findViewById(R.id.add);
        spinner = view.findViewById(R.id.spinnergenero);

        String[] genero = {"Male", "Feminine"};

        FirebaseApp.initializeApp(getActivity());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, genero));

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        return view;
    }

    public void validate() {

        String nameError = null;
        String descripcionError = null;
        String UrlError = null;

        if (TextUtils.isEmpty(editTextName.getText())) {
            nameError = getString(R.string.mandatory);
        }
        toggleTextInputLayoutError(textInputName, nameError);


        if (TextUtils.isEmpty(editTextDescripcion.getText())) {
            descripcionError = getString(R.string.mandatory);
        }
        toggleTextInputLayoutError(textInputDescripcion, descripcionError);


        if (TextUtils.isEmpty(editTextURL.getText())) {
            UrlError = getString(R.string.mandatory);
        }
        toggleTextInputLayoutError(textInputURL, UrlError);

        if(nameError==null||descripcionError==null||UrlError==null)
            agregarUsuario();

    }

    public void agregarUsuario() {

        String textgenero = spinner.getSelectedItem().toString();

        Usuario usuario = new Usuario(
                editTextName.getText().toString(),
                editTextDescripcion.getText().toString(),
                textgenero,
                editTextURL.getText().toString()
        );

        databaseReference.child("Usuario").push().setValue(usuario,
                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        Toast.makeText(getActivity(), "Usuario Añadido.", Toast.LENGTH_SHORT).show();
                    }
                });

        Toast.makeText(getActivity(), "Usuario Añadido.", Toast.LENGTH_SHORT).show();
        limpiarCampos();
    }
    public void limpiarCampos() {
        editTextName.setText("");
        editTextDescripcion.setText("");
        editTextURL.setText("");
    }

    private void toggleTextInputLayoutError(@NonNull TextInputLayout textInputLayout,
                                                   String msg) {
        textInputLayout.setError(msg);
        textInputLayout.setErrorEnabled(msg != null);


    }

}