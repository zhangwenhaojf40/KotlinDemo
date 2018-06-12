package hao.wen.zhang.kotlindemo.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import hao.wen.zhang.kotlindemo.R
import hao.wen.zhang.kotlindemo.base.BaseFragment
import hao.wen.zhang.kotlindemo.module.RankBean
import hao.wen.zhang.kotlindemo.prsent.RankPresent
import hao.wen.zhang.kotlindemo.view.i.IRankView
import kotlinx.android.synthetic.main.fragment_rank.*

/**
 * 作者：ZWH
 * 创建日期： 2018/6/8 0008   上午 11:21
 * 描述说明：排行
 */
class RankFragment :BaseFragment(), IRankView {
    var bean:RankBean?=null
    override fun getData(bean: RankBean) {
        this.bean=bean
        setAdapeter()

    }

    private fun setAdapeter() {

        mRecyclerView.layoutManager=LinearLayoutManager(activity)
        mRecyclerView.adapter=RankAdapeter(R.layout.item_category_detail,bean!!.itemList)
    }

    var mPresent=RankPresent()
    var url:String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString("url")


    }
    override fun initData() {

        mPresent.attachView(this)
        mPresent.setUrl(url!!)
        mPresent.request()

    }

    override fun initView(view: View) {

    }

    override fun getRes(): Int = R.layout.fragment_rank

    override fun resetNet() {
        mPresent.setUrl(url!!)
        mPresent.request()
    }
    companion object {
        fun newInstans(url :String):RankFragment {
            var fragment=RankFragment()
            var bundle=Bundle()
            bundle.putString("url",url)
            fragment.arguments=bundle
            return fragment
        }
    }
    class RankAdapeter(layout:Int,datas:List<RankBean.Item>) : BaseQuickAdapter<RankBean.Item, BaseViewHolder>(layout,datas) {
        override fun convert(helper: BaseViewHolder, item: RankBean.Item) {
            helper.setText(R.id.tv_title, item.data.title)
            var iv_image = helper.getView<ImageView>(R.id.iv_image)
            Glide.with(mContext).load(item.data.cover.detail).into(iv_image)
            val timeFormat = durationFormat(item.data.duration)
            helper.setText(R.id.tv_tag, "#${item.data.category}/$timeFormat")
        }
        /**
         * 格式化时间
         */
        fun durationFormat(duration: Int?): String {
            val minute = duration!! / 60
            val second = duration % 60
            return if (minute <= 9) {
                if (second <= 9) {
                    "0$minute' 0$second''"
                } else {
                    "0$minute' $second''"
                }
            } else {
                if (second <= 9) {
                    "$minute' 0$second''"
                } else {
                    "$minute' $second''"
                }
            }
        }

    }
}