package ir.ugstudio.memorizesent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Nullable
    private SentenceViewModel viewModel;

    private CompositeDisposable disposable;

    private Button showNext;
    private TextView sentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new SentenceViewModel(getDataModel());
        showNext = findViewById(R.id.show_next);
        sentence = findViewById(R.id.sentence);

        showNext.setOnClickListener(view -> viewModel.repeatFinished());
    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    @Override
    protected void onPause() {
        unBind();
        super.onPause();
    }

    private void bind() {
        assert viewModel != null;

        disposable = new CompositeDisposable();

        disposable.add(viewModel
                .getSentenceStream()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showNextSentence));
        viewModel.repeatFinished();
    }

    private void showNextSentence(String s) {
        sentence.setText(s);
    }

    private void unBind() {
        disposable.dispose();
    }

    private DataModel getDataModel() {
        return ((SentenceApplication) getApplication()).getDataModel();
    }
}
