package com.example.da_traloicauhoi.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.da_traloicauhoi.Repository.linhVucRepository;
import com.example.da_traloicauhoi.RoomDatabase.Entity.User;

import java.util.List;

public class LinhVucViewModel extends AndroidViewModel {
    private linhVucRepository mReposity;

    private LiveData<List<User>> mAllWords;

    public LinhVucViewModel(@NonNull Application application) {
        super(application);
        mReposity = new linhVucRepository(application);
        mAllWords = mReposity.getAllLinhVucs();
    }

    LiveData<List<User>> getmAllWords(){
        return mAllWords;
    }

    public void insert(User user){mReposity.Insert(user);}


}
