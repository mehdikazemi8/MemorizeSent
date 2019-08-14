package ir.ugstudio.memorizesent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import io.reactivex.disposables.CompositeDisposable;

public class ScrambleFragment extends Fragment {

    private TextView scrambledSentence;
    private Button repeatScrambleFinished;

    SentenceViewModel sentenceViewModel;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static ScrambleFragment newInstance() {
        return new ScrambleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scramble, container, false);

        scrambledSentence = view.findViewById(R.id.scrambled_sentence);
        repeatScrambleFinished = view.findViewById(R.id.repeat_scrambled_finished);

        sentenceViewModel = new SentenceViewModel(((SentenceApplication) (Objects.requireNonNull(getActivity())).getApplication()).getDataModel());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        repeatScrambleFinished.setOnClickListener(button -> sentenceViewModel.repeatScrambleFinished());
        sentenceViewModel.repeatScrambleFinished();
    }

    private void bind() {
        compositeDisposable.add(sentenceViewModel.getScrambleSentenceStream().subscribe(this::updateSentenceText));
    }

    private void updateSentenceText(String scrambledSentenceStr) {
        scrambledSentence.setText(scrambledSentenceStr);
    }

    private void unBind() {
        compositeDisposable.dispose();
    }

    @Override
    public void onPause() {
        unBind();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        bind();
    }
}