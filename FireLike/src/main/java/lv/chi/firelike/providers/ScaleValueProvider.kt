@file:Suppress("unused")

package lv.chi.firelike.providers

import java.util.*

/**
 * Returns an array of key values for scale animations.
 *   2.0f - double height and width
 *   1.0f - no change
 *   0.5f - half of original width and height
 */
typealias ScaleValueProvider = () -> FloatArray

/**
 * Returns a value array for uniform scale change animation from 1.0 to endValue
 */
class ShrinkingScaleProvider(
    private val endValue: Float
) : ScaleValueProvider {
    override fun invoke() = floatArrayOf(1f, endValue)
}

/**
 * Returns a value array for randomised animation where target might grow a bit and
 * then shrink down to minValue.
 *
 * Returned array contains 4 values in following ranges (never less than minScale):
 *     0 -> 1
 *     1 -> [0.75; 1.25] or minScale
 *     2 -> [0.25; 0.75] or minScale
 *     3 -> minScale
 */
class RandomGrowShrinkProvider(
    private val minScale: Float,
    private val random: Random
) : ScaleValueProvider {

    override fun invoke() = floatArrayOf(
        1f,
        maxOf(minScale, 0.75f + (random.nextFloat() / 2)),
        maxOf(minScale, 0.25f + (random.nextFloat() / 2)),
        minScale
    )
}
