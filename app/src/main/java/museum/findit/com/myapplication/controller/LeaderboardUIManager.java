package museum.findit.com.myapplication.controller;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import museum.findit.com.myapplication.model.Player;

public class LeaderboardUIManager {
    public Boolean isLeaderboardCreated = false;
    public HashMap<String, HashMap<String, TextView>> leaderboardHashMap;

    public LeaderboardUIManager() {
    }

    public void updateLeaderboard(HashMap<String, Player> players) {
        if (leaderboardHashMap == null) return;
        for (Map.Entry<String, Player> entry : players.entrySet()) {
            Player player = entry.getValue();
            HashMap<String, TextView> rowHashMap = leaderboardHashMap.get(entry.getKey());
            updateRow(rowHashMap, player);
        }
    }

    public void updateRow(HashMap<String, TextView> rowHashMap, Player player) {
        if (rowHashMap == null) return;
        TextView scoreTextView = rowHashMap.get("score");
        TextView percentageTextView = rowHashMap.get("percentage");

        if (scoreTextView == null || percentageTextView == null) return;
        scoreTextView.setText(player.score.toString());
        percentageTextView.setText(player.percentage.toString() + "%");
    }

    public HashMap<String, HashMap<String, TextView>> createLeaderboard(Context context, TableLayout leaderboardLayout, HashMap<String, Player> players) {
        HashMap<String, HashMap<String, TextView>> leaderboardHashMap = new HashMap<String, HashMap<String, TextView>>();
        for (Map.Entry<String, Player> entry : players.entrySet()) {
            Player player = entry.getValue();
            HashMap<String, TextView> rowHashMap = addTableRow(context, leaderboardLayout, player.username, player.score, player.percentage);
            leaderboardHashMap.put(entry.getKey(), rowHashMap);
        }
        return leaderboardHashMap;
    }

    public HashMap<String, TextView> addTableRow(Context context, TableLayout leaderboardLayout, String username, Integer score, Integer percentage) {
        TableRow row = new TableRow(context);
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT, 1.0f);
        row.setLayoutParams(tableRowParams);
        leaderboardLayout.addView(row);

        addUsernameTextView(context, row, username);
        TextView scoreTextView = addNumberTextView(context, row, score);
        TextView percentageTextView = addNumberTextView(context, row, percentage);

        HashMap<String, TextView> textViewHashMap = new HashMap<String, TextView>();
        textViewHashMap.put("score", scoreTextView);
        textViewHashMap.put("percentage", percentageTextView);
        return textViewHashMap;
    }

    public void addUsernameTextView(Context context, TableRow row, String username) {
        TextView usernameTextView = new TextView(context);

        TableRow.LayoutParams textViewParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1.0f);
        textViewParams.topMargin = 30;
        usernameTextView.setLayoutParams(textViewParams);

        usernameTextView.setTextColor(Color.parseColor("#ff669900"));
        usernameTextView.setTextSize(18);
        usernameTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        usernameTextView.setFocusable(true);
        usernameTextView.setFocusableInTouchMode(true);
        usernameTextView.setGravity(Gravity.CENTER);
        usernameTextView.setText(username);
        row.addView(usernameTextView);
    }

    public TextView addNumberTextView(Context context, TableRow row, Integer number) {
        TextView numberTextView = new TextView(context);

        TableRow.LayoutParams textViewParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1.0f);
        textViewParams.topMargin = 30;
        numberTextView.setLayoutParams(textViewParams);

        numberTextView.setTextColor(Color.BLACK);
        numberTextView.setTextSize(18);
        numberTextView.setGravity(Gravity.CENTER);
        numberTextView.setText(number.toString());
        row.addView(numberTextView);
        return numberTextView;
    }
}