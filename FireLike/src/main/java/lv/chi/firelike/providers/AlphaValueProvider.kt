package lv.chi.firelike.providers

/**
 * Return an array of key values for alpha value animation.
 *    1.0f - no transparency
 *    0.0f - completely transparent
 */
typealias AlphaValueProvider = () -> FloatArray

/**
 * Returns a value array for uniform fade out animation.
 */
class FadeOutAlphaValueProvider : AlphaValueProvider {
    override fun invoke() = floatArrayOf(1f, 0f)
}

/**
 * Returns a value array for uniform fade in animation.
 */
class FadeInAlphaValueProvider : AlphaValueProvider {
    override fun invoke() = floatArrayOf(0f, 1f)
}
