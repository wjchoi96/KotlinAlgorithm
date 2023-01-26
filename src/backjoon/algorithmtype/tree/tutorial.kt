/*
    바킹독님 알고리즘 강의 중 Tree 단원
    https://blog.encrypted.gg/1019


    Tree에서의 BFS, DFS
    
    #BFS
    - 부모 정점은 이미 방문
    - 자식 정점들을 Queue에 넣어주면 됨
    -> 이웃한 정점들에 대해 부모 정점을 제외하고 전부 Queue에 넣어주면 됨
    때문에, visit배열이 필요없고, 부모가 누구인지에 대한 정보만을 가지면 됨(각 정점의 부모 정점을 기록할 배열에 저장)

    각 노드의 부모 정보를 BFS한번으로 알아낼 수 있음
    1. Root의 부모는 0
    2. Bfs를 진행하며, nextNode가 currentNode의 부모라면 contiune
    3. 아니라면 nextNode의 부모로 currentNode를 지정
    
    #BFS의 시간복잡도
    O(V+E)
    트리에서는 E = V - 1 이기에 
    O(V)가 됨

    #DFS
    DFS도 BFS와 유사하게, 자신과 연결된 정점들은 1개만 부모이고 나머지는 전부 자식이라는 성질을 이용해
    vis배열 대신, 부모 배열과 depth배열을 채우면서 처리 가능
    - BFS의 Queue를 Stack으로 변경

    1. Root의 부모는 0
    2. DFS를 진행하며, nextNode가 currentNode의 부모라면 continue
    3. 아니라면 nextNode의 부모로 currentNode를 지정
    4. depth는 currentNode의 depth + 1

    재귀를 이용하면 코드가 한결 간결해짐
    #단순 순회 재귀 DFS

    fun dfs(cur: Int, par: Int) {
        for(next in arr[cur]) {
            if(par == next) continue
            dfs(next, cur)
        }
    }

    #이진 트리의 데이터 저장
    - 자식 정점이 최대 2개인 트리
    - class로 Node(left, right)를 생성해 이진트리 정보 저장
    - left, right 배열을 따로 만들어, 각 Level별 왼쪽, 오른쪽 자식 Node정보 확인
    - Heap처럼 단일 배열로 저장해, Heap 공식으로 접근
        0. 편의상 1이 Root
        1. 루트노드번호 ×2면 왼쪽 자식
        2. 루트노드번호 ×2 +1 오른쪽자식    

    #이진 트리의 순회
    - Level, 전위, 중위, 후위 순회
    - 다른 트리라고 하더라도, 순회 결과가 일치할 수 있음
    - 2개의 순회 결과가 주어졌을때, 중위 순회가 포함되어있다면 유일하지만, 중위 순회가 포함되지 않았다면 유일하지 않음
    https://www.geeksforgeeks.org/if-you-are-given-two-traversal-sequences-can-you-construct-the-binary-tree/

    #이진트리의 Level 순회
    - Root를 시작점으로 BFS

    #이진트리의 전위 순회
    1. 현재 정점
    2. 왼쪽 서브트리를 전위 순회
    3. 오른 서브트리를 전위 순회
    - DFS와 방문 순서가 동일

    #이진트리의 중위 순회
    1. 왼쪽 서브트리를 중위 순회
    2. 현재 정점을 방문
    3. 오른쪽 서브 트리를 중위 순회
    - 이진 탐색 트리였다면, 자연스럽게 크기 순으로 순회
    - 재귀로 구현

    #이진트리의 후위 순회
    1. 왼쪽 서브트리를 후위 순회
    2. 오른쪽 서브트리를 후위 순회
    3. 현재 정점 방문

    #연습문제
    1. 백준 11725(solve)
    2. 백준 1991(solve)
*/