package com.example.gameoflife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import android.widget.ImageButton;

import java.util.ArrayList;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static RecyclerViewAdapter rva;
    boolean[][] data = new boolean[20][30];
    private Handler mHandler;

    public static class RandomizeAll extends AsyncTask<boolean[][], Void, Void> {


        @Override
        protected Void doInBackground(boolean[][]... booleans) {
            boolean[][] data = booleans[0];
            for (int i=0; i<data.length;i++) {
                for (int e=0; e<data[0].length;e++) {
                    Random rand = new Random();
                    if (rand.nextInt(10) < 3) {
                        data[i][e] = true;
                    } else {
                        data[i][e] = false;
                    }


                }


            }
            return null;


        }

    }

    public static class ClearAll extends AsyncTask<boolean[][], Void, Void> {


        @Override
        protected Void doInBackground(boolean[][]... booleans) {
            boolean[][] data = booleans[0];
            for (int i=0; i<data.length;i++) {
                for (int e=0; e<data[0].length;e++) {
                    data[i][e] = false;


                }


            }
            return null;


        }

    }


    public static class generationalChange extends AsyncTask<boolean[][], Void, Void> {


        @Override
        protected Void doInBackground(boolean[][]... booleans) {
            boolean[][] arr = booleans[0];
            ArrayList<Integer> valuesX1 = new ArrayList<>();
            ArrayList<Integer> valuesy1 = new ArrayList<>();
            ArrayList<Integer> valuesX0 = new ArrayList<>();
            ArrayList<Integer> valuesy0 = new ArrayList<>();

            for (int i = 0; i < arr.length; i++) {
                for (int e = 0; e < arr[i].length; e++) {
                    int neighbors = numNeighbors(arr, i, e);
                    if (arr[i][e]) {
                        if (neighbors < 2 || neighbors > 3) {
                            valuesX0.add(i);
                            valuesy0.add(e);

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

            for (int i = 0; i < valuesX0.size(); i++) {

                arr[valuesX0.get(i)][valuesy0.get(i)] = false;

            }
            for (int i = 0; i < valuesX1.size(); i++) {

                arr[valuesX1.get(i)][valuesy1.get(i)] = true;

            }


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
            if (arr[row - 1][column]) {
                count++;

            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }


        return count;
    }


    boolean play = false;

    @Override
    protected void onDestroy() {
        stopGenerations();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView rv = findViewById(R.id.recyclerView);
        rva = new RecyclerViewAdapter(data);
        rv.setLayoutManager(llm);
        rv.setAdapter(rva);
        final ImageButton pause = findViewById(R.id.pause);
        final ImageButton next = findViewById(R.id.next);
        final ImageButton clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startClearAll();
                rva.notifyDataSetChanged();
                stopClearAll();
            }
        });
        final ImageButton random = findViewById(R.id.randomized);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RandomizeAll().execute(data);
                rva.notifyDataSetChanged();

            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new generationalChange().execute(data);
                rva.notifyDataSetChanged();
            }
        });
        pause.setImageDrawable(getDrawable(R.drawable.ic_play_arrow_black_24dp));
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("test", "pause is clicked");

                if (play) {
                    play = false;
                    stopGenerations();
                    pause.setImageDrawable(getDrawable(R.drawable.ic_play_arrow_black_24dp));

                } else {
                    play = true;
                    startGenerations();
                    pause.setImageDrawable(getDrawable(R.drawable.ic_pause_black_24dp));


                }


            }
        });


    }

    Runnable mRunGeneration = new Runnable() {
        @Override
        public void run() {
            try {
                new generationalChange().execute(data);
                rva.notifyDataSetChanged();
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mRunGeneration, 10);
            }
        }
    };

    void startGenerations() {
        mRunGeneration.run();
    }

    void stopGenerations() {
        mHandler.removeCallbacks(mRunGeneration);
    }

    Runnable mRunClear = new Runnable() {
        @Override
        public void run() {
            try {
                new ClearAll().execute(data);
                rva.notifyDataSetChanged();
            } catch (Exception e) {
                Log.d("test","whyyyyyy");
            }
        }
    };

    void startClearAll() {
        mRunClear.run();
    }

    void stopClearAll() {
        mHandler.removeCallbacks(mRunClear);
    }

}
