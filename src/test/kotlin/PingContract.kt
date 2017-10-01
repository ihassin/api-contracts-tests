import org.json.JSONObject
import org.jetbrains.spek.api.Spek

import org.http4k.client.ApacheClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertTrue

class PingContract: Spek({
    it("has a ping function with a status element") {
        val body = ApacheClient()(Request(Method.GET, "https://dev-bookit-api.buildit.tools/v1/ping")).body.toString()
        assertTrue(JSONObject(body).has("status"))
    }
})
