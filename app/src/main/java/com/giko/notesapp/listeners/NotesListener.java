package com.giko.notesapp.listeners;

import com.giko.notesapp.entities.Note;

public interface NotesListener {
    void onNoteClicked(Note note, int position);
}
