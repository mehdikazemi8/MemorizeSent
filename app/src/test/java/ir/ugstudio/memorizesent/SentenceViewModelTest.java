package ir.ugstudio.memorizesent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.when;

public class SentenceViewModelTest {

    private final String SAMPLE_SENTENCE_1 = "Hi there sample sentence here";
    private final String SAMPLE_SENTENCE_2 = "now 2";

    @Mock
    private DataModel dataModel;

    private SentenceViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(dataModel.getSentenceStream()).thenReturn(Observable.from(new String[]{SAMPLE_SENTENCE_1, SAMPLE_SENTENCE_2}));
        viewModel = new SentenceViewModel(dataModel);
    }

    @Test
    public void getSentenceStream_emits_whenRepeatFinishedIsCalled() {
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        viewModel.getSentenceStream().subscribe(testSubscriber);

        viewModel.repeatFinished();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(SAMPLE_SENTENCE_1);

        viewModel.repeatFinished();
        testSubscriber.assertValues(SAMPLE_SENTENCE_1, SAMPLE_SENTENCE_2);
    }
}

