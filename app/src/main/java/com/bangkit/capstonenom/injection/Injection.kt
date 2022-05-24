package com.bangkit.capstonenom.injection

import android.content.Context
import com.bangkit.capstonenom.api.ApiConfig
import com.bangkit.capstonenom.utils.DataSource.Companion.getInstance
import com.bangkit.capstonenom.utils.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val dataSource = getInstance(ApiConfig.apiInstance)
        return Repository.getInstance(dataSource)
    }
}