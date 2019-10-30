package com.example.da_traloicauhoi.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.da_traloicauhoi.RoomDatabase.Dao.DAO;
import com.example.da_traloicauhoi.RoomDatabase.Entity.User;
import com.example.da_traloicauhoi.RoomDatabase.LinhVucRoomDataBase;

import java.util.List;

public class linhVucRepository {
    private DAO mLinhVucDao;
    private LiveData<List<User>> mAllLinhVucs;

    public linhVucRepository(Application application){
        LinhVucRoomDataBase db = LinhVucRoomDataBase.getDataBase(application);
        mLinhVucDao = db.linhvucDao();
        mAllLinhVucs = mLinhVucDao.getAlphabetizedLinhvucs();
    }

    public LiveData<List<User>> getAllLinhVucs(){
        return mAllLinhVucs;
    }
    public void Insert(User user){
        new insertAsyncTask(mLinhVucDao).execute(user);
    }
    private static class insertAsyncTask extends AsyncTask<User, Void, Void>{
        private DAO mAsyncTaskDao;

        public insertAsyncTask(DAO mLinhVucDao) {
            mAsyncTaskDao = mLinhVucDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mAsyncTaskDao.Insert(users[0]);
            return null;
        }
    }
}
