package com.example.planefirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //глобальное объявление
    EditText edName, edMaxSpeed,edPassenger;

    private DatabaseReference mDataBase; //доступ к базе данных
    private String Plane_Key = "Plane"; //для отображения вводимых данных внутри группы Plane

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //поиск 3-ex EditText на экране
        edName = findViewById(R.id.edName);
        edMaxSpeed = findViewById(R.id.edMaxSpeed);
        edPassenger = findViewById(R.id.edPassenger);
        mDataBase = FirebaseDatabase.getInstance().getReference(Plane_Key);
    }

    //метод нажатия на кнопку СОХРАНИТЬ
    public void onClickSave(View view) {
        String id = mDataBase.getKey(); //id генерируется при добавлении данных в базу данных
        String name = edName.getText().toString(); //получение с EditText названия самолета
        String maxspeed = edMaxSpeed.getText().toString(); //получение с EditText макс. скорости самолета
        String passenger =edPassenger.getText().toString(); //получение c EditText вместимости пассажиров в самолете

        Plane newPlane = new Plane(id, name, maxspeed, passenger); //экземпляр класса Plane, которому передаем поля для последующего добавления введенных данных в базу данных
        //проверка условия на пустоту в полях
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(maxspeed) && !TextUtils.isEmpty(passenger)) {
            mDataBase.push().setValue(newPlane); //добавление введенных данных в базу данных
            Toast.makeText(this, "Данные успешно добавлены!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Поля должны быть заполнены!!!", Toast.LENGTH_SHORT).show(); //всплывающее сообщение при нажатии на кнопку
        }
    }

    //метод нажатия на кнопку ВЫВЕСТИ
    public void onClickConclusion(View view){
        Intent intent = new Intent(MainActivity.this,MainActivity2.class); //при нажатии на кнопку, переходим на 2 активити
        startActivity(intent);
        Toast.makeText(this,"Вы перешли на 2 активити!", Toast.LENGTH_SHORT).show(); //всплывающее сообщение при нажатии на кнопку
    }
}