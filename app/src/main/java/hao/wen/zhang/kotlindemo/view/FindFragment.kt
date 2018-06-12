package hao.wen.zhang.kotlindemo.view

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import hao.wen.zhang.kotlindemo.R
import hao.wen.zhang.kotlindemo.base.BaseFragment
import hao.wen.zhang.kotlindemo.util.TableLayoutUtils


/**
 * 作者：ZWH
 * 创建日期： 2018/6/5 0005   下午 3:01
 * 描述说明：
 */
class FindFragment : BaseFragment() {
    val adapeter by lazy { FindViewPage(fragments,titls,childFragmentManager) }
    var titls:List<String> = arrayListOf("关注","分类")
    var fragments:List<Fragment> = arrayListOf(FollowFragment.newInstans(),CategoryFragment.newInstans())
    lateinit var mViewPage:ViewPager
    lateinit var mTabLayout:TabLayout


     fun setAdapeter() {
        mViewPage.adapter=adapeter
        mTabLayout.setupWithViewPager(mViewPage)
        TableLayoutUtils.setIndicator(mTabLayout,60,50)


    }





    override fun resetNet() {

    }

    override fun initData() {

        setAdapeter()
    }

    override fun initView(view: View) {
        mViewPage=view.findViewById<ViewPager>(R.id.mFindViewPager)
        mTabLayout=view.findViewById<TabLayout>(R.id.mFindTabLayout)

    }

    override fun getRes(): Int {
        return R.layout.fragment_find
    }
    companion object {

        fun newInstans(): BaseFragment {
            return FindFragment()

        }
    }
    class FindViewPage(var fragments:List<Fragment>,var titls:List<String>,fm:FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
           return fragments.get(position)
        }

        override fun getPageTitle(position: Int): CharSequence?= titls.get(position)

        override fun getCount(): Int=fragments.size

    }
}