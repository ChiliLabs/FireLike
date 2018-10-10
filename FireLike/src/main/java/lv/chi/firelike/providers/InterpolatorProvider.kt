@file:Suppress("unused")

package lv.chi.firelike.providers

import android.view.animation.Interpolator
import java.util.*

/**
 * Returns an [Interpolator] that will be used on all animations for a single icon.
 */
typealias InterpolatorProvider = () -> Interpolator

/**
 * Returns the provided interpolator for every animation.
 */
class SingleInterpolatorProvider(private val interpolator: Interpolator) : InterpolatorProvider {
    override fun invoke() = interpolator
}

/**
 * Returns random interpolator from the provided list.
 */
class RandomInterpolatorProvider(
    private vararg val interpolators: Interpolator,
    private val random: Random = Random()
) : InterpolatorProvider {

    override fun invoke(): Interpolator = interpolators[random.nextInt(interpolators.size)]
}
