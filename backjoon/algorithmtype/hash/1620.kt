// sliver 4
/*
    바킹독님 알고리즘 강의 중 해시 단원 연습문제


    도감에서 포켓몬의 이름을 보면 번호 출력
    포켓몬의 번호를 보면 이름을 출력

    포켓문의 개수 N
    맞춰야 하는 문제 M

    1<= N, M <= 100,000
    둘쨰줄부터 1번부터 N번까지의 포켓몬의 정보가 입력
    이름 : 영어, 첫글자 대문자, 일부값은 마지막 문자만 대문자
    2 <= 이름길이 <= 20

    그 다음부터 맞춰야 하는 문제m개가 한줄에 하나씩 입력

    이름, 번호가 주어지니 map을 사용
    이름을 key, 번호를 값으로 사용하자

    key값으로 value를 가져올 수 있지만
    value값으로 key값을 가져오는건 순회를 거쳐야한다

    두개다 저장해서 꺼내올까
*/
/*
    제출

    1. 성공
    이름, 번호를 각 key로 해서 둘다 저장하는 방식

    개선
    이름을 key로 번호를 value 로 지정해서 map에 저장
    번호를 idx로 이름을 value로 지정해서 list에 저장
    
    개선 제출
    1. 성공
*/

fun main(args : Array<String>){
    val bw = System.out.bufferedWriter()
    val br = System.`in`.bufferedReader()


    val (n, m) = br.readLine().split(' ').map{ it.toInt() }

    // number 와 name 을 따로 저장하는 방식
    val hashMap = HashMap<String, Int>()
    val nameList = ArrayList<String>()

    repeat(n) {
        val value = br.readLine()
        hashMap[value] = it+1
        nameList.add(value) // 꺼내올땐 -1 해줘야한다
    }
    for(i in 0 until m){
        val value = br.readLine().run{toIntOrNull() ?: this}
        if(value is Int) {
            bw.write("${nameList[value.toInt()-1]}\n")
        }else{
            bw.write("${hashMap[value]}\n")
        }
    }

    // 두쌍을 모두 map에 저장하는 방식
    // val hashMap = HashMap<String, String>()
    // for(i in 1 until n+1){
    //     val value = br.readLine()
    //     hashMap[i.toString()] = value
    //     hashMap[value] = i.toString()
    // }
    // for(i in 0 until m){
    //     bw.write("${hashMap[br.readLine()]}\n")
    // }

    bw.flush()
    bw.close()
    br.close()
}