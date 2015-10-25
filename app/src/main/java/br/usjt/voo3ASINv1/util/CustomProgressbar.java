package br.usjt.voo3ASINv1.util;

import android.app.ProgressDialog;
import android.content.Context;

import br.usjt.voo3ASINv1.R;


public class CustomProgressbar {
    private static ProgressDialog dialog;

    public static void show(final Context context) {
        show(context, false);
    }

    public static void show(final Context context, final boolean cancelable) {
        show(context, R.string.progress_carregando, cancelable);
    }

    public static void show(final Context context, final int messageId) {
        show(context, context.getString(messageId), false);
    }

    public static void show(final Context context, final int messageId, final boolean cancelable) {
        show(context, context.getString(messageId), cancelable);
    }

    public static void show(final Context context, final String message) {
        show(context, message, false);
    }

    public static void show(final Context context, final String message, final boolean cancelable) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(cancelable);
        dialog.show();
    }

    public static void hide() {
        if (dialog == null) return;
        dialog.dismiss();
        dialog = null;
    }

    public static void updateProgressMessage(String message) {
        if (dialog != null)
            dialog.setMessage(message);
    }
}