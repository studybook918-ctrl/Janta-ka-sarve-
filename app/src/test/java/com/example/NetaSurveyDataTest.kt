package com.example

import com.example.data.model.DistrictAndConstituencyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class NetaSurveyDataTest {

  @Test
  fun testDistrictsCountAndPresence() {
    val districts = DistrictAndConstituencyData.districts
    assertEquals("UP must have 75 districts", 75, districts.size)

    val districtIds = districts.map { it.districtId }.toSet()
    assertTrue("Lucknow must be present", districtIds.contains("lucknow"))
    assertTrue("Varanasi must be present", districtIds.contains("varanasi"))
    assertTrue("Ayodhya must be present", districtIds.contains("ayodhya"))
    assertTrue("Gorakhpur must be present", districtIds.contains("gorakhpur"))
    assertTrue("Prayagraj must be present", districtIds.contains("prayagraj"))
    assertTrue("Agra must be present", districtIds.contains("agra"))
  }

  @Test
  fun testConstituenciesTotalCountAndNumbering() {
    val constituencies = DistrictAndConstituencyData.constituencies
    assertEquals("UP must have 403 assembly constituencies", 403, constituencies.size)

    val serialNumbers = constituencies.map { it.officialSerialNumber }
    assertEquals("First seat should be 1", 1, serialNumbers.minOrNull())
    assertEquals("Last seat should be 403", 403, serialNumbers.maxOrNull())

    // Ensure all 1..403 are present
    val missing = (1..403).toSet() - serialNumbers.toSet()
    assertTrue("No constituency numbers should be missing: $missing", missing.isEmpty())
  }

  @Test
  fun testDistrictMappingIntegrity() {
    val validDistrictIds = DistrictAndConstituencyData.districts.map { it.districtId }.toSet()
    for (c in DistrictAndConstituencyData.constituencies) {
      assertTrue(
        "Constituency ${c.constituencyNameHindi} (${c.officialSerialNumber}) has invalid district ${c.districtId}",
        validDistrictIds.contains(c.districtId)
      )
    }

    // Verify Lucknow constituencies (9 seats)
    val lucknowSeats = DistrictAndConstituencyData.getConstituenciesByDistrict("lucknow")
    assertEquals(9, lucknowSeats.size)
  }

  @Test
  fun testCandidatesSlateGeneration() {
    val seat = DistrictAndConstituencyData.constituencies.first { it.constituencyId == "ac_171" } // Lucknow West
    val candidates = DistrictAndConstituencyData.getCandidatesForConstituency(seat)
    assertFalse("Candidates list should not be empty", candidates.isEmpty())

    val symbols = candidates.map { it.symbolKey }
    assertTrue("Lotus symbol should be present", symbols.contains("lotus"))
    assertTrue("Bicycle symbol should be present", symbols.contains("bicycle"))
    assertTrue("Elephant symbol should be present", symbols.contains("elephant"))
    assertTrue("Hand symbol should be present", symbols.contains("hand"))
    assertTrue("NOTA option should be present", symbols.contains("nota"))
  }
}
