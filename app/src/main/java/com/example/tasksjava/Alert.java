package com.example.tasksjava;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;



public class Alert {

    DbHelperTask dbHelperTask;
    DbHelperexEcutedTask dbHelperexEcutedTask;
    public interface OnPositiveButtonClickListener {
        void onPositiveButtonClick();
    }
    public void createTwoButtonsAlertDialog(String title, String name, Context context, String helper, OnPositiveButtonClickListener listener) {
        dbHelperTask = new DbHelperTask(context, Const.NAME_COLUMN,null ,1);
        dbHelperexEcutedTask = new DbHelperexEcutedTask(context, Const.NAME_DATABASE_TASKECUTED,null ,2);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(name);
        builder.setNegativeButton(Const.NO_BUTTON_EN, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.dismiss();
                    }
                });
        if (Const.HELPER_TASK.equals(helper)){
        builder.setPositiveButton(Const.YES_BUTTON_EN,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dbHelperTask.deleteTaskByName(name);
                        MainActivity mainActivity = new MainActivity();
                        listener.onPositiveButtonClick();

                    }
                });
        }
        if (Const.HELPER_ECUTED.equals(helper)){
            builder.setPositiveButton(Const.YES_BUTTON_EN,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            dbHelperexEcutedTask.deleteTaskByName(name);
                            EcutedActivity ecutedActivity = new EcutedActivity();
                            listener.onPositiveButtonClick();

                        }
                    });
        }
        else System.out.println("ERORR IN ALERT");
        builder.show();
    }



}
