package com.example.blendings_backend.usecase.global.consts

object TableName {

    private const val TABLE_NAME_PREFIX = "tbl_"
    private const val MAP_TABLE_NAME_PREFIX = "map_"

    const val USER_TABLE_NAME = TABLE_NAME_PREFIX + "user"
    const val COUPLE_MAP_TABLE_NAME = MAP_TABLE_NAME_PREFIX + "couple"

    const val CLAIM_TABLE_NAME = TABLE_NAME_PREFIX + "claim"
}