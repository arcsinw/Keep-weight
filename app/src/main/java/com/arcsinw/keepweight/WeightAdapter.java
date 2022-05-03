package com.arcsinw.keepweight;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arcsinw.keepweight.model.Weight;

import java.util.List;

public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.WeightViewHolder> {
    private List<Weight> weights;

    public WeightAdapter(List<Weight> weights) {
        this.weights = weights;
    }

    @NonNull
    @Override
    public WeightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weight_list_item, parent, false);
        WeightViewHolder viewHolder = new WeightViewHolder(listItemView);
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Weight weight = weights.get(position);
                Toast.makeText(view.getContext(), String.valueOf(weight.getWeight()), Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeightViewHolder holder, int position) {
        Weight weight = weights.get(position);
        holder.weightTextView.setText(String.valueOf(weight.getWeight()));
        holder.timeTextView.setText(String.valueOf(weight.getCreateTime()));
    }

    @Override
    public int getItemCount() {
        return this.weights.size();
    }


    static class WeightViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView weightTextView;
        TextView timeTextView;

        public WeightViewHolder(View view) {
            super(view);

            itemView = view;
            weightTextView = view.findViewById(R.id.weight);
            timeTextView = view.findViewById(R.id.time);
        }
    }
}
