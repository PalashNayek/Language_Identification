package com.palash.language_identification.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.language_identification.repositories.LanguageIdentifierRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageIdentifierViewModel @Inject constructor(
    private val repository: LanguageIdentifierRepository
) : ViewModel() {

    private val _identifiedLanguage = MutableLiveData<String>()
    val identifiedLanguage: LiveData<String> get() = _identifiedLanguage

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun identifyLanguage(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.identifyLanguage(
                text = text,
                onSuccess = { languageCode ->
                    _identifiedLanguage.postValue(languageCode)
                },
                onFailure = { exception ->
                    _error.postValue(exception.message)
                }
            )
        }
    }
}