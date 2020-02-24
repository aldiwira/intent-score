package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

import static id.putraprima.skorbola.MainActivity.DATA_KEY;

public class MatchActivity extends AppCompatActivity {

    //TODO
    //1.Menampilkan detail match sesuai data dari main activity
    //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
    //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"

    private static final String TAG = MatchActivity.class.getCanonicalName();
    private ImageView homeLogo;
    private ImageView awayLogo;
    private Uri homeUri;
    private Uri awayUri;
    private TextView homeTeamName;
    private TextView awayTeamName;
    private TextView homeScore;
    private TextView awayScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        homeLogo = findViewById(R.id.home_logo_match);
        awayLogo = findViewById(R.id.away_logo_match);
        homeTeamName = findViewById(R.id.txt_home);
        awayTeamName = findViewById(R.id.txt_away);
        homeScore = findViewById(R.id.score_home);
        awayScore = findViewById(R.id.score_away);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            try {
                ScoreData data = getIntent().getParcelableExtra(DATA_KEY);
                homeTeamName.setText(data.getHomeTeamName());
                awayTeamName.setText(data.getAwayTeamName());
                homeScore.setText(data.getScoreHomeTeam());
                awayScore.setText(data.getScoreAwayTeam());
                Bitmap homePath = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getHomeUri());
                Bitmap awayPath = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getAwayUri());
                homeLogo.setImageBitmap(homePath);
                awayLogo.setImageBitmap(awayPath);
            } catch (IOException e){
                Log.e(TAG, e.getMessage());
            }
        }
    }

    public void handleScoreHome(View view) {
        int scoreHome = Integer.parseInt(homeScore.getText().toString());
        String s;
        scoreHome++;
        s = Integer.toString(scoreHome);
        homeScore.setText(s);

    }

    public void handleAwayScore(View view) {
        int scoreAway = Integer.parseInt(awayScore.getText().toString());
        String s;
        scoreAway++;
        s = Integer.toString(scoreAway);
        awayScore.setText(s);
    }

    public void handleCekHasil(View view) {
        String homeName = homeTeamName.getText().toString();
        String awayName = awayTeamName.getText().toString();
        String scoreHome = homeScore.getText().toString();
        String scoreAway = awayScore.getText().toString();
        ScoreData data = new ScoreData(homeName, awayName, scoreHome, scoreAway, homeUri, awayUri);
        Intent in = new Intent(this, ResultActivity.class);
        in.putExtra(DATA_KEY, data);
        startActivity(in);
    }

}
