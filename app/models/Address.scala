package models

import play.api.libs.json._

case class Address(
                    street: String,
                    zip: String,
                    country: String
                    )

object Address extends AddressJson

trait AddressJson {
  implicit val addressJsonWrite = Json.writes[Address]
  implicit val addressJsonRead = Json.reads[Address]
}