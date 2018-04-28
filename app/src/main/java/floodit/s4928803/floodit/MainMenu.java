package floodit.s4928803.floodit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button buttonInstructions = findViewById(R.id.bt_instructions);
        Button buttonDifficulty = findViewById(R.id.bt_difficulty);
        buttonInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchInstructions();
            }
        });
        buttonDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDifficulty();
            }
        });
    }

    private void launchDifficulty() {
        Intent intent = new Intent(this, SettingsActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, android.R.anim.fade_in, android.R.anim.fade_out);
        startActivity(intent, options.toBundle());
    }

    private void launchInstructions() {
        Intent intent = new Intent(this, InstructionsActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, android.R.anim.fade_in, android.R.anim.fade_out);
        startActivity(intent, options.toBundle());
    }
}
