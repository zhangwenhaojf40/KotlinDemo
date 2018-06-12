package hao.wen.zhang.kotlindemo.view

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import hao.wen.zhang.kotlindemo.R
import hao.wen.zhang.kotlindemo.R.id.mRecyclerView
import hao.wen.zhang.kotlindemo.base.BaseFragment
import hao.wen.zhang.kotlindemo.module.CategoryBean
import hao.wen.zhang.kotlindemo.prsent.CategoryPresent
import hao.wen.zhang.kotlindemo.view.i.ICategoryView
import kotlinx.android.synthetic.main.fragment_me_layout.*

/**
 * 作者：ZWH
 * 创建日期： 2018/6/11 0011   下午 2:18
 * 描述说明：
 */
class CategoryFragment:BaseFragment(), ICategoryView<ArrayList<CategoryBean>> {
    val mPresent by lazy { CategoryPresent() }
    val adapter by lazy { CategoryAdapter(R.layout.item_classify_layout,null) }
    override fun setAdapter() {
        mRecyclerView.layoutManager=LinearLayoutManager(activity)
        mRecyclerView.adapter=adapter
    }

    override fun setNet() {
        mPresent.request()
    }

    override fun getData(data: ArrayList<CategoryBean>) {
        adapter.setNewData(data)
    }
    override fun resetNet() {
        setNet()
    }

    override fun initData() {
        setAdapter()
        setNet()
    }

    override fun initView(view: View) {
        mPresent.attachView(this)
    }

    override fun getRes(): Int = R.layout.fragment_category
    companion object {
        fun newInstans():CategoryFragment{
            return CategoryFragment()
        }
    }

    class CategoryAdapter(res:Int,datas:ArrayList<CategoryBean>?) : BaseQuickAdapter<CategoryBean, BaseViewHolder>(res,datas) {
        override fun convert(helper: BaseViewHolder, item: CategoryBean) {
            helper.setText(R.id.tv_category_name,"#${item.name}")
            var bg =  helper.getView<ImageView>(R.id.iv_category)
            Glide.with(mContext).load(item.bgPicture).into(bg)
        }

    }
}