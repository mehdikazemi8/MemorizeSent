package ir.ugstudio.memorizesent;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

class MainViewModel {

    BehaviorSubject<Boolean> memorizeSubject = BehaviorSubject.create();
    BehaviorSubject<Boolean> scrambleSubject = BehaviorSubject.create();

    public void memorizeClicked() {
        memorizeSubject.onNext(true);
    }

    public Observable<Boolean> getMemorizeStream() {
        return memorizeSubject;
    }

    public void scrambleClicked() {
        scrambleSubject.onNext(true);
    }

    public Observable<Boolean> getScrambleStream() {
        return scrambleSubject;
    }
}
