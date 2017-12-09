package com.example.bahador.costumlistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    public boolean isChecked(int position) {
        return list.get(position).checked;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        // reuse views
        MainActivity.ViewHolder viewHolder = new MainActivity.ViewHolder();
        if (rowView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.row, null);

            viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.rowCheckBox);
            viewHolder.icon = (ImageView) rowView.findViewById(R.id.rowImageView);
            viewHolder.text = (TextView) rowView.findViewById(R.id.rowTextView);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (MainActivity.ViewHolder) rowView.getTag();
        }

        viewHolder.icon.setImageDrawable(list.get(position).ItemDrawable);
        viewHolder.checkBox.setChecked(list.get(position).checked);

        final String itemStr = list.get(position).ItemString;
        viewHolder.text.setText(itemStr);

        viewHolder.checkBox.setTag(position);

            /*
            viewHolder.checkBox.setOnCheckedChangeListener(
                    new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    list.get(position).checked = b;

                    Toast.makeText(getApplicationContext(),
                            itemStr + "onCheckedChanged\nchecked: " + b,
                            Toast.LENGTH_LONG).show();
                }
            });
            */

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean newState = !list.get(position).isChecked();
                list.get(position).checked = newState;
                //toast.getAppContext(itemStr, newState);

            }
        });

        viewHolder.checkBox.setChecked(isChecked(position));

        return rowView;
    }
}
