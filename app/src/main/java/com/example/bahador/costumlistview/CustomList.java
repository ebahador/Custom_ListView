package com.example.bahador.costumlistview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by bahador on 12/9/17.
 */

class ItemsListAdapter extends BaseAdapter{

    private Context context;
    private List<Item> list;

    ItemsListAdapter(Context c, List<Item> l) {
        context = c;
        list = l;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private boolean isChecked(int position) {
        return list.get(position).checked;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        // reuse views
        MainActivity.ViewHolder viewHolder = new MainActivity.ViewHolder();
        if (rowView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.row, null);

            viewHolder.checkBox = rowView.findViewById(R.id.rowCheckBox);
            viewHolder.icon = rowView.findViewById(R.id.rowImageView);
            viewHolder.text = rowView.findViewById(R.id.rowTextView);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (MainActivity.ViewHolder) rowView.getTag();
        }

        viewHolder.icon.setImageDrawable(list.get(position).ItemDrawable);
        viewHolder.checkBox.setChecked(list.get(position).checked);

        final String itemStr = list.get(position).ItemString;
        viewHolder.text.setText(itemStr);

        viewHolder.checkBox.setTag(position);

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).checked = !list.get(position).isChecked();

            }
        });

        viewHolder.checkBox.setChecked(isChecked(position));

        return rowView;
    }
}
