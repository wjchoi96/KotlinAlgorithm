package src.backjoon.algorithmtype.backtracking
// gold4
/*
    네이버 카페 문제50선 중 백트래킹, 완전탐색 유형

    좋은 수열

    숫자 1,2,3 으로만 이루어지는 수열이 있다
    임의의 길이에 인접한 두 개의 부분 수열이 동일한것이 있다면 => 나쁜수열
    예)
    33
    32121323
    123123213

    그렇지 않다면 => 좋은수열
    예)
    2
    32
    32123
    1232123

    길이가 N인 좋은 수열들을 N자리의 정수로 보아, 그중 가장 작은 수를 구하라
    1<= N <= 80

    1초
*/
/*
    1,2,3 으로만 이루어져있다
    길이가 n

    1. 중복된 수 픽업 가능

    단순히 생각나는건
    1. 수를 뽑고, 뽑힌 숫자들을 탐색하며 나쁜수열인지 판단
    2. 지금 이 수를 뽑으면 나쁜수열이 되는지 판단

    나쁜수열 판단 방법
    - 인접한 두개의 부분수열이 동일하다면
    1. 같은 수가 연속으로 올때
    2. 현재idx 부터 2, 3, 4 단위로 수열을 쪼갰을때 동일한게 있다면
*/
/*
    제출
    1. 런타임에러(9%) : NumberFormat
    - 80자리의 숫자를 Int형으로 변환 시도 -> 펑
    - 비교할 필요없이 무조건 첫번째 나오는 착한수열이 최소값이다
    => 1부터 시작하는 dfs이기 때문

    2. 맞았습니다

    개선점
    1. res 배열 없이 string 으로만 진행해보자
    => 맞았습니다

    


*/


import java.io.*
private lateinit var res : Array<Int>
private var n = 0
private lateinit var bw : BufferedWriter
fun main(args : Array<String>){
    bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    n = br.readLine().toInt()
    br.close()
    res = Array(n){0}

    dfs2()

    bw.flush()
    bw.close()
}

private fun dfs2(depth : Int = 0, res : String = ""){
    if(depth == n){
        bw.write("$res\n")
        bw.flush()
        bw.close()
        System.exit(0)
        return
    }
    for(i in 1 until 4){
        val str = res + i.toString()
        if(checkBadStr(str)){
            dfs2(depth+1, str)
        }
    }
}

// 범위가 1 ~ size/2+1 이기 때문에
// fIdx, sIdx 가 음수가 될만한 범위에서 실행을 하지 않는다
// fIdx 는 당연히 음수가 될수 없고, sIdx는 1~size까지 범위를 잡으면 음수가 나올 수 있다
private fun checkBadStr(res : String) : Boolean{
    val size = res.count()
    //마지막부터 1:1, 2:2, 3:3 ... size/2:size/2 비교
    for(c in 1 until size/2+1){
        val fIdx = size - c
        val sIdx = size - (2*c)
        val f = res.substring(fIdx until size)
        val s = res.substring(sIdx until fIdx)
        if(f == s){
            return false
        }
    }
    return true
}


// solve 1
/*
    [1,2,3] 을 뽑는다
    N개까지
*/
private fun dfs(depth : Int = 0){
    if(depth == n){
        print("good : ")
        for(i in 0 until n){
            print("${res[i]} ")
        }
        print("\n")
        var str = ""
        for(i in 0 until n){
            str += res[i].toString()
        }
        bw.write("$str\n")
        bw.flush()
        bw.close()
        System.exit(0)
        return
    }
    for(i in 1 until 4){
        res[depth] = i
        if(checkBadArr(depth)){
            dfs(depth + 1)
        }
    }
}


// appendIdx : 방금 추가된 item의 idx, 해당 idx로 접근하면 item이 존재한다
private fun checkBadArr(appendIdx : Int) : Boolean { 
    val size = appendIdx + 1
    var str = ""
    for(i in 0 until size){
        str += res[i].toString()
    }
    print("str[$appendIdx] : $str\n")
    for(childSize in 1 until size/2+1) {
        // 1, 2, 3, 4 ... size/2 까지 순회
        // 마지막 childSize개와 그 앞 childSize가 동일한지 체크
        val firstIdx = size - childSize
        val secondIdx = size - (childSize*2)
        // print("f : $firstIdx, s : $secondIdx, c : $childSize s : ${size/1}\n")
        if(firstIdx < 0 || secondIdx < 0) {
            continue
        }
        val first = str.substring(firstIdx until size)
        val second = str.substring(secondIdx until firstIdx)
        print("$first : $second\n")
        if(first == second){
            print("bad : $str\n")
            return false
        }
    }

    return true
}

/*
    idx = 1 => size = 2
    for(i in 1 until size/2){
        if(size/i != 0){ 현재 size/2가 i로 나뉜다면 => size/2 가 기준인 이유는 부분수열이 되려면 적어도 절반의 크기는 되어야 하기 때문이다 ( 80 => 40, 40 / 80 => 39,41 이건 동일한 부분수열이 아니다)
            // i 크기의 부분 수열을 체크해야한다
        }
    }
    
    근데 그럼 80번째 item을 놓을때는
    1, 2, 3, ... 40 까지 부분수열을 체크해줘야하나?

    !!!!!!!!!!!!
    //https://bellog.tistory.com/43
    마지막에 추가된 수를 기준으로
    마지막 1개와 그 앞 1개가 동일한지
    마지막 2개와 그 앞 2개가 동일한지
    마지막 3개와 그 앞 3개가 동일한지
    ...
    이런식으로 검색
    size + size =< arrSize 일떄만 가능

    appendIdx : 8
    size : 9

    1,2,3,4 => childIdx

    res[8], res[7] => first 8, second 7
    res[7]res[8], res[5]res[6] => first 7, second 5
*/