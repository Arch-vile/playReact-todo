package converters.json

import model.TodoItem
import play.api.libs.json._
import play.api.libs.functional.syntax._

object JsonConverters {

  implicit val todoWrites = new Writes[TodoItem] {
    def writes(todo: TodoItem) = Json.obj(
      "title" -> todo.title,
      "labels" -> todo.labels
    )
  }

  implicit val todoReads: Reads[TodoItem] = (
    (JsPath \ "title").read[String]
      and
      (JsPath \ "labels").read[Set[String]]
    ) (TodoItem.apply _)


}
