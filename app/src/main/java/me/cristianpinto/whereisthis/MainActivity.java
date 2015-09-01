package me.cristianpinto.whereisthis;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    Button easy;
    Button medium;
    Button hard;
    int dif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        easy = (Button) findViewById(R.id.easy);
        medium = (Button) findViewById(R.id.medium);
        hard = (Button) findViewById(R.id.hard);

        easy.setBackgroundResource(R.drawable.roundedbuttonclicked);

        dif = 1;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void chooseGameMode(View view) {
        Intent intent = new Intent(this, ChooseGameMode.class);
        intent.putExtra("Hardness", (int)dif);
        startActivity(intent);
    }

    public void easyGame(View view) {
        easy.setBackgroundResource(R.drawable.roundedbuttonclicked);
        medium.setBackgroundResource(R.drawable.roundedbutton);
        hard.setBackgroundResource(R.drawable.roundedbutton);
        dif = 1;
    }

    public void mediumGame(View view) {
        medium.setBackgroundResource(R.drawable.roundedbuttonclicked);
        easy.setBackgroundResource(R.drawable.roundedbutton);
        hard.setBackgroundResource(R.drawable.roundedbutton);
        dif = 2;
    }

    public void hardGame(View view) {
        hard.setBackgroundResource(R.drawable.roundedbuttonclicked);
        medium.setBackgroundResource(R.drawable.roundedbutton);
        easy.setBackgroundResource(R.drawable.roundedbutton);
        dif = 3;
    }

    public void RankingView(View view) {
        Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
    }

    public void HelpView(View view) {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);

    }
    public void About(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);

    }
}
