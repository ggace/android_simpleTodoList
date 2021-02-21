package com.example.simpletodolist_nodb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<com.example.simpletodolist_nodb.Todo> lists;
    EditText todo;
    ItemAdapter adapter;

    int lastIndex = 1;

    void make100lists(){
        for(int i = 0; i < 100; i++){
            lists.add(0, new Todo(i+1, (i+1) + "", false) );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //초기화
        lists = new ArrayList<>();

        make100lists();
        //view 가녀오기
        ListView listView = findViewById(R.id.main_activity__listViewTodo);
        todo = findViewById(R.id.activity_main__editTextTodo);
        //어댑터 설정

        View.OnClickListener showModify = v -> {
            View parent = (View)v.getParent();

            TextView textViewContent = parent.findViewById(R.id.item_todo__textViewContent);
            EditText editTextContent = parent.findViewById(R.id.item_todo__editTextContent);

            Button btnModify =parent.findViewById(R.id.item_todo__modify);
            Button btnDelete =parent.findViewById(R.id.item_todo__delete);
            Button btnDetail =parent.findViewById(R.id.item_todo__detail);
            Button btnShowModify =parent.findViewById(R.id.item_todo__showModify);
            Button btnCancelModify =parent.findViewById(R.id.item_todo__cancelModify);

            textViewContent.setVisibility(View.GONE);
            btnShowModify.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
            btnDetail.setVisibility(View.GONE);
            editTextContent.setVisibility(View.VISIBLE);
            btnModify.setVisibility(View.VISIBLE);
            btnCancelModify.setVisibility(View.VISIBLE);

            lists.get((int)v.getTag()).setModifyMode(true);

            editTextContent.setText(textViewContent.getText().toString());
        };

        View.OnClickListener delete = v -> {

            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            lists.remove((int)v.getTag());
                            adapter.notifyDataSetChanged();
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };

            new AlertDialog.Builder(this)
                    .setMessage("정말 삭제하시겠습니까?")
                    .setPositiveButton("예", onClickListener).setNegativeButton("아니오", onClickListener).show();


        };

        View.OnClickListener detail = v -> {
            Intent intent = new Intent(this, DetailActivity.class);
            Todo todo = lists.get((int)v.getTag());
            intent.putExtra("id", todo.getId());
            intent.putExtra("content", todo.getContent());
            startActivity(intent);
        };

        View.OnClickListener modify = v -> {
            View parent = (View)v.getParent();

            TextView textViewContent = parent.findViewById(R.id.item_todo__textViewContent);
            EditText editTextContent = parent.findViewById(R.id.item_todo__editTextContent);

            Button btnModify =parent.findViewById(R.id.item_todo__modify);
            Button btnDelete =parent.findViewById(R.id.item_todo__delete);
            Button btnDetail =parent.findViewById(R.id.item_todo__detail);
            Button btnShowModify =parent.findViewById(R.id.item_todo__showModify);
            Button btnCancelModify =parent.findViewById(R.id.item_todo__cancelModify);
            if(editTextContent.getText().toString().trim().length() == 0){
                Toast.makeText(this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            lists.get((int)v.getTag()).setContent(editTextContent.getText().toString());
            adapter.notifyDataSetChanged();

            textViewContent.setVisibility(View.VISIBLE);
            btnShowModify.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            btnDetail.setVisibility(View.VISIBLE);
            editTextContent.setVisibility(View.GONE);
            btnModify.setVisibility(View.GONE);
            btnCancelModify.setVisibility(View.GONE);

            lists.get((int)v.getTag()).setModifyMode(false);

        };

        View.OnClickListener cancelModify = v -> {
            View parent = (View)v.getParent();

            TextView textViewContent = parent.findViewById(R.id.item_todo__textViewContent);
            EditText editTextContent = parent.findViewById(R.id.item_todo__editTextContent);

            Button btnModify =parent.findViewById(R.id.item_todo__modify);
            Button btnDelete =parent.findViewById(R.id.item_todo__delete);
            Button btnDetail =parent.findViewById(R.id.item_todo__detail);
            Button btnShowModify =parent.findViewById(R.id.item_todo__showModify);
            Button btnCancelModify =parent.findViewById(R.id.item_todo__cancelModify);

            textViewContent.setVisibility(View.VISIBLE);
            btnShowModify.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            btnDetail.setVisibility(View.VISIBLE);
            editTextContent.setVisibility(View.GONE);
            btnModify.setVisibility(View.GONE);
            btnCancelModify.setVisibility(View.GONE);

            lists.get((int)v.getTag()).setModifyMode(false);
        };

        adapter = new ItemAdapter(lists, showModify, delete, detail, modify, cancelModify);
        listView.setAdapter(adapter);

        //each list onclick 설정
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, DetailActivity.class);
            Todo todo = lists.get(position);
            intent.putExtra("id", todo.getId());
            intent.putExtra("content", todo.getContent());
            startActivity(intent);
        });

    }

    public void btnAddTodoClicked(View view) {

        String todoText = todo.getText().toString();

        if (todoText.trim().length() == 0){
            Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }


        lists.add(0, new Todo(lastIndex++, todoText, false));
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