import com.fasterxml.jackson.databind.JsonNode
import org.json.JSONObject
import org.jetbrains.spek.api.Spek

import org.http4k.client.ApacheClient
import org.http4k.core.*
import org.http4k.core.Status.Companion.OK
import org.http4k.format.Jackson
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertTrue

class BookingContract: Spek({
    it("has a booking function with a status element") {
        // Extension method API:
        val json = Jackson

        val bodyParams: JsonNode = json.obj(
                "bookableId" to json.number(0),
                "bookingId" to json.number(0),
                "startDateTime" to json.string("2017-10-02T22:03:27.409Z"),
                "endDateTime" to json.string("2017-10-02T22:04:27.409Z"),
                "subject" to json.string("getting to know you"))

        val response = ApacheClient()(Request(Method.POST, "https://dev-bookit-api.buildit.tools/v1/booking").header("Content-Type", "application/json").body(bodyParams.toString())).body.toString()

        assertTrue(JSONObject(response).has("bookingId"))
        assertTrue(JSONObject(response).has("bookableId"))
        assertTrue(JSONObject(response).has("subject"))
        assertTrue(JSONObject(response).has("startDateTime"))
        assertTrue(JSONObject(response).has("endDateTime"))
    }
})
