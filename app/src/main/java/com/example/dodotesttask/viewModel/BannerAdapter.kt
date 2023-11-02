package com.example.dodotesttask.viewModel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.caverock.androidsvg.SVG
import com.example.dodotesttask.R
import com.example.dodotesttask.model.Banner

import java.io.InputStream


class BannerAdapter(private val context: Context) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    private var bannerList: List<Banner> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = bannerList[position]

        val svgInputStream: InputStream = context.assets.open(banner.svgFileName)
        val svg = SVG.getFromInputStream(svgInputStream)
        val picture = svg.renderToPicture()
        val bitmap = Bitmap.createBitmap(picture.width, picture.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawPicture(picture)
        holder.bannerImage.setImageBitmap(bitmap)

        // Используем AppCompatImageView для отображения SVG-изображения
        holder.bannerImage.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }

    fun updateData(newBannerList: List<Banner>) {
        bannerList = newBannerList
        notifyDataSetChanged()
    }

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bannerImage: AppCompatImageView = itemView.findViewById(R.id.bannerImage)
    }
}

