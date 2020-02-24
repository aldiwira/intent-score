package id.putraprima.skorbola;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class ScoreData implements Parcelable {
    private String homeTeamName;
    private String awayTeamName;
    private String scoreHomeTeam;
    private String scoreAwayTeam;
    private Uri homeUri;
    private Uri awayUri;
    private String status;

    //send logo and name team
    public ScoreData(String homeTeamName, String awayTeamName, Uri homeUri, Uri awayUri) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeUri = homeUri;
        this.awayUri = awayUri;
    }

    public ScoreData(String homeTeamName, String awayTeamName, String scoreHomeTeam, String scoreAwayTeam, Uri homeUri, Uri awayUri) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.scoreHomeTeam = scoreHomeTeam;
        this.scoreAwayTeam = scoreAwayTeam;
        this.homeUri = homeUri;
        this.awayUri = awayUri;
    }

    public ScoreData(String homeTeamName, String awayTeamName, String scoreHomeTeam, String scoreAwayTeam, Uri homeUri, Uri awayUri, String status) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.scoreHomeTeam = scoreHomeTeam;
        this.scoreAwayTeam = scoreAwayTeam;
        this.homeUri = homeUri;
        this.awayUri = awayUri;
        this.status = status;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public String getScoreHomeTeam() {
        return scoreHomeTeam;
    }

    public String getScoreAwayTeam() {
        return scoreAwayTeam;
    }

    public Uri getHomeUri() {
        return homeUri;
    }

    public Uri getAwayUri() {
        return awayUri;
    }

    public String getStatus() {
        return status;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public void setScoreHomeTeam(String scoreHomeTeam) {
        this.scoreHomeTeam = scoreHomeTeam;
    }

    public void setScoreAwayTeam(String scoreAwayTeam) {
        this.scoreAwayTeam = scoreAwayTeam;
    }

    public void setHomeUri(Uri homeUri) {
        this.homeUri = homeUri;
    }

    public void setAwayUri(Uri awayUri) {
        this.awayUri = awayUri;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.homeTeamName);
        dest.writeString(this.awayTeamName);
        dest.writeString(this.scoreHomeTeam);
        dest.writeString(this.scoreAwayTeam);
        dest.writeParcelable(this.homeUri, flags);
        dest.writeParcelable(this.awayUri, flags);
    }

    protected ScoreData(Parcel in) {
        this.homeTeamName = in.readString();
        this.awayTeamName = in.readString();
        this.scoreHomeTeam = in.readString();
        this.scoreAwayTeam = in.readString();
        this.homeUri = in.readParcelable(Uri.class.getClassLoader());
        this.awayUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Parcelable.Creator<ScoreData> CREATOR = new Parcelable.Creator<ScoreData>() {
        @Override
        public ScoreData createFromParcel(Parcel source) {
            return new ScoreData(source);
        }

        @Override
        public ScoreData[] newArray(int size) {
            return new ScoreData[size];
        }
    };
}
