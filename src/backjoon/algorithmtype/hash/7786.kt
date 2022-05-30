/*
    바킹독님 알고리즘 강의 중 해시 단원 연습문제
    이분탐색 학습 후 이분탐색으로도 풀어보자

    enter name => 출근
    leave name => 퇴근

    로그를 통해 현재 회사에 남아있는 모든 사람을 구하라
    회사에 동명이인이 없다 => set, key 값으로 사용 가능
    대소문자가 다른경우 다른 이름
    name : 알파벳 대소문자로 이루어진 5글자 이하의 문자열
    2 <= n <= 10^6
*/
/*
    enter: name을 hash map에 저장
    leave: hash map에서 해당 name을 찾아 제거

    사전순의 역순으로 출력하라

    hash collection 들의 사용법을 익히고 넘어간다 생각하자
*/
fun main(args : Array<String>){
    val bw = System.out.bufferedWriter()
    val br = System.`in`.bufferedReader()

    val mMap : MutableMap<String, String> = HashMap() // HashMap<String, String> = HashMap()
    val hashSet = HashSet<String>()
    val n = br.readLine().toInt()
    for(i in 0 until n){
        val (name, operator) = br.readLine().split(' ')
        when(operator){
            "enter" -> {
                mMap[name] = name  //mMap.put(name, name)
                hashSet.add(name)
            }
            "leave" -> {
                mMap.remove(name)
                hashSet.remove(name)
            }
        }
    }

    // map keys to array list (values : 값들)
    // val keys = ArrayList(mMap.keys).apply{
    //     sort()
    //     reverse()
    // }
    // for(k in keys){
    //     print("$k\n")
    // }

    // map keys to array 
    // val mapToArray = mMap.keys.toTypedArray().apply {
    //     sort()
    //     reverse()
    // }
    // for(k in 0 until mapToArray.size){
    //     print("${list[k]}\n")
    // }

   
    // set to array
    // val setToArray = hashSet.toTypedArray().apply{
    //     sort()
    //     reverse()
    // }
    // for(k in 0 until setToArray.size){
    //     print("${list[k]}\n")
    // }

    val setToList = ArrayList(hashSet).apply {
        sort()
        reverse()
    }
    for(k in setToList){
        print("$k\n")
    }
    

    bw.flush()
    bw.close()
    br.close()
}
