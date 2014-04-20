import models.User
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._

@RunWith(classOf[JUnitRunner])
class ModelSpec extends Specification {

  def withUser(test: (User) => Any) {
    val user = User(username = "user", password = "pass")
    User.insert(user)
    try test(user)
    finally User.remove(user)
  }

  "A user" should {
    "be findable by his username" in new WithApplication { user =>
      User.findOneByUsername("user").get.username must be equalTo "user"
    }
  }

  "A nil user" should {
    "not be retrievable" in new WithApplication {
      User.remove(User(username = "user", password = "pass"))
      User.findOneByUsername("user") must be equalTo None
    }
  }

}