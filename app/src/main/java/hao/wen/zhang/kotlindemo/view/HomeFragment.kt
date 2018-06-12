package hao.wen.zhang.kotlindemo.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import hao.wen.zhang.kotlindemo.R
import hao.wen.zhang.kotlindemo.base.BaseFragment
import hao.wen.zhang.kotlindemo.module.HomeBean
import hao.wen.zhang.kotlindemo.prsent.HomeFragmentPresent
import hao.wen.zhang.kotlindemo.view.i.IHomeFragment
import kotlinx.android.synthetic.main.fragment_every.*


/**
 * 作者：ZWH
 * 创建日期： 2018/6/5 0005   下午 2:42
 * 描述说明：
 */
class HomeFragment : BaseFragment(), IHomeFragment<HomeBean> {
    override fun resetNet() {
        setNet()
    }
    var scroll=0

    lateinit var adapeter:HomeAdapeter;
    lateinit var mRecyclerView:RecyclerView;

    var isLoad=true

    override fun getData(data: HomeBean) {


        if (isLoad) {
            adapeter.addData(data.issueList[0].itemList)
            adapeter.loadMoreComplete()
        } else {
            adapeter.setNewData(data.issueList[0].itemList)

        }
    }

    val mPresent by lazy { HomeFragmentPresent() }
    override fun initView(view: View) {
        mRecyclerView  = view.findViewById<RecyclerView>(R.id.recycleview)
    }

    override fun getRes(): Int {
        return R.layout.fragment_every
    }

    override fun initData() {

        mPresent.attachView(this)

        setAdapeter()
        setNet()


    }

    private fun setNet() {
        mPresent.request()
    }
    /*
    * 设置适配器
    * 监听加载更多
    * */

    private fun setAdapeter() {

        mRecyclerView.layoutManager=LinearLayoutManager(activity)
        adapeter=HomeAdapeter(R.layout.item_home_content_layout, null)
        mRecyclerView.adapter= adapeter

        adapeter.setOnLoadMoreListener({
            isLoad = true

            mPresent.request()

        },mRecyclerView)
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                scroll+=dy
                if (scroll >= 400)
                mToolBar.visibility=View.VISIBLE
                else {

                    mToolBar.visibility=View.GONE
                }
            }
        })


    }
    /*
    * 实例化
    * */
    companion object {

        fun newInstans(): BaseFragment {
            return HomeFragment()

        }
    }

    class HomeAdapeter(layoutResId: Int, data: List<HomeBean.Issue.Item>?) : BaseQuickAdapter<HomeBean.Issue.Item, BaseViewHolder>(layoutResId, data) {

        lateinit var str: String
        override fun convert(helper: BaseViewHolder, item: HomeBean.Issue.Item) {
            helper.setText(R.id.tv_title, item.data.title)
            str = "#"

            for (tag in item.data.tags) {
                str = str + tag.name + "/"
            }
            helper.setText(R.id.tv_tag, str)
            helper.setText(R.id.tv_category, "#${item.data.category}")
            var iv_cover_feed = helper.getView<ImageView>(R.id.iv_cover_feed)
            Glide.with(mContext).load(item.data.cover.detail).into(iv_cover_feed)
            var iv_avatar = helper.getView<ImageView>(R.id.iv_avatar)
            Glide.with(mContext).load(item.data.author.icon).into(iv_avatar)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresent.detachView()
    }


}