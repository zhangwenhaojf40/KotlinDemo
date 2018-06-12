package hao.wen.zhang.kotlindemo.view

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import hao.wen.zhang.kotlindemo.R
import hao.wen.zhang.kotlindemo.adapter.FocusItemAdapter
import hao.wen.zhang.kotlindemo.base.BaseFragment
import hao.wen.zhang.kotlindemo.module.FollowBean
import hao.wen.zhang.kotlindemo.prsent.FollowPresent
import hao.wen.zhang.kotlindemo.view.i.IFollowView
import kotlinx.android.synthetic.main.fragment_follow.*

/**
 * 作者：ZWH
 * 创建日期： 2018/6/11 0011   下午 2:08
 * 描述说明：
 */
class FollowFragment : BaseFragment(), IFollowView<FollowBean> {
    val adapter by lazy { FollowAdapter(R.layout.item_focus_layout,null) }
    val mPresent by lazy { FollowPresent() }
    var isLoading=false
    override fun getData(data: FollowBean) {
        if (isLoading) {
            adapter.addData(data.itemList)
            adapter.loadMoreComplete()
        } else {
            adapter.setNewData(data.itemList)

        }
    }

    override fun setAdapeter() {




        mRecyclerView.layoutManager=LinearLayoutManager(activity)
        mRecyclerView.adapter=adapter
      /*  adapter.setOnLoadMoreListener ({
                isLoading=true
                mPresent.request()

        },mRecyclerView)*/
        adapter.setOnLoadMoreListener(object :BaseQuickAdapter.RequestLoadMoreListener{
            override fun onLoadMoreRequested() {
                isLoading=true
                mPresent.request()
            }

        },mRecyclerView)
    }

    override fun setNet() {
        mPresent.request()
    }

    override fun loadMore() {
        mPresent.request()
    }


    override fun initData() {
        setAdapeter()
        setNet()
    }

    override fun initView(view: View) {
        mPresent.attachView(this)
    }

    override fun getRes(): Int = R.layout.fragment_follow

    override fun resetNet() {
    }
    companion object {
        fun newInstans():BaseFragment{
            return FollowFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresent.detachView()
    }
    class FollowAdapter(res:Int,datas:List<FollowBean.Item>?) : BaseQuickAdapter<FollowBean.Item,BaseViewHolder>(res,datas){
        override fun convert(helper: BaseViewHolder, item: FollowBean.Item) {
            helper.setText(R.id.tv_title, item.data.header.title)
            helper.setText(R.id.tv_desc, item.data.header.description)
            var iv_avatar = helper.getView<ImageView>(R.id.iv_avatar)
            Glide.with(mContext).load(item.data.header.icon).into(iv_avatar)
            var fl_recyclerView = helper.getView<RecyclerView>(R.id.fl_recyclerView)
            fl_recyclerView.setHasFixedSize(true)
            fl_recyclerView.layoutManager = LinearLayoutManager(mContext as Activity, LinearLayoutManager.HORIZONTAL, false)
            var adapter = FocusItemAdapter(item.data.itemList, R.layout.item_focus_horizontal_layout)
            fl_recyclerView.adapter = adapter
           /* adapter.setOnItemClickListener { _, _, position ->
                var intent = Intent(mContext, VideoDetailActivity::class.java)
                intent.putExtra("video_url", item.data.itemList.get(position).data.playUrl)
                intent.putExtra("video_title", item.data.itemList.get(position).data.title)
                (mContext as Activity).startActivity(intent)
            }*/
        }

    }
}