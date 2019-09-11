package com.example.gameoflife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    static RecyclerViewAdapter rva;
    boolean[][] data = new boolean[100][100];

    public static void generationChange(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int e = 0; e < arr[i].length; e++) {
                int neighbors = numNeighbors(arr, i, e);
                if (arr[i][e]) {
                    if (neighbors < 2 || neighbors > 3) {
                        arr[i][e] = false;

                    }


                } else {
                    if (neighbors == 3) {
                        arr[i][e] = true;

                    }

                }

            }


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
            if (arr[row + 1][column]) {
                count++;

            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        ///ffieu

        return count;
    }


    boolean play = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.recyclerView);
        rva = new RecyclerViewAdapter(data);
        rv.setAdapter(rva);
        Button pause = findViewById(R.id.pause);
        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generationChange(data);
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
            }
        });




    }



}
