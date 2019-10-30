package com.example.da_traloicauhoi.RoomDatabase.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.da_traloicauhoi.RoomDatabase.Entity.User;

import java.util.List;

@Dao
public interface DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)//khi gặp id trùng sẽ bỏ qua
    void Insert(User word);

    @Query("DELETE FROM linh_vuc")
    void DeleteAll();

    @Query("SELECT * FROM linh_vuc")
    LiveData<List<User>> getAlphabetizedLinhvucs();
}
