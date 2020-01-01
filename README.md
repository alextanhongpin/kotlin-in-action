# Kotlin

## Linting

```bash
$ brew install ktlint
```

## Running

```kotlin
$ kotlinc main.kt -include-runtime -d main.jar
$ java -jar main.jar
```

In your `.zshrc` or `.bash_profile`, add this:

```
function kotlinr() {
  echo Compiling, please wait...
  kotlinc $1 -include-runtime -d out.jar
  java -jar out.jar
}
```
