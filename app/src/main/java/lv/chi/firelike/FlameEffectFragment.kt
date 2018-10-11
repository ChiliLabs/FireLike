package lv.chi.firelike

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_sample.*
import lv.chi.firelike.common.SAMPLE_TEXT
import lv.chi.firelike.common.SampleAdapter
import java.util.*

class FlameEffectFragment : Fragment() {

    private val handler = Handler()
    private val random = Random()

    private lateinit var iconEmitter: IconEmitterManager

    private lateinit var randomClick: Runnable
    private lateinit var lm: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lm = LinearLayoutManager(context)

        iconEmitter = IconEmitterManager(sample_root, DefaultConfigs.flame(R.drawable.ic_thumb_blue, random))

        sample_recycler.apply {
            adapter = SampleAdapter(SAMPLE_TEXT) {
                iconEmitter.emitIconFromView(it)
            }
            layoutManager = lm
        }
    }


    override fun onResume() {
        super.onResume()

        randomClick = Runnable {
            val fvp = lm.findFirstCompletelyVisibleItemPosition()
            val lvp = lm.findLastCompletelyVisibleItemPosition()
            val p = fvp + random.nextInt(lvp - fvp)

            lm.findViewByPosition(p)?.findViewById<ImageView>(R.id.item_image)?.callOnClick()

            handler.postDelayed(randomClick, 100)
        }

        handler.postDelayed(randomClick, 500)
    }

    override fun onPause() {
        handler.removeCallbacks(randomClick)
        super.onPause()
    }
}
