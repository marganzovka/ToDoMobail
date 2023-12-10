package com.example.tasksjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton addedButton, buttonSeting;
    TextView buttonTo;
    EditText nameField;
    String nameTask;
    ListView listView;
    ArrayList<String> arr = new ArrayList<>();
    Animation anim;
    ArrayAdapter<String> adapter;
    DbHelperTask dbHelperTask;
    DbHelperexEcutedTask dbHelperexEcutedTask;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addedButton = findViewById(R.id.addedButton);
        nameField = findViewById(R.id.nameField);
        listView = findViewById(R.id.listView);
        anim = AnimationUtils.loadAnimation(this, R.anim.animation);
        buttonTo = findViewById(R.id.buttonTo);
        dbHelperTask = new DbHelperTask(MainActivity.this, Const.NAME_COLUMN,null ,1);
        buttonSeting = findViewById(R.id.buttonSetting);





        // Добавление заданий
        addedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameTask = nameField.getText().toString();
                if (nameTask.equals(""))
                {
                    Toast.makeText(MainActivity.this, Const.TOAST_NOTNULL_EN, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dbHelperTask.AddTask(nameTask);
                    nameField.setText("");
                    loadTasksAndUpdateListView();

                }
            }
        });



        // Удаление и добавление
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                // Добавление
                dbHelperexEcutedTask =  new DbHelperexEcutedTask(MainActivity.this, Const.NAME_DATABASE_TASKECUTED,null ,2);
                nameTask = (String) listView.getItemAtPosition(position);
                dbHelperexEcutedTask.AddExecutedTask(nameTask);

                // Удаление
                dbHelperTask.deleteTaskByName(nameTask);
                loadTasksAndUpdateListView();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Alert alert = new Alert();
                String NameTask = (String) listView.getItemAtPosition(position);
                alert.createTwoButtonsAlertDialog(Const.ALERT_NAME_EN, NameTask, MainActivity.this, Const.HELPER_TASK, new Alert.OnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick() {
                        loadTasksAndUpdateListView();
                    }
                });

                return true;
            }

        });


        // Переход на новую активити
        Intent deletadAct = new Intent(this, EcutedActivity.class);
        buttonTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(deletadAct);
            }
        });


    }


    @Override
    protected void onResume() {
        loadTasksAndUpdateListView();
        super.onResume();
    }


    // Получение данных из БД и обновление ListView
    public void loadTasksAndUpdateListView() {
        ArrayList<String> tasks = dbHelperTask.getAllTasks();

        arr.clear();
        arr.addAll(tasks);

        adapter = new ArrayAdapter<>(MainActivity.this, R.layout.list, R.id.text, arr);
        listView.setAdapter(adapter);
    }


}
