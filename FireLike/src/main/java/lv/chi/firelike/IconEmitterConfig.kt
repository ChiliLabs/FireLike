package lv.chi.firelike

import lv.chi.firelike.providers.*

/**
 * Configuration for [IconEmitterManager]
 */
data class IconEmitterConfig(
    /**
     * Image resource id that will be used to draw each icon.
     */
    val iconResource: Int,

    val interpolatorProvider: InterpolatorProvider,
    val durationProvider: DurationValueProvider,
    val translationYProvider: TranslationValueProvider,
    val translationXProvider: TranslationValueProvider,
    val scaleValueProvider: ScaleValueProvider?,
    val alphaValueProvider: AlphaValueProvider?
)
