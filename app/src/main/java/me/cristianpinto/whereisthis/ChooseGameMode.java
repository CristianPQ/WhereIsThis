package me.cristianpinto.whereisthis;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class ChooseGameMode extends ActionBarActivity {
    //public final static String GAME_MODE = "me.cristianpinto.whereisthis.MODE";

    int hardness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game_mode);
        try {
            Intent intent = getIntent();
            hardness = intent.getExtras().getInt("Hardness");
        } catch (Exception e) {
            Intent backIntent = new Intent(this, MainActivity.class);
            startActivity(backIntent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_game_mode, menu);
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

    public void playGameMode1(View view) {
        Intent intent = new Intent(this, PlayGame.class);
        intent.putExtra("GameMode", (int)1);
        intent.putExtra("Hardness", (int)hardness);
        startActivity(intent);
    }

    public void playGameMode2(View view) {
        Intent intent = new Intent(this, PlayGame.class);
        intent.putExtra("GameMode", (int)2);
        intent.putExtra("Hardness", (int)hardness);
        startActivity(intent);
    }

    public void playGameMode3(View view) {
        Intent intent = new Intent(this, PlayGame.class);
        intent.putExtra("GameMode", (int)3);
        intent.putExtra("Hardness", (int)hardness);
        startActivity(intent);
    }
}
