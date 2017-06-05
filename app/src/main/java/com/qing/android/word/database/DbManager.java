package com.qing.android.word.database;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;


/**
 * Created by qing on 2017/5/24.
 */

public class DbManager {

    private static final int BUFFER_SIZE = 400000;
    private static final String DB_FOLDER = "dictionary";
    private static final String DB_NAME = "wordBase.db";
    private static final String PACKAGE_NAME = "com.qing.android.word";
    private SQLiteDatabase database;
    private AssetManager mAssets;

    public DbManager(Context context) {
        mAssets = context.getAssets();
    }

    public void copyDatabase() {
        String DB_PATH = "/data"
                + Environment.getDataDirectory().getAbsolutePath() + "/"
                + PACKAGE_NAME + "/databases";

        copyDatabase(DB_PATH + '/' + DB_NAME);

    }


    private SQLiteDatabase copyDatabase(String dbfile) {

        String assetPath = DB_FOLDER + "/" + DB_NAME;

        try {
            File f = new File(dbfile);
            if (!(f.exists())) {
                InputStream is = mAssets.open(assetPath);
                OutputStream os = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    os.write(buffer, 0, count);
                }
                os.flush();
                os.close();
                is.close();
            }
            else {
                f.delete();
                InputStream is = mAssets.open(assetPath);
                OutputStream os = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    os.write(buffer, 0, count);
                }
                os.flush();
                os.close();
                is.close();
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
