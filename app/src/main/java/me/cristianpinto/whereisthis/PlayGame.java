package me.cristianpinto.whereisthis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class PlayGame extends ActionBarActivity {

    ImageView continent;
    HashMap<String, String> options;
    ArrayList<String> notFound;
    Button textView;
    TextView errorView;
    TextView timerView;
    TextView scoreView;
    int score;

    String roundWord;
    CounterClass timer;

    Button start;
    Button playAgain;

    int gameMode;
    int hardness;

    String name;

    int cont;

    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferenceEditor;
    private static final int PREFERENCE_MODE_PRIVATE = 0;
    private static final String RANKINGFILE = "RankingFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        Intent intent = getIntent();
        gameMode = intent.getExtras().getInt("GameMode");
        hardness = intent.getExtras().getInt("Hardness");
        //1, paisos
        //2, capitals
        //3, monuments

        play(gameMode);

        score = 0;

        textView = (Button) findViewById(R.id.TView);
        errorView = (TextView) findViewById(R.id.ErrorView);
        errorView.setText("");

        timerView = (TextView) findViewById(R.id.TimerView);
        setTime(hardness);

        scoreView = (TextView) findViewById(R.id.ScoreView);
        scoreView.setText("Score: "+score);

        start = (Button) findViewById(R.id.start);
        playAgain = (Button) findViewById(R.id.playagain);


        roundWord = getRandomWord();
        //roundWord = "Brazil";
        textView.setText(options.get(roundWord));

        //setContentView(textView);*/
        //setContentView(R.layout.activity_play_game);


        continent = (ImageView) findViewById(R.id.continent);
        continent.setOnTouchListener(null);

        continent.setColorFilter(Color.argb(125, 22, 161, 217));
        preferenceSettings = getSharedPreferences(RANKINGFILE, PREFERENCE_MODE_PRIVATE);
        preferenceEditor = preferenceSettings.edit();

        cont = 0;

    }

    OnTouchListener imgSourceOnTouchListener = new OnTouchListener(){

        PointF DownPT = new PointF(); // Record Mouse Position When Pressed Down
        PointF StartPT = new PointF(); // Record Start Position of 'img'
        PointF mv = new PointF();
        boolean onMove = false;

        @Override
        public boolean onTouch(View v, MotionEvent event){

            int p = 10-cont;
            if (p<0) p =0;
            cont =0;

            switch(event.getAction()){
                case MotionEvent.ACTION_UP:
                    if(!onMove) {
                        Log.d("up", "---IN UP------");

                        int[] values = new int[2];
                        v.getLocationOnScreen(values);
                        float eventX = event.getX();
                        float eventY = event.getY();
                        float[] eventXY = new float[]{eventX, eventY};

                        Matrix invertMatrix = new Matrix();
                        ((ImageView) v).getImageMatrix().invert(invertMatrix);

                        invertMatrix.mapPoints(eventXY);
                        int x = Integer.valueOf((int) eventXY[0]);
                        int y = Integer.valueOf((int) eventXY[1]);
                        //int x = (int) event.getX();
                        //int y = (int) event.getY();
                        //y += values[1];
                        //®y += 450;
                        //x += 315;

                        Drawable d = ((ImageView) v).getDrawable();
                        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
                        //Bitmap bitmap = convertToBitmap(d, 720, 1280);


                        if (x < 0) {
                            x = 0;
                        } else if (x > bitmap.getWidth() - 1) {
                            x = bitmap.getWidth() - 1;
                        }

                        if (y < 0) {
                            y = 0;
                        } else if (y > bitmap.getHeight() - 1) {
                            y = bitmap.getHeight() - 1;
                        }

                        int touchedRGB = bitmap.getPixel(x, y);
                        String Hex = Integer.toHexString(touchedRGB);
                        //String actual = String.valueOf(textView.getText());

                        //Log.d("Actual country", "-------"+actual);

                        String c = "Sea";

                        int fail = 1;
                        int good = 5;

                        switch (hardness) {
                            case 1:
                                fail = 1;
                                good = 5;
                                break;
                            case 2:
                                fail = 3;
                                good = 6;
                                break;
                            case 3:
                                fail = 5;
                                good = 7;
                                break;
                            default:
                                break;
                        }
                        switch (Hex) {
                            case "ffa73b8b":
                                c = "Brazil";
                                if (roundWord.equals(c)) {
                                    notFound.remove(roundWord);
                                    roundWord = getRandomWord();
                                    textView.setText(options.get(roundWord));
                                    errorView.setTextColor(Color.GREEN);
                                    errorView.setText("Correct");
                                    score += good + p;
                                } else {
                                    errorView.setTextColor(Color.parseColor("#ef2313"));
                                    errorView.setText("This is not " + options.get(roundWord));
                                    score -= fail;
                                }
                                //Log.d("Pais", "es "+c);
                                break;
                            case "ffffb446":
                                c = "Bolivia";
                                if (roundWord.equals(c)) {
                                    notFound.remove(roundWord);
                                    roundWord = getRandomWord();
                                    textView.setText(options.get(roundWord));
                                    errorView.setTextColor(Color.GREEN);
                                    errorView.setText("Correct");
                                    score += good + p;
                                } else {
                                    errorView.setTextColor(Color.parseColor("#ef2313"));
                                    errorView.setText("This is not " + options.get(roundWord));
                                    score -= fail;
                                }
                                //Log.d("Pais", "es "+c);
                                break;
                            case "ffffe4a5":
                                c = "French Guyana";
                                if (roundWord.equals(c)) {
                                    notFound.remove(roundWord);
                                    roundWord = getRandomWord();
                                    textView.setText(options.get(roundWord));
                                    errorView.setTextColor(Color.GREEN);
                                    errorView.setText("Correct");
                                    score += good + p;
                                } else {
                                    errorView.setTextColor(Color.parseColor("#ef2313"));
                                    errorView.setText("This is not " + options.get(roundWord));
                                    score -= fail;
                                }
                                //Log.d("Pais", "es "+c);
                                break;
                            case "ffb3f7c4":
                                c = "Suriname";
                                if (roundWord.equals(c)) {
                                    notFound.remove(roundWord);
                                    roundWord = getRandomWord();
                                    textView.setText(options.get(roundWord));
                                    errorView.setTextColor(Color.GREEN);
                                    errorView.setText("Correct");
                                    score += good + p;
                                } else {
                                    errorView.setTextColor(Color.parseColor("#ef2313"));
                                    errorView.setText("This is not " + options.get(roundWord));
                                    score -= fail;
                                }
                                //Log.d("Pais", "es "+c);
                                break;
                            case "ff00a855":
                                c = "Guyana";
                                if (roundWord.equals(c)) {
                                    notFound.remove(roundWord);
                                    roundWord = getRandomWord();
                                    textView.setText(options.get(roundWord));
                                    errorView.setTextColor(Color.GREEN);
                                    errorView.setText("Correct");
                                    score += good + p;
                                } else {
                                    errorView.setTextColor(Color.parseColor("#ef2313"));
                                    errorView.setText("This is not " + options.get(roundWord));
                                    score -= fail;
                                }
                                //Log.d("Pais", "es "+c);
                                break;
                            case "ff733f74":
                                c = "Venezuela";
                                if (roundWord.equals(c)) {
                                    notFound.remove(roundWord);
                                    roundWord = getRandomWord();
                                    textView.setText(options.get(roundWord));
                                    errorView.setTextColor(Color.GREEN);
                                    errorView.setText("Correct");
                                    score += good + p;
                                } else {
                                    errorView.setTextColor(Color.parseColor("#ef2313"));
                                    errorView.setText("This is not " + options.get(roundWord));
                                    score -= fail;
                                }
                                //Log.d("Pais", "es "+c);
                                break;
                            case "ffaf2313":
                                c = "Colombia";
                                if (roundWord.equals(c)) {
                                    notFound.remove(roundWord);
                                    roundWord = getRandomWord();
                                    textView.setText(options.get(roundWord));
                                    errorView.setTextColor(Color.GREEN);
                                    errorView.setText("Correct");
                                    score += good + p;
                                } else {
                                    errorView.setTextColor(Color.parseColor("#ef2313"));
                                    errorView.setText("This is not " + options.get(roundWord));
                                    score -= fail;
                                }
                                //Log.d("Pais", "es "+c);
                                break;
                            case "fff14c57":
                                c = "Ecuador";
                                if (roundWord.equals(c)) {
                                    notFound.remove(roundWord);
                                    roundWord = getRandomWord();
                                    textView.setText(options.get(roundWord));
                                    errorView.setTextColor(Color.GREEN);
                                    errorView.setText("Correct");
                                    score += good + p;
                                } else {
                                    errorView.setTextColor(Color.parseColor("#ef2313"));
                                    errorView.setText("This is not " + options.get(roundWord));
                                    score -= fail;
                                }
                                //Log.d("Pais", "es "+c);
                                break;
                            case "fffb8384":
                                c = "Peru";
                                if (roundWord.equals(c)) {
                                    notFound.remove(roundWord);
                                    roundWord = getRandomWord();
                                    textView.setText(options.get(roundWord));
                                    errorView.setTextColor(Color.GREEN);
                                    errorView.setText("Correct");
                                    score += good + p;
                                } else {
                                    errorView.setTextColor(Color.parseColor("#ef2313"));
                                    errorView.setText("This is not " + options.get(roundWord));
                                    score -= fail;
                                }
                                //Log.d("Pais", "es "+c);
                                break;
                            case "ff27529f":
                                c = "Chile";
                                if (roundWord.equals(c)) {
                                    notFound.remove(roundWord);
                                    roundWord = getRandomWord();
                                    textView.setText(options.get(roundWord));
                                    errorView.setTextColor(Color.GREEN);
                                    errorView.setText("Correct");
                                    score += good + p;
                                } else {
                                    errorView.setTextColor(Color.parseColor("#ef2313"));
                                    errorView.setText("This is not " + options.get(roundWord));
                                    score -= fail;
                                }
                                //Log.d("Pais", "es "+c);
                                break;
                            case "ffb2aad6":
                                c = "Argentina";
                                if (roundWord.equals(c)) {
                                    notFound.remove(roundWord);
                                    roundWord = getRandomWord();
                                    textView.setText(options.get(roundWord));
                                    errorView.setTextColor(Color.GREEN);
                                    errorView.setText("Correct");
                                    score += good + p;
                                } else {
                                    errorView.setTextColor(Color.parseColor("#ef2313"));
                                    errorView.setText("This is not " + options.get(roundWord));
                                    score -= fail;
                                }
                                //Log.d("Pais", "es "+c);
                                break;
                            case "ff667b8b":
                                c = "Paraguay";
                                if (roundWord.equals(c)) {
                                    notFound.remove(roundWord);
                                    roundWord = getRandomWord();
                                    textView.setText(options.get(roundWord));
                                    errorView.setTextColor(Color.GREEN);
                                    errorView.setText("Correct");
                                    score += good + p;
                                } else {
                                    errorView.setTextColor(Color.parseColor("#ef2313"));
                                    errorView.setText("This is not " + options.get(roundWord));
                                    score -= fail;
                                }
                                //Log.d("Pais", "es "+c);
                                break;
                            case "ffd9e0e2":
                                c = "Uruguay";
                                if (roundWord.equals(c)) {
                                    notFound.remove(roundWord);
                                    roundWord = getRandomWord();
                                    textView.setText(options.get(roundWord));
                                    errorView.setTextColor(Color.GREEN);
                                    errorView.setText("Correct");
                                    score += good + p;
                                } else {
                                    errorView.setTextColor(Color.parseColor("#ef2313"));
                                    errorView.setText("This is not " + options.get(roundWord));
                                    score -= fail;
                                }
                                //Log.d("Pais", "es "+c);
                                break;
                            default:
                                errorView.setTextColor(Color.parseColor("#ef2313"));
                                errorView.setText("This is not a country");
                                //Log.d("NOOOOOOO Pais", "Error, eso es el mar!");
                                break;
                        }
                        scoreView.setText("Score: " + score);

                        //Log.d("color", "----touched color: " + "#" + Hex);
                        //Log.d("coord", x+"-----"+y);

                        /*colorRGB.setTextColor(touchedRGB);


                        int color = bitmap.getPixel(x, y);
                        int r = Color.red(color);
                        int g = Color.green(color);
                        int b = Color.blue(color);
                        if(r ==167 && g ==59  && b ==139) Log.d("si", "es brasil");

                        Log.d("imageCoord", values[0]+"-----"+values[1]);
                        Log.d("coord", x+"-----"+y);
                        Log.d("color", "--------"+r+"-----"+g+"----"+b+"---");*/


                    }
                    onMove = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    onMove = true;
                    mv = new PointF( event.getX() - DownPT.x, event.getY() - DownPT.y);

                    continent.setX((int) (StartPT.x + mv.x));
                    continent.setY((int) (StartPT.y + mv.y));
                    StartPT = new PointF( continent.getX(), continent.getY() );
                    break;
                case MotionEvent.ACTION_DOWN:

                    DownPT.x = event.getX();
                    DownPT.y = event.getY();
                    StartPT = new PointF( continent.getX(), continent.getY() );
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    /*OnTouchListener imgSourceOnTouchListener = new OnTouchListener(){

        PointF DownPT = new PointF(); // Record Mouse Position When Pressed Down
        PointF StartPT = new PointF(); // Record Start Position of 'img'
        PointF mv = new PointF();

        @Override
        public boolean onTouch(View v, MotionEvent event){

        }
    };*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_game, menu);
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

    public void play(int mode) {

        notFound = new ArrayList<String>();
        notFound.add("Brazil");
        notFound.add("Colombia");
        notFound.add("Argentina");
        notFound.add("Peru");
        notFound.add("Venezuela");
        notFound.add("Chile");
        notFound.add("Ecuador");
        notFound.add("Bolivia");
        notFound.add("Paraguay");
        notFound.add("Uruguay");
        notFound.add("Guyana");
        notFound.add("Suriname");
        notFound.add("French Guyana");

        options = new HashMap<String, String>();

        switch (mode) {
            case 1:
                options.put("Brazil", "Brazil");
                options.put("Colombia", "Colombia");
                options.put("Argentina", "Argentina");
                options.put("Peru", "Peru");
                options.put("Venezuela", "Venezuela");
                options.put("Chile", "Chile");
                options.put("Ecuador", "Ecuador");
                options.put("Bolivia", "Bolivia");
                options.put("Paraguay", "Paraguay");
                options.put("Uruguay", "Uruguay");
                options.put("Guyana", "Guyana");
                options.put("Suriname", "Suriname");
                options.put("French Guyana", "French Guyana");
                break;
            case 2:
                options.put("Brazil", "Brasilia");
                options.put("Colombia", "Bogota");
                options.put("Argentina", "Buenos Aires");
                options.put("Peru", "Lima");
                options.put("Venezuela", "Caracas");
                options.put("Chile", "Santiago de Chile");
                options.put("Ecuador", "Quito");
                options.put("Bolivia", "Sucre");
                options.put("Paraguay", "Asuncion");
                options.put("Uruguay", "Montevideo");
                options.put("Guyana", "Goergetown");
                options.put("Suriname", "Paramaribo");
                options.put("French Guyana", "Cayena");
                break;
            case 3:
                options.put("Brazil", "Cristo Redentor");
                options.put("Colombia", "Parque Nacional del Cafe");
                options.put("Argentina", "Cataratas del Iguazu");
                options.put("Peru", "Macchu Picchu");
                options.put("Venezuela", "Salto del Angel");
                options.put("Chile", "Isla Magdalena");
                options.put("Ecuador", "Islas Galapagos");
                options.put("Bolivia", "Lago Titicaca");
                options.put("Paraguay", "Lagunas Saladas Campo Maria");
                options.put("Uruguay", "Mano de Punta del Este");
                options.put("Guyana", "Parque Nacional Kaieteur");
                options.put("Suriname", "Puente Jules Wijdenbosch");
                options.put("French Guyana", "Isla de la salvacion");
                break;
            default:
                break;
        }
    }

    public String getRandomWord() {
        int size = notFound.size();
        if(size < 1) {
            continent.setOnTouchListener(null);
            continent.setColorFilter(Color.rgb(46, 204, 113));
            CheckNewScore();
            timer.cancel();
            playAgain.setVisibility(View.VISIBLE);
            return "finish";
        }
        Random rand = new Random();
        int rnum = rand.nextInt(size);

        String ret = notFound.get(rnum);
        //String ret = "It's ready";
        return ret;
    }

    //@TargetApi(Build.VERSION_CODES.GINGERBREAD)
    //@SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override public void onFinish() {
            timerView.setText("00:00");
            continent.setColorFilter(Color.rgb(207, 0, 15));
            continent.setOnTouchListener(null);
            playAgain.setVisibility(View.VISIBLE);
        }
        //@SuppressLint("NewApi")
        //@TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            //System.out.println(hms);
            timerView.setText(hms);
            ++cont;
        }


    }

    public void setTime(int h) {
        switch (h) {
            case 1:
                timerView.setText("01:30");
                timer = new CounterClass(90000,1000);
                //timer.start();
                break;
            case 2:
                timerView.setText("01:00");
                timer = new CounterClass(60000,1000);
                //timer.start();
                break;
            case 3:
                timerView.setText("00:30");
                timer = new CounterClass(30000,1000);
                //timer.start();
                break;
            default:
                break;
        }
    }

    public void newRandomWord(View view) {
        roundWord = getRandomWord();
        textView.setText(options.get(roundWord));
        errorView.setTextColor(Color.RED);
        errorView.setText("New place");
        switch (hardness) {
            case 1:
                score -= 1;
                break;
            case 2:
                score -= 3;
                break;
            case 3:
                score -= 5;
                break;
            default:
                break;
        }
        scoreView.setText("Score: " + score);
    }

    public void SetClickable(View view) {
        continent.setOnTouchListener(imgSourceOnTouchListener);
        continent.clearColorFilter();
        timer.start();
        start.setVisibility(View.GONE);

    }

    public void PlayAgain(View view) {
        playAgain.setVisibility(View.GONE);
        play(gameMode);

        score = 0;

        errorView.setText("");

        setTime(hardness);

        scoreView.setText("Score: "+score);



        roundWord = getRandomWord();
        //roundWord = "Brazil";
        textView.setText(options.get(roundWord));

        //setContentView(textView);*/
        //setContentView(R.layout.activity_play_game);


        continent.setOnTouchListener(imgSourceOnTouchListener);

        continent.clearColorFilter();
        timer.start();
    }

    public void CheckScore() {
        String time = String.valueOf(timerView.getText());
        String dif = "unknoun";
        switch (gameMode) {
            case 1:
                dif = "countries";
                break;
            case 2:
                dif = "capitals";
                break;
            case 3:;
                dif = "monuments";
                break;
            default:
                break;
        }
        switch (hardness) {
            case 1:
                int second = preferenceSettings.getInt("score2", 0);
                if (second < score) {
                    //Log.d("name value", "-----"+name);
                    int first = preferenceSettings.getInt("score1", 0);
                    if (first < score) {
                        String n1 = preferenceSettings.getString("name1", "no name");
                        String d1 = preferenceSettings.getString("time1", "no description");
                        preferenceEditor.putInt("score2", first);
                        preferenceEditor.putString("name2", n1);
                        preferenceEditor.putString("time2", d1);

                        preferenceEditor.putInt("score1", score);
                        preferenceEditor.putString("name1", name + " in easy level " + dif + " mode");
                        preferenceEditor.putString("time1", time + "left");
                        preferenceEditor.commit();

                    }
                    else {
                        preferenceEditor.putInt("score2", score);
                        preferenceEditor.putString("name2", name+" in easy level "+ dif+" mode");
                        preferenceEditor.putString("time2", time + "left");
                        preferenceEditor.commit();
                    }
                }
                break;
            case 2:
                int second2 = preferenceSettings.getInt("score4", 0);
                if (second2 < score) {
                    int first = preferenceSettings.getInt("score3", 0);
                    if (first < score) {
                        String n1 = preferenceSettings.getString("name3", "no name");
                        String d1 = preferenceSettings.getString("time3", "no description");
                        preferenceEditor.putInt("score4", first);
                        preferenceEditor.putString("name4", n1);
                        preferenceEditor.putString("time4", d1);

                        preferenceEditor.putInt("score3", score);
                        preferenceEditor.putString("name3", name+" in medium level "+ dif+" mode");
                        preferenceEditor.putString("time3", time + "left");
                        preferenceEditor.commit();

                    }
                    else {
                        preferenceEditor.putInt("score4", score);
                        preferenceEditor.putString("name4", name+" in medium level "+ dif+" mode");
                        preferenceEditor.putString("time4", time + "left");
                        preferenceEditor.commit();
                    }
                }
                break;
            case 3:
                int second3 = preferenceSettings.getInt("score6", 0);
                if (second3 < score) {
                    int first = preferenceSettings.getInt("score5", 0);
                    if (first < score) {
                        String n1 = preferenceSettings.getString("name5", "no name");
                        String d1 = preferenceSettings.getString("time5", "no description");
                        preferenceEditor.putInt("score6", first);
                        preferenceEditor.putString("name6", n1);
                        preferenceEditor.putString("time6", d1);

                        preferenceEditor.putInt("score5", score);
                        preferenceEditor.putString("name5", name+" in hard level "+ dif+" mode");
                        preferenceEditor.putString("time5", time + "left");
                        preferenceEditor.commit();

                    }
                    else {
                        preferenceEditor.putInt("score6", score);
                        preferenceEditor.putString("name6", name+" in hard level "+ dif+" mode");
                        preferenceEditor.putString("time6", time + "left");
                        preferenceEditor.commit();
                    }
                }
                break;
            default:
                break;
        }

    }
    public void CheckNewScore() {
        switch (hardness) {
            case 1:
                int second = preferenceSettings.getInt("score2", 0);
                if (second < score) {
                    PopUpName();
                }
                break;
            case 2:
                int second2 = preferenceSettings.getInt("score4", 0);
                if (second2 < score) {
                    PopUpName();
                }
                break;
            case 3:
                int second3 = preferenceSettings.getInt("score6", 0);
                if (second3 < score) {
                    PopUpName();
                }
                break;
            default:
                break;
        }
    }

    public void PopUpName() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("New best score");
        alert.setMessage("Write your name");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                // Do something with value!
                name = String.valueOf(input.getText());
                CheckScore();


            }
        });


        alert.show();
    }

    public void ZoomIn(View view) {
        float scale = continent.getScaleX();
        continent.setScaleX((float) (scale/0.8));
        continent.setScaleY((float) (scale/0.8));
    }

    public void ZoomOut(View view) {
        float scale = continent.getScaleX();
        continent.setScaleX((float) (scale*0.8));
        continent.setScaleY((float) (scale*0.8));
    }
}
