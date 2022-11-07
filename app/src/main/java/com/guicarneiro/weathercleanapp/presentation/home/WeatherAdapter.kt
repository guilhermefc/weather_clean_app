package com.guicarneiro.weathercleanapp.presentation.home

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guicarneiro.weathercleanapp.BuildConfig
import com.guicarneiro.weathercleanapp.R
import com.guicarneiro.weathercleanapp.databinding.WeatherItemBinding
import com.guicarneiro.weathercleanapp.domain.entities.WeatherDaily
import com.guicarneiro.weathercleanapp.presentation.common.WeatherTypeToRes
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter(private val dataSet: List<WeatherDaily>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    lateinit var context: Context

    inner class ViewHolder(val binding: WeatherItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        context = viewGroup.context
        val binding = WeatherItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.binding.textViewDay.text = SimpleDateFormat("MMM dd", Locale.US).format(item.date)
        holder.binding.textViewTemperatureMin.text = context.resources.getString(
            R.string.temperature,
            item.minTemperature.toString(),
            item.tempScale
        )
        holder.binding.textViewTemperatureMax.text = context.resources.getString(
            R.string.temperature,
            item.maxTemperature.toString(),
            item.tempScale
        )
        holder.binding.textVieWeather.text = item.condition
        holder.binding.imageViewCondition.setImageResource(WeatherTypeToRes.parseWeatherTypeToResource(item.conditionType))
    }

    override fun getItemCount() = dataSet.size

}