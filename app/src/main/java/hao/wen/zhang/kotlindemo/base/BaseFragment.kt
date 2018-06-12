package hao.wen.zhang.kotlindemo.base


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.TextView
import hao.wen.zhang.kotlindemo.R
import hao.wen.zhang.kotlindemo.http.LoadingDialog
import kotlinx.android.synthetic.main.base_layout.*
import kotlinx.android.synthetic.main.base_layout.view.*

/**
 * 作者：ZWH
 * 创建日期： 2018/6/5 0005   下午 2:43
 * 描述说明：
 */
abstract  class BaseFragment : Fragment() , IView {
    var dialog: LoadingDialog? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflate(activity, R.layout.base_layout, null)
        LayoutInflater.from(activity).inflate(getRes(),view.findViewById(R.id.content),true)

        view.findViewById<TextView>(R.id.error_text).setOnClickListener{
            resetNet()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initData()
    }

    abstract fun resetNet()

    abstract fun initData()

    abstract fun initView(view: View)

    abstract fun getRes(): Int





    open override fun dissmassLoading() {
        dialog?.dismiss()
        dialog = null
    }

    override fun dissError() {
        error_layout.visibility=View.GONE
        view!!.content.visibility=View.VISIBLE
    }

    open override fun showLoading() {
        //这里如果是null，直接抛异常
        dialog = LoadingDialog(this.activity!!)
        dialog?.setCanceledOnTouchOutside(false)
        dialog!!.setCanceledOnTouchOutside(false)
        dialog?.show()
    }

    override fun error() {
        error_layout.visibility=View.VISIBLE
        view!!.content.visibility=View.GONE
    }

}