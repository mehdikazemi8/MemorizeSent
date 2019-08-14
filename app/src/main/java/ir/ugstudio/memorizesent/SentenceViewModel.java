package ir.ugstudio.memorizesent;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;

public class SentenceViewModel {

    @NonNull
    private final DataModel dataModel;

    private Queue<String> sentences = new LinkedList<>();

    @NonNull
    private final BehaviorSubject<String> sentenceSubject = BehaviorSubject.create();
    @NonNull
    private final BehaviorSubject<String> scrambleSentenceSubject = BehaviorSubject.create();

    private CompositeDisposable disposable = new CompositeDisposable();

    public SentenceViewModel(@NonNull DataModel dataModel) {
        this.dataModel = dataModel;

        disposable.add(this.dataModel.getSentenceStream()
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(sentence -> sentences.add(sentence))
        );
    }

    @NonNull
    public Observable<String> getSentenceStream() {
        return sentenceSubject;
    }

    @NonNull
    public Observable<String> getScrambleSentenceStream() {
        return scrambleSentenceSubject;
    }

    public void repeatFinished() {
        if (sentences.size() > 0) {
            sentenceSubject.onNext(sentences.remove());
        }
    }

    public void repeatScrambleFinished() {
        if (sentences.size() > 0) {
            scrambleSentenceSubject.onNext(scramble(sentences.remove()));
        }
    }

    public String scramble(String s) {
        List<String> words = Arrays.asList(s.split(" "));
        Collections.shuffle(words);

        StringBuilder scrambledSentence = new StringBuilder();
        for (String word : words) {
            scrambledSentence.append(" ").append(word);
        }
        return scrambledSentence.toString();
    }
}
