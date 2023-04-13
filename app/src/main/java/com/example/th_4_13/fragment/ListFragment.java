package com.example.th_4_13.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.th_4_13.DatabaseHandler;
import com.example.th_4_13.R;
import com.example.th_4_13.adapter.TaskListAdapter;
import com.example.th_4_13.model.Task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    TaskListAdapter taskListAdapter;
    RecyclerView recyclerListTask;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerListTask = view.findViewById(R.id.recyclerListTask);

//        try {
//            mList.add(new Task(1, "Nấu cơm", "Cần nấu cơm", "New",
//                    "14/4/2023", false));
//            mList.add(new Task(2, "Nấu cơm 2", "Cần nấu cơm", "New",
//                    "15/4/2023", false));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        DatabaseHandler db = new DatabaseHandler(view.getContext());
        try {
            db.addTask(new Task(0, "Nấu cơm123", "Cần nấu cơm", "New",
                    "14/4/2023", false));
            db.addTask(new Task(0, "Nấu cơm 2", "Cần nấu cơm", "New",
                    "15/4/2023", false));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Task> mList = null;
        try {
            mList = db.getAllTasks();
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        String[] st = new String[mList.size()];
//        int k = 0;
//        for (Task i : mList) {
//            st[k++] = i.getId() + " " + i.getName();
//        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                view.getContext(), LinearLayoutManager.VERTICAL, false);
        taskListAdapter = new TaskListAdapter(view.getContext(), mList);
        recyclerListTask.setLayoutManager(linearLayoutManager);
        recyclerListTask.setAdapter(taskListAdapter);
        return view;
    }
}