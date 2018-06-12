package hao.wen.zhang.kotlindemo.view






import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import hao.wen.zhang.kotlindemo.R
import hao.wen.zhang.kotlindemo.base.BaseFragment
import hao.wen.zhang.kotlindemo.module.TabInfoBean
import hao.wen.zhang.kotlindemo.prsent.HotPresent
import hao.wen.zhang.kotlindemo.view.i.IHotView
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * 作者：ZWH
 * 创建日期： 2018/6/5 0005   下午 3:01
 * 描述说明：
 */

class HotFragment : BaseFragment(), IHotView<TabInfoBean> {
    var infoBean :TabInfoBean?=null
    override fun getData(data: TabInfoBean) {
        infoBean=data
        addFragment()


        adapeter = HotAdapeter(childFragmentManager,fragments,titles)
        mViewPager.adapter=adapeter
        mTabLayout.setupWithViewPager(mViewPager)
        mViewPager.offscreenPageLimit=2
    }


    val titles = ArrayList<String>()

     val mPresent  by lazy { HotPresent() }
     var  adapeter :HotAdapeter?=null
       var fragments=ArrayList<Fragment>()

     override fun resetNet() {
         mPresent.request()
     }

     override fun initData() {
         mPresent.attachView(this)
         mPresent.request()
     }

     override fun initView(view: View) {



     }

     private fun addFragment() {
         for (i in 0 until 3) {
             fragments.add(RankFragment.newInstans(infoBean!!.tabInfo.tabList.get(i).apiUrl))

         }

         titles.add("月排行")
         titles.add("周排行")
         titles.add("日排行")
     }

     override fun getRes(): Int {
         return R.layout.fragment_hot
     }
     companion object {

         fun newInstans(): BaseFragment = HotFragment()

     }

     class HotAdapeter(fm: FragmentManager,var fragments:ArrayList<Fragment>,var titles:ArrayList<String >):FragmentPagerAdapter(fm){
         override fun getItem(position: Int): Fragment=fragments.get(position)


         override fun getCount(): Int =3

         override fun getPageTitle(position: Int): CharSequence=titles.get(position)

     }


}