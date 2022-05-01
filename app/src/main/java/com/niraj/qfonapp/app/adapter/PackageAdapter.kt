package com.niraj.qfonapp.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.niraj.qfonapp.R
import com.niraj.qfonapp.data.model.remote.DataItem
import com.niraj.qfonapp.databinding.PackageRecyclerviewItemBinding
import kotlin.collections.ArrayList

class PackageAdapter(
    dataItemArrayList: ArrayList<DataItem?>,
    requestManager: RequestManager
)
    : RecyclerView.Adapter<PackageAdapter.ViewHolder>() {
    private var dataItemArrayList: ArrayList<DataItem?> = arrayListOf()
    private var requestManager: RequestManager

    init {
        this.dataItemArrayList = dataItemArrayList
        this.requestManager = requestManager
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val appBinding = PackageRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(appBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(dataItemArrayList[position]) {
                this ?: return
                requestManager
                    .load(userImage)
                    .apply(RequestOptions().circleCrop())
                    .into(packageRecyclerviewItemBinding.userImage)

                packageRecyclerviewItemBinding.userName.text = userName

                when(posttype) {
                    1 -> {
                        requestManager.load(content).into(packageRecyclerviewItemBinding.imgContent)
                    }
                    2-> {
                        requestManager.load(thumb).into(packageRecyclerviewItemBinding.imgContent)
                        packageRecyclerviewItemBinding.videoBtn.visibility = View.VISIBLE
                    }
                    else -> {}
                }
                packageRecyclerviewItemBinding.numberOfLikes.text = likes
                packageRecyclerviewItemBinding.numberOfComments.text = comments

            }
        }
    }

    override fun getItemCount(): Int {
        return dataItemArrayList.size
    }

    fun setPackageList(dataItemArrayList: ArrayList<DataItem?>) {
        this.dataItemArrayList = dataItemArrayList
        notifyDataSetChanged()
    }

    class ViewHolder(val packageRecyclerviewItemBinding: PackageRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(packageRecyclerviewItemBinding.root)

}