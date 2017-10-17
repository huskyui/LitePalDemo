package com.example.android.litepaltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDatabase = (Button)findViewById(R.id.create_database);//create
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
                Log.i("database","success");
            }
        });

        Button addData = (Button)findViewById(R.id.add_data);//add
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("the Da vinci code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("UNKNOWN");
                book.save();
            }
        });

        Button updateData = (Button)findViewById(R.id.update_data);//update
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setAuthor("Anchor");
                book.updateAll("name = ? and author = ?","the Da vinci code","Dan Brown");
            }
        });

        Button queryData = (Button)findViewById(R.id.query_data);//query
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = DataSupport.findAll(Book.class);
                for(Book book:books){
                    Log.d("MainActivity","book name is"+book.getName());
                    Log.d("MainActivity","book author is"+book.getAuthor());

                }
            }
        });

    }
}
/**
 * List<Book> books = DataSupport.findAll(Book.class);查找所有book
 * List<Book> books = DataSupport.select("name","author").find(Book.class);查找所有book的name和author
 * 混编
 * List<Book> books = DataSupport.select("name","author","pages")
 *                    .where("pages > ?","400")
 *                    .order("pages")
 *                    .limit(10)
 *                    .offset(10)
 *                    .find(Book.class);
 * 查询在第11-20条满足页数大于400这个条件的name。author和pages这3列数据，并将查询结果按照页数升序排列
 *
 * 还支持原生的SQL查询
 * Cursor c = DataSupport.findBySQL("select * from Book where pages > ? and price < ?","400","20");
 * 返回是Cursor，当然还需要用之前的老方法将数据一一取出
 *
 *
 * */
