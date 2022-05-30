/*
    카카오 2021 인턴십 코테 1번문제

    숫자 문자열과 영단어
    level 1

    숫자의 일부 자릿수를 영단어로 바꾼 카드를 주면 원래 숫자를 찾는다

    각 숫자를 map에 저장해놓고
    key 값을 포함하고있다면 replace

    다른 풀이
    굳이 map에 저장안해놓고 일일히 replace를 하는 경우도 있다
    contains 는 호출 안해도 된다
*/

fun main(args : Array<String>){
    var s = "one4seveneight"
    // var s = "23four5six7"
    // var s = "2three45sixseven"
    // var s = "123"

    val map = HashMap<String, Int>()
    map["zero"] = 0
    map["one"] = 1
    map["two"] = 2
    map["three"] = 3
    map["four"] = 4
    map["five"] = 5
    map["six"] = 6
    map["seven"] = 7
    map["eight"] = 8
    map["nine"] = 9

    map.forEach{ k, v ->
        if(s.contains(k)){
            s = s.replace(k, v.toString())
        }
    }

    print("$s\n")
    print("${solution(s)}\n")
}

private fun solution(s: String): Int {
    var str = s
    val map = HashMap<String, Int>()
    map["zero"] = 0
    map["one"] = 1
    map["two"] = 2
    map["three"] = 3
    map["four"] = 4
    map["five"] = 5
    map["six"] = 6
    map["seven"] = 7
    map["eight"] = 8
    map["nine"] = 9

    map.forEach{ k, v ->
        if(s.contains(k)){
            str = str.replace(k, v.toString())
        }
    }
    return str.toInt()
}