package com.example.simpletodolist_nodb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    List<Todo> lists;

    View.OnClickListener showModify;
    View.OnClickListener delete;
    View.OnClickListener detail;
    View.OnClickListener modify;
    View.OnClickListener cancelModify;


    ItemAdapter(List<Todo> lists, View.OnClickListener showModify, View.OnClickListener delete, View.OnClickListener detail, View.OnClickListener modify, View.OnClickListener cancelModify){

        this.lists = lists;
        this.showModify = showModify;
        this.delete = delete;
        this.detail = detail;
        this.modify = modify;
        this.cancelModify = cancelModify;
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

        Todo todo = lists.get(position);

        if(convertView == null && !todo.getModifyMode()){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.id = convertView.findViewById(R.id.item_todo__no);
            viewHolder.content = convertView.findViewById(R.id.item_todo__textViewContent);
            viewHolder.showModify = convertView.findViewById(R.id.item_todo__showModify);
            viewHolder.delete = convertView.findViewById(R.id.item_todo__delete);
            viewHolder.detail = convertView.findViewById(R.id.item_todo__detail);
            viewHolder.modify = convertView.findViewById(R.id.item_todo__modify);
            viewHolder.cancelModify = convertView.findViewById(R.id.item_todo__cancelModify);

            viewHolder.delete.setOnClickListener(this.delete);
            viewHolder.showModify.setOnClickListener(this.showModify);
            viewHolder.detail.setOnClickListener(this.detail);
            viewHolder.modify.setOnClickListener(this.modify);
            viewHolder.cancelModify.setOnClickListener(this.cancelModify);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }





        viewHolder.id.setTag(position);
        viewHolder.content.setTag(position);
        viewHolder.showModify.setTag(position);
        viewHolder.delete.setTag(position);
        viewHolder.detail.setTag(position);
        viewHolder.modify.setTag(position);
        viewHolder.cancelModify.setTag(position);

        viewHolder.id.setText(todo.getId() + "");
        viewHolder.content.setText(todo.getContent());

        if(todo.getModifyMode()){
            viewHolder.showModify.performClick();
        }
        else{
            viewHolder.cancelModify.performClick();
        }

        return convertView;
    }

    class ViewHolder {
        TextView id;
        TextView content;
        Button showModify;
        Button delete;
        Button detail;
        Button modify;
        Button cancelModify;
    }
}
