package ir.ugstudio.memorizesent;

import androidx.annotation.NonNull;

import io.reactivex.Observable;

public class DataModel {

    @NonNull
    private static final String SENTENCE_FILE = "/res/raw/sentences.txt";

    @NonNull
    public Observable<String> getSentenceStream() {
        return FileDataReader.readFileByLine(SENTENCE_FILE);
    }
}
