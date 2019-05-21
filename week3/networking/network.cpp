#include <stdio.h>
#include <iostream>
#include <vector>
#include <sstream>
#include <algorithm>

//Source: https://github.com/kartikkukreja/blog-codes/blob/master/src/Union%20Find%20(Disjoint%20Set)%20Data%20Structure.cpp
class UF    {
  int *id, cnt, *sz;
public:
  // Create an empty union find data structure with N isolated sets.
  UF(int N)   {
    cnt = N;
    id = new int[N];
    sz = new int[N];
    for(int i=0; i<N; i++)	{
      id[i] = i;
      sz[i] = 1;
    }
  }
  ~UF()	{
    delete [] id;
    delete [] sz;
  }
  // Return the id of component corresponding to object p.
  int find(int p)	{
    int root = p;
    while (root != id[root])
    root = id[root];
    while (p != root) {
      int newp = id[p];
      id[p] = root;
      p = newp;
    }
    return root;
  }
  // Replace sets containing x and y with their union.
  void merge(int x, int y)	{
    int i = find(x);
    int j = find(y);
    if (i == j) return;

    // make smaller root point to larger one
    if   (sz[i] < sz[j])	{
      id[i] = j;
      sz[j] += sz[i];
    } else	{
      id[j] = i;
      sz[i] += sz[j];
    }
    cnt--;
  }
  // Are objects x and y in the same set?
  bool connected(int x, int y)    {
    return find(x) == find(y);
  }
  // Return the number of disjoint sets.
  int count() {
    return cnt;
  }
};

int main (){

    int t;
    std::cin >> t;
    for (int testcase = 1; testcase <= t; testcase++) {

        int n;
        std::cin >> n;

        std::vector< std::pair<int,std::pair<int,int> > > graph;

        for (int j = 1; j <= n; j++){
            for (int k = 1; k <= n; k++){
                if (k <= j){
                    int asd;
                    std::cin >> asd;
                    continue;
                }
                int in;
                std::cin >> in;
                std::pair< int,std::pair<int,int> > aux;
                aux.first = in;
                aux.second.first = j;
                aux.second.second = k;
                graph.push_back(aux);
            }
        }
        std::sort(graph.begin(), graph.end());
        UF uf(n+1);
        std::vector< std::pair <int,int> > res;

        for (int j = 0; j < graph.size(); j++){

            int u = uf.find(graph[j].second.first);
            int v = uf.find(graph[j].second.second);

            if (u != v){
                std::pair <int,int> aux;
                aux.first = graph[j].second.first;
                aux.second = graph[j].second.second;
                res.push_back(aux);
                uf.merge(u,v);
            }

        }

        //http://stackoverflow.com/questions/279854/how-do-i-sort-a-vector-of-pairs-based-on-the-second-element-of-the-pair
        std::sort(res.begin(), res.end(), [](const std::pair<int,int> &left, const std::pair<int,int> &right) {
            return left.second < right.second;
        });
        std::sort(res.begin(), res.end(), [](const std::pair<int,int> &left, const std::pair<int,int> &right) {
            return left.first < right.first;
        });

        std::cout << "Case #" << testcase <<":\n";
        for (int j = 0; j < res.size(); j++)
            std::cout << res[j].first << " " << res[j].second << "\n";
        if (res.size() == 0)
            std::cout << "\n";

    }
    return 0;
}
