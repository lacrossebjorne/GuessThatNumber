package example.com.guessthatnumber;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by bjorn on 2016-03-29.
 */
public class Game extends AppCompatActivity {

    private Button zeroB, oneB, twoB, threeB, fourB, fiveB, sixB, sevenB, eightB, nineB, clearB, inputB;
    private TextView rules, triesLeftText, display;
    private String input = "";
    private Integer tries = 1;
    private Integer noOfTries;
    private int columnCount = 0;
    private TableLayout prevGuessesTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        final int random = new Random().nextInt(MainActivity.getLevel()) + 1;
        rules = (TextView) findViewById(R.id.rules);
        if(MainActivity.getLevel() == 10)
            rules.setText("Find the number between 1 and 10");
        else if (MainActivity.getLevel() == 100)
            rules.setText("Find the number between 1 and 100");
        else
            rules.setText("Find the number between 1 and 1000");
        noOfTries = setNoOfTries();
        triesLeftText = (TextView) findViewById(R.id.triesLeft);
        triesLeftText.setText(noOfTries.toString());
        display = (TextView) findViewById(R.id.display);
        prevGuessesTable = (TableLayout) findViewById(R.id.prevTable);

        zeroB = (Button) findViewById(R.id.zeroB);
        oneB = (Button) findViewById(R.id.oneB);
        twoB = (Button) findViewById(R.id.twoB);
        threeB = (Button) findViewById(R.id.threeB);
        fourB = (Button) findViewById(R.id.fourB);
        fiveB = (Button) findViewById(R.id.fiveB);
        sixB = (Button) findViewById(R.id.sixB);
        sevenB = (Button) findViewById(R.id.sevenB);
        eightB = (Button) findViewById(R.id.eightB);
        nineB = (Button) findViewById(R.id.nineB);
        clearB = (Button) findViewById(R.id.clearB);
        inputB = (Button) findViewById(R.id.inputB);

        zeroB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = input + 0;
                display.setText(input);
            }
        });

       oneB.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               input = input + 1;
               display.setText(input);
           }
       });

       twoB.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               input = input + 2;
               display.setText(input);
           }
       });

       threeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = input + 3;
                display.setText(input);
            }
        });

        fourB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = input + 4;
                display.setText(input);
            }
        });

        fiveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = input + 5;
                display.setText(input);
            }
        });

        sixB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = input + 6;
                display.setText(input);
            }
        });

        sevenB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = input + 7;
                display.setText(input);
            }
        });

        eightB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = input + 8;
                display.setText(input);
            }
        });

        nineB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = input + 9;
                display.setText(input);
            }
        });

        clearB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = "";
                display.setText("0");
            }
        });

        inputB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.equals("")) {
                    if (tries < noOfTries) {
                        if (Integer.parseInt(input) == random) {
                            Integer triesLeft = noOfTries - tries;
                            triesLeftText.setText(triesLeft.toString());
                            FragmentManager fm = getFragmentManager();
                            GameOverDialog god = GameOverDialog.newInstance("Win!", random, true, tries);
                            god.show(fm, "game_over_frag");
                        } else if (Integer.parseInt(input) < random) {
                            insertPreviousGuess("more than " + input);
                            Integer triesLeft = noOfTries - tries;
                            triesLeftText.setText(triesLeft.toString());
                        } else {
                            insertPreviousGuess("less than " + input);
                            Integer triesLeft = noOfTries - tries;
                            triesLeftText.setText(triesLeft.toString());
                        }
                        input = "";
                        display.setText("0");
                        tries++;
                    } else {
                        FragmentManager fm = getFragmentManager();
                        GameOverDialog god = GameOverDialog.newInstance("Loss!", random, false, tries);
                        god.show(fm, "game_over_frag");
                    }
                }
            }
        });
    }

    private Integer setNoOfTries() {
        switch (MainActivity.getLevel()) {
            case 10:
                return 5;
            case 100:
                return 7;
            case 1000:
                return 11;
            default:
                return 0;
        }
    }

    private void insertPreviousGuess(String guess) {
        TextView text = new TextView(Game.this);
        text.setText(guess);
        text.setTextSize(12);
        text.setPadding(2, 0, 0, 0);
        text.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        if((columnCount % 2 == 0) || columnCount == 0) {
            TableRow row = new TableRow(Game.this);
            row.addView(text);
            text.setGravity(Gravity.START);
            row.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            prevGuessesTable.addView(row);
        } else {
            int rowCount = prevGuessesTable.getChildCount();
            TableRow row = (TableRow) prevGuessesTable.getChildAt(rowCount - 1);
            text.setGravity(Gravity.START);
            row.addView(text);
        }
        columnCount++;
    }
}