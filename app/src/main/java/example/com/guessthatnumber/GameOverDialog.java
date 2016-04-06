package example.com.guessthatnumber;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by bjorn on 2016-03-30.
 */
public class GameOverDialog extends DialogFragment {

    public GameOverDialog () {}

    public static GameOverDialog newInstance(String title, int random, boolean winLoss, int tries) {
        GameOverDialog gameOverDialog = new GameOverDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("theNumber", random);
        bundle.putBoolean("winOrLoss", winLoss);
        bundle.putInt("tries", tries);
        gameOverDialog.setArguments(bundle);
        return gameOverDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        boolean winloss = getArguments().getBoolean("winOrLoss");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        if (winloss) {
            int random = getArguments().getInt("theNumber");
            int tries = getArguments().getInt("tries");
            alertDialogBuilder.setMessage("You guessed the secret number: " + random + " with only " + tries + " guesses.\nClick OK to play again.");
        }else
            alertDialogBuilder.setMessage("Bummer you didn't find the number.\n Now you will always wonder...");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(intent);
            }
        });
        return alertDialogBuilder.create();
    }
}
