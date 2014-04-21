package controllers

import play.api.mvc._
import play.api.libs.json._
import models._
import controllers.Actions._

import com.mongodb.casbah.WriteConcern
import se.radley.plugin.salat.Binders._

object Users extends Controller {

  def index() = Action {
    val users = User.findAll().toList
    Ok(Json.toJson(users))
  }

  def create = JsonAction[User] { user =>
    User.save(user, WriteConcern.Safe)
    Ok(Json.toJson(user))
  }

  def view(id: String) = Action {

    val user = User.findOneById(new ObjectId(id))
    Ok(Json.toJson(user))
  }

  def update(id: String) = JsonAction[User] { requestUser =>
    val user = requestUser.copy(new ObjectId(id))
    User.save(user, WriteConcern.Safe)
    Ok(Json.toJson(user))
  }

  def delete(id: String) = Action {
    User.removeById(new ObjectId(id))
    Ok("")
  }
}
