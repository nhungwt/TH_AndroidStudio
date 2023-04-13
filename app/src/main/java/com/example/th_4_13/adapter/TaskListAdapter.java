package com.example.th_4_13.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th_4_13.R;
import com.example.th_4_13.model.Task;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    Context context;
    List<Task> mList;

    public TaskListAdapter(Context context, List<Task> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = mList.get(position);
        holder.task_id.setText("ID: " + Integer.toString(task.getId()));
        holder.task_name.setText("  Name: " + task.getName());
        holder.task_deadline.setText("Deadline: " + task.getDeadline());
        holder.task_content.setText("  " + task.getContent());
        holder.task_status.setText("Status: " + task.getStatus());
        holder.task_colaboration.setText(task.getColaboration() ? "Colab: " + "Làm nhóm" : "Colab: " + "Cá nhân");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView task_id, task_name, task_deadline,
                task_content, task_status, task_colaboration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            task_id = itemView.findViewById(R.id.task_id);
            task_name = itemView.findViewById(R.id.task_name);
            task_deadline = itemView.findViewById(R.id.task_deadline);
            task_content = itemView.findViewById(R.id.task_content);
            task_status = itemView.findViewById(R.id.task_status);
            task_colaboration = itemView.findViewById(R.id.task_colaboration);
        }
    }
}
