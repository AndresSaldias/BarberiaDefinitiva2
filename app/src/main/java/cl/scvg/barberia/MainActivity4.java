package cl.scvg.barberia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

import cl.scvg.barberia.clases.Cita;
import cl.scvg.barberia.clases.Peluquero;

public class MainActivity4 extends AppCompatActivity {


    Button guardar;
    ListView lvHora, lvDia;
    String lugar, trabajador,fecha,hora;
    Random random = new Random();

    ArrayList<String> listHora = new ArrayList<>();
    ArrayList<String> listDia = new ArrayList<>();


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);

        Intent intent = getIntent();

        lugar = intent.getStringExtra("direccion");
        trabajador = intent.getStringExtra("peluquero");



        guardar = findViewById(R.id.buttonGuardar);
        lvDia=findViewById(R.id.lv_DIA);
        lvHora=findViewById(R.id.lv_HORA);

        inicializarFireBase();

        listDia.add("LUNES");
        listDia.add("MARTES");
        listDia.add("MIERCOLES");
        listDia.add("JUEVES");
        listDia.add("VIERNES");
        listDia.add("SABADO");

        listHora.add("12:30");
        listHora.add("13:00");
        listHora.add("13:30");
        listHora.add("14:00");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listHora);
        lvHora.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listDia);
        lvDia.setAdapter(adapter2);

        lvHora.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtiene el elemento seleccionado
                hora = listHora.get(position);



            }
        });
        lvDia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtiene el elemento seleccionado
                fecha = listDia.get(position);



            }
        });





        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //id deberia ser algo ligado al inicio de sesion
                //pa que no de error
                String id = String.valueOf(random.nextInt(100));
                postCita(id,trabajador,lugar,fecha,hora);

                Intent intent_lista = new Intent(MainActivity4.this, MainActivitymenu.class);

                intent_lista.putExtra("id",id);


                startActivity(intent_lista);


            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void postCita(String id, String peluquero, String lugar,String dates,String horas) {

        Cita cita = new Cita();

        cita.setID(id);
        cita.setPeluquero(peluquero);
        cita.setLugar(lugar);
        cita.setFecha(dates);
        cita.setHora(horas);

        databaseReference.child("Citas").child(cita.getID()).setValue(cita);



    }




    private void inicializarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
    }
}