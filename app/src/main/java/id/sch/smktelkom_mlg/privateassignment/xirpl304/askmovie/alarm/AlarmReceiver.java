package id.sch.smktelkom_mlg.privateassignment.xirpl304.askmovie.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import id.sch.smktelkom_mlg.privateassignment.xirpl304.askmovie.Main2Activity;
import id.sch.smktelkom_mlg.privateassignment.xirpl304.askmovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl304.askmovie.database.Task;
import id.sch.smktelkom_mlg.privateassignment.xirpl304.askmovie.database.TaskDataBase;


public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        TaskDataBase dataBase = new TaskDataBase(context);
        ArrayList<Task> tasks = dataBase.getAllTasks("Current");
        int i = 0;

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate = df.format(Calendar.getInstance().getTime());
        DateFormat tdf = new SimpleDateFormat("HH:mm");
        String currentTime = tdf.format(Calendar.getInstance().getTime());
        for (Task ts : tasks) {
            String taskDate = ts.getDate();
            String taskTime = ts.getTime();
            Intent notificationIntent = new Intent(context, Main2Activity.class);
            if (taskTime.equals("")) {
                if (taskDate.equals(currentDate) && currentTime.equals("09:00")) {
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                            notificationIntent, i);
                    Notification n = new Notification.Builder(context)
                            .setVibrate(new long[]{1000, 1000})
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Reminder")
                            .setContentText(ts.getTask())
                            .setContentIntent(pendingIntent).setAutoCancel(true)
                            .build();

                    NotificationManager notificationManager = (NotificationManager) context
                            .getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(i++, n);
                }
            } else {
                if (taskDate.equals(currentDate) && taskTime.equals(currentTime)) {
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                            notificationIntent, i);
                    Notification n = new Notification.Builder(context)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Reminder")
                            .setContentText(ts.getTask())
                            .setContentIntent(pendingIntent).setAutoCancel(true)
                            .build();

                    NotificationManager notificationManager = (NotificationManager) context
                            .getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(i++, n);
                }
            }

        }
    }
}
