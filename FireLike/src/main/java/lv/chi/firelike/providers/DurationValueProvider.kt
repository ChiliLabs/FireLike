@file:Suppress("unused")

package lv.chi.firelike.providers

import java.util.*
import kotlin.math.roundToInt

/**
 * Returns a duration value for all animations on a single icon.
 */
typealias DurationValueProvider = () -> Float

/**
 * Returns the provided value for every animation.
 */
class ConstantValueProvider(val value: Float) : DurationValueProvider {

    override fun invoke() = value
}

/**
 * Returns random value in provided range.
 */
class RandomValueInRangeProvider(
    private val minValue: Float,
    private val maxValue: Float,
    private val random: Random = Random()
) : DurationValueProvider {

    override fun invoke(): Float {
        val delta = Math.abs(maxValue - minValue).roundToInt()
        return minValue + random.nextInt(delta)
    }
}
