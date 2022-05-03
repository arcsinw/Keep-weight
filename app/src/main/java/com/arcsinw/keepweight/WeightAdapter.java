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

        View listItemView;
        WeightViewHolder weightViewHolder;

        if (null != convertView) {
            listItemView = convertView;
            weightViewHolder = (WeightViewHolder) convertView.getTag();
        } else {
            listItemView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

            weightViewHolder = new WeightViewHolder();
            weightViewHolder.weightTextView = listItemView.findViewById(R.id.weight);
            weightViewHolder.timeTextView = listItemView.findViewById(R.id.time);

            listItemView.setTag(weightViewHolder);
        }
        weightViewHolder.weightTextView.setText(String.valueOf(weight.getWeight()));
        weightViewHolder.timeTextView.setText(String.valueOf(weight.getCreateTime()));
        return listItemView;
    }


    class WeightViewHolder {
        TextView weightTextView;

        TextView timeTextView;
    }
}
