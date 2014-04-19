import models.User
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class DatabaseSpec extends Specification {

  "A user" should {
    "be findable by username" in new WithApplication {
      User.findOneByUsername("foobar") must be equalTo None
    }
  }

}