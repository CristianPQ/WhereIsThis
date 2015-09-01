package me.cristianpinto.whereisthis;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Ranking extends ActionBarActivity {
    private SharedPreferences preferenceSettings;
    private static final int PREFERENCE_MODE_PRIVATE = 0;
    private static final String RANKINGFILE = "RankingFile";
    TextView[] scores;
    TextView[] names;
    TextView[] descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        preferenceSettings = getSharedPreferences(RANKINGFILE, PREFERENCE_MODE_PRIVATE);
        scores = new TextView[6];
        names = new TextView[6];
        descriptions = new TextView[6];

        scores[0] = (TextView) findViewById(R.id.icon1);
        names[0] = (TextView) findViewById(R.id.firstLine1);
        descriptions[0] = (TextView) findViewById(R.id.secondLine1);

        scores[1] = (TextView) findViewById(R.id.icon2);
        names[1] = (TextView) findViewById(R.id.firstLine2);
        descriptions[1] = (TextView) findViewById(R.id.secondLine2);

        scores[2] = (TextView) findViewById(R.id.icon3);
        names[2] = (TextView) findViewById(R.id.firstLine3);
        descriptions[2] = (TextView) findViewById(R.id.secondLine3);

        scores[3] = (TextView) findViewById(R.id.icon4);
        names[3] = (TextView) findViewById(R.id.firstLine4);
        descriptions[3] = (TextView) findViewById(R.id.secondLine4);

        scores[4] = (TextView) findViewById(R.id.icon5);
        names[4] = (TextView) findViewById(R.id.firstLine5);
        descriptions[4] = (TextView) findViewById(R.id.secondLine5);

        scores[5] = (TextView) findViewById(R.id.icon6);
        names[5] = (TextView) findViewById(R.id.firstLine6);
        descriptions[5] = (TextView) findViewById(R.id.secondLine6);

        for(int i = 0; i < 6; ++i) {
            Log.d("Reading rankings", "-------"+i);
            String scoreKey = "score" +Integer.toString(i+1);;
            int sc = preferenceSettings.getInt(scoreKey,0);

            String scorenum = "";
            if (sc < 100) {
                scorenum = scorenum +"0";
                if(sc < 10) {
                    scorenum = scorenum +"0";
                }
            }
            scorenum = scorenum + Integer.toString(sc);;
            scores[i].setText(scorenum);

            String nameKey = "name" +Integer.toString(i+1);;
            String name = preferenceSettings.getString(nameKey, "no name");

            names[i].setText(name);

            String descriptionKey = "time" +Integer.toString(i+1);;
            String description = preferenceSettings.getString(descriptionKey, "no description");

            descriptions[i].setText(description);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ranking, menu);
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
}
