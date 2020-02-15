package ru.test.weatherapp.infra

interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}