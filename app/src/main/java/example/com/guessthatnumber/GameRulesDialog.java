package example.com.guessthatnumber;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bjorn on 2016-03-30.
 */
public class GameRulesDialog extends DialogFragment {

    public GameRulesDialog() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Rules");
        alertDialogBuilder.setMessage(setRulesTextView());
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getContext(), Game.class);
                getContext().startActivity(intent);
            }
        });
        return alertDialogBuilder.create();
    }

    public String setRulesTextView () {

        switch (MainActivity.getLevel()) {
            case 10:
                return getText(R.string.easy_rules).toString();
                //rulesTextView.setText(R.string.easy_rules);
            case 100:
                return getText(R.string.medium_rules).toString();
                //rulesTextView.setText(R.string.medium_rules);
            case 1000:
                return getText(R.string.hard_rules).toString();
                //rulesTextView.setText(R.string.hard_rules);
            default:
                return getText(R.string.error).toString();
        }
    }
}
