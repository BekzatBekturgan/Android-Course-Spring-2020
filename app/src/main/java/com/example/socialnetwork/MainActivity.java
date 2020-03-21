package com.example.socialnetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<News> news = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        // создаем адаптер
        DataAdapter adapter = new DataAdapter(this, news);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData(){

        news.add(new News(R.drawable.icn_user, "Photo", "8 минут назад", "№1 фото", R.drawable.vk1));
        news.add(new News(R.drawable.icn_user, "Photo", "8 минут назад", "№1 фото", R.drawable.vk2));
        news.add(new News(R.drawable.icn_user, "Photo", "8 минут назад", "№1 фото", R.drawable.vk3));
        news.add(new News(R.drawable.icn_user, "Photo", "8 минут назад", "№1 фото", R.drawable.vk4));
        news.add(new News(R.drawable.icn_user, "Photo", "8 минут назад", "№1 фото", R.drawable.vk5));
        news.add(new News(R.drawable.icn_user, "Photo", "8 минут назад", "№1 фото", R.drawable.vk6));
        news.add(new News(R.drawable.icn_user, "Photo", "8 минут назад", "№1 фото", R.drawable.vk7));
        news.add(new News(R.drawable.icn_user, "Photo", "8 минут назад", "№1 фото", R.drawable.vk8));

    }
}
