package com.dhananjay.imgsearch.utils.networkconnections

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}