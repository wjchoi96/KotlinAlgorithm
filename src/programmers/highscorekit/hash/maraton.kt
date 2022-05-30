//level 1
// 완주하지 못한 선수
/*
    프로그래머스 고득점 kit 해시 유형문제
    https://programmers.co.kr/learn/courses/30/lessons/42576?language=java

    마라톤에 참여한 선수 중 단 한명만이 완주하지 못하였다
    참여한 선수들의 이름 배열 participant
    완주한 선수들의 이름 completion 

    완주하지 못한 선수의 이름을 출력

    1<= n <= 100,000
    동명이인 존재 할 수 있다
*/
/*
    hash set 에 participant 목록을 저장하고 => x
    동명이인이 존재할 수 있으니 map 으로 변경
    completion을 순회하며 hash set에서 값을 지운다
    남은 값 : 완주하지 못한 선수

    getOrDefault 메소드를 알게 되었다
*/

fun main(args : Array<String>){
    val bw = System.out.bufferedWriter()

    val participant = arrayOf(
        "mislav", "stanko", "mislav", "ana" //"marina", "josipa", "nikola", "vinko", "filipa"
    )
    val completion = arrayOf(
        "stanko", "ana", "mislav" //"josipa", "filipa", "marina", "nikola"
    )

    val map = HashMap<String, Int>()
    for(p in 0 until participant.size){
        val name = participant[p]
        map[name] = map.getOrDefault(name, 0) + 1
    }
    for(c in 0 until completion.size){
        val name = completion[c]
        map[name] = map.get(name)!! - 1
    }

    map.forEach { k, v ->
        if(v != 0){
            bw.write("$k\n")
        }
    }

    bw.flush()
    bw.close()
}

// private fun solve2(){
//     val participant = arrayOf(
//         "mislav", "stanko", "mislav", "ana" //"marina", "josipa", "nikola", "vinko", "filipa"
//     )
//     val completion = arrayOf(
//         "stanko", "ana", "mislav" //"josipa", "filipa", "marina", "nikola"
//     )

//     val map = HashMap<String, Int>()
//     for(p in 0 until participant.size){
//         if(map.containsKey(participant[p])){
//             print("catch before : ${map.get(participant[p])}\n")
//             map[participant[p]] = map.get(participant[p])!! + 1
//             print("catch : ${map.get(participant[p])}\n")
//         }else{
//             map[participant[p]] = 1
//         }
//     }
//     bw.write("$map\n")
//     for(c in 0 until completion.size){
//         if(map.get(completion[c])?:0 > 1){
//             map[completion[c]] = map.get(completion[c])!! - 1
//         }else{
//             map.remove(completion[c])
//         }
//     }

//     map.forEach { k, _ ->
//         bw.write("$k\n")
//     }

//     bw.flush()
//     bw.close()
// }

// 동명이인이 있을 수 있다는 문제조건을 확인 못하고 set으로 구현
// private fun useSet(bw : bufferedWriter)[
//     val participant = arrayOf(
//         "marina", "josipa", "nikola", "vinko", "filipa"
//     )
//     val completion = arrayOf(
//         "josipa", "filipa", "marina", "nikola"
//     )
//     val set = HashSet<String>()
//     for(p in 0 until participant.size){
//         set.add(participant[p])
//     }
//     for(c in 0 until completion.size){
//         set.remove(completion[c])
//     }

//     set.forEach {
//         bw.write("$it\n")
//     }
//     bw.write("$set\n")
// ]