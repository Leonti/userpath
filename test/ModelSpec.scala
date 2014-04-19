import models.User
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class ModelSpec extends Specification {

  "A user" should {
    "be findable by his username" in new WithApplication {
      User.insert(User(username = "user", password = "pass"))
      User.findOneByUsername("user").get.username must be equalTo "user"
    }
  }

  "A non-existing user" should {
    "not be retrievable" in new WithApplication {
      User.findOneByUsername("foobar") must be equalTo None
    }
  }

}