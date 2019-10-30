package com.example.da_traloicauhoi.RoomDatabase.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Fts3;
import androidx.room.PrimaryKey;

@Fts3 //Yeu cau kha nang tuong thich voi Sqlite
@Entity(tableName = "linh_vuc")
public class User {
    @PrimaryKey
    @NonNull
    public int id;

    public String ten_linh_vuc;

    public User(int id,String ten_linh_vuc) {
        this.id = id ;
        this.ten_linh_vuc = ten_linh_vuc;
    }
}
