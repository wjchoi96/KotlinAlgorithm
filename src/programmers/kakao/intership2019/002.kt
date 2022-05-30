/*
    카카오 2019 인턴 기출문제 2번
    level 2

    튜플

    셀수있는 수량의 순서있는 열거 또는 
    어떤 순서를 따르는 요소들의 모음을 튜플 이라고 한다

    n개의 요소를 지닌 튜플을 n-튜플 이라고 하며
    (a1, a2, a3, ..., an) 이라고 표현한다

    1. 중복된 원소가 있을 수 있다
    2. 원소에 순서가 있으며, 순서가 다르면 서로 다른 튜플
    3. 튜플의 원소 개수는 유한하다

    원소의 개수가n개, 중복되는 원소가 없는 튜플이 주어질때
    {,} 같은 집합기호를 이용해 표현 가능
    (a1, a2, a3, ..., an) 
    => {{a1}, {a1, a2}, {a1,a2,a3} ... {a1, a2, ... an}}

    집합은 원소의 순서가 바뀌어도 상관없다
    (2, 1, 3, 4)
    => {{2}, {2, 1}, {2, 1, 3}, {2, 1, 3, 4}}
    => {{2, 1}, {2}, {2, 1, 3}, {2, 1, 3, 4}}
    => {{2}, {2, 1, 3, 4}, {2, 1}, {2, 1, 3}}

    특정 튜플을 표현하는 집합 s가 매개변수로 주어질때 x가 표현하는 튜플을 배열에 담아 리턴

    중복방지 set에 넣으면 끝 아닌가?
    => set은 순서를 유지하지 않아서 탈락
    map
*/
/*
    5<= s <=1,000,000

    숫자와 {,} 로만 이루어져 있다
    숫자가 0으로 시작하는 경우는 없다

    리턴 배열의 길이가 1이상 500이하인 경우만 입력으로 주어진다
*/
/*
    문자열을 배열 단위로 쪼갠다
    1. 앞뒤 {}제거
    2. ,로 split
    3. 각 나눠진 집합의 앞뒤 {} 제거

    생각이 1도 안나서 이리저리 헤메면서 이것저것 하다가
    결국 손도 못댄수준으로 포기

    https://yline.tistory.com/144
    거의 보고 베낀수준

    => 대충 깨달음을 얻은듯
    => 문자열은 모르겠으면 대충 디버깅하면서 이리저리 만져보는게 답인듯
*/

fun main(args : Array<String>){
    val s = "{{1,2,3},{2,1},{1,2,4,3},{2}}"//"{{2},{2,1},{2,1,3},{2,1,3,4}}"
    Kakao2019002().solution(s)
}

private class Kakao2019002 {
    fun solution(s: String): IntArray {
        val res = s.split("},{").toMutableList()
        for(i in 0 until res.size){
            res[i] = res[i].replace("}", "").replace("{", "")
        }
        res.sortBy{it.count()}
        val result = ArrayList<Int>()
        for(str in res){
            val intRes = str.split(',').map{it.toInt()}
            for(v in intRes){
                if(!result.contains(v)){
                    result.add(v)
                }
            }
        }
        print("$result\n")
        return result.toIntArray()
    }
}

// https://yline.tistory.com/144 보고 진행
private class Kakao2019002SolveSeeCode {
    fun solution(s: String): IntArray {
        val res = s.substring(2, s.count()-2).split("},{").toMutableList()
        res.sortBy{it.count()}
        print("$res\n")
        val resList = ArrayList<Int>()
        for(i in 0 until res.size){
            val value = res[i].split(',').map{it.toInt()}
            for(v in value){
                if(!resList.contains(v)){
                    resList.add(v)
                    print("${v}\n")
                }
            }
        }
        print("$resList\n")
        return resList.toIntArray()
    }
}