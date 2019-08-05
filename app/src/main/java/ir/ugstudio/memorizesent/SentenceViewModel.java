package ir.ugstudio.memorizesent;

import androidx.annotation.NonNull;

import java.util.LinkedList;
import java.util.Queue;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;


public class SentenceViewModel {

    @NonNull
    private final DataModel dataModel;

    private Queue<String> sentences = new LinkedList<>();

    @NonNull
    private final BehaviorSubject<String> sentenceSubject = BehaviorSubject.create();
    private CompositeSubscription subscription = new CompositeSubscription();

    public SentenceViewModel(@NonNull DataModel dataModel) {
        this.dataModel = dataModel;

        subscription.add(this.dataModel.getSentenceStream()
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(sentence -> sentences.add(sentence))
        );
    }

    @NonNull
    public Observable<String> getSentenceStream() {
        return sentenceSubject.asObservable();
    }

    public void repeatFinished() {
        if (sentences.size() > 0) {
            sentenceSubject.onNext(sentences.remove());
        }
    }
}
