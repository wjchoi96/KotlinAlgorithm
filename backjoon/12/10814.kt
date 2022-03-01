// sliver 5
// 12-9

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st : StringTokenizer

    val size = br.readLine().toInt()
    val a = Array<Member>(size){Member(0,"",0)}

    for(i in 0 until size){
        st = StringTokenizer(br.readLine())
        a[i] = Member(st.nextToken().toInt(), st.nextToken(), i)
    }

    quickSort4(a, 0, size-1)

    for(i in a){
        bw.write("${i.age} ${i.name}\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

// 버블은 시간초과고 ㅅㅂ
private fun bubbleSort(a : Array<Member>, size : Int){
    for(i in 0 until size){
        for(j in size-1 downTo i+1){
            if(a[j].age < a[j-1].age){
                swap(a, j, j-1)
            }
        }
    }
}

// 퀵은 idx 조절이 안되고 ㅅㅂ
private fun quickSort4(a : Array<Member>, start : Int, end : Int){
    var pl = start
    var pr = end
    val x = a[(start + end)/2]

    print("a[$start] ~ a[$end] : {\n")
    for(i in start until end+1){
        print("    (${a[i].age}, ${a[i].name})\n")
    }
    print("}\n")

    do{
        // pl포인터의 값이 피벗보다 뒤에 있어야 하는 값이 나올때까지 반복
        while(a[pl].compare(x) > 0)pl++
        // pr포인터의 값이 피벗보다 앞에 있어야 하는 값이 나올때가지 반복
        while(a[pr].compare(x) < 0)pr--
        if(pl<=pr){
            swap(a, pl++, pr--)
        }
    }while(pl<=pr)

    if(start<pr) quickSort4(a, start, pr)
    if(pl<end) quickSort4(a, pl, end)
}

private fun swap(a : Array<Member>, idx1 : Int, idx2 : Int){
    val t = a[idx1]
    a[idx1] = a[idx2]
    a[idx2] = t
}

class Member internal constructor(
    val age : Int,
    val name : String,
    val idx : Int
){
    // 내가 앞쪽에 있어야하면( 나이가 적거나, 더 먼저 입력받았다면 ) 양수 리턴
    fun compare(other : Member) : Int{
        if(age == other.age){
            // 내 Idx 가 앞쪽이면 양수 리턴
            return other.idx - idx
        }else{
            // 내 나이가 더 적으면 양수 리턴
            return other.age - age
        }
    }

}

/*
    1. 나이순
    2. 나이가 같다면 가입한 순 ( 입력받은 순서 -> idx 가 낮은순 )
    - idx가 낮은순 -> 그대로 두면 된다
*/