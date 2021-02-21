package com.example.simpletodolist_nodb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<com.example.simpletodolist_nodb.Todo> lists;
    EditText todo;
    ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //초기화
        lists = new ArrayList<>();;
        //view 가녀오기
        ListView listView = findViewById(R.id.main_activity__listViewTodo);
        todo = findViewById(R.id.activity_main__editTextTodo);
        //어댑터 설정
        adapter = new ItemAdapter(lists);
        listView.setAdapter(adapter);

    }

    public void btnAddTodoClicked(View view) {

        String todoText = todo.getText().toString();

        if (todoText.isEmpty()){
            Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        lists.add(0, new Todo(lists.size()+1, todoText));
        todo.setText("");

        adapter.notifyDataSetChanged();
    }

    public void btnDeleteAllTodosClicked(View view) {

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        lists.clear();
                        adapter.notifyDataSetChanged();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        new AlertDialog.Builder(this)
                .setMessage("정말 모두 삭제하시겠습니까?")
                .setPositiveButton("예", onClickListener).setNegativeButton("아니오", onClickListener).show();


    }

    public void btnFinishAppClicked(View view) {
        finish();
    }
}