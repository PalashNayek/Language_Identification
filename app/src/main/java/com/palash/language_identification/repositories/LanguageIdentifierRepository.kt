package com.palash.language_identification.repositories

import com.google.mlkit.nl.languageid.LanguageIdentifier
import javax.inject.Inject

class LanguageIdentifierRepository @Inject constructor(
    private val languageIdentifier: LanguageIdentifier
) {

    fun identifyLanguage(
        text: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        languageIdentifier.identifyLanguage(text)
            .addOnSuccessListener { languageCode ->
                onSuccess(languageCode)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}