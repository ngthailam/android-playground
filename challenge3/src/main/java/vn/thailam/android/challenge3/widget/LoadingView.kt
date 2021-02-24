package vn.thailam.android.challenge3.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import vn.thailam.android.challenge3.R

class LoadingView @JvmOverloads constructor(
    context: Context? = null,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val cvTopLeft by lazy { findViewById<CircleView>(R.id.cvTopLeft) }
    private val cvTopRight by lazy { findViewById<CircleView>(R.id.cvTopRight) }
    private val cvBottomRight by lazy { findViewById<CircleView>(R.id.cvBottomRight) }
    private val cvBottomLeft by lazy { findViewById<CircleView>(R.id.cvBottomLeft) }

    private val cvTopLeftAnimator get() = cvTopLeft.generateSpanAnimation(POSITION.TOP_LEFT)
    private val cvTopRightAnimator get() = cvTopRight.generateSpanAnimation(POSITION.TOP_RIGHT)
    private val cvBottomRightAnimator get() = cvBottomRight.generateSpanAnimation(POSITION.BOTTOM_RIGHT)
    private val cvBottomLeftAnimator get() = cvBottomLeft.generateSpanAnimation(POSITION.BOTTOM_LEFT)

    var spanAnimationDuration = 3_000L

    var rotateAnimationDuration = 6_000L

    var spanSize = 80f

    init {
        inflate(getContext(), R.layout.view_loading, this)


        val rotate = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = rotateAnimationDuration
            repeatCount = Animation.INFINITE
            interpolator = AccelerateDecelerateInterpolator()
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                    println("ZZLL repeasted...")
                    startSpanAnim()
                }

                override fun onAnimationEnd(animation: Animation?) = Unit
                override fun onAnimationStart(animation: Animation?) {
                    startSpanAnim()
                }
            })
        }
        startAnimation(rotate)
    }

    private fun startSpanAnim() {
        cvTopLeftAnimator.start()
        cvTopRightAnimator.start()
        cvBottomRightAnimator.start()
        cvBottomLeftAnimator.start()
    }

    private fun CircleView.generateSpanAnimation(position: POSITION): ViewPropertyAnimator {
        return animate()
            .setDuration(spanAnimationDuration)
            .translationXBy(spanSize * position.alphaX)
            .translationYBy(spanSize * position.alphaY)
            .withEndAction {
                animate()
                    .setDuration(spanAnimationDuration)
                    .translationXBy(spanSize * position.alphaX * -1)
                    .translationYBy(spanSize * position.alphaY * -1)
                    .start()
            }
    }

    enum class POSITION private constructor(val alphaX: Float, val alphaY: Float) {
        TOP_LEFT(-1f, -1f),
        TOP_RIGHT(1f, -1f),
        BOTTOM_RIGHT(1f, 1f),
        BOTTOM_LEFT(-1f, 1f);
    }
}