package lv.chi.firelike

import android.animation.Animator
import android.graphics.PointF
import android.view.View


internal fun viewPositionPointF(view: View) = IntArray(2)
    .also { view.getLocationOnScreen(it) }
    .let { PointF(it.first().toFloat(), it.last().toFloat()) }

internal operator fun PointF.minus(other: PointF): PointF = PointF(
    this.x - other.x,
    this.y - other.y
)

internal class SimpleAnimationListener(
    private val onStart: (Animator?) -> Unit,
    private val onEnd: (Animator?) -> Unit,
    private val onCancel: ((Animator?) -> Unit)? = null
) : Animator.AnimatorListener {

    override fun onAnimationStart(animation: Animator?) {
        onStart(animation)
    }

    override fun onAnimationEnd(animation: Animator?) {
        onEnd(animation)
    }

    override fun onAnimationCancel(animation: Animator?) {
        onCancel?.invoke(animation) ?: onEnd(animation)
    }

    override fun onAnimationRepeat(animation: Animator?) {
    }
}
