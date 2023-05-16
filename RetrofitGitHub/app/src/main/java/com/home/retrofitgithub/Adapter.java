package com.home.retrofitgithub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.home.retrofitgithub.network.GitResp;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.GitViewHolder> {


    List<GitResp> repositories;

    public Adapter() {
        repositories = new ArrayList<>();
    }

    @NonNull
    @Override
    public GitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_git, parent, false);
        return new GitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitViewHolder holder, int position) {
        holder.bind(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return (repositories != null && repositories.size() > 0) ? repositories.size() : 0;
    }

    static class GitViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public GitViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

        public void bind(GitResp repositorie){
            textView.setText(repositorie.getName());
        }
    }

    public void setRepositories(List<GitResp> repositories){
        this.repositories = repositories;
        notifyDataSetChanged();
    }
}
