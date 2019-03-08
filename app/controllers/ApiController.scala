package controllers

import repositories.{TodoList}
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.Json

@Singleton
class ApiController @Inject()(cc: ControllerComponents, todoList: TodoList) extends AbstractController(cc){
  def getAllTodos = Action { implicit request => 
    Status(200)(Json.toJson(todoList.getAllTodos))
  }

  def getTodos(id: Int) = Action { implicit request => 
    todoList.getTodos(id) match {
      case Some(todos) => Status(200)(Json.toJson(todos))
      case None => Status(404)
    }
  }

  def addTodo = Action(parse.json) { implicit request => 
    val body = request.body
    val content = (body \ "content").as[String] 

    Status(200)(Json.toJson(todoList.addTodo(content)))
  }

  def updateTodo(id: Int) = Action(parse.json) { implicit request =>
    val body = request.body
    val content = (body \ "content").as[String]

    todoList.updateTodo(id, content) match {
      case Some(todos) => Status(200)(Json.toJson(todos))
      case None => Status(404)
    }
  }

  def removeTodo(id: Int) = Action { implicit request => 
    todoList.removeTodo(id) match {
      case Some(todos) => Status(200)(Json.toJson(todos))
      case None => Status(404)
    }

    
  }
}

