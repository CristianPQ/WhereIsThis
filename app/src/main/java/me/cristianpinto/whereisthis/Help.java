package me.cristianpinto.whereisthis;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Help extends ActionBarActivity {

    ImageView continent;
    Button elementView;
    TextView errorView;
    TextView timer;
    TextView score;
    Button zoomin;
    Button zoomout;
    TextView messageView;
    Button next;
    int cont;
    float size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        continent = (ImageView) findViewById(R.id.continent);
        elementView= (Button) findViewById(R.id.TView);
        errorView = (TextView) findViewById(R.id.ErrorView);
        errorView.setTextColor(Color.BLACK);
        timer = (TextView) findViewById(R.id.TimerView);
        score = (TextView) findViewById(R.id.ScoreView);
        zoomin = (Button) findViewById(R.id.zoomin);
        zoomout = (Button) findViewById(R.id.zoomout);
        messageView = (TextView) findViewById(R.id.messageView);
        next = (Button) findViewById(R.id.nextButton);
        cont = 0;
        size = messageView.getTextSize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
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

    public void Next(View view) {
        ++cont;
        if(cont > 8) cont = 8;
        helping();
    }

    public void Back(View view) {
        --cont;
        if(cont < 0) cont = 0;
        helping();
    }

    public void helping() {

        switch (cont) {
            case 0:
                zoomin.setBackgroundResource(R.drawable.roundedbutton);
                messageView.setText("Choose the hardness and a game mode");
                break;
            case 1:
                zoomin.setBackgroundResource(R.drawable.redroundedbutton);
                zoomout.setBackgroundResource(R.drawable.roundedbutton);
                messageView.setText("This button it's to do zoom in");
                break;
            case 2:
                zoomin.setBackgroundResource(R.drawable.roundedbutton);
                zoomout.setBackgroundResource(R.drawable.redroundedbutton);
                timer.setTextColor(Color.BLACK);
                messageView.setText("This button it's to do zoom out");
                break;
            case 3:
                zoomout.setBackgroundResource(R.drawable.roundedbutton);
                timer.setTextColor(Color.rgb(242, 38, 19));
                score.setTextColor(Color.BLACK);
                messageView.setText("This is the timer, so you must be fast choosing a place");
                break;
            case 4:
                timer.setTextColor(Color.BLACK);
                score.setTextColor(Color.rgb(242,38,19));
                errorView.setTextColor(Color.BLACK);
                messageView.setText("This is your score, the best ones are recorded in the HighScore view");
                break;
            case 5:
                score.setTextColor(Color.BLACK);
                errorView.setTextColor(Color.rgb(242,38,19));
                elementView.setTextColor(Color.BLACK);
                messageView.setText("This is the error text, so if you fail it will tell you why");
                break;
            case 6:
                errorView.setTextColor(Color.BLACK);
                elementView.setTextColor(Color.rgb(242,38,19));
                messageView.setText("Here will appear the places that you have to match, if you touch it you will get a new random place");
                break;
            case 7:
                elementView.setTextColor(Color.BLACK);
                messageView.setTextSize(size/2);
                messageView.setText("The Score:\nIf you touch the correct country you will get more point\nIf you fail you will lose some of them" +
                        "\nIf you get a new random place you will lose some too");
                break;
            case 8:
                messageView.setText("GOOD LUCK!");
                float big = size*2;
                messageView.setTextSize(big);
                break;
            default:
                break;

        }
    }
}
