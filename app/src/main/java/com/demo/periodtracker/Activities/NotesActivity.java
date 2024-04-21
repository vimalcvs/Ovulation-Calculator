package com.demo.periodtracker.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.demo.periodtracker.Adapters.DiaryNotesRecyclerAdapter;

import com.demo.periodtracker.Databases.Entities.Note;
import com.demo.periodtracker.Databases.NoteHandler;
import com.demo.periodtracker.R;
import com.demo.periodtracker.Utils.Utils;
import com.demo.periodtracker.databinding.ActivityNotesBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


public class NotesActivity extends AppCompatActivity implements DiaryNotesRecyclerAdapter.OnLongItemCLickedListener {
    ActivityNotesBinding binding;
    NoteHandler handler;
    boolean savedNotes = false;
    DatePickerDialog.OnDateSetListener setListener;

    public static void lambda$onItemLongClicked$7(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityNotesBinding inflate = ActivityNotesBinding.inflate(getLayoutInflater());
        binding = inflate;
        setContentView(inflate.getRoot());

      

        setCurrentDate();
        binding.parentLayout.setOnClickListener(NotesActivity.this::m112x3d0b51f4);
        binding.saveBtn.setOnClickListener(NotesActivity.this::m113x261316f5);
        showNotesList();
        binding.cancelBtn.setOnClickListener(NotesActivity.this::m114xf1adbf6);
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesActivity.this.m115xf822a0f7(view);
            }
        });
        binding.dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesActivity.this.m116xe12a65f8(view);
            }
        });
    }

    public void m112x3d0b51f4(View view) {
        Utils.hideKeyboard(this);
    }

    public void m113x261316f5(View view) {
        Utils.hideKeyboard(this);
        if (binding.dairyNoteInp.getText().toString().isEmpty()) {
            binding.dairyNoteInp.setError(getResources().getString(R.string.please_fill_all_fields));
        } else {
            addNote();
        }
    }

    public void m114xf1adbf6(View view) {
        Utils.hideKeyboard(this);
        showNotesList();
    }

    public void m115xf822a0f7(View view) {
        binding.inputArea.setVisibility(View.VISIBLE);
        binding.detailArea.setVisibility(View.GONE);
        binding.addBtn.setVisibility(View.GONE);
    }

    public void m116xe12a65f8(View view) {
        String[] split = binding.dateTv.getText().toString().split("/");
        String trim = split[0].trim();
        String trim2 = split[1].trim();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, 16973940, this.setListener, Integer.parseInt(split[2].trim()), Integer.parseInt(trim2) - 1, Integer.parseInt(trim));
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        datePickerDialog.show();
    }

    void setCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(5);
        binding.dateTv.setText(i2 + "/" + (calendar.get(2) + 1) + "/" + i);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.handler = new NoteHandler(this);
        showNotesList();
    }

    private void addNote() {
        this.handler.addNote(new Note(binding.dateTv.getText().toString(), binding.dairyNoteInp.getText().toString()));
        binding.inputArea.setVisibility(View.GONE);
        binding.detailArea.setVisibility(View.VISIBLE);
        binding.addBtn.setVisibility(View.VISIBLE);
        binding.dairyNoteInp.setText("");
        showNotesList();
        Toast.makeText(this, getResources().getString(R.string.saved_successfully), Toast.LENGTH_SHORT).show();
        this.savedNotes = true;
        Utils.hideKeyboard(this);
    }

    private void showNotesList() {
        this.setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                NotesActivity.this.m118x1de85726(datePicker, i, i2, i3);
            }
        };
        List<Note> arrayList = new ArrayList<>();
        NoteHandler noteHandler = this.handler;
        if (noteHandler != null) {
            arrayList = noteHandler.getAllNotes();
        }
        if (arrayList.size() > 0) {
            binding.diaryNotesRecycler.setLayoutManager(new LinearLayoutManager(this));
            Collections.reverse(arrayList);
            binding.diaryNotesRecycler.setAdapter(new DiaryNotesRecyclerAdapter(arrayList, this, this));
            binding.diaryNotesRecycler.scrollToPosition(0);
            binding.diaryNotesRecycler.setVisibility(View.VISIBLE);
            binding.emptyTv.setVisibility(View.GONE);
            binding.inputArea.setVisibility(View.GONE);
            binding.detailArea.setVisibility(View.VISIBLE);
            binding.addBtn.setVisibility(View.VISIBLE);
            return;
        }
        binding.addBtn.setVisibility(View.GONE);
        binding.detailArea.setVisibility(View.GONE);
        binding.inputArea.setVisibility(View.VISIBLE);
        binding.emptyTv.setVisibility(View.VISIBLE);
        binding.diaryNotesRecycler.setVisibility(View.GONE);
    }

    public void m118x1de85726(DatePicker datePicker, int i, int i2, int i3) {
        int i4 = i2 + 1;
        binding.dateTv.setText(i3 + "/" + i4 + "/" + i);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i4 + (-1));
        calendar.set(5, i3);
    }

    @Override
    public void onItemLongClicked(final int i) {
        new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.delete_confirmation)).setMessage(getResources().getString(R.string.are_you_sure_you_want_to_delete_this_history)).setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i2) {
                NotesActivity.this.m117xbca8c07e(i, dialogInterface, i2);
            }
        }).setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i2) {
                NotesActivity.lambda$onItemLongClicked$7(dialogInterface, i2);
            }
        }).show();
    }

    public void m117xbca8c07e(int i, DialogInterface dialogInterface, int i2) {
        this.handler.deleteNotes(String.valueOf(i));
        showNotesList();
        Toast.makeText(this, getResources().getString(R.string.deleted_successfully), Toast.LENGTH_SHORT).show();
    }
}
