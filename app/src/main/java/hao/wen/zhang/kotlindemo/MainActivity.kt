package hao.wen.zhang.kotlindemo

import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import hao.wen.zhang.kotlindemo.base.BaseAcitivty
import hao.wen.zhang.kotlindemo.base.BaseFragment
import hao.wen.zhang.kotlindemo.util.BottomNavigationViewHelper
import hao.wen.zhang.kotlindemo.view.FindFragment
import hao.wen.zhang.kotlindemo.view.HomeFragment
import hao.wen.zhang.kotlindemo.view.HotFragment
import hao.wen.zhang.kotlindemo.view.MeFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseAcitivty(), BottomNavigationView.OnNavigationItemSelectedListener {

    var currentInt=0
    var fragmens=ArrayList<BaseFragment>();
    var current:BaseFragment?=null
    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {
            R.id.home->
                switch(0)
            R.id.find-> switch(1)
            R.id.hot-> switch(2)
            R.id.me-> switch(3)
        }

        return true
    }


    override fun initView() {
        BottomNavigationViewHelper.disableShiftMode(bottom);
        bottom.setOnNavigationItemSelectedListener(this)
    }

    override fun getRes(): Int {
        return R.layout.activity_main

    }

    override fun initData() {
        addFragment()
        current=fragmens.get(0)
        supportFragmentManager.beginTransaction().add(R.id.fl_content, fragmens.get(0)).commit()


    }

    private fun addFragment() {
        fragmens.add(HomeFragment.newInstans())
        fragmens.add(FindFragment.newInstans())
        fragmens.add(HotFragment.newInstans())
        fragmens.add(MeFragment.newInstans())
    }

    fun switch(position: Int) {
        if (currentInt==position)return
        val trans = supportFragmentManager.beginTransaction()
        var fragment=fragmens.get(position)

        if (!fragment.isAdded) {
            trans.add(R.id.fl_content,fragment)
        }
        trans.show(fragment).hide(current)
        trans.commit()
        current=fragment
        currentInt=position
    }

}
