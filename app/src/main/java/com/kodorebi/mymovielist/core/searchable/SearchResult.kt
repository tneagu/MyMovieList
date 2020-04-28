package com.kodorebi.mymovielist.core.searchable

interface SearchResult<T> {
    val relevance: Int
    val item : T
}