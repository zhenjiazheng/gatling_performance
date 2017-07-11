import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import scala.concurrent.duration._

class TransactionAll extends Simulation {
	val feeder = csv("token.csv").random;
	val httpConf = http.baseURL("https://api-release-hyb.thy360.com") // Here is the root for all relative URLs
//	val headers_json = Map("token"->"$token","appId"->"appidzzkg9021v754d")
			//val headers_json = Map("token"->"3306283b-7340-484b-962b-d8eb9342f090","appId"->"appidzzkg9021v754d")
	val test1 = scenario("All")
    .feed(feeder)
		.exec(http("All").get("/ja/memberbao/v1/transactiondetail/99?page=1&limit=10")
		.headers(Map("token"->"${token}","appId"->"appidzzkg9021v754d"))
		.check(status.is(200)))
		.pause(2)	
		
	setUp(test1.inject(atOnceUsers(10))).protocols(httpConf)
}