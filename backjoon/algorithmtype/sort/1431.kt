/*
    바킹독님 알고리즘 강의 중 정렬 응용 연습문제
    sliver 3

    시리얼 번호

    다솜이는 여러개의 기타를 가지고있는데, 기타를 빨리 찾기 위해 기타를 시리얼 번호 순서대로 정렬하고자 한다

    시리얼 번호 A가 B의 앞에 오는 경우는 다음과 같다
    1. A,B의 길이가 서로 다르면 짧은것이 앞으로 온다
    2. 길이가 같다면, A,B 각각 모든 자리수의 합을 더해 작은 합을 가지는것이 앞으로 온다
    (숫자만 더한다)
    3. 1,2번으로 비교할 수 없다면 사전순으로 비교
    (숫자가 알파벳보다 사전순으로 작다)
*/
/*
    1<= N <= 50
    시리얼 번호의 길이는 최대 50
    알파벳 대문자, 숫자로만 이루어져있다
*/
/*
    comparator, comparable, sortWith 에 대해 한번 더 깊게 공부가 되었다

    comparator, comparable 차리
    https://st-lab.tistory.com/243

    compareable 은 자기자신과 인자로 들어오는 인자의 비교
    comparator 은 2개의 인자를 서로 비교

    리턴값은 1, 0, -1을 주로 사용
    A가 B보다 클때, 해당사실을 알리고싶다면 1 리턴
    A==B => 0
    A<B => -1
    를 보통 return A-B로 나타낸다
    
    sortWith 의 인자로는 compareBy를 사용해 여러개의 comparable을 보내거나
    compartor을 받는다
*/
/*
    제출
    1. 틀렸습니다(79%)
    - 3번 조건 처리중, 둘다 숫자일때의 대소관계를 처리 하지 않았다

    2. 성공
*/
/*
    개선
    1. 결과 출력 코드
    2. string 비교 코드
    - 굳이 일일히 해줄 필요없이 아스키 코드값으로 비교를 해주는 comparTo 를 사용하거나 
    - 대소관계에 따른 출력값을 설정해주면 된다
    - 근데 테스트할때는 왜 틀렸던거지
    - tc가 잘못되었던것=> serialSum으로 걸러져서 뒤로 가게 되었던것이다

    3. 그렇다면 comparable로 해도 될것같은데
*/
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    println("${"12a".compareTo("aaa")}")

    val n = br.readLine().toInt()
    val arr = Array(n) { "" }
    repeat(n){
        arr[it] = br.readLine()
    }
    arr.sortWith(compareBy(
        {it.count()},
        {it.serialSum()},
        {it}
    ))
    arr.forEach { println("$it") }

    bw.flush()
    br.close()
    bw.close()
}
private fun String.serialSum(): Int{
    var sum = 0
    for(i in 0 until count()){
        if(this[i].isDigit()){
            sum+=this[i].toString().toInt()
        }
    }
    return sum
}

private fun solve2(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val arr = Array(n) { "" }
    repeat(n){
        arr[it] = br.readLine()
    }
    arr.sortWith(Comparator<String>{ a,b ->
        when {
            a.count() < b.count() -> -1
            a.count() > b.count() -> 1
            a.serialSum() < b.serialSum() -> -1
            a.serialSum() > b.serialSum() -> 1
            else -> if(a>b)1 else -1 //a.compareTo(b)
        }
    })
    arr.forEach { println("$it") }

    bw.flush()
    br.close()
    bw.close()
}

private fun solve1(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val arr = Array(n) { "" }
    repeat(n){
        arr[it] = br.readLine()
    }
    arr.sortWith(Comparator<String>{ a,b ->
        when {
            a.count() > b.count() -> 1
            a.count() < b.count() -> -1
            a.serialSum() < b.serialSum() -> 1
            a.serialSum() > b.serialSum() -> -1
            else -> {
                var res = 0
                for(i in 0 until a.count()){
                    val ai = a[i]
                    val bi = b[i]
                    when{
                        ai.isDigit() && !bi.isDigit() -> {
                            res = -1
                            break
                        }
                        !ai.isDigit() && bi.isDigit() -> {
                            res = 1
                            break
                        }
                        else -> {
                            res = ai-bi
                            if(res == 0) continue
                            else break
                        }
                    }
                }
                res
            }
        }
    })
    arr.forEach { println("$it") }

    bw.flush()
    br.close()
    bw.close()
}