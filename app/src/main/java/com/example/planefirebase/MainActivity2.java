package com.example.planefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    ListView listView; //визуальный компонент
    ArrayAdapter <String> adapter; //адаптер, который связывает эл. управления с данными
    List<String> list; //источник данных
    TextView textView;

    private DatabaseReference mDataBase; //доступ к базе данных
    private String Plane_Key = "Plane"; //для отображения вводимых данных внутри группы Plane

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.tvProbel);
        listView = findViewById(R.id.LVConclusion); //поиск компонента listView на экране
        list = new ArrayList<>(); //создание ArrayList
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list); //создание ArrayAdapter
        listView.setAdapter(adapter); //устанавливаем для списка адаптер
        mDataBase = FirebaseDatabase.getInstance().getReference(Plane_Key);

        //слушатель для работы с FireBae
        ValueEventListener valueEventListener = new ValueEventListener() {
            //меняется информация о том, что хранится в БД
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //цикл для вытягивания данных о самолетах
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Plane plane = ds.getValue(Plane.class);

                    list.add("Самолет: " + plane.name);
                    list.add("Макс. скорость: " + plane.maxspeed + " км/ч");
                    list.add("Пассажиры: " + plane.passenger );
                    //te
                    list.add("\n\n");
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDataBase.addValueEventListener(valueEventListener); //добавили к БД слушателя
    }



}