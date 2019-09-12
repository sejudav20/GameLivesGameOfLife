package com.example.gameoflife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static RecyclerViewAdapter rva;
    boolean[][] data = new boolean[30][30];

    public static void generationChange(boolean[][] arr) {
        ArrayList<Integer> valuesX1=new ArrayList<>();
        ArrayList<Integer> valuesy1=new ArrayList<>();
        ArrayList<Integer> valuesX0=new ArrayList<>();
        ArrayList<Integer> valuesy0=new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int e = 0; e < arr[i].length; e++) {
                int neighbors = numNeighbors(arr, i, e);
                if (arr[i][e]) {
                    if (neighbors < 2 || neighbors > 3) {

                        valuesX0.add(i);
                        valuesy0.add(e);

                        //arr[i][e] = false;

                    }


                } else {
                    if (neighbors == 3) {
                        valuesX1.add(i);
                        valuesy1.add(e);
                       // arr[i][e] = true;

                    }

                }

            }


        }
        for(int i=0;i<valuesX0.size();i++){

            arr[valuesX0.get(i)][valuesy0.get(i)] = false;

        }
        for(int i=0;i<valuesX1.size();i++){

            arr[valuesX1.get(i)][valuesy1.get(i)] = true;

        }

        rva.notifyDataSetChanged();

    }

    public static int numNeighbors(boolean[][] arr, int row, int column) {
        int count = 0;
        try {
            if (arr[row][column + 1]) {
                count++;

            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            if (arr[row + 1][column]) {
                count++;

            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            if (arr[row + 1][column + 1]) {
                count++;

            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        try {
            if (arr[row - 1][column + 1]) {
                count++;

            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        try {
            if (arr[row - 1][column - 1]) {
                count++;

            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        try {
            if (arr[row + 1][column - 1]) {
                count++;

            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            if (arr[row][column - 1]) {
                count++;

            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            if (arr[row - 1][column]) {
                count++;

            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }


        return count;
    }


    boolean play = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView rv = findViewById(R.id.recyclerView);
        rva = new RecyclerViewAdapter(data);
        rv.setLayoutManager(llm);
        rv.setAdapter(rva);


        Button pause = findViewById(R.id.pause);
        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generationChange(data);
                Log.d("test","next is clicked");

            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (play) {
                    play = false;
                } else {
                    play = true;
                }
                Log.d("test","pause is clicked");
                while(play){
                    generationChange(data);
                    try {
                        Thread.sleep(30);
                        Log.d("test","sleeping");
                    } catch (InterruptedException e) {
                        Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });




    }



}
