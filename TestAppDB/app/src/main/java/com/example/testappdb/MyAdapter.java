package com.example.testappdb;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    KeyService keyService = new KeyService();

    Context context;
    ArrayList<Description> list;
    Integer score = 0;

    public MyAdapter(Context context, ArrayList<Description> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Description description = list.get(position);
        holder.tvName.setText(description.getName());
        switch (description.getName()) {
            case "Гипертимность":
                score = keyService.getHyperthymicity() * 3;
                holder.tvScore.setText("(" + score + "/24)");
                break;
            case "Дистимность":
                score = keyService.getDysthymicity() * 3;
                holder.tvScore.setText("(" + score + "/24)");
                break;
            case "Циклотимность":
                score = keyService.getCyclothymicity() * 3;
                holder.tvScore.setText("(" + score + "/24)");
                break;
            case "Возбудимость":
                score = keyService.getExcitability() * 3;
                holder.tvScore.setText("(" + score + "/24)");
                break;
            case "Застревание":
                score = keyService.getJamming() * 2;
                holder.tvScore.setText("(" + score + "/24)");
                break;
            case "Эмотивность":
                score = keyService.getEmotivity() * 3;
                holder.tvScore.setText("(" + score + "/24)");
                break;
            case "Экзальтированность":
                score = keyService.getExaltation() * 6;
                holder.tvScore.setText("(" + score + "/24)");
                break;
            case "Тревожность":
                score = keyService.getAnxiety() * 3;
                holder.tvScore.setText("(" + score + "/24)");
                break;
            case "Педантичность":
                score = keyService.getPedantry() * 2;
                holder.tvScore.setText("(" + score + "/24)");
                break;
            case "Демонстративность":
                score = keyService.getDemonstrativeness() * 2;
                holder.tvScore.setText("(" + score + "/24)");
                break;
        };
        Log.i("Score","--------------------------------");
        Log.i("Score","score:" + score);
        Log.i("Score","--------------------------------");
        if (score <= 6){
            holder.tvDegree.setText("(Низкое)");
            holder.tvDescription.setText((description.getLow()));
        }
        else if (score <= 18){
            holder.tvDegree.setText("(Среднее)");
            holder.tvDescription.setText((description.getMedium()));
        }
        else{
            holder.tvDegree.setText("(Высокое)");
            holder.tvDescription.setText((description.getHigh()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvName,tvScore,tvDegree,tvDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvDegree = itemView.findViewById(R.id.tvDegree);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
