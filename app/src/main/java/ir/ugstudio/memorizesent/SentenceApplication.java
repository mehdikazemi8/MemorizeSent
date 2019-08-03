package ir.ugstudio.memorizesent;

import android.app.Application;

import androidx.annotation.NonNull;

public class SentenceApplication extends Application {
    @NonNull
    private final DataModel dataModel;

    public SentenceApplication(@NonNull DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public SentenceApplication() {
        dataModel = new DataModel();
    }

    @NonNull
    public DataModel getDataModel() {
        return dataModel;
    }
}
