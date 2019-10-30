package com.example.da_traloicauhoi.RoomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.da_traloicauhoi.RoomDatabase.Dao.DAO;
import com.example.da_traloicauhoi.RoomDatabase.Entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class LinhVucRoomDataBase extends RoomDatabase {
    public abstract DAO linhvucDao();

    private  static volatile LinhVucRoomDataBase INSTANCE;
    private static RoomDatabase.Callback sRoomDataBaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    public static LinhVucRoomDataBase getDataBase(final Context context){
        if (INSTANCE == null){
            synchronized (LinhVucRoomDataBase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LinhVucRoomDataBase.class,"db_me")
                            .build();
                }
            }
        }
        return  INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final DAO mDao;

        private PopulateDbAsync(LinhVucRoomDataBase db) {
            mDao = db.linhvucDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.DeleteAll();
            User user = new User(1,"Hello");
            mDao.Insert(user);
            user = new User(2,"ty ne");
            mDao.Insert(user);
            return null;
        }
    }

}
