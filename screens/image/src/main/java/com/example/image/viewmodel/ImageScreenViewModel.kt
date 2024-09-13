package com.example.image.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.rodipit.quotes_api.api.QuotesRepository


internal class ImageScreenViewModel : ViewModel() {

    private val _state: MutableStateFlow<String?> = MutableStateFlow(null)


    private val quotesRepo: QuotesRepository by inject(QuotesRepository::class.java)

    private var loadingJob: Job? = null

    val state
        get() =
            _state.asStateFlow()


}
