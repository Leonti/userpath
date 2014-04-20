package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Address(
                    street: String,
                    zip: String,
                    country: String
                    )

object Address extends AddressJson

trait AddressJson {

  implicit val addressJsonWrite = Json.writes[Address]
  implicit val addressJsonRead = Json.reads[Address]

//  implicit val addressJsonWrite = new Writes[Address] {
//    def writes(a: Address): JsValue = {
//      Json.obj(
//        "street" -> a.street,
//        "zip" -> a.zip,
//        "country" -> a.country
//      )
//    }
//  }
//
//  implicit val addressJsonRead = (
//    (__ \ 'street).read[String] ~
//      (__ \ 'zip).read[String] ~
//      (__ \ 'country).read[String]
//    )(Address.apply _)
}