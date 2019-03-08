package models

import play.api.libs.json.Json

case class Todo(id: Int, timestamp: Long, content: String, isComplete: Boolean)

object Todo{
  implicit val format = Json.format[Todo] 
}