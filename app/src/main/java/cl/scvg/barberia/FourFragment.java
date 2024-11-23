package cl.scvg.barberia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cl.scvg.barberia.clases.Cita;

/*

A simple {@link Fragment} subclass.
Use the {@link FourFragment#newInstance} factory method to
create an instance of this fragment.*/
public class FourFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView lvLista;

    private List<String> ListCita = new ArrayList();
    ArrayAdapter<String> adapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private static final String ARG_DIREC = "id";
    private String id;

    public FourFragment() {
        // Required empty public constructor
    }

    /*

    Use this factory method to create a new instance of
this fragment using the provided parameters.*
    @param param1 Parameter 1.
    @param param2 Parameter 2.
            @return A new instance of fragment FourFragment.*/
    public static FourFragment newInstance(String param1, String param2) {
        FourFragment fragment = new FourFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;}

    public static FourFragment newInstance(String id) {
        FourFragment fragment = new FourFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DIREC, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            id = getArguments().getString(ARG_DIREC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño para este fragmento
        View view = inflater.inflate(R.layout.fragment_four, container, false);

        // Inicializar ListView
        ListView listView = view.findViewById(R.id.LvLista);

        // Inicializar Firebase
        inicializarFirebase();

        // Llamar al método para cargar la cita por ID
        cargarCitaPorID("33333"); // Aquí reemplaza "33333" por el ID de la cita que deseas mostrar

        // Crear y asignar el adaptador
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, ListCita);
        listView.setAdapter(adapter);

        return view;
    }

    // Inicializa Firebase
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Citas"); // Nodo donde están las citas
    }

    // Método para cargar una cita por su ID
    private void cargarCitaPorID(String idCita) {
        databaseReference.child(idCita).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ListCita.clear(); // Limpiar la lista

                if (dataSnapshot.exists()) {
                    Cita cita = dataSnapshot.getValue(Cita.class);
                    if (cita != null) {
                        // Si el ID de la cita coincide con el ID buscado, agregar la cita a la lista
                        String citaTexto = "ID: " + cita.getID() + "\n" +
                                "Peluquero: " + cita.getPeluquero() + "\n" +
                                "Lugar: " + cita.getLugar() + "\n" +
                                "Fecha: " + cita.getFecha() + "\n" +
                                "Hora: " + cita.getHora();
                        ListCita.add(citaTexto);
                    }
                } else {
                    Toast.makeText(getContext(), "No se encontró una cita con ID: " + idCita, Toast.LENGTH_SHORT).show();
                }

                // Notificar al adaptador que los datos han cambiado
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Manejo de errores
                Toast.makeText(getContext(), "Error al cargar la cita", Toast.LENGTH_SHORT).show();
            }
        });
    }






}












