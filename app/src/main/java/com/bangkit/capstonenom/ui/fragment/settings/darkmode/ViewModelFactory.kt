package com.bangkit.capstonenom.ui.fragment.settings.darkmode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.bangkit.capstonenom.ui.fragment.settings.SettingsViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val pref: SettingPreferences) : NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}