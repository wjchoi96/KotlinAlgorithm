/*
    프로그래머스 고득점 kit 
    해시 유형

    level 2

    구조대 : 119
    박준영 : 97 674 223
    지영석 : 11 9552 4432

    구조대는 지영석의 전화번호의 접두사

    접두어 : 어떤 단어의 머리에 덧붙이는말

    전화번호를 담은 배열에 어떤 번호가 다른 번호의 접두어인 경우가 있다면 false, 아니면 true를 리턴

    1<= n <= 1,000,000
    1<= 전화번호길이 <= 20
    중복된 전화번호 없음
    
*/

/*
    당장 생각나는 방법은 브루트포스로 모두 비교하는거밖에 없는데

    set으로 하기엔 index가 없으니 map으로 구현

    참고 : https://coding-grandpa.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A0%84%ED%99%94%EB%B2%88%ED%98%B8-%EB%AA%A9%EB%A1%9D-%ED%95%B4%EC%8B%9C-Lv-2-%EC%9E%90%EB%B0%94-Java
*/

fun main(args : Array<String>){
    solve3()
}

// 풀이가 2중 loop밖에 생각나지않아서 hash를 사용한 풀이를 검색해보고 구현
/*
    접미어가 되는 문자열을 체크하는게 아닌, 접미어를 포함하는 문자열을 체크하는것이다
    119 => 1, 11 를 접미어로 가지니 해당 key값이 map에 포함되는지 체크
    1195524421 => 1, 11, 119, 1195... 등을 접미어로 가지니 해당 key값이 map에 포함되어있나 체크

    substring(start, end) 는 end idx를 포함하지 않게 자른다

    정리하면
    자기 자신을 쪼개 접미어를 만들고, 해당 접미어가 map에 포함되어있나 체크
*/
private fun solve1(){
    val phoneArr = arrayOf( // false
        "119", "97674223", "1195524421"
    )
    // val phoneArr = arrayOf( // true
    //     "123","456","789"
    // )
    // val phoneArr = arrayOf( // false
    //     "12","123","1235","567","88"
    // )

    val map = HashMap<String, String>()
    for(i in 0 until phoneArr.size){
        val phone = phoneArr[i]
        map[phone] = phone
    }
    var isContain = false
    loop@for(i in 0 until phoneArr.size){
        val phone = phoneArr[i]
        // print("$phone : ${map.containsKey(phone)}\n")
        for(s in 0 until phone.length){
            // print("${phone.substring(0, s)}\n")
            if(map.containsKey(phone.substring(0, s))){
                // print("${phone.substring(0, s)} contain\n")
                isContain = true
                break@loop
            }
        }
    }
    if(!isContain){
        print("true\n")
    }else{
        print("false\n")
    }
}

/*
    2중 loop으로 구현
    자기 자신을 접미어로 포함하고 있는 값이 있는지 체크
*/
private fun solve2(){
     val phoneArr = arrayOf( // false
        "119", "97674223", "1195524421"
    )
    // val phoneArr = arrayOf( // true
    //     "123","456","789"
    // )
    // val phoneArr = arrayOf( // false
    //     "12","123","1235","567","88"
    // )

    var isContain = false
    loop@for(i in 0 until phoneArr.size){
        val phone = phoneArr[i]
        for(j in 0 until phoneArr.size){
            if(j == i){
                continue
            }
            for(p in 1 until phoneArr[j].length){
                if(phoneArr[j].substring(0, p).contains(phone)){
                    print("${phone.substring(0, p)} => ${phoneArr[j]}\n")
                    isContain = true
                    break@loop
                }
            }
        }
    }
    if(isContain){
        print("false\n")
    }else{
        print("true\n")
    }
}

/*
    2중 loop를 1중 loop로 개선 : sort 이후 startsWith 함수 사용
*/
private fun solve3(){
    // val phoneArr = arrayOf( // false
    //     "119", "97674223", "1195524421"
    // )
    val phoneArr = arrayOf( // true
        "123","456","789"
    )
    // val phoneArr = arrayOf( // false
    //     "12","123","1235","567","88"
    // )

    var isContain = false
    phoneArr.sort()
    for(i in 0 until phoneArr.size-1){
        if(phoneArr[i+1].startsWith(phoneArr[i])){
            print("${phoneArr[i+1]} start with ${phoneArr[i]}\n")
            isContain = true
        }
    }
    if(isContain){
        print("false\n")
    }else{
        print("true\n")
    }
}