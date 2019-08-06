package ir.ugstudio.memorizesent;

import androidx.annotation.NonNull;

import java.util.LinkedList;
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

    public void repeatFinished() {
        if (sentences.size() > 0) {
            sentenceSubject.onNext(sentences.remove());
        }
    }
}
