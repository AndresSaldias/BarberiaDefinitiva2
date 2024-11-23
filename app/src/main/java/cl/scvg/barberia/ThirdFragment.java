package cl.scvg.barberia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {

    public ThirdFragment() {
        // Constructor vacío
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View rootView = inflater.inflate(R.layout.fragment_third, container, false);

        // Configurar ListView
        ListView listView = rootView.findViewById(R.id.listViewSettings);
        String[] settingsItems = {"Perfil", "Cuentas", "Notificaciones", "Ayuda", "Comentarios", "Privacidad"};

        // Crear el adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, settingsItems);
        listView.setAdapter(adapter);

        // Configurar el listener de los clics en la lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Mostrar un mensaje al hacer clic
                Toast.makeText(getActivity(), "Próximamente", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
