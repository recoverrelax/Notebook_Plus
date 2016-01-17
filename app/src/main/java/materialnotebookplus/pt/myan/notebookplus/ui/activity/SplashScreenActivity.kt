package materialnotebookplus.pt.myan.notebookplus.ui.activity

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import materialnotebookplus.pt.myan.notebookplus.R
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor

class SplashScreenActivity : AppCompatActivity() {

    val title by lazy { find<TextView>(R.id.spash_title) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        doAnimations()
    }

    private fun doAnimations() {

        /**
         * Set initial Title Scalling to 0.7f
         */
        title.scaleY = 0.1f
        title.scaleX = 0.1f

        AnimatorSet().apply {
            setDuration(3000)
            playTogether(
                    ObjectAnimator.ofFloat(title, "scaleX", 0.1f, 0.8f, 0.9f, 1.0f),
                    ObjectAnimator.ofFloat(title, "scaleY", 0.1f, 0.8f, 0.9f, 1.0f),
                    ObjectAnimator.ofFloat(title, "translationY", 1000f, -100f, 0f)
            )
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) { }
                override fun onAnimationCancel(animation: Animator?) { }
                override fun onAnimationStart(animation: Animator?) { }

                override fun onAnimationEnd(animation: Animator?) {
                    startActivity(
                            intentFor<MainActivity>().clearTop()
                    )
                }
            })
        }.start()
    }
}