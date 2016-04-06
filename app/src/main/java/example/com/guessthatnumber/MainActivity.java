package example.com.guessthatnumber;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.net.nsd.NsdManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
    private Button easy, medium, hard;
    private static int level;
    private TextView logoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        easy = (Button) findViewById(R.id.easyButton);
        medium = (Button) findViewById(R.id.mediumButton);
        hard = (Button) findViewById(R.id.hardButton);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = 10;
                showGameRulesDialog();
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = 100;
                showGameRulesDialog();
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = 1000;
                showGameRulesDialog();
            }
        });
    }

    public static int getLevel() {
        return level;
    }

    private void showGameRulesDialog() {
        FragmentManager fm = getFragmentManager();
        GameRulesDialog gameDialog = new GameRulesDialog();
        gameDialog.show(fm, "game_rules_fragment");
    }
}
