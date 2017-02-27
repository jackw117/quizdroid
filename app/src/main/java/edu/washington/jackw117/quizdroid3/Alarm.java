package edu.washington.jackw117.quizdroid3;

import android.app.AlarmManager;
import android.content.Context;

/**
 * Created by Jack on 2/26/2017.
 */

public class Alarm {
    Context c;

    public Alarm(Context c) {
        this.c = c;
    }

    public AlarmManager getAlarm() {
        return  (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
    }
}
