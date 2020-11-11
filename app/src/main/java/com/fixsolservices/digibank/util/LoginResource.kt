package com.fixsolservices.digibank.util


class LoginResource<T>(val status: AuthStatus, val data: T?, val message: String?) {

    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
    }

    companion object {
        fun <T> authenticated(data: T?): LoginResource<T?> {
            return LoginResource(AuthStatus.AUTHENTICATED, data, null)
        }

        fun <T> error(msg: String, data: T?): LoginResource<T?> {
            return LoginResource(AuthStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): LoginResource<T?> {
            return LoginResource(AuthStatus.LOADING, data, null)
        }

        fun <T> logout(): LoginResource<T?> {
            return LoginResource<T?>(AuthStatus.NOT_AUTHENTICATED, null, null)
        }
    }

}