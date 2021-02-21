package com.example.simpletodolist_nodb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    List<Todo> lists;

    ItemAdapter(List<Todo> lists){
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lists.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.id = convertView.findViewById(R.id.item_todo__no);
            viewHolder.content = convertView.findViewById(R.id.item_todo__content);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Todo todo = lists.get(position);

        viewHolder.id.setText(todo.getId() + "");
        viewHolder.content.setText(todo.getContent());



        return convertView;
    }

    class ViewHolder {
        TextView id;
        TextView content;
    }
}
