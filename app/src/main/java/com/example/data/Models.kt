package com.example.data

data class District(
    val id: String,
    val nameHi: String,
    val nameEn: String
)

data class Constituency(
    val id: String,
    val number: Int,
    val nameHi: String,
    val nameEn: String,
    val districtId: String
)

data class Candidate(
    val id: String,
    val serialNumber: Int,
    val nameHi: String,
    val partyHi: String,
    val symbolEmoji: String,
    val symbolNameHi: String
)

data class SurveyResultItem(
    val candidate: Candidate,
    val votes: Int,
    val percentage: Float
)

sealed class AppScreen {
    object Home : AppScreen()
    data class Evm(val district: District, val constituency: Constituency) : AppScreen()
    data class Results(val district: District, val constituency: Constituency, val chosenCandidateId: String? = null) : AppScreen()
}
