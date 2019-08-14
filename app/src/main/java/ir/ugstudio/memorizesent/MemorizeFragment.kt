package ir.ugstudio.memorizesent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MemorizeFragment : Fragment() {

    var compositeDisposable = CompositeDisposable()
    private lateinit var sentenceViewModel: SentenceViewModel

    private lateinit var sentence: TextView
    private lateinit var showNext: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // todo, must get dataModel from Application
        sentenceViewModel = SentenceViewModel(DataModel())

        return inflater.inflate(R.layout.fragment_memorize, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sentence = view.findViewById(R.id.sentence)
        showNext = view.findViewById(R.id.show_next)

        showNext.setOnClickListener { sentenceViewModel.repeatFinished() }

        sentenceViewModel.repeatFinished()
    }

    private fun bind() {
        compositeDisposable.add(sentenceViewModel.sentenceStream
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { showNextSentence(it) })
    }

    private fun showNextSentence(s: String) {
        sentence.text = s
    }

    private fun unBind() {
        compositeDisposable.dispose()
    }

    override fun onPause() {
        unBind()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        bind()
    }

    companion object {
        @JvmStatic
        fun newInstance(): MemorizeFragment {
            return MemorizeFragment()
        }
    }
}