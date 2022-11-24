package com.example.capstone.dataimport androidx.paging.PagingSourceimport androidx.paging.PagingStateimport com.example.capstone.api.ApiServiceimport com.example.capstone.model.Greeventsimport com.example.capstone.preference.PreferenceLoginimport kotlin.Exceptionclass GreeventsPagingSource(private val preferenceLogin: PreferenceLogin,private val apiService: ApiService): PagingSource<Int, Greevents>() {    override fun getRefreshKey(state: PagingState<Int, Greevents>): Int? {        return state.anchorPosition?.let {            val anchorPage = state.closestPageToPosition(it)            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)        }    }    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Greevents> {        return try {            val page = params.key ?: INITIAL_PAGE            val token = preferenceLogin.getUser().token.toString()            if (token.isNotEmpty()){                val dataResponse = token.let { apiService.getAllEvents("Bearer $it") }                if (dataResponse.isSuccessful){                    LoadResult.Page(                        data = dataResponse.body()?.data ?: emptyList(),                        prevKey = if (page == INITIAL_PAGE) null else page -1,                        nextKey = if (dataResponse.body()?.data.isNullOrEmpty()) null else page + 1                    )                } else{                    LoadResult.Error(Exception("Gagal Memuat Event"))                }            } else{                LoadResult.Error(Exception("Token User Kosong"))            }        } catch (exception: Exception){            return LoadResult.Error(exception)        }    }    private companion object {        const val INITIAL_PAGE = 1    }}