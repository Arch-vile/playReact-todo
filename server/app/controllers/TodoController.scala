package controllers

import javax.inject._

import model.TodoItem
import play.api.libs.json.{JsError, Json}
import play.api.mvc._
import converters.json.JsonConverters._

@Singleton
class TodoController @Inject() extends Controller {

  def list = Action {

    Ok(Json.toJson(TodoItem("title",Set("some label","Other label"))))
  }


  def create = Action(BodyParsers.parse.json) { request =>
    val todoResult = request.body.validate[TodoItem]
    todoResult.fold(
      errors => {
        BadRequest(Json.obj("message" -> JsError.toJson(errors)))
      },
      todo => {
        Ok(Json.obj("message" -> "All Ok"))
      }
    )

  }

}
