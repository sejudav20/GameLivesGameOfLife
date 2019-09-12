package com.example.gameoflife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static RecyclerViewAdapter rva;
    boolean[][] data = new boolean[20][30];

    public static class generationalChange extends AsyncTask<boolean[][], Void, Void> {


        @Override
        protected Void doInBackground(boolean[][]... booleans) {
            boolean[][] arr = booleans[0];
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


            return null;
        }
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
        final ImageButton pause = findViewById(R.id.pause);
        final ImageButton next = findViewById(R.id.next);
        final ImageButton clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (boolean[] b : data) {
                    for (boolean bo : b) {
                        bo = false;


                    }


                }
            }
        });
        final ImageButton random = findViewById(R.id.randomized);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (boolean[] b : data) {
                    for (boolean bo : b) {
                        Random rand = new Random();
                        if (rand.nextInt(10) < 3) {
                            bo = true;
                        } else {
                            bo = false;
                        }


                    }


                }
            }

    });
        next.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View view){
        new generationalChange().execute(data);
    }
    });
        pause.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        if (play) {
            play = false;
            pause.setImageDrawable(getDrawable(R.drawable.ic_play_arrow_black_24dp));

        } else {
            play = true;
            pause.setImageDrawable(getDrawable(R.drawable.ic_pause_black_24dp));


        }
    }
    });


}


}
