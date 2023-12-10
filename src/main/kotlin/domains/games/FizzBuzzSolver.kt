package domains.games

class FizzBuzzSolver {

    fun solve(number: Int): String {

        if(number % 15 == 0)
            return "FizzBuzz"

        if(number % 3 == 0)
            return "Fizz"

        if(number % 5 == 0)
            return "Buzz"

        return number.toString()
    }

}