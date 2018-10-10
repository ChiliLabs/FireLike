@file:Suppress("unused")

package lv.chi.firelike.providers

import java.lang.Math.abs
import java.util.*
import kotlin.math.roundToInt

/**
 * Returns an array of key positions for movement animation along one of the axis.
 * @param start - initial position of the animation.
 */
typealias TranslationValueProvider = (start: Float) -> FloatArray

/**
 * Returns an array for movement animation for specified offset.
 * Direction depends on the sign of the offset.
 */
class ConstantOffsetProvider(val offset: Float) : TranslationValueProvider {

    override fun invoke(start: Float) = floatArrayOf(start, start + offset)
}

/**
 * Returns an array for movement animation for some random offset in specified range.
 * Direction depends on the sign of the minOffset.
 * (maxOffset should have same sign, although it is not enforced)
 */
class RandomOffsetInRangeProvider(
    private val minOffset: Float,
    private val maxOffset: Float,
    private val random: Random = Random()
) : TranslationValueProvider {

    override fun invoke(start: Float): FloatArray {
        val delta = abs(maxOffset - minOffset)
        val actualOffset =
            if (minOffset > 0) minOffset + random.nextInt(delta.toInt())
            else minOffset - random.nextInt(delta.toInt())

        return floatArrayOf(start, start + actualOffset)
    }
}

/**
 * Returns an array for movement animation between up to maxKeyPoints points
 * placed randomly around start position, making nice curved path.
 *
 * Array elements are generated as follows:
 *     0 - always `start` value
 *     1 - random value in a range [minOffset, minOffset + delta] to left or right from the start
 *     2 - random value in the same range, but in opposite direction
 *     3 - similar to 1st
 *     4 - similar to 2nd
 *     ... etc up to maxKeyPoints + 1
 * For simplicity offset values should be positive.
 */
class RandomCurveKeypointProvider(
    private val minOffset: Float,
    private val maxOffset: Float,
    private val maxKeyPoints: Int,
    private val random: Random = Random()
) : TranslationValueProvider {

    override fun invoke(start: Float): FloatArray {
        val direction = if (random.nextBoolean()) 1 else -1
        val arraySize = random.nextInt(maxOf(1, maxKeyPoints - 1))
        val deltaOffset = abs(maxOffset - minOffset).roundToInt()

        fun calculateRandomOffset() = direction * (minOffset + random.nextInt(deltaOffset))

        return listOf(
            listOf(start),
            (0..arraySize).map {
                if (it % 2 == 0) start + calculateRandomOffset()
                else start - calculateRandomOffset()
            }
        ).flatten().toFloatArray()
    }
}
