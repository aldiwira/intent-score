package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    public static String DATA_KEY = "DATA_KEY";
    private ImageView homeLogo;
    private ImageView awayLogo;
    private Uri homeUri;
    private Uri awayUri;
    private EditText homeTeamName;
    private EditText awayTeamName;


    //TODO
    //Fitur Main Activity
    //1. Validasi Input Home Team
    //2. Validasi Input Away Team
    //3. Ganti Logo Home Team
    //4. Ganti Logo Away Team
    //5. Next Button Pindah Ke MatchActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        homeTeamName = findViewById(R.id.home_team);
        awayTeamName = findViewById(R.id.away_team);
    }

    public void handleHomeTeamLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }


    public void handleAwayLogo(View view) {
        Intent inten = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(inten, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            return;
        }
        if (requestCode == 1){
            if (data != null){
                try {
                    homeUri = data.getData();
                    Bitmap home = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
                    homeLogo.setImageBitmap(home);
                } catch(IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        } else if (requestCode == 2){
            if (data != null){
                try {
                    awayUri = data.getData();
                    Bitmap home = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
                    awayLogo.setImageBitmap(home);
                } catch(IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handleNext(View view) {
        String homeTeam = homeTeamName.getText().toString();
        String awayTeam = awayTeamName.getText().toString();
        Uri homePath = homeUri;
        Uri awayPath = awayUri;
        String homeScore = "0";
        String awayScore = "0";

        if (homeTeam!= null&&homePath!=null&&awayTeam!=null&&awayPath!=null){
            ScoreData data = new ScoreData(homeTeam, awayTeam, homeScore, awayScore, homePath, awayPath);
            Intent inten = new Intent(this, MatchActivity.class);
            inten.putExtra(DATA_KEY, data);
            startActivity(inten);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Masukan logo dan nama team terlebih dahulu!!";
            int duration = Toast.LENGTH_SHORT;
            Toast tos = Toast.makeText(context, text, duration);
            tos.show();
        }
    }
}
