# Classes

```kotlin
interface Clickable {
	fun click()
}

class Button : Clickable {
	override fun click() = println("I was clicked")
}

fun main() {
	Button().click()
}
```


Multiple interfaces:

```kotlin
interface Clickable {
	fun click()
	fun showOff() = println("I am clickable")
}

interface Focusable {
	fun setFocus(b: Boolean) =
		println("I ${if (b) "got" else "lost"} focus.")
	
	fun showOff() = println("I'm focusable")
}

class Button : Clickable, Focusable {
	override fun click() = println("I was clicked")

	override fun showOff() {
		super<Clickable>.showOff()
		super<Focusable>.showOff()
	}
	// If you only need to implement one of them.
	override fun showOff() = super<Clickable>.showOff()
}

fun main() {
	Button().click()
    Button().showOff()
}
```

Expressions as sealed class:

```kotlin
interface Expr
class Num(val value: Int): Expr
class Sum(val left: Expr, val right: Expr): Expr

fun eval(e: Expr): Int =
	when(e) {
		is Num -> e.value
		is Sum -> eval(e.right) + eval(e.left)
		else ->
			throw IllegalArgumentException("Unknown expression")
	}


// If you handle all subclasses of a sealed class in a when expression, you
// don't need to provide the default branch.
sealed class Expr {
	class Num(val value: Int): Expr()
	clas Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int =
	when (e) {
		is Expr.Num -> e.value
		is Expr.Sum -> eval(e.right) + eval(e.left)
	}
```

Initializing class:

```kotlin
open class User(val nickname: String)
class Twitter(nickname: String) : User(nickname) 

fun main() {
    	val twitterUser = Twitter("john")
        println(twitterUser.nickname)
}

```

Defining a class with multiple secondary constructor:

```kotlin
class User {
	val nickname: String
	constructor(email: String) {
		nickname = email.substringBefore("@")
	}

	constructor(facebookAccountId: Int) {
		nickname = getFacebookName(facebookAccountId)
	}
}
```

Replacing secondary constructor with factory methods:
```kotlin
class User private constructor(val nickname: String) {
	companion object {
		fun newSubscribingUser(email: String) =
			User(email.substringBefore("@"))
		
		fun newFacebookUser(accountId: Int) =
			User(getFacebookName(accountId))
	}
}
```
