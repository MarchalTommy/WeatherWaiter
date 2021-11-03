package com.aki.weatherwaiter_exomind.data.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.*
import com.aki.weatherwaiter_exomind.model.GetWeatherByCityName
import com.aki.weatherwaiter_exomind.data.repository.Repository
import kotlinx.coroutines.launch


class Viewmodel : ViewModel() {

    private val repo = Repository()

    /*
    Shortcut here to init without losing time my 5 cities
     */
    val cityList = listOf(
        "Rennes",
        "Paris",
        "Nantes",
        "Bordeaux",
        "Lyon"
    )

    private val _weatherByCityName1 = MutableLiveData<GetWeatherByCityName?>()
    val weatherByCityName1: LiveData<GetWeatherByCityName?> = _weatherByCityName1

    private val _weatherByCityName2 = MutableLiveData<GetWeatherByCityName?>()
    val weatherByCityName2: LiveData<GetWeatherByCityName?> = _weatherByCityName2

    private val _weatherByCityName3 = MutableLiveData<GetWeatherByCityName?>()
    val weatherByCityName3: LiveData<GetWeatherByCityName?> = _weatherByCityName3

    private val _weatherByCityName4 = MutableLiveData<GetWeatherByCityName?>()
    val weatherByCityName4: LiveData<GetWeatherByCityName?> = _weatherByCityName4

    private val _weatherByCityName5 = MutableLiveData<GetWeatherByCityName?>()
    val weatherByCityName5: LiveData<GetWeatherByCityName?> = _weatherByCityName5

    private val _progression = MutableLiveData<Int>()
    val progression: LiveData<Int> = _progression

    val waitingStrings = ArrayList<String>(3)
    private var cTimer: CountDownTimer? = null

    /*
    Shortcut here to init without losing time my 3 Strings used for the waiting message
     */
    init {
        waitingStrings.add("Nous téléchargeons les données…")
        waitingStrings.add("C\'est presque fini…")
        waitingStrings.add("Plus que quelques secondes avant d\'avoir le résultat !")
    }

    fun getWeatherByCityName() {
        cTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                when (millisUntilFinished) {
                     in 50001..60000 -> {
                        viewModelScope.launch {
                            _weatherByCityName1.postValue(repo.getWeatherByCityName(cityList[0]))
                        }
                        _progression.postValue(0)
                    }
                    in 40001..50000 -> {
                        viewModelScope.launch {
                            _weatherByCityName2.postValue(repo.getWeatherByCityName(cityList[1]))
                        }
                        _progression.postValue(20)
                    }
                    in 30001..40000 -> {
                        viewModelScope.launch {
                            _weatherByCityName3.postValue(repo.getWeatherByCityName(cityList[2]))
                        }
                        _progression.postValue(40)
                    }
                    in 20001..30000 -> {
                        viewModelScope.launch {
                            _weatherByCityName4.postValue(repo.getWeatherByCityName(cityList[3]))
                        }
                        _progression.postValue(60)
                    }
                    in 10001..20000L -> {
                        viewModelScope.launch {
                            _weatherByCityName5.postValue(repo.getWeatherByCityName(cityList[4]))
                        }
                        _progression.postValue(80)
                    }
                }
            }
            override fun onFinish() {
                _progression.postValue(100)
                if (cTimer != null) cTimer!!.cancel()
            }
        }
        (cTimer as CountDownTimer).start()
    }
}