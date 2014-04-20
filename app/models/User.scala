package models

import play.api.Play.current
import java.util.Date
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import play.api.libs.json._

import se.radley.plugin.salat._
import se.radley.plugin.salat.Binders._
import MongoContext._

case class User(
                 id: ObjectId = new ObjectId,
                 username: String,
                 password: String,
                 address: Option[Address] = None,
                 added: Date = new Date(),
                 updated: Option[Date] = None,
                 @Key("company_id") company: Option[ObjectId] = None
                 )

object User extends UserDAO with UserJson

trait UserDAO extends ModelCompanion[User, ObjectId] {
  def collection = mongoCollection("users")

  val dao = new SalatDAO[User, ObjectId](collection) {}

  // Indexes
  collection.ensureIndex(DBObject("username" -> 1), "user_email", unique = true)

  // Queries
  def findOneByUsername(username: String): Option[User] = dao.findOne(MongoDBObject("username" -> username))

  def findByCountry(country: String) = dao.find(MongoDBObject("address.country" -> country))
}

trait UserJson {
  implicit val userJsonWrite = Json.writes[User]
  implicit val userJsonRead = Json.reads[User]
}