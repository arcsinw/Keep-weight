package com.arcsinw.keepweight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arcsinw.keepweight.model.Weight;

import java.util.List;

public class WeightAdapter extends ArrayAdapter<Weight> {
    private int resourceId;

    public WeightAdapter(@NonNull Context context, int resourceId, @NonNull List objects) {
        super(context, resourceId, objects);

        this.resourceId = resourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Weight weight = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView weightTextView = view.findViewById(R.id.weight);
        TextView timeTextView = view.findViewById(R.id.time);

        weightTextView.setText(String.valueOf(weight.getWeight()));
        timeTextView.setText(String.valueOf(weight.getCreateTime()));
        return view;
    }
}
