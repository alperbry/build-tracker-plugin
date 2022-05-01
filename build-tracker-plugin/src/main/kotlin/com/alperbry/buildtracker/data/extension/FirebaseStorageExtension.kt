package com.alperbry.buildtracker.data.extension

open class FirebaseStorageExtension {

    private var _email: String = ""
    private var _password: String = ""
    private var _apiKey: String = ""
    private var _databaseId: String = ""

    internal val email: String
        get() = _email

    internal val password: String
        get() = _password

    internal val apiKey: String
        get() = _apiKey

    internal val databaseId: String
        get() = _databaseId

    fun email(email: String) {
        this._email = email
    }

    fun password(password: String) {
        this._password = password
    }

    fun apiKey(apiKey: String) {
        this._apiKey = apiKey
    }

    fun databaseId(databaseId: String) {
        this._databaseId = databaseId
    }
}
