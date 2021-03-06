package com.fit5046.paindiary.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fit5046.paindiary.dao.PainRecordDAO;
import com.fit5046.paindiary.entity.PainRecord;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PainRecord.class}, version = 4, exportSchema = false)
public abstract class PainRecordDatabase extends RoomDatabase {

    public abstract PainRecordDAO painRecordDAO();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static PainRecordDatabase INSTANCE;
    public static synchronized PainRecordDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PainRecordDatabase.class, "PainRecordDatabase")
                    .fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
