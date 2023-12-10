package domains.games

class FizzBuzzSolver {

    fun solve(number: Int): String {
        if(number % 3 == 0)
            return "Fizz"
        return number.toString()
    }

}