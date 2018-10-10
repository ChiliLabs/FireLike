package lv.chi.firelike

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.PointF
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlin.math.roundToLong

/**
 * @param root - root view group in which icons will be animated
 * @param config - set of resources and providers to create animation for each icon.
 */
class IconEmitterManager(
    private val root: ViewGroup,
    private val config: IconEmitterConfig
) {

    private val viewCache = ViewCache()

    /**
     * Creates new animated icon using all the value providers in [IconEmitterConfig] starting
     * from provided view position.
     *
     * @param view - determines starting position of the animation
     */
    fun emitIconFromView(view: View) {
        val pos = viewPositionPointF(view).let {
            PointF(it.x + view.paddingStart, it.y + view.paddingTop)
        }

        val target = viewCache.popOrCreate {
            ImageView(root.context).apply { setImageResource(config.iconResource) }
        }

        createAnimatorSet(target, pos).start()
    }

    private fun createAnimatorSet(target: View, pos: PointF) = AnimatorSet().apply {
        duration = config.durationProvider().roundToLong()
        interpolator = config.interpolatorProvider()

        playTogether(getAnimators(target, pos - viewPositionPointF(root)))
        setTarget(target)
        addListener(SimpleAnimationListener(
            onStart = {
                root.addView(target)
            },
            onEnd = {
                root.removeView(target)
                viewCache.push(target)
            }
        ))
    }

    private fun getAnimators(target: View, position: PointF): List<ObjectAnimator> {
        val scaleValues = config.scaleValueProvider?.invoke()

        return listOfNotNull(
            ObjectAnimator.ofFloat(target, View.TRANSLATION_X, *config.translationXProvider(position.x)),
            ObjectAnimator.ofFloat(target, View.TRANSLATION_Y, *config.translationYProvider(position.y)),
            scaleValues?.let { ObjectAnimator.ofFloat(target, View.SCALE_X, *scaleValues) },
            scaleValues?.let { ObjectAnimator.ofFloat(target, View.SCALE_Y, *scaleValues) },
            config.alphaValueProvider?.let { ObjectAnimator.ofFloat(target, View.ALPHA, *it()) }
        )
    }
}
