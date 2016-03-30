package example.com.guessthatnumber;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by bjorn on 2016-03-29.
 */
public class Game extends AppCompatActivity {

    private Button zeroB, oneB, twoB, threeB, fourB, fiveB, sixB, sevenB, eightB, nineB, clearB, inputB;
    private TextView previousGuesses, triesLeft, display;
    private String input = "";
    private Integer tries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridL);
        final int random = new Random().nextInt(MainActivity.getLevel()) + 1;
        setNoOfTries();
        triesLeft = (TextView) findViewById(R.id.triesLeft);
        triesLeft.setText("Tries left " + tries.toString());
        display = (TextView) findViewById(R.id.display);
        previousGuesses = (TextView) findViewById(R.id.prevField);

        //previousGuesses.setWidth(gridLayout.getMeasuredWidth());

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


        //inputB.setWidth(gridLayout.getWidth());

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
                if (tries > 0) {
                    tries--;
                    if (Integer.parseInt(input)== random) {
                        FragmentManager fm = getFragmentManager();
                        GameOverDialog god = GameOverDialog.newInstance("Win!", random, true, tries);
                        god.show(fm, "game_over_frag");
                    }

                    else if (Integer.parseInt(input) < random) {
                        previousGuesses.setText(previousGuesses.getText() + "\nmore than " + input);
                        triesLeft.setText("Tries left " + tries);
                    }
                    else {
                        previousGuesses.setText(previousGuesses.getText() + "\nless than " + input);
                        triesLeft.setText("Tries left " + tries);
                    }
                    input = "";
                    display.setText("0");
                }else {
                    FragmentManager fm = getFragmentManager();
                    GameOverDialog god = GameOverDialog.newInstance("Loss!", random, false, tries);
                    god.show(fm, "game_over_frag");
                }
            }


        });
    }

    private void setNoOfTries() {
        switch (MainActivity.getLevel()) {
            case 10:
                tries = 5;
                break;
            case 100:
                tries = 7;
                break;
            case 1000:
                tries = 11;
                break;
        }
    }
}