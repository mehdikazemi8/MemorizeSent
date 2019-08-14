package ir.ugstudio.memorizesent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.when;

public class SentenceViewModelTest {

    private final String SAMPLE_SENTENCE_1 = "Hi there sample sentence here";
    private final String SAMPLE_SENTENCE_2 = "now this is sentence 2";

    @Mock
    private DataModel dataModel;

    private SentenceViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(dataModel.getSentenceStream()).thenReturn(Observable.fromArray(SAMPLE_SENTENCE_1, SAMPLE_SENTENCE_2));
        viewModel = new SentenceViewModel(dataModel);
    }

    @Test
    public void getSentenceStream_emits_whenRepeatFinishedIsCalled() {
        TestObserver<String> testSubscriber = new TestObserver<>();

        viewModel.getSentenceStream().subscribe(testSubscriber);

        viewModel.repeatFinished();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(SAMPLE_SENTENCE_1);

        viewModel.repeatFinished();
        testSubscriber.assertValues(SAMPLE_SENTENCE_1, SAMPLE_SENTENCE_2);
    }

    @Test
    public void getScrambledSentenceStream_emits_whenRepeatScrambleFinishedIsCalled() {
        TestObserver<String> testSubscriber = new TestObserver<>();

        viewModel.getScrambleSentenceStream().subscribe(testSubscriber);

        viewModel.repeatScrambleFinished();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        viewModel.repeatScrambleFinished();
        testSubscriber.assertValueCount(2);
    }

    @Test
    public void testScrambleMethod() {
        assert !SAMPLE_SENTENCE_2.equals(viewModel.scramble(SAMPLE_SENTENCE_2));
    }
}

