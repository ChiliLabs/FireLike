package lv.chi.firelike

import android.view.View
import java.util.*

internal class ViewCache : LinkedList<View>() {

    fun popOrCreate(create: () -> View): View = if (size > 0) pop() else create()
}
