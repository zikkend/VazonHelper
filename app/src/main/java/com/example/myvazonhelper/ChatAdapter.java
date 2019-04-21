package com.example.myvazonhelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class  ChatAdapter extends ArrayAdapter<ChatMessage> {
    public ChatAdapter( Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_mesenger, null);

            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.messageUser = (TextView) convertView.findViewById(R.id.nickuser);
        viewHolder.messageText = (TextView) convertView.findViewById(R.id.message);
        viewHolder.messageTime = (TextView) convertView.findViewById(R.id.messageTime);

        ChatMessage item = getItem(position);
        if(item != null){
            viewHolder.messageUser.setText(item.getMessageUser());
            viewHolder.messageText.setText(item.getMessageText());
            viewHolder.messageTime.setText(item.getMessageTime());
        }

        return convertView;
    }

    private static class ViewHolder{
        TextView messageText;
        TextView messageUser;
        TextView messageTime;
    }
}


