package ir.ugstudio.memorizesent;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.CompletableSubject;
import io.reactivex.subjects.PublishSubject;

class MainViewModel {

    PublishSubject<Boolean> memorizeSubject = PublishSubject.create();
    PublishSubject<Boolean> scrambleSubject = PublishSubject.create();

    public void memorizeClicked() {
        memorizeSubject.onNext(true);
    }

    public PublishSubject<Boolean> getMemorizeStream() {
        return memorizeSubject;
    }

    public void scrambleClicked() {
        scrambleSubject.onNext(true);
    }

    public PublishSubject<Boolean> getScrambleStream() {
        return scrambleSubject;
    }
}
