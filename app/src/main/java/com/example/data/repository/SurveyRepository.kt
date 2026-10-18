package com.example.data.repository

import android.content.Context
import com.example.R
import com.example.data.OperationType
import com.example.data.handleFirestoreError
import com.example.data.model.Candidate
import com.example.data.model.Constituency
import com.example.data.model.District
import com.example.data.model.DistrictAndConstituencyData
import com.example.data.model.Survey
import com.example.data.model.SurveyResponse
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SurveyRepository(
  private val db: FirebaseFirestore
) {
  constructor(context: Context) : this(
    FirebaseFirestore.getInstance(
      context.applicationContext.getString(R.string.firestore_database_id)
    )
  )

  fun getDistricts(): List<District> = DistrictAndConstituencyData.districts

  fun getConstituencies(districtId: String): List<Constituency> =
    DistrictAndConstituencyData.getConstituenciesByDistrict(districtId)

  fun getCandidates(constituency: Constituency): List<Candidate> =
    DistrictAndConstituencyData.getCandidatesForConstituency(constituency)

  suspend fun getSurvey(constituencyId: String): Survey {
    val surveyId = "survey_$constituencyId"
    val docRef = db.collection("surveys").document(surveyId)
    return try {
      val snapshot = docRef.get().await()
      if (snapshot.exists()) {
        val total = snapshot.getLong("totalResponses") ?: 0L
        @Suppress("UNCHECKED_CAST")
        val countsRaw = snapshot.get("candidateCounts") as? Map<String, Any> ?: emptyMap()
        val counts = countsRaw.mapValues { (_, v) -> (v as? Number)?.toLong() ?: 0L }
        Survey(
          surveyId = surveyId,
          constituencyId = constituencyId,
          title = snapshot.getString("title") ?: "उत्तर प्रदेश जनमत सर्वेक्षण",
          status = snapshot.getString("status") ?: "active",
          methodology = snapshot.getString("methodology") ?: "ऑनलाइन जनमत सर्वेक्षण",
          totalResponses = total,
          candidateCounts = counts,
          startAt = snapshot.getString("startAt") ?: "2026-10-01",
          endAt = snapshot.getString("endAt") ?: "2027-03-31"
        )
      } else {
        createDefaultSurvey(surveyId, constituencyId)
      }
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.GET, docRef.path)
      createDefaultSurvey(surveyId, constituencyId)
    }
  }

  suspend fun getUserResponse(surveyId: String, userId: String): SurveyResponse? {
    val docId = "${surveyId}_$userId"
    val docRef = db.collection("surveyResponses").document(docId)
    return try {
      val snapshot = docRef.get().await()
      if (snapshot.exists()) {
        snapshot.toObject(SurveyResponse::class.java)
      } else {
        null
      }
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.GET, docRef.path)
      null
    }
  }

  suspend fun submitVote(
    surveyId: String,
    constituencyId: String,
    candidateId: String,
    userId: String
  ): Result<Unit> {
    val docId = "${surveyId}_$userId"
    val responseRef = db.collection("surveyResponses").document(docId)
    val surveyRef = db.collection("surveys").document(surveyId)

    return try {
      val existing = responseRef.get().await()
      if (existing.exists()) {
        return Result.failure(IllegalStateException("आपकी राय पहले ही दर्ज हो चुकी है।"))
      }

      val votePayload = mapOf(
        "responseId" to docId,
        "surveyId" to surveyId,
        "constituencyId" to constituencyId,
        "candidateId" to candidateId,
        "userId" to userId,
        "createdAt" to FieldValue.serverTimestamp()
      )

      val surveySnap = surveyRef.get().await()
      if (!surveySnap.exists()) {
        val defaultSurvey = createDefaultSurvey(surveyId, constituencyId)
        val initialCounts = mapOf(candidateId to 1L)
        val surveyPayload = mapOf(
          "surveyId" to surveyId,
          "constituencyId" to constituencyId,
          "title" to defaultSurvey.title,
          "status" to "active",
          "methodology" to defaultSurvey.methodology,
          "totalResponses" to 1L,
          "candidateCounts" to initialCounts,
          "startAt" to defaultSurvey.startAt,
          "endAt" to defaultSurvey.endAt
        )
        responseRef.set(votePayload).await()
        surveyRef.set(surveyPayload).await()
      } else {
        responseRef.set(votePayload).await()
        val updates = mapOf(
          "totalResponses" to FieldValue.increment(1),
          "candidateCounts.$candidateId" to FieldValue.increment(1)
        )
        surveyRef.update(updates).await()
      }
      Result.success(Unit)
    } catch (e: Exception) {
      handleFirestoreError(e, OperationType.CREATE, responseRef.path)
      Result.failure(e)
    }
  }

  private fun createDefaultSurvey(surveyId: String, constituencyId: String): Survey {
    return Survey(
      surveyId = surveyId,
      constituencyId = constituencyId,
      title = "उत्तर प्रदेश जनमत सर्वेक्षण — $constituencyId",
      status = "active",
      methodology = "ऑनलाइन जनमत सर्वेक्षण (एक नागरिक, एक राय — सुरक्षित ईमेल/Google सत्यापन)",
      totalResponses = 0L,
      candidateCounts = emptyMap()
    )
  }
}
