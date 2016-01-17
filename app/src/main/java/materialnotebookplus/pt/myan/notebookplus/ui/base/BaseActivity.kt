package materialnotebookplus.pt.myan.notebookplus.ui.base

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.FrameLayout
import materialnotebookplus.pt.myan.notebookplus.R
import materialnotebookplus.pt.myan.notebookplus.ui.fragment.NormalNotesFragment
import materialnotebookplus.pt.myan.notebookplus.ui.fragment.RecentNotesFragment
import materialnotebookplus.pt.myan.notebookplus.util.initFragment
import materialnotebookplus.pt.myan.notebookplus.util.stringFromRes
import org.jetbrains.anko.find
import org.jetbrains.anko.findOptional

abstract class BaseActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    val toolbar by lazy{find<Toolbar>(R.id.toolbar)}
    val drawer by lazy{findOptional<DrawerLayout>(R.id.drawer_layout)}
    val navigationView by lazy{findOptional<NavigationView>(R.id.nav_view)}
    val mainContent by lazy{find<FrameLayout>(R.id.mainContent)}

    val toggle by lazy{
        ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
    }

    abstract val layoutRes: Int;
    abstract val hasNavigationView: Boolean;
    abstract val toolbarTitle: Int?;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                if(hasNavigationView) R.layout.activity_base_navigation_drawer else R.layout.activity_base_coordinator_l
        )
        setSupportActionBar(toolbar)

        drawer?.let {
            it.setDrawerListener(toggle)
            toggle.syncState()
            navigationView?.setNavigationItemSelectedListener(this)
        }

        toolbarTitle?.let{
            title = stringFromRes(it)
        }

        navigationView?.let {
            val item = it.menu.findItem(R.id.normal_notes)
            onNavigationItemSelected(item)
            it.setCheckedItem(R.id.normal_notes)
        }
        mainContent.addView(layoutInflater.inflate(layoutRes, mainContent, false))

        if(!hasNavigationView) {
            supportActionBar.setDisplayHomeAsUpEnabled(true)
            supportActionBar.setHomeButtonEnabled(true)
        }
    }

    override fun onBackPressed() {
        drawer?.let{
            if (it.isDrawerOpen(GravityCompat.START)) {
                it.closeDrawer(GravityCompat.START)
            }
        }
        super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem?): Boolean {
        // Handle navigation view item clicks here.

        when(item?.itemId){
            R.id.recent_notes -> initFragment(R.id.container, RecentNotesFragment())
            R.id.normal_notes -> initFragment(R.id.container, NormalNotesFragment())

            R.id.recent_notes2 -> initFragment(R.id.container, RecentNotesFragment())
            R.id.normal_notes2 -> initFragment(R.id.container, NormalNotesFragment())
        }
        drawer?.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}