package com.the12smb.submissionjetpackcompose.data

import com.the12smb.submissionjetpackcompose.model.Content
import com.the12smb.submissionjetpackcompose.model.ContentData
import com.the12smb.submissionjetpackcompose.model.ContentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class ContentRepository {
    private val contents = mutableListOf<ContentList>()

    init {
        if (contents.isEmpty()) {
            ContentData.contents.forEach {
                contents.add(ContentList(it, 0))
            }
        }
    }

    fun getAllContents(): Flow<List<ContentList>> {
        return flowOf(contents)
    }

//    fun searchContent(query: String) = flow {
//        val data = contents.filter {
//            it.name.contains(query, ignoreCase = true)
//        }
//        emit(data)
//    }

    fun getContentListById(contentId: Long): ContentList {
        return contents.first {
            it.content.id == contentId
        }
    }

    companion object {
        @Volatile
        private var instance: ContentRepository? = null

        fun getInstance(): ContentRepository =
            instance ?: synchronized(this) {
                ContentRepository().apply {
                    instance = this
                }
            }
    }
}