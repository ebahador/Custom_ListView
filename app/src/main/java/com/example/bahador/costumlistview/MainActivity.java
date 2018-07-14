package com.example.bahador.costumlistview;


import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static class ViewHolder {
        CheckBox checkBox;
        ImageView icon;
        TextView text;
    }

    private void initItems() {
        items = new ArrayList<>();

        TypedArray arrayDrawable = getResources().obtainTypedArray(R.array.resicon);
        TypedArray arrayText = getResources().obtainTypedArray(R.array.restext);

        for (int i = 0; i < arrayDrawable.length(); i++) {
            Drawable d = arrayDrawable.getDrawable(i);
            String s = arrayText.getString(i);
            Item item = new Item(d, s, false);
            items.add(item);
        }

        arrayDrawable.recycle();
        arrayText.recycle();
    }


    Button btnLookup;
    List<Item> items;
    ListView listView;
    ItemsListAdapter myItemsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        btnLookup = findViewById(R.id.lookup);

        initItems();
        myItemsListAdapter = new ItemsListAdapter(this, items);
        listView.setAdapter(myItemsListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this,
                        ((Item) (parent.getItemAtPosition(position))).ItemString,
                        Toast.LENGTH_LONG).show();
            }
        });

        btnLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder str = new StringBuilder("Check items:\n");

                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isChecked()) {
                        str.append(i).append("\n");
                    }
                }

                Toast.makeText(MainActivity.this,
                        str.toString(),
                        Toast.LENGTH_LONG).show();

            }
        });
    }

}