#include <stdio.h>
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <string>
#include <unordered_map>

int main (){

    int t;
    std::cin >> t;
    for (int testcase = 1; testcase <= t; testcase++) {

        int n, m;
        std::cin >> n >> m;

        if (m == 0){
            std::cout << "Case #" << testcase << ": yes\n";
            continue;
        }

        std::unordered_map<int,std::vector<int> > graph(n);
        std::queue<int> queue;
        std::string res = "yes";


        int teams [n];
        std::fill_n(teams, n, -1);


        for (int i = 1; i <= m; i++) {
            int a, b;
            std::cin >> a >> b;
            graph[a-1].push_back(b-1);
            graph[b-1].push_back(a-1);
        }

        queue.push(graph.begin()->first);
        teams[graph.begin()->first] = 1;

        bool flag = true;

        while(!queue.empty()){

            int node = queue.front();

            for (int j = 0; j < graph[node].size(); j++){

                if(teams[graph[node][j]] == -1 ){
                    teams[graph[node][j]] = (teams[node] + 1) %2;
                    queue.push(graph[node][j]);
                } else if (teams[graph[node][j]] == teams[node]) {
                    flag = false;
                    break;
                    }
            }
            if (flag)
                queue.pop();
            else
                break;
        }
        if (!flag){
            res = "no";
        } else {
            res = "yes";
            int size = sizeof(teams)/sizeof(*teams);
            for (int j = 0; j < size; j++){
                if (teams[j] == -1){
                    res = "no";
                    break;
                }
            }
        }
        std::cout << "Case #" << testcase << ": " << res << "\n";
      }

    }
    return 0;
}
