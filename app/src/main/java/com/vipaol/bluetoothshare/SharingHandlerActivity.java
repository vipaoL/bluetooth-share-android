package com.vipaol.bluetoothshare;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class SharingHandlerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get intent, intent action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        Log.d("Received file type", type);

        if (type != null && action.equals(Intent.ACTION_SEND)) {
            sendFile(intent.getParcelableExtra(Intent.EXTRA_STREAM));
        }
        finish();
    }

    void sendFile(Uri fileUri) {
        if (fileUri != null) {
            Log.d("Received string", String.valueOf(fileUri));
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setPackage("com.android.bluetooth");
            i.putExtra(Intent.EXTRA_STREAM, fileUri);
            // set fake mime type to be able to send even restricted types, e.g. application/java-archive
            i.setType("image/jpeg");
            startActivity(i);
        }
    }
}