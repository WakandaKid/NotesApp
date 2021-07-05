package com.giko.notesapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.giko.notesapp.dao.NoteDao;
import com.giko.notesapp.entities.Note;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NotesDB extends RoomDatabase {

    private static NotesDB notesDB;

    public static synchronized NotesDB getDatabase(Context context){
        if (notesDB == null){
            notesDB = Room.databaseBuilder(
                    context,
                    NotesDB.class,
                    "notes_db"
            ).build();
        }

        return notesDB;
    }

    public abstract NoteDao noteDao();

}
