package lv.chi.firelike

import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import lv.chi.firelike.providers.*
import java.util.*

object DefaultConfigs {

    fun straightUp(iconResource: Int) = IconEmitterConfig(
        iconResource = iconResource,
        interpolatorProvider = SingleInterpolatorProvider(LinearInterpolator()),
        durationProvider = ConstantValueProvider(400f),
        translationYProvider = ConstantOffsetProvider(-300f),
        translationXProvider = ConstantOffsetProvider(0f),
        scaleValueProvider = null,
        alphaValueProvider = FadeOutAlphaValueProvider()
    )

    fun flame(iconResource: Int, random: Random = Random()) = IconEmitterConfig(
        iconResource = iconResource,
        interpolatorProvider = RandomInterpolatorProvider(
            AccelerateDecelerateInterpolator(),
            LinearInterpolator(),
            AccelerateInterpolator()
        ),
        durationProvider = RandomValueInRangeProvider(
            minValue = 600f,
            maxValue = 800f,
            random = random
        ),
        translationYProvider = RandomOffsetInRangeProvider(
            minOffset = -300f,
            maxOffset = -600f,
            random = random
        ),
        translationXProvider = RandomCurveKeypointProvider(
            minOffset = 20f,
            maxOffset = 50f,
            maxKeyPoints = 6,
            random = random
        ),
        scaleValueProvider = RandomGrowShrinkProvider(
            minScale = 0.33f,
            random = random
        ),
        alphaValueProvider = FadeOutAlphaValueProvider()
    )
}