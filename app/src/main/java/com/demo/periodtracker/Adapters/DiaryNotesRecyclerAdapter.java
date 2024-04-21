package com.demo.periodtracker.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.demo.periodtracker.Activities.DiaryNotesPreviewActivity;
import com.demo.periodtracker.Databases.Entities.Note;
import com.demo.periodtracker.Databases.Params;
import com.demo.periodtracker.R;

import java.util.List;


public class DiaryNotesRecyclerAdapter extends RecyclerView.Adapter<DiaryNotesRecyclerAdapter.ViewHolder> {
    Activity activity;
    OnLongItemCLickedListener listener;
    List<Note> notesList;


    public DiaryNotesRecyclerAdapter(List<Note> list, OnLongItemCLickedListener onLongItemCLickedListener, Activity activity) {
        this.notesList = list;
        this.listener = onLongItemCLickedListener;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.diary_notes_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.dateTv.setText(this.notesList.get(i).getDate());
        viewHolder.noteTv.setText(this.notesList.get(i).getNote());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaryNotesRecyclerAdapter.this.m125xd130109f(i, view);
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return DiaryNotesRecyclerAdapter.this.m126xefea4860(i, view);
            }
        });
    }

    public void m125xd130109f(int i, View view) {
        Intent intent = new Intent(this.activity, DiaryNotesPreviewActivity.class);
        intent.putExtra(Params.KEY_NOTE_ID, this.notesList.get(i).getId());
        this.activity.startActivity(intent);
    }

    public boolean m126xefea4860(int i, View view) {
        this.listener.onItemLongClicked(this.notesList.get(i).getId());
        return true;
    }

    @Override
    public int getItemCount() {
        return this.notesList.size();
    }

    public interface OnLongItemCLickedListener {
        void onItemLongClicked(int i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTv;
        TextView noteTv;

        public ViewHolder(View view) {
            super(view);
            this.dateTv = view.findViewById(R.id.dateRecyclerItem);
            this.noteTv = view.findViewById(R.id.notesDescTv);
        }
    }
}
