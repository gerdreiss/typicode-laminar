package pro.reiss

object Domain:
  enum DisplayTarget:
    case USERS, USER, POST, ERROR

  final case class Geo(
      lat: String,
      lng: String
  )

  final case class Address(
      street: String,
      suite: String,
      city: String,
      zipcode: String,
      geo: Geo
  )

  final case class Company(
      name: String,
      catchPhrase: String,
      bs: String
  )

  final case class User(
      id: Int,
      name: String,
      username: String,
      email: String,
      phone: String,
      website: String,
      address: Address,
      company: Company
  )

  final case class Todo(
      userId: Int,
      id: Int,
      title: String,
      completed: Boolean
  )

  final case class Post(
      userId: Int,
      id: Int,
      title: String,
      body: String
  )

  final case class Comment(
      postId: Int,
      id: Int,
      name: String,
      email: String,
      body: String
  )

  final case class Users(
      users: List[User] = List.empty,
      todos: List[Todo] = List.empty,
      posts: List[Post] = List.empty,
      comments: List[Comment] = List.empty,
      error: Option[String] = None,
      displayTarget: DisplayTarget = DisplayTarget.USERS
  )

  object Users:
    def empty: Users = Users()
