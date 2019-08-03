package ir.ugstudio.memorizesent;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import rx.Observable;
import rx.Subscriber;

public final class FileDataReader {
    public static Observable<String> readFileByLine(@NonNull String filename) {
        return Observable.create(subscriber -> {
            InputStream inputStream = FileDataReader.class.getResourceAsStream(filename);

            if (inputStream == null) {
                subscriber.onError(new IllegalArgumentException("File not found on classpath: " + filename));
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    subscriber.onNext(line);
                    Log.d("Tag", "reader " + line);
                }
                reader.close();
                subscriber.onCompleted();
            } catch (IOException e) {
                subscriber.onError(e);
            }
        });
    }
}
