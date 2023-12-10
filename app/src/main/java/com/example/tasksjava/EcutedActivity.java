package com.example.tasksjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

public class EcutedActivity extends AppCompatActivity {

    TextView buttonTo;
    ListView listView;
    ArrayAdapter<String> adapter;
    LinkedList<String> arr = new LinkedList<>();
    DbHelperexEcutedTask dbHelperexEcutedTask;
    DbHelperTask dbHelperTask;
    ImageButton buttonSeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleted);


        listView = findViewById(R.id.listView);
        buttonTo = findViewById(R.id.buttonTo);




        dbHelperexEcutedTask = new DbHelperexEcutedTask(EcutedActivity.this, Const.NAME_DATABASE_TASKECUTED,null ,2);
        dbHelperTask = new DbHelperTask(EcutedActivity.this, Const.NAME_COLUMN,null ,1);
        loadTasksAndUpdateListView();


        // Переход на main
        buttonTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Удаление и перенос
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Добавление
                String nameTask = (String) listView.getItemAtPosition(position);
                dbHelperTask.AddTask(nameTask);


                //Удаление
                dbHelperexEcutedTask.deleteTaskByName(nameTask);
                loadTasksAndUpdateListView();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Alert alert = new Alert();
                String NameTask = (String) listView.getItemAtPosition(position);
                alert.createTwoButtonsAlertDialog(Const.ALERT_NAME_EN, NameTask, EcutedActivity.this, Const.HELPER_ECUTED, new Alert.OnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick() {
                        loadTasksAndUpdateListView();
                    }
                });
                return true;
            }
        });



    }



    @Override
    protected void onResume() {
        loadTasksAndUpdateListView();
        super.onResume();
    }

    private void loadTasksAndUpdateListView() {
        ArrayList<String> tasks = dbHelperexEcutedTask.getAllTasks();

        arr.clear();
        arr.addAll(tasks);

        adapter = new ArrayAdapter<>(EcutedActivity.this, R.layout.list_ecuted, R.id.text, arr);
        listView.setAdapter(adapter);

        for (String task : arr) {
            Log.d("DeletedActivity", "Task: " + task);
        }

    }
}