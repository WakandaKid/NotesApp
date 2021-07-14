package com.giko.notesapp.adapters;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.giko.notesapp.R;
import com.giko.notesapp.entities.Note;
import com.giko.notesapp.listeners.NotesListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>{

    private List<Note> notes;

    private NotesListener notesListener;

    public NotesAdapter(List<Note> notes, NotesListener notesListener) {
        this.notes = notes;
        this.notesListener = notesListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_note,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NoteViewHolder holder, int position) {
        holder.setNote(notes.get(position));
        holder.layoutNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesListener.onNoteClicked(notes.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvSubtitle, tvDateTime;
        LinearLayout layoutNote;
        RoundedImageView rImageView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSubtitle = itemView.findViewById(R.id.tvSubtitle);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            layoutNote = itemView.findViewById(R.id.layoutNote);
            rImageView = itemView.findViewById(R.id.rImageNote);
        }

        public void setNote(Note note){
            tvTitle.setText(note.getTitle());

            if (note.getSubtitle().trim().isEmpty()){
                tvSubtitle.setVisibility(View.GONE);
            }else {
                tvSubtitle.setText(note.getSubtitle());
            }

            tvDateTime.setText(note.getDateTime());

            GradientDrawable gradientDrawable = (GradientDrawable) layoutNote.getBackground();

            if (note.getColor() != null){
                gradientDrawable.setColor(Color.parseColor(note.getColor()));
            }else {
                gradientDrawable.setColor(Color.parseColor("#333333"));
            }

            if (note.getImagePath() != null){
                rImageView.setImageBitmap(BitmapFactory.decodeFile(note.getImagePath()));
                rImageView.setVisibility(View.VISIBLE);
            }else {
                rImageView.setVisibility(View.GONE);
            }
        }
    }
}
