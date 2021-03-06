package com.arcsinw.keepweight;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arcsinw.keepweight.model.Weight;
import com.arcsinw.keepweight.utils.DataBaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_menu_item:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHelper = new DataBaseHelper(this, "keep_weight.db", null, 1);
        List<Weight> weights = dataBaseHelper.getWeights();
        weightEditText = findViewById(R.id.weightEditText);

        WeightAdapter adapter = new WeightAdapter(weights);
        RecyclerView weightRecyclerView = (RecyclerView) findViewById(R.id.weightListView);
        weightRecyclerView.setLayoutManager(new LinearLayoutManager(this) {{
            setOrientation(RecyclerView.VERTICAL);
        }});
        weightRecyclerView.setAdapter(adapter);

//        weightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                WeightAdapter weightAdapter = (WeightAdapter) adapterView.getAdapter();
//                Weight weight = weightAdapter.getItem(i);
//
//                Toast.makeText(MainActivity.this, String.valueOf(weight.getWeight()), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private EditText weightEditText;

    public void addWeightBtnClick(View view) {
        double weight = Double.parseDouble(weightEditText.getText().toString());

        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("weight", weight);
        values.put("create_time", System.currentTimeMillis());
        database.insert("weight", null, values);
        Toast.makeText(MainActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();

        dataBaseHelper = new DataBaseHelper(this, "keep_weight.db", null, 1);
        List<Weight> weights = dataBaseHelper.getWeights();
        weightEditText = findViewById(R.id.weightEditText);

        WeightAdapter adapter = new WeightAdapter(weights);
        RecyclerView weightRecyclerView = (RecyclerView) findViewById(R.id.weightListView);
        weightRecyclerView.setLayoutManager(new LinearLayoutManager(this) {{
            setOrientation(RecyclerView.VERTICAL);
        }});
        weightRecyclerView.setAdapter(adapter);
    }
}