/*
    바킹독님 알고리즘 강의 중 스택-수식의 괄호 쌍 단원 연습문제
    sliver 1

    괄호의 값

    4개의 기호
    (,),[,] 를 이용해서 만들어지는 올바른 괄호열이란 다음과 같이 정의된다

    1. 한 쌍의 괄호로만 이루어진 (), []는 올바른 괄호열이다
    2. 만일 x가 올바른 괄호열이라면 (x), [x] 또한 올바른 괄호열이다
    3. x,y 가 올바른 괄호열이라면 이들을 결합한 xy도 올바른 괄호열이다

    (()[[]]) 나 (())[][] 는 올바른 괄호열
    ([)] 나 (()()[] 는 올바른 괄호열이 아니다

    어떠한 올바른 괄호열 x에 대하여 그 값을 정의하고 값(x) 라고 표현

    1. () : 2
    2. [] : 3
    3. (x) : 2*x
    4. [x] : 3*x
    5. 올바른 괄호열 x와 y가 결합된 xy의 괄호값은 값(xy) = 값(x)+값(y)로 계산된다
    
    (()[[]])([])
    (2[3])(3)
    (11)6
    22+6 = 28
*/
/*
    괄호값을 계산할 타이밍
    닫힌 괄호가 호출되었을때 값을 계산

    1. 열린괄호 push
    2. 닫힌괄호 만나면 pop
    - 현재 괄호와, pop된 괄호를 조합해 점수를 계산. 점수 push
    - 닫힌 괄호인데, pop했더니 숫자가 나오면 일치하는 닫는 괄호가 나올떄까지 계속 pop, 중간에 나오는 숫자는 모두 더한다
    - 일치하는 닫힌괄호가 나오면 해당 괄호값 * 중간에 나오는 숫자들의 합, push
    3. 문자열 순회가 끝나면 stack 을 모두 pop해 더해준다

    stack 을 char 형으로 한뒤 isDigit() 를 통해 숫자인지 판별해야할듯

    !! 어라 올바르지 않은 괄호도 나오네
    그건 그냥 건너뛰면 될듯
    pop해버려
    만일 입력이 올바르지 못한 괄호열이면 반드시 0을 출력해야 한다. 

    char 형이면 숫자를 push 할 수가 없는디
*/
/*
    제출
    1. 틀렸습니다(0%)
    -> 올바르지 않은 문자열 0 출력

    2. 틀렸습니다(44%)
    -> 스택이 빈 경우를 추가해봄

    3. 틀렸습니다(44%)
    -> 전혀 다른 값이 들어가는 경우를 처리

    4. 틀렸습니다(44%)
    -> 문자열 순회가 끝나고 스택에 숫자가 아닌 값이 있다면 올바르지않은 괄호열
    => [()

    5. 틀렸습니다(95%)
    => ]() 
    => ] 가 먼저 나오면 push가 안되니까;
    => stack is empty 검사한 코드를 빼면 안되는거였네

    6. 성공

    반례가 굉장히 많네;
    예상대로 올바르지 않은 문자열을 잡아내는데 오류가 있었던것
    1. 주어진 괄호 이외의 값이 들어오는 경우를 누락
    2. 문자열 순회가 끝나고 스택에 숫자가 아닌 값이 남아있어선 안되는것을 누락
    3. 닫는 괄호가 입력되었는데, stack 이 비어있는 경우를 누락( 닫는 괄호가 먼저 들어오는 경우 )

*/

import java.util.Stack
fun main(args : Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   
    val stack = Stack<String>()
    
    var isGoodValue = true
    br.readLine().forEach {
        when(it){
            '(', '[' -> stack.push(it.toString())
            ')' -> {
                if(stack.isEmpty()){
                    isGoodValue = false
                }
                var sum = 0
                while(!stack.isEmpty()){
                    val pop = stack.pop()
                    if(pop == "("){
                        val value = 2 * (if(sum==0)1 else sum)
                        stack.push(value.toString())
                        break
                    }else if(pop != ")" && pop != "]" && pop != "["){
                        sum += pop.toInt()
                    }else{
                        isGoodValue = false
                        break
                    }// 다른값이 나오면 올바르지 않은 괄호열
                }
            }
            ']' -> {
                if(stack.isEmpty()){
                    isGoodValue = false
                }
                var sum = 0
                while(!stack.isEmpty()){
                    val pop = stack.pop()
                    if(pop == "["){
                        val value = 3 * (if(sum==0)1 else sum)
                        stack.push(value.toString())
                        break
                    }else if(pop != ")" && pop != "]" && pop != "("){
                        sum += pop.toInt()
                    }else{
                        isGoodValue = false
                        break
                    }// 다른값이 나오면 올바르지 않은 괄호열
                }
            }
            else -> isGoodValue = false
        }
    }
    if(!isGoodValue){
        bw.write("0\n")
    }else{
        var sum = 0
        while(!stack.isEmpty()){
            val pop = stack.pop()
            println("pop : $pop")
            when(pop) {
                "(", ")", "[", "]" -> {
                    println("catch")
                    sum = 0
                    break
                }
                else -> sum+=pop.toInt()
            }
        }
        bw.write("$sum\n")
    }

    bw.flush()
    bw.close()
    br.close()
}