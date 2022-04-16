package com.android.s19110239;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtTitle;
    Button btnStartTask;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTitle = findViewById(R.id.txtTitle);
        btnStartTask = findViewById(R.id.btnStartTask);
        progressBar = findViewById(R.id.progressBar);

    }

    public void startTask(View view) {
        new SimpleAsyncTask().execute();
    }

    private class SimpleAsyncTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtTitle.setText("Napping...");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 1; i <= 100; i++) {
                try {
                    Thread.sleep(600);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int number = values[0];
            progressBar.setProgress(number);
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            txtTitle.setText("Working...");
            Toast.makeText(MainActivity.this, "Time out", Toast.LENGTH_SHORT).show();
        }
    }
}