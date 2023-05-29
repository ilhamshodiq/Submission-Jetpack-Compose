package com.the12smb.submissionjetpackcompose.di

import com.the12smb.submissionjetpackcompose.data.ContentRepository

object Injection {
    fun provideRepository(): ContentRepository {
        return ContentRepository.getInstance()
    }
}