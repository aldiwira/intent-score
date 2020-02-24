package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static id.putraprima.skorbola.MainActivity.DATA_KEY;

public class ResultActivity extends AppCompatActivity {

    private TextView statusMatch;
    ScoreData data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        statusMatch = findViewById(R.id.status_match);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            data = getIntent().getParcelableExtra(DATA_KEY);
            statusMatch.setText("The Winner is "+checkWinner());
        }

    }

    private String checkWinner() {
        String stat;
        int home = Integer.parseInt(data.getScoreHomeTeam());
        int away = Integer.parseInt(data.getScoreAwayTeam());

        if (home > away){
            stat = data.getHomeTeamName();
        } else if(home < away){
            stat = data.getAwayTeamName();
        } else {
            stat = "draw";
        }
        return stat;
    }


}
