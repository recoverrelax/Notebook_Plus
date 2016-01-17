package materialnotebookplus.pt.myan.notebookplus.util

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.View

fun RecyclerView.hideFabOnScroll(fab: FloatingActionButton){
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (dy > 0 && fab.isVisible()) {
                fab.hide()
            } else if (dy < 0 && fab.notVisible()) {
                fab.show()
            }
        }
    })
}

fun View.isVisible() = visibility == View.VISIBLE
fun View.notVisible() = visibility != View.VISIBLE
fun View.setGone() { visibility = View.GONE }
