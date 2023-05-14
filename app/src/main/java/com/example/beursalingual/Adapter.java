package com.example.beursalingual;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<String> wordsList;
    private Context context;

    public Adapter(Context context, List<String> wordsList){
        this.inflater= LayoutInflater.from(context);
        this.wordsList = wordsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_custom_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String word = wordsList.get(position);

        // load text into individual textViews
        holder.inputtedWord.setText(word);

    }

    @Override
    public int getItemCount() {
        return wordsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView inputtedWord;
        ConstraintLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            inputtedWord = itemView.findViewById(R.id.textInputToCheck);
            parentLayout = itemView.findViewById(R.id.recyclerview);
        }
    }
}
