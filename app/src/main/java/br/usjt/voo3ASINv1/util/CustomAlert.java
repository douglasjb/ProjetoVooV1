package br.usjt.voo3ASINv1.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import br.usjt.voo3ASINv1.R;


public class CustomAlert {
    public static void showAlertMessage(Context context, int titleId, int titleMessage) {
        showAlertMessage(context, titleId, titleMessage, null);
    }

    public static void showAlertMessage(Context context, int message) {
        showAlertMessage(context, R.string.empty, message);
    }

    public static void showAlertMessage(Context context, String title, String titleMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(titleMessage);
        builder.setPositiveButton(R.string.dialog_ok, null);
        builder.setCancelable(true);
        builder.show();
    }

    public static void showPositiveAlertMessage(Context context, int titleId, int titleMessage, final OnMessageButtonPress callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId);
        builder.setMessage(titleMessage);
        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (callback != null)
                    callback.onMessageButtonPress(true);
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public static void showAlertMessage(Context context, int titleId, int titleMessage, final OnMessageButtonPress callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleId);
        builder.setMessage(titleMessage);
        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (callback != null)
                    callback.onMessageButtonPress(true);
            }
        });
        builder.setCancelable(true);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if (callback != null)
                    callback.onMessageButtonPress(false);
            }
        });
        builder.show();
    }

    public static void showConfirmationMessage(Context context, String title, String message, final OnMessageButtonPress callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.dialog_sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (callback != null)
                    callback.onMessageButtonPress(true);
            }
        });
        builder.setNegativeButton(R.string.dialog_nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (callback != null)
                    callback.onMessageButtonPress(false);
            }
        });
        builder.show();
    }

    public static void showConfirmationMessage(Context context, int titleId, int titleMessageId, final OnMessageButtonPress callback) {
        showConfirmationMessage(context, context.getString(titleId), context.getString(titleMessageId), callback);
    }

    public static void showConfirmationMessage(Context context, int titleMessage, final OnMessageButtonPress callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("");
        builder.setMessage(titleMessage);
        builder.setPositiveButton(R.string.dialog_sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (callback != null)
                    callback.onMessageButtonPress(true);
            }
        });
        builder.setNegativeButton(R.string.dialog_nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (callback != null)
                    callback.onMessageButtonPress(false);
            }
        });
        builder.show();
    }

    public interface OnMessageButtonPress {
        void onMessageButtonPress(boolean okButtonClick);
    }

    public interface OnMessageButtonPressWithInput {
        void onMessageButtonPress(boolean okButtonClick, EditText editText);
    }
}