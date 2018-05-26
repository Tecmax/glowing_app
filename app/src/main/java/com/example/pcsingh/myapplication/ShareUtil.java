package com.example.pcsingh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class ShareUtil {
    private static final String TAG = ShareUtil.class.getSimpleName();

    public static void shareData(Context ctx, String caption, final Bitmap bm) {
        Log.d(TAG, "caption : " + caption);
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        final Uri uri = getImageUri(ctx, bm, caption);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Share To Earn");
        ctx.startActivity(Intent.createChooser(shareIntent, "Share To Earn"));
    }

    private static Uri getImageUri(Context context, Bitmap bitmap, String caption) {
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        final String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, caption, null);
        return Uri.parse(path);
    }
}
