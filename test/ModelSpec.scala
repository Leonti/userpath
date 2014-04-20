import models.{User, Address}

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._

@RunWith(classOf[JUnitRunner])
class ModelSpec extends Specification {

  "A user" should {
    "be retrieved by his username" in new WithApplication {
      withUser {
        user =>
          User.findOneByUsername(user.username).get must be equalTo user
      }
    }

    "be associated with an address" in new WithApplication {
      val user = User(username = "user", password = "pass", address = Some(Address("street", "zip", "country")))
      User.insert(user)
      try User.findOneByUsername("user").get.address must be equalTo Some(Address("street", "zip", "country"))
      // finally User.remove(user)
    }
  }

  "A nil user" should {
    "not be retrievable" in new WithApplication {
      User.remove(User(username = "nil", password = "pass"))
      User.findOneByUsername("nil") must be equalTo None
    }
  }

  def withUser(test: (User) => Any) {
    val user = User(username = "user", password = "pass")
    User.insert(user)
    try test(user)
    finally User.remove(user)
  }
}