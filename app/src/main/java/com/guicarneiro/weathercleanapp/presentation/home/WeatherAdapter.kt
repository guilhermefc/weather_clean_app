package com.guicarneiro.weathercleanapp.presentation.home

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guicarneiro.weathercleanapp.BuildConfig
import com.guicarneiro.weathercleanapp.R
import com.guicarneiro.weathercleanapp.databinding.WeatherItemBinding
import com.guicarneiro.weathercleanapp.domain.entities.ConsolidatedWeather
import com.squareup.picasso.Picasso

class WeatherAdapter(private val dataSet: List<ConsolidatedWeather>) :
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
        holder.binding.textViewDay.text = DateFormat.getDateFormat(context).format(item.applicableDate)
        holder.binding.textViewTemperature.text = context.resources.getString(
            R.string.temperature,
            item.getTempNow()
        )
        holder.binding.textVieWeather.text = item.weatherStateName

        val picassoURL = BuildConfig.API_BASE_URL_IMAGES +
                item.weatherStateAbbr + BuildConfig.API_IMAGES_FORMAT
        Picasso.get().load(picassoURL).into(holder.binding.imageViewCondition);
    }

    override fun getItemCount() = dataSet.size

}