package com.zzriders.cricketindoorrules.games.converters

interface Converter<Input, Output> {
    fun convert(input : Input) : Output?
}