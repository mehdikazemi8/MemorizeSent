package ir.ugstudio.memorizesent;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;
    private Button memorizeButton;
    private Button scrambleButton;

    private MainViewModel mainViewModel;

//    @Nullable
//    private SentenceViewModel viewModel;

//    private CompositeDisposable disposable;

//    private Button showNext;
//    private TextView sentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compositeDisposable = new CompositeDisposable();
        mainViewModel = new MainViewModel();

        findViews();
        setOnClickListeners();

//        viewModel = new SentenceViewModel(getDataModel());
//        showNext = findViewById(R.id.show_next);
//        sentence = findViewById(R.id.sentence);
//
//        showNext.setOnClickListener(view -> viewModel.repeatFinished());
    }

    private void findViews() {
        memorizeButton = findViewById(R.id.memorize_button);
        scrambleButton = findViewById(R.id.scramble_button);
    }

    private void setOnClickListeners() {
        memorizeButton.setOnClickListener(view -> mainViewModel.memorizeClicked());
        scrambleButton.setOnClickListener(view -> mainViewModel.scrambleClicked());
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
        compositeDisposable.add(mainViewModel.getMemorizeStream().subscribe(this::openMemorizeFragment));
        compositeDisposable.add(mainViewModel.getScrambleStream().subscribe(this::openScrambleFragment));
    }

    private void openScrambleFragment(Boolean aBoolean) {
        ScrambleFragment fragment = ScrambleFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void openMemorizeFragment(Object aBoolean) {
        MemorizeFragment fragment = MemorizeFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void unBind() {
        compositeDisposable.dispose();
    }

    /*
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
    */
}
