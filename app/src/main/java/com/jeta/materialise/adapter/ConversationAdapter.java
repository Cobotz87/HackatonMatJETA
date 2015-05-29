package com.jeta.materialise.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jeta.materialise.JETAapp;
import com.jeta.materialise.matjeta.MainActivity;
import com.jeta.materialise.matjeta.R;
import com.jeta.materialise.model.Message;

import opennlp.maxent.Main;

/**
 * Created by ArifHBtz on 5/29/2015.
 */
public class ConversationAdapter extends BaseAdapter{

    @Override
    public int getCount() {
        return JETAapp.getMessageManager().getMessages().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) parent.getContext().
                getSystemService(parent.getContext().LAYOUT_INFLATER_SERVICE);
        View msgLayout = inflater.inflate(R.layout.layout_message, parent, false);

        if(JETAapp.getMessageManager().getMessages().isEmpty())
            return msgLayout;

        TextView tvOwnerName = (TextView) msgLayout.findViewById(R.id.tv_msg_owner);
        TextView tvMsgContent = (TextView) msgLayout.findViewById(R.id.tv_msg_content);

        Message currMessage = JETAapp.getMessageManager().getMessages().get(position);
        tvOwnerName.setText(currMessage.getOwnerName());
        tvMsgContent.setText(currMessage.getMessage());

        return msgLayout;
    }
}
