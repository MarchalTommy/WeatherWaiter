package com.aki.weatherwaiter_exomind.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.aki.weatherwaiter_exomind.R
import com.aki.weatherwaiter_exomind.model.GetWeatherByCityName

@BindingAdapter("app:text")
fun changeTextEverySixSeconds(textView: TextView, text: List<String>) {
    var cTimer: CountDownTimer? = null

    textView.text = text[0]

    cTimer = object : CountDownTimer(18000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            if (millisUntilFinished in 6001..12000) {
                textView.text = text[1]
            } else if (millisUntilFinished <= 6000) {
                textView.text = text[2]
            }
        }

        override fun onFinish() {
            textView.text = text[0]
            cTimer?.start()
        }
    }
    (cTimer as CountDownTimer).start()
}

@BindingAdapter("app:formatCity")
fun formatCity(textView: TextView, string: String?) {
    var formattedText = ""
    if (string != null) {
        val splitStr = string.split(" ")
        formattedText = splitStr.last()
    }
    textView.text = formattedText
}

@BindingAdapter("app:formatTemp")
fun formatTemp(textView: TextView, number: Double?) {
    if (number != null) {
        textView.text = "${(number.toInt())}°C"
    }
}

@BindingAdapter("app:iconPop")
fun changeIconOnWeather(view: ImageView, weather: GetWeatherByCityName.Weather?) {

    if (weather != null) {
        view.setImageDrawable(getDrawableWeather(weather, view.context))
    }
}

@BindingAdapter("app:hideIfFull")
fun hideIfFull(view: View, number: Int) {
    view.visibility = if (number == 100) View.GONE else View.VISIBLE
}

@BindingAdapter("app:showIfFull")
fun showIfFull(view: View, number: Int) {
    view.visibility = if (number == 100) View.VISIBLE else View.GONE
}

@BindingAdapter("app:formatToPercentage")
fun formatToPercentage(textView: TextView, number: Int) {
    textView.text = "$number%"
}

private fun getDrawableWeather(
    weather: GetWeatherByCityName.Weather,
    context: Context
): Drawable? {
    return when (weather.id) {
        in 200..232 -> {
            ContextCompat.getDrawable(context, R.drawable.thunder)
        }
        in 300..321 -> {
            ContextCompat.getDrawable(context, R.drawable.light_rain)
        }
        in 500..531 -> {
            ContextCompat.getDrawable(context, R.drawable.heavy_rain)
        }
        in 600..622 -> {
            ContextCompat.getDrawable(context, R.drawable.snow)
        }
        in 701..781 -> {
            ContextCompat.getDrawable(context, R.drawable.cloud_mist)
        }
        800 -> {
            ContextCompat.getDrawable(context, R.drawable.sun)
        }
        in 801..802 -> {
            ContextCompat.getDrawable(context, R.drawable.cloudy)
        }
        in 803..804 -> {
            ContextCompat.getDrawable(context, R.drawable.clouds)
        }
        else -> {
            ContextCompat.getDrawable(context, R.drawable.ic_error)
        }
    }
}
