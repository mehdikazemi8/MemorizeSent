package ir.ugstudio.memorizesent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;

import java.util.Observer;

import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;

public class MainViewModelTest {

    private MainViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new MainViewModel();
    }

    @Test
    public void getMemorizeStream_emits_whenMemorizeClickedIsCalled() {
        TestObserver<Boolean> testObserver = new TestObserver<>();
        viewModel.getMemorizeStream().subscribe(testObserver);
        viewModel.memorizeClicked();
        testObserver.assertValue(true);
    }

    @Test
    public void getScrambleStream_emits_whenScrambleClickedIsCalled() {
        TestObserver<Boolean> testObserver = new TestObserver<>();
        viewModel.getScrambleStream().subscribe(testObserver);
        viewModel.scrambleClicked();
        testObserver.assertValue(true);
    }
}
