/*
    2020 카카오 인턴 기출문제 2번
    level 2

    수식 최대화

    +, -, * 3종류의 연산자

    연산자 우선순위 문젠데..
    백트래킹, 스택?

    숫자 배열 준비
    연산자 배열 준비

    연산자의 우선순위 조합은 6가지뿐
    우선순위에 맞는 연산자의 인덱스를 연산자 배열에서 찾아서 진행
    num[i] operator[i] num[i+1]
    num i, numi+1 제거


    numbers
    operators
    로 분리
    operators 배열을 순회하면서 연산자를 뽑는다.(매번 순서가 다르게)
    => priority(우선순위)

    우선순위 1부터 계산
    operator을 순회하면서 우선순위1순위를 만나면
    numbers[i], operators[i], numbers[i+1] 수행

    그리고 그 결과를 어떻게 전달을하지

    operators 만 분리
    operators를 이용해서 priority배열을 생성(우선순위, idx가 빠른 item이 우선순위가 높은것)

    priority를 순회하면서
    원본을 subString 한다

    // https://programmers.co.kr/questions/11811
    numStack
    opStack

    숫자[i+1], op[i] 를 스택에 체크 후 넣는다
    이번 op[i]가 현재 계산해야하는 우선순위가 아니라면 push
    맞다면 숫자[i+1] op[i] 숫자[pop] 을 한 후 계산 결과인 숫자를 push

    계산이 맞다면 끝나고 숫자는1개 연산자는0개여야 한다
    
*/
/*
    제출

    1. 70.0 / 100.0
    [11, 12, 13, 14, 15, 24, 27, 28, 29] 실패
    => int 값 초과;

    2. 성공
    => solve1

*/
/*
    개선
    1. 특정 연산자를 계산해버리는방법
    - number, operator 을 list로 사용
    - number[i] operator[i] number[i+1] 수행 후
    해당 값을 number[i+1]에 대입, remove number[i], remove operator[i]
    
        remove 는 O(N)
        연산자 하나당 o(N)작업을 2개

        장점은 다음 idx를 indexOfFirst를 통해 빠르게 나아가는것이나
        indexOfFirst도 o(N)아닌가

        그러면 연산자 하나당 o(N) 3개

        내방식은 stack to list 할때 O(N)2번이 끝
        => 연산자의 한 종류를 모두 끝마칠때
        나머지는 모두 o(1)
        => 내가 푼 방식이 더 나은듯. 가독성만 올려볼까

        => 다만 linkedList 라면 remove시간복잡도가 O(1)이다
        => LinkedList 라면 이게 더 보기도좋고 빠를것
    
    => Kakako002Solve2 성공

*/
import java.util.Stack
fun main(args : Array<String>){
    val str = "100-200*300-500+20" 
    // val str = "50*6-3*2"
    // val str = "200-300-500-600*40+500+500" // 1248000
    // int값 초과
    // val str = "177-661*999*99-133+221+334+555-166-144-551-166*166-166*166-133*88*55-11*4+55*888*454*12+11-66+444*99" //6083974714
    Kakako002Solve2().solution(str)
}


// ============== 성공 ==================
private class Kakako002Solve2 {
    private val prioritys = arrayOf(
        arrayOf('*', '+', '-'),
        arrayOf('*', '-', '+'),
        arrayOf('+', '*', '-'),
        arrayOf('+', '-', '*'),
        arrayOf('-', '*', '+'),
        arrayOf('-', '+', '*')
    )
    
    fun solution(expression: String): Long {
        var numbersStr = expression.replace("*", " ")
        numbersStr = numbersStr.replace("+", " ")
        numbersStr = numbersStr.replace("-", " ")

        var numbers = numbersStr.split(' ').map{it.toLong()}.toMutableList()
        var operators = MutableList<Char>(numbers.size-1){' '}
        var oIdx = 0
        for(i in 0 until expression.count()){
            if(!expression[i].isDigit()){
                operators[oIdx++] = expression[i]
            }
        }

        var max : Long = 0
        repeat(prioritys.size){
            val tNumbers = numbers.toMutableList()
            val tOperators = operators.toMutableList()
            for(i in 0 until prioritys[it].size){
                doCalculation(prioritys[it][i], tNumbers, tOperators)
            }
            val res = tNumbers.first()
            max = Math.max(max, Math.abs(res))
        }
        print("max : $max\n")
        return max
    }

    // 하나의 operator 을 끝내버린다
    private fun doCalculation(operator : Char, numbers : MutableList<Long>, operators : MutableList<Char>){
        print("start[$operator]: $numbers\n")
        var i = operators.indexOfFirst { it==operator }
        while(i>=0){
            if(operators[i] == operator){
                numbers[i+1] = calculation(numbers[i], numbers[i+1], operator)
                numbers.removeAt(i)
                operators.removeAt(i)
                print("n : $numbers\n")
            }
            i = operators.indexOfFirst { it==operator } // for문을 통해 size로 접근하면 중간에 remove를 수행하기때문에 나중에 index out of range 뜬다
        }
    }

    private fun calculation(x : Long, y : Long, op : Char) : Long {
        // print("$x $op $y\n")
        return when(op){
            '+' -> x+y
            '*' -> x*y
            '-' -> x-y
            else -> x+y
        }
    }

}


// ============== 성공 ==================
private class Kakako002Solve1 {
    private val prioritys = arrayOf(
        arrayOf('*', '+', '-'),
        arrayOf('*', '-', '+'),
        arrayOf('+', '*', '-'),
        arrayOf('+', '-', '*'),
        arrayOf('-', '*', '+'),
        arrayOf('-', '+', '*')
    )
    private val nStack = Stack<Long>()
    private val oStack = Stack<Char>()
    fun solution(expression: String): Long {
        var numbersStr = expression.replace("*", " ")
        numbersStr = numbersStr.replace("+", " ")
        numbersStr = numbersStr.replace("-", " ")

        var numbers = numbersStr.split(' ').map{it.toLong()}
        var operators = Array<Char>(numbers.size-1){' '}
        var oIdx = 0
        for(i in 0 until expression.count()){
            if(!expression[i].isDigit()){
                operators[oIdx++] = expression[i]
            }
        }
        return doCalcuation(numbers.toTypedArray(), operators)
    }

    private fun doCalcuation(oNumbers : Array<Long>, oOperators : Array<Char>) : Long {
        var max : Long = 0
        repeat(6){
            val priority = prioritys[it]
            var numbers = oNumbers
            var operators = oOperators
            for(pIdx in 0 until priority.size){
                val p = priority[pIdx]
                nStack.push(numbers[0])
                for(i in 0 until operators.size){
                    nStack.push(numbers[i+1])
                    oStack.push(operators[i])
                    if(operators[i] == p){
                        val numBack = nStack.pop()
                        val numFirst = nStack.pop()
                        nStack.push(calculation(numFirst, numBack, oStack.pop()))
                    }
                }
                numbers = nStack.toList().toTypedArray()
                operators = oStack.toList().toTypedArray()
                nStack.clear()
                oStack.clear()
            }
            val res = Math.abs(numbers.first())
            max = Math.max(max,res.toLong())
        }
        return max
    }

    private fun calculation(x : Long, y : Long, op : Char) : Long {
        print("$x $op $y\n")
        return when(op){
            '+' -> x+y
            '*' -> x*y
            '-' -> x-y
            else -> x+y
        }
    }

}

